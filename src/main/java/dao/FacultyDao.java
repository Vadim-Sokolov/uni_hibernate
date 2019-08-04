package dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import models.Faculty;

public class FacultyDao {

	public Faculty save(Faculty faculty) {
		Faculty facultyToReturn = null;
		if (faculty.getId() == null) {
			facultyToReturn = create(faculty);
		} else {
			facultyToReturn = update(faculty);
		}
		return facultyToReturn;
	}

	public Faculty create(Faculty faculty) {
		Configuration configuration = new Configuration().configure();
		try (SessionFactory sessionFactory = configuration.buildSessionFactory();
				Session session = sessionFactory.openSession()) {
			Transaction transaction = session.beginTransaction();
			Integer id = (Integer) session.save(faculty);
			transaction.commit();
			faculty.setId(id);
		} catch (HibernateException e) {
			throw new DaoException("Cannot create faculty", e);
		}
		return faculty;
	}

	public Faculty update(Faculty faculty) {
		Configuration configuration = new Configuration().configure();
		try (SessionFactory sessionFactory = configuration.buildSessionFactory();
				Session session = sessionFactory.openSession()) {
			Transaction transaction = session.beginTransaction();
			session.update(faculty);
			transaction.commit();
		} catch (HibernateException e) {
			throw new DaoException("Cannot update faculty", e);
		}
		return faculty;
	}

	public void delete(Faculty faculty) {
		Configuration configuration = new Configuration().configure();
		try (SessionFactory sessionFactory = configuration.buildSessionFactory();
				Session session = sessionFactory.openSession()) {
			Transaction transaction = session.beginTransaction();
			session.delete(faculty);
			transaction.commit();
		} catch (HibernateException e) {
			throw new DaoException("Cannot delete faculty", e);
		}
	}

	public Faculty findById(Integer id) {
		Faculty facultyToReturn = null;
		Configuration configuration = new Configuration().configure();
		try (SessionFactory sessionFactory = configuration.buildSessionFactory();
				Session session = sessionFactory.openSession()) {
			Transaction transaction = session.beginTransaction();
			facultyToReturn = session.get(Faculty.class, id);
			transaction.commit();
		} catch (HibernateException e) {
			throw new DaoException("Cannot find faculty", e);
		}
		return facultyToReturn;
	}

	public List<Faculty> findAll() {
		List<Faculty> faculties = new ArrayList<>();
		Configuration configuration = new Configuration().configure();
		try (SessionFactory sessionFactory = configuration.buildSessionFactory();
				Session session = sessionFactory.openSession()) {
			Transaction transaction = session.beginTransaction();
			faculties = session.createQuery("from Faculty", Faculty.class).getResultList();
			transaction.commit();
		} catch (HibernateException e) {
			throw new DaoException("Cannot find faculties", e);
		}
		return faculties;
	}
}
