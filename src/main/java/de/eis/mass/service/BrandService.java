package de.eis.mass.service;

import java.util.List;

import de.eis.mass.domain.Brand;
import de.eis.mass.domain.SubCategory;

/**
 * Beschreibt die Schnittstelle, um auf der Tabelle "Brand" innerhalb der
 * Datenbank Operationen durchf�hren zu k�nnen.
 * 
 */
public interface BrandService {

	/**
	 * Gibt alle Marken die sich in der Datenbank befinden zur�ck.
	 * 
	 * @return alle Marken
	 */
	public List<Brand> getAllBrands();

	/**
	 * Sucht nach einer bestimmten Marke, welche �ber den Namen bestimmt wird.
	 * 
	 * @param brand
	 *            die zu suchende Marke
	 * @return die gefundene Marke
	 */
	public Brand getBrandByName(String brand);

	/**
	 * Sucht nach einer bestimmten Marke, welche �ber die Id bestimmt wird.
	 * 
	 * @param id
	 *            der zu suchenden Marke
	 * @return die gefundene Marke
	 */
	public Brand getBrandById(Long id);

	/**
	 * Gibt alle Marken einer bestimmten Unterkategorie zur�ck.
	 * 
	 * @param subCategory
	 *            dessen Marken gefunden werden soll
	 * @return alle Marken die der Unterkategorie entsprechen
	 */
	public List<Brand> getAllBrandsFor(SubCategory subCategory);

}
