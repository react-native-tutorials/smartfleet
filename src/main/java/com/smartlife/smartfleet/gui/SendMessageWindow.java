package com.smartlife.smartfleet.gui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Image;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextPane;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import com.smartlife.smartfleet.domain.Acciones;
import com.smartlife.smartfleet.domain.Dispositivo;
import com.smartlife.smartfleet.domain.Equipo;
import com.smartlife.smartfleet.domain.view.ActionView;
import com.smartlife.smartfleet.dto.EquipmentDetail;
import com.smartlife.smartfleet.facade.SmartFacade;
import com.smartlife.smartfleet.ui.common.SortedComboBoxModel;
import com.smartlife.smartfleet.utils.Constants;
import java.awt.Color;
import javax.swing.JTable;
import javax.swing.JTextArea;

@SuppressWarnings({ "unchecked", "rawtypes" })
public class SendMessageWindow extends JDialog {

	private static final long serialVersionUID = -3876351077320691727L;
	static final String FRM_ASIG_TITLE = "SmartFleet ~ Enviar Acciones";
	private final JPanel contentPanel = new JPanel();
	SmartFacade facade;
	SmartMainFrame parent;
	private JButton okButton;
	private JButton cancelButton;
	private JComboBox cboEquipo;
	SortedComboBoxModel equipmentComboModel;
	private JScrollPane mainPane;
	private JScrollPane dataPane;
	private JTable tblData;
	private ActionViewTableModel model;
	private JTextPane txtAccion;
	private JTextArea textArea;

	/**
	 * Create the dialog.
	 */
	public SendMessageWindow(SmartMainFrame mainFrame) {
		this.parent = mainFrame;
		this.facade = (SmartFacade) mainFrame.context.getBean("smartFacade");

		setSize(570, 565);
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
			JPanel buttonPane = new JPanel();
			buttonPane.setBackground(Color.BLACK);
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				getRootPane().setDefaultButton(okButton);
			}

			JLabel lblEquipo = new JLabel("Equipo:");
			lblEquipo.setForeground(Color.WHITE);
			lblEquipo.setHorizontalAlignment(SwingConstants.RIGHT);
			lblEquipo.setBounds(15, 39, 69, 20);
			contentPanel.add(lblEquipo);
			contentPanel.add(getCboEquipo());

			JLabel lblAccion = new JLabel("Accion:");
			lblAccion.setForeground(Color.WHITE);
			lblAccion.setHorizontalAlignment(SwingConstants.RIGHT);
			lblAccion.setBounds(15, 75, 69, 20);
			contentPanel.add(lblAccion);

			contentPanel.add(getMainPane());
			contentPanel.add(getOkButton());

			contentPanel.add(getDataPane());
			{
				buttonPane.add(getCancelButton());
			}
		}
	}

	public JComboBox getCboEquipo() {
		if (cboEquipo == null) {
			List<EquipmentDetail> allEquipos = facade.findAllEquip();
			List<String> allEquipoCode = new ArrayList<>();
			for (EquipmentDetail detail : allEquipos) {
				allEquipoCode.add(detail.getCode());
			}
			String[] allEquipoArray = allEquipoCode.stream().map(String::new).toArray(String[]::new);
			equipmentComboModel = new SortedComboBoxModel(allEquipoArray);
			cboEquipo = new JComboBox(equipmentComboModel);
			cboEquipo.setBounds(94, 36, 206, 26);
		}
		return cboEquipo;
	}

	public JScrollPane getMainPane() {
		if (mainPane == null) {
			mainPane = new JScrollPane();
			mainPane.setBounds(94, 78, 320, 120);
			mainPane.setViewportView(getTxtAccion());
			mainPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
			mainPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		}
		return mainPane;
	}

	public JScrollPane getDataPane() {
		if (dataPane == null) {
			dataPane = new JScrollPane();
			dataPane.setViewportView(getTblData());
			dataPane.setBounds(28, 220, 492, 234);
			dataPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
			dataPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		}
		return dataPane;
	}

	public JTable getTblData() {
		if (tblData == null) {
			List<Acciones> allAcctions = facade.findAllActions();
			List<ActionView> allActionView = actionsToView(allAcctions);
			model = new ActionViewTableModel(allActionView);
			tblData = new JTable(model);
		}
		return tblData;
	}

	private List<ActionView> actionsToView(final List<Acciones> allAcctions) {
		List<ActionView> allView = new ArrayList<>();
		for (Acciones act : allAcctions) {
			ActionView view = new ActionView();
			view.setActionId(act.getId());
			view.setCodEqui(act.getEquipo().getCodigoEquipo());
			view.setDateSent(act.getFechaAsig());
			view.setMessage(act.getAccion());
			allView.add(view);
		}
		return allView;
	}

	public JTextPane getTxtAccion() {
		if (txtAccion == null) {
			txtAccion = new JTextPane();
			txtAccion.setBounds(94, 78, 320, 120);
		}
		return txtAccion;
	}

	public JButton getOkButton() {
		if (okButton == null) {
			okButton = new JButton("Enviar");
			okButton.setBounds(443, 172, 77, 29);
			okButton.setActionCommand("OK");
			okButton.addActionListener(e -> sendAccion());
		}
		return okButton;
	}

	public void sendAccion() {
		final String accion = getTxtAccion().getText().trim();
		final String selEqui = getCboEquipo().getSelectedItem().toString();
		final Equipo equipo = facade.findEquipoByCode(selEqui);
		final Dispositivo dispAssig = facade.findDispByEquiAssigned(equipo);
		final String ipDisp = dispAssig.getIpDispositivo();
		final String portDisp = dispAssig.getPortDisp();
		Acciones action = new Acciones();
		action.setEquipo(equipo);
		action.setFechaAsig(new Date());
		action.setAccion(accion);
		Boolean flag = sendMessage(ipDisp, portDisp, accion);
		if (flag) {
			parent.workGUI.getTxtMessages().append("\nMessage sent to: " + selEqui);
			Long id = facade.saveAction(action);
			ActionView view = new ActionView();
			view.setActionId(id);
			view.setCodEqui(selEqui);
			view.setDateSent(action.getFechaAsig());
			view.setMessage(action.getAccion());
			ActionViewTableModel model = (ActionViewTableModel) getTblData().getModel();
			model.addRow(view);
			JOptionPane.showMessageDialog(this, "Message sent correctly", Constants.MSG_TITLE,
					JOptionPane.INFORMATION_MESSAGE);
		}
	}

	public Boolean sendMessage(final String ip, final String port, final String message) {
		Boolean flag = Boolean.FALSE;
		try (Socket socket = new Socket(ip, Integer.parseInt(port));
				ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());) {
			oos.writeObject(message);
			flag = Boolean.TRUE;
		} catch (IOException e) {
			JOptionPane.showMessageDialog(this, "Could not connect to: " + ip + ":" + port, Constants.MSG_TITLE,
					JOptionPane.ERROR_MESSAGE);
		}
		return flag;
	}

	public JButton getCancelButton() {
		if (cancelButton == null) {
			cancelButton = new JButton("Cancel");
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
