package com.driver;

import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
@Repository
public class OrderRepository {
    List<Order> orders=new ArrayList<>();
    Map<DeliveryPartner,List<String>> deliveryPartner=new HashMap<>();

    Map<String,String> orderPartnerPair=new HashMap<>();

    public Order getOrderbyId(String id){
        for(Order o: orders){
            if(o.getId().equals(id)){
                return o;
            }
        }
        return null;
    }

    public DeliveryPartner getPartnerbyId(String id)
    {
        for(DeliveryPartner dP: deliveryPartner.keySet())
        {
            if(dP.getId().equals(id))
            {
                return dP;
            }
        }
        return null;
    }

    public int getOrderCountbyId(String partnerId)
    {
        for(DeliveryPartner dp : deliveryPartner.keySet())
        {
            if(dp.getId().equals(partnerId))
            {
                return dp.getNumberOfOrders();
            }
        }
        return 0;
    }

    public List<String> getOrdersbyId(String id)
    {
        for(DeliveryPartner dp : deliveryPartner.keySet())
        {
            if(dp.getId().equals(id))
            {
                return deliveryPartner.get(dp);
            }
        }
        return null;
    }

    public List<String> getAllOrders()
    {
        List<String> allOrders=new ArrayList<>();

        for(Order o : orders)
        {
            allOrders.add(o.getId());
        }

        return allOrders;
    }

    public int getCountOfUnassignedOrders()
    {
        int count=0;

        for(Order ord: orders)
        {
            if(!orderPartnerPair.containsKey(ord.getId()))
            {
                count++;
            }
        }

        return count;
    }

    public void check(String partnerId)
    {
        for(String s : orderPartnerPair.keySet())
        {
            if(orderPartnerPair.get(s).equals(partnerId))
            {
                orderPartnerPair.remove(s);
            }
        }
    }

    public void delOrder(String orderId)
    {
        for(Order o : orders)
        {
            if(o.getId().equals(orderId))
            {
                orders.remove(o);
            }
        }
    }

    public int getOrdersLeftAfterGivenTimeByPartnerId(String time, String partnerId)
    {
        int count=0;
        boolean f=true;
        int tym=Integer.parseInt(time);

        for(DeliveryPartner dp : deliveryPartner.keySet())
        {
            if(dp.getId().equals(partnerId))
            {
                for(String s : deliveryPartner.get(dp))
                {
                    for(Order ord: orders)
                    {
                        if(s.equals(ord.getId()) && ord.getDeliveryTime()>tym)
                        {
                            count++;
                        }
                    }
                }
               break;
            }
        }
        return count;
    }

}
