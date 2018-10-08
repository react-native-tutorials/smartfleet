package com.smartlife.smartfleet.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Image;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import com.smartlife.smartfleet.domain.Dispositivo;
import com.smartlife.smartfleet.domain.Equipo;
import com.smartlife.smartfleet.domain.EquipoDispositivo;
import com.smartlife.smartfleet.domain.view.DispAssignView;
import com.smartlife.smartfleet.dto.EquipmentDetail;
import com.smartlife.smartfleet.facade.SmartFacade;
import com.smartlife.smartfleet.ui.common.SortedComboBoxModel;
import com.smartlife.smartfleet.utils.Constants;

@SuppressWarnings({ "unchecked", "rawtypes" })
public class SmartAssignDisp extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	static final String FRM_ASIG_TITLE = "SmartFleet ~ Asignar Dispositivos";
	SmartFacade facade;
	SmartMainFrame parent;
	SortedComboBoxModel equipmentComboModel;

	private JComboBox cboEquipos;
	private JComboBox cboDisp;
	private JCheckBox chckbxActivo;
	private JButton okButton;
	private JScrollPane scrollPane;
	private DispAssignTableModel model;
	private JTable tblData;
	private JButton cancelButton;

	/**
	 * Create the dialog.
	 */
	public SmartAssignDisp(SmartMainFrame mainFrame) {
		this.parent = mainFrame;
		this.facade = (SmartFacade) mainFrame.context.getBean("smartFacade");

		setSize(694, 548);
		final int x = mainFrame.getX() + mainFrame.getWidth();
		final int y = mainFrame.getY() + mainFrame.getHeight() - getHeight();
		setLocation(x, y);
		final Image img = new ImageIcon(SmartMainFrame.class.getResource(Constants.ICON_PATH)).getImage();
		setIconImage(img);
		setTitle(FRM_ASIG_TITLE);

		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBackground(Color.BLACK);
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{
			JLabel lblEquipos = new JLabel("Equipos:");
			lblEquipos.setForeground(Color.WHITE);
			lblEquipos.setHorizontalAlignment(SwingConstants.RIGHT);
			lblEquipos.setBounds(33, 30, 95, 20);
			contentPanel.add(lblEquipos);
		}
		{
			JLabel lblDispositivo = new JLabel("Dispositivo:");
			lblDispositivo.setForeground(Color.WHITE);
			lblDispositivo.setHorizontalAlignment(SwingConstants.RIGHT);
			lblDispositivo.setBounds(33, 68, 95, 20);
			contentPanel.add(lblDispositivo);
		}
		{
			contentPanel.add(getCboEquipos());
		}
		{
			contentPanel.add(getCboDisp());
		}
		contentPanel.add(getChckbxActivo());
		{
			contentPanel.add(getOkButton());
			getRootPane().setDefaultButton(getOkButton());
		}
		contentPanel.add(getScrollPane());
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setBackground(Color.BLACK);
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				buttonPane.add(getCancelButton());
			}
		}
	}

	public JComboBox getCboEquipos() {
		if (cboEquipos == null) {
			List<EquipmentDetail> allEquipos = facade.findAllEquip();
			List<String> allEquipoCode = new ArrayList<>();
			for (EquipmentDetail detail : allEquipos) {
				allEquipoCode.add(detail.getCode());
			}
			// EquipmentDetail[] tipoArray = allEquipos.stream().map(EquipmentDetail ::
			// new).toArray(EquipmentDetail[] :: new);
			String[] allEquipoArray = allEquipoCode.stream().map(String::new).toArray(String[]::new);
			equipmentComboModel = new SortedComboBoxModel(allEquipoArray);
			cboEquipos = new JComboBox(equipmentComboModel);
			cboEquipos.setBounds(143, 27, 200, 26);
		}
		return cboEquipos;
	}

	public JComboBox getCboDisp() {
		if (cboDisp == null) {
			List<Dispositivo> allDisp = facade.findAllDevices();
			List<String> allDispStr = new ArrayList<>();
			for(Dispositivo disp : allDisp) {
				allDispStr.add(disp.getIpDispositivo());
			}
			String[] allDispStrArray = allDispStr.stream().map(String::new).toArray(String[]::new);
			SortedComboBoxModel model = new SortedComboBoxModel(allDispStrArray);
			cboDisp = new JComboBox(model);
			cboDisp.setBounds(143, 65, 200, 26);
		}
		return cboDisp;
	}

	public JCheckBox getChckbxActivo() {
		if (chckbxActivo == null) {
			chckbxActivo = new JCheckBox("Activo");
			chckbxActivo.setBackground(Color.BLACK);
			chckbxActivo.setForeground(Color.WHITE);
			chckbxActivo.setBounds(143, 107, 139, 29);
		}
		return chckbxActivo;
	}

	public JScrollPane getScrollPane() {
		if (scrollPane == null) {
			scrollPane = new JScrollPane();
			scrollPane.setBounds(28, 206, 629, 231);
			scrollPane.setViewportView(getTblData());
			scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
			scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		}
		return scrollPane;
	}

	public JTable getTblData() {
		if (tblData == null) {
			List<EquipoDispositivo> allEquiDispList = facade.findAllEquiDisp();
			List<DispAssignView> allView = dispAsignToView(allEquiDispList);
			model = new DispAssignTableModel(allView);
			tblData = new JTable(model);
		}
		return tblData;
	}

	private List<DispAssignView> dispAsignToView(List<EquipoDispositivo> allEquiDispList) {
		List<DispAssignView> allView = new ArrayList<>();
		for(EquipoDispositivo item : allEquiDispList) {
			DispAssignView view = new DispAssignView();
			view.setAssignId(item.getId());
			view.setEquiCode(item.getEquipo().getCodigoEquipo());
			view.setDispIp(item.getDispositivo().getIpDispositivo());
			view.setFechaAsig(item.getFechaAsig());
			view.setActive(item.getActivo());
			allView.add(view);
		}
		return allView;
	}

	public JButton getOkButton() {
		if (okButton == null) {
			okButton = new JButton("Grabar");
			okButton.setBounds(143, 148, 121, 29);
			okButton.setActionCommand("OK");
			okButton.addActionListener(e -> saveData());
		}
		return okButton;
	}

	public void saveData() {
		Date fechaAsig = new Date();
		String codeStr = getCboEquipos().getSelectedItem().toString();
		Equipo equipo = facade.findEquipoByCode(codeStr);
		String ipStr = getCboDisp().getSelectedItem().toString();
		Dispositivo disp = facade.findDispByIp(ipStr);
		String active = "Y";
		EquipoDispositivo newItem = new EquipoDispositivo();
		newItem.setActivo(active);
		newItem.setEquipo(equipo);
		newItem.setDispositivo(disp);
		newItem.setFechaAsig(fechaAsig);
		Long newId = facade.saveEquiDisp(newItem);
		DispAssignView newView = new DispAssignView();
		newView.setAssignId(newId);
		newView.setFechaAsig(newItem.getFechaAsig());
		newView.setEquiCode(newItem.getEquipo().getCodigoEquipo());
		newView.setDispIp(newItem.getDispositivo().getIpDispositivo());
		newView.setActive(newItem.getActivo());
		DispAssignTableModel model = (DispAssignTableModel)getTblData().getModel();
		model.addRow(newView);
	}

	public JButton getCancelButton() {
		if (cancelButton == null) {
			cancelButton = new JButton("Salir");
			cancelButton.setActionCommand("Cancel");
			cancelButton.addActionListener(e -> closeFrame());
		}
		return cancelButton;
	}

	public void closeFrame() {
		parent.setVisible(Boolean.TRUE);
		this.dispose();
	}
}
