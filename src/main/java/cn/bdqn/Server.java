package cn.bdqn;

import javax.xml.ws.Endpoint;

import cn.bdqn.service.impl.WeatherServiceImpl;

public class Server {
	//发布服务
    protected Server() throws Exception {
        System.out.println("启动服务");
        WeatherServiceImpl weatherServiceImpl = new WeatherServiceImpl();
        String address = "http://localhost:8084/spring09/cn.bdqn.service.WeatherService";
        Endpoint.publish(address, weatherServiceImpl);
    }

    public static void main(String args[]) throws Exception {
        new Server();
        System.out.println("服务准备就绪...");
        Thread.sleep(2 * 60 * 1000);
        System.out.println("服务退出...");
        System.exit(0);
    }
}
