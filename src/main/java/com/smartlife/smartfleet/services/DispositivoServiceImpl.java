/**
 * 
 */
package com.smartlife.smartfleet.services;

import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Service;

import com.smartlife.smartfleet.dao.DispositivoDAO;
import com.smartlife.smartfleet.domain.Dispositivo;
import com.smartlife.smartfleet.domain.Equipo;
import com.smartlife.smartfleet.domain.EquipoDispositivo;
import com.smartlife.smartfleet.domain.GpsDispositivo;

/**
 * @author Marius Iulian Grigoras
 *
 */
@Service
public class DispositivoServiceImpl implements DispositivoService {

	DispositivoDAO dispositivoDAO;
	
	@Override
	public Long saveDispositivo(Dispositivo dispositivo) {
		return dispositivoDAO.saveDispositivo(dispositivo);
	}
	
	@Override
	public List<Dispositivo> findAll() {
		return dispositivoDAO.findAll();
	}

	@Override
	public Dispositivo findById(Long id) {
		return dispositivoDAO.findById(id);
	}

	@Override
	public Dispositivo findByMac(String mac) {
		return dispositivoDAO.findByMac(mac);
	}

	@Override
	public Dispositivo findByIp(String ip) {
		return dispositivoDAO.findByIp(ip);
	}
	
	@Override
	public void updateDisp(Dispositivo disp) {
		dispositivoDAO.updateDisp(disp);
	}

	@Override
	public void deleteDipositivo(Dispositivo dispositivo) {
		dispositivoDAO.deleteDipositivo(dispositivo);
	}

	@Override
	public void deleteDispositivo(Long id) {
		dispositivoDAO.deleteDispositivo(id);
	}

	@Override
	public Long saveGpsDispositivo(GpsDispositivo gps) {
		return dispositivoDAO.saveGpsDispositivo(gps);
	}

	@Override
	public void saveGps(GpsDispositivo gps) {
		dispositivoDAO.saveGps(gps);
	}

	@Override
	public List<GpsDispositivo> findGpsByDisp(Dispositivo disp) {
		return dispositivoDAO.findGpsByDisp(disp);
	}

	@Override
	public GpsDispositivo findGpsByDispAndTime(Dispositivo disp, Date crtTime) {
		return dispositivoDAO.findGpsByDispAndTime(disp, crtTime);
	}

	@Override
	public GpsDispositivo findGpsById(Long id) {
		return dispositivoDAO.findGpsById(id);
	}

	@Override
	public List<GpsDispositivo> findAllGps() {
		return dispositivoDAO.findAllGps();
	}

	@Override
	public List<GpsDispositivo> findAllGpsParam(Long id) {
		return dispositivoDAO.findAllGpsParam(id);
	}

	@Override
	public Dispositivo findDispByEquiAssigned(Long equiId) {
		return dispositivoDAO.findDispByEquiAssigned(equiId);
	}

	public DispositivoDAO getDispositivoDAO() {
		return dispositivoDAO;
	}

	public void setDispositivoDAO(DispositivoDAO dispositivoDAO) {
		this.dispositivoDAO = dispositivoDAO;
	}

	@Override
	public Equipo findEquiByDispAssigned(Long id) {
		return dispositivoDAO.findEquiByDispAssigned(id);
	}

	@Override
	public Long saveEquiDisp(EquipoDispositivo equiDisp) {
		return dispositivoDAO.saveEquiDisp(equiDisp);
	}

	
}
