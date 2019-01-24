package com.company.InjectTest.annoationfactory.test;

import com.company.InjectTest.annoationfactory.constant.FeedSourceType;
import com.company.InjectTest.annoationfactory.factory.DeliveryServiceFactory;
import com.company.InjectTest.annoationfactory.service.FSPerShareDeliveryService;
import com.company.InjectTest.annoationfactory.service.IDeliveryService;
import org.junit.Assert;

public class app {

    public static void main(String[] args) throws Exception {

        IDeliveryService service = DeliveryServiceFactory.getInstance().getDeliveryService(FeedSourceType.FSP);
        Assert.assertEquals(service.getClass(), FSPerShareDeliveryService.class);

    }
}
