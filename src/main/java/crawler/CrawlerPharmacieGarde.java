package crawler;

import java.util.ArrayList;

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

public class CrawlerPharmacieGarde {

	public void start()
	{
		crawlPharmacieDeGarde();
	}

	public void crawlPharmacieDeGarde()
	{
		PharmacieMetier service=new PharmacieMetierImpl();

		List<String> liste1 = new ArrayList<String>();
		List<String> liste2 = new ArrayList<String>();
		List<String> liste3 = new ArrayList<String>();
		String url = this.getCrawlUrl();
		try
		{

			Document doc = Jsoup.connect(url).timeout(0).userAgent("Chrome").get();
			Elements names = doc.select(".pharmacies_elements_cls #resultats_h3_span .h2_rs_st_pnl_home .moodalbox");
			Elements addresses = doc.select(".pharmacies_elements_cls>div span[itemprop=\"streetAddress\"]");
			Elements tels = doc.select(".pharmacies_elements_cls div:nth-child(2) .tel_services");

			for (Element name : names)
			{
				liste1.add(name.text());
			}
			for (Element address : addresses)
			{
				liste2.add(address.text());
			}
			for (Element tel : tels)
			{
				liste3.add(tel.text());
			}





			for(int i=0 ; i<liste1.size(); i=i+1)
			{	
				List<String> listeNoms = service.getAllNomsPharmacies();

				for(String nom : listeNoms)
				{
					String nom2 = nom.replaceAll(" ", "").toLowerCase();
					String nom1 = liste1.get(i).replaceAll(" ", "").toLowerCase();
					
					if(nom1.equals(nom2))
					{
						Pharmacie pharmaciee = service.getPharmacie(nom);
						pharmaciee.setGarde(true);
						service.update(pharmaciee);
					}
				}

			}
		}
		catch(Exception e)
		{
			Logger.getLogger(WebCrawler.class.getName()).log(Level.SEVERE,null,e);
		}

	}
	public String getCrawlUrl()
	{
		String url = "http://www.telecontact.ma/services/pharmacies-de-garde/agadir-Maroc";
		return url;
	}
}
