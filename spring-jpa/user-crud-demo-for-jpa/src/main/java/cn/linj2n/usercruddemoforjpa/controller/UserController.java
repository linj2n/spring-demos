package cn.linj2n.usercruddemoforjpa.controller;

import cn.linj2n.usercruddemoforjpa.entity.User;
import cn.linj2n.usercruddemoforjpa.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    private static final String RESULT_SUCCESS = "success";
    private static final String RESULT_FAILED = "failed";

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/save")
    public String save(@RequestParam("name") String name,
                       @RequestParam("age") Integer age,
                       @RequestParam("gender") String gender) {
        User user = new User(null,name,age,gender);
        try {
            userRepository.save(user);
            return RESULT_SUCCESS;
        } catch (Exception e) {
            e.printStackTrace();
            return RESULT_FAILED;
        }
    }

    @GetMapping("/delete")
    public String delete(@RequestParam("id") Long id) {
        try {
            userRepository.delete(id);
            return RESULT_SUCCESS;
        } catch (Exception e) {
            e.printStackTrace();
            return RESULT_FAILED;
        }
    }

    @GetMapping("/all")
    public List<User> all() {
        return userRepository.findAll();
    }

    @GetMapping("/find")
    public User find(@RequestParam("id") Long id) {
        return userRepository.findOne(id);
    }

}
