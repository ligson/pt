package cn.bdqn.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.jws.WebMethod;
import javax.jws.WebService;

import cn.bdqn.pojo.TemperatureInfo;
import cn.bdqn.service.WeatherService;

@WebService(serviceName="WeatherService",portName="WeatherServicePort",targetNamespace="http://www.bdqn.cn/ws/weather")//(serviceName="WeatherService",endpointInterface="cn.bdqn.service.WeatherService")
public class WeatherServiceImpl implements WeatherService{
	@WebMethod(operationName="getWeathers")
	public List<TemperatureInfo> getWeathers(String city, List<Date> dates) {
		List<TemperatureInfo> list = new  ArrayList<TemperatureInfo>();
		for(Date date : dates){
			list.add(getTemperature(city,date));
		}
		return list;
	}
	
	@WebMethod(exclude = true)
	public TemperatureInfo getTemperature(String city, Date date){
		//模拟根据城市和日期获取天气信息
		return new TemperatureInfo(city, date, 28,33,31,"晴");
	}
}
