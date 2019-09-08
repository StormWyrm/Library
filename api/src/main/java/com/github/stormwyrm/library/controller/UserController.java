package com.github.stormwyrm.library.controller;

import com.github.stormwyrm.library.model.BaseResult;
import com.github.stormwyrm.library.model.User;
import com.github.stormwyrm.library.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    private IUserService userService;

    @CrossOrigin
    @PostMapping("/login")
    public BaseResult login(@RequestBody User user) {
        String username = user.getUsername();
        String password = user.getPassword();
        if (!userService.isExist(username)) {
            String message = "账号不存在";
            return new BaseResult(400, message, "");
        }
        user = userService.get(username, password);
        if (user == null) {
            String message = "账号不存在";
            return new BaseResult(400, message, "");
        } else {
            return new BaseResult(200, "", user);
        }
    }
}
