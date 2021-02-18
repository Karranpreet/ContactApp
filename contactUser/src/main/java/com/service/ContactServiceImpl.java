package com.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dao.BaseDao;
import com.dao.ContactDao;
import com.domain.Contact;
import com.rm.ContactRowMapper;

@Service
public class ContactServiceImpl extends BaseDao implements ContactService{
      
	 @Autowired
	 private ContactDao contactDao;
	 
	public void save(Contact c) {
		// TODO Auto-generated method stub
		contactDao.save(c);
	}

	public void update(Contact c) {
		// TODO Auto-generated method stub
		contactDao.update(c);
	}

	public void delete(Integer contactId) {
		// TODO Auto-generated method stub
		contactDao.delete(contactId);
	}

	public void delete(Integer[] contactIds) {
		// TODO Auto-generated method stub
		String ids = StringUtil.toCommaSeperatorString(contactIds);
		String sql = "DELETE from contact where contactId IN("+ids+")";
		super.getJdbcTemplate().update(sql);
	}

	public List<Contact> findUserContact(Integer userId) {
		String query = "SELECT contactId ,userId,name, phone, email, address,remark FROM contact WHERE userId=?";
		List<Contact> c= super.getJdbcTemplate().query(query, new ContactRowMapper(),userId);
		for(int i =0 ;i<= c.size();i++) {
			System.out.println(c.toString());
		}
		return c;
		//contactDao.findByProperty("userId", userId);
//		return c;
	}

	public Contact findById(Integer contactId) {
		// TODO Auto-generated method stub
		return contactDao.findById(contactId);
	}

	public List<Contact> findUserContact(Integer userId, String txt) {
		String query = "SELECT contactId ,userId,name, phone, email, address,remark FROM contact WHERE userId=? AND (name LIKE '%"+txt+"%' OR address LIKE '%"+txt+"%' OR email LIKE '%"+txt+"%')";
		return super.getJdbcTemplate().query(query, new ContactRowMapper(),userId);
	}

}
