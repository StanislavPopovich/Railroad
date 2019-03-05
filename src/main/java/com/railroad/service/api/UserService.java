package com.railroad.service.api;

import com.railroad.model.User;
import java.util.List;

/**
 * Service interface for {@link com.railroad.model.User}
 * @author Stanislav Popovich
 * @version 1.0
 */
public interface UserService {

    void save(User user);
    User findByUsername(String userName);
    List<User> getAll();
}
