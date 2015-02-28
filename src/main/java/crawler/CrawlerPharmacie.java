package crawler;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import mapping.Pharmacie;
import metier.PharmacieMetier;
import metier.PharmacieMetierImpl;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class CrawlerPharmacie {

	public void start()
	{
		crawlPharmacie();
	}

	public void crawlPharmacie()
	{
		try{
			Document doc ;
			PharmacieMetier service=new PharmacieMetierImpl();
			Pharmacie pharmacie=new Pharmacie();
			String nom;
			String adresse;
			String tel;

			List<Pharmacie>listePharmacies=service.getAllPharmacies();

			for(int i=1;i<=23;i++)
			{

				doc= Jsoup.connect("http://www.telecontact.ma/liens/pharmacies/agadir.php&page="+i).timeout(0).userAgent("Chrome").get();
				Elements divAll =doc.getElementsByClass("drs");

				if(i!=21)
				{
					for(Element phar:divAll)
					{
						nom=phar.child(0).child(0).child(0).child(1).child(1).text();
						adresse=phar.child(0).child(0).child(0).child(3).child(0).child(0).child(0).text();
						tel=phar.child(0).child(0).child(0).child(4).child(1).text();

						Boolean fail=false;
						for(Pharmacie p:listePharmacies)
						{
							if(p.getNom().equals(nom))
							{
								p.setAdresse(adresse);
								p.setTel(tel);
								p.setGarde(false);
								service.update(p);
								fail=true;
								break;
							}
							else 
								continue;

						}
						
						if(fail==false)
						{
							pharmacie.setNom(nom);
							pharmacie.setAdresse(adresse);
							pharmacie.setTel(tel);
							pharmacie.setGarde(false);
							service.insert(pharmacie);

						}
					}
				}
				
				if(i==21)
				{
					int j=1;
					for(Element phar:divAll)
					{

						nom=phar.child(0).child(0).child(0).child(1).child(1).text();

						if(j!=9)
						{
							adresse=phar.child(0).child(0).child(0).child(3).child(0).child(0).child(0).text();
							tel=phar.child(0).child(0).child(0).child(4).child(1).text();
							j++;
						}
						else
						{
							adresse=phar.child(0).child(0).child(0).child(2).child(0).child(0).child(0).text();
							tel=phar.child(0).child(0).child(0).child(3).child(1).text();
							j++;
						}

						Boolean fail=false;
						for(Pharmacie p:listePharmacies)
						{
							if(p.getNom().equals(nom))
							{
								p.setAdresse(adresse);
								p.setTel(tel);
								p.setGarde(false);
								service.update(p);
								fail=true;
								break;
							}
							else 
								continue;

						}
						if(fail==false)
						{

							pharmacie.setNom(nom);
							pharmacie.setAdresse(adresse);
							pharmacie.setTel(tel);
							pharmacie.setGarde(false);
							service.insert(pharmacie);

						}
					}
				}
			}
		}
		catch(Exception e)
		{
			Logger.getLogger(WebCrawler.class.getName()).log(Level.SEVERE,null,e);
		}

	}
}
