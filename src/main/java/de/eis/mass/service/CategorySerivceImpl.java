package de.eis.mass.service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;

import de.eis.mass.domain.Category;
import de.eis.mass.domain.SubCategory;

@Service("categoryService")
public class CategorySerivceImpl implements CategoryService {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	public List<SubCategory> getAllSubCategoriesFor(Category category) {
		String sql = "SELECT s.* FROM SUB_CATEGORY s JOIN CATEGORY c ON s.category_id = c.category_id WHERE c.name = ?";
		List<SubCategory> subCategories = jdbcTemplate.query(sql,
				new SubCategoryRowMapper(), category.getName());
		return subCategories;
	}

	public List<Category> getAllCategories() {
		String sql = "SELECT * FROM CATEGORY";
		List<Category> allCategories = jdbcTemplate.query(sql,
				new CategoryRowMapper());
		return allCategories;
	}

	public Category getCategoryByName(String name) {
		String sql = "SELECT * FROM CATEGORY WHERE name = ?";
		List<Category> category = jdbcTemplate.query(sql,
				new CategoryRowMapper(), name.toLowerCase());
		return category.get(0);
	}

	public SubCategory getSubCategoryByName(String name) {
		String sql = "SELECT * FROM SUB_CATEGORY WHERE name = ?";
		List<SubCategory> subCategory = jdbcTemplate.query(sql,
				new SubCategoryRowMapper(), name.toLowerCase());
		return subCategory.get(0);
	}

	public SubCategory getSubCategoryById(Long id) {
		String sql = "SELECT * FROM SUB_CATEGORY WHERE sub_c_id = ?";
		List<SubCategory> subCategory = jdbcTemplate.query(sql,
				new SubCategoryRowMapper(), id);
		return subCategory.get(0);
	}

	public Category getCategoryBySubCategory(SubCategory subCategory) {
		String sql = "SELECT * FROM CATEGORY c JOIN SUB_CATEGORY s ON s.category_id = c.category_id AND s.sub_c_id = ?";
		List<Category> category = jdbcTemplate.query(sql,
				new CategoryRowMapper(), subCategory.getId());
		return category.get(0);
	}

	private class SubCategoryRowMapper implements RowMapper<SubCategory> {

		public SubCategory mapRow(ResultSet resultSet, int arg1)
				throws SQLException {
			return new SubCategory(resultSet.getLong("Sub_C_ID"),
					resultSet.getString("name"));
		}

	}

	private class CategoryRowMapper implements RowMapper<Category> {

		public Category mapRow(ResultSet resultSet, int arg1)
				throws SQLException {
			return new Category(resultSet.getLong("Category_ID"),
					resultSet.getString("name"));
		}

	}

}
