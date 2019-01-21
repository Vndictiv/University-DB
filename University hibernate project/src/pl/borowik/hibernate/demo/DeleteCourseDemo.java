package pl.borowik.hibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import pl.borowik.hibernate.demo.entity.*;

public class DeleteCourseDemo {

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

            // get course
            int courseId = 10;
            int courseId2 = 12;
            int courseId3 = 13;
            Course myCourse = session.get(Course.class, courseId);
            Course myCourse2 = session.get(Course.class, courseId2);
            Course myCourse3 = session.get(Course.class, courseId3);

            //delete the course
            System.out.println("deleting course... ");
            session.delete(myCourse);
            session.delete(myCourse2);
            session.delete(myCourse3);

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