package com.company.testCase;

import org.apache.log4j.Logger;

/**
 * Created by atomic on 8/15/2017.
 */
public class ETLJava {
    private static final Logger logError = Logger.getLogger(ETLJava.class);

    private static String ExchangeSubMarketGlobalId_Out;

    public static void testGeneralInfo(String ExchangeId, String ExchangeSubMarketGlobalId){
        try {
            //logInfo("ExchangeSubMarketGlobalId:"+ExchangeSubMarketGlobalId+",ExchangeId:"+ExchangeId);
            if (ExchangeSubMarketGlobalId != null && ExchangeSubMarketGlobalId.length() > 0) {
                String exchangeKeyPrefix="[{\"ExchangeId\":\"";
                String subMartketKeyPrefix="\"Submarket\":\"";
                int exchangeKeyStart = ExchangeSubMarketGlobalId.indexOf(exchangeKeyPrefix);
                int subMarketKeyStart = ExchangeSubMarketGlobalId.indexOf(subMartketKeyPrefix);
                String exchangeIdTxt = "";
                String subMarketExchangeIdTxt = "";
                if (exchangeKeyStart >= 0) {
                    int exchangeValueStart = exchangeKeyStart + exchangeKeyPrefix.length();
                    int exchangeValueEnd = exchangeValueStart + 10;
                    if(exchangeValueEnd<=ExchangeSubMarketGlobalId.length()){
                        exchangeIdTxt = ExchangeSubMarketGlobalId.substring(exchangeValueStart, exchangeValueEnd);
                    }else{
                        logError("Wrong format:" + ExchangeSubMarketGlobalId);
                    }
                }
                if (subMarketKeyStart >= 0) {
                    int subMarketValueStart = subMarketKeyStart + subMartketKeyPrefix.length();
                    int subMarketValueEnd = subMarketValueStart + 10;
                    if(subMarketValueEnd<=ExchangeSubMarketGlobalId.length()){
                        subMarketExchangeIdTxt = ExchangeSubMarketGlobalId.substring(subMarketValueStart, subMarketValueStart + 10);
                    }else{
                        logError("Wrong format:" + ExchangeSubMarketGlobalId);
                    }
                }

                if(ExchangeId != null && ExchangeId.length()>0) {
                    if (ExchangeId.equals(exchangeIdTxt)) {
                        ExchangeSubMarketGlobalId_Out = subMarketExchangeIdTxt;
                    } else if (ExchangeId.equals("EX$$$$XOTC") && exchangeIdTxt.equals("EX$$$$PINX")) {
                        ExchangeSubMarketGlobalId_Out = subMarketExchangeIdTxt;
                    }
                    System.out.println(ExchangeSubMarketGlobalId_Out);
                }
            } else {
                ExchangeSubMarketGlobalId_Out = null;
                System.out.println(ExchangeSubMarketGlobalId_Out);
            }

            //logInfo("ExchangeSubMarketGlobalId_Out:"+ExchangeSubMarketGlobalId_Out);
        } catch (Exception e) {
            logError("Fail to parse exchange submarket. Exception:" + e);
        }
    }

    public static void main(String[] args) {
        String ExchangeId="NSA";
        String ExchangeSubMarketGlobalId = "[{\"ExchangeId\":\"EX$$$$PINX\",\"Submarket\":\"EX$$$$PINC\"}]";
        testGeneralInfo(ExchangeId, ExchangeSubMarketGlobalId);
        testGeneralInfo(ExchangeId, null);
        testGeneralInfo(null, ExchangeSubMarketGlobalId);
        testGeneralInfo(null, null);
    }

    public static void logError(String msg){
        System.out.println("logError: "+msg);
    }
}
