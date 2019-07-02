package com.company.DesignMode.Consumer_Producer3;

import com.lmax.disruptor.WorkHandler;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

/**
 * Created by eli9 on 8/28/2017.
 */
public class Consumer implements WorkHandler<PCData> {

    @Override
    public void onEvent(PCData pcData) throws Exception {
        System.out.println(Thread.currentThread().getId() + " :Event: --" + pcData.get() * pcData.get() + " --");
    }
}
