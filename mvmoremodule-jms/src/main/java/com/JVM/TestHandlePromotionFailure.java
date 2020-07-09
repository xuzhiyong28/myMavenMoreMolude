package com.JVM;

public class TestHandlePromotionFailure {
    private static final int _1M = 1024 * 1024;

    /***
     * -Xms20M -Xmx20M -Xmn10M -XX:+PrintGCDetails -XX:+PrintGCDateStamps -XX:+PrintGCTimeStamps -XX:SurvivorRatio=8 -XX:+UseSerialGC
     * -Xms20M -Xmx20M -Xmn10M -XX:+PrintGCDetails -XX:SurvivorRatio=8 -XX:+UseParallelGC
     * -Xms20M -Xmx20M -Xmn10M -XX:+PrintGCDetails -XX:SurvivorRatio=8 -XX:+UseParNewGC -XX:+UseConcMarkSweepGC
     * @param args
     */
    public static void main(String[] args){
        byte[] a1,a2,a3,a4;
        a1 = new byte[2 * _1M];
        a2 = new byte[2 * _1M];
        a3 = new byte[2 * _1M];
        a4 = new byte[4 * _1M];
    }
}
