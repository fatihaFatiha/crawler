package metier;

import java.util.List;

import dao.PharmacieDao;
import dao.PharmacieHibernateDao;
import mapping.Pharmacie;

public class PharmacieMetierImpl implements PharmacieMetier{

	private PharmacieDao pharmacieDao = new PharmacieHibernateDao();
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Pharmacie> getAllPharmacies() {
		
		return pharmacieDao.getAllPharmacies();
	}
	
	@Override
	public Pharmacie getPharmacie(String nom)
	{
		return pharmacieDao.getPharmacie(nom);
	}
	@Override
	public void insert(Pharmacie pharmacie) 
	{
		pharmacieDao.insert(pharmacie);
	}
	
	@Override
	public void update(Pharmacie pharmacie)
	{
		pharmacieDao.update(pharmacie);
		
	}
	
	@Override
	public List<String> getAllNomsPharmacies()
	{
		return pharmacieDao.getAllNomsPharmacies();
	}
	
	@Override
	public void deleteAll()
	{
		pharmacieDao.deleteAll();
	}

	public PharmacieDao getPharmacieDao() {
		return pharmacieDao;
	}

	public void setPharmacieDao(PharmacieDao pharmacieDao) {
		this.pharmacieDao = pharmacieDao;
	}
	
	
}
