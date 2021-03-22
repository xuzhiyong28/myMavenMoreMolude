package xzy.designPattern.dutychain;

public class Leader003 extends Leader{
    @Override
    public void handleRequest() {
        System.out.println("========Leader003审批===========");
        if (getNext() != null) {
            getNext().handleRequest();
        }
    }
}
