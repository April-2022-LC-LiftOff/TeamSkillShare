package com.skills.skills.controllers;

import com.skills.skills.data.SkillsCategoryRepository;
import com.skills.skills.data.SkillsRepository;
import com.skills.skills.data.TagRepository;
import com.skills.skills.data.UserRepository;
import com.skills.skills.models.*;
import com.skills.skills.models.dto.LoginFormDTO;
import com.skills.skills.models.dto.RegisterFormDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Controller
@RequestMapping("")
public class AuthenticationController {

    @Autowired
    UserRepository userRepository;

    @Autowired
    SkillsRepository skillsRepository;

    @Autowired
    public TagRepository tagRepository;

    @Autowired
    public SkillsCategoryRepository skillsCategoryRepository;

    private static final String userSessionKey = "user";

    public User getUserFormSession(HttpSession session) {
        Integer userId = (Integer) session.getAttribute(userSessionKey);
        if (userId == null) {
            return null;
        }

        Optional<User> user = userRepository.findById(userId);

        if (user.isEmpty()) {
            return null;
        }

        return user.get();
    }

    private static void setUserInSession(HttpSession session, User user) {
        session.setAttribute(userSessionKey, user.getId());

    }

    @GetMapping("/users/profile")
    public String displayPageAfterLogin (HttpSession session, Model model) {
        User user = getUserFormSession(session);
        model.addAttribute("user", user);
        model.addAttribute("skills", user.getSkills());
        model.addAttribute(new Skill());
        model.addAttribute(new Tag());
        model.addAttribute("tags", tagRepository.findAll());
        return "users/profile";
    }

    @PostMapping("/users/profile")
        public String displayPageAfterFilter (@RequestParam int tagId, HttpSession session, Model model) {
        User user = getUserFormSession(session);

        List<Skill> skills;
        List<Skill> filteredSkills = new ArrayList<>();
        skills = user.getSkills();

        skills.forEach(skill -> {
            if (skill.tag.getTagName() == tagId) {
                filteredSkills.add(skill);
            }
        });
        model.addAttribute("user", user);
        model.addAttribute("skills", filteredSkills);
        model.addAttribute(new Skill());
        model.addAttribute("tags", tagRepository.findAll());
        return "users/profile";
    }


    @GetMapping("/register")
    public String displayRegistrationForm(Model model) {

        model.addAttribute(new RegisterFormDTO());
        model.addAttribute(new UserProfile());
        model.addAttribute("title", "REGISTER");
        return "register";
    }

    @PostMapping("/register")
    public String processRegistrationForm(@ModelAttribute @Valid UserProfile userProfile, Errors errors1,
                                          @ModelAttribute @Valid RegisterFormDTO registerFormDTO,
                                          Errors errors, HttpServletRequest request, Model model) {

        if (errors.hasErrors() || errors1.hasErrors()) {
            // model.addAttribute("title", "Registration");
            return "register";
        }

        User existingUser = userRepository.findByUsername(registerFormDTO.getUsername());

        if (existingUser != null) {
            errors.rejectValue("username", "username.alreadyexists", "Please choose a different username.");
            model.addAttribute("title", "Registration");
            return "register";
        }

        String password = registerFormDTO.getPassword();
        String verifyPassword = registerFormDTO.getVerifyPassword();

        if (!password.equals(verifyPassword)) {
            errors.rejectValue("password", "passwords.mismatch", "Passwords do not match");
            model.addAttribute("title", "Registration");
            return "register";
        }

        User newUser = new User(registerFormDTO.getUsername(), registerFormDTO.getPassword(), userProfile);
        userRepository.save(newUser);
        setUserInSession(request.getSession(), newUser);

        //return "redirect:login";
        //return "users/index";
        //return "register_success";
        return "redirect:/users/profile";

    }

    @GetMapping("login")
    public String displayLoginForm(Model model) {

        model.addAttribute(new LoginFormDTO());
        model.addAttribute("title", "LOG IN");
        return "login";
    }

    @PostMapping("login")
    public String processLoginForm(@ModelAttribute @Valid LoginFormDTO loginFormDTO,
                                   Errors errors, HttpServletRequest request,
                                   Model model) {


        if (errors.hasErrors()) {
            model.addAttribute("title", "LOG IN");
            return "login";
        }

        User theUser = userRepository.findByUsername(loginFormDTO.getUsername());

        if (theUser == null) {
            errors.rejectValue("username", "user.invalid", "The given username does not exist");
            model.addAttribute("title", "LOG IN");
            return "login";
        }

        String password = loginFormDTO.getPassword();

        if (!theUser.isMatchingPassword(password)) {
            errors.rejectValue("password", "password.invalid", "Invalid password");
            model.addAttribute("title", "LOG IN");
            return "login";
        }

        setUserInSession(request.getSession(), theUser);

        return "redirect:/users/profile";
    }

    @GetMapping("/logout")
    public String logout(HttpServletRequest request) {
        request.getSession().invalidate();
        return "redirect:/login";
    }
}

