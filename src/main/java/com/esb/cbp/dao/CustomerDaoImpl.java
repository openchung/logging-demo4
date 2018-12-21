package com.esb.cbp.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Repository;

import com.esb.cbp.dao.CustomerDao;
import com.esb.cbp.entity.Customer;

public class CustomerDaoImpl implements CustomerDao{
	
	@Autowired
	    private JdbcTemplate jdbcTemplate;
	
	@Override
	public void insert(Customer cus) {
		String sql = "INSERT INTO customer " +
				"(CUST_ID, NAME, AGE) VALUES (?, ?, ?)" ;
		jdbcTemplate.update(sql, new Object[]{
				cus.getCustId(), cus.getName(), cus.getAge()
		});
	}
	
	@Override
	public void inserBatch(List<Customer> customers) {
		String sql = "INSERT INTO customer " + "(CUST_ID, NAME, AGE) VALUES (?, ?, ?)";
		jdbcTemplate.batchUpdate(sql, new BatchPreparedStatementSetter() {
			public void setValues(PreparedStatement ps, int i) throws SQLException {
				Customer customer = customers.get(i);
				ps.setLong(1, customer.getCustId());
				ps.setString(2, customer.getName());
				ps.setInt(3, customer.getAge());
			}
			
			public int getBatchSize() {
				return customers.size();
			}
		});

	}
	
	public List<Customer> loadAllCustomer(){
		String sql = "SELECT * FROM customer";
		List<Map<String, Object>> rows = jdbcTemplate.queryForList(sql);
		
		List<Customer> result = new ArrayList<Customer>();
		for(Map<String, Object> row:rows){
			Customer cus = new Customer();
			cus.setCustId((Long)row.get("cust_id"));
			cus.setName((String)row.get("name"));
			cus.setAge((Integer) row.get("age"));
			result.add(cus);
		}
		
		return result;
	}

	@Override
	public Customer findCustomerById(long cust_id) {
		String sql = "SELECT * FROM customer WHERE CUST_ID = ?";
		return (Customer)jdbcTemplate.queryForObject(sql, new Object[]{cust_id}, new RowMapper<Customer>(){
			@Override
			public Customer mapRow(ResultSet rs, int rwNumber) throws SQLException {
				Customer cust = new Customer();
				cust.setCustId(rs.getLong("cust_id"));
				cust.setName(rs.getString("name"));
				cust.setAge(rs.getInt("age"));
				return cust;
			}
		});
	}

	@Override
	public String findNameById(long cust_id) {
		String sql = "SELECT name FROM customer WHERE cust_id = ?";
		return jdbcTemplate.queryForObject(sql, new Object[]{cust_id}, String.class);
	}

	@Override
	public int getTotalNumberCustomer() {
		String sql = "SELECT Count(*) FROM customer";
		int total = jdbcTemplate.queryForObject(sql, Integer.class);
		return total;
	}

	
}

