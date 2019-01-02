package com.company.Thread.ObjectPool;

import com.company.Dao.TestC;
import com.sun.org.apache.xml.internal.utils.ObjectPool;
import org.apache.commons.pool2.impl.GenericObjectPool;
import org.apache.commons.pool2.impl.GenericObjectPoolConfig;

public class ObjectPoolTest extends ObjectPool {

    public void testGenericObjectPool(){
        MyPoolableObjectFactory factory = new MyPoolableObjectFactory();

        GenericObjectPoolConfig poolConfig = new GenericObjectPoolConfig();
        // 设置最大的空闲对象数量
        poolConfig.setMaxIdle(20);
        // 设置最小的空闲对象数量
        poolConfig.setMinIdle(20);
        // 设置对象池中对象最大的数量
        poolConfig.setMaxIdle(20);


        GenericObjectPool<TestConnection> objectPool = new GenericObjectPool<>(factory, poolConfig);
        TestConnection testConnection = null;
        try {
            testConnection = objectPool.borrowObject();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            if (testConnection != null){
                objectPool.returnObject(testConnection);
            }
        }

    }
}
