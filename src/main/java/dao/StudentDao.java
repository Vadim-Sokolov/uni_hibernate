package dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import models.Student;

public class StudentDao {

	public Student save(Student student) {
		Student studentToReturn = null;
		if (student.getId() == null) {
			studentToReturn = create(student);
		} else {
			studentToReturn = update(student);
		}
		return studentToReturn;
	}

	public Student create(Student student) {
		Configuration configuration = new Configuration().configure();
		try (SessionFactory sessionFactory = configuration.buildSessionFactory();
				Session session = sessionFactory.openSession()) {
			Transaction transaction = session.beginTransaction();
			Integer id = (Integer) session.save(student);
			transaction.commit();
			student.setId(id);
		} catch (HibernateException e) {
			throw new DaoException("Cannot create student", e);
		}
		return student;
	}

	public Student update(Student student) {
		Configuration configuration = new Configuration().configure();
		try (SessionFactory sessionFactory = configuration.buildSessionFactory();
				Session session = sessionFactory.openSession()) {
			Transaction transaction = session.beginTransaction();
			session.update(student);
			transaction.commit();
		} catch (HibernateException e) {
			throw new DaoException("Cannot update student", e);
		}
		return student;
	}

	public void delete(Student student) {
		Configuration configuration = new Configuration().configure();
		try (SessionFactory sessionFactory = configuration.buildSessionFactory();
				Session session = sessionFactory.openSession()) {
			Transaction transaction = session.beginTransaction();
			session.delete(student);
			transaction.commit();
		} catch (HibernateException e) {
			throw new DaoException("Cannot delete student", e);
		}
	}

	public Student findById(Integer id) {
		Student studentToReturn = null;
		Configuration configuration = new Configuration().configure();
		try (SessionFactory sessionFactory = configuration.buildSessionFactory();
				Session session = sessionFactory.openSession()) {
			Transaction transaction = session.beginTransaction();
			studentToReturn = session.get(Student.class, id);
			transaction.commit();
		} catch (HibernateException e) {
			throw new DaoException("Cannot find student", e);
		}
		return studentToReturn;
	}

	public List<Student> findAll() {
		List<Student> students = new ArrayList<>();
		Configuration configuration = new Configuration().configure();
		try (SessionFactory sessionFactory = configuration.buildSessionFactory();
				Session session = sessionFactory.openSession()) {
			Transaction transaction = session.beginTransaction();
			students = session.createQuery("from Student", Student.class).getResultList();
			transaction.commit();
		} catch (HibernateException e) {
			throw new DaoException("Cannot find students", e);
		}
		return students;
	}
}
