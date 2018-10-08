package com.smartlife.smartfleet.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Image;
import java.awt.event.ItemEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import com.smartlife.smartfleet.domain.Equipo;
import com.smartlife.smartfleet.domain.Estado;
import com.smartlife.smartfleet.domain.EstadoEquipo;
import com.smartlife.smartfleet.domain.Razon;
import com.smartlife.smartfleet.domain.view.EquiStateView;
import com.smartlife.smartfleet.dto.EquipmentDetail;
import com.smartlife.smartfleet.facade.SmartFacade;
import com.smartlife.smartfleet.ui.common.SortedComboBoxModel;
import com.smartlife.smartfleet.utils.Constants;

@SuppressWarnings({ "unchecked", "rawtypes" })
public class SmartAsigStateWindow extends JDialog {

	private static final long serialVersionUID = -4989321031251941824L;
	static final String FRM_ASIG_TITLE = "SmartFleet ~ Asignar Estados";
	private final JPanel contentPanel = new JPanel();

	// private AbstractRefreshableApplicationContext context;
	SmartFacade facade;
	SmartMainFrame parent;
	private JTextArea txtComment;
	private JScrollPane paneComment;

	private JComboBox cboRazon;
	private JComboBox cboEstado;
	private JComboBox cboEquipos;
	SortedComboBoxModel equipmentComboModel;
	SortedComboBoxModel reasonComboModel;
	SortedComboBoxModel stateComboModel;
	private JTable tblData;
	private JScrollPane paneData;
	private JButton btnSave;
	private JPanel buttonPane;
	private JButton cancelButton;

