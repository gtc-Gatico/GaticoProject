import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class TestCountWord {
    public static void main(String[] args) {
//        hello spark
//        hello flink
//        hello atguigu
//        hive hive
//        hello flink
//        hello atguigu
        List<String> wordList = new ArrayList<>();
        wordList.add("hello"); wordList.add("spark");
        wordList.add("hello"); wordList.add("flink");
        wordList.add("hello"); wordList.add("atguigu");
        wordList.add("hive");  wordList.add("hive");
        wordList.add("hello"); wordList.add("flink");
        wordList.add("hello"); wordList.add("atguigu");

        wordList.stream().collect(Collectors.groupingBy(String::toString)).forEach((s, strings) -> {
            System.out.print(s+"\t");
            System.out.println(strings.size());
        });


    }
}
