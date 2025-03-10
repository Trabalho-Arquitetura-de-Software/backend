package ucsal.br.api.management_service.entity;

import jakarta.persistence.*;
import ucsal.br.api.management_service.dto.ProfessorDTO;

import java.util.UUID;

@Entity
@Table(name = "professor_users")
public class ProfessorEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(nullable = false)
    private String name;

    @Column(unique = true, nullable = false)
    private String email;

    @Column(nullable = false)
    private String password;

    @Column(unique = true, nullable = false)
    private Long registrationNumber;

    @Column()
    private boolean deleted;

    public ProfessorEntity() {
    }

    public ProfessorEntity(UUID id, String name, String email, String password, Long registrationNumber, boolean deleted) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.password = password;
        this.registrationNumber = registrationNumber;
        this.deleted = deleted;
    }

    //Entity to DTO converter
    public ProfessorEntity(ProfessorDTO professorDTO) {
        this.id = professorDTO.getId();
        this.name = professorDTO.getName();
        this.email = professorDTO.getEmail();
        this.password = professorDTO.getPassword();
        this.registrationNumber = professorDTO.getRegistrationNumber();
        this.deleted = professorDTO.isDeleted();
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

