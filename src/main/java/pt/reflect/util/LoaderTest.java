package pt.reflect.util;

import java.lang.reflect.Field;
import java.util.Vector;

public class LoaderTest {
    public static void main(String[] args) throws Exception{
        Field f=ClassLoader.class.getDeclaredField("classes");
        f.setAccessible(true);
        Vector classes=(Vector)f.get(ClassLoader.getSystemClassLoader());
        System.out.println(classes.size());
    }
}
