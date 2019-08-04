package dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import models.Course;

public class CourseDao {

	public Course save(Course course) {
		Course courseToReturn = null;
		if (course.getId() == null) {
			courseToReturn = create(course);
		} else {
			courseToReturn = update(course);
		}
		return courseToReturn;
	}

	public Course create(Course course) {
		Configuration configuration = new Configuration().configure();
		try (SessionFactory sessionFactory = configuration.buildSessionFactory();
				Session session = sessionFactory.openSession()) {
			Transaction transaction = session.beginTransaction();
			Integer id = (Integer) session.save(course);
			transaction.commit();
			course.setId(id);
		} catch (HibernateException e) {
			throw new DaoException("Cannot create course", e);
		}
		return course;
	}

	public Course update(Course course) {
		Configuration configuration = new Configuration().configure();
		try (SessionFactory sessionFactory = configuration.buildSessionFactory();
				Session session = sessionFactory.openSession()) {
			Transaction transaction = session.beginTransaction();
			session.update(course);
			transaction.commit();
		} catch (HibernateException e) {
			throw new DaoException("Cannot update course", e);
		}
		return course;
	}

	public void delete(Course course) {
		Configuration configuration = new Configuration().configure();
		try (SessionFactory sessionFactory = configuration.buildSessionFactory();
				Session session = sessionFactory.openSession()) {
			Transaction transaction = session.beginTransaction();
			session.delete(course);
			transaction.commit();
		} catch (HibernateException e) {
			throw new DaoException("Cannot delete course", e);
		}
	}

	public Course findById(Integer id) {
		Course courseToReturn = null;
		Configuration configuration = new Configuration().configure();
		try (SessionFactory sessionFactory = configuration.buildSessionFactory();
				Session session = sessionFactory.openSession()) {
			Transaction transaction = session.beginTransaction();
			courseToReturn = session.get(Course.class, id);
			transaction.commit();
		} catch (HibernateException e) {
			throw new DaoException("Cannot find course", e);
		}
		return courseToReturn;
	}

	public List<Course> findAll() {
		List<Course> courses = new ArrayList<>();
		Configuration configuration = new Configuration().configure();
		try (SessionFactory sessionFactory = configuration.buildSessionFactory();
				Session session = sessionFactory.openSession()) {
			Transaction transaction = session.beginTransaction();
			courses = session.createQuery("from Course", Course.class).getResultList();
			transaction.commit();
		} catch (HibernateException e) {
			throw new DaoException("Cannot find courses", e);
		}
		return courses;
	}
}
