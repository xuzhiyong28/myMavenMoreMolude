package com.IO;/**
 * Created by Administrator on 2018-05-09.
 */

import com.IO.pojo.Box;

import java.io.*;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * @author xuzhiyong
 * @createDate 2018-05-09-19:03
 * ObjectInputStream 和 ObjectOutputStream 是包装流，用来包装其他节点流来处理对基本数据和对象操作支持
 * 例如想直接把一个序列化对象放到文件可以直接用ObjectOutputStream来包装FileOutputStream，用writeObject直接写入对象
 * 只有支持 java.io.Serializable 或 java.io.Externalizable 接口的对象才能被ObjectInputStream/ObjectOutputStream所操作！
 */
public class ObjectStreamTest {
    private static final String TMP_FILE = "D:\\box.tmp";
    public static void main(String agrs[]){
        try {
            testWrite();
            testRead();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void testWrite() throws IOException {
        ObjectOutputStream oos = null;
        try{
            oos = new ObjectOutputStream(new FileOutputStream(TMP_FILE));
            oos.writeBoolean(true);
            oos.writeByte((byte)65);
            oos.writeChar('a');
            oos.writeInt(20131015);
            oos.writeFloat(3.14F);
            oos.writeDouble(1.414D);
            // 写入HashMap对象
            HashMap map = new HashMap();
            map.put("one", "red");
            map.put("two", "green");
            map.put("three", "blue");
            oos.writeObject(map);
            // 写入自定义的Box对象，Box实现了Serializable接口
            Box box = new Box("desk", 80, 48);
            oos.writeObject(box);
        }catch (Exception e){
            if(oos != null) oos.close();
        }
    }


    public static void testRead(){
        try{
            ObjectInputStream in = new ObjectInputStream(new FileInputStream(TMP_FILE));
            System.out.printf("boolean:%b\n" , in.readBoolean());
            System.out.printf("byte:%d\n" , (in.readByte()&0xff));
            System.out.printf("char:%c\n" , in.readChar());
            System.out.printf("int:%d\n" , in.readInt());
            System.out.printf("float:%f\n" , in.readFloat());
            System.out.printf("double:%f\n" , in.readDouble());
            // 读取HashMap对象
            HashMap map = (HashMap) in.readObject();
            Iterator iter = map.entrySet().iterator();
            while (iter.hasNext()) {
                Map.Entry entry = (Map.Entry)iter.next();
                System.out.printf("%-6s -- %s\n" , entry.getKey(), entry.getValue());
            }
            // 读取Box对象，Box实现了Serializable接口
            Box box = (Box) in.readObject();
            System.out.println("box: " + box);
            in.close();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

}
