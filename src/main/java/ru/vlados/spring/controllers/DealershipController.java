package ru.vlados.spring.controllers;


import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.vlados.spring.dao.OrderDAO;
import ru.vlados.spring.models.Order;

import java.util.Map;


@Controller
@RequestMapping("/dealer")
@ComponentScan("ru.vlados.spring")
public class DealershipController {

    private final OrderDAO orderDAO;

    public DealershipController(OrderDAO orderDAO){
        this.orderDAO = orderDAO;
    }

    @ModelAttribute("cars")
    public Map<String,Integer> map(){
        return orderDAO.getCars();
    }




    @GetMapping()
    public String deal(Model model){
        model.addAttribute("orders",orderDAO.getOrders());
        return "dealer/car";
    }
    @GetMapping("/buy")
    public String buy(@RequestParam(value = "car",required = false) String car, Model model){
        if(orderDAO.getCars().containsKey(car)){
            model.addAttribute("price",orderDAO.getCars().get(car));
            model.addAttribute("car",car);
            return "dealer/orderPage";
        }
        return "redirect:/dealer/car";
    }
    @ModelAttribute("dealer")
    public String dealersName(){
        return "cool";
    }
    @PostMapping()
    public String createOrder(@ModelAttribute("order") Order order){
        orderDAO.add(order);
        return "/dealer/orderPage";
    }
    @GetMapping("/order")
    public String getOrderForm(@ModelAttribute("order")Order order){
        return "/dealer/order";
    }
}
