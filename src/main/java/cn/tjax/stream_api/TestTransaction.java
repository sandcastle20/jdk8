package cn.tjax.stream_api;

import org.apache.logging.log4j.util.PropertySource;
import org.junit.Before;
import org.junit.Test;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @ClassName TestTransaction
 * @Description TODO
 * @Author 洛上云雾
 * @Date 2020/6/28 16:49
 * @Version 1.0
 */
public class TestTransaction {

    List<Transaction> transactions = null;

    @Before
    public void before(){
        Trader raoul = new Trader("Raoul","Cambridge");
        Trader mario = new Trader("Mario","Milan");
        Trader alan = new Trader("Alan","Cambridge");
        Trader brain = new Trader("Brain","Cambridge");

        transactions = Arrays.asList(
                new Transaction(brain,2011,300),
                new Transaction(raoul,2012,1000),
                new Transaction(raoul,2011,400),
                new Transaction(mario ,2012,710),
                new Transaction(mario ,2012,700),
                new Transaction(alan ,2012,950)
        );
    }

    //1.找出2011年发生的所有交易，并且按交易额排序（从低到高）
    @Test
    public void test01(){
        transactions.stream()
                .filter(transaction -> transaction.getYear()==2011)
                .sorted(Comparator.comparingInt(Transaction::getValue))
                .forEach(System.out::println);
        //Transaction(trader=Trader(name=Brain, city=Cambridge), year=2011, value=300)
        //Transaction(trader=Trader(name=Raoul, city=Cambridge), year=2011, value=400)
    }
    //2.交易员都在哪些不同的城市工作过？
    @Test
    public void test02(){
        transactions.stream()
                .map(trader -> trader.getTrader().getCity())
                .distinct()
                .forEach(System.out::println);//Cambridge Milan

    }
    //3.查找所有来自剑桥的交易员，并按姓名进行排序
    @Test
    public void test03(){
        transactions.stream()
                .filter((t) -> t.getTrader().getCity().equals("Cambridge"))
                .map(Transaction::getTrader)
                .sorted(Comparator.comparing(Trader::getName))
                .distinct()
                .forEach(System.out::println);
        //Trader(name=Alan, city=Cambridge)
        //Trader(name=Brain, city=Cambridge)
        //Trader(name=Raoul, city=Cambridge)
    }
    //4.返回所有交易员的名字字符串，按字母顺序排序
    @Test
    public void test04(){
        String str = transactions.stream()
                .map(transaction -> transaction.getTrader().getName())
                .sorted()
                .distinct()
                .collect(Collectors.joining(","));
        System.out.println(str);//Alan,Brain,Mario,Raoul

        System.out.println("---");
        String str2 = transactions.stream()
                .map(transaction -> transaction.getTrader().getName())
                .sorted()
                .reduce("", String::concat);
        System.out.println(str2);//AlanBrainMarioMarioRaoulRaoul

        System.out.println("---");
        transactions.stream()
                .map(transaction -> transaction.getTrader().getName())
                .flatMap(TestTransaction::filterCharacter)
                .sorted((s1,s2) -> s1.compareToIgnoreCase(s2))
                .forEach(System.out::print);//aaaaaAaBiiilllMMnnoooorRRrruu



    }

    public static Stream<String> filterCharacter(String str){
        ArrayList<String> list = new ArrayList<>();
        for (Character ch : str.toCharArray()) {
            list.add(ch.toString());
        }
        return list.stream();
    }
    //5.有没有交易员是在米兰工作的？
    @Test
    public void test05(){
        boolean bl = transactions.stream()
                .anyMatch((t) -> t.getTrader().getCity().equals("Milan"));
        System.out.println(bl);//true
    }
    //6.打印生活在剑桥的交易员的所有交易额
    @Test
    public void test06(){
        Optional<Integer> sum = transactions.stream()
                .filter((e) -> e.getTrader().getCity().equals("Cambridge"))
                .map(Transaction::getValue)
                .reduce(Integer::sum);
        System.out.println(sum.get());//2650
    }
    //7.所有交易中，最高的交易额是多少
    @Test
    public void test07(){
        Optional<Integer> max = transactions.stream()
                .map(Transaction::getValue)
                .max(Integer::compare);
        System.out.println(max.get());//1000


    }
    //8.找到交易额最小的交易
    @Test
    public void test08(){
        Optional<Transaction> min = transactions.stream()
                .min(Comparator.comparingInt(Transaction::getValue));

        System.out.println(min.get());//Transaction(trader=Trader(name=Brain, city=Cambridge), year=2011, value=300)
    }

}
