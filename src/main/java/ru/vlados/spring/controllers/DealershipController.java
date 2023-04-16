package ru.vlados.spring.controllers;


import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.vlados.spring.models.Car;
import ru.vlados.spring.models.Order;
import ru.vlados.spring.services.CarService;
import ru.vlados.spring.services.OrdersService;

import java.util.List;


@Controller
@RequestMapping("/dealer")
@ComponentScan("ru.vlados.spring")
public class DealershipController {

    private final OrdersService ordersService;
    private final CarService carService;

    @Autowired
    public DealershipController(OrdersService ordersService, CarService carService){
        this.ordersService = ordersService;
        this.carService = carService;
    }

    @ModelAttribute("cars")
    public List<Car> map(){
        return carService.findAll();
    }

    @GetMapping()
    public String deal(@ModelAttribute("order")Order order,Model model){
        model.addAttribute("orders",ordersService.findAll());
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
        Car car = carService.findByName(order.getModel());
        order.setChosenCar(car);
        order.setPrice(car.getPrice());
        ordersService.save(order);
        return "redirect:/dealer";
    }
    @GetMapping("/order/{id}")
    public String getOrderForm(Model model,@PathVariable(value = "id") int id){
        Order order = ordersService.findOne(id);
        model.addAttribute("order", order);
        model.addAttribute("cars", carService.findAll());
        return "/dealer/orderPage";
    }

    @PatchMapping("/order/{id}")
    public String editOrder(@ModelAttribute("order") @Valid Order order, BindingResult bindingResult, @PathVariable("id")int id, Model model){
        Order originalOrder = ordersService.findOne(id);
        if (originalOrder == null) {
            return "dealer/orderPage";
        }

        if(bindingResult.hasErrors()) {
            // передаем оригинальный заказ и ошибки в представление
            model.addAttribute("order", originalOrder);
            model.addAttribute("errors", bindingResult.getAllErrors());
            return "dealer/orderPage";
        }
        Car car = carService.findByName(order.getModel());
        order.setChosenCar(car);
        order.setPrice(car.getPrice());
        // обновляем только выбранную машину и дату
        originalOrder.setDate(order.getDate());

        ordersService.update(id,originalOrder);
        return "redirect:/dealer";
    }

    @DeleteMapping("/order/{id}")
    public String deleteOrder(@ModelAttribute("order") Order order, @PathVariable("id") int id){
        ordersService.delete(id);
        return "redirect:/dealer";
    }
}
