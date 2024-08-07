package com.test.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

import org.springframework.stereotype.Component;

import com.test.model.User;

@Component
public class UserDaoService {

    private static List<User> users = new ArrayList<>();
    private static int userCount = 0;

    static {
        users.add(new User(++userCount, "pradeep", LocalDate.now().minusYears(33)));
        users.add(new User(++userCount, "jyotshna", LocalDate.now().minusYears(27)));
        users.add(new User(++userCount, "punarvika", LocalDate.now().minusMonths(19)));
        users.add(new User(++userCount, "jasvin", LocalDate.now().minusMonths(5)));
    }

    public List<User> findAll() {
        return users;
    }

    public User findUser(int id) {
        Predicate<? super User> predicate = user -> user.getId().equals(id);
        return users.stream().filter(predicate).findFirst().get();
    }

    public User saveUser(User user) {
        if (user.getId() == null) {
            user.setId(++userCount);
        }
        users.add(user);
        return user;
    }
}
