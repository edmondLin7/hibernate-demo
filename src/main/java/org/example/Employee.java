package org.example;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import org.hibernate.query.Query;
import org.hibernate.Session;

import java.util.List;

@Entity
public class Employee {
    @Id
    private int id;
    private String name;
    private String email;

    public Employee(){

    }

    public Employee(int id, String name, String email) {
        this.id = id;
        this.name = name;
        this.email = email;
    }

    public static void addEmployee(Session session, int id, String name, String email) {
        try {
            Employee emp = new Employee(id, name, email);
            session.save(emp);
        } catch (Exception e) {
            System.out.println("Oops! Something went wrong while retrieving the employee");
        }
    }

    public static void updateEmployee(Session session, int id, String newName, String newEmail) {
        try {
            Employee emp = session.get(Employee.class, id); // Retrieve the Employee with ID 1
            emp.setName(newName);
            emp.setEmail(newEmail);
        } catch (Exception e) {
            System.out.println("Oops! Something went wrong while retrieving the employee");
        }
    }

    public static void deleteEmployee(Session session, int id) {
        try {
            Employee emp = session.get(Employee.class, id);
            session.delete(emp);
        } catch (Exception e) {
            System.out.println("Oops! Something went wrong while retrieving the employee");
        }
    }

    public static List<Employee> getEmployees(Session session) {
        try {
            Query<Employee> query = session.createQuery("FROM Employee", Employee.class);
            return query.list();
        } catch (Exception e) {
            System.out.println("Oops! Something went wrong while retrieving the employee");
        }
        return null;
    }

    public static Employee getEmployeeById(Session session, int id) {
        try {
            return session.get(Employee.class, id);
        } catch (Exception e) {
            System.out.println("Oops! Something went wrong while retrieving the employee");
        }
        return null;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}