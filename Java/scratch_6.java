class Overloading{

    public static void display (short num)
    {
        System.out.println("short: "+num);
    }

    public static void display(int num) {
        System.out.println("int: " + num);
    }

    public static void display(long num) {
        System.out.println("long: " + num);
    }

    public static void display(Integer num) {
        System.out.println("Integer: " + num);
    }
    public static void display(double num) {
        System.out.println("double: " + num);
    }

    public static void display(int... nums) {
        System.out.print("varargs: ");
        for (int num : nums) {
            System.out.print(num + " ");
        }
        System.out.println();
    }

    public static void main(String[] args) {
        short a=10;
        int b = 3;
        long c = 8;
        double k=5.5;
        display('r');
        display(k);
        display(a);
        display(6);           // Calls display(int num)
        display(c);        // Calls display(long num)
        display(new Integer(50)); // Calls display(Integer num)
        display(1, 2, 3, 4);  // Calls display(int... nums)
        display(b,8);  // Calls display(int... nums)

    }
}