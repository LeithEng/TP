import java.io.*;
import java.util.Collection;

public class Employee implements Serializable {
    private String firstname;
    private String lastname;
    private int age;
    public Employee(String firstname,String lastname, int age)
    {
        this.age=age;
        this.firstname=firstname;
        this.lastname=lastname;
    }
    public void printEmp()
    {
        System.out.println("First name: "+firstname);
        System.out.println("Last name: "+lastname);
        System.out.println("Age: "+age);
        System.out.println("-------------");
    }

    public int getAge() {
        return age;
    }

    public String getFirstname() {
        return firstname;
    }

    public String getLastname() {
        return lastname;
    }


}
class SaveEmployees{
    public static void main(String[] args) {
        Employee e = new Employee("Leith","Engazzou",20);
        try{ File f = new File("src/employees.ser");
            FileOutputStream of = new FileOutputStream(f);
            ObjectOutputStream objf = new ObjectOutputStream(of);
            objf.writeObject(e);
            objf.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}