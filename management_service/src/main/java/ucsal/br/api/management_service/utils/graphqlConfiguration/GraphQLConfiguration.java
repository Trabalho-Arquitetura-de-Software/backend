    package ucsal.br.api.management_service.utils.graphqlConfiguration;

    import graphql.execution.CoercedVariables;
    import graphql.language.StringValue;
    import graphql.language.Value;
    import graphql.schema.*;
    import graphql.GraphQLContext;
    import org.springframework.context.annotation.Bean;
    import org.springframework.context.annotation.Configuration;
    import org.springframework.graphql.execution.RuntimeWiringConfigurer;

    import java.time.LocalDate;
    import java.time.format.DateTimeFormatter;
    import java.time.format.DateTimeParseException;
    import java.util.Locale;

    @Configuration
    public class GraphQLConfiguration {

        @Bean
        public GraphQLScalarType dateScalar() {
            return GraphQLScalarType.newScalar()
                    .name("Date")
                    .description("Data no formato ISO (yyyy-MM-dd)")
                    .coercing(new Coercing<LocalDate, String>() {

                        private final DateTimeFormatter formatter = DateTimeFormatter.ISO_LOCAL_DATE;

                        @Override
                        public String serialize(Object dataFetcherResult, GraphQLContext context, Locale locale)
                                throws CoercingSerializeException {
                            if (dataFetcherResult instanceof LocalDate) {
                                return ((LocalDate) dataFetcherResult).format(formatter);
                            }
                            throw new CoercingSerializeException("Data inválida para serialização");
                        }

                        @Override
                        public LocalDate parseValue(Object input, GraphQLContext context, Locale locale)
                                throws CoercingParseValueException {
                            try {
                                if (input instanceof String) {
                                    return LocalDate.parse((String) input, formatter);
                                }
                                throw new CoercingParseValueException("Formato de valor inválido para LocalDate");
                            } catch (DateTimeParseException e) {
                                throw new CoercingParseValueException("Erro ao fazer parse do valor para LocalDate");
                            }
                        }

                        @Override
                        public LocalDate parseLiteral(Value<?> input, CoercedVariables variables, GraphQLContext context, Locale locale)
                                throws CoercingParseLiteralException {
                            if (input instanceof StringValue) {
                                try {
                                    return LocalDate.parse(((StringValue) input).getValue(), formatter);
                                } catch (DateTimeParseException e) {
                                    throw new CoercingParseLiteralException("Erro ao converter literal para LocalDate");
                                }
                            }
                            throw new CoercingParseLiteralException("Valor literal inválido para data");
                        }
                    })
                    .build();
        }

        @Bean
        public RuntimeWiringConfigurer runtimeWiringConfigurer(GraphQLScalarType dateScalar) {
            return wiringBuilder -> wiringBuilder.scalar(dateScalar);
        }
    }
