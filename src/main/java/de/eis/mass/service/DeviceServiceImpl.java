package de.eis.mass.service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;

import de.eis.mass.domain.Device;

@Service("deviceService")
public class DeviceServiceImpl implements DeviceService {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	public void save(Device device) {
		String sql = "INSERT INTO DEVICE (Reg_ID) VALUES (?)";
		jdbcTemplate.update(sql, device.getId());
	}

	public List<Device> getAllDevices() {
		String sql = "SELECT * FROM DEVICE";
		return jdbcTemplate.query(sql, new DeviceRowMapper());
	}

	public Device getDeviceByid(String id) {
		String sql = "SELECT * FROM DEVICE WHERE Reg_ID = ?";
		try {
			List<Device> devicesWithId = jdbcTemplate.query(sql,
					new DeviceRowMapper(), id);

			return devicesWithId.get(0);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}

	}

	public boolean contains(String id) {
		String sql = "SELECT * FROM DEVICE WHERE reg_id = ?";
		List<Device> device = jdbcTemplate
				.query(sql, new DeviceRowMapper(), id);
		return !device.isEmpty();
	}

	// private Long getNextUserId() {
	// String sql = "call next value for UID_SEQ";
	// return jdbcTemplate.queryForLong(sql);
	// }

	private class DeviceRowMapper implements RowMapper<Device> {

		public Device mapRow(ResultSet resultSet, int arg1) throws SQLException {
			return new Device(resultSet.getString("Reg_ID"));
		}

	}

}
