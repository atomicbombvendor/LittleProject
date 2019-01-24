package com.company.InjectTest.annoationfactory.service;


import com.company.InjectTest.annoationfactory.constant.FeedSource;
import com.company.InjectTest.annoationfactory.constant.FeedSourceType;

@FeedSource(FeedSourceType.FSP)
public class FSPerShareDeliveryService implements IDeliveryService {


    @Override
    public String getValue(String input) {
        return "input: " + input;
    }
}