package com.lwf.juc.c_023singleton;

import org.apache.commons.lang3.SerializationUtils;

import java.io.ObjectStreamException;
import java.io.Serializable;

public class SingletonInnerSERIALIZABLE implements Serializable {
    private SingletonInnerSERIALIZABLE(){
    }
    private static class SingletonHolder{
        private static SingletonInnerSERIALIZABLE instance = new SingletonInnerSERIALIZABLE();
    }
    public static SingletonInnerSERIALIZABLE getInstance(){
        return SingletonHolder.instance;
    }

    //加了这个方法就不会反序列化出新的单例了
    private Object readResolve() throws ObjectStreamException{
        return  SingletonHolder.instance;
    }

    public static void main(String[] args) {
        SingletonInnerSERIALIZABLE instance =SingletonInnerSERIALIZABLE.getInstance();
        byte[] serialize= SerializationUtils.serialize(instance);
        SingletonInnerSERIALIZABLE newInstance=SerializationUtils.deserialize(serialize);
        System.out.println(instance==newInstance);
    }
}
