package com.spi;

import java.util.ServiceLoader;

/***
 * JAVA SPI机制
 */
public class Main {
    public static void main(String[] args){
        ServiceLoader<MyInterface> shouts = ServiceLoader.load(MyInterface.class);
        for(MyInterface myInterface : shouts){
            myInterface.shout();
        }
    }
}
