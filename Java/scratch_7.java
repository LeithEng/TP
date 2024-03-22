class GarbageCollection {

    static class MyClass {
        private int id;

        public MyClass(int id) {
            this.id = id;
        }

        protected void finalize() throws Throwable {
            System.out.println("Object with ID " + id + " is being finalized.");
        }
    }

    public static void main(String[] args) {

        MyClass obj1 = new MyClass(1);
        MyClass obj2 = new MyClass(2);

        obj1 = null;
        obj2 = null;

        System.gc();

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}