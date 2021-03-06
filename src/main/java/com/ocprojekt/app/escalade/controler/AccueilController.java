package com.ocprojekt.app.escalade.controler;

import com.ocprojekt.app.escalade.entities.User;
import com.ocprojekt.app.escalade.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;


@Controller
public class AccueilController {

    @Autowired
    private UserRepository userRepository;

    @RequestMapping(value="/Accueil")
    public String Accueil(){
        return "Accueil";
    }

    @RequestMapping(value="/")
    public String Home(){
        return "redirect:Accueil";
    }

   @RequestMapping(value="/login")
   public String login(){
        return "login";
    }

    @RequestMapping(value="/logout", method = RequestMethod.GET)
    public String logoutPage (HttpServletRequest request, HttpServletResponse response) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null){
            new SecurityContextLogoutHandler().logout(request, response, auth);
        }
        return "redirect:/login?logout";
    }

    @RequestMapping(value="/Enregistrement")
    public String Enregistrement(Model model){
        model.addAttribute("user", new User());
        return "Enregistrement";
    }

    @RequestMapping(value="/saveUser", method = RequestMethod.POST)
    public String saveUser(@Valid User user,
                           BindingResult bindingResult,
                           Model model){
        user.setRole("USER");
        user.setEnabled(true);
        if(bindingResult.hasErrors())
            return "Enregistrement";
        userRepository.save(user);
        model.addAttribute("user", user);
        return "ConfirmEnregistrement";
    }
}
