package com.company.DesignMode.Consumer_Producer3;

import com.lmax.disruptor.RingBuffer;
import org.apache.commons.lang3.RandomUtils;

import java.nio.ByteBuffer;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

/**
 * Created by eli9 on 8/28/2017.
 */
public class Producer {

    private final RingBuffer<PCData> ringBuffer;

    public Producer(RingBuffer ringBuffer){
        this.ringBuffer = ringBuffer;
    }

    public void pushData(ByteBuffer bb){

        long sequence = ringBuffer.next();

        try{
            PCData pcData = ringBuffer.get(sequence);
            pcData.set(bb.getLong(0));
        }finally {
            ringBuffer.publish(sequence);
        }
    }

}
