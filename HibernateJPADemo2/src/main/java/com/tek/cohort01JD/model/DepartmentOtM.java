package com.tek.cohort01JD.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table
public class DepartmentOtM implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int deptid;
    private String deptname;

    @OneToMany(targetEntity = TeacherOtM.class, orphanRemoval = true, cascade = CascadeType.ALL)
    private List<TeacherOtM> teacherList;

    public DepartmentOtM(int deptid, String deptname) {
        super();
        this.deptid = deptid;
        this.deptname = deptname;
    }

    public DepartmentOtM() {    }

    public int getDeptid() { return deptid; }

    public void setDeptid(int deptid) {
        this.deptid = deptid;
    }

    public String getDeptname() {
        return deptname;
    }

    public void setDeptname(String deptname) {
        this.deptname = deptname;
    }

    public List<TeacherOtM> getTeacherList() {
        return teacherList;
    }

    public void setTeacherList(List<TeacherOtM> teacherList) {
        this.teacherList = teacherList;
    }

}
