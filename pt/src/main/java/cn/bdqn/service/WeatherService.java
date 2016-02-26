package cn.bdqn.service;

import java.util.Date;
import java.util.List;

import javax.jws.WebMethod;
import javax.jws.WebService;

import cn.bdqn.pojo.TemperatureInfo;

@WebService(serviceName="WeatherService",portName="WeatherServicePort",targetNamespace="http://www.bdqn.cn/ws/weather")
public interface WeatherService {
	@WebMethod
	List<TemperatureInfo> getWeathers(String city,List<Date> dates);
}
