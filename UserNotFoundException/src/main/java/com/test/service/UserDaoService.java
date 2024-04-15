package com.test.service;

import com.test.exception.UserNotFoundException;
import com.test.model.User;
import org.springframework.aot.hint.predicate.SerializationHintsPredicates;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

@Component
public class UserDaoService {
    private static int userCount = 0;
    private static List<User> users = new ArrayList<>();

    static {
        users.add(new User(++userCount, "pradeep", LocalDate.now().minusYears(33)));
        users.add(new User(++userCount, "jyotshna", LocalDate.now().minusYears(26)));
        users.add(new User(++userCount, "punarvika", LocalDate.now().minusYears(2)));
        users.add(new User(++userCount, "jasvin", LocalDate.now().minusDays(147)));
        users.add(new User(++userCount, "test", LocalDate.now().minusYears(21)));
    }

    public List<User> findAll() {
        return users;
    }

    public User findOne(int id) {
        Predicate<? super User> predicate = user -> user.getId().equals(id);
        return users.stream().filter(predicate).findFirst().orElse(null);
        /*for (User u : users) {
            if (u.getId() == id) {
                return u;
            }
        }
        return null;*/
    }

    public User createUser(User user) {
        if (user.getId() == null) {
            user.setId(++userCount);
        }
        users.add(user);
        return user;
    }
}
