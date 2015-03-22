package metier;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.experimental.theories.suppliers.TestedOn;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.runners.MockitoJUnitRunner;
import org.mockito.stubbing.Answer;

import mapping.Pharmacie;
import dao.PharmacieHibernateDao;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.mockito.Matchers.anyObject;
import static org.mockito.Mockito.doAnswer;

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
@RunWith(MockitoJUnitRunner.class)
public class PharmacieMetierImplTest {

	PharmacieMetierImpl metier;
	@Mock
	PharmacieHibernateDao dao;
	List<Pharmacie> listePharmacies;
	List<String> listeNomsPharmacies;

	@Before
	public void setUp() throws Exception {
		dao = mock(PharmacieHibernateDao.class);
		metier = new PharmacieMetierImpl();
		metier.setPharmacieDao(dao);
		setUpRecord();
	}

	private void setUpRecord() {
		listePharmacies = new ArrayList<Pharmacie>();
		listeNomsPharmacies = new ArrayList<String>();

		Pharmacie pharmacie = new Pharmacie("elfida", "06125541628", "AGADIR",
				false);
		Pharmacie pharmacieGarde = new Pharmacie("elwifak", "0219981726",
				"agadir", true);
		listePharmacies.add(pharmacie);
		listePharmacies.add(pharmacieGarde);

		listeNomsPharmacies.add(listePharmacies.get(0).getNom());
		listeNomsPharmacies.add(listePharmacies.get(1).getNom());

		when(dao.getAllPharmacies()).thenReturn(listePharmacies);
		when(dao.getAllNomsPharmacies()).thenReturn(listeNomsPharmacies);
		when(dao.getPharmacie("elfida")).thenReturn(listePharmacies.get(0));
		when(dao.getPharmacie("elwifak")).thenReturn(listePharmacies.get(1));

	}

	@After
	public void tearDown() throws Exception {
		dao = null;
		metier = null;
		listePharmacies.clear();
	}

	@Test
	public void testGetAllPharmacies() {
		List<Pharmacie> listePharmacies = metier.getAllPharmacies();
		assertEquals(listePharmacies.size(), 2);
		Mockito.verify(dao).getAllPharmacies();
	}

	@Test
	public void testGetPharmacie() {
		Pharmacie pharmacie = metier.getPharmacie("elfida");
		assertNotNull(pharmacie);
		assertEquals(pharmacie.getNom(), "elfida");
		Mockito.verify(dao).getPharmacie("elfida");
	}

	@Test
	public void testInsert() {
		Pharmacie pharmacie = new Pharmacie("Pharmacie La poste", "0612451285",
				"Agadir", false);

		doAnswer(new Answer<Object>() {
			public Object answer(InvocationOnMock invocation) {
				Object[] args = invocation.getArguments();
				listePharmacies.add((Pharmacie) args[0]);
				return null;
			}
		}).when(dao).insert((Pharmacie) anyObject());

		metier.insert(pharmacie);
		List<Pharmacie> listePharmacies = metier.getAllPharmacies();
		assertEquals(listePharmacies.size(), 3);

		Mockito.verify(dao).insert(pharmacie);
	}

	@Test
	public void testUpdate() {
		Pharmacie pharmacie = metier.getAllPharmacies().get(0);
		pharmacie.setNom("dakhla Pharmacy");
		doAnswer(new Answer<Object>() {
			public Object answer(InvocationOnMock invocation) {
				listePharmacies.get(0).setNom("dakhla Pharmacy");
				return null;
			}
		}).when(dao).update((Pharmacie) anyObject());

		metier.update(pharmacie);
		assertEquals(metier.getAllPharmacies().get(0).getNom(),
				"dakhla Pharmacy");
		Mockito.verify(dao).update(pharmacie);
	}

	@Test
	public void testGetAllNomsPharmacies() {
		List<String> listeNomsPharmacies = metier.getAllNomsPharmacies();
		assertEquals(listeNomsPharmacies.size(), 2);
		Mockito.verify(dao).getAllNomsPharmacies();
	}

	@Test
	public void testDeleteAll() {
		doAnswer(new Answer<Object>() {
			public Object answer(InvocationOnMock invocation) {
				listePharmacies.clear();
				return null;
			}
		}).when(dao).deleteAll();
		metier.deleteAll();
		assertEquals(metier.getAllPharmacies().size(), 0);
		Mockito.verify(dao).deleteAll();
	}

}
