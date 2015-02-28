package metier;

import java.util.List;

import mapping.Pharmacie;

public interface PharmacieMetier {
	
	public List<Pharmacie> getAllPharmacies();
	public Pharmacie getPharmacie(String nom);
	public void insert(Pharmacie pharmacie);
	public void update(Pharmacie pharmacie);
	public List<String> getAllNomsPharmacies();
	public void deleteAll();
}

