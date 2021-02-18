package com.dao;

import java.util.List;

import com.domain.Contact;

public interface ContactDao {
	public void save(Contact u);
	public void update(Contact u);
	public void delete(Integer contactId);
	public Contact findById(Integer contactId);
	public List<Contact> findAll();
	public List<Contact> findByProperty(String propName,Object propValue);

}
