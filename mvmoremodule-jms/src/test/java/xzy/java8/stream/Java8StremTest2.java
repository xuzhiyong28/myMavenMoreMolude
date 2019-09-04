package xzy.java8.stream;

import com.google.common.collect.Lists;
import org.junit.Test;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

public class Java8StremTest2 {

    private static  List<Transaction> transactions = Lists.newArrayList();

    static {
        Trader raoul = new Trader("Raoul", "Cambridge");
        Trader mario = new Trader("Mario", "Milan");
        Trader alan = new Trader("Alan", "Cambridge");
        Trader brian = new Trader("Brian", "Cambridge");
        transactions = Lists.newArrayList(
                new Transaction(brian, 2011, 300),
                new Transaction(raoul, 2012, 1000),
                new Transaction(raoul, 2011, 400),
                new Transaction(mario, 2012, 710),
                new Transaction(mario, 2012, 700),
                new Transaction(alan, 2012, 950)
        );
    }

    @Test
    public void test1(){
        //找出2011年的所有交易并按交易额排序
        List<Transaction> list = transactions.stream()
                .filter(transaction -> transaction.getYear() == 2011)
                .sorted(Comparator.comparing(Transaction::getValue))
                .collect(Collectors.toList());
        System.out.println(list);
    }

    @Test
    public void test2(){
        //交易员都在哪些不同的城市工作过
        List<String> citis = transactions.stream()
                .map(transaction -> transaction.getTrader().getCity())
                .distinct()
                .collect(Collectors.toList());
        System.out.println(citis);

        //或者使用collect(toSet)也可以实现去重
        Set<String> sets = transactions.stream()
                .map(transaction -> transaction.getTrader().getCity())
                .collect(Collectors.toSet());
        System.out.println(sets);
    }

    @Test
    public void test3(){
        //返回所有交易员的姓名字符串，按字母顺序排序
        String nameStr = transactions.stream()
                .map(transaction -> transaction.getTrader().getName())
                .distinct()
                .reduce("",(s, s2) -> s + s2 + ","); //这种 + 在java里面性能太差，字符串多会有性能问题，换成下面的方式
        System.out.println(nameStr);


        String nameStr2 = transactions.stream()
                .map(transaction -> transaction.getTrader().getName())
                .distinct()
                .sorted()
                .collect(Collectors.joining());
        System.out.println(nameStr2);

    }

    @Test
    public void test4(){
        //有没有交易员是在米兰工作的
        System.out.println(transactions.stream().anyMatch(transaction -> transaction.getTrader().getCity().equals("Milan")));
    }

    @Test
    public void test5(){
        //打印生活在剑桥的交易员的所有交易额
        transactions.stream()
                .filter(transaction -> transaction.getTrader().getCity().equals("Cambridge"))
                .map(Transaction::getValue)
                .forEach(System.out::println);

        //所有交易中，最高的交易额是多少
        Optional<Integer> highestValue = transactions.stream()
                .map(Transaction::getValue)
                .reduce(Integer::max);
        System.out.println(highestValue.get());
    }

    @Test
    public void test6(){
        //// IntStream 、 DoubleStream 和LongStream ，分别将流中的元素特化为 int 、 long 和 double ，从而避免了暗含的装箱成本

    }


}
