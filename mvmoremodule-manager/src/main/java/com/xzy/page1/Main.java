package com.xzy.page1;/**
 * Created by Administrator on 2018-04-17.
 */

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * @author xuzhiyong
 * @createDate 2018-04-17-18:43
 */
public class Main {
   public static void main(String agrs[]) throws Exception {
       Car car = Main.newInstanceCar();
       System.out.println(car.toString());
   }

   public static Car newInstanceCar() throws Exception{
       //通过类加载器获取到类
       ClassLoader loader = Thread.currentThread().getContextClassLoader();
       Class clazz = loader.loadClass("com.xzy.page1.Car");

       //获取到类的默认构造器并实例化
       Constructor cons = clazz.getDeclaredConstructor((Class[]) null);
       Car car = (Car) cons.newInstance();

       //通过反射机制设置属性
       Method setBrandMethod = clazz.getMethod("setBrand",String.class);
       setBrandMethod.invoke(car,"许志勇");
       Method setColorMethod = clazz.getMethod("setColor",String.class);
       setColorMethod.invoke(car,"颜色");
       Method setMaxSpeedMethod = clazz.getMethod("setMaxSpeed",int.class);
       setMaxSpeedMethod.invoke(car,100);
       return car;
   }
}
