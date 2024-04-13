package train.train.web;

import train.train.dto.UserDto;
import train.train.entities.Role;
import train.train.entities.User;
import train.train.services.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@CrossOrigin("http://localhost:4200")

@RequestMapping("/user")
public class UserController {


	public static final Logger logger = LoggerFactory.getLogger(UserController.class);


	@Autowired
    private UserService userService;

    @PostMapping("/register")
    public User userRegister(@RequestBody UserDto userDto, Role role) {
        logger.info("register");
        return userService.register(userDto);
    }




    @PostMapping("/update")
    public User update( @RequestBody UserDto userDto) {
        logger.info("update");
        return userService.update( userDto);
    }

    @DeleteMapping("/{id}")
    void delete(@PathVariable Long id){
        userService.delete(id);
    }
    @GetMapping("/users")
    public ResponseEntity<List<User>> getAllUsers() {
        logger.info("Fetching all users");
        List<User> users = userService.getAllUsers();
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public User getUser(@PathVariable Long id) {
        logger.info("get user");
        return userService.getUser(id);
    }
    @GetMapping("/user/{email}")
    public User getUserByuserEmail(@PathVariable String email) {
        logger.info("get user");
        return userService.getUserByemail(email);
    }
    @GetMapping("/user")
    public Principal user(Principal user) {
        return user;
    }




}