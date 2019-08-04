package dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import models.Lecture;

public class LectureDao {

	public Lecture save(Lecture lecture) {
		Lecture lectureToReturn = null;
		if (lecture.getId() == null) {
			lectureToReturn = create(lecture);
		} else {
			lectureToReturn = update(lecture);
		}
		return lectureToReturn;
	}

	public Lecture create(Lecture lecture) {
		Configuration configuration = new Configuration().configure();
		try (SessionFactory sessionFactory = configuration.buildSessionFactory();
				Session session = sessionFactory.openSession()) {
			Transaction transaction = session.beginTransaction();
			Integer id = (Integer) session.save(lecture);
			transaction.commit();
			lecture.setId(id);
		} catch (HibernateException e) {
			throw new DaoException("Cannot create lecture", e);
		}
		return lecture;
	}

	public Lecture update(Lecture lecture) {
		Configuration configuration = new Configuration().configure();
		try (SessionFactory sessionFactory = configuration.buildSessionFactory();
				Session session = sessionFactory.openSession()) {
			Transaction transaction = session.beginTransaction();
			session.update(lecture);
			transaction.commit();
		} catch (HibernateException e) {
			throw new DaoException("Cannot update lecture", e);
		}
		return lecture;
	}

	public void delete(Lecture lecture) {
		Configuration configuration = new Configuration().configure();
		try (SessionFactory sessionFactory = configuration.buildSessionFactory();
				Session session = sessionFactory.openSession()) {
			Transaction transaction = session.beginTransaction();
			session.delete(lecture);
			transaction.commit();
		} catch (HibernateException e) {
			throw new DaoException("Cannot delete lecture", e);
		}
	}

	public Lecture findById(Integer id) {
		Lecture lectureToReturn = null;
		Configuration configuration = new Configuration().configure();
		try (SessionFactory sessionFactory = configuration.buildSessionFactory();
				Session session = sessionFactory.openSession()) {
			Transaction transaction = session.beginTransaction();
			lectureToReturn = session.get(Lecture.class, id);
			transaction.commit();
		} catch (HibernateException e) {
			throw new DaoException("Cannot find lecture", e);
		}
		return lectureToReturn;
	}

	public List<Lecture> findAll() {
		List<Lecture> lectures = new ArrayList<>();
		Configuration configuration = new Configuration().configure();
		try (SessionFactory sessionFactory = configuration.buildSessionFactory();
				Session session = sessionFactory.openSession()) {
			Transaction transaction = session.beginTransaction();
			lectures = session.createQuery("from Lecture", Lecture.class).getResultList();
			transaction.commit();
		} catch (HibernateException e) {
			throw new DaoException("Cannot find lectures", e);
		}
		return lectures;
	}
}
