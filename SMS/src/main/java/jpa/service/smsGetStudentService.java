package jpa.service;

public class smsGetStudentService {

    private static StudentService studentservice = null;

    static
    {
        studentservice =  new StudentService();
    }
    public static StudentService  getStudentService()
    {
        return studentservice;
    }
}

