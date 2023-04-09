package ru.vlados.spring.controllers;


import jakarta.validation.Valid;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.vlados.spring.dao.OrderDAO;
import ru.vlados.spring.models.Car;
import ru.vlados.spring.models.Order;

import java.util.List;


@Controller
@RequestMapping("/dealer")
@ComponentScan("ru.vlados.spring")
public class DealershipController {

    private final OrderDAO orderDAO;

    public DealershipController(OrderDAO orderDAO){
        this.orderDAO = orderDAO;
    }

    @ModelAttribute("cars")
    public List<Car> map(){
        return orderDAO.getCars();
    }

    @GetMapping()
    public String deal(@ModelAttribute("order")Order order,Model model){
        model.addAttribute("orders",orderDAO.getOrders());
        return "dealer/car";
    }
    @ModelAttribute("dealer")
    public String dealersName(){
        return "cool";
    }
    @PostMapping()
    public String createOrder(@ModelAttribute("order")@Valid Order order,BindingResult bindingResult){
        if(bindingResult.hasErrors()) {
            return "dealer/car";
        }
        orderDAO.add(order);
        return "redirect:/dealer";
    }
    @GetMapping("/order/{id}")
    public String getOrderForm(Model model,@PathVariable(value = "id") int id){
        Order order = orderDAO.getOrder(id);
        model.addAttribute("order", order);
        model.addAttribute("cars", orderDAO.getCars());
        model.addAttribute("car",order.getChosenCar());
        return "/dealer/orderPage";
    }

    @PatchMapping("/order/{id}")
    public String editOrder(@ModelAttribute("order") @Valid Order order, BindingResult bindingResult, @PathVariable("id")int id, Model model){
        Order originalOrder = orderDAO.getOrder(id);
        if (originalOrder == null) {
            return "dealer/orderPage";
        }

        if(bindingResult.hasErrors()) {
            // передаем оригинальный заказ и ошибки в представление
            model.addAttribute("order", originalOrder);
            model.addAttribute("errors", bindingResult.getAllErrors());
            return "dealer/orderPage";
        }
        // обновляем только выбранную машину и дату
        originalOrder.setChosenCar(order.getChosenCar());
        originalOrder.setDate(order.getDate());

        orderDAO.edit(originalOrder, id);
        return "redirect:/dealer";
    }

    @DeleteMapping("/order/{id}")
    public String deleteOrder(@ModelAttribute("order") Order order, @PathVariable("id") int id){
        orderDAO.delete(id);
        return "redirect:/dealer";
    }
}
