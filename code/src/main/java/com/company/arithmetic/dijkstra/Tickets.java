package com.company.arithmetic.dijkstra;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class Tickets {

    /**
     * 单程票采用限时分段计价（每相邻两站之间为1个区间），起价1.5元，
     * 可乘坐6个区间，
     * 7-9个区间票价2元，
     * 10-12个区间票价2.5元，
     * 13-15个区间票价3元，
     * 16-20个区间票价3.5元，
     * 21-25个区间票价4元，
     * 全程票价4 元。每次乘车限时150分钟。
     */
    public static BigDecimal calcNormalTicketPrice(int stations){

        if (stations <= 6){
            return BigDecimal.valueOf(1.5).setScale(2, RoundingMode.HALF_UP);
        }

        if (stations <= 9){
            return BigDecimal.valueOf(2.0).setScale(2, RoundingMode.HALF_UP);
        }

        if (stations <= 12){
            return BigDecimal.valueOf(2.5).setScale(2, RoundingMode.HALF_UP);
        }

        if (stations <= 15){
            return BigDecimal.valueOf(3.0).setScale(2, RoundingMode.HALF_UP);
        }

        if (stations <= 20){
            return BigDecimal.valueOf(3.5).setScale(2, RoundingMode.HALF_UP);
        }else{
            return BigDecimal.valueOf(4.0).setScale(2, RoundingMode.HALF_UP);
        }
    }

    public static BigDecimal calcWuHanTong(BigDecimal singTicketPrice){
        return singTicketPrice.multiply(BigDecimal.valueOf(0.9))
                .setScale(2, RoundingMode.HALF_UP);
    }

    public static BigDecimal calcDayTickets(BigDecimal singTicketPrice){
        return BigDecimal.valueOf(0.0).setScale(2, RoundingMode.HALF_UP);
    }
}
