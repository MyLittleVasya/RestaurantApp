package com.example.chatrebuild.service;

import com.example.chatrebuild.RequestBodies.CreateOrderRequest;
import com.example.chatrebuild.dto.DishDTO;
import com.example.chatrebuild.dto.OrderDTO;
import com.example.chatrebuild.entity.DishEntity;
import com.example.chatrebuild.entity.OrderEntity;
import com.example.chatrebuild.entity.OrderState;
import com.example.chatrebuild.repo.DishRepo;
import com.example.chatrebuild.repo.OrderRepo;
import com.example.chatrebuild.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class OrderService {

    @Autowired
    private OrderRepo orderRepo;
    @Autowired
    private DishRepo dishRepo;
    @Autowired
    private UserRepo userRepo;
    public boolean createNewOrder(CreateOrderRequest request) throws Exception{
        try {
            var dishList = new ArrayList<DishEntity>();
            String[] idList = request.getIdList().split(" ");
            for (String id: idList) {
                var dish = dishRepo.findById(Long.parseLong(id));
                dishRepo.save(dish);
                dishList.add(dish);
            }
            var order = new OrderEntity(userRepo.findByUsername(request.getLogin()), dishList);
            orderRepo.save(order);
            return true;
        }
        catch (Exception e) {
            return false;
        }

    }

    @Transactional
    public List<OrderDTO> returnOrdersDTO(String login) {
        var user = userRepo.findByUsername(login);
        var orderList = orderRepo.findByCustomer(user);
        var returnList = new ArrayList<OrderDTO>();
        for (var order:orderList) {
            var dishDTOList = new ArrayList<DishDTO>();
            for (var dish: order.getDishList()){
                dishDTOList.add(new DishDTO(dish.getDishName(), dish.getPrice()));
            }
            returnList.add(new OrderDTO(order.getId(),  dishDTOList, order.getPrice(), order.getOrderState().toString()));
        }
        return returnList;
    }

    public boolean deleteOrder(long id) {
        var order = orderRepo.findById(id);
        if (order != null) {
            orderRepo.delete(order);
            return true;
        }
        return false;
    }
    @Transactional
    public List<OrderDTO> returnAllOrdersDTO() {
        var orderList = orderRepo.findAll();
        var returnList = new ArrayList<OrderDTO>();
        for (var order:orderList) {
            var dishDTOList = new ArrayList<DishDTO>();
            for (var dish: order.getDishList()){
                dishDTOList.add(new DishDTO(dish.getDishName(), dish.getPrice()));
            }
            returnList.add(new OrderDTO(order.getId(),  dishDTOList, order.getPrice(), order.getOrderState().toString()));
        }
        return returnList;
    }

    public boolean changeState(Map<String, String> request) {
        var order = orderRepo.findById(Long.parseLong(request.get("id")));
        if (order != null) {
            if (request.get("state").equals("PROCESSING")) {
                order.setOrderState(OrderState.PROCESSING);
                orderRepo.save(order);
                return true;
            }
            if (request.get("state").equals("TROUBLE")) {
                order.setOrderState(OrderState.TROUBLE);
                orderRepo.save(order);
                return true;
            }
            if (request.get("state").equals("READY")) {
                order.setOrderState(OrderState.READY);
                orderRepo.save(order);
                return true;
            }
        }
        return false;
    }
}
