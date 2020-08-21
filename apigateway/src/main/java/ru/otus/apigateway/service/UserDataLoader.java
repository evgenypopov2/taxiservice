package ru.otus.apigateway.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.otus.apigateway.entity.Role;
import ru.otus.apigateway.entity.User;

import java.util.List;

@Service
public class UserDataLoader {

    private final UserService userService;
    private final RoleService roleService;

    public UserDataLoader(
            UserService userService,
            RoleService roleService
    ) {
        this.userService = userService;
        this.roleService = roleService;
        loadData();
    }

    private void loadData() {
        List<User> userList = userService.findAll();
        if (userList.size() == 0) {
            Role roleAdmin = new Role();
            roleAdmin.setName("ROLE_ADMIN");
            roleService.save(roleAdmin);
            Role roleUser = new Role();
            roleUser.setName("ROLE_USER");
            roleService.save(roleUser);

            User user = new User();
            user.setLogin("admin");
            user.setPassword("admin");
            user.setRole(roleAdmin);
            userService.saveUser(user);
            user = new User();
            user.setLogin("user");
            user.setPassword("user");
            user.setRole(roleUser);
            userService.saveUser(user);
        }
    }


}
