/**
 * 
 */
package com.smartlife.smartfleet.services;

import java.util.List;

import com.smartlife.smartfleet.domain.security.Usuario;

/**
 * @author Marius-Iulian Grigoras
 *
 */
public interface UserService {
	Long saveUser(Usuario user);
	Usuario findUserByName(String userName);
	List<Usuario> findAll();
	Usuario findById(Long id);
	void deleteUser(Usuario user);
	void updateUser(Usuario user);
}
