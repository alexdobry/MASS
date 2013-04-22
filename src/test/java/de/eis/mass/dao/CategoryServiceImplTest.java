package de.eis.mass.dao;

import static org.junit.Assert.assertEquals;


import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import de.eis.mass.domain.Category;
import de.eis.mass.domain.SubCategory;
import de.eis.mass.service.CategoryService;


/**
 * Diese Klasse enthält alle Tests für die Datenbank Operationen auf die
 * Category Tabelle. Dabei handelt es sich um den Test-Driven Development
 * Ansatz.
 * 
 */
@RunWith(SpringJUnit4ClassRunner.class)
@Transactional
@ContextConfiguration(locations = { "classpath:/applicationContext.xml" })
public class CategoryServiceImplTest {

	@Autowired
	private CategoryService categoryService;

	@Test
	public void getAllSubCategoriesForCategory() {
		Category category = new Category("technik");
		List<SubCategory> subCategories = new ArrayList<SubCategory>();

		subCategories = categoryService.getAllSubCategoriesFor(category);
		assertNotNull(subCategories);
		assertEquals(subCategories.size(), 2);
	}

	@Test
	public void getAllCategories() {
		List<Category> allCategories = categoryService.getAllCategories();

		assertNotNull(allCategories);
		assertEquals(allCategories.size(), 5);
	}

	@Test
	public void getCategoryByName() {
		String name = "technik";
		Category category = categoryService.getCategoryByName(name);

		assertNotNull(category);
		assertEquals(category.getName(), name);
	}

	@Test
	public void getSubCategoryByName() {
		String name = "Fernseher";
		SubCategory subCategory = categoryService.getSubCategoryByName(name);

		assertNotNull(subCategory);
		assertEquals(subCategory.getName(), name.toLowerCase());
	}

	@Test
	public void getSubCategoryById() {
		Long id = new Long(1);
		SubCategory subCategory = categoryService.getSubCategoryById(id);

		assertNotNull(subCategory);
		assertEquals(subCategory.getId(), id);
	}

	@Test
	public void getCategoryBySubCategory() {
		SubCategory subCategory = categoryService
				.getSubCategoryByName("fernseher");
		Category category = categoryService
				.getCategoryBySubCategory(subCategory);

		assertNotNull(category);
		assertTrue(categoryService.getAllSubCategoriesFor(category).contains(
				subCategory));
	}
}
