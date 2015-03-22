package mapping;

// Generated 8 f�vr. 2015 15:44:33 by Hibernate Tools 4.3.1

import java.io.IOException;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;

import static javax.persistence.GenerationType.IDENTITY;

import javax.persistence.Id;
import javax.persistence.Table;

import com.google.code.geocoder.Geocoder;
import com.google.code.geocoder.GeocoderRequestBuilder;
import com.google.code.geocoder.model.GeocodeResponse;
import com.google.code.geocoder.model.GeocoderGeometry;
import com.google.code.geocoder.model.GeocoderRequest;

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
@Entity
@Table(name = "pharmacie", catalog = "pharmacie")
public class Pharmacie implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer id;
	private String nom;
	private String tel;
	private String adresse;
	private String longitude;
	private String attitude;
	private boolean garde;

	public Pharmacie() {
	}

	public Pharmacie(String nom, String tel, String adresse, boolean garde) {
		this.nom = nom;
		this.tel = tel;
		this.adresse = adresse;
		this.garde = garde;
	}

	public Pharmacie(String nom, String tel, String adresse, boolean garde,
			String longitude, String attitude) {
		this.nom = nom;
		this.tel = tel;
		this.adresse = adresse;
		this.garde = garde;
		this.longitude = longitude;
		this.attitude = attitude;
	}

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@Column(name = "nom", nullable = false, length = 254)
	public String getNom() {
		return this.nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	@Column(name = "tel", nullable = false, length = 254)
	public String getTel() {
		return this.tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	@Column(name = "adresse", nullable = false, length = 254)
	public String getAdresse() {
		return this.adresse;
	}

	public void setAdresse(String adresse) {
		this.adresse = adresse;
	}

	@Column(name = "longitude", nullable = false, length = 254)
	public String getLongitude() {
		return this.longitude;
	}

	public void setLongitude(String adresse) {
		this.longitude = trouverLongitude(adresse);
	}

	@Column(name = "attitude", nullable = false, length = 254)
	public String getAttitude() {
		return this.attitude;
	}

	public void setAttitude(String adresse) {
		this.attitude = trouverAttitude(adresse);
	}

	@Column(name = "garde", nullable = false)
	public boolean isGarde() {
		return this.garde;
	}

	public void setGarde(boolean garde) {
		this.garde = garde;
	}

	GeocodeResponse geocoderResponse = null;
	GeocoderGeometry g = null;

	public String trouverAttitude(String adr) {
		final Geocoder geocoder = new Geocoder();
		GeocoderRequest geocoderRequest = new GeocoderRequestBuilder()
				.setAddress(adr + ",Agadir").setLanguage("en")
				.getGeocoderRequest();
		try {
			geocoderResponse = geocoder.geocode(geocoderRequest);
			g = geocoderResponse.getResults().get(0).getGeometry();

		} catch (IOException e) {
			System.out.println("Exception while downloading data : "
					+ e.getMessage());
		}
		return g.getLocation().getLat().toString();
	}

	// méthode pour avoir longitude d'une adresse

	public String trouverLongitude(String adr) {
		final Geocoder geocoder = new Geocoder();
		GeocoderRequest geocoderRequest = new GeocoderRequestBuilder()
				.setAddress(adr + ",Agadir").setLanguage("en")
				.getGeocoderRequest();
		try {
			geocoderResponse = geocoder.geocode(geocoderRequest);
			g = geocoderResponse.getResults().get(0).getGeometry();

		} catch (IOException e) {
			System.out.println("Exception while downloading data : "
					+ e.getMessage());
		}
		return g.getLocation().getLng().toString();
	}
}
