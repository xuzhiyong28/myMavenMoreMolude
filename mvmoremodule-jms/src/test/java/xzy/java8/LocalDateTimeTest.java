package xzy.java8;

import org.junit.Test;

import java.time.LocalDateTime;
import java.time.Month;
import java.time.format.DateTimeFormatter;


public class LocalDateTimeTest {

    @Test
    public void Test001() {
        LocalDateTime rightNow = LocalDateTime.now();
        System.out.println("档前时刻 ：" + rightNow);
        System.out.println("年份 ：" + rightNow.getYear());
        System.out.println("月份 ：" + rightNow.getMonth());
        System.out.println("日 ：" + rightNow.getDayOfMonth());
        System.out.println("时 ：" + rightNow.getHour());
        System.out.println("分 ：" + rightNow.getMinute());
        System.out.println("秒 ：" + rightNow.getSecond());
    }

    @Test
    public void Test002() {
        LocalDateTime beforeData = LocalDateTime.of(2019, Month.APRIL, 12, 9, 21, 32);
        System.out.println(beforeData);
    }

    @Test
    public void Test003(){
        LocalDateTime rightNow = LocalDateTime.now();
        String result1 = rightNow.format(DateTimeFormatter.ISO_DATE);
        String result2 = rightNow.format(DateTimeFormatter.BASIC_ISO_DATE);
        String result3 = rightNow.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        System.out.println(result1);
        System.out.println(result2);
        System.out.println(result3);
    }
}
