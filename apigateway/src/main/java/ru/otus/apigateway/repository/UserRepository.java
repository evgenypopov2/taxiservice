package ru.otus.apigateway.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.otus.apigateway.entity.User;

public interface UserRepository extends JpaRepository<User, Integer> {

    User findByLogin(String login);
}
