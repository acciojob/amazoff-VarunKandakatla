package com.driver;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class OrderService {
    @Autowired
    OrderRepository orderRepository;
    public void addOrder(Order order){
        orderRepository.orders.add(order);
    }

    public void addPartner(String partnerId)
    {
        orderRepository.deliveryPartner.put(new DeliveryPartner(partnerId),new ArrayList<>());
    }

    public void addOrderPartnerPair(String orderId, String partnerId)
    {
        for(DeliveryPartner dp: orderRepository.deliveryPartner.keySet()){
            if(dp.getId().equals(partnerId))
            {
               if( orderRepository.deliveryPartner.get(dp).contains(orderId))
                break;
               else {
                   orderRepository.deliveryPartner.get(dp).add(orderId);
                   dp.setNumberOfOrders(dp.getNumberOfOrders()+1);
               }
            }

        }
        orderRepository.orderPartnerPair.put(orderId,partnerId);


    }

    public Order getOrderbyId(String orderId)
    {
        return orderRepository.getOrderbyId(orderId);
    }

    public DeliveryPartner getPartnerbyId(String id)
    {
        return orderRepository.getPartnerbyId(id);
    }

    public int getOrderCountbyId(String partnerId)
    {
        return orderRepository.getOrderCountbyId(partnerId);
    }

    public List<String>  getOrdersbyId(String id)
    {
        return orderRepository.getOrdersbyId(id);
    }

    public List<String> getAllOrders()
    {
        return orderRepository.getAllOrders();
    }

    public int getCountOfUnassignedOrders()
    {
        return orderRepository.getCountOfUnassignedOrders();
    }

    public void deletePartnerById(String partnerId)
    {
        orderRepository.deliveryPartner.remove(partnerId);
        orderRepository.check(partnerId);
    }

    public void deleteOrderById(String orderId)
    {
        orderRepository.orderPartnerPair.remove(orderId);
        orderRepository.delOrder(orderId);
//        orderRepository.orders.remove(orderId);
    }

    public int getOrdersLeftAfterGivenTimeByPartnerId(String time, String partnerId)
    {
        return orderRepository.getOrdersLeftAfterGivenTimeByPartnerId(time,partnerId);
    }
}
