package sg.edu.nus.iss.pizzaassessment.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import sg.edu.nus.iss.pizzaassessment.model.Delivery;
import sg.edu.nus.iss.pizzaassessment.model.Order;
import sg.edu.nus.iss.pizzaassessment.model.Pizza;
import sg.edu.nus.iss.pizzaassessment.service.PizzaService;

@Controller
@RequestMapping
public class PizzaController {

    @Autowired
    private PizzaService service;

    @GetMapping(path ="/")
    public String showLandingPage(Model m) {
        m.addAttribute("pizza", new Pizza());
        return "index";
    }

    @PostMapping(path="/pizza")
    public String postPizza(Model m, HttpSession session, @Valid Pizza pizza, BindingResult result) {

        if (result.hasErrors()) {
            return "index";            
        }

        List<ObjectError> errors = service.validatePizzaOrder(pizza);
        if (!errors.isEmpty()) {
            for (ObjectError e : errors) {
                result.addError(e);
            }
            return "index";
        }

        session.setAttribute("pizza", pizza);
        m.addAttribute("delivery", new Delivery());

        return "delivery";
    }

    @PostMapping(path="/pizza/order")
    public String postPizzaOrder(Model model, HttpSession session, @Valid Delivery delivery, BindingResult result) {

        if (result.hasErrors()) {
            return "delivery";
        }

        Pizza p = (Pizza) session.getAttribute("pizza");
        Order o = service.savePizza(p, delivery);
        model.addAttribute("order", o);

        return "order";
    }

}
