package jpa.entitymodels;

import java.util.*;

import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor(force=true)  //I heart lombok
@Entity
@Table(name = "student")
public class Student implements Comparable<Student> {
    @Id
 //   @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "email",length = 50,unique = true,nullable = false)
    @NonNull
    private String sEmail;
    @Column(name = "name",length = 50,nullable = false,insertable = true)
    @NonNull
    private String sName;
    @Column(name = "password",length = 50,nullable = false,insertable = true)
    @NonNull
    private String sPass;

    @ManyToMany(fetch = FetchType.EAGER, cascade=CascadeType.ALL)  //might be able to use just .PERSIST and .MERGE
    @JoinTable(name="STUDENT_COURSES",
            joinColumns={@JoinColumn(referencedColumnName ="email")},
            inverseJoinColumns = {@JoinColumn(referencedColumnName="id")})
    @ToString.Exclude
    private Set<Course> sCourses = new HashSet<>();


    public Student(String sEmail, String sName, String sPass) {
        this.sEmail = sEmail;
        this.sName = sName;
        this.sPass = sPass;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Student student = (Student) o;
        return sEmail.equalsIgnoreCase(student.getSEmail()) && sName.equalsIgnoreCase(student.getSName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(sEmail);  //ignoring hash collisions on email
    }

    @Override
    public String toString() {
        return "Student{" +
                "sEmail='" + sEmail + '\'' +
                ", sName='" + sName + '\'' +
                ", sPass='" + sPass + '\'' +
                '}';
    }

    @Override
    public int compareTo(Student that) {
        if (that == null) return 1;
        return(this.sEmail.compareTo(that.getSEmail()));
    }
}
