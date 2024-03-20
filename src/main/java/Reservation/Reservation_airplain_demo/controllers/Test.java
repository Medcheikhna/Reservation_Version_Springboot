package Reservation.Reservation_airplain_demo.controllers;

 
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import Reservation.Reservation_airplain_demo.models.FlightInfo;
import Reservation.Reservation_airplain_demo.repository.FlightInfoRepository;

import org.springframework.ui.Model;

@Controller
@RequestMapping("/reservation")
public class Test {
 
    @GetMapping("/test" )
    public String getBankAccountPage(Model model) { 
        return "WEB-INF/test";
    }

}
