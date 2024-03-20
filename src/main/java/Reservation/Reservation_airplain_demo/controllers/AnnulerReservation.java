package Reservation.Reservation_airplain_demo.controllers;

import Reservation.Reservation_airplain_demo.models.Reservation;
import Reservation.Reservation_airplain_demo.repository.ReservationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/annuler")
public class AnnulerReservation {

    @Autowired
    ReservationRepository repository;


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
