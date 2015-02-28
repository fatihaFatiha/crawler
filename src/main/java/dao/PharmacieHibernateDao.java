package dao;

import java.util.List;





import mapping.Pharmacie;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class PharmacieHibernateDao implements PharmacieDao{

	private List<Pharmacie> listePharmacies;
	private List<String> listeNoms;
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public List getAllPharmacies() {
		Session session=HibernateUtil.getSession();
		try
		{
			session.beginTransaction();
			Query q = session.createQuery("from Pharmacie");
			listePharmacies = q.list();
			return listePharmacies;
		}
		catch(HibernateException e)
		{
			throw e;
		}
		finally
		{
			session.close();
		}
	}
	@SuppressWarnings("unchecked")
	@Override
	public List<String> getAllNomsPharmacies()
	{
		Session session=HibernateUtil.getSession();
		try
		{
			session.beginTransaction();
			Query q = session.createQuery("select nom from Pharmacie");
			listeNoms = q.list();
			return listeNoms;
		}
		catch(HibernateException e)
		{
			throw e;
		}
		finally
		{
			session.close();
		}
	}

	@Override
	public Pharmacie getPharmacie(String nom)
	{
		Session session = HibernateUtil.getSession();
		try
		{
			session.beginTransaction();
			Query q = session.createQuery("from Pharmacie as p where p.nom=:nom");
			q.setParameter("nom", nom);
			return (Pharmacie) q.uniqueResult();
		}
		finally
		{
			session.close();
		}
	}
	@Override
	public void insert(Pharmacie pharmacie) {

		Session session = HibernateUtil.getSession();
		Transaction tx=null;
		try
		{
			tx = session.beginTransaction();
			session.save(pharmacie);
			tx.commit();         
		}
		catch(RuntimeException e)
		{
			if(tx != null) 
			{
				tx.rollback();
			}
			throw e;
		}
		finally
		{
			session.close();
		}
	}
	@Override
	public void update(Pharmacie pharmacie) {
		Session session = HibernateUtil.getSession();
		Transaction tx=null;
		try
		{
			tx=session.beginTransaction();
			session.update(pharmacie);
			tx.commit();
		}
		catch(RuntimeException e)
		{
			if(tx != null) 
			{
				tx.rollback();
			}
			throw e;
		}
		finally
		{
			session.close();
		}
	}
	@Override
	public void deleteAll() {
		Session session = HibernateUtil.getSession();
		Transaction tx = null;
		try
		{
			tx=session.beginTransaction();
			Query q = session.createQuery("delete from Pharmacie");
			q.executeUpdate();
			tx.commit();
		}
		catch(RuntimeException e)
		{
			if(tx != null) 
			{
				tx.rollback();
			}
			throw e;
		}
		finally
		{
			session.close();
		}
	}
}
