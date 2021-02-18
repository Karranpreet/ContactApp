package com.service;

import java.util.List;

import com.domain.Contact;

public interface ContactService {
 
	public void save(Contact c);
	public void update(Contact c);
	public void delete(Integer contactId);
	public void delete(Integer[] contactIds);
	public List<Contact> findUserContact(Integer userId);
	// to search user based on txt
	public List<Contact> findUserContact(Integer userId,String txt);
	public Contact findById(Integer contactId);
}
