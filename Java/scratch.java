class A {
    String f (B obj) { return ("A et B"); }
    String f(A obj) { return ("A et A"); }
    class B extends A {
        String f(B obj) {return("B et B");}
        String f(A obj) {return("B et A"); }

        static class Test {
            public static void main (String [] args) {
                A al = new A ();
                B b = new B();
                System.out.println(al.f(al));
                System.out.println(al.f(b));
                System.out.println(b.f(al));}}}}