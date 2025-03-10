package ucsal.br.api.management_service.controller;

import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;
import ucsal.br.api.management_service.dto.ProfessorDTO;
import ucsal.br.api.management_service.graphql.ProfessorSubscription;
import ucsal.br.api.management_service.service.ProfessorService;

import java.util.List;
import java.util.UUID;

@Controller
public class ProfessorController {
    private final ProfessorService professorService;
    private final ProfessorSubscription professorSubscription;

    public ProfessorController(ProfessorService professorService, ProfessorSubscription professorSubscription) {
        this.professorService = professorService;
        this.professorSubscription = professorSubscription;
    }

    @QueryMapping
    public List<ProfessorDTO> listProfessors() {
        return professorService.listProfessors();
    }

    @QueryMapping
    public ProfessorDTO getProfessorById(@Argument UUID id) {
        return professorService.getProfessorById(id);
    }

    @MutationMapping
    public ProfessorDTO createProfessor(@Argument String name, @Argument String email, @Argument String password, @Argument Long registrationNumber) {
        ProfessorDTO professorDTO = new ProfessorDTO(null, name, email, password, registrationNumber);
        professorService.createProfessor(professorDTO);
        professorSubscription.publish(professorDTO);
        return professorDTO;
    }

    @MutationMapping
    public ProfessorDTO updateProfessor(@Argument UUID id, @Argument String name, @Argument String email, @Argument String password, @Argument Long registrationNumber) {
        ProfessorDTO professorDTO = new ProfessorDTO(id, name, email, password, registrationNumber);
        return professorService.updateProfessor(professorDTO);
    }

    @MutationMapping
    public ProfessorDTO deleteProfessorById(@Argument UUID id) {
        ProfessorDTO professorDTO = professorService.getProfessorById(id);
        professorService.deleteProfessorById(professorDTO.getId());
        professorSubscription.publish(professorDTO);
        return professorDTO;
    }
}

