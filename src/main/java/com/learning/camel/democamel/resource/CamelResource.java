package com.learning.camel.democamel.resource;

import com.learning.camel.democamel.model.Order;
import com.learning.camel.democamel.processor.OrderProcessor;
import lombok.extern.slf4j.Slf4j;
import org.apache.camel.BeanInject;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.model.rest.RestBindingMode;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Slf4j
public class CamelResource extends RouteBuilder {

    @BeanInject
    OrderProcessor processor;



    @Override
    public void configure() throws Exception {
        restConfiguration().component("servlet").port(9099).host("localhost").bindingMode(RestBindingMode.json);
        rest().get("helloworld").produces(MediaType.APPLICATION_JSON_VALUE).route().setBody(constant("Hello world"));

//        rest().get("Orders").produces(MediaType.APPLICATION_JSON_VALUE).route().setBody(() -> service.getOrders())
//                .endRest();

        rest().get("Orders")
                .produces(MediaType.APPLICATION_JSON_VALUE).outType(List.class)
                .route()
                .id("get-orders")
                .process(processor).endRest();

        rest().post("Order")
                .consumes(MediaType.APPLICATION_JSON_VALUE).type(Order.class)
                .produces(MediaType.APPLICATION_JSON_VALUE).outType(Order.class)
                .route()
                .id("post-order")
                .process(processor).endRest();
    }

    //abcdefghijklmnopqrstuvwxyz
}
