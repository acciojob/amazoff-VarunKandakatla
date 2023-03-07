package com.driver;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderService {
    @Autowired
    OrderRepository orderRepository;

    public void addOrder(Order order)
    {
        orderRepository.addOrder(order);
    }

    public void addPartner(String partnerId)
    {
        orderRepository.addPartner(partnerId);
    }

    public void addOrderPartnerPair(String orderId, String partnerId)
    {
        orderRepository.addOrderPartnerPair(orderId,partnerId);
    }

    public Order getOrderById(String orderId)
    {
        return orderRepository.getOrderById(orderId);
    }

    public DeliveryPartner getPartnerById(String partnerId)
    {
        return orderRepository.getPartnerById(partnerId);
    }

    public int getOrderCountByPartnerId(String partnerId)
    {
        int a=0;
        try{
            a=orderRepository.getOrderCountByPartnerId(partnerId);
        }
        catch (Exception e){
            return 0;
        }
        return a;
    }

    public List<String> getOrdersByPartnerId(String partnerId)
    {
        return orderRepository.getOrdersByPartnerId(partnerId);
    }

    public List<String> getAllOrders()
    {
        return orderRepository.getAllOrders();
    }

    public int getCountOfUnassignedOrders(){
        return orderRepository.getCountOfUnassignedOrders();
    }

    public int getOrdersLeftAfterGivenTimeByPartnerId(String time, String partnerId)
    {
        String stringTime[]=time.split(":");

        int hours=Integer.parseInt(stringTime[0]);
        int mins=Integer.parseInt(stringTime[1]);

        int tym=hours*60+mins;
        return orderRepository.getOrdersLeftAfterGivenTimeByPartnerId(tym,partnerId);
    }

    public String getLastDeliveryTimeByPartnerId(String partnerId)
    {
        return orderRepository.getLastDeliveryTimeByPartnerId(partnerId);
    }

    public void deletePartnerById(String partnerId)
    {
        orderRepository.deletePartnerById(partnerId);
    }

    public void deleteOrderById(String orderId)
    {
        orderRepository.deleteOrderById(orderId);
    }
}
