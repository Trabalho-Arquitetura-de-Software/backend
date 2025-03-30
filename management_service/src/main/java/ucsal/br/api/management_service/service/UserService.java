package ucsal.br.api.management_service.service;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import ucsal.br.api.management_service.dto.UserDTO;
import ucsal.br.api.management_service.entity.UserEntity;
import ucsal.br.api.management_service.repository.IUserRepository;
import ucsal.br.api.management_service.utils.exception.InvalidEmailException;
import ucsal.br.api.management_service.utils.exception.UserAlredyExistsException;
import ucsal.br.api.management_service.utils.exception.UserNotFoundException;
import ucsal.br.api.management_service.utils.type.UserRole;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class UserService {
    private final IUserRepository userRepository;

    public UserService(IUserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public Optional<String> isEmail(String email) {
        String regex = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches() ? Optional.of(email) : Optional.empty();
    }

    public UserDTO findUserByEmail(String email) {
        UserEntity user = userRepository.findByEmail(email);
        if (user == null) {
            throw new UserNotFoundException("User not Found!");
        }
        return new UserDTO(user);
    }

    public List<UserDTO> findUserByRole(UserRole role) {
        List<UserEntity> users = userRepository.findByRole(role);
        return users.stream().map(UserDTO::new).toList();
    }

    public UserDTO findUserById(UUID id) {
        Optional<UserEntity> user = userRepository.findById(id);
        return user.map(UserDTO::new).get();
    }

    public List<UserDTO> findAllUsers() {
        List<UserEntity> users = userRepository.findAll();
        return users.stream().map(UserDTO::new).toList();
    }

    public UserDTO saveUser(UserDTO userDTO) {
        if (userRepository.findByEmail(userDTO.getEmail()) != null) throw new UserAlredyExistsException("User Already Exists");
        String encryptedPassword = new BCryptPasswordEncoder().encode(userDTO.getPassword());
        userDTO.setPassword(encryptedPassword);
        if (isEmail(userDTO.getEmail()).isEmpty()) {
            throw new InvalidEmailException("Invalid Email");
        }
        userDTO.setEmail(isEmail(userDTO.getEmail()).get());
        UserEntity userEntity = new UserEntity(userDTO);
        return new UserDTO(userRepository.save(userEntity));
    }

    public UserDTO updateUser(UserDTO userDTO) {
        UserEntity userEntity = userRepository.findById(userDTO.getId()).orElseThrow(() -> new UserNotFoundException("User not found"));
        userEntity.setName(userDTO.getName());

        if (userDTO.getEmail() != null) {
            userEntity.setEmail(userDTO.getEmail());
        }
        userEntity.setPassword(userDTO.getPassword());

        if (userDTO.getRole() != null) {
            userEntity.setRole(userDTO.getRole());
        }
        return new UserDTO(userRepository.save(userEntity));
    }

    public UserDTO deleteUser(UUID id) {
        Optional<UserEntity> user = userRepository.findById(id);
        if (user.isPresent()) {
            UserEntity userEntity = user.get();
            userRepository.delete(userEntity);
            return new UserDTO(userEntity);
        } else {
            return null;
        }
    }
}