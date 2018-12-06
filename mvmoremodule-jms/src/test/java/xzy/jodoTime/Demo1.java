package xzy.jodoTime;

import org.joda.time.DateTime;
import org.junit.Test;

import java.util.Date;

public class Demo1 {
    @Test
    public void test1(){
        DateTime dateTime1 = new DateTime();
        System.out.println(dateTime1.toString("yyyy-MM-dd"));

        DateTime dateTime2 = new DateTime(2016,2,14,0,0,0);
        System.out.println(dateTime2);

        DateTime dateTime3 = new DateTime(1456473917004L);
        System.out.println(dateTime3); // out: 2016-02-26T16:05:17.004+08:00

        DateTime dateTime4 = new DateTime(new Date());
        System.out.println(dateTime4);

        DateTime dateTime5 = new DateTime("2016-02-15T00:00:00.000+08:00");
        System.out.println(dateTime5); // out: 2016-02-15T00:00:00.000+08:00
    }

    @Test
    public void test2(){
        //with开头的方法（比如：withYear）：用来设置DateTime实例到某个时间，因为DateTime是不可变对象，所以没有提供setter方法可供使用，with方法也没有改变原有的对象，
        // 而是返回了设置后的一个副本对象
        DateTime dateTime = new DateTime(new Date());
        System.out.println(dateTime);

        DateTime dateTimeDayOfYear =  dateTime.withDayOfYear(35); //转成当前时间所在年份的某一天，例如这里是35 , 就是2018年的第35天对应的日期
        System.out.println(dateTimeDayOfYear);

        DateTime dateTimewithDayOfMonth = dateTime.withDayOfMonth(3); //转成当前时间所在月份的第三天
        System.out.println(dateTimewithDayOfMonth);
    }

    @Test
    public void test3(){
        //plus/minus开头的方法（比如：plusDay, minusMonths）：用来返回在DateTime实例上增加或减少一段时间后的实例。
        DateTime dateTime = new DateTime(new Date());
        DateTime dateTime1 = dateTime.plusDays(20); //今天再加上20天
        System.out.println(dateTime1);

        DateTime dateTime2 = dateTime.plusMonths(2); //今天再加上2个月
        System.out.println(dateTime2);

        DateTime dateTime3 = dateTime.minusDays(20); //今天再减去20天
        System.out.println(dateTime3);
    }


}
