package sg.edu.nus.iss.pizzaassessment.repository;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import sg.edu.nus.iss.pizzaassessment.model.Order;

@Repository
public class PizzaRepository {
    
    @Autowired 
    @Qualifier("pizza")
    private RedisTemplate<String, Object> template;

    public void save(Order o) {
        template.opsForValue().set(o.getOrderID(), o.toJSON().toString());
    }

    public Optional<Order> get(String orderID) {
        String json = (String) template.opsForValue().get(orderID);

        if (json == null || json.trim().length() <= 0) {
            return Optional.empty();
        }

        return Optional.of(Order.create(json));
    }

}