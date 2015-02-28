package dao;

import static org.junit.Assert.*;

import java.util.List;

import mapping.Pharmacie;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class PharmacieHibernateDaoTest {

	DataBaseConnection dbConn;
	PharmacieHibernateDao dao;


	@Before
	public void setUp() throws Exception 
	{
		dbConn = new DataBaseConnection();
		dao = new PharmacieHibernateDao();
		deleteAllPharmacies();
		setUpRecord();
	}

	@After
	public void tearDown() throws Exception 
	{
		dbConn=null;
		dao=null;
	}

	private void deleteAllPharmacies() 
	{
		String sql = "DELETE FROM Pharmacie";
		dbConn.execute(sql);
	}

	private void setUpRecord() 
	{
		String sql="INSERT INTO Pharmacie"+ "(id,nom,garde,tel,adresse) VALUES " + "('1','wafa','1','0614255265','agadir')";
		dbConn.execute(sql);
		String sql2="INSERT INTO Pharmacie"+ "(id,nom,garde,tel,adresse) VALUES " + "('2','lamia','0','0614255265','agadir')";
		dbConn.execute(sql2);
	}

	@Test
	public void testGetAllPharmacies()
	{
		System.out.println("test GetAllPharmacie");
		@SuppressWarnings("unchecked")
		List<Pharmacie> pharmacies = dao.getAllPharmacies();
		assertEquals(pharmacies.size(),2);
		Pharmacie pharmacie = pharmacies.get(0);
		assertEquals("wafa", pharmacie.getNom());
	}

	@Test
	public void testGetAllNomsPharmacies() 
	{
		System.out.println("test GetAllNomPharmacie");
		List <String> nomsPharmacies = dao.getAllNomsPharmacies();
		assertEquals(nomsPharmacies.size(),2);
		String nom = nomsPharmacies.get(0);
		assertEquals("wafa", nom);
	}

	@Test
	public void testGetRubrique() 
	{
		Pharmacie pharmacie = dao.getPharmacie("wafa");
		assertEquals(pharmacie.getAdresse(),"agadir"); 
	}

	@Test
	public void testInsert() 
	{
		System.out.println("insert");
		Pharmacie pharmacie = new Pharmacie();
		pharmacie.setAdresse("agadir");
		pharmacie.setGarde(true);
		pharmacie.setTel("0897661677");
		pharmacie.setNom("elfidae");
		dao.insert(pharmacie);
		String sql = "SELECT * FROM pharmacie";
		int numberOfRecords = dbConn.getNumberOfRecords(sql);
		assertEquals(numberOfRecords, 3);
	}

	@Test
	public void testUpdate() 
	{
		System.out.println("update");
		Pharmacie pharmacie=(Pharmacie) dao.getAllPharmacies().get(0);
		pharmacie.setNom("wafae");
		dao.update(pharmacie);
		assertEquals(pharmacie.getNom(),"wafae");
	}

	@Test
	public void testDeleteAll() 
	{
		dao.deleteAll();
		String sql ="select * from Pharmacie";
		int nb = dbConn.getNumberOfRecords(sql);
		assertEquals(nb,0);
	}

}
