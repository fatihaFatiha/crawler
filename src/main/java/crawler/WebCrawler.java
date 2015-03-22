package crawler;

import metier.PharmacieMetier;
import metier.PharmacieMetierImpl;

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
public class WebCrawler implements Runnable {

	PharmacieMetier service = new PharmacieMetierImpl();

	@Override
	public void run() {

		deleteAll();
		new CrawlerPharmacie().start();
		new CrawlerPharmacieGarde().start();
		System.out
				.println("La collection des informations concernant les pharmacies d'agadir est terminée avec succès");

	}

	public void deleteAll() {
		
		service.deleteAll();
		
	}

	public static void main(String[] args) {
		new CrawlerPharmacie().start();
		new CrawlerPharmacieGarde().start();
		System.out
				.println("La collection des informations concernant les pharmacies d'agadir est terminée avec succès");

	}
}
