package cn.linj2n.thymeleaf.Controller;

import cn.linj2n.thymeleaf.Repository.UserRepository;
import cn.linj2n.thymeleaf.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    /**
     * retrieve all users
     * @param model
     * @return
     */
    @GetMapping
    public ModelAndView list(Model model) {
        model.addAttribute("userlist", userRepository.listUsers());
        model.addAttribute("title", "用户管理");
        return new ModelAndView("users/list", "userModel", model);

    }

    /**
     * retrieve specified User by id
     * @param id
     * @param model
     * @return
     */
    @GetMapping("{id}")
    public ModelAndView view(@PathVariable("id") Long id, Model model) {
        model.addAttribute("user", userRepository.getUserById(id));
        model.addAttribute("title", "查看用户");
        return new ModelAndView("users/view", "userModel", model);
    }

    /**
     * get user created-form page
     * @param model
     * @return
     */
    @GetMapping("/form")
    public ModelAndView createForm(Model model) {
        model.addAttribute("user",new User());
        model.addAttribute("title", "创建用户");
        return new ModelAndView("users/form", "userModel", model);
    }

//    /**
//     * create new User
//     * @param user
//     * @return
//     */
//    @PostMapping
//    public ModelAndView saveOrUpdateUser(User user) {
//        user = userRepository.saveOrUpdateUser(user);
//        return new ModelAndView("redirect:/users");
//    }
}

