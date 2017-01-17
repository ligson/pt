package org.ligson.alipay.count;

import java.util.Comparator;

public class MonthFlowCompator implements Comparator<MonthFlow> {

	@Override
	public int compare(MonthFlow o1, MonthFlow o2) {
		if (o1.getYear() != o2.getYear()) {
			return o1.getYear() - o2.getYear();
		} else {
			return o1.getMonth() - o2.getMonth();
		}
	}

}
