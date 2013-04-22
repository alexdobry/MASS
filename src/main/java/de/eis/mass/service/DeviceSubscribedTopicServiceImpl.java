package de.eis.mass.service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;

import de.eis.mass.domain.Brand;
import de.eis.mass.domain.Category;
import de.eis.mass.domain.Dealer;
import de.eis.mass.domain.Device;
import de.eis.mass.domain.SubCategory;

@Service("deviceSubscribedTopicService")
public class DeviceSubscribedTopicServiceImpl implements
		DeviceSubscribedTopicService {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	public int save(Device device, Category category) {
		String sql = "INSERT INTO SUBSCRIPTION (Subscription_ID, Reg_ID, Category_ID, Sub_C_ID, Dealer_ID, Brand_ID) VALUES (?,?,?,?,?,?)";
		return jdbcTemplate.update(sql, getNextIdForSupscription(),
				device.getId(), category.getId(), 0, 0, 0);
	}

	public int save(Device device, Category category, SubCategory subCategory) {
		String sql = "INSERT INTO SUBSCRIPTION (Subscription_ID, Reg_ID, Category_ID, Sub_C_ID, Dealer_ID, Brand_ID) VALUES (?,?,?,?,?,?)";
		return jdbcTemplate.update(sql, getNextIdForSupscription(),
				device.getId(), category.getId(), subCategory.getId(), 0, 0);
	}

	public int save(Device device, Category category, SubCategory subCategory,
			Dealer dealer) {
		String sql = "INSERT INTO SUBSCRIPTION (Subscription_ID, Reg_ID, Category_ID, Sub_C_ID, Dealer_ID, Brand_ID) VALUES (?,?,?,?,?,?)";
		return jdbcTemplate.update(sql, getNextIdForSupscription(),
				device.getId(), category.getId(), subCategory.getId(),
				dealer.getId(), 0);
	}

	public int save(Device device, Category category, SubCategory subCategory,
			Dealer dealer, Brand brand) {
		String sql = "INSERT INTO SUBSCRIPTION (Subscription_ID, Reg_ID, Category_ID, Sub_C_ID, Dealer_ID, Brand_ID) VALUES (?,?,?,?,?,?)";
		return jdbcTemplate.update(sql, getNextIdForSupscription(),
				device.getId(), category.getId(), subCategory.getId(),
				dealer.getId(), brand.getId());
	}

	public List<Category> getCategorySubscriptionsFor(Device device) {
		String sql = "SELECT c.* FROM CATEGORY c JOIN SUBSCRIPTION s ON c.category_id = s.category_id AND s.reg_ID = ?";
		List<Category> categories = jdbcTemplate.query(sql,
				new CategoryRowMapper(), device.getId());
		return categories;
	}

	private Long getNextIdForSupscription() {
		String sql = "call next value for Subscription_SEQ";
		return jdbcTemplate.queryForLong(sql);
	}

	private class CategoryRowMapper implements RowMapper<Category> {

		public Category mapRow(ResultSet resultSet, int arg1)
				throws SQLException {
			return new Category(resultSet.getLong("Category_ID"),
					resultSet.getString("Name"));
		}

	}

}
