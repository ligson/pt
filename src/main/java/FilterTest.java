import java.util.Arrays;

public class FilterTest extends ArrayFilter<Integer> {
	public FilterTest(Integer[] arrays) {
		super(arrays);
	}

	public static Integer[] arrays = new Integer[] { 11, 7, 12, 67, 14, 89, 89, 1 };

	public static void main(String[] args) {
		FilterTest filterTest = new FilterTest(arrays);
		System.out.println(Arrays.toString(filterTest.getResult(Integer.class)));
	}

	@Override
	public boolean filter(Integer t1, Integer t2) {
		return Math.abs(t1 - t2) > 10;
	}
}
