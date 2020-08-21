package ru.otus.apigateway.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.otus.apigateway.entity.Role;
import ru.otus.apigateway.repository.RoleRepository;

@Service
@AllArgsConstructor
public class RoleService {

    private final RoleRepository roleRepository;

    public Role save(Role role) {
        return roleRepository.save(role);
    }
}
