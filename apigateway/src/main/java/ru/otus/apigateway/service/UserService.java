package ru.otus.apigateway.service;

import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.otus.apigateway.config.jwt.JwtFilter;
import ru.otus.apigateway.config.jwt.JwtProvider;
import ru.otus.common.dto.ClientRegistrationDTO;
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
    private final JwtFilter jwtFilter;
    private final JwtProvider jwtProvider;

    public List<User> findAll() {
        return userRepository.findAll();
    }

    public <T extends ClientRegistrationDTO> User createUser(T clientRegistrationRequestDTO) {
        User u = new User();
        u.setPassword(clientRegistrationRequestDTO.getPassword());
        u.setLogin(clientRegistrationRequestDTO.getLogin());
        u.setFirstName(clientRegistrationRequestDTO.getFirstName());
        u.setLastName(clientRegistrationRequestDTO.getLastName());
        u.setEmail(clientRegistrationRequestDTO.getEmail());
        u.setPhone(clientRegistrationRequestDTO.getPhone());
        return saveUser(u);
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

    public User getUserFromAuthHeader(String authorizationHeader) {
        return userRepository.findByLogin(
                jwtProvider.getLoginFromToken(jwtFilter.getTokenFromAuthHeader(authorizationHeader)));
    }
}
