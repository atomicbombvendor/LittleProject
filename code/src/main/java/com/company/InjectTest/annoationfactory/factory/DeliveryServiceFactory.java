package com.company.InjectTest.annoationfactory.factory;

import com.company.InjectTest.annoationfactory.constant.FeedSource;
import com.company.InjectTest.annoationfactory.constant.FeedSourceType;
import com.company.InjectTest.annoationfactory.service.IDeliveryService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DeliveryServiceFactory extends _Factory<IDeliveryService, FeedSource> {

    private static final Logger LOG = LoggerFactory.getLogger(DeliveryServiceFactory.class);

    private final static DeliveryServiceFactory INSTANCE = new DeliveryServiceFactory();

    private DeliveryServiceFactory() {
        super(IDeliveryService.class, FeedSource.class);
    }

    public static DeliveryServiceFactory getInstance() {
        return INSTANCE;
    }


    public IDeliveryService getDeliveryService(FeedSourceType feedSourceType) throws Exception {
        return this.getElement(s -> s.value() == feedSourceType);
    }
}
