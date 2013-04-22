package de.eis.mass.service;

import java.util.List;

import de.eis.mass.domain.Brand;
import de.eis.mass.domain.SubCategory;

/**
 * Beschreibt die Schnittstelle, um auf der Tabelle "Brand" innerhalb der
 * Datenbank Operationen durchführen zu können.
 * 
 */
public interface BrandService {

	/**
	 * Gibt alle Marken die sich in der Datenbank befinden zurück.
	 * 
	 * @return alle Marken
	 */
	public List<Brand> getAllBrands();

	/**
	 * Sucht nach einer bestimmten Marke, welche über den Namen bestimmt wird.
	 * 
	 * @param brand
	 *            die zu suchende Marke
	 * @return die gefundene Marke
	 */
	public Brand getBrandByName(String brand);

	/**
	 * Sucht nach einer bestimmten Marke, welche über die Id bestimmt wird.
	 * 
	 * @param id
	 *            der zu suchenden Marke
	 * @return die gefundene Marke
	 */
	public Brand getBrandById(Long id);

	/**
	 * Gibt alle Marken einer bestimmten Unterkategorie zurück.
	 * 
	 * @param subCategory
	 *            dessen Marken gefunden werden soll
	 * @return alle Marken die der Unterkategorie entsprechen
	 */
	public List<Brand> getAllBrandsFor(SubCategory subCategory);

}
