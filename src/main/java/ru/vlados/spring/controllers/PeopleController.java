package ru.vlados.spring.controllers;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.vlados.spring.dao.PersonDAO;
import ru.vlados.spring.models.Person;

@Controller
@RequestMapping("/people")
@ComponentScan("ru.vlados.spring")
public class PeopleController {

    private final PersonDAO personDAO;


    public PeopleController(PersonDAO personDAO) {
        this.personDAO = personDAO;
    }

    @GetMapping()
    public String index(Model model){
        model.addAttribute("people",personDAO.index());
        return "people/index";
    }
    @GetMapping("/{id}")
    public String show(@PathVariable("id")int id, Model model){
        model.addAttribute("person",personDAO.show(id));
        return "people/show";
    }
    @PostMapping()
    public String create(@ModelAttribute("person")Person person){
        personDAO.save(person);
        return "redirect:/people";
    }

    @GetMapping("/new")
    public String newPerson(Model model){
        model.addAttribute("person",new Person());
        return "people/new";
    }

}
