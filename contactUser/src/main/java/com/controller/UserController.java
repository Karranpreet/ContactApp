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
import org.springframework.web.bind.annotation.ResponseBody;

import com.command.LoginCommand;
import com.command.UserCommand;
import com.domain.User;
import com.exception.UserBlockedException;
import com.service.UserService;

@Controller
public class UserController {
	
	@Autowired
  private UserService userService;
	
	@RequestMapping(value= {"/","/index"})
	public String getIndex(Model m) {
		m.addAttribute("command",new LoginCommand());
		return "index";
	}
	
	 @RequestMapping(value = "/login", method = RequestMethod.POST)
	    public String handleLogin(@ModelAttribute("command") LoginCommand cmd, Model m, HttpSession session) //httpsession we be provided by front controller
	 {
	        try {
	            User loggedInUser = userService.login(cmd.getName(), cmd.getPassword());
	            if (loggedInUser == null) {
	                //FAILED
	                //add error message and go back to login-form
	                m.addAttribute("err", "Login Failed! Enter valid credentials.");
	                return "index";//JSP - Login FORM
	            } else //SUCCESS
	            //check the role and redirect to a appropriate dashboard 
	            {
	                if (loggedInUser.getRole().equals(UserService.ROLE_ADMIN)) {
	                    //add user detail in session (assign session to logged in user)
	                   addUserInSession(loggedInUser, session);
	                	
	                    return "redirect:admin/dashboard";
	                } else if (loggedInUser.getRole().equals(UserService.ROLE_USER)) {
	                    //add user detail in session (assign session to logged in user)
	                    addUserInSession(loggedInUser, session);
	                    return "redirect:user/dashboard";
	                } else {
	                    //add error message and go back to login-form
	                    m.addAttribute("err", "Invalid User ROLE");
	                    return "index";//JSP - Login FORM
	                }
	            }
	        } catch (UserBlockedException ex) {
	            //add error message and go back to login-form
	            m.addAttribute("err", ex.getMessage());
	            return "index";//JSP - Login FORM
	        }
	    }
	
	@RequestMapping(value= {"/user/dashboard"})
	public String userDashboard() {
		return "dashboard_user";
	}
	
	@RequestMapping(value= {"/admin/dashboard"})
	public String adminDashboard() {
		return "dashboard_admin";
	}
	
	@RequestMapping(value= "/logout")
	public String logout(HttpSession session) {
		session.invalidate();
		return "redirect:index";
	}
	
	@RequestMapping(value = "/admin/users")
    public String getUserList(Model m) {
        m.addAttribute("userList", userService.getUsersList());
        System.out.println("********************");
        System.out.println(userService.getUsersList());
        return "users"; //JSP
    }
	
	@RequestMapping(value= "/reg_form")
	public String registerationForm(Model m) {
		UserCommand cm = new UserCommand();
		m.addAttribute("command",cm);
		return "reg_form";
	}

	@RequestMapping(value= "/register")
	public String register(@ModelAttribute("command") UserCommand cmd, Model m) {
		try {
			m.addAttribute("command",cmd);
			User user = cmd.getUser();
			user.setRole(userService.ROLE_USER);
			user.setLoginStatus(userService.LOGIN_STATUS_ACTIVE);
			userService.register(user);
			return "redirect:index";
		} catch(DuplicateKeyException e) {
			m.addAttribute("err","User already present");
			return "reg_form";
		}
	
	}
	
	public void addUserInSession(User u,HttpSession session) {
		session.setAttribute("user", u);
		session.setAttribute("userId", u.getUserId());
		session.setAttribute("role", u.getRole());
	}
	
	 @RequestMapping(value = "/admin/change_status")
	    @ResponseBody
	    public String changeLoginStatus(@RequestParam Integer userId, @RequestParam Integer loginStatus) {
	        try {
	            userService.changeLoginStatus(userId, loginStatus);
	            return "SUCCESS: Status Changed";
	        } catch (Exception e) {
	            e.printStackTrace();
	            return "ERROR: Unable to Change Status";
	        }
	    }
}
