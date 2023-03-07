package com.driver;

import org.springframework.beans.factory.annotation.Autowired;

public class Order {

    private String id;
    private int deliveryTime;
@Autowired
    OrderRepository orderRepository;
    public Order(String id, String deliveryTime) {

        // The deliveryTime has to converted from string to int and then stored in the attribute
        //deliveryTime  = HH*60 + MM
        this.id=id;
        String stringTime[]=deliveryTime.split(":");

        int hours=Integer.parseInt(stringTime[0]);
        int mins=Integer.parseInt(stringTime[1]);

        this.deliveryTime=hours*60+mins;

        orderRepository.OrderDeliveryTime.put(this.deliveryTime,deliveryTime);
    }

    public String getId() {
        return id;
    }

    public int getDeliveryTime() {return deliveryTime;}
}
