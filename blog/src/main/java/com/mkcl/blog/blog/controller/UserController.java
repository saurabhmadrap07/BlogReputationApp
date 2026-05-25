package com.mkcl.blog.blog.controller;

import com.mkcl.blog.blog.entity.User;
import com.mkcl.blog.blog.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/userpost")
    User newUser(@RequestBody User newUser){
        return userService.save(newUser);
    }

    // Get all users with reputation
    @GetMapping("/reputation")
    public List<User> getAllUsersWithReputation() {
        return userService.getAllUsersWithReputation();
    }

    // Get leaderboard sorted by reputation
    @GetMapping("/leaderboard")
    public List<User> getLeaderboard() {
        return userService.getLeaderboard();
    }
}
