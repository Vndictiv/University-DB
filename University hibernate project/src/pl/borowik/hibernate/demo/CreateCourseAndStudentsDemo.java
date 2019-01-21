package pl.borowik.hibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import pl.borowik.hibernate.demo.entity.*;

public class CreateCourseAndStudentsDemo {

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

            //create a course
            Course myCourse = new Course("Java - How to learn Java by yourself");

            //save the course
            System.out.println("\nSaving the course... ");
            session.save(myCourse);
            System.out.println("\nCourse is saved... ");

            //create the students
            Student myStudent = new Student("Kazimierz", "Wielki", "zamek@gmail.com");

            //add students to the course
            myCourse.addStudent(myStudent);

            //save the students
            System.out.println("\nSaving students... ");
            session.save(myStudent);
            System.out.println("\nStudents are saved... " + myCourse.getStudents());


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