package com.github.stormwyrm.library.service;

import com.github.stormwyrm.library.model.User;

public interface IUserService {
    boolean isExist(String username);

    User getByName(String username);

    User get(String username, String password);

    void add(User user);
}
