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
import de.eis.mass.domain.Offer;
import de.eis.mass.domain.SubCategory;
import de.eis.mass.service.BrandService;
import de.eis.mass.service.CategoryService;
import de.eis.mass.service.DealerService;
import de.eis.mass.service.OfferService;

/**
 * Diese Klasse enthält alle Tests für die Datenbank Operationen auf die Offer
 * Tabelle. Gleichzeitig werden interne Methoden, die zum Verarbeiten eines
 * Angebotes benötigt werden geprüft. Dabei handelt es sich um den Test-Driven
 * Development Ansatz.
 * 
 */
@RunWith(SpringJUnit4ClassRunner.class)
@Transactional
@ContextConfiguration(locations = { "classpath:/applicationContext.xml" })
public class OfferServiceImplTest {

	@Autowired
	private OfferService offerService;
	@Autowired
	private CategoryService categoryService;
	@Autowired
	private DealerService dealerService;
	@Autowired
	private BrandService brandService;

	@Test
	public void testSave() {
		Offer offer = new Offer(new Long(1), new Long(1), new Long(1),
				new Long(1), "name", 1.0);
		int resultCount = offerService.save(offer);

		assertNotNull(resultCount);
		assertEquals(resultCount, 1);
	}

	@Test
	public void testOffersPeriodOfTimeBeforeCurrentDay() {
		String endDate = "2012-12-31";

		Offer offer = new Offer("name", 1.0, new Long(1), new Long(1),
				new Long(1), endDate);

		int comparison = offer.getEffectiveStartDate().compareTo(
				offer.getEffectiveEndDate());
		// startDate(currentDate) should be after endDate
		assertEquals(comparison, 1);
	}

	@Test
	public void testOffersPeriodOfTimeAfterCurrentDay() {
		String endDate = "2013-10-10";

		Offer offer = new Offer("name", 1.0, new Long(1), new Long(1),
				new Long(1), endDate);

		int comparison = offer.getEffectiveStartDate().compareTo(
				offer.getEffectiveEndDate());
		// startDate(currentDate) should be before endDate
		assertEquals(comparison, -1);
	}

	@Test
	public void getAllOffersForGivenCategory() {
		Category category = categoryService.getCategoryByName("technik");

		List<Offer> offers = offerService.getAllOffersFor(category);

		assertNotNull(offers);
		assertEquals(offers.size(), 10);
	}

	@Test
	public void getAllOffersForGivenDealer() {
		Dealer dealer = dealerService.getDealerByName("saturn");

		List<Offer> offers = offerService.getAllOffersFor(dealer);

		assertNotNull(offers);
		for (Offer offer : offers) {
			assertEquals(offer.getDealerId(), dealer.getId());
		}

	}

	@Test
	public void getAllOffersForGivenSubCategory() {
		SubCategory subCategory = categoryService
				.getSubCategoryByName("fernseher");

		List<Offer> offers = offerService.getAllOffersFor(subCategory);

		assertNotNull(offers);
		for (Offer offer : offers) {
			assertEquals(offer.getSubCategoryId(), subCategory.getId());
		}
	}

	@Test
	public void getAllOffersForGivenBrand() {
		Brand brand = brandService.getBrandByName("samsung");

		List<Offer> offers = offerService.getAllOffersFor(brand);

		assertNotNull(offers);
		for (Offer offer : offers) {
			assertEquals(offer.getBrandId(), brand.getId());
		}
	}

	@Test
	public void getOffersWithAllProperties() {
		List<Offer> offers = new ArrayList<Offer>();
		// category = technik
		// subcategory 1 = fernseher
		// dealer 1 = aldi
		// adress = 'teststraße 1', '12345', 'teststadt1'
		// brand 1 = cola
		Offer firstOffer = new Offer("erstes angebot", 5, 10, new Long(1),
				new Long(1), new Long(1), "2003-10-10", "1", "2", "3", "4");
		// category = lebensmittel
		// subcategory 1 = getraenk
		// dealer 1 = saturn
		// adress = 'teststraße 5', '12345', 'teststadt5'
		// brand 1 = samsung
		Offer secondOffer = new Offer("zweites angebot", 5, 10, new Long(5),
				new Long(5), new Long(5), "2003-10-10", "1", "2", "3", "4");
		offers.add(firstOffer);
		offers.add(secondOffer);

		List<Offer> offersWithAllProperties = offerService
				.getOffersWithAllProperties(offers);

		assertNotNull(offersWithAllProperties);
		assertEquals(offersWithAllProperties.size(), 2);
		assertEquals(
				firstOffer.getCategoryName(),
				categoryService.getCategoryByName(
						categoryService.getCategoryBySubCategory(
								categoryService.getSubCategoryById(firstOffer
										.getSubCategoryId())).getName())
						.getName());
		assertEquals(firstOffer.getSubCategoryName(), categoryService
				.getSubCategoryById(firstOffer.getSubCategoryId()).getName());
		assertEquals(firstOffer.getBrandName(),
				brandService.getBrandById(firstOffer.getBrandId()).getName());
		assertEquals(firstOffer.getDealerName(),
				dealerService.getDealerById(firstOffer.getDealerId()).getName());
		assertEquals(firstOffer.getStreet(), "teststraße 1");
		assertEquals(firstOffer.getZip(), "12345");
		assertEquals(firstOffer.getCity(), "teststadt1");

		assertEquals(
				secondOffer.getCategoryName(),
				categoryService.getCategoryByName(
						categoryService.getCategoryBySubCategory(
								categoryService.getSubCategoryById(secondOffer
										.getSubCategoryId())).getName())
						.getName());
		assertEquals(secondOffer.getSubCategoryName(), categoryService
				.getSubCategoryById(secondOffer.getSubCategoryId()).getName());
		assertEquals(secondOffer.getBrandName(),
				brandService.getBrandById(secondOffer.getBrandId()).getName());
		assertEquals(secondOffer.getDealerName(),
				dealerService.getDealerById(secondOffer.getDealerId())
						.getName());
		assertEquals(secondOffer.getStreet(), "teststraße 5");
		assertEquals(secondOffer.getZip(), "12345");
		assertEquals(secondOffer.getCity(), "teststadt5");

	}

	@Test
	public void getDiscount() {
		Offer offer1 = new Offer(10, 4);
		Offer offer2 = new Offer(130, 90);
		int discount1 = offer1.getDiscount();
		int discount2 = offer2.getDiscount();

		assertNotNull(discount1);
		assertEquals(discount1, 60);
		assertNotNull(discount2);
		assertEquals(discount2, 31);
	}
}
