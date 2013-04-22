package de.eis.mass.dao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import de.eis.mass.domain.Brand;
import de.eis.mass.domain.Category;
import de.eis.mass.domain.Dealer;
import de.eis.mass.domain.Device;
import de.eis.mass.domain.SubCategory;
import de.eis.mass.service.BrandService;
import de.eis.mass.service.CategoryService;
import de.eis.mass.service.DealerService;
import de.eis.mass.service.DeviceService;
import de.eis.mass.service.DeviceSubscribedTopicService;

/**
 * Diese Klasse enthält alle Tests für die Datenbank Operationen auf die
 * Supscription Tabelle. Primär werden Anfragen über das Abonnement zwischen
 * Device und Topics geprüft. Dabei handelt es sich um den Test-Driven
 * Development Ansatz.
 * 
 */
@RunWith(SpringJUnit4ClassRunner.class)
@Transactional
@ContextConfiguration(locations = { "classpath:/applicationContext.xml" })
public class DeviceSubscribedTopicServiceImplTest {

	@Autowired
	private DeviceSubscribedTopicService deviceSubscribedTopicService;
	@Autowired
	private DeviceService deviceService;
	@Autowired
	private DealerService dealerService;
	@Autowired
	private CategoryService categoryService;
	@Autowired
	private BrandService brandService;

	@Test
	public void deviceSubscribedCategory() {
		Device device = new Device("AnotherRandomIdHere123");
		deviceService.save(device);
		Category category = categoryService.getCategoryByName("technik");

		int rowsAffected = deviceSubscribedTopicService.save(device, category);
		assertNotNull(rowsAffected);
		assertEquals(rowsAffected, 1);
	}

	@Test
	public void deviceSubscribedSubCategory() {
		Device device = new Device("AnotherRandomIdHere123");
		deviceService.save(device);
		Category category = categoryService.getCategoryByName("technik");
		SubCategory subCategory = categoryService
				.getSubCategoryByName("fernseher");

		int rowsAffected = deviceSubscribedTopicService.save(device, category,
				subCategory);
		assertNotNull(rowsAffected);
		assertEquals(rowsAffected, 1);
	}

	@Test
	public void deviceSubscribedDealer() {
		Device device = new Device("AnotherRandomIdHere123");
		deviceService.save(device);
		Category category = categoryService.getCategoryByName("technik");
		SubCategory subCategory = categoryService
				.getSubCategoryByName("fernseher");
		Dealer dealer = dealerService.getDealerByName("saturn");

		int rowsAffected = deviceSubscribedTopicService.save(device, category,
				subCategory, dealer);
		assertNotNull(rowsAffected);
		assertEquals(rowsAffected, 1);
	}

	@Test
	public void deviceSubscribedBrand() {
		Device device = new Device("AnotherRandomIdHere123");
		deviceService.save(device);
		Category category = categoryService.getCategoryByName("technik");
		SubCategory subCategory = categoryService
				.getSubCategoryByName("fernseher");
		Dealer dealer = dealerService.getDealerByName("saturn");
		Brand brand = brandService.getBrandByName("samsung");

		int rowsAffected = deviceSubscribedTopicService.save(device, category,
				subCategory, dealer, brand);
		assertNotNull(rowsAffected);
		assertEquals(rowsAffected, 1);
	}

	@Test
	public void testGetCategorySubscribtionForGivenDevice() {
		List<Category> categories = new ArrayList<Category>();
		Device device = new Device("AnotherRandomIdHere123");
		deviceService.save(device);
		categories.add(categoryService.getCategoryByName("technik"));
		categories.add(categoryService.getCategoryByName("bekleidung"));

		for (Category category : categories) {
			deviceSubscribedTopicService.save(device, category);
		}

		List<Category> categorySubscribtionForDevice = deviceSubscribedTopicService
				.getCategorySubscriptionsFor(device);
		assertNotNull(categorySubscribtionForDevice);
		assertEquals(categorySubscribtionForDevice.size(), 2);
		assertEquals(categorySubscribtionForDevice, categories);
	}
}