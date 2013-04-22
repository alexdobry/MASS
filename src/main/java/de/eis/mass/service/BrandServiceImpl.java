package de.eis.mass.service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;

import de.eis.mass.domain.Brand;
import de.eis.mass.domain.SubCategory;

@Service("brandService")
public class BrandServiceImpl implements BrandService {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	public List<Brand> getAllBrands() {
		String sql = "SELECT * FROM BRAND";
		List<Brand> allBrands = jdbcTemplate.query(sql, new BrandRowMapper());
		return allBrands;
	}

	public Brand getBrandByName(String name) {
		String sql = "SELECT * FROM BRAND WHERE name = ?";
		List<Brand> brand = jdbcTemplate.query(sql, new BrandRowMapper(), name.toLowerCase());
		return brand.get(0);
	}
	
	public Brand getBrandById(Long id) {
		String sql = "SELECT * FROM BRAND WHERE brand_id = ?";
		List<Brand> brand = jdbcTemplate.query(sql, new BrandRowMapper(), id);
		return brand.get(0);
	}

	public List<Brand> getAllBrandsFor(SubCategory subCategory) {
		List<Brand> brands = new ArrayList<Brand>();

		if (subCategory.getName().equals("fernseher")) {
			String sql = "SELECT * FROM BRAND WHERE name = 'samsung' OR name = 'lg' OR name = 'sony' OR name = 'toschiba'";
			List<Brand> brand = jdbcTemplate.query(sql, new BrandRowMapper());
			brands.addAll(brand);
		}

		if (subCategory.getName().equals("notebook")) {
			String sql = "SELECT * FROM BRAND WHERE name = 'samsung' OR name = 'lg' OR name = 'sony' OR name = 'toschiba' OR name = 'acer'";
			List<Brand> brand = jdbcTemplate.query(sql, new BrandRowMapper());
			brands.addAll(brand);
		}

		if (subCategory.getName().equals("t-shirt")
				|| subCategory.getName().equals("hose")) {
			String sql = "SELECT * FROM BRAND WHERE name = 'nike' OR name = 'puma' OR name = 'adidas'";
			List<Brand> brand = jdbcTemplate.query(sql, new BrandRowMapper());
			brands.addAll(brand);
		}

		return brands;
	}

	private class BrandRowMapper implements RowMapper<Brand> {

		public Brand mapRow(ResultSet resultSet, int arg1) throws SQLException {
			return new Brand(resultSet.getLong("Brand_ID"),
					resultSet.getString("Name"));
		}

	}

}
