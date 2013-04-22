package de.eis.mass.controller;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;

import de.eis.mass.domain.Brand;
import de.eis.mass.domain.Category;
import de.eis.mass.domain.Criteria;
import de.eis.mass.domain.Dealer;
import de.eis.mass.domain.SubCategory;
import de.eis.mass.service.BrandService;
import de.eis.mass.service.CategoryService;
import de.eis.mass.service.DealerService;
import de.eis.mass.service.OfferService;

/**
 * Wird unter anderem aufgerufen, sobald die Weboberfl�che im Browser gestartet
 * wird.
 * 
 */
@Controller
public class WelcomeController {

	private Logger log = Logger.getLogger(WelcomeController.class);
	private CategoryService categoryService;
	private DealerService dealerService;
	private BrandService brandService;
	private OfferService offerService;

	public WelcomeController(CategoryService categoryService,
			DealerService dealerService, BrandService brandService,
			OfferService offerService) {
		this.categoryService = categoryService;
		this.dealerService = dealerService;
		this.brandService = brandService;
		this.offerService = offerService;
	}

	/**
	 * Zeigt die Welcome.jsp als Startseite an
	 * 
	 * @return die jsp, welche angezeigt werden soll
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String welcome() {
		log.debug("WelcomeController called");
		return "welcome";
	}

	/**
	 * L�dt dynamisch alle Kategorien aus der Datenbank und gibt diese an die
	 * Welcome.jsp zur�ck. Diese werden in einem Dropdown Men� angezeigt.
	 * 
	 * @return alle Kategorien
	 */
	@ModelAttribute("categories")
	public List<String> categories() {
		List<String> categories = new ArrayList<String>();
		List<Category> allCategories = categoryService.getAllCategories();
		for (Category category : allCategories) {
			categories.add(category.getName());
		}
		categories.remove(0);

		return categories;
	}

	/**
	 * Lauscht auf den GET-Request, der von der Welcome.jsp �ber einen AJAX
	 * ausgel�st wird. Dabei wird je nachdem, welche Kategorie ausgew�hlt wurde
	 * die entsprechenden Unterkategorien aus der Datenbank geladen. Diese
	 * werden anschlie�end in ein JSON Objekt gemarshalled und an die
	 * Welcome.jsp �bergeben.
	 * 
	 * @param category
	 *            dessen Unterkategorien gen�tigt werden
	 * @return alle Unterkategorien
	 */
	@RequestMapping(value = "/subcategories", method = RequestMethod.GET)
	@ResponseBody
	public String getSubCategoriesFor(@RequestParam String category) {
		Category categoryByName = categoryService.getCategoryByName(category);

		List<SubCategory> subCategories = categoryService
				.getAllSubCategoriesFor(categoryByName);
		return new Gson().toJson(subCategories);

	}

	/**
	 * Lauscht auf den GET-Request, der von der Welcome.jsp �ber einen AJAX
	 * ausgel�st wird. Dabei wird je nachdem, welche Kategorie ausgew�hlt wurde
	 * die entsprechenden H�ndler aus der Datenbank geladen. Diese werden
	 * anschlie�end in ein JSON Objekt gemarshalled und an die Welcome.jsp
	 * �bergeben.
	 * 
	 * @param category
	 *            dessen H�ndler gen�tigt werden
	 * @return alle H�ndler
	 */
	@RequestMapping(value = "/dealers", method = RequestMethod.GET)
	@ResponseBody
	public String getDealersFor(@RequestParam String category) {
		Category categoryByName = categoryService.getCategoryByName(category);

		List<Dealer> dealers = dealerService.getAllDealerFor(categoryByName);
		return new Gson().toJson(dealers);
	}

	/**
	 * Lauscht auf den GET-Request, der von der Welcome.jsp �ber einen AJAX
	 * ausgel�st wird. Dabei wird je nachdem, welche Unterkategorie ausgew�hlt
	 * wurde die entsprechenden Marken aus der Datenbank geladen. Diese werden
	 * anschlie�end in ein JSON Objekt gemarshalled und an die Welcome.jsp
	 * �bergeben.
	 * 
	 * @param subCategory
	 *            dessen Marken gen�tigt werden
	 * @return alle Marken
	 */
	@RequestMapping(value = "/brands", method = RequestMethod.GET)
	@ResponseBody
	public String getBrandsFor(@RequestParam String subCategory) {
		SubCategory subCategoryByName = categoryService
				.getSubCategoryByName(subCategory);

		List<Brand> brands = brandService.getAllBrandsFor(subCategoryByName);
		return new Gson().toJson(brands);
	}

	/**
	 * Lauscht auf den GET-Request, der von der Welcome.jsp �ber einen AJAX
	 * ausgel�st wird. Dabei wird je nachdem, welche Unterkategorie ausgew�hlt
	 * wurde dessen entsprechenden Kriterien aus der Datenbank geladen. Diese
	 * werden anschlie�end in ein JSON Objekt gemarshalled und an die
	 * Welcome.jsp �bergeben.
	 * 
	 * @param subCategory
	 *            dessen Kriterien gen�tigt werden
	 * @return alle Kriterien
	 */
	@RequestMapping(value = "/criteria", method = RequestMethod.GET)
	@ResponseBody
	public String getCriteria(@RequestParam String subCategory) {
		SubCategory subCategoryByName = categoryService
				.getSubCategoryByName(subCategory);
		List<Criteria> criterias = offerService
				.getCriteriasFor(subCategoryByName);
		return new Gson().toJson(criterias);
	}

}
