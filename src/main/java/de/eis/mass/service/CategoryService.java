package de.eis.mass.service;

import java.util.List;

import de.eis.mass.domain.Category;
import de.eis.mass.domain.SubCategory;

/**
 * Beschreibt die Schnittstelle, um auf der Tabelle "Category" und "SubCategory"
 * innerhalb der Datenbank Operationen durchführen zu können.
 * 
 */
public interface CategoryService {

	/**
	 * Gibt alle Unterkategorien einer bestimmten Kategorie zurück.
	 * 
	 * @param category
	 *            dessen Unterkategorien gefunden werden soll
	 * @return alle Unterkategorien die der Kategorie entsprechen
	 */
	List<SubCategory> getAllSubCategoriesFor(Category category);

	/**
	 * Gibt alle Kategorien zurück.
	 * 
	 * @return alle gefundenen Kategorien
	 */
	List<Category> getAllCategories();

	/**
	 * Sucht nach einer bestimmten Kategorie, welche über den Namen bestimmt
	 * wird.
	 * 
	 * @param name
	 *            der zu suchenden Kategorie
	 * @return die gefundene Kategorie
	 */
	Category getCategoryByName(String name);

	/**
	 * Sucht nach einer bestimmten Unterkategorie, welche über den Namen
	 * bestimmt wird.
	 * 
	 * @param name
	 *            der zu suchenden Unterkategorie
	 * @return die gefundene Unterkategorie
	 */
	SubCategory getSubCategoryByName(String name);

	/**
	 * Sucht nach einer bestimmten Unterkategorie, welche über die Id bestimmt
	 * wird.
	 * 
	 * @param id
	 *            der zu suchenden Unterkategorie
	 * @return die gefundene Unterkategorie
	 */
	SubCategory getSubCategoryById(Long id);

	/**
	 * Sucht die entsprechende Kategorie einer bestimmten Unterkategorie
	 * 
	 * @param subCategory
	 *            nach dessen Kategorie gesucht werden soll
	 * @return die gefundene Kategorie
	 */
	Category getCategoryBySubCategory(SubCategory subCategory);

}
