package xzy.designPattern.dutychain;

public abstract class Leader {
    private Leader leader;

    public void setNext(Leader leader){
        this.leader = leader;
    }

    public Leader getNext(){
        return leader;
    }
    //具体处理
    public abstract void handleRequest();
}
