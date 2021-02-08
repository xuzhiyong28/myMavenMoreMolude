package xzy.designPattern.decorate;

public class LruDecorateCache implements Cache{

    private Cache cache;

    public LruDecorateCache(Cache cache){
        this.cache = cache;
    }


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
        System.out.println("=========做一些修饰==========");
        cache.putObject(key,value);
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
