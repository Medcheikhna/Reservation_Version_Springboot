package Reservation.Reservation_airplain_demo.controllers;

import Reservation.Reservation_airplain_demo.models.PaymentData;
import Reservation.Reservation_airplain_demo.models.Reservation;
import Reservation.Reservation_airplain_demo.repository.ReservationRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;

@Controller
@RequestMapping("/payment")
public class PaymentController {

    private RestTemplate restTemplate;
    @Autowired
    ReservationRepository repository;
    @Autowired
    public PaymentController(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @PostMapping("/processPayment")
    public String processPayment(@RequestParam("cardNumber") String cardNumber,
                                 @RequestParam("owner") String owner,
                                 @RequestParam("cvv") String cvv,
                                 @RequestParam("expirationDate") String expirationDate,
                                 @RequestParam("accountNumber") String accountNumber,

                                 @RequestParam("amount") float amount,
                                 String flightIds,String time,String sources,String destinations,String userNmame,String userId,

                                 Model model
                                 ) {
//        String time = (String) session.getAttribute("time");
//        String flightIds = (String) session.getAttribute("flightIds");
       System.out.println("===/==/=#=#" + flightIds);
//        String sources = (String) session.getAttribute("sources");
//        String destinations = (String) session.getAttribute("destinations");
//        String userNmame = (String) session.getAttribute("userNmame");
       System.out.println("===/==/==" + userNmame);
        System.out.println("===/==/==" + time);
        // Stockage des données dans le modèle
        model.addAttribute("time", time);
        model.addAttribute("flightIds", flightIds);
        model.addAttribute("sources", sources);
        model.addAttribute("destinations", destinations);
        model.addAttribute("userNmame",userNmame);
        model.addAttribute("userId",userId);

        // Construire l'URL de votre API Django
        String apiUrl = "http://127.0.0.1:8000/make_payment/";

        // Construire les données du formulaire pour l'envoi à l'API Django
        PaymentData paymentData = new PaymentData();
        paymentData.setCardNumber(cardNumber);
        paymentData.setOwner(owner);
        paymentData.setCvv(cvv);
        paymentData.setExpirationDate(expirationDate);
        paymentData.setAccountNumber(accountNumber);

        paymentData.setAmount(amount);

        System.out.println("-========= "+paymentData + " -=========");

        // Convertir l'objet PaymentData en JSON
        ObjectMapper objectMapper = new ObjectMapper();
        String jsonData = null;
        try {
            jsonData = objectMapper.writeValueAsString(paymentData);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        // Envoyer les données du formulaire à l'API Django
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<String> entity = new HttpEntity<>(jsonData, headers);
       // ResponseEntity<String> response = restTemplate.postForEntity(apiUrl, entity, String.class);
        System.out.println(entity);
        ResponseEntity<String> response = restTemplate.postForEntity(apiUrl, entity, String.class);
//System.out.println(response);
        // Vérifier la réponse de l'API Django et rediriger en conséquence
        if (response.getStatusCode() == HttpStatus.OK) {


            return "WEB-INF/Ticket"; // Rediriger vers une page de confirmation de paiement réussi

        } else {

            String errorMessage = "Solde insuffisant sur la carte";
            model.addAttribute("errorMessage", errorMessage);
            return "WEB-INF/payment"; // Rediriger vers une page d'erreur de paiement
        }
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
        return "redirect:../user/login";
    }



}
