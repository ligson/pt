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
        System.out.println(a.hashCode());
        a = new A();
        a.name = "222";
        System.out.println(a.hashCode());
    }
    public static void main(String[] args) {
        A a = new A();
        System.out.println(a.hashCode());
        a.name = "111111";
        test(a);
        System.out.println(a.hashCode());
        System.out.println(a.name);
    }
}
