package thread;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Stream;

import org.apache.commons.collections.map.HashedMap;

public class LamdaTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		test2();
		
		System.out.println("=======================================");
		
		//람다식
		Func add = (int a, int b) -> test3(a,b);
		Func sub =(int a, int b) -> a/b;
		System.out.println("add :: " +add.calc(1, 2));
		System.out.println("sub :: " +sub.calc(4, 2));
		
		System.out.println("=======================================");
		
		//스트림
		Stream<Integer> st = Arrays.asList(1,2,3).stream();
		Stream<Integer> st2 = Arrays.asList(1,2,3).parallelStream();
		
		st.forEach(System.out::println);

		//st.limit(2).forEach(System.out::println);
		//st.skip(2).forEach(System.out::println);
		//st.filter(i -> i>=2).forEach(System.out::println);
		//st.reduce((a,b)-> a-b).get();
		
//		HashMap <String, String>hmap = new HashedMap<String, String>();
//		Arrays.asList(arg0)
		List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6);
		
//		System.out.println(sumAll(numbers, n -> true));
//		System.out.println(sumAll(numbers, n -> n % 2 == 0));
//		System.out.println(sumAll(numbers, n -> n > 3));
		
		//인터페이스를 상속받은 객체를 별도로 만들지 않고 바로 사용가능하다.
		Moveable aa = str -> System.out.println("str11111-->>"+str); 
		aa.move("aaaa");
		
		
		List<Fruit> fruits = Arrays.asList(new Fruit("apple", "red"), new Fruit("melon", "green"), new Fruit("banana", "yellow"));
		List<Fruit> appleList = extractFruitList(fruits, fruit -> "apple".equals(fruit.getName()));
		List<Fruit> redList = extractFruitList(fruits, fruit -> "red".equals(fruit.getColor()));


	}
	
	static List<Fruit> extractFruitList(List<Fruit> fruits, Predicate<Fruit> predicate){
	    List<Fruit> resultList = new ArrayList<>();
	    for(Fruit fruit : fruits){
	        if(predicate.test(fruit)){
	            resultList.add(fruit);
	        }
	    }

	    return resultList;
	}


	/**
	 * 
	 * @param numbers
	 * @param p
	 * @return
	 */
	public static int sumAll(List<Integer> numbers, Predicate<Integer> p) {
	    int total = 0;
	    for (int number : numbers) {
	        if (p.test(number)) {
	            total += number;
	        }
	    }
	    return total;
	}
	public static int test3(int a, int b){
		return (a + b) * 3;
	}
	/**
	 * 기존 스레드
	 */
	public static void test1(){
		new Thread(new Runnable() {
			
			@Override
			public void run() {
				// TODO Auto-generated method stub
				System.out.println("Hello World.");
			}
		}).start();
	}
	/**
	 * 람다식 표현
	 */
	public static void test2(){
		new Thread(()->{
			System.out.println("Hello Lamda.");
		}).start();
	}
}
