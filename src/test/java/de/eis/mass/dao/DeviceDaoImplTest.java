package de.eis.mass.dao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import de.eis.mass.domain.Device;
import de.eis.mass.service.DeviceService;

/**
 * Diese Klasse enthält alle Tests für die Datenbank Operationen auf die
 * Device Tabelle. Dabei handelt es sich um den Test-Driven Development
 * Ansatz.
 * 
 */
@RunWith(SpringJUnit4ClassRunner.class)
@Transactional
@ContextConfiguration(locations = { "classpath:/applicationContext.xml" })
public class DeviceDaoImplTest {

	@Autowired
	private DeviceService deviceService;

	@Test
	public void getAllDevices() {
		List<Device> allDevices = deviceService.getAllDevices();

		assertNotNull("allDevices should not be null", allDevices);
		assertTrue("device size should be 0", allDevices.size() == 0);
	}

	@Test
	public void testSave() {
		String regId = "SomeRandomIdHere123";
		Device device = new Device(regId);
		deviceService.save(device);

		assertEquals(deviceService.getDeviceByid(regId), device);
	}

	@Test
	public void testContainsGivenDevice() {
		String id = "AnotherRandomIdHere123";
		Device device = new Device(id);
		deviceService.save(device);

		boolean contains = deviceService.contains(id);

		assertNotNull(contains);
		assertTrue(contains);
	}

}
