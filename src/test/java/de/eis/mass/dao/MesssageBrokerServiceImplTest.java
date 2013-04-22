package de.eis.mass.dao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import de.eis.mass.domain.Brand;
import de.eis.mass.domain.Dealer;
import de.eis.mass.domain.Device;
import de.eis.mass.domain.Offer;
import de.eis.mass.domain.SubCategory;
import de.eis.mass.service.BrandService;
import de.eis.mass.service.CategoryService;
import de.eis.mass.service.DealerService;
import de.eis.mass.service.DeviceService;
import de.eis.mass.service.DeviceSubscribedTopicService;
import de.eis.mass.service.MessageBrokerService;

/**
 * Diese Klasse enthält alle Tests für die Datenbank Operationen auf die
 * Supscription Tabelle. Primär wird das Verteilen der Nachricht über den
 * Message Broker geprüft. Dabei handelt es sich um den Test-Driven Development
 * Ansatz.
 * 
 */
@RunWith(SpringJUnit4ClassRunner.class)
@Transactional
@ContextConfiguration(locations = { "classpath:/applicationContext.xml" })
public class MesssageBrokerServiceImplTest {

	@Autowired
	private DeviceService deviceService;
	@Autowired
	private CategoryService categoryService;
	@Autowired
	private BrandService brandService;
	@Autowired
	private DealerService dealerService;
	@Autowired
	private DeviceSubscribedTopicService deviceSubscribedTopicService;
	@Autowired
	private MessageBrokerService messageBrokerService;

	@Test
	public void testMessageBrokerWithOneOffer() {
		// setup offer
		SubCategory subCategory = categoryService
				.getSubCategoryByName("fernseher");
		Dealer dealer = dealerService.getDealerByName("saturn");
		Brand brand = brandService.getBrandByName("samsung");
		Offer offer = new Offer(new Long(100), subCategory.getId(),
				brand.getId(), dealer.getId(), "Samsung Fernseher", 300);

		// subscriber
		List<Device> subscriber = new ArrayList<Device>();

		subscriber.add(new Device("1Id"));
		subscriber.add(new Device("2Id"));
		subscriber.add(new Device("3Id"));
		subscriber.add(new Device("4Id"));
		subscriber.add(new Device("5Id"));

		for (Device device : subscriber) {
			deviceService.save(device);
		}

		// subscription
		deviceSubscribedTopicService.save(subscriber.get(0),
				categoryService.getCategoryByName("technik"),
				categoryService.getSubCategoryByName("fernseher"));
		deviceSubscribedTopicService.save(subscriber.get(1),
				categoryService.getCategoryByName("technik"),
				categoryService.getSubCategoryByName("notebook"));
		deviceSubscribedTopicService.save(subscriber.get(2),
				categoryService.getCategoryByName("technik"),
				categoryService.getSubCategoryByName("fernseher"),
				dealerService.getDealerByName("saturn"));
		deviceSubscribedTopicService.save(subscriber.get(3),
				categoryService.getCategoryByName("moebel"),
				categoryService.getSubCategoryByName("tisch"));
		deviceSubscribedTopicService.save(subscriber.get(4),
				categoryService.getCategoryByName("technik"),
				categoryService.getSubCategoryByName("fernseher"),
				dealerService.getDealerByName("media markt"),
				brandService.getBrandByName("samsung"));
		deviceSubscribedTopicService.save(subscriber.get(0),
				categoryService.getCategoryByName("technik"));

		Set<Device> devices = messageBrokerService.getSubscriberFor(offer);

		assertNotNull("subscriber should not be null", devices);
		assertEquals("there are at least 2 subscriber", devices.size(), 2);
		assertTrue("device 1 is one of them",
				devices.contains(subscriber.get(0)));
		assertTrue("device 3 is the other one",
				devices.contains(subscriber.get(2)));

	}
}
