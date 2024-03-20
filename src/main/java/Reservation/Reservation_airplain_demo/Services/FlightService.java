// package Reservation.Reservation_airplain_demo.Services;


// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.stereotype.Service;

// import Reservation.Reservation_airplain_demo.models.FlightInfo;
// import Reservation.Reservation_airplain_demo.repository.FlightInfoRepository;

// import java.util.List;

// @Service
// public class FlightService {

//     @Autowired
//     private FlightInfoRepository flightRepository;

//     public List<FlightInfo> searchFlights(String from, String to, String departure) {
//         try {
//             return flightRepository.findBySourceAndDestinationAndJourneyDate(from, to, departure);
//         } catch (Exception e) {
//             e.printStackTrace();
//             // Gérez les exceptions appropriées ici
//             return null;
//         }
//     }
// }
