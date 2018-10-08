package com.smartlife.smartfleet.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.smartlife.smartfleet.domain.Estado;
import com.smartlife.smartfleet.domain.Razon;
import com.smartlife.smartfleet.domain.common.Tipo;
import com.smartlife.smartfleet.dto.StateDetail;
import com.smartlife.smartfleet.facade.SmartFacade;
import com.smartlife.smartfleet.ui.common.SortedComboBoxModel;
import com.smartlife.smartfleet.utils.Constants;

/**
 * @author Marius-Iulian Grigoras
 *
 */
public class EditStateUI extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6760348649916628264L;
	protected final Log logger = LogFactory.getLog(getClass());
	static final String OK_CMD = "OK";
	static final String CANCEL_CMD = "CANCEL";
	static final String COLOR_CMD = "COLOR_CMD";
	private final JPanel contentPanel = new JPanel();
	private JButton okButton;
	private JButton cancelButton;
	private JTextField txtEstado;
	private JTextField txtRazon;
	private JComboBox<String> cboCategory;
	private SortedComboBoxModel categComboModel;
	Boolean isNew = Boolean.FALSE;
	StateDetail stateDetail;
	StatePanel statePanel;
	SmartFacade facade;
	EditStateActionListener listener;
	private JButton btnColor;
	private JLabel lblColorvalue;
	Integer oldColor;
	/**
	 * Create the dialog.
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public EditStateUI(StateDetail det_, StatePanel pane_) {
		this.stateDetail = det_;
		this.statePanel = pane_;
		this.listener = new EditStateActionListener(this);
		facade = statePanel.getSmartFacade();
		if (det_.getId() == null) {
			isNew = Boolean.TRUE;
		}
		setBounds(100, 100, 554, 257);
		setTitle("Nuevo/Editar Estado/Razon");
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPanel.setBackground(Color.BLACK);
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		GridBagLayout gbl_contentPanel = new GridBagLayout();
		gbl_contentPanel.columnWidths = new int[]{0, 0, 0, 0};
		gbl_contentPanel.rowHeights = new int[]{0, 0, 0, 0, 0, 0};
		gbl_contentPanel.columnWeights = new double[]{0.0, 0.0, 1.0, Double.MIN_VALUE};
		gbl_contentPanel.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		contentPanel.setLayout(gbl_contentPanel);
		{
			JLabel lblTippEstado = new JLabel("Tipo estado:");
			lblTippEstado.setBackground(Color.BLACK);
			lblTippEstado.setForeground(Color.WHITE);
			GridBagConstraints gbc_lblTippEstado = new GridBagConstraints();
			gbc_lblTippEstado.insets = new Insets(0, 0, 5, 5);
			gbc_lblTippEstado.anchor = GridBagConstraints.EAST;
			gbc_lblTippEstado.gridx = 1;
			gbc_lblTippEstado.gridy = 1;
			contentPanel.add(lblTippEstado, gbc_lblTippEstado);
		}
		{
			
			List<Tipo> allTipo = facade.findTipoByCategory("TIPO_ESTADO");
			List<String> tipoNames = new ArrayList<>();
			for(Tipo tipo : allTipo) {
				tipoNames.add(tipo.getTipo());
			}
			
			String[] tipoArray = tipoNames.stream().map(String :: new).toArray(String[] :: new);
			
			categComboModel = new SortedComboBoxModel(tipoArray);
			cboCategory = new JComboBox(categComboModel);
			if (!isNew) {
				final String categ = stateDetail.getCategoria();
				cboCategory.setSelectedItem(categ);
			}
			
			GridBagConstraints gbc_comboBox = new GridBagConstraints();
			gbc_comboBox.insets = new Insets(0, 0, 5, 0);
			gbc_comboBox.fill = GridBagConstraints.HORIZONTAL;
			gbc_comboBox.gridx = 2;
			gbc_comboBox.gridy = 1;
			contentPanel.add(cboCategory, gbc_comboBox);
		}
		{
			JLabel lblEstado = new JLabel("Estado:");
			lblEstado.setBackground(Color.BLACK);
			lblEstado.setForeground(Color.WHITE);
			GridBagConstraints gbc_lblEstado = new GridBagConstraints();
			gbc_lblEstado.insets = new Insets(0, 0, 5, 5);
			gbc_lblEstado.anchor = GridBagConstraints.EAST;
			gbc_lblEstado.gridx = 1;
			gbc_lblEstado.gridy = 2;
			contentPanel.add(lblEstado, gbc_lblEstado);
		}
		{
			txtEstado = new JTextField();
			if (!isNew) {
				final String estado = stateDetail.getEstado();
				txtEstado.setText(estado);
			}
			GridBagConstraints gbc_txtEstado = new GridBagConstraints();
			gbc_txtEstado.insets = new Insets(0, 0, 5, 0);
			gbc_txtEstado.fill = GridBagConstraints.HORIZONTAL;
			gbc_txtEstado.gridx = 2;
			gbc_txtEstado.gridy = 2;
			contentPanel.add(txtEstado, gbc_txtEstado);
			txtEstado.setColumns(10);
			txtEstado.addKeyListener(listener);
		}
		{
			btnColor = new JButton("Color");
			btnColor.setActionCommand(COLOR_CMD);
			btnColor.addActionListener(listener);
			GridBagConstraints gbc_btnColor = new GridBagConstraints();
			gbc_btnColor.anchor = GridBagConstraints.EAST;
			gbc_btnColor.insets = new Insets(0, 0, 5, 5);
			gbc_btnColor.gridx = 1;
			gbc_btnColor.gridy = 3;
			contentPanel.add(btnColor, gbc_btnColor);
		}
		{
			lblColorvalue = new JLabel("");
			lblColorvalue.setOpaque(Boolean.TRUE);
			lblColorvalue.setForeground(Color.WHITE);
			if (!isNew) {
				oldColor = stateDetail.getColor();
				lblColorvalue.setBackground(new Color(oldColor));
			}
			GridBagConstraints gbc_lblColorvalue = new GridBagConstraints();
			gbc_lblColorvalue.fill = GridBagConstraints.BOTH;
			gbc_lblColorvalue.anchor = GridBagConstraints.WEST;
			gbc_lblColorvalue.insets = new Insets(0, 0, 5, 0);
			gbc_lblColorvalue.gridx = 2;
			gbc_lblColorvalue.gridy = 3;
			contentPanel.add(lblColorvalue, gbc_lblColorvalue);
		}
		{
			JLabel lblRazon = new JLabel("Razon:");
			lblRazon.setBackground(Color.BLACK);
			lblRazon.setForeground(Color.WHITE);
			GridBagConstraints gbc_lblRazon = new GridBagConstraints();
			gbc_lblRazon.insets = new Insets(0, 0, 0, 5);
			gbc_lblRazon.anchor = GridBagConstraints.EAST;
			gbc_lblRazon.gridx = 1;
			gbc_lblRazon.gridy = 4;
			contentPanel.add(lblRazon, gbc_lblRazon);
		}
		{
			txtRazon = new JTextField();
			if (!isNew) {
				String razon = stateDetail.getRazon();
				txtRazon.setText(razon);
			}
			
			GridBagConstraints gbc_txtRazon = new GridBagConstraints();
			gbc_txtRazon.fill = GridBagConstraints.HORIZONTAL;
			gbc_txtRazon.gridx = 2;
			gbc_txtRazon.gridy = 4;
			contentPanel.add(txtRazon, gbc_txtRazon);
			txtRazon.setColumns(10);
			txtRazon.addKeyListener(listener);
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setBackground(Color.BLACK);
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				okButton = new JButton("Guardar");
				okButton.setActionCommand(OK_CMD);
				okButton.addActionListener(listener);
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				cancelButton = new JButton("Cancelar");
				cancelButton.setActionCommand(CANCEL_CMD);
				cancelButton.addActionListener(listener);
				buttonPane.add(cancelButton);
			}
		}
	}
	
	public void disposeFrame() {
		final int response = JOptionPane.showConfirmDialog(this, "Are you sure you want to discard changes?",
				Constants.MSG_TITLE, JOptionPane.OK_CANCEL_OPTION);
		if (JOptionPane.OK_OPTION == response) {
			dispose();
		}
	}
	
	@SuppressWarnings("static-access")
	public void saveItem() {
		final int response = JOptionPane.showConfirmDialog(this, "Are you sure you want to save changes?", Constants.MSG_TITLE,
				JOptionPane.OK_CANCEL_OPTION);
		if (JOptionPane.OK_OPTION == response) {
			final String categoria = (String) cboCategory.getSelectedItem();
			Tipo tipo = facade.findTipoByName(categoria);
			final String state = txtEstado.getText().trim();
			final String razon = txtRazon.getText().trim();
			final Integer color = lblColorvalue.getBackground().getRGB();
			if(!isNew) {
				final Long detId = stateDetail.getId();
				final String detStatusName = stateDetail.getEstado();
				Estado existsEstado = facade.findState(detStatusName);
				existsEstado.setCategoria(tipo);
				existsEstado.setEstado(state);
				existsEstado.setColor(color);
				Razon existReason = facade.findReasonById(detId);
				existReason.setRazon(razon);
				existReason.setEstado(existsEstado);
				existsEstado.getRazones().add(existReason);
				facade.updateState(existsEstado);
				
				StateTableModel tableModel = (StateTableModel)statePanel.getStateTable().getModel();
				final int rowIndex = statePanel.getStateTable().getSelectedRow();
				tableModel.setValueAt(categoria, rowIndex, 1);
				tableModel.setValueAt(state, rowIndex, 2);
				tableModel.setValueAt(color, rowIndex, 3);
				tableModel.setValueAt(razon, rowIndex, 4);
				
				int rowCount = statePanel.getStateTable().getRowCount();
				for(int item=0;item<rowCount;item++) {
					Integer colorValue = (Integer)tableModel.getValueAt(item, 3);
					if(oldColor.equals(colorValue)) {
						tableModel.setValueAt(color, item, 3);
					}
				}
				tableModel.fireTableRowsUpdated(rowIndex, rowIndex);
				tableModel.fireTableDataChanged();
			}else {
				Estado estado = new Estado();
				estado.setCategoria(tipo);
				estado.setEstado(state);
				estado.setColor(color);
				Razon reazon = new Razon();
				reazon.setEstado(estado);
				reazon.setRazon(razon);
				List<Razon> reasons = new ArrayList<>();
				reasons.add(reazon);
				estado.setRazones(reasons);
				facade.saveState(estado);
				Long newReasonid = facade.findReasonByName(razon).getId();
				
				stateDetail.setId(newReasonid);
				stateDetail.setCategoria(categoria);
				stateDetail.setEstado(state);
				stateDetail.setColor(color);
				stateDetail.setRazon(razon);
				statePanel.noItems++;
				statePanel.getLblNoItems().setText(Constants.LBL_ITEMS_TITLE+statePanel.noItems);
				((StateTableModel)statePanel.getStateTable().getModel()).addRow(stateDetail);
			}
			dispose();
		}
	}

	public void chooseColor() {
		final Color color = JColorChooser.showDialog(this, Constants.MSG_TITLE, Color.WHITE);
		lblColorvalue.setBackground(color);
		btnColor.setBackground(color);
	}
}
