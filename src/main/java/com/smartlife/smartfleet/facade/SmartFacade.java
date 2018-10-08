package com.smartlife.smartfleet.facade;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.smartlife.smartfleet.domain.Acciones;
import com.smartlife.smartfleet.domain.ApplicationParameter;
import com.smartlife.smartfleet.domain.Dispositivo;
import com.smartlife.smartfleet.domain.Equipo;
import com.smartlife.smartfleet.domain.EquipoDispositivo;
import com.smartlife.smartfleet.domain.Estado;
import com.smartlife.smartfleet.domain.EstadoEquipo;
import com.smartlife.smartfleet.domain.GpsDispositivo;
import com.smartlife.smartfleet.domain.Operador;
import com.smartlife.smartfleet.domain.Razon;
import com.smartlife.smartfleet.domain.Turno;
import com.smartlife.smartfleet.domain.common.Tipo;
import com.smartlife.smartfleet.domain.security.Usuario;
import com.smartlife.smartfleet.domain.view.EquiStateView;
import com.smartlife.smartfleet.dto.EquipmentDetail;
import com.smartlife.smartfleet.dto.StateDetail;
import com.smartlife.smartfleet.services.ApplicationService;
import com.smartlife.smartfleet.services.DispositivoService;
import com.smartlife.smartfleet.services.EquipoService;
import com.smartlife.smartfleet.services.OperatorService;
import com.smartlife.smartfleet.services.StateService;
import com.smartlife.smartfleet.services.TipoService;
import com.smartlife.smartfleet.services.TurnoService;
import com.smartlife.smartfleet.services.UserService;

@Component("smartFacade")
public class SmartFacade {

	StateService stateService;
	TipoService tipoService;
	ApplicationService applicationService;
	DispositivoService dispositivoService;
	EquipoService equipoService;
	OperatorService operatorService;
	UserService userService;
	TurnoService turnoService;

	public List<StateDetail> findStateDetail() {
		return stateService.findStateDetails();
	}

	public List<Tipo> findAllTipo() {
		return tipoService.findAll();
	}

	public List<Tipo> findTipoByCategory(final String category) {
		return tipoService.findByCategoria(category);
	}

	public List<Estado> findAllStates() {
		return stateService.findAll();
	}

	public Estado findState(final String name) {
		return stateService.findEstadoByName(name);
	}

	public Estado findEstadoByEquiState(final Long equiStateId) {
		return stateService.findEstadoByEquiState(equiStateId);
	}
	
	public Estado findStateById(final Long id) {
		return stateService.findEstadoById(id);
	}

	public Razon findReasonById(final Long reasonId) {
		return stateService.findRazonById(reasonId);
	}

	public Razon findReasonByName(final String reasonName) {
		return stateService.findRazonByName(reasonName);
	}

	public List<Razon> findAllReasonByState(final String stateName) {
		return stateService.findAllReasonByState(stateName);
	}

	public Long saveRazon(final Razon razon) {
		return stateService.saveRazon(razon);
	}

	public void deleteReason(final Long id) {
		final Razon reason = stateService.findRazonById(id);
		if (reason != null) {
			stateService.deleteRazon(reason);
		}
	}

	public void updateState(final Estado state) {
		stateService.updateEstado(state);
	}

	public Tipo findTipoByName(final String name) {
		return tipoService.findTipoByName(name);
	}

	public Long saveState(final Estado state) {
		return stateService.saveEstado(state);
	}

	public void saveTipo(final Tipo tipo) {
		tipoService.saveTipo(tipo);
	}

	public ApplicationParameter findParameter(final String code) {
		return applicationService.findParameterByCode(code);
	}

	public Dispositivo findDispById(final Long id) {
		return dispositivoService.findById(id);
	}

	public void updateDisp(final Dispositivo disp) {
		dispositivoService.updateDisp(disp);
	}

	public Long saveDisp(final Dispositivo disp) {
		return dispositivoService.saveDispositivo(disp);
	}

	public List<Dispositivo> findAllDevices() {
		return dispositivoService.findAll();
	}

	public void deleteDevice(final Long id) {
		final Dispositivo device = dispositivoService.findById(id);
		if (device != null) {
			dispositivoService.deleteDipositivo(device);
		}
	}

	public void saveGps(final GpsDispositivo gps) {
		dispositivoService.saveGps(gps);
	}

	public List<GpsDispositivo> findAllGps() {
		return dispositivoService.findAllGps();
	}

	public List<GpsDispositivo> findAllGpsParam(final Long id) {
		return dispositivoService.findAllGpsParam(id);
	}

	public GpsDispositivo findPreviousGpsLocation(final Long id) {
		return dispositivoService.findGpsById(id);
	}

	public Equipo findEquipoById(final Long id) {
		return equipoService.findById(id);
	}

	public Equipo findEquipoByCode(final String code) {
		return equipoService.findByCode(code);
	}

	public Dispositivo findDispByEquiAssigned(final Equipo equipo) {
		final Long equiId = equipo.getId();
		return dispositivoService.findDispByEquiAssigned(equiId);
	}
	
	public Equipo findEquipoByDispAssigned(final Dispositivo disp) {
		final Long dispId = disp.getId();
		return dispositivoService.findEquiByDispAssigned(dispId);
	}

	public List<EquipmentDetail> findAllEquip() {
		return equipoService.findAllEquipments();
	}

