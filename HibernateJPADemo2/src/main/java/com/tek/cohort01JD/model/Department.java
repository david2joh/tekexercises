package com.tek.cohort01JD.model;
import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table
public class Department implements Serializable  {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue( strategy=GenerationType.IDENTITY )
    private int deptid;
    private String deptname;

    public Department(int deptid, String deptname) {
        super();
        this.deptid = deptid;
        this.deptname = deptname;
    }
    public Department()
    {

    }
    public int getDeptid() { return deptid; }
    public void setDeptid(int deptid) {this.deptid = deptid;}
    public String getDeptname() {return deptname;}
    public void setDeptname(String deptname) {this.deptname = deptname;}
}
