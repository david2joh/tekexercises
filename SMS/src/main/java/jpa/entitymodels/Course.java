package jpa.entitymodels;

import java.util.*;

import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor(force = true)  //I heart lombok
@Entity
@Table(name = "course")
public class Course implements Comparable<Course>{
    @Id
    // @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    @NonNull
    private Integer cId;
    @Column(name = "name", length = 50, nullable = false, insertable = true)
    @NonNull
    private String cName;
    @Column(name = "Instructor", length = 50, nullable = false, insertable = true)
    @NonNull
    private String cInstructorName;

    @ToString.Exclude
    @ManyToMany(mappedBy = "sCourses",fetch = FetchType.EAGER)
    private Set<Student> students = new HashSet<>();


    public Course(Integer cId, String cName, String cInstructorName) {
        this.cId = cId;
        this.cName = cName;
        this.cInstructorName = cInstructorName;
    }


    @Override
    public boolean equals(Object o) { //going to say that the courseID really is unique and courseID implies courseName
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Course course = (Course) o;
        return cId.equals(course.getCId()) && cInstructorName.equalsIgnoreCase(course.getCInstructorName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(cId, cName);
    }

    @Override
    public String toString() {
        return "Course{" +
                "id=" + cId +
                ", courseName='" + cName + '\'' +
                ", instructor=" + cInstructorName +
                '}';
    }

    @Override
    public int compareTo(Course that) {
        return this.getCId() - that.getCId();
    }
}
