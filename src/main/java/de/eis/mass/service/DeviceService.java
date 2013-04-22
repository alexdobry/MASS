package de.eis.mass.service;

import java.util.List;

import de.eis.mass.domain.Device;

/**
 * Beschreibt die Schnittstelle, um auf der Tabelle "Device" innerhalb der
 * Datenbank Operationen durchführen zu können.
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
	 * Sucht nach einem bestimmten Gerät, welcher über die Id bestimmt wird.
	 * 
	 * @param id
	 *            des zu suchenden Gerätes
	 * @return das gefundene Gerät
	 */
	public Device getDeviceByid(String id);

	/**
	 * Gibt alle Geräte die sich in der Datenbank befinden zurück.
	 * 
	 * @return alle Geräte
	 */
	public List<Device> getAllDevices();

	/**
	 * Prüft, ob das Gerät bereits in der Datenbank eingetragen ist
	 * 
	 * @param id
	 *            des zu überprüfenden Gerätes
	 * @return true, wenn das Gerät bereits existiert. false, wenn das Gerät
	 *         noch nicht existiert
	 */
	public boolean contains(String id);
}
