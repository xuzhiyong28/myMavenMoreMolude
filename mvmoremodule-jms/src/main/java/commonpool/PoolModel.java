package commonpool;

public class PoolModel {

    public PoolModel(){}

    public PoolModel(int index, String name) {
        this.index = index;
        this.name = name;
    }

    private int index;
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    @Override
    public String toString() {
        return "PoolModel{" +
                "index=" + index +
                ", name='" + name + '\'' +
                '}';
    }
}
