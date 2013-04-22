package de.eis.mass.dao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import de.eis.mass.domain.Dealer;
import de.eis.mass.service.DealerService;

/**
 * Diese Klasse enthält alle Tests für die Datenbank Operationen auf die
 * Dealer Tabelle. Dabei handelt es sich um den Test-Driven Development
 * Ansatz.
 * 
 */
@RunWith(SpringJUnit4ClassRunner.class)
@Transactional
@ContextConfiguration(locations = { "classpath:/applicationContext.xml" })
public class DealerServiceImplTest {

	@Autowired
	DealerService dealerService;

	@Test
	public void getDealerByName() {
		String name = "aldi";
		Dealer byName = dealerService.getDealerByName(name);

		assertEquals(byName.getName(), name);
	}

	@Test
	public void getAllDealer() {
		List<Dealer> allDealer = dealerService.getAllDealer();

		assertNotNull(allDealer);
		assertEquals(allDealer.size(), 13);
	}

	@Test
	public void getDealerById() {
		Long id = new Long(1);
		Dealer dealer = dealerService.getDealerById(id);

		assertNotNull(dealer);
		assertEquals(dealer.getId(), id);
	}
}
