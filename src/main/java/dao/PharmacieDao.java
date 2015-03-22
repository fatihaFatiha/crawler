package dao;

import java.util.List;

import mapping.Pharmacie;

/**
 * 
 * 
 * @author BERNINZ Khadija
 * @author BJIJE Fatiha
 * @author ZOUBIR Fatima Zohra
 * 
 * 
 * 
 */
public interface PharmacieDao {

	@SuppressWarnings("rawtypes")
	public List getAllPharmacies();

	public Pharmacie getPharmacie(String nom);

	public void insert(Pharmacie pharmacie);

	public void update(Pharmacie pharmacie);

	public List<String> getAllNomsPharmacies();

	public void deleteAll();
}
