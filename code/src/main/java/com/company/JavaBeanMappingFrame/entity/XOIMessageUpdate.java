package com.company.JavaBeanMappingFrame.entity;

import java.time.LocalDateTime;

//Table: XOIMessageUpdate
public class XOIMessageUpdate {

    @com.company.JavaBeanMappingFrame.utils.Column(value="XOIMessageId")
    private String XOIMessageId;
    @com.company.JavaBeanMappingFrame.utils.Column(value="LastUpdateTimeDB")
    private LocalDateTime LastUpdateTime;


    public String getXOIMessageId() {

        return XOIMessageId;
    }

    @com.company.JavaBeanMappingFrame.utils.Column(value="setXOIMessageId")
    public void setXOIMessageId(String XOIMessageId) {

        this.XOIMessageId = XOIMessageId;
    }

    public LocalDateTime getLastUpdateTime() {

        return LastUpdateTime;
    }

    @com.company.JavaBeanMappingFrame.utils.Column(value="setLastUpdateTime")
    public void setLastUpdateTime(LocalDateTime lastUpdateTime) {

        LastUpdateTime = lastUpdateTime;
    }

    @Override
    public String toString() {
        return "XOIMessageUpdate{" +
                "XOIMessageId='" + XOIMessageId + '\'' +
                ", LastUpdateTime=" + LastUpdateTime +
                '}';
    }
}
