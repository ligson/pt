package org.ligson.alipay.count;

public class MonthFlow {
	private int year;
	private int month;
	private double outMoney;
	private double inMoney;

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	public int getMonth() {
		return month;
	}

	public void setMonth(int month) {
		this.month = month;
	}

	public double getOutMoney() {
		return outMoney;
	}

	public void setOutMoney(double outMoney) {
		this.outMoney = outMoney;
	}

	public double getInMoney() {
		return inMoney;
	}

	public void setInMoney(double inMoney) {
		this.inMoney = inMoney;
	}

	public MonthFlow(int year, int month, double outMoney, double inMoney) {
		super();
		this.year = year;
		this.month = month;
		this.outMoney = outMoney;
		this.inMoney = inMoney;
	}

	@Override
	public String toString() {
		return "MonthFlow [year=" + year + ", month=" + month + ", outMoney=" + outMoney + ", inMoney=" + inMoney + "]";
	}
	

}
