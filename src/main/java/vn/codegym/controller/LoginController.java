package vn.codegym.controller;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import vn.codegym.dto.UserDto;
import vn.codegym.entity.Role;
import vn.codegym.entity.User;
import vn.codegym.repository.IRoleRepository;
import vn.codegym.repository.IUserRepository;

import java.util.ArrayList;
import java.util.Optional;

@Controller
@RequestMapping({ ""})
public class LoginController {
    @Autowired
    private IUserRepository userRepository;
    @Autowired
    private IRoleRepository roleRepository;
    @GetMapping("/login")
    public String showLoginPage(@RequestParam(value = "error", required = false) String error,
                                @RequestParam(value = "logout", required = false) String logout, Model model) {
        if (error != null) {
            model.addAttribute("error", "Invalid login information, please re-enter");
        }

        if (logout != null) {
            model.addAttribute("message", "You have been signed out!");
        }
        return "/login/loginForm";
    }
    @GetMapping("/create")
    public String showCreateForm(Model model){
        model.addAttribute("userDto", new UserDto());
        return "/login/createForm";
    }

    @PostMapping("/create")
    public String CreateAccount(@Validated @ModelAttribute("userDto") UserDto userDto, BindingResult bindingResult, Model model){
        if (bindingResult.hasFieldErrors()) return "/login/createForm";
        User user = new User();
        BeanUtils.copyProperties(userDto,user);
        user.setRoles(new ArrayList<>());
        Optional<Role> role = roleRepository.findById(2L);
        user.getRoles().add(role.get());
        user.setWallet(500);
        BCryptPasswordEncoder bc = new BCryptPasswordEncoder();
        user.setPassword(bc.encode(user.getPassword()));
        try {
            userRepository.save(user);
        }catch (Exception e){
            model.addAttribute("message2","(username already exists)");
            return "/login/createForm";
        }
        model.addAttribute("message","Create account success");
        return "/login/createForm";
    }
}

