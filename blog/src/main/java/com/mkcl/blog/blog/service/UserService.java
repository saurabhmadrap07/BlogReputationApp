package com.mkcl.blog.blog.service;

import com.mkcl.blog.blog.entity.User;
import com.mkcl.blog.blog.repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepo;

    public List<User> getAllUsersWithReputation() {
        return userRepo.findAll();
    }

    public List<User> getLeaderboard() {
        return userRepo.findAll(Sort.by(Sort.Direction.DESC, "reputation"));
    }


//    public User save(User newUser) {
//        return userRepo.save(new User(String username, Integer ));
//    }
}
