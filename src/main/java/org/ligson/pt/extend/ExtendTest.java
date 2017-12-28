package org.ligson.pt.extend;

public class ExtendTest {
    static class A{
        private String name;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }
    static class B extends A{

    }
    public static void test(A a){
        a.name = "33333";
        a = new A();
        a.name = "222";
    }
    public static void main(String[] args) {
        A a = new A();
        a.name = "111111";
        test(a);
        System.out.println(a.name);
    }
}
