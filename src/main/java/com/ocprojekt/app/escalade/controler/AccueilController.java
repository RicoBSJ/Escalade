package com.ocprojekt.app.escalade.controler;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class AccueilController {

    @RequestMapping(value="/Accueil")
    public String Accueil(){
        return "Accueil";
    }

}