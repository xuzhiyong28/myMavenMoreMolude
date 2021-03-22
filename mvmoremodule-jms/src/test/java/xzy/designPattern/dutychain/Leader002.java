package xzy.designPattern.dutychain;

public class Leader002 extends Leader{
    @Override
    public void handleRequest() {
        System.out.println("========Leader002审批===========");
        if (getNext() != null) {
            getNext().handleRequest();
        }
    }
}
