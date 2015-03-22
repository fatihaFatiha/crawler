package metier;

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
public interface PharmacieMetier {

	public List<Pharmacie> getAllPharmacies();

	public Pharmacie getPharmacie(String nom);

	public void insert(Pharmacie pharmacie);

	public void update(Pharmacie pharmacie);

	public List<String> getAllNomsPharmacies();

	public void deleteAll();
}
