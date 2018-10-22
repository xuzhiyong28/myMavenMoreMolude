package xzy.guava;

import com.google.common.base.Charsets;
import com.google.common.io.Files;
import com.google.common.io.LineProcessor;
import org.apache.commons.lang3.CharSet;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.List;

public class FilesTest {
    @Test
    public void test1(){
        File file = new File("D://1.txt");
        try {
            //如果没有对应文件会自动创建
            //用覆盖的方式写入
            Files.write("许志勇111".getBytes("UTF-8"),file);
            Files.write("许志勇222".getBytes("UTF-8"),file);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void test2(){
        try {
            byte[] bytes = Files.toByteArray(new File("D://1.txt"));
            String str = new String(bytes,"UTF-8");
            System.out.println(str);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void test3(){
        try {
            //适合于小文件，大文件会有问题，应该使用readLines的另一个处理方法
            List<String> lines = Files.readLines(new File("D://1.txt"), Charset.defaultCharset());
            System.out.print(lines);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void test4(){
        File file = new File("D://1.txt");
        try {
            Files.readLines(file, Charsets.UTF_8, new LineProcessor<Integer>() {
                private int rowNum = 0;
                @Override
                public boolean processLine(String s) throws IOException {
                    rowNum ++ ;
                    System.out.println(s);
                    return true;
                }
                @Override
                public Integer getResult() {
                    return rowNum;
                }
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void test5(){
        File file1 = new File("D://1.txt");
        File file2 = new File("D://2.txt");
        try {
            Files.copy(file1,file2);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void test6(){
        //创建一个临时目录
        File file = Files.createTempDir();
        //C:\Users\xuzy\AppData\Local\Temp\1540174984468-0
        System.out.println(file);
    }

    @Test
    public void test7(){
        try {
            Files.createParentDirs(new File("D://xuzhiyong//1.txt"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void test8(){
        //获取不含扩展名的文件名
        String s = Files.getNameWithoutExtension("D://1.txt");
        System.out.println(s);
    }

    @Test
    public void test9(){
        try {
            //追加字符到文件后面
            Files.append("Hello world", new File("D://1.txt"),Charset.defaultCharset());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
