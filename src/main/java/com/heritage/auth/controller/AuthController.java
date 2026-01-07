package com.heritage.auth.controller;
import com.heritage.auth.entity.User;
import com.heritage.auth.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class AuthController {
    @Autowired
    UserMapper userMapper;

    @PostMapping("/login")
    public String login(@RequestBody User u){
        User db = userMapper.findByUsername(u.getUsername());
        if(db!=null && db.getPassword().equals(u.getPassword()))
            return db.getRole();
        return "FAIL";
    }
}
