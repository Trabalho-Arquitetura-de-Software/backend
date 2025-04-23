package ucsal.br.api.management_service.controller;

import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Controller;
import ucsal.br.api.management_service.dto.LoginReturnDTO;
import ucsal.br.api.management_service.dto.UserDTO;
import ucsal.br.api.management_service.entity.UserEntity;
import ucsal.br.api.management_service.service.UserService;
import ucsal.br.api.management_service.utils.security.TokenService;
import ucsal.br.api.management_service.utils.type.UserRole;

import java.util.List;
import java.util.UUID;

@Controller
public class UserController {
    private final UserService userService;
    private final TokenService tokenService;
    private final AuthenticationManager authenticationManager;

    public UserController(UserService userService, TokenService tokenService, AuthenticationManager authenticationManager) {
        this.userService = userService;
        this.tokenService = tokenService;
        this.authenticationManager = authenticationManager;
    }

    @PreAuthorize("hasRole('ADMIN')")
    @QueryMapping
    public UserDTO findUserByEmail(@Argument String email) {
        return userService.findUserByEmail(email);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @QueryMapping
    public List<UserDTO> findUserByRole(@Argument UserRole role) {
        return userService.findUserByRole(role);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @QueryMapping
    public UserDTO findUserById(@Argument UUID id) {
        return userService.findUserById(id);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @QueryMapping
    public List<UserDTO> findAllUsers() {
        return userService.findAllUsers();
    }

    @PreAuthorize("hasRole('ADMIN')")
    @MutationMapping
    public UserDTO saveUser(@Argument String name, @Argument String affiliatedSchool, @Argument String email, @Argument String password, @Argument UserRole role) {
        return userService.saveUser(new UserDTO(name, affiliatedSchool, email, password, role));
    }

    @PreAuthorize("hasRole('ADMIN')")
    @MutationMapping
    public UserDTO updateUser(@Argument UUID id, @Argument String name, @Argument String affiliatedSchool, @Argument String email, @Argument String password, @Argument UserRole role) {
        return userService.updateUser(new UserDTO(id, name, affiliatedSchool, email, password, role));
    }

    @PreAuthorize("hasRole('ADMIN')")
    @MutationMapping
    public UserDTO deleteUser(@Argument UUID id) {
        return userService.deleteUser(id);
    }

    @MutationMapping
    public LoginReturnDTO login(@Argument String email, @Argument String password) {
        var usernamePassword = new UsernamePasswordAuthenticationToken(email, password);
        var auth = authenticationManager.authenticate(usernamePassword);

        if (auth.isAuthenticated()) {
            var user = (UserEntity) auth.getPrincipal();
            var token = tokenService.generateToken(user);
            return new LoginReturnDTO(userService.findUserByEmail(email), token);
        }

        throw new RuntimeException("Erro ao autenticar usuário");
    }

    @PreAuthorize("hasRole('STUDENT')")
    @MutationMapping
    public Boolean changePassword(@Argument String email, @Argument String password, @Argument String newPassword) {
        return userService.changePassword(email, password, newPassword);
    }
}
