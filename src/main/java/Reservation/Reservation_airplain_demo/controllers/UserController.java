package Reservation.Reservation_airplain_demo.controllers;

 
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import Reservation.Reservation_airplain_demo.models.FlightInfo;
import Reservation.Reservation_airplain_demo.models.Reservation;
import Reservation.Reservation_airplain_demo.models.Utilisateur;
import Reservation.Reservation_airplain_demo.repository.FlightInfoRepository;
import Reservation.Reservation_airplain_demo.repository.UtilisateurRepository;
import jakarta.servlet.http.HttpSession;

import org.springframework.ui.Model;

@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    UtilisateurRepository repository;

    @Autowired
    FlightInfoRepository fightrepository;
    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;
    @GetMapping("all")
    public String getBankAccountPage(Model model) {
        System.out.println("deidine");
        List<Utilisateur> all = repository.findAll();
        model.addAttribute("users", all);
        return "WEB-INF/HomePage_1";
    }
 

    @GetMapping("login")
    public String loginPage(Model model) {
  
      return "WEB-INF/index";
    }
    @GetMapping("signup")
    public String signupPage(Model model) {
  
      return "WEB-INF/signup";
    }

  @PostMapping("verifyLogin")
  public String login(HttpSession session, String username, String password, Model model) {
 
    Utilisateur user = repository.findByNomUtilisateur(username);
      BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
    if (user != null && encoder.matches(password, user.getMotDePasse())) {
      session.setAttribute("user", user);

      // model.addAttribute("login",user);
      System.out.println("ok " + username + " " + password);
      model.addAttribute("user", user);

        List<FlightInfo> all = fightrepository.findAll();
        model.addAttribute("flightlist", all);
      return "WEB-INF/HomePage_1";
    }
    System.out.println("" + username + " " + password);
    model.addAttribute("eroor", "true");

    return "WEB-INF/index";

  }
 @PostMapping("/verifysignup")
 public String createUser(Utilisateur user, String confirmerMotDePasse, Model model) {
     // Vérifier si les mots de passe correspondent
     if (!user.getMotDePasse().equals(confirmerMotDePasse)) {
         model.addAttribute("error", "Le mot de passe et la confirmation ne correspondent pas");
         return "WEB-INF/signup"; // Rediriger vers la page d'inscription avec un message d'erreur
     }

     // Vérifier la complexité du mot de passe
     String password = user.getMotDePasse();
     if (!password.matches("^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,}$")) {
         model.addAttribute("error", "Le mot de passe doit contenir au moins une minuscule, une majuscule, un chiffre, un caractère spécial et au moins 8 caractères au total");
         return "WEB-INF/signup"; // Rediriger vers la page d'inscription avec un message d'erreur
     }

     // Encrypter le mot de passe et enregistrer l'utilisateur
     String encryptedPassword = bCryptPasswordEncoder.encode(user.getMotDePasse());
     user.setMotDePasse(encryptedPassword);
     repository.save(user);

     return "redirect:../user/login";
 }


}
