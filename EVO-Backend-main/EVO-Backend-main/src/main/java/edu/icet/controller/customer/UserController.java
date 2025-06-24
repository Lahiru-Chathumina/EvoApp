package edu.icet.controller.customer;

import edu.icet.dto.customer.User;
import edu.icet.service.system.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/userController")
@RequiredArgsConstructor
public class UserController {

    final UserService userSerivce;

    @PostMapping("/user")
    public ResponseEntity<String> saveUser(@RequestBody User user) {
        return userSerivce.saveUser(user)
                ? ResponseEntity.ok("user saved")
                : ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }
    
    @GetMapping("/users")
    public ResponseEntity<List<User>> getAllUsers() {
        List<User> allUsersList = userSerivce.getAllUsers();
        return !allUsersList.isEmpty()
                ? ResponseEntity.ok(allUsersList)
                : ResponseEntity.noContent().build();
    }

    @PatchMapping("/userId/{id}")
    public ResponseEntity<String> updateUser(@PathVariable("id") Long userId, @RequestBody User user) {
        return (userSerivce.updateUser(userId, user))
                ? ResponseEntity.ok("user updated")
                : ResponseEntity.status(HttpStatus.NOT_MODIFIED).build();
    }

    @DeleteMapping("/userId/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable("id") Long userId){
        return userSerivce.deleteUser(userId)
                ? ResponseEntity.ok("user "+userId+" deleted")
                : ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @GetMapping("/userId/{id}")
    public ResponseEntity<String> isUserExtist(@PathVariable("id") Long userId){
        return userSerivce.isUserExist(userId)
                ? ResponseEntity.ok("user "+userId+" is Exist")
                : ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @GetMapping("/user")
    public ResponseEntity<String> isUserExtist( User user){
        return userSerivce.isUserExist(user)
                ? ResponseEntity.ok(user+" is Exist")
                : ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

}
