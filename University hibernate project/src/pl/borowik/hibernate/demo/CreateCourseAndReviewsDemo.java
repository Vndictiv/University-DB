package pl.borowik.hibernate.demo;

import pl.borowik.hibernate.demo.entity.Course;
import pl.borowik.hibernate.demo.entity.Instructor;
import pl.borowik.hibernate.demo.entity.InstructorDetail;
import pl.borowik.hibernate.demo.entity.Review;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class CreateCourseAndReviewsDemo {

    public static void main(String[] args) {

        // create session factory
        SessionFactory factory = new Configuration()
                                    .configure("hibernate.cfg.xml")
                                    .addAnnotatedClass(Instructor.class)
                                    .addAnnotatedClass(InstructorDetail.class)
                                    .addAnnotatedClass(Course.class)
                                    .addAnnotatedClass(Review.class)
                                    .buildSessionFactory();

        // create a session
        Session session = factory.getCurrentSession();

        try {
            // start transaction
            session.beginTransaction();

            //create a course
            Course myCourse = new Course("Pacman - How to score one million points");


            //add some reviews
            myCourse.addReview(new Review("Great course!"));
            myCourse.addReview(new Review("Love it!"));
            myCourse.addReview(new Review("I like this course, it's great!"));

            //save the course ... and leverange the cascade all
            System.out.println("saving course and display: " + myCourse);
            System.out.println(myCourse.getReviews());
            session.save(myCourse);

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