package jpa.service;

public class smsGetCourseService {

    private static CourseService courseservice = null;

    static
    {
        courseservice =  new CourseService();
    }
    public static CourseService  getCourseService()
    {
        return courseservice;
    }
}

