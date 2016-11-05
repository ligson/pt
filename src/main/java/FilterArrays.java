import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FilterArrays {
	public static int[] arrays = new int[] { 11, 7, 12, 67, 14, 89, 89, 1};
//1 6 7 8 9 10
	public static void main(String[] args) {
		recordIdx(0);
		System.out.println(list);
		
		int[] arrays2 = new int[arrays.length-list.size()];
		int count = 0;
		for(int i = 0;i<arrays.length;i++){
			if(!list.contains(i)){
				arrays2[count++] = arrays[i];
			}
		}
		System.out.println(Arrays.toString(arrays2));
		
	}

	public static List<Integer> list = new ArrayList<>();

	public static void recordIdx(int i) {
		int idx = getIdx(i);
		if (idx != -1) {
			if (idx < arrays.length) {
				recordIdx(idx);
			}
		}
	}

	// 返回要删除的元素的位置
	public static int getIdx(int idx) {

		for (int i = idx + 1; i < arrays.length; i++) {
			System.out.print("比较:"+arrays[i] +":"+arrays[idx]);
			if (Math.abs(arrays[i] - arrays[idx]) > 2) {
				list.add(i);
				System.out.println(" 未通过");
			}else{
				System.out.println(" 通过");
				return i;
			}
		}
		return -1;
	}
	
	

}
