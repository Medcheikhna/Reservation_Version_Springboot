package Reservation.Reservation_airplain_demo.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import Reservation.Reservation_airplain_demo.models.FlightInfo;

public interface FlightInfoRepository extends JpaRepository<FlightInfo, Long> {
 
    @Query(value = "from FlightInfo where source = ?1 and destination = ?2 and journey_date = ?3 " )
    FlightInfo findBySourceAndDestinationAndJourneyDate(String source, String destination, String journeyDate);
}
