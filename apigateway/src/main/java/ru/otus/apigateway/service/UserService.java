package ru.otus.apigateway.service;

import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.otus.apigateway.dto.RegistrationRequestDTO;
import ru.otus.apigateway.entity.Role;
import ru.otus.apigateway.entity.User;
import ru.otus.apigateway.repository.RoleRepository;
import ru.otus.apigateway.repository.UserRepository;

import java.util.List;

@Service
@AllArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    public List<User> findAll() {
        return userRepository.findAll();
    }

    public User createUser(RegistrationRequestDTO registrationRequestDTO) {
        User u = new User();
        u.setPassword(registrationRequestDTO.getPassword());
        u.setLogin(registrationRequestDTO.getLogin());
        u.setFirstName(registrationRequestDTO.getFirstName());
        u.setLastName(registrationRequestDTO.getLastName());
        u.setEmail(registrationRequestDTO.getEmail());
        u.setPhone(registrationRequestDTO.getPhone());
        return userRepository.save(u);
    }

    public User saveUser(User user) {
        Role userRole = roleRepository.findByName("ROLE_USER");
        user.setRole(userRole);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }

    public User findByLogin(String login) {
        return userRepository.findByLogin(login);
    }

    public User findByLoginAndPassword(String login, String password) {
        User user = findByLogin(login);
        if (user != null) {
            if (passwordEncoder.matches(password, user.getPassword())) {
                return user;
            }
        }
        return null;
    }
}
