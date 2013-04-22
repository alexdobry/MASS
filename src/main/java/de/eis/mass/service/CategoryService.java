package de.eis.mass.service;

import java.util.List;

import de.eis.mass.domain.Category;
import de.eis.mass.domain.SubCategory;

/**
 * Beschreibt die Schnittstelle, um auf der Tabelle "Category" und "SubCategory"
 * innerhalb der Datenbank Operationen durchf�hren zu k�nnen.
 * 
 */
public interface CategoryService {

	/**
	 * Gibt alle Unterkategorien einer bestimmten Kategorie zur�ck.
	 * 
	 * @param category
	 *            dessen Unterkategorien gefunden werden soll
	 * @return alle Unterkategorien die der Kategorie entsprechen
	 */
	List<SubCategory> getAllSubCategoriesFor(Category category);

	/**
	 * Gibt alle Kategorien zur�ck.
	 * 
	 * @return alle gefundenen Kategorien
	 */
	List<Category> getAllCategories();

	/**
	 * Sucht nach einer bestimmten Kategorie, welche �ber den Namen bestimmt
	 * wird.
	 * 
	 * @param name
	 *            der zu suchenden Kategorie
	 * @return die gefundene Kategorie
	 */
	Category getCategoryByName(String name);

	/**
	 * Sucht nach einer bestimmten Unterkategorie, welche �ber den Namen
	 * bestimmt wird.
	 * 
	 * @param name
	 *            der zu suchenden Unterkategorie
	 * @return die gefundene Unterkategorie
	 */
	SubCategory getSubCategoryByName(String name);

	/**
	 * Sucht nach einer bestimmten Unterkategorie, welche �ber die Id bestimmt
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
