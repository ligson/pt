package pt.reflect.util;

public class CurrentMethod {
	public static void run(){
		StackTraceElement[] st = Thread.currentThread().getStackTrace();
		for(StackTraceElement element:st){
			System.out.println(element.getClassName()+":"+element.getMethodName());
		}
	}
	public static void run(String arg){
		StackTraceElement[] st = Thread.currentThread().getStackTrace();
		for(StackTraceElement element:st){
			System.out.println(element.getClassName()+":"+element.getMethodName());
		}
	}
	public static void main(String[] args) {
		run();
		run("arg");
	}
}
