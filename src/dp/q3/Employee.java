package dp.q3;

import java.util.ArrayList;
import java.util.List;

public class Employee {

    String name;
    float salary;
    String role;
    List<Employee> subordinate;

    public Employee(String name, float salary, String role) {
        this.name = name;
        this.salary = salary;
        this.role = role;
        subordinate = new ArrayList<>();
    }

    
    public void add(Employee e) {
        subordinate.add(e);
    }


    public void remove(Employee e) {
        subordinate.remove(e);
    }

    
    public List<Employee> getSubordinates() {
        return subordinate;
    }

   
    public String getName() {
        return name;
    }
    


    public float getSalaries() {
        float totalSalary = salary;
        totalSalary = subordinate.stream().map((e) -> e.getSalary()).reduce(totalSalary, (accumulator, _item) -> accumulator + _item);
        return totalSalary;
    }

  
    public float getSalary() {
        return salary;
    }

   
    public String getRole() {
        return role;
    }

}
