package dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import models.Group;

public class GroupDao {

	public Group save(Group group) {
		Group groupToReturn = null;
		if (group.getId() == null) {
			groupToReturn = create(group);
		} else {
			groupToReturn = update(group);
		}
		return groupToReturn;
	}

	public Group create(Group group) {
		Configuration configuration = new Configuration().configure();
		try (SessionFactory sessionFactory = configuration.buildSessionFactory();
				Session session = sessionFactory.openSession()) {
			Transaction transaction = session.beginTransaction();
			Integer id = (Integer) session.save(group);
			transaction.commit();
			group.setId(id);
		} catch (HibernateException e) {
			throw new DaoException("Cannot create group", e);
		}
		return group;
	}

	public Group update(Group group) {
		Configuration configuration = new Configuration().configure();
		try (SessionFactory sessionFactory = configuration.buildSessionFactory();
				Session session = sessionFactory.openSession()) {
			Transaction transaction = session.beginTransaction();
			session.update(group);
			transaction.commit();
		} catch (HibernateException e) {
			throw new DaoException("Cannot update group", e);
		}
		return group;
	}

	public void delete(Group group) {
		Configuration configuration = new Configuration().configure();
		try (SessionFactory sessionFactory = configuration.buildSessionFactory();
				Session session = sessionFactory.openSession()) {
			Transaction transaction = session.beginTransaction();
			session.delete(group);
			transaction.commit();
		} catch (HibernateException e) {
			throw new DaoException("Cannot delete group", e);
		}
	}

	public Group findById(Integer id) {
		Group groupToReturn = null;
		Configuration configuration = new Configuration().configure();
		try (SessionFactory sessionFactory = configuration.buildSessionFactory();
				Session session = sessionFactory.openSession()) {
			Transaction transaction = session.beginTransaction();
			groupToReturn = session.get(Group.class, id);
			transaction.commit();
		} catch (HibernateException e) {
			throw new DaoException("Cannot find group", e);
		}
		return groupToReturn;
	}

	public List<Group> findAll() {
		List<Group> groups = new ArrayList<>();
		Configuration configuration = new Configuration().configure();
		try (SessionFactory sessionFactory = configuration.buildSessionFactory();
				Session session = sessionFactory.openSession()) {
			Transaction transaction = session.beginTransaction();
			groups = session.createQuery("from Group", Group.class).getResultList();
			transaction.commit();
		} catch (HibernateException e) {
			throw new DaoException("Cannot find groups", e);
		}
		return groups;
	}
}
