package com.example.demoBoot;

import com.example.demoBoot.JwtGenerator;
import com.example.demoBoot.beans.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {
    public static List<User> users = new ArrayList<User>();
    private JwtGenerator jwtGenerator;
    @Autowired
    public UserController(JwtGenerator jwtGenerator){
        this.jwtGenerator=jwtGenerator;
        users.add(new User("Bob", "1234"));
        users.add(new User("Miguel", "1234"));
    }

    @PostMapping("/register")
    public ResponseEntity<?> postUser(@RequestBody User user){
        try{
            users.add(user);
            return new ResponseEntity<>(user, HttpStatus.CREATED);
        } catch (Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.CONFLICT);
        }
    }
    @PostMapping("/login")
    public ResponseEntity<?> loginUser(@RequestBody User user) {
        //try {
            /*if(user.getUsername() == null || user.getPassword() == null) {
                throw new UsernameNotFoundException("UserName or Password is Empty");
            }*/
            //User userData = users.stream().filter(u -> u.getUsername().equals(user.getUsername()) && u.getPassword().equals(user.getPassword())).findFirst().orElse(null);
            User userData = new User("Bob", "1234");
            /*if(userData == null){
                System.out.println("Usuario no encontrado");
                throw new UsernameNotFoundException("UserName or Password is Invalid");
            }*/
            return new ResponseEntity<>("test", HttpStatus.OK);
       /* } catch (UsernameNotFoundException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.CONFLICT);
        } */
    }
}