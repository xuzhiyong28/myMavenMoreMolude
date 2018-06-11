package com.mvmoremoduleJms.util;/**
 * Created by Administrator on 2018-04-09.
 */

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 * @author xuzhiyong
 * @createDate 2018-04-09-10:11
 */
public class SerializeUtil {

    public static byte[] serialize(Object object) {
        ObjectOutputStream oos = null;
        ByteArrayOutputStream baos = null;
        try {
            baos = new ByteArrayOutputStream();
            oos = new ObjectOutputStream(baos);
            oos.writeObject(object);
            oos.flush();
            return baos.toByteArray();
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            try{
                if(oos != null) oos.close();
                if(baos != null) baos.close();
            }catch (Exception ex){
                ex.printStackTrace();
            }
        }
        return null;
    }

    public static Object unserialize(byte[] bytes){
        ByteArrayInputStream bais = null;
        ObjectInputStream ois = null;
        try{
            bais = new ByteArrayInputStream(bytes);
            ois = new ObjectInputStream(bais);
            return ois.readObject();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            try{
                if(bais != null) bais.close();
                if(ois != null) ois.close();
            }catch (Exception ex){
                ex.printStackTrace();
            }
        }
        return null;
    }
}
