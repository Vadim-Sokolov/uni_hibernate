package dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import models.Auditorium;

public class AuditoriumDao {

	public Auditorium save(Auditorium auditorium) {
		Auditorium auditoriumToReturn = null;
		if (auditorium.getId() == null) {
			auditoriumToReturn = create(auditorium);
		} else {
			auditoriumToReturn = update(auditorium);
		}
		return auditoriumToReturn;
	}

	public Auditorium create(Auditorium auditorium) {
		Configuration configuration = new Configuration().configure();
		try (SessionFactory sessionFactory = configuration.buildSessionFactory();
				Session session = sessionFactory.openSession()) {
			Transaction transaction = session.beginTransaction();
			Integer id = (Integer) session.save(auditorium);
			transaction.commit();
			auditorium.setId(id);
		} catch (HibernateException e) {
			throw new DaoException("Cannot create auditorium", e);
		}
		return auditorium;
	}

	public Auditorium update(Auditorium auditorium) {
		Configuration configuration = new Configuration().configure();
		try (SessionFactory sessionFactory = configuration.buildSessionFactory();
				Session session = sessionFactory.openSession()) {
			Transaction transaction = session.beginTransaction();
			session.update(auditorium);
			transaction.commit();
		} catch (HibernateException e) {
			throw new DaoException("Cannot update auditorium", e);
		}
		return auditorium;
	}

	public void delete(Auditorium auditorium) {
		Configuration configuration = new Configuration().configure();
		try (SessionFactory sessionFactory = configuration.buildSessionFactory();
				Session session = sessionFactory.openSession()) {
			Transaction transaction = session.beginTransaction();
			session.delete(auditorium);
			transaction.commit();
		} catch (HibernateException e) {
			throw new DaoException("Cannot delete auditorium", e);
		}
	}

	public Auditorium findById(Integer id) {
		Auditorium auditoriumToReturn = null;
		Configuration configuration = new Configuration().configure();
		try (SessionFactory sessionFactory = configuration.buildSessionFactory();
				Session session = sessionFactory.openSession()) {
			Transaction transaction = session.beginTransaction();
			auditoriumToReturn = session.get(Auditorium.class, id);
			transaction.commit();
		} catch (HibernateException e) {
			throw new DaoException("Cannot find auditorium", e);
		}
		return auditoriumToReturn;
	}

	public List<Auditorium> findAll() {
		List<Auditorium> auditoriums = new ArrayList<>();
		Configuration configuration = new Configuration().configure();
		try (SessionFactory sessionFactory = configuration.buildSessionFactory();
				Session session = sessionFactory.openSession()) {
			Transaction transaction = session.beginTransaction();
			auditoriums = session.createQuery("from auditoriums", Auditorium.class).getResultList();
			transaction.commit();
		} catch (HibernateException e) {
			throw new DaoException("Cannot find auditoriums", e);
		}
		return auditoriums;
	}
}
