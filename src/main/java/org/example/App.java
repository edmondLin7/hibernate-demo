package org.example;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Hello world!
 *
 */

public class App 
{
    public static void main( String[] args ) {
        // create a configuration object
        Configuration config = new Configuration();

        // read the configuration object
        config.configure("hibernate.cfg.xml");

        // create session factory
        SessionFactory sessionFactory = config.buildSessionFactory();

        // open the start
        Session session = sessionFactory.openSession();

        // begin transaction
        Transaction transaction = session.beginTransaction();

        Scanner input = new Scanner(System.in);
        System.out.println("Enter which action you would like to do by choosing a number 1-6");
        System.out.println("1, addEmployee");
        System.out.println("2, updateEmployee");
        System.out.println("3, deleteEmployee");
        System.out.println("4, getAllEmployees");
        System.out.println("5, getEmployeeById");
        System.out.println("6, exit");
        boolean shouldContinue = true;
        int currentAction = input.nextInt();
        String name = "";
        String email = "";
        int id = 0;
        // Possible transaction
        while (shouldContinue) {
            switch (currentAction) {
                case 1:
                    System.out.println("Enter the id of the employee to insert");
                    id = input.nextInt();
                    input.nextLine();
                    System.out.println("Enter the name of the new employee to insert");
                    //input.nextLine();
                    name = input.nextLine();
                    // input.nextLine();
                    System.out.println("Enter the email");
                    email = input.nextLine();
                    Employee.addEmployee(session, id, name, email);
                    transaction.commit();
                    break;
                case 2:
                    System.out.println("Enter the id of the employee to update");
                    id = input.nextInt();
                    input.nextLine();
                    System.out.println("Enter the updated name");
                    name = input.nextLine();
                    System.out.println("Enter the email name");
                    email = input.nextLine();
                    Employee.updateEmployee(session, id, name, email);
                    transaction.commit();
                    break;
                case 3:
                    System.out.println("Enter the id of the employee to delete");
                    id = input.nextInt();
                    Employee.deleteEmployee(session, id);
                    transaction.commit();
                    break;
                case 4:
                    List<Employee> employeeList = Employee.getEmployees(session);
                    transaction.commit();
                    for (Employee emps : employeeList) {
                        System.out.println("Id: " + emps.getId() +
                                " Name: " + emps.getName() +
                                " Employee: " + emps.getEmail());
                    }
                    break;
                case 5:
                    System.out.println("Enter the id of the employee to retrieve");
                    id = input.nextInt();
                    Employee outputEmp = Employee.getEmployeeById(session, id);
                    System.out.println("Id: " + outputEmp.getId() +
                            " Name: " + outputEmp.getName() +
                            " Employee: " + outputEmp.getEmail());
                    break;
                case 6:
                    shouldContinue = false;
                    break;
            }
            System.out.println("Enter which action you would like to do by choosing a number 1-6");
            System.out.println("1, addEmployee");
            System.out.println("2, updateEmployee");
            System.out.println("3, deleteEmployee");
            System.out.println("4, getAllEmployees");
            System.out.println("5, getEmployeeById");
            System.out.println("6, exit");
            currentAction = input.nextInt();
        }
        session.close();
    }
}
