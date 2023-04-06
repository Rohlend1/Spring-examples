package ru.vlados.spring.controllers;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;


import java.util.HashMap;
import java.util.Map;


@Controller
@RequestMapping("/dealer")
public class DealershipController implements InitializingBean {
    private final Map<String,Integer> cars = new HashMap<>();

    @Override
    public void afterPropertiesSet(){
        cars.put("Porsche",10_000_000);
        cars.put("Mercedes",15_000_000);
        cars.put("BMW",8_000_000);
        cars.put("Lada",1_000_000);
        cars.put("Tesla",3_000_000);
    }
    @GetMapping("")
    public String deal(Model model){
        return "dealer/dealer";
    }
    @GetMapping("/buy")
    public String buy(@RequestParam(value = "car",required = false) String car, Model model){
        if(cars.containsKey(car)){
            model.addAttribute("price",cars.get(car));
            model.addAttribute("car",car);
            return "dealer/price";
        }
        return "dealer/dealer";
    }
    @ModelAttribute("dealer")
    public String dealersName(){
        return "cool";
    }

}
