package Reservation.Reservation_airplain_demo.controllers;

import java.util.List;

import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import Reservation.Reservation_airplain_demo.models.FlightInfo;
import Reservation.Reservation_airplain_demo.models.Reservation;
import Reservation.Reservation_airplain_demo.repository.ReservationRepository;

import org.springframework.ui.Model;

@Controller
@RequestMapping("/reservation")
public class ReservationControler {

    @Autowired
    ReservationRepository repository;

    @GetMapping("all")
    public String getBankAccountPage(Model model) {
        System.out.println("deidine");
        List<Reservation> all = repository.findAll();
        model.addAttribute("flightlist", all);
        return "WEB-INF/HomePage_1";
    }

    @PostMapping("/createReservation")
    public String createReservation(String userId, String flightIds,String time,String sources,String destinations,String userNmame,
                                    Model model) {
                System.out.println("MedUserId "+ userId);
                model.addAttribute("searchBooking", "searchBooking");

        Reservation reservation = new Reservation();

        System.out.println("========source "+sources);

        System.out.println(userNmame);

        // Stockage des données dans le modèle
        model.addAttribute("time", time);
        model.addAttribute("flightIds", flightIds);
        model.addAttribute("sources", sources);
        model.addAttribute("destinations", destinations);
        model.addAttribute("userNmame",userNmame);

        reservation.setIdUtilisateur((long) Integer.parseInt(userId));
        reservation.setFlightid(Integer.parseInt(flightIds));
        reservation.setStatus("Reserve");
        repository.save(reservation);
         return "WEB-INF/payment";

    }
    @PostMapping("/cancelReservation")
    public String cancelReservation(String userId, String flightIds) {

        Reservation reservation = new Reservation();
        reservation.setIdUtilisateur((long) Integer.parseInt(userId));
        reservation.setFlightid(Integer.parseInt(flightIds));
        reservation.setStatus("Annuler");
        repository.save(reservation);
        System.out.println("Helloe in HomPage"+ flightIds);

        // Redirection vers la page suivante
        return "WEB-INF/HomePage_1";
    }

}
