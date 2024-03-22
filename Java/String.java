class Boxing {
    public static void main(String args[]) {
        System.out.println("StringBuffer:");
        StringBuffer x = new StringBuffer("Java"); // Use constructor
        x = x.append(" Rules!");
        System.out.println("x = " + x); // the output is: Java Rules!

        x = new StringBuffer(x.toString().toLowerCase()); // Use constructor to create a new StringBuffer
        System.out.println("x = " + x); // no assignment, the output is still: x = Java Rules!

        x = new StringBuffer(x.toString().toLowerCase()); // Use constructor and assign to x
        System.out.println("x = " + x); // the assignment causes the output: x = java rules!

        StringBuffer s1 = new StringBuffer("spring "); // Use constructor
        StringBuffer s2 = new StringBuffer(s1.toString().concat("summer ")); // Use constructor
        s1.append("fall ");
        s2.append(s1);
        s1 = new StringBuffer(s1.toString().concat("winter ")); // Use constructor
        System.out.println(s1 + " " + s2);


        System.out.println("StringBuilder:");

        StringBuilder y = new StringBuilder("Java"); // Use constructor
        y = y.append(" Rules!");
        System.out.println("y = " + y); // the output is: Java Rules!

        y = new StringBuilder(x.toString().toLowerCase()); // Use constructor to create a new StringBuilder
        System.out.println("y = " + y); // no assignment, the output is still: x = Java Rules!

        y = new StringBuilder(x.toString().toLowerCase()); // Use constructor and assign to x
        System.out.println("y = " + y); // the assignment causes the output: x = java rules!

        StringBuilder s3 = new StringBuilder("spring "); // Use constructor
        StringBuilder s4 = new StringBuilder(s3.toString().concat("summer ")); // Use constructor
        s3.append("fall ");
        s4.append(s1);
        s3 = new StringBuilder(s1.toString().concat("winter ")); // Use constructor
        System.out.println(s3 + " " + s4);
    }
}