package com.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import com.domain.User;
import com.rm.UserRowMapper;

@Repository  //Spring @Repository annotation is used to indicate that the class provides the mechanism for storage, retrieval, search, update and delete operation on objects.
public class UserDaoImpl extends BaseDao implements UserDao {

	public void save(User u) {
		String sql ="Insert into user(name, phone, email, address, loginName, password, role, loginStatus)"+ "VALUES(:name, :phone, :email, :address, :loginName, :password, :role, :loginStatus)";
		Map m = new HashMap();
		m.put("name", u.getName());
		m.put("phone", u.getPhone());
		m.put("email", u.getEmail());
		m.put("address", u.getAddress());
		m.put("loginName", u.getLoginName());
		m.put("password", u.getPassword());
		m.put("role", u.getRole());
		m.put("loginStatus", u.getLoginStatus());
		
		KeyHolder kh = new GeneratedKeyHolder(); // generate value when any update by sql
		SqlParameterSource ps = new MapSqlParameterSource(m); // provide source of values
		super.getNamedParameterJdbcTemplate().update(sql, ps,kh);
		Integer userId = kh.getKey().intValue();
		u.setUserId(userId);
		
	}

	public void update(User u) {
	String sql ="UPDATE user SET name =:name, phone =:phone,email =:email,address=:address,role = :role,loginStatus =:loginStatus WHERE userId =:userId";
    
	Map m = new HashMap();
	m.put("userId", u.getUserId());
	m.put("name", u.getName());
	m.put("phone", u.getPhone());
	m.put("email", u.getEmail());
	m.put("address", u.getAddress());
	m.put("loginName", u.getLoginName());
	m.put("password", u.getPassword());
	m.put("role", u.getRole());
	m.put("loginStatus", u.getLoginStatus());
	SqlParameterSource ps = new MapSqlParameterSource(m); // provide source of values
	super.getNamedParameterJdbcTemplate().update(sql, ps);
		
	}

	public void delete(Integer userId) {
		String sql = "DELETE FROM user WHERE userId=?";
		super.getJdbcTemplate().update(sql, userId);
		
	}

	public User findById(Integer userId) {
		String query = "SELECT userId,name,phone,email,address,loginName,role,loginStatus FROM user WHERE userId=?";
		User u = super.getJdbcTemplate().queryForObject(query, new UserRowMapper(),userId);
		return u;
	}

	public List<User> findAll() {
		String query = "SELECT userId,name,phone,email,address,loginName,role,loginStatus FROM user";
		return super.getJdbcTemplate().query(query, new UserRowMapper());
		
	}

	public List<User> findByProperty(String propName, Object propValue) {
		  String sql = "SELECT userId, name, phone, email, address, loginName, role, loginStatus"
	                + " FROM user WHERE "+propName+"=?";
	         return super.getJdbcTemplate().query(sql, new UserRowMapper(), propValue);
	}

}
