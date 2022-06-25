package ru.job4j.dreamjob.service;

import org.springframework.stereotype.Service;
import ru.job4j.dreamjob.model.User;
import ru.job4j.dreamjob.store.UserDBStore;

@Service
public class UserService {
    UserDBStore userDBStore;

    public UserService(UserDBStore userDBStore) {
        this.userDBStore = userDBStore;
    }

    public User add(User user) {
        return userDBStore.add(user);
    }
}
