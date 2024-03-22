class A {
    public String toString() {
        String resultat = "Je suis un A";
        return resultat;
    }
}

class B extends A {
    public String toString() {
        String resultat = super.toString();
        resultat += ", et je suis B";
        return resultat;
    }
}

class Test {
    public static void main(String args[]) {
        A a = new B();
        a.toString();
    }
}
