package com.handoferis.handoferis.DAO;

import com.handoferis.handoferis.models.Users;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Component
public class UserDAOService {

    private static List<Users> users = new ArrayList<>();

    public List<Users> findAll() { return users; }

    public Users save(Users user) {
        users.add(user);
        return user;
    }

    //public User findOne()
}
