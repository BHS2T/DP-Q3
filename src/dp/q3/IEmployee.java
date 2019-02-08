package dp.q3;

import java.util.List;

interface IEmployee {

    void add(IEmployee e);

    void remove(IEmployee e);

    List<IEmployee> getSubordinates();

    String getName();

    float getSalaries();

    float getSalary();
    
    String getRole();

}
