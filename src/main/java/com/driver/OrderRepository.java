package com.driver;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class OrderRepository {

    Map<String,Order> orderMap=new HashMap<>();
    static Map<Integer,String> OrderDeliveryTime=new HashMap<>();
    List<Order> orderList=new ArrayList<>();
    Map<String,DeliveryPartner> deliveryPartnerMap=new HashMap<>();
    List<DeliveryPartner> deliveryPartnerList=new ArrayList<>();

    Map<String,String> orderPartnerPairMap=new HashMap<>();
    Map<String,List<String>> partnerOrders=new HashMap<>();

    public void addOrder(Order order)
    {
//        if(!orderMap.containsKey(order.getId()))
//        {
            orderMap.put(order.getId(),order);
            orderList.add(order);
//        }
    }

    public void addPartner(String partnerId)
    {
        if(!deliveryPartnerMap.containsKey(partnerId))
        {
            DeliveryPartner deliveryPartner=new DeliveryPartner(partnerId);
            deliveryPartnerMap.put(partnerId,deliveryPartner);
            deliveryPartnerList.add(deliveryPartner);
            partnerOrders.put(partnerId,new ArrayList<>());
        }
    }

    public void addOrderPartnerPair(String orderId, String partnerId)
    {
        if(orderMap.containsKey(orderId) && deliveryPartnerMap.containsKey(partnerId))
        {
            orderPartnerPairMap.put(orderId,partnerId);
//            if(partnerOrders.get(partnerId).contains(orderId))
//            {
//                partnerOrders.get(partnerId).remove(orderId);
//                deliveryPartnerMap.get(partnerId).setNumberOfOrders(partnerOrders.get(partnerId).size());
//            }
            partnerOrders.get(partnerId).add(orderId);
            deliveryPartnerMap.get(partnerId).setNumberOfOrders(partnerOrders.get(partnerId).size());
        }
    }

    public Order getOrderById(String orderId)
    {
        if(orderMap.containsKey(orderId))
            return orderMap.get(orderId);

        return null;
    }

    public DeliveryPartner getPartnerById(String partnerId)
    {
        if(deliveryPartnerMap.containsKey(partnerId))
        return deliveryPartnerMap.get(partnerId);

        return null;
    }

    public int getOrderCountByPartnerId(String partnerId)
    {
        return partnerOrders.get(partnerId).size();
    }

    public List<String> getOrdersByPartnerId(String partnerId)
    {
        return partnerOrders.get(partnerId);
    }

    public List<String> getAllOrders()
    {
        List<String> orders=new ArrayList<>();

        for(String s : orderMap.keySet())
        {
            orders.add(s);
        }

        return orders;
    }

    public int getCountOfUnassignedOrders()
    {
        return orderMap.size()-orderPartnerPairMap.size();
    }

    public int getOrdersLeftAfterGivenTimeByPartnerId(int time, String partnerId)
    {
        int count=0;

        List<String> orders=partnerOrders.get(partnerId);

        for(String orderId : orders)
        {
            Order order1=orderMap.get(orderId);

            if(order1.getDeliveryTime()>time)
            {
                count++;
            }
        }
        return count;
    }

    public String getLastDeliveryTimeByPartnerId(String partnerId){
        int max=0;

        for(String orderId : partnerOrders.get(partnerId))
        {
            Order order1=orderMap.get(orderId);

            if(max<order1.getDeliveryTime())
            {
                max=order1.getDeliveryTime();
            }
        }

        return OrderDeliveryTime.get(max);
    }

    public void deletePartnerById(String partnerId)
    {
        for(String orderId : orderPartnerPairMap.keySet())
        {
            if(orderPartnerPairMap.get(orderId).equals(partnerId))
            {
                partnerOrders.get(partnerId).remove(orderId);
                orderPartnerPairMap.remove(orderId);
            }
        }

        partnerOrders.remove(partnerId);
        deliveryPartnerMap.remove(partnerId);

        for(DeliveryPartner dp: deliveryPartnerList)
        {
            if(dp.getId().equals(partnerId))
            {
                deliveryPartnerList.remove(dp);
                break;
            }
        }
    }

    public void deleteOrderById(String orderId)
    {
        String partnerId= orderPartnerPairMap.get(orderId);
        orderPartnerPairMap.remove(orderId);
        partnerOrders.get(partnerId).remove(orderId);
        orderMap.remove(orderId);
        deliveryPartnerMap.get(partnerId).setNumberOfOrders(partnerOrders.get(partnerId).size());


        for(Order order : orderList)
        {
            if(order.getId().equals(orderId))
            {
                orderList.remove(order);
                break;
            }
        }

    }

}
