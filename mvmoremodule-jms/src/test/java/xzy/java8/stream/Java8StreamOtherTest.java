package xzy.java8.stream;

import org.junit.Test;

import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

/**
 * @author xuzhiyong
 * @createDate 2019-09-04-21:20
 */
public class Java8StreamOtherTest {
    @Test
    public void test(){
        //文件流
        //Files.lines ，它会返回一个由指定文件中的各行构成的字符串流
        try(
                Stream<String> lines = Files.lines(Paths.get("data.txt"), Charset.defaultCharset())
        ){
                lines.forEach(System.out::println);
        }catch (Exception e){
                e.printStackTrace();
        }
    }
}
