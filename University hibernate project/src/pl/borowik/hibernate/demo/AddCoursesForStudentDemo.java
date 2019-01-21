package pl.borowik.hibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import pl.borowik.hibernate.demo.entity.*;

public class AddCoursesForStudentDemo {

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
            int theId = 2;
            Student myStudent = session.get(Student.class, theId);
            System.out.println("\nStudent loaded: " + myStudent);
            System.out.println("\nCourses: " + myStudent.getCourses());
            System.out.println(" ");
            //create more courses
            Course myCourse = new Course("How to earn million dolars");
            Course myCourse2 = new Course("How to build a House");

            //add student to courses
            myCourse.addStudent(myStudent);
            myCourse2.addStudent(myStudent);

            //save the courses
            System.out.println("\nSaving the courses... ");
            session.save(myCourse);
            session.save(myCourse2);

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