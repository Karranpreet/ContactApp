package com.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import com.domain.Contact;
import com.domain.User;
import com.rm.ContactRowMapper;
import com.rm.UserRowMapper;

@Repository
public class ContactDaoImpl extends BaseDao implements ContactDao {

	public void save(Contact u) {
		String sql ="Insert into contact(userId,name, phone, email, address,remark)"+ "VALUES(:userId,:name, :phone, :email, :address,:remark)";
		Map m = new HashMap();
		m.put("userId", u.getUserId());
		m.put("name", u.getName());
		m.put("phone", u.getPhone());
		m.put("email", u.getEmail());
		m.put("address", u.getAddress());
		m.put("remark", u.getRemark());
		
		KeyHolder kh = new GeneratedKeyHolder(); // generate value when any update by sql
		SqlParameterSource ps = new MapSqlParameterSource(m); // provide source of values
		super.getNamedParameterJdbcTemplate().update(sql, ps,kh);
		Integer contactId = kh.getKey().intValue();
		u.setContactId(contactId);
		
	}

	public void update(Contact u) {
		// TODO Auto-generated method stub
		String sql ="UPDATE contact SET name =:name, phone =:phone,email =:email,address=:address,remark=:remark WHERE contactId =:contactId";
	    
		Map m = new HashMap();
		m.put("contactId",u.getContactId());
		
		m.put("name", u.getName());
		m.put("phone", u.getPhone());
		m.put("email", u.getEmail());
		m.put("address", u.getAddress());
		m.put("remark", u.getRemark());
		
		SqlParameterSource ps = new MapSqlParameterSource(m); // provide source of values
		super.getNamedParameterJdbcTemplate().update(sql, ps);
	}

	public void delete(Integer contactId) {
		// TODO Auto-generated method stub
		String sql = "DELETE FROM contact WHERE contactId=?";
		super.getJdbcTemplate().update(sql, contactId);
	}

	public Contact findById(Integer contactId) {
		String query = "SELECT contactId ,userId,name, phone, email, address,remark FROM contact WHERE contactId=?";
		Contact u = super.getJdbcTemplate().queryForObject(query, new ContactRowMapper(),contactId);
		return u;
	}

	public List<Contact> findAll() {
		String query = "SELECT contactId ,userId,name, phone, email, address,remark FROM contact";
		return super.getJdbcTemplate().query(query, new ContactRowMapper());
	}

	public List<Contact> findByProperty(String propName, Object propValue) {
		// TODO Auto-generated method stub
		return null;
	}

}
