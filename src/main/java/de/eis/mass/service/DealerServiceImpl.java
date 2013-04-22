package de.eis.mass.service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;

import de.eis.mass.domain.Category;
import de.eis.mass.domain.Dealer;

@Service("dealerService")
public class DealerServiceImpl implements DealerService {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	public Dealer getDealerByName(String name) {
		String sql = "SELECT * FROM DEALER WHERE NAME = ?";
		List<Dealer> dealer = jdbcTemplate.query(sql, new DealerRowMapper(),
				name.toLowerCase());
		return dealer.get(0);
	}

	public List<Dealer> getAllDealer() {
		String sql = "SELECT * FROM DEALER";
		List<Dealer> allDealer = jdbcTemplate.query(sql, new DealerRowMapper());
		return allDealer;
	}

	public Dealer getDealerById(Long id) {
		String sql = "SELECT * FROM DEALER WHERE dealer_id = ?";
		List<Dealer> dealer = jdbcTemplate
				.query(sql, new DealerRowMapper(), id);
		return dealer.get(0);
	}

	public List<Dealer> getAllDealerFor(Category category) {
		List<Dealer> dealers = new ArrayList<Dealer>();

		if (category.getName().equals("technik")) {
			String sql = "SELECT * FROM DEALER WHERE name = 'saturn' OR name = 'media markt'";
			List<Dealer> dealer = jdbcTemplate
					.query(sql, new DealerRowMapper());
			dealers.addAll(dealer);
		}

		if (category.getName().equals("lebensmittel")) {
			String sql = "SELECT * FROM DEALER WHERE name = 'aldi' OR name = 'lidl' OR name = 'rewe' OR name = 'edeka'";
			List<Dealer> dealer = jdbcTemplate
					.query(sql, new DealerRowMapper());
			dealers.addAll(dealer);
		}

		if (category.getName().equals("kleidung")) {
			String sql = "SELECT * FROM DEALER WHERE name = 'h&m' OR name = 'karstadt' OR name = 'sport check' OR name = 'c&a'";
			List<Dealer> dealer = jdbcTemplate
					.query(sql, new DealerRowMapper());
			dealers.addAll(dealer);
		}

		return dealers;
	}

	private class DealerRowMapper implements RowMapper<Dealer> {

		public Dealer mapRow(ResultSet resultSet, int arg1) throws SQLException {
			return new Dealer(resultSet.getLong("Dealer_ID"),
					resultSet.getString("Name"));
		}

	}

}
