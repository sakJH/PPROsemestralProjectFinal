package uhk.sakac.recenzentiuhk.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import uhk.sakac.recenzentiuhk.dao.RoleDao;
import uhk.sakac.recenzentiuhk.exception.ResourceAlreadyExists;
import uhk.sakac.recenzentiuhk.exception.ResourceNotFoundException;
import uhk.sakac.recenzentiuhk.model.User;
import uhk.sakac.recenzentiuhk.security.UserDetails;
import uhk.sakac.recenzentiuhk.service.implementation.AuthServiceImplementation;

import java.util.List;


@Controller
public class AuthenticationController {
    @Autowired
    private AuthServiceImplementation authenticationService;

    @Autowired
    private RoleDao roleDao;


    @RequestMapping("/")
    public String home() {
        return "redirect:/movies";
    }

    @RequestMapping("/login")
    public String loginPage() {
        return "home/login";
    }

    @RequestMapping("/reviews")
    public String reviewsPage(){
        return "user/movie/review/reviews";
    }


    @RequestMapping("/login-error")
    public String loginError(Model model) {
        model.addAttribute("loginError", true);
        return "home/login";
    }

    @RequestMapping("/logout-success")
    public String logoutSuccess() {
        return "index";
    }

    @RequestMapping(value = "signup", method = RequestMethod.GET)
    public String signupPage(Model model) {
        User user = new User();
        model.addAttribute("user", user);
        return "home/register";
    }

    @RequestMapping(value = "/signup", method = RequestMethod.POST)
    public String signupSubmit(User user, Model model) {
        try {
            authenticationService.signupSubmit(user);
            return "home/login";

        } catch (ResourceAlreadyExists e) {
            model.addAttribute("signupError", true);
            return "home/register";
        }
    }

    @RequestMapping("/all_user")
    public String allUser(Model model) {
        try {
            List<User> users = authenticationService.allUser();
            model.addAttribute("allUser", users);
        } catch (ResourceNotFoundException e) {
            model.addAttribute("error", true);
        }
        return "admin/user/all_user";
    }

    @RequestMapping("/delete/user/{id}")
    public String deleteUser(@PathVariable(value = "id") Long userId) {
        try {
            authenticationService.deleteUser(userId);
        } catch (ResourceNotFoundException e) {
            System.out.println("Problem on Delete!");
        }
        return "redirect:/all_user";
    }

    @RequestMapping("/user_profile")
    public String profile(@AuthenticationPrincipal UserDetails principal, Model model) {
        try {
            User loggedUser = authenticationService.profile(principal);
            model.addAttribute("user", loggedUser);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "home/profile";
    }

    @RequestMapping(value = "/edit/user/{id}", method = RequestMethod.GET)
    public String setEditUserPage(@PathVariable(value = "id") Long userId, Model model) {
        try {
            User user = authenticationService.setEditUserPage(userId);
            model.addAttribute("user", user);
            model.addAttribute("allRole", roleDao.findAll());
        } catch (ResourceNotFoundException e) {
            e.printStackTrace();
        }
        return "admin/user/edit_user";
    }

    @RequestMapping(value = "/edit/user/{id}", method = RequestMethod.POST)
    public String editUserSubmit(@PathVariable(value = "id") Long userId, User userDetails) {
        try {
            authenticationService.editUserSubmit(userId, userDetails);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "redirect:/all_user";
    }

    @RequestMapping("/edit/user/profile/image/{id}")
    public String profilePictureUpdate(@PathVariable(value = "id") Long id, @RequestParam("file") MultipartFile file) {
        try {
            authenticationService.saveUpdatedProfilePicture(id, file);
        }catch (Exception e){
            e.printStackTrace();
        }
        return "redirect:/user_profile";
    }
}
