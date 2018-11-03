package com.company.testCase;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by atomic on 4/26/2017.
 * 测试JDBCTemplate的批量更新
 */
public class testbathSqlJoin {

    /**
     * 测试批量更新的sql是怎么样的
     * 原博客链接： http://blog.csdn.net/nifury/article/details/51698021
     */
    public static void updateWinnerList() {
        List<String> orderInfoList = new ArrayList<>();
        String s1 = "winner_id1,express_company1,express_order1";
        String s2 = "winner_id2,express_company2,express_order2";
        String s3 = "winner_id3,express_company3,express_order3";
        String s4 = "winner_id3,express_company4,express_order4";
        orderInfoList.add(s1); orderInfoList.add(s2); orderInfoList.add(s3); orderInfoList.add(s4);
        String prefix ="INSERT INTO `one_winner`(`winner_id`,`express_company`,`express_order`) VALUES \n";
        StringBuffer suffix = new StringBuffer();
        for (int i = 1,length = orderInfoList.size(); i < length; i++) {
            String oneWinner = orderInfoList.get(i);
            String[] ary = oneWinner.split(",");
            suffix.append("('"+ary[0]+"','"+ary[1]+"','"+ary[2]+"')\n,");
        }
        String sql = prefix+suffix.substring(0, suffix.length() - 2)+
                "on duplicate key update `express_company`=values(`express_company`),`express_order`=values(`express_order`)";
        System.out.println(sql);
    }
}
