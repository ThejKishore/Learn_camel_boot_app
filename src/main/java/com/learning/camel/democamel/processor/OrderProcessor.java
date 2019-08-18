package com.learning.camel.democamel.processor;

import com.learning.camel.democamel.model.Order;
import com.learning.camel.democamel.services.OrderService;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component
public class OrderProcessor implements Processor {

    @Autowired
    private OrderService service;

    @Override
    public void process(Exchange exchange) throws Exception {

        if(exchange.getFromRouteId().equals("get-orders")){
            exchange.getOut().setBody(service.getOrders());
        } else if(exchange.getFromRouteId().equals("post-order")){
            service.addOrder(exchange.getIn().getBody(Order.class));
        }
    }

}