import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public abstract class ArrayFilter<T> {
	private T[] arrays;

	public ArrayFilter(T[] arrays) {
		super();
		this.arrays = arrays;
	}

	public abstract boolean filter(T t1, T t2);

	public T[] getResult(Class<T> clazz) {
		recordIdx(0);
		@SuppressWarnings("unchecked")
		T[] arrays2 = (T[]) Array.newInstance(clazz, arrays.length-list.size()); 
		int count = 0;
		for(int i = 0;i<arrays.length;i++){
			if(!list.contains(i)){
				arrays2[count++] = arrays[i];
			}
		}
		return arrays2;
	}

	private List<Integer> list = new ArrayList<Integer>();

	public void recordIdx(int i) {
		int idx = getIdx(i);
		if (idx != -1) {
			if (idx < arrays.length) {
				recordIdx(idx);
			}
		}
	}

	// 返回要删除的元素的位置
	public int getIdx(int idx) {
		for (int i = idx + 1; i < arrays.length; i++) {
			if (filter(arrays[i], arrays[idx])) {
				list.add(i);
			} else {
				return i;
			}
		}
		return -1;
	}
}
