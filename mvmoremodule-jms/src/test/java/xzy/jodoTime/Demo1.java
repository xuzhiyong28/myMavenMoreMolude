package xzy.jodoTime;

import org.joda.time.*;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.junit.Test;

import java.util.Date;
import java.util.Locale;

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

        DateTime dateTime4 = DateTime.parse("2012-12-03");
        DateTime dateTime4Temp = dateTime4.plusDays(1).plusYears(1).plusMonths(1).plusWeeks(1).plusMillis(1).plusHours(1).plusSeconds(1);
        System.out.println(dateTime4Temp.toString("yyyy-MM-dd hh:mm:ss"));
    }

    @Test
    public void test4(){
        //返回Property的方法：Property是DateTime中的属性，保存了一些有用的信息
        DateTime dateTime = new DateTime(new Date());
        //获取日期的月份，天
        System.out.println(dateTime.monthOfYear().getAsText());
        System.out.println(dateTime.monthOfYear().getAsString());
        System.out.println(dateTime.dayOfMonth().getAsText());
        System.out.println(dateTime.dayOfMonth().getAsString());
        System.out.println(dateTime.dayOfWeek().getAsText());
        System.out.println(dateTime.dayOfWeek().getAsString());
    }

    @Test
    public void test5(){
        //有时我们需要对一个DateTime的某些属性进行置0操作。比如，我想得到当天的0点时刻。那么就需要用到Property中round开头的方法（roundFloorCopy）
        DateTime dateTime = new DateTime(new Date());
        System.out.println(dateTime.dayOfWeek().roundCeilingCopy()); // 得到今天的0点时刻
    }

    @Test
    public void test6(){
        //格式化日期
        DateTime dateTime = new DateTime(new Date());
        System.out.println(dateTime.toString("yyyy-MM-dd hh:mm:ss"));
        System.out.println(dateTime.toString("EEEE dd MMMM, yyyy HH:mm:ssa"));
        System.out.println(dateTime.toString("MM/dd/yyyy HH:mm ZZZZ"));
        System.out.println(dateTime.toString("MM/dd/yyyy HH:mm Z"));

        DateTime dateTime1 = new DateTime(1456473917004L);
        System.out.println(dateTime1.toString("yyyy-MM-dd hh:mm:ss"));
        System.out.println(dateTime1.toString("yyyy-MM-dd hh:mm:ss", Locale.CHINESE));

        //根据指定格式将字符串转成DateTime对象
        DateTime dt2 = DateTimeFormat.forPattern( "yyyy-MM-dd HH:mm:ss" ).parseDateTime( "2012-12-26 03:27:39" );
    }

    @Test
    public void test7(){
        //获取两个时间的天数
        LocalDate localDate = new LocalDate(new Date());
        LocalDate localDate1 = new LocalDate(2017,10,01);

        int days = Days.daysBetween(localDate1,localDate).getDays();
        System.out.println("days =" + days);

        int months = Months.monthsBetween(localDate1,localDate).getMonths();
        System.out.println("months =" + months);

        int years = Years.yearsBetween(localDate1,localDate).getYears();
        System.out.println("years = " + years);
    }
}
