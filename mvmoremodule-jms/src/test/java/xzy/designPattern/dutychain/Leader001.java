package xzy.designPattern.dutychain;

public class Leader001 extends Leader {
    @Override
    public void handleRequest() {
        System.out.println("========Leader001审批===========");
        if (getNext() != null) {
            getNext().handleRequest();
        }
    }
}
