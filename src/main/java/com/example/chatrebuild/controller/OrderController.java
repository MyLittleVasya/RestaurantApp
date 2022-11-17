package com.example.chatrebuild.controller;

import com.example.chatrebuild.RequestBodies.CreateOrderRequest;
import com.example.chatrebuild.dto.OrderDTO;
import com.example.chatrebuild.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
public class OrderController {

    @Autowired
    private OrderService orderService;

    @PostMapping("/createOrder")
    public HttpStatus createOrder(@RequestBody CreateOrderRequest request) throws Exception {
        if (orderService.createNewOrder(request))
            return HttpStatus.OK;
        else
            return HttpStatus.INTERNAL_SERVER_ERROR;
    }

    @GetMapping("/showOrders")
    public List<OrderDTO> showOrdersOfUser(@RequestParam String login) {
        return orderService.returnOrdersDTO(login);
    }

    @PostMapping("/deleteOrder")
    public HttpStatus deleteOrder(@RequestParam long id) {
        if (orderService.deleteOrder(id))
            return HttpStatus.OK;
        else
            return HttpStatus.BAD_GATEWAY;
    }

    @GetMapping("/getAllOrders")
    public List<OrderDTO> getAllOrders() {
        return orderService.returnAllOrdersDTO();
    }

    @PostMapping("/changeOrderState")
    public HttpStatus changeOrderState(@RequestBody Map<String, String> request) {
        if (orderService.changeState(request))
            return HttpStatus.OK;
        return HttpStatus.BAD_GATEWAY;
    }
}
