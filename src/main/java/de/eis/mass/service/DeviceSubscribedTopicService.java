package de.eis.mass.service;

import java.util.List;

import de.eis.mass.domain.Brand;
import de.eis.mass.domain.Category;
import de.eis.mass.domain.Dealer;
import de.eis.mass.domain.Device;
import de.eis.mass.domain.SubCategory;

/**
 * Beschreibt die Schnittstelle, um auf der Tabelle "Supscription" innerhalb der
 * Datenbank Operationen durchf�hren zu k�nnen.
 * 
 */
public interface DeviceSubscribedTopicService {

	/**
	 * Speichert das Abonnement zwischen einem Ger�t und einer Kategorie
	 * 
	 * @param device
	 *            der Abonniert
	 * @param category
	 *            die abonniert werden soll
	 * @return 1, wenn es erfolgreich war. 0, wenn nicht
	 */
	public int save(Device device, Category category);

	/**
	 * Speichert das Abonnement zwischen einem Ger�t und einer Unterkategorie
	 * 
	 * @param device
	 *            der Abonniert
	 * @param category
	 *            die �ber der Unterkategorie steht
	 * @param subCategory
	 *            die abonniert werden soll
	 * @return 1, wenn es erfolgreich war. 0, wenn nicht
	 */
	public int save(Device device, Category category, SubCategory subCategory);

	/**
	 * Speichert das Abonnement zwischen einem Ger�t und einem H�ndler
	 * 
	 * @param device
	 *            der Abonniert
	 * @param category
	 *            die �ber der Unterkategorie steht
	 * @param subCategory
	 *            die �ber dem H�ndler steht
	 * @param dealer
	 *            der abonniert werden soll
	 * @return 1, wenn es erfolgreich war. 0, wenn nicht
	 */
	public int save(Device device, Category category, SubCategory subCategory,
			Dealer dealer);

	/**
	 * Speichert das Abonnement zwischen einem Ger�t und einem H�ndler
	 * 
	 * @param device
	 *            der Abonniert
	 * @param category
	 *            die �ber der Unterkategorie steht
	 * @param subCategory
	 *            die �ber dem H�ndler steht
	 * @param dealer
	 *            der �ber der Marke steht
	 * @param brand
	 *            welche abonniert werden soll
	 * @return 1, wenn es erfolgreich war. 0, wenn nicht
	 */
	public int save(Device device, Category category, SubCategory subCategory,
			Dealer dealer, Brand brand);

	/**
	 * Gibt alle Kategorien zur�ck, die ein Ger�t abonniert hat
	 * 
	 * @param device
	 *            der Abonnement
	 * @return alle Kategorie, die abonniert wurden
	 */
	public List<Category> getCategorySubscriptionsFor(Device device);

}
