package com.company.JavaBeanMappingFrame.dao;

import com.company.JavaBeanMappingFrame.entity.XOIMessageUpdate;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.util.StringUtil;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by eli9 on 3/28/2018.
 */
public class daoTest {
    @Test
    public void getEntitys() throws Exception {
        String sql = "SELECT Top 10 XOIMessageId, " +
                "Convert(varchar(30) , LastUpdateTime, 120) LastUpdateTimeDB " +
                "From ReferenceData.dbo.XOIMessageUpdate";
        String db = "GeExoiDevDB8009";
        List<XOIMessageUpdate> result = dao.getEntitys(db, sql, XOIMessageUpdate.class);

        System.out.println(StringUtils.join(result, ","));
        Assert.assertTrue(result.size() > 1);
    }

}