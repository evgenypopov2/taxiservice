package ru.otus.apigateway.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.otus.apigateway.entity.Role;

public interface RoleRepository extends JpaRepository<Role, Integer> {

    Role findByName(String name);
}
