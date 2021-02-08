package xzy.designPattern.decorate;

/***
 * 接口
 */
public interface Cache {
    String getId();
    int getSize();
    void putObject(Object key, Object value);
    Object getObject(Object key);
    Object removeObject(Object key);
    void clear();
}
