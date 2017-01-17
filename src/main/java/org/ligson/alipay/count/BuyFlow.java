package org.ligson.alipay.count;

import java.util.Date;

import org.apache.commons.lang3.time.DateFormatUtils;

public class BuyFlow {
	private Date date;
	private double money;
	private String type;
	private double yue;

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public double getMoney() {
		return money;
	}

	public void setMoney(double money) {
		this.money = money;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public double getYue() {
		return yue;
	}

	public void setYue(double yue) {
		this.yue = yue;
	}

	public BuyFlow(Date date, double money, String type, double yue) {
		super();
		this.date = date;
		this.money = money;
		this.type = type;
		this.yue = yue;
	}

	@Override
	public String toString() {
		return "BuyFlow [date=" + DateFormatUtils.format(date,"yyyy-MM-dd HH:mm:ss") + ", money=" + money + ", type=" + type + ", yue=" + yue + "]";
	}

}
