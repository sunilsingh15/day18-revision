package sg.edu.nus.iss.pizzaassessment.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.json.Json;
import jakarta.json.JsonObject;
import sg.edu.nus.iss.pizzaassessment.model.Order;
import sg.edu.nus.iss.pizzaassessment.service.PizzaService;

@RestController
@RequestMapping(path = "/order")
public class PizzaRestController {

    @Autowired
    private PizzaService service;

    @GetMapping(path = "{orderID}")
    public ResponseEntity<String> getOrderDetails(@PathVariable String orderID) {

        Optional<Order> ord = service.getOrderByOrderID(orderID);

        if (ord.isEmpty()) {
            JsonObject error = Json.createObjectBuilder()
                    .add("message", "Order %s not found".formatted(orderID))
                    .build();

            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error.toString());
        }

        return ResponseEntity.ok(ord.get().toJSON().toString());
    }

}
