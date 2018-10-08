/**
 * 
 */
package com.smartlife.smartfleet.dao;

import java.util.Date;
import java.util.List;

import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.smartlife.smartfleet.domain.security.Usuario;
import com.smartlife.smartfleet.utils.DateUtils;

/**
 * @author Marius-Iulian Grigoras
 *
 */
@Repository
public class UserDAOImpl extends SmartDAO implements UserDAO {

	public UserDAOImpl(SessionFactory sf) {
		super(sf);
	}

	@Transactional
	@Override
	public Long saveUser(Usuario user) {
		final Long userId = (Long)getCurrentSession().save(user);
		return userId;
	}
	
	@Transactional
	public List<Usuario> findAll() {
		String sqlQuery = "select u.* from usuarios u";
		Query<Usuario> query = sessionFactory.getCurrentSession().createNativeQuery(sqlQuery, Usuario.class);
		List<Usuario> turnos = (List<Usuario>) query.list();
		return turnos;
	}

	@Override
	public Usuario findUserByName(String userName) {
		Usuario usuario = null;
		String sqlQuery = "select u.* from usuarios u where u.usu_acc = '" + userName + "'";
		Query<Usuario> query = getCurrentSession().createNativeQuery(sqlQuery, Usuario.class);
		List<Usuario> usuarios = query.getResultList();
		if(!usuarios.isEmpty()) {
			usuario = usuarios.get(0);
		}
		return usuario;
	}
	@Transactional
	public Usuario findById(Long id) {
		Usuario user = null;
		String sqlQuery = "select u.* from usuarios u where u.id = '" + id + "'";
		Query<Usuario> query = getCurrentSession().createNativeQuery(sqlQuery, Usuario.class);
		List<Usuario> usuarios = query.getResultList();
		if(!usuarios.isEmpty()) {
			user = usuarios.get(0);
		}
		return user;
	}
	
	@Transactional
	public void deleteUser(Usuario user) {
		getCurrentSession().delete(user);
	}

	@Transactional
	public void updateUser(Usuario user) {
		Date systemDate = DateUtils.getSystemDate();
		user.setFechaAct(systemDate);
		getCurrentSession().merge(user);
	}
}