	/**
	 * Create the dialog.
	 */
	public SmartAsigStateWindow(SmartMainFrame mainFrame) {
		this.parent = mainFrame;
		// this.context = mainFrame.context;
		this.facade = (SmartFacade) mainFrame.context.getBean("smartFacade");

		setSize(938, 548);
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

		JLabel lblEquipo = new JLabel("Equipo:");
		lblEquipo.setForeground(Color.WHITE);
		lblEquipo.setHorizontalAlignment(SwingConstants.RIGHT);
		lblEquipo.setBounds(15, 67, 99, 20);
		contentPanel.add(lblEquipo);

		JLabel lblEstado = new JLabel("Estado:");
		lblEstado.setForeground(Color.WHITE);
		lblEstado.setHorizontalAlignment(SwingConstants.RIGHT);
		lblEstado.setBounds(15, 114, 99, 20);
		contentPanel.add(lblEstado);

		JLabel lblRazon = new JLabel("Razon:");
		lblRazon.setForeground(Color.WHITE);
		lblRazon.setHorizontalAlignment(SwingConstants.RIGHT);
		lblRazon.setBounds(15, 159, 99, 20);
		contentPanel.add(lblRazon);
		contentPanel.add(getCboEquipos());
		contentPanel.add(getCboEstado());
		contentPanel.add(getCboRazon());

		JLabel lblComentario = new JLabel("Comentario:");
		lblComentario.setForeground(Color.WHITE);
		lblComentario.setHorizontalAlignment(SwingConstants.RIGHT);
		lblComentario.setBounds(15, 195, 99, 20);
		contentPanel.add(lblComentario);
		contentPanel.add(getPaneComment());
		{
			contentPanel.add(getBtnSave());
			getRootPane().setDefaultButton(getBtnSave());
		}
		contentPanel.add(getPaneData());

		{
			getContentPane().add(getButtonPane(), BorderLayout.SOUTH);
		}

		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				parent.setVisible(Boolean.TRUE);
			}
		});
	}

	public JScrollPane getPaneComment() {
		if (paneComment == null) {
			paneComment = new JScrollPane();
			paneComment.setBounds(115, 198, 224, 122);
			paneComment.setViewportView(getTxtComment());
		}
		return paneComment;
	}

	public JTextArea getTxtComment() {
		if (txtComment == null) {
			txtComment = new JTextArea();
			txtComment.setBounds(112, 332, 4, 22);
		}
		return txtComment;
	}

	public JComboBox getCboRazon() {
		if (cboRazon == null) {
			String state = getCboEstado().getSelectedItem().toString();
			reasonComboModel = obtainReasonModel(state);
			cboRazon = new JComboBox(reasonComboModel);
			cboRazon.setBounds(115, 156, 224, 26);
		}
		return cboRazon;
	}

	public SortedComboBoxModel obtainReasonModel(final String state) {
		List<Razon> allReasonByState = facade.findAllReasonByState(state);
		List<String> razonesStr = new ArrayList<>();
		if (!allReasonByState.isEmpty()) {
			for (Razon item : allReasonByState) {
				razonesStr.add(item.getRazon());
			}
		} else {
			razonesStr.add("Sin registros");
		}
		String[] allReasonArray = razonesStr.stream().map(String::new).toArray(String[]::new);
		SortedComboBoxModel reasonComboModel = new SortedComboBoxModel(allReasonArray);
		return reasonComboModel;
	}

	public JComboBox getCboEstado() {
		if (cboEstado == null) {
			List<Estado> allEstado = facade.findAllStates();
			List<String> allStateString = new ArrayList<>();
			for (Estado item : allEstado) {
				allStateString.add(item.getEstado());
			}
			String[] allStateArray = allStateString.stream().map(String::new).toArray(String[]::new);
			stateComboModel = new SortedComboBoxModel(allStateArray);
			cboEstado = new JComboBox(stateComboModel);
			cboEstado.setBounds(115, 114, 224, 26);
			cboEstado.addItemListener(e -> {
				if (ItemEvent.SELECTED == e.getStateChange()) {
					String stateItem = (String) e.getItem();
					reasonComboModel = obtainReasonModel(stateItem);
					getCboRazon().setModel(reasonComboModel);
				}

			});
		}
		return cboEstado;
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
			cboEquipos.setBounds(115, 64, 224, 26);
			cboEquipos.addItemListener(e->{
				if(ItemEvent.SELECTED==e.getStateChange()) {
					String chosenEqui = cboEquipos.getSelectedItem().toString();
					List<EquiStateView> lastStates = new ArrayList<>();
					EquiStateView allEquiStateView = facade.findLastEstateEquiDetail(chosenEqui);
					String state = allEquiStateView.getEstado();
					String razon = allEquiStateView.getRazon();
					String comment = allEquiStateView.getComentario();
					lastStates.add(allEquiStateView);
					EquiStateTabelModel model = new EquiStateTabelModel(lastStates);
					getTblData().setModel(model);
					
					getCboEstado().setSelectedItem(state);
					getCboRazon().setSelectedItem(razon);
					getTxtComment().setText(comment);
				}
			});
		}
		return cboEquipos;
	}

	public JScrollPane getPaneData() {
		if (paneData == null) {
			paneData = new JScrollPane();
			paneData.setBounds(354, 67, 547, 200);
			paneData.setViewportView(getTblData());
			paneData.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
			paneData.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		}
		return paneData;
	}

	public JTable getTblData() {
		if (tblData == null) {
			final String equiCode = (String) getCboEquipos().getSelectedItem();
			List<EquiStateView> allEquiStateView = facade.findAllEquiStateByEqui(equiCode);
			EquiStateTabelModel model = new EquiStateTabelModel(allEquiStateView);
			tblData = new JTable(model);
		}
		return tblData;
	}

	public JButton getBtnSave() {
		if (btnSave == null) {
			btnSave = new JButton("Asignar");
			btnSave.addActionListener(e -> saveAsignedData());
			btnSave.setBounds(221, 336, 115, 29);
			btnSave.setActionCommand("OK");
		}
		return btnSave;
	}

	protected void saveAsignedData() {
		final String equiCode = getCboEquipos().getSelectedItem().toString();
		final Equipo equipo = facade.findEquipoByCode(equiCode);
		final Long equiId = equipo.getId();
		final String stateCode = getCboEstado().getSelectedItem().toString();
		final Estado state = facade.findState(stateCode);
		final String razonCode = getCboRazon().getSelectedItem().toString();
		final Razon reason = facade.findReasonByName(razonCode);
		final String comentText = getTxtComment().getText().trim();

		EstadoEquipo lastState = facade.findLastEstEquiByEquipo(equiId);
		
		if (lastState != null) {
			final Long lastStateId = lastState.getId();
			final int rowCount = getTblData().getRowCount();
			EquiStateTabelModel model = (EquiStateTabelModel) getTblData().getModel();
			for(int i = 0; i<rowCount; i++) {
				Long value = (Long)model.getValueAt(i, model.findColumn("ID"));
				if(lastStateId.equals(value)) {
					model.setValueAt(new Date(), i, model.findColumn("FECHA FIN"));	
					model.fireTableRowsUpdated(i, i);
					model.fireTableDataChanged();
					break;
				} 
			}
			EstadoEquipo estadoEquipo = new EstadoEquipo();
			estadoEquipo.setEquipo(equipo);
			estadoEquipo.setEstado(state);
			estadoEquipo.setRazon(reason);
			estadoEquipo.setComentario(comentText);
			estadoEquipo.setFechaIni(new Date());
			Long newId = facade.saveEquipmentState(estadoEquipo);
			EquiStateView view = new EquiStateView();
			view.setId(newId);
			view.setEquipo(equiCode);
			view.setEstado(stateCode);
			view.setRazon(razonCode);
			view.setComentario(comentText);
			view.setFechaInicio(estadoEquipo.getFechaIni());
			model = (EquiStateTabelModel) getTblData().getModel();
			model.addRow(view);
		} else {

			EstadoEquipo estadoEquipo = new EstadoEquipo();
			estadoEquipo.setEquipo(equipo);
			estadoEquipo.setEstado(state);
			estadoEquipo.setRazon(reason);
			estadoEquipo.setComentario(comentText);
			estadoEquipo.setFechaIni(new Date());
			Long newId = facade.saveEquipmentState(estadoEquipo);
			EquiStateView view = new EquiStateView();
			view.setId(newId);
			view.setEquipo(equiCode);
			view.setEstado(stateCode);
			view.setRazon(razonCode);
			view.setComentario(comentText);
			view.setFechaInicio(estadoEquipo.getFechaIni());
			EquiStateTabelModel model = (EquiStateTabelModel) getTblData().getModel();
			model.addRow(view);
		}
	}


	public JPanel getButtonPane() {
		if (buttonPane == null) {
			buttonPane = new JPanel();
			buttonPane.setBackground(Color.BLACK);
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			{
				buttonPane.add(getCancelButton());
			}
		}
		return buttonPane;
	}

	public JButton getCancelButton() {
		if (cancelButton == null) {
			cancelButton = new JButton("Salir");
			cancelButton.addActionListener(e -> closeFrame());
			cancelButton.setActionCommand("Cancel");
		}
		return cancelButton;
	}

	public void closeFrame() {
		parent.setVisible(Boolean.TRUE);
		this.dispose();
	}

}
