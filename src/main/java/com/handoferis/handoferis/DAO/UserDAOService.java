package com.handoferis.handoferis.DAO;

import com.handoferis.handoferis.models.User;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Component
public class UserDAOService {

    private static List<User> users = new ArrayList<>();

    public List<User> findAll() { return users; }

    public User save(User user) {
        users.add(user);
        return user;
    }

    //public User findOne()
}
