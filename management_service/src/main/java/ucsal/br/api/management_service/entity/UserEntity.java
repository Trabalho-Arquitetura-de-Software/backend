package ucsal.br.api.management_service.entity;

import jakarta.persistence.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import ucsal.br.api.management_service.dto.UserDTO;
import ucsal.br.api.management_service.utils.type.UserRole;

import java.util.Collection;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "users")
public class UserEntity implements UserDetails {

    @Id
    @PrimaryKeyJoinColumn
    @Column(unique = true)
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column()
    private String name;

    @Column(unique = true, nullable = false)
    private String email;

    @Column(nullable = false)
    private String affiliatedSchool;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private UserRole role;

    public UserEntity() {
    }

    public UserEntity(UUID id, String name, String affiliatedSchool, String email, String password, UserRole role) {
        this.id = id;
        this.name = name;
        this.affiliatedSchool = affiliatedSchool;
        this.email = email;
        this.password = password;
        this.role = role;
    }

    public UserEntity(String name, String affiliatedSchool, String email, String password, UserRole role) {
        this.name = name;
        this.affiliatedSchool = affiliatedSchool;
        this.email = email;
        this.password = password;
        this.role = role;
    }

    public UserEntity(UserDTO userDTO) {
        this.id = userDTO.getId();
        this.name = userDTO.getName();
        this.affiliatedSchool = userDTO.getAffiliatedSchool();
        this.email = userDTO.getEmail();
        this.password = userDTO.getPassword();
        this.role = userDTO.getRole();
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return switch (this.role) {
            case ADMIN -> List.of(
                    new SimpleGrantedAuthority("ROLE_ADMIN"),
                    new SimpleGrantedAuthority("ROLE_PROFESSOR"),
                    new SimpleGrantedAuthority("ROLE_STUDENT")
            );
            case PROFESSOR -> List.of(
                    new SimpleGrantedAuthority("ROLE_PROFESSOR"),
                    new SimpleGrantedAuthority("ROLE_STUDENT")
            );
            case STUDENT -> List.of(
                    new SimpleGrantedAuthority("ROLE_STUDENT")
            );
        };
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

    public String getAffiliatedSchool() {
        return affiliatedSchool;
    }

    public void setAffiliatedSchool(String affiliatedSchool) {
        this.affiliatedSchool = affiliatedSchool;
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

    public UserRole getRole() {
        return role;
    }

    public void setRole(UserRole role) {
        this.role = role;
    }
}
