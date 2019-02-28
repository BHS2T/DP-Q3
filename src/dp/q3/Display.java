package dp.q3;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.List;
import java.util.ServiceLoader;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;

public class Display implements ActionListener, TreeSelectionListener {

    Employee CEO;
    String zBoss;
    JScrollPane jScrollPane;
    JTree jTree;
    String selectedRole;
    JFrame jFrame;
    JLabel salaryLbl, totalSalaryLbl, addEmployeeLbl, messageLbl, nameLbl, roleLbl, salary2Lbl;
    JTextField role, name, salary;
    JButton addEmployeeBtn;
    Employee selectedEmployee;

    public Display() throws IOException {
//        File f = new File("employeeList.txt");
//        if(f.exists()){
//            f.delete();
//        }
        //Initialize 
        CEO = new Employee("Samri", 30000, "CEO", "ME");
//        Employee vpProd = new Employee("Tsiyon", 25000, "Production Head", "CEO");
//        Employee vpMkt = new Employee("Hilinish", 2500, "Marketing Head","CEO");
//        CEO.add(vpProd);
//        CEO.add(vpMkt);
//        Employee slsMgr = new Employee("Abeba", 20000, "Sales","Production Head");
//        vpProd.add(slsMgr);
//        Employee slsAss = new Employee("Zenabu", 15000, "Salses Assistant","Sales");
//        slsMgr.add(slsAss);

    }

    public static void main(String[] args) throws IOException {
        Display display = new Display();
        display.displayTree();
        
        
    }

    public void displayTree() {
        jFrame = new JFrame();
        jFrame.setTitle("Employees");
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        DefaultMutableTreeNode top = new DefaultMutableTreeNode(CEO.role);
        
        createNodes(top, CEO);
        jTree = new JTree(top);
        jScrollPane = new JScrollPane(jTree);
        jScrollPane.setBounds(20, 30, 310, 340);
        jFrame.add(jScrollPane);

        //Label
        salaryLbl = new JLabel();
        salaryLbl.setText("Salary: ");
        salaryLbl.setFont(new java.awt.Font("Tahoma", 1, 24));
        salaryLbl.setBounds(30, 370, 120, 40);
        jFrame.add(salaryLbl);

        //Label
        addEmployeeLbl = new JLabel();
        addEmployeeLbl.setText("Add Employee: ");
        addEmployeeLbl.setFont(new java.awt.Font("Tahoma", 1, 24));
        addEmployeeLbl.setBounds(350, 20, 190, 40);
        jFrame.add(addEmployeeLbl);

        //Label
        totalSalaryLbl = new JLabel();
        totalSalaryLbl.setText("ET.");
        totalSalaryLbl.setFont(new java.awt.Font("Tahoma", 1, 24));
        totalSalaryLbl.setBounds(150, 370, 300, 40);
        jFrame.add(totalSalaryLbl);

        //Field labels
        roleLbl = new JLabel("Role: ");
        nameLbl = new JLabel("Name: ");
        salary2Lbl = new JLabel("Salary: ");

        roleLbl.setBounds(350, 70, 70, 30);
        nameLbl.setBounds(350, 120, 70, 30);
        salary2Lbl.setBounds(350, 170, 70, 30);

        jFrame.add(roleLbl);
        jFrame.add(nameLbl);
        jFrame.add(salary2Lbl);

        //TextFields
        role = new JTextField();
        role.setBounds(400, 70, 190, 30);
        jFrame.add(role);

        name = new JTextField();
        name.setBounds(400, 120, 190, 30);
        jFrame.add(name);

        salary = new JTextField();
        salary.setBounds(400, 170, 190, 30);
        jFrame.add(salary);

        //Buy Button
        addEmployeeBtn = new JButton("Add Employee");
        addEmployeeBtn.setBounds(390, 220, 130, 35);
        jFrame.add(addEmployeeBtn);

        //Message label
        messageLbl = new JLabel();
        messageLbl.setText("M: ");
        messageLbl.setFont(new java.awt.Font("Tahoma", 0, 18));
        messageLbl.setBounds(350, 265, 190, 40);
        jFrame.add(messageLbl);

        jFrame.setLayout(null);
        jFrame.setSize(650, 500);
        jFrame.setVisible(true);

        //Listeners
        jTree.addTreeSelectionListener(this);
        addEmployeeBtn.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == addEmployeeBtn) {
            String roleS,nameS,bossS;
            float salaryS;
            roleS =role.getText();
            nameS = name.getText();
            
            salaryS = Float.parseFloat(salary.getText());
                    
            Employee emp = new Employee(nameS,salaryS,roleS,zBoss);
            Employee.add(emp);
            
            try {
                Display prov = Display.getDefault();
                //jTree.repaint();
//        jFrame.remove(jScrollPane);
                //DefaultMutableTreeNode top = new DefaultMutableTreeNode(CEO.role);
            } catch (IOException ex) {
                Logger.getLogger(Display.class.getName()).log(Level.SEVERE, null, ex);
            }
        
        //createNodes(top, CEO);
        //jTree = new JTree(top);
        //jScrollPane = new JScrollPane(jTree);
        //jScrollPane.setBounds(20, 30, 310, 340);
        //jFrame.add(jScrollPane);

            totalSalaryLbl.setText("button clicked");
            
        }
    }

    private void createNodes(DefaultMutableTreeNode top, Employee empl) {
        List<Employee> subordinates = empl.getSubordinates(empl.role);
        for (Employee emp : subordinates) {
            DefaultMutableTreeNode dmtn = new DefaultMutableTreeNode(emp.role);
            top.add(dmtn);
           // subordinates = emp.getSubordinates(empl.role);
            createNodes(dmtn, emp);
        }

    }

    @Override
    public void valueChanged(TreeSelectionEvent tse) {
        DefaultMutableTreeNode node = (DefaultMutableTreeNode) jTree.getLastSelectedPathComponent();
        if (node == null) {
            return;
        } else {
            Object nodeInfo = node.getUserObject();
            System.out.println("Role: " + nodeInfo);
            Employee e = Employee.getEmpByRole(node.toString().trim());
            float sal = e.getSalaries(e.role);
            System.out.println();
                   totalSalaryLbl.setText(Float.toString(sal));
            zBoss = node.toString();
        }
//        totalSalaryLbl.setText(node.toString());
 

    }
    public static Display getDefault() throws IOException{
        ServiceLoader<Display> sl = ServiceLoader.load(Display.class);
        Display disp = new Display();
        disp.displayTree();
        
        sl.reload();
        for(Display dis:sl){
            disp = dis;
        }
        return disp;
    }

}
