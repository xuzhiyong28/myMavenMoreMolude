package xzy.designPattern.decorate;

public class CommonCache implements Cache{
    @Override
    public String getId() {
        return null;
    }

    @Override
    public int getSize() {
        return 0;
    }

    @Override
    public void putObject(Object key, Object value) {
        System.out.println("========普通缓存put方法=========");
    }

    @Override
    public Object getObject(Object key) {
        return null;
    }

    @Override
    public Object removeObject(Object key) {
        return null;
    }

    @Override
    public void clear() {

    }
}
