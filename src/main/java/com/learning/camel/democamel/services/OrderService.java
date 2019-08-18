package com.learning.camel.democamel.services;

import com.learning.camel.democamel.model.Order;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

@Component
public class OrderService {

    private List<Order> list = new ArrayList<>();

    @PostConstruct
    public void initDB() {
        list.add(new Order(67, "Mobile", 5000));
        list.add(new Order(89, "Book", 400));
        list.add(new Order(45, "AC", 15000));
        list.add(new Order(67, "Shoes", 4000));
    }

    public Order addOrder(Order order) {
        list.add(order);
        return order;
    }

    public List<Order> getOrders() {
        return list;
    }
}
