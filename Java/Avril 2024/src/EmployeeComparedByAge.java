import java.util.*;
public class EmployeeComparedByAge implements Comparable<EmployeeComparedByAge>
{
    String name;
    int age;
    int salary;
    public int compareTo(EmployeeComparedByAge e) {
        return Integer.compare(age, e.age);
    }
}
class Ex4_compareTo
{
    public static void main(String [] args)
    {
        System.out.println("Using compareTo implementation to compare employee criteria\n");
                ArrayList<EmployeeComparedByAge > a = new ArrayList<EmployeeComparedByAge >();
        EmployeeComparedByAge e1= new EmployeeComparedByAge();
        e1.name="Sami"; e1.salary=2000; e1.age=30;

        EmployeeComparedByAge e2= new EmployeeComparedByAge();
        e2.name="Mohamed"; e2.salary=800; e2.age=32;

        EmployeeComparedByAge e3= new EmployeeComparedByAge();
        e3.name="Youssef"; e3.salary=1400; e3.age=20;

        a.add(e1); a.add(e2); a.add(e3);

        for (EmployeeComparedByAge x : a)
        {
            System.out.println(x.name + " has " + x.salary + " salary and " + x.age +
                    " years olds");
        }
        System.out.println("\n\nSorting a elements...");
        Collections.sort(a);
        for (EmployeeComparedByAge x : a)
        {
            System.out.println(x.name + " has " + x.salary + " salary and " + x.age +
                    " years olds");
        }
    }
}