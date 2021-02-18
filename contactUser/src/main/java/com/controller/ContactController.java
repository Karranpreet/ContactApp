package com.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.command.LoginCommand;
import com.command.UserCommand;
import com.domain.Contact;
import com.domain.User;
import com.service.ContactService;

@Controller
public class ContactController {
  
	 @Autowired
	 private ContactService contactService;
  
	@RequestMapping(value="/user/contact_form")
	public String contactForm(Model m) {
		m.addAttribute("command",new Contact());
		return "contact_form";
	}
	
	@RequestMapping(value= "/user/saveContact",method = RequestMethod.POST)
	public String saveOrUpdateContact(@ModelAttribute("command") Contact c, Model m, HttpSession session) {
		Integer contactId =(Integer)session.getAttribute("aContactId");
		if(contactId == null) {
			try {
				Integer userId = (Integer)session.getAttribute("userId");
				c.setUserId(3); //FK
				contactService.save(c);
				
				return "redirect:clist";
			} catch(Exception e) {
				
				return "contact_form";
			}
		} else {
			try {
				c.setContactId(contactId); //FK
				contactService.update(c);
				
				return "redirect:clist";
			} catch(Exception e) {
				
				return "contact_form";
			}
		}
		
	
	}
	
	@RequestMapping(value="/user/clist")
	public String contactList(Model m,HttpSession session) {
		Integer userId = (Integer)session.getAttribute("userId");
		System.out.println("***************************");
		System.out.println(userId);
		m.addAttribute("contactList",contactService.findUserContact(userId));
		System.out.println(contactService.findUserContact(userId));
		return "clist";
	}
	
	@RequestMapping(value="/user/edit_contact")
	public String prepareEditForm(Model m,HttpSession session,@RequestParam("cid") Integer contactId) {
		session.setAttribute("aContactId", contactId);
		Contact c = contactService.findById(contactId);
		m.addAttribute("command",c);
		return "contact_form";
	}
	
	@RequestMapping(value="/user/contactSearch")
	public String prepareEditForm(Model m,HttpSession session,@RequestParam("freeText") String freeText) {
		Integer userId = (Integer)session.getAttribute("userId");
		m.addAttribute("contactList",contactService.findUserContact(userId, freeText));
		return "clist";
	}
	
	@RequestMapping(value="/user/del_contact")
	public String deleteContact(@RequestParam("cid") Integer contactId) {
		contactService.delete(contactId);
		return "redirect:clist";
	}
	
	@RequestMapping(value="/user/bulk_delete")
	public String deleteBulkContact(@RequestParam("cid") Integer[] contactIds) {
		contactService.delete(contactIds);
		return "redirect:clist";
	}
}
