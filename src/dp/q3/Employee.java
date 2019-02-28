package dp.q3;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Employee {

    String name;
    float salary;
    String role;
    String boss;
    List<Employee> subordinate;

    public Employee(String name, float salary, String role, String boss) {
        this.name = name;
        this.salary = salary;
        this.role = role;
        this.boss = boss;
        subordinate = new ArrayList<>();
    }

    public static void add(Employee e) {
        try {
            write("employeeList.txt", e.getName() + ":" + e.getRole() + ":" + e.getSalary() + ":" + e.getBoss());

        } catch (IOException ex) {
            System.out.println("Can't add Employee");
        }
    }

    public List<Employee> getSubordinates(String role) {
        ArrayList<String> readT = new ArrayList<String>();
        try {
            readT = read("employeeList.txt");
        } catch (IOException ex) {
            System.out.println("Can't add Employee");

        }
        for (String n : readT) {
            String[] str_array = n.split(":");
            System.out.println("--->" + str_array[0]);

            if (role.equalsIgnoreCase(str_array[3])) {
                System.out.println("-----hereeeeeeeeeeeeee=>>---" + str_array[0] + " " + str_array[1] + " " + str_array[2] + " " + str_array[3]);
                Employee newEmp = new Employee(str_array[0], Float.parseFloat(str_array[2]), str_array[1], str_array[3]);
                subordinate.add(newEmp);

            }
        }
        System.out.println(subordinate.size());
        return subordinate;
    }

    public String getName() {
        return name;
    }

    public static Employee getEmpByRole(String role) {

        Employee rE = new Employee("Samri", 30000, "CEO", "ME");;
        ArrayList<String> readT = new ArrayList<String>();
        try {
            readT = read("employeeList.txt");
        } catch (IOException ex) {
            System.out.println("Can't read Employee");

        }
        for (String n : readT) {
            String[] str_array = n.split(":");
            System.out.println("--x->" + str_array[3]);
            System.out.println("--y->" + role);

            if (role.equalsIgnoreCase(str_array[3])) {
                
            System.out.println("here");

               rE = new Employee(str_array[0], Float.parseFloat(str_array[2]), str_array[1], str_array[3]);

            }
        }
        return rE;
    }

    public float getSalaries(String role) {
Employee emp = getEmpByRole(role);
        float totalSalary = emp.salary;
        System.out.println("AAAAAAA      "+emp.salary);
        ArrayList<String> readT = new ArrayList<String>();
        try {
            readT = read("employeeList.txt");
        } catch (IOException ex) {
            System.out.println("Can't add Employee");

        }
        for (String n : readT) {
            String[] str_array = n.split(":");
            System.out.println("--->" + str_array[0]);

            if (role.equalsIgnoreCase(str_array[3])) {
                totalSalary+= Float.parseFloat(str_array[2]);

            }
        }
        return totalSalary;
    }

    public float getSalary() {
        return salary;
    }

    public String getRole() {
        return role;
    }

    public String getBoss() {
        return boss;
    }

    public static ArrayList<String> read(String file) throws IOException {

        try {
            List<String> lines = Collections.emptyList();
            lines = Files.readAllLines(Paths.get(file), StandardCharsets.UTF_8);
        } catch (IOException e) {
            e.printStackTrace();
        }

        BufferedReader bufReader = new BufferedReader(new FileReader(file));
        ArrayList<String> listOfLines = new ArrayList<>();
        String line = bufReader.readLine();
        while (line != null) {
            listOfLines.add(line);
            line = bufReader.readLine();
        }
        bufReader.close();
        return listOfLines;
    }

    public static void write(String filename, String str) throws IOException {
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(filename, true));
            writer.append(str);
            writer.append(System.lineSeparator());
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