	public Long saveEquipment(final Equipo equipo) {
		return equipoService.saveEquipo(equipo);
	}

	public void updateEquipment(final Equipo equipo) {
		equipoService.updateEquipo(equipo);
	}

	public void deleteEquipement(final Long id) {
		final Equipo equipo = equipoService.findById(id);
		List<EquipoDispositivo> disps = equipo.getDispositivos();
		if (!disps.isEmpty()) {
			for (EquipoDispositivo item : disps) {
				equipoService.deleteEquipoDisp(item);
			}
		}
		equipoService.deleteEquipo(equipo);
	}

	public List<Operador> findAllOpertors() {
		return operatorService.findAllOperadores();
	}

	public void deleteOperator(final Long id) {
		Operador oper = operatorService.findOperadorById(id);
		if (oper != null) {
			operatorService.deleteOperador(oper);
		}
	}

	public Usuario findUserByName(final String userAccount) {
		return userService.findUserByName(userAccount);
	}

	public List<Usuario> findAllUsers() {
		return userService.findAll();
	}

	public Usuario findUserById(final Long id) {
		return userService.findById(id);
	}

	public void updateUser(final Usuario user) {
		userService.updateUser(user);
	}

	public void deleteUser(final Long id) {
		final Usuario user = userService.findById(id);
		if (user != null) {
			userService.deleteUser(user);
		}
	}

	public Long saveUser(Usuario user) {
		return userService.saveUser(user);
	}

	public Operador findOperatorById(final Long id) {
		return operatorService.findOperadorById(id);
	}

	public void updateOperator(final Operador oper) {
		operatorService.updateOperador(oper);
	}

	public Long saveOperator(final Operador oper) {
		return operatorService.saveOperador(oper);
	}

	public List<Turno> findAllTurnos() {
		return turnoService.findAll();
	}

	public void deleteTurno(final Long id) {
		final Turno turno = turnoService.findById(id);
		if (turno != null) {
			turnoService.deleteTurno(turno);
		}
	}

	public Turno findTurnoById(final Long id) {
		return turnoService.findById(id);
	}

	public void updateTurno(final Turno turno) {
		turnoService.updateTurno(turno);
	}

	public Long saveTurno(final Turno turno) {
		return turnoService.saveTurno(turno);
	}

	public void setStateService(StateService stateService) {
		this.stateService = stateService;
	}

	public void setTipoService(TipoService tipoService) {
		this.tipoService = tipoService;
	}

	public void setApplicationService(ApplicationService applicationService) {
		this.applicationService = applicationService;
	}

	public void setDispositivoService(DispositivoService dispositivoService) {
		this.dispositivoService = dispositivoService;
	}

	public void setEquipoService(EquipoService equipoService) {
		this.equipoService = equipoService;
	}

	public void setOperatorService(OperatorService operatorService) {
		this.operatorService = operatorService;
	}

	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	public void setTurnoService(TurnoService turnoService) {
		this.turnoService = turnoService;
	}

	public List<EquiStateView> findAllEquiStateByEqui(String equiCode) {
		final Equipo equipo = equipoService.findByCode(equiCode);
		final Long equiId = equipo.getId();
		List<EstadoEquipo> allEquiState = stateService.findAllEstEquiByEquipo(equiId);
		List<EquiStateView> equiViewList = new ArrayList<>();
		for (EstadoEquipo item : allEquiState) {
			EquiStateView view = new EquiStateView();
			view.setId(item.getId());
			view.setEquipo(item.getEquipo().getCodigoEquipo());
			view.setEstado(item.getEstado().getEstado());
			view.setRazon(item.getRazon().getRazon());
			view.setComentario(item.getComentario());
			view.setFechaInicio(item.getFechaIni());
			view.setFechaFin(item.getFechaFin());
			equiViewList.add(view);
		}
		return equiViewList;
	}

	public Long saveEquipmentState(final EstadoEquipo item) {
		return stateService.saveEquiState(item);
	}

	public EstadoEquipo findLastEstEquiByEquipo(Long equiId) {
		return stateService.findLastEstEquiByEquipo(equiId);
	}

	public EquiStateView findLastEstateEquiDetail(String code) {
		final Equipo equipo = equipoService.findByCode(code);
		final Long equiId = equipo.getId();
		EstadoEquipo equiState = stateService.findLastEstEquiByEquipo(equiId);
		EquiStateView view = new EquiStateView();
		if (equiState != null) {
			view.setId(equiState.getId());
			view.setEquipo(equiState.getEquipo().getCodigoEquipo());
			view.setEstado(equiState.getEstado().getEstado());
			view.setRazon(equiState.getRazon().getRazon());
			view.setComentario(equiState.getComentario());
			view.setFechaInicio(equiState.getFechaIni());
			view.setFechaFin(equiState.getFechaFin());
		}
		return view;
	}
	
	public Long saveAction(final Acciones accion) {
		return equipoService.saveAction(accion);
	}
	
	public List<Acciones> findAllActions(){
		return equipoService.findAllActions();
	}
	
	public List<EquipoDispositivo> findAllEquiDisp(){
		return equipoService.findAllEquiDisp();
	}
	
	public Dispositivo findDispByIp(String ip) {
		return dispositivoService.findByIp(ip);
	}
	
	public Long saveEquiDisp(EquipoDispositivo equiDisp) {
		return dispositivoService.saveEquiDisp(equiDisp);
	}
}