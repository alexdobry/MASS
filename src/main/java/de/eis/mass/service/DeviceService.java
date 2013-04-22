package de.eis.mass.service;

import java.util.List;

import de.eis.mass.domain.Device;

/**
 * Beschreibt die Schnittstelle, um auf der Tabelle "Device" innerhalb der
 * Datenbank Operationen durchf�hren zu k�nnen.
 * 
 */
public interface DeviceService {

	/**
	 * Speichert das entsprechende Device in die Datenbank
	 * 
	 * @param device
	 *            das zu persistieren ist
	 */
	public void save(Device device);

	/**
	 * Sucht nach einem bestimmten Ger�t, welcher �ber die Id bestimmt wird.
	 * 
	 * @param id
	 *            des zu suchenden Ger�tes
	 * @return das gefundene Ger�t
	 */
	public Device getDeviceByid(String id);

	/**
	 * Gibt alle Ger�te die sich in der Datenbank befinden zur�ck.
	 * 
	 * @return alle Ger�te
	 */
	public List<Device> getAllDevices();

	/**
	 * Pr�ft, ob das Ger�t bereits in der Datenbank eingetragen ist
	 * 
	 * @param id
	 *            des zu �berpr�fenden Ger�tes
	 * @return true, wenn das Ger�t bereits existiert. false, wenn das Ger�t
	 *         noch nicht existiert
	 */
	public boolean contains(String id);
}
