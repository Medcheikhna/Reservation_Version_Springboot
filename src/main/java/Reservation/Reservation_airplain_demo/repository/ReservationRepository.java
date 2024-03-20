package Reservation.Reservation_airplain_demo.repository;

 
import org.springframework.data.jpa.repository.JpaRepository;

import Reservation.Reservation_airplain_demo.models.Reservation;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface ReservationRepository extends JpaRepository<Reservation,Integer> {

}
