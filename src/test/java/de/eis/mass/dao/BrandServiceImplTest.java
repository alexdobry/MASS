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

import de.eis.mass.domain.Brand;
import de.eis.mass.service.BrandService;

/**
 * Diese Klasse enthält alle Tests für die Datenbank Operationen auf die Brand
 * Tabelle. Dabei handelt es sich um den Test-Driven Development Ansatz.
 * 
 */
@RunWith(SpringJUnit4ClassRunner.class)
@Transactional
@ContextConfiguration(locations = { "classpath:/applicationContext.xml" })
public class BrandServiceImplTest {

	@Autowired
	private BrandService brandService;

	@Test
	public void getAllBrands() {
		List<Brand> allBrands = brandService.getAllBrands();

		assertNotNull(allBrands);
		assertEquals(allBrands.size(), 15);
	}

	@Test
	public void getBrandByName() {
		String name = "sony";
		Brand brand = brandService.getBrandByName(name);

		assertNotNull(brand);
		assertEquals(brand.getName(), name);
	}

	@Test
	public void getBrandById() {
		Long id = new Long(1);
		Brand brand = brandService.getBrandById(id);

		assertNotNull(brand);
		assertEquals(brand.getId(), id);
	}
}
