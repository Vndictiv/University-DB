package pl.borowik.hibernate.demo;

import pl.borowik.hibernate.demo.entity.*;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;


public class GetCoursesForStudentDemo {

    public static void main(String[] args) {

        // create session factory
        SessionFactory factory = new Configuration()
                                    .configure("hibernate.cfg.xml")
                                    .addAnnotatedClass(Instructor.class)
                                    .addAnnotatedClass(InstructorDetail.class)
                                    .addAnnotatedClass(Course.class)
                                    .addAnnotatedClass(Review.class)
                                    .addAnnotatedClass(Student.class)
                                    .buildSessionFactory();

        // create a session
        Session session = factory.getCurrentSession();

        try {
            // start transaction
            session.beginTransaction();

            //get the student from db
            int theId = 3;
            Student myStudent = session.get(Student.class, theId);
            System.out.println("\nStudent loaded: " + myStudent);
            System.out.println("\nCourses: " + myStudent.getCourses());
            System.out.println(" ");


            // commit transaction
            session.getTransaction().commit();

            System.out.println("Success!");
        }
         finally {
            session.close();
            factory.close();
        }
    }

}