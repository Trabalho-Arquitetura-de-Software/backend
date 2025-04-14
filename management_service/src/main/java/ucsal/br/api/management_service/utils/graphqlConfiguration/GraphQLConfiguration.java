package ucsal.br.api.management_service.utils.graphqlConfiguration;

import graphql.language.StringValue;
import graphql.schema.Coercing;
import graphql.schema.GraphQLScalarType;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Configuration
public class GraphQLConfiguration {

    @Bean
    public GraphQLScalarType dateScalar() {
        return GraphQLScalarType.newScalar()
                .name("Date")
                .description("Data no formato ISO (yyyy-MM-dd)")
                .coercing(new Coercing<LocalDate, String>() {
                    @Override
                    public String serialize(Object dataFetcherResult) {
                        return ((LocalDate) dataFetcherResult).format(DateTimeFormatter.ISO_LOCAL_DATE);
                    }

                    @Override
                    public LocalDate parseValue(Object input) {
                        return LocalDate.parse(input.toString(), DateTimeFormatter.ISO_LOCAL_DATE);
                    }

                    @Override
                    public LocalDate parseLiteral(Object input) {
                        if (input instanceof StringValue) {
                            return LocalDate.parse(((StringValue) input).getValue(), DateTimeFormatter.ISO_LOCAL_DATE);
                        }
                        return null;
                    }
                })
                .build();
    }
}
