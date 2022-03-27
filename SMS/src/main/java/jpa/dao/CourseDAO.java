package jpa.dao;

import jpa.entitymodels.Course;

import java.util.List;
import java.util.Set;

public interface CourseDAO {
    List<Course> getAllCourses();
}
