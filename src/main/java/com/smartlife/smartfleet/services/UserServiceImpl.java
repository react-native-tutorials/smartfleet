/**
 * 
 */
package com.smartlife.smartfleet.services;

import java.util.List;

import com.smartlife.smartfleet.dao.UserDAO;
import com.smartlife.smartfleet.domain.security.Usuario;

/**
 * @author Marius-Iulian Grigoras
 *
 */
public class UserServiceImpl implements UserService {

	UserDAO userDAO;
	
	@Override
	public Long saveUser(final Usuario user) {
		return userDAO.saveUser(user);
	}
	
	@Override
	public Usuario findUserByName(final String userName) {
		return userDAO.findUserByName(userName);
	}
	
	@Override
	public List<Usuario> findAll(){
		return userDAO.findAll();
	}

	public void setUserDAO(UserDAO userDAO) {
		this.userDAO = userDAO;
	}

	@Override
	public Usuario findById(Long id) {
		return userDAO.findById(id);
	}

	@Override
	public void deleteUser(Usuario user) {
		userDAO.deleteUser(user);
	}

	@Override
	public void updateUser(Usuario user) {
		userDAO.updateUser(user);
	}
}
