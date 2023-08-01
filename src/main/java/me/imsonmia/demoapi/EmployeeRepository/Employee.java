package me.imsonmia.demoapi.EmployeeRepository;

import java.util.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

@Entity
public class Employee {
    public enum Level {
        BOARD,
        UPPER,
        MIDDLE,
        EMPLOYEE,
        TEMP,
    }

    public enum Role {
        MAINTENANCE,
        DEVELOPMENT,
        FINANCES,
        LOGISTICS,
        MANAGEMENT,
    }

    private @Id @GeneratedValue Long id;
    private String name;
    private int monthlyPayrollCents;
    private int age;
    private Date joinDate;
    private Level empLevel;
    private Role empRole;

    Employee() {
    };

    Employee(String name, int monthlyPayrollCents, int age, Date joinDate, Level empLevel, Role empRole) {
        this.name = name;
        this.monthlyPayrollCents = monthlyPayrollCents;
        this.age = age;
        this.joinDate = joinDate;
        this.empLevel = empLevel;
        this.empRole = empRole;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getMonthlyPayrollCents() {
        return monthlyPayrollCents;
    }

    public int getAge() {
        return age;
    }

    public Date getJoinDate() {
        return joinDate;
    }

    public Level getEmpLevel() {
        return empLevel;
    }

    public Role getEmpRole() {
        return empRole;
    }

    public void setMonthlyPayrollCents(int monthlyPayrollCents) {
        this.monthlyPayrollCents = monthlyPayrollCents;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setJoinDate(Date joinDate) {
        this.joinDate = joinDate;
    }

    public void setEmpLevel(Level empLevel) {
        this.empLevel = empLevel;
    }

    public void setEmpRole(Role empRole) {
        this.empRole = empRole;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
