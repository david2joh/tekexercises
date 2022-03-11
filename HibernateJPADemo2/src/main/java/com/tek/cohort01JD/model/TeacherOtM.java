package com.tek.cohort01JD.model;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table
public class TeacherOtM implements Serializable  {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue( strategy=GenerationType.IDENTITY )
    private int teacherid;
    private String salary;
    private String Teachername;
/*
    @ManyToOne
    private Department department;
 */
    public TeacherOtM(int teacherid, String salary, String teachername) {
        super();
        this.teacherid = teacherid;
        this.salary = salary;
        Teachername = teachername;
    }
    public TeacherOtM()
    {}
/*
    public Department getDep() {
        return department;
    }
    public void setDep(Department department) {
        this.department = department;
    }
*/
    public int getTeacherid() {
        return teacherid;
    }
    public void setTeacherid(int teacherid) {
        this.teacherid = teacherid;
    }
    public String getSalary() {
        return salary;
    }
    public void setSalary(String salary) {
        this.salary = salary;
    }
    public String getTeachername() {
        return Teachername;
    }
    public void setTeachername(String teachername) {
        Teachername = teachername;
    }
}