package lambdasinaction.chap3;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.IntSummaryStatistics;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ExecuteAround {

	public static void main(String ...args) throws IOException{

		String collect = Arrays.stream(new String[]{"a","b","c"}).collect(Collectors.joining(","));
		System.out.println(collect);


	}

	public static List<Lambdas.Apple> map(List<Integer> list, Function<Integer, Lambdas.Apple> function) {
		List<Lambdas.Apple> apples = new ArrayList<>();
		for (Integer integer : list) {
			apples.add(function.apply(integer));
		}
		return apples;
	}

    public static String processFileLimited() throws IOException {
        try (BufferedReader br =
                     new BufferedReader(new FileReader("lambdasinaction/chap3/data.txt"))) {
            return br.readLine();
        }
    }


	public static String processFile(BufferedReaderProcessor p) throws IOException {
		try(BufferedReader br = new BufferedReader(new FileReader("lambdasinaction/chap3/data.txt"))){
			return p.process(br);
		}

	}

	public interface BufferedReaderProcessor{
		public String process(BufferedReader b) throws IOException;

	}
}
