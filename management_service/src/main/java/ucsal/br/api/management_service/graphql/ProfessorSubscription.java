package ucsal.br.api.management_service.graphql;

import org.springframework.stereotype.Controller;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Sinks;
import ucsal.br.api.management_service.dto.ProfessorDTO;

@Controller
public class ProfessorSubscription {
    private final Sinks.Many<ProfessorDTO> professorSink = Sinks.many().multicast().onBackpressureBuffer();

    public void publish(ProfessorDTO professorDTO) {
        professorSink.tryEmitNext(professorDTO);
    }

    public Flux<ProfessorDTO> professorAdded() {
        return professorSink.asFlux();
    }
}

