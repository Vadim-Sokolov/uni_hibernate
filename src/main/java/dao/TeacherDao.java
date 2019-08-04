package dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import models.Teacher;

public class TeacherDao {

	public Teacher save(Teacher teacher) {
		Teacher teacherToReturn = null;
		if (teacher.getId() == null) {
			teacherToReturn = create(teacher);
		} else {
			teacherToReturn = update(teacher);
		}
		return teacherToReturn;
	}

	public Teacher create(Teacher teacher) {
		Configuration configuration = new Configuration().configure();
		try (SessionFactory sessionFactory = configuration.buildSessionFactory();
				Session session = sessionFactory.openSession()) {
			Transaction transaction = session.beginTransaction();
			Integer id = (Integer) session.save(teacher);
			transaction.commit();
			teacher.setId(id);
		} catch (HibernateException e) {
			throw new DaoException("Cannot create teacher", e);
		}
		return teacher;
	}

	public Teacher update(Teacher teacher) {
		Configuration configuration = new Configuration().configure();
		try (SessionFactory sessionFactory = configuration.buildSessionFactory();
				Session session = sessionFactory.openSession()) {
			Transaction transaction = session.beginTransaction();
			session.update(teacher);
			transaction.commit();
		} catch (HibernateException e) {
			throw new DaoException("Cannot update teacher", e);
		}
		return teacher;
	}

	public void delete(Teacher teacher) {
		Configuration configuration = new Configuration().configure();
		try (SessionFactory sessionFactory = configuration.buildSessionFactory();
				Session session = sessionFactory.openSession()) {
			Transaction transaction = session.beginTransaction();
			session.delete(teacher);
			transaction.commit();
		} catch (HibernateException e) {
			throw new DaoException("Cannot delete teacher", e);
		}
	}

	public Teacher findById(Integer id) {
		Teacher teacherToReturn = null;
		Configuration configuration = new Configuration().configure();
		try (SessionFactory sessionFactory = configuration.buildSessionFactory();
				Session session = sessionFactory.openSession()) {
			Transaction transaction = session.beginTransaction();
			teacherToReturn = session.get(Teacher.class, id);
			transaction.commit();
		} catch (HibernateException e) {
			throw new DaoException("Cannot find teacher", e);
		}
		return teacherToReturn;
	}

	public List<Teacher> findAll() {
		List<Teacher> teachers = new ArrayList<>();
		Configuration configuration = new Configuration().configure();
		try (SessionFactory sessionFactory = configuration.buildSessionFactory();
				Session session = sessionFactory.openSession()) {
			Transaction transaction = session.beginTransaction();
			teachers = session.createQuery("from Teacher", Teacher.class).getResultList();
			transaction.commit();
		} catch (HibernateException e) {
			throw new DaoException("Cannot find teachers", e);
		}
		return teachers;
	}
}
