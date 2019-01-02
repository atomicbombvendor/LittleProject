package com.company.Thread.ObjectPool;

import org.apache.commons.pool2.PooledObject;
import org.apache.commons.pool2.PooledObjectFactory;
import org.apache.commons.pool2.impl.DefaultPooledObject;

public class MyPoolableObjectFactory implements PooledObjectFactory<TestConnection> {

    /**
     * 构造一个新的对象
     */
    @Override
    public PooledObject<TestConnection> makeObject() throws Exception {
        TestConnection connection = new TestConnection();
        return new DefaultPooledObject<>(connection);
    }

    /**
     * 断开连接
     */
    @Override
    public void destroyObject(PooledObject<TestConnection> pooledObject) throws Exception {
        pooledObject.getObject().disConnect();
    }

    /**
     * 判断是否保持连接状态
     */
    @Override
    public boolean validateObject(PooledObject<TestConnection> pooledObject) {
        return pooledObject.getObject().isConnect();
    }

    /**
     * 激活对象
     */
    @Override
    public void activateObject(PooledObject<TestConnection> pooledObject) throws Exception {
        pooledObject.getObject().connect();
    }

    /**
     * 钝化一个对象,也可以理解为反初始化
     */
    @Override
    public void passivateObject(PooledObject<TestConnection> pooledObject) throws Exception {

    }
}
