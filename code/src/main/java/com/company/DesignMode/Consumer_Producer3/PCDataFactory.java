package com.company.DesignMode.Consumer_Producer3;

import com.lmax.disruptor.EventFactory;

public class PCDataFactory implements EventFactory {

    @Override
    public PCData newInstance(){
        return new PCData();
    }
}
