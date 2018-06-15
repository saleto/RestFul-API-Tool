package pb360.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import pb360.model.Registration;
import pb360.service.UserService;

@RestController
@RequestMapping({"/user"})
public class UserController {
	@Autowired
    private UserService userService;

    @PostMapping
    public Registration create(@RequestBody Registration user){
        return userService.create(user);
    }
}
