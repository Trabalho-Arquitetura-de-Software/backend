package ucsal.br.api.management_service.service;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import ucsal.br.api.management_service.dto.ProfessorDTO;
import ucsal.br.api.management_service.entity.ProfessorEntity;
import ucsal.br.api.management_service.repository.IProfessorRepository;

import java.util.List;
import java.util.UUID;

@Service
public class ProfessorService {
    private final IProfessorRepository professorRepository;

    public ProfessorService(IProfessorRepository professorRepository) {
        this.professorRepository = professorRepository;
    }

    public List<ProfessorDTO> listProfessors() {
        List<ProfessorEntity> professors = professorRepository.findAll();
        return professors.stream()
                .filter(professor -> !professor.isDeleted())
                .map(ProfessorDTO::new)
                .toList();
    }

    public ProfessorDTO getProfessorById(UUID id) {
        ProfessorEntity professor = professorRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Professor not found with ID: " + id));

        if (professor.isDeleted()) {
            throw new EntityNotFoundException("Professor with ID: " + id + " is deleted");
        }

        return new ProfessorDTO(professor);
    }

    public void createProfessor(ProfessorDTO professorDTO) {
        try {
            ProfessorEntity professor = new ProfessorEntity(professorDTO);
            professor.setDeleted(false);
            professorRepository.save(professor);
        } catch (DataIntegrityViolationException e) {
            throw new RuntimeException("Database integrity error: " + e.getMessage());
        } catch (Exception e) {
            throw new RuntimeException("Error creating professor: " + e.getMessage());
        }
    }

    public ProfessorDTO updateProfessor(ProfessorDTO professorDTO) {
        ProfessorEntity professor = professorRepository.findById(professorDTO.getId())
                .orElseThrow(() -> new EntityNotFoundException("Professor not found with ID: " + professorDTO.getId()));

        if (professor.isDeleted()) {
            throw new EntityNotFoundException("Professor not found with ID: " + professorDTO.getId());
        }

        professor.setName(professorDTO.getName());
        if (professorDTO.getEmail() != null) {
            professor.setEmail(professorDTO.getEmail());
        }
        professor.setPassword(professorDTO.getPassword());

        if (professorDTO.getRegistrationNumber() != null) {
            professor.setRegistrationNumber(professorDTO.getRegistrationNumber());
        }

        return new ProfessorDTO(professorRepository.save(professor));
    }

    public void deleteProfessorById(UUID id) {
        ProfessorEntity professor = professorRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Professor not found with ID: " + id));
        professor.setDeleted(true);
        professorRepository.save(professor);
    }
}

