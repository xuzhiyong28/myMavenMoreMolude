package xzy.designPattern.dutychain;

import org.junit.Test;

public class DutyChainTest {

    @Test
    public void test0(){
        Leader001 leader001 = new Leader001();
        Leader002 leader002 = new Leader002();
        Leader003 leader003 = new Leader003();
        leader001.setNext(leader002);
        leader002.setNext(leader003);
        leader001.handleRequest();
    }

}
