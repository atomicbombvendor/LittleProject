package com.company.utils;

import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by atomic on 5/26/2017.
 */
public class MD5UtilTest {
    @Test
    public void getMD5() throws Exception {
        String value1 = "0P000000071AQ12002033112200203300.0655940.0655940.016218";
        String value2 = "0P000000071AQ12002033112200203000.0655940.0655940.016218";
        String md5_1 = MD5Util.getMD5(value1);
        String md5_2 = MD5Util.getMD5(value2);

//        Assert.assertTrue(md5_1.equals(md5_2));

        Assert.assertTrue(value1.equals(value2));

    }

}