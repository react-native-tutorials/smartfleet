/**
 * 
 */
package com.smartlife.smartfleet.dao;

import java.util.Date;
import java.util.List;

import com.smartlife.smartfleet.domain.Dispositivo;
import com.smartlife.smartfleet.domain.Equipo;
import com.smartlife.smartfleet.domain.EquipoDispositivo;
import com.smartlife.smartfleet.domain.GpsDispositivo;

/**
 * @author Marius Iulian Grigoras
 *
 */
public interface DispositivoDAO {

	Long saveDispositivo(Dispositivo dispositivo);
	List<Dispositivo> findAll();
	Dispositivo findById(Long id);
	Dispositivo findByMac(String mac);
	Dispositivo findByIp(String ip);
	Dispositivo findDispByEquiAssigned(Long id);
	void updateDisp(Dispositivo disp);
	void deleteDipositivo(Dispositivo dispositivo);
	void deleteDispositivo(Long id);
	
	Long saveGpsDispositivo(GpsDispositivo gps);
	void saveGps(GpsDispositivo gps);
	List<GpsDispositivo> findGpsByDisp(Dispositivo disp);
	List<GpsDispositivo> findAllGps();
	List<GpsDispositivo> findAllGpsParam(Long id);
	GpsDispositivo findGpsByDispAndTime(Dispositivo disp, Date crtTime);
	GpsDispositivo findGpsById(Long id);
	Equipo findEquiByDispAssigned(Long id);
	
	 Long saveEquiDisp(EquipoDispositivo equiDisp);
}
