package com.company.Thread.ObjectPool;

import com.sun.org.apache.xml.internal.utils.ObjectPool;
import org.apache.commons.pool2.impl.GenericObjectPool;
import org.apache.commons.pool2.impl.GenericObjectPoolConfig;

public class ObjectPoolTest extends ObjectPool {

    public static void main(String[] args) {
        ObjectPoolTest test = new ObjectPoolTest();
        test.testGenericObjectPool();
        test.testGenericObjectPool();
        test.testGenericObjectPool();
        test.testGenericObjectPool();
        test.testGenericObjectPool();
        test.testGenericObjectPool();
    }

    public void testGenericObjectPool(){
        MyPoolableObjectFactory factory = new MyPoolableObjectFactory();

        GenericObjectPoolConfig poolConfig = new GenericObjectPoolConfig();
        // 设置最大的空闲对象数量
        poolConfig.setMaxIdle(1);
        // 设置最小的空闲对象数量
        poolConfig.setMinIdle(0);
        // 设置对象池中对象最大的数量
        poolConfig.setMaxTotal(3);


        GenericObjectPool<TestConnection> objectPool = new GenericObjectPool<>(factory, poolConfig);
        TestConnection testConnection = null;
        try {
            testConnection = objectPool.borrowObject();
            System.out.println(testConnection.hashCode());
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            if (testConnection != null){
                objectPool.returnObject(testConnection);
            }
        }

    }
}
