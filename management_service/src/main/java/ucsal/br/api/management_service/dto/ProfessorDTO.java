package ucsal.br.api.management_service.dto;

import ucsal.br.api.management_service.entity.ProfessorEntity;

import java.util.UUID;

public class ProfessorDTO {
    private UUID id;
    private String name;
    private String email;
    private String password;
    private Long registrationNumber;
    private boolean deleted;

    public ProfessorDTO() {
    }

    public ProfessorDTO(UUID id, String name, String email, String password, Long registrationNumber) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.password = password;
        this.registrationNumber = registrationNumber;
    }

    //DTO to Entity converter
    public ProfessorDTO(ProfessorEntity professor) {
        this.id = professor.getId();
        this.name = professor.getName();
        this.email = professor.getEmail();
        this.password = professor.getPassword();
        this.registrationNumber = professor.getRegistrationNumber();
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Long getRegistrationNumber() {
        return registrationNumber;
    }

    public void setRegistrationNumber(Long registration_number) {
        this.registrationNumber = registration_number;
    }

    public boolean isDeleted() {
        return deleted;
    }

    public void setDeleted(boolean deleted) {
        this.deleted = deleted;
    }
}

