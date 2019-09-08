package com.github.stormwyrm.library.service.impl;

import com.github.stormwyrm.library.dao.IUserDao;
import com.github.stormwyrm.library.model.User;
import com.github.stormwyrm.library.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("userService")
public class UserServiceImpl implements IUserService {

    @Autowired
    private IUserDao userDao;

    @Override
    public boolean isExist(String username) {
        User user = getByName(username);
        return null != user;
    }

    @Override
    public User getByName(String username) {
        return userDao.getByName(username);
    }

    @Override
    public User get(String username, String password) {
        return userDao.getByUsernamePassword(username, password);
    }

    @Override
    public void add(User user) {
        userDao.insert(user);
    }
}
