package Reservation.Reservation_airplain_demo.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import Reservation.Reservation_airplain_demo.models.FlightInfo;
import Reservation.Reservation_airplain_demo.models.Utilisateur;
import Reservation.Reservation_airplain_demo.repository.FlightInfoRepository;
import jakarta.servlet.http.HttpSession;

import org.springframework.ui.Model;

@Controller
@RequestMapping("/flight")
public class FlightInfoControler {

    @Autowired
    FlightInfoRepository repository;

    @PostMapping("/searchFlights")
    public String searchFlights(@RequestParam String from, @RequestParam String to,
            @RequestParam String departure, HttpSession session, Model model) {
        FlightInfo flightsearch = repository.findBySourceAndDestinationAndJourneyDate(from, to, departure);

        System.out.println("Med");
        if (flightsearch != null) {
            Utilisateur user = (Utilisateur) session.getAttribute("user");
            model.addAttribute("userId", user.getIdUtilisateur());
          model.addAttribute("userNmame",user.getNomUtilisateur());
            model.addAttribute("flightsearch", flightsearch);
            return "WEB-INF/BookingPage";
        } else {
            model.addAttribute("eroor", "true");
            model.addAttribute("message", "Aucun vol trouvé pour les critères spécifiés.");

            return "WEB-INF/HomePage_1";
        }
    }
    @GetMapping("all")
    public String getBankAccountPage(Model model) {
        System.out.println("deidine");
        List<FlightInfo> all = repository.findAll();
        model.addAttribute("flightlist", all);
        return "WEB-INF/HomePage_1";
    }

}
