/**
 * 
 */
package com.smartlife.smartfleet.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.Map;

import javax.swing.AbstractAction;
import javax.swing.ActionMap;
import javax.swing.InputMap;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JToolBar;
import javax.swing.KeyStroke;
import javax.swing.ListSelectionModel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.table.TableColumn;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.context.support.AbstractRefreshableApplicationContext;

import com.smartlife.smartfleet.dto.EquipmentDetail;
import com.smartlife.smartfleet.facade.SmartFacade;
import com.smartlife.smartfleet.utils.Constants;

/**
 * @author Marius-Iulian Grigoras
 *
 */
public class EquipmentPanel extends JPanel {

	/**
	 * generated serial version
	 */
	private static final long serialVersionUID = -6962072004664689497L;
	protected final Log logger = LogFactory.getLog(getClass());

	static final String NEW_CMD = "NEW_EQUIP";
	static final String ED_CMD = "EDIT_EQUIP";
	static final String SAV_CMD = "SAVE_EQUIP";
	static final String DEL_CMD = "DEL_EQUIP";
	static final String REF_CMD = "REF_CMD";

	private JToolBar tlbEquipos;
	private JButton btnNuevo;
	private JButton btnRefresh;
	private JButton btnBorrar;
	private JButton btnEditSave;

	private JScrollPane paneEquipo;
	private JTable tblEquipos;
	private EquipmentTableModel equiposTableModel;

	private JPanel statusPanel;
	private JLabel lblNoItems;
	private JLabel lblSearch;
	private JTextField txtSearch;
	private JCheckBox chkEnable;

	private AbstractRefreshableApplicationContext context;
	private SmartFacade facade;
	EquipmentActionListener listener;
	int noItems = 0;
	private TableRowSorter<TableModel> rowSorter;
	List<Map.Entry<Integer, Integer>> pairList = new ArrayList<>();

	public EquipmentPanel(AbstractRefreshableApplicationContext ctx) {
		this.listener = new EquipmentActionListener(this);
		this.context = ctx;
		this.facade = (SmartFacade) context.getBean("smartFacade");
		setBackground(Color.BLACK);
		setLayout(new BorderLayout(0, 0));
		add(getTlbEquipos(), BorderLayout.NORTH);
		add(getPaneEquipo(), BorderLayout.CENTER);
		add(getStatusPanel(), BorderLayout.SOUTH);
	}

	public JToolBar getTlbEquipos() {
		if (tlbEquipos == null) {
			tlbEquipos = new JToolBar();
			tlbEquipos.setRollover(true);
			tlbEquipos.setFloatable(false);
			tlbEquipos.setLayout(null);
			tlbEquipos.add(getBtnNuevo());
			tlbEquipos.add(getBtnEditSave());
			tlbEquipos.add(getBtnBorrar());
			tlbEquipos.add(getChkEnable());
			tlbEquipos.add(getLblSearch());
			tlbEquipos.add(getTxtSearch());
			tlbEquipos.setPreferredSize(new Dimension(this.getWidth(), 25));
			tlbEquipos.setBackground(Color.BLACK);
		}
		return tlbEquipos;
	}

	public JButton getBtnNuevo() {
		if (btnNuevo == null) {
			btnNuevo = new JButton("Nuevo");
			btnNuevo.setBounds(0, 0, 70, 20);
			btnNuevo.setBorder(new EmptyBorder(0, 0, 0, 0));
			btnNuevo.setForeground(Color.WHITE);
			btnNuevo.setBackground(Color.BLACK);
			btnNuevo.setRolloverEnabled(Boolean.TRUE);
			btnNuevo.setActionCommand(NEW_CMD);
			btnNuevo.addActionListener(listener);
		}
		return btnNuevo;
	}

	public JButton getBtnEditSave() {
		if (btnEditSave == null) {
			btnEditSave = new JButton("Editar");
			btnEditSave.setBounds(70, 0, 70, 20);
			btnEditSave.setBorder(new EmptyBorder(0, 0, 0, 0));
			btnEditSave.setBackground(Color.BLACK);
			btnEditSave.setForeground(Color.white);
			btnEditSave.setActionCommand(ED_CMD);
			btnEditSave.addActionListener(listener);
		}
		return btnEditSave;
	}

	public JButton getBtnRefresh() {
		if (btnRefresh == null) {
			btnRefresh = new JButton("Refresh");
			btnRefresh.setBounds(300, 0, 100, 20);
			btnRefresh.setBorder(new EmptyBorder(0, 0, 0, 0));
			btnRefresh.setForeground(Color.WHITE);
			btnRefresh.setBackground(Color.black);
			btnRefresh.setActionCommand(REF_CMD);
			btnRefresh.addActionListener(listener);
		}
		return btnRefresh;
	}

	public JButton getBtnBorrar() {
		if (btnBorrar == null) {
			btnBorrar = new JButton("Borrar");
			btnBorrar.setBounds(140, 0, 70, 20);
			btnBorrar.setBorder(new LineBorder(Color.BLACK));
			btnBorrar.setForeground(Color.WHITE);
			btnBorrar.setBackground(Color.black);
			btnBorrar.setActionCommand(DEL_CMD);
			btnBorrar.addActionListener(listener);
		}
		return btnBorrar;
	}

	public JCheckBox getChkEnable() {
		if (chkEnable == null) {
			chkEnable = new JCheckBox();
			chkEnable.setBounds(270, 0, 40, 20);
			chkEnable.setBackground(Color.BLACK);
			chkEnable.setSelected(Boolean.FALSE);
			chkEnable.addChangeListener(e->	enableSerach());
		}
		return chkEnable;
	}

	protected void enableSerach() {
		Boolean isSelected = getChkEnable().isSelected();
		getLblSearch().setEnabled(isSelected);
		getTxtSearch().setEnabled(isSelected);
	}

	public JLabel getLblSearch() {
		if (lblSearch == null) {
			lblSearch = new JLabel("Buscar:");
			lblSearch.setBounds(310,0, 50, 20);
			lblSearch.setForeground(Color.WHITE);
			lblSearch.setEnabled(Boolean.FALSE);
		}
		return lblSearch;
	}

	public JTextField getTxtSearch() {
		if (txtSearch == null) {
			txtSearch = new JTextField();
			logger.info(getLblSearch().getBounds().x+getLblSearch().getWidth()+20);
			txtSearch.setBounds(getLblSearch().getBounds().x+getLblSearch().getWidth()+20, 0, 200, 20);
			txtSearch.setColumns(20);
			txtSearch.addActionListener(e-> searchTable());
			txtSearch.addKeyListener(new KeyAdapter() {
				@Override
				public void keyReleased(KeyEvent e) {
					JTextField sourceField = (JTextField)e.getComponent();
					final int pos = sourceField.getCaretPosition();
					sourceField.setText(sourceField.getText().toUpperCase());
					sourceField.setCaretPosition(pos);
				}; 
			});
			txtSearch.setEnabled(Boolean.FALSE);
		}
		return txtSearch;
	}
	
	public JScrollPane getPaneEquipo() {
		if (paneEquipo == null) {
			paneEquipo = new JScrollPane();
			paneEquipo.setViewportView(getTblEquipos());
		}
		return paneEquipo;
	}

	public JTable getTblEquipos() {

		if (tblEquipos == null) {

			List<EquipmentDetail> allEquip = facade.findAllEquip();
			equiposTableModel = new EquipmentTableModel(allEquip);
			noItems = equiposTableModel.getRowCount();
			tblEquipos = new JTable(equiposTableModel);
			tblEquipos.setOpaque(Boolean.TRUE);
			tblEquipos.setBackground(Color.BLACK);
			tblEquipos.setForeground(Color.WHITE);
			tblEquipos.setFillsViewportHeight(Boolean.TRUE);
			tblEquipos.setAutoResizeMode(JTable.AUTO_RESIZE_LAST_COLUMN);
			tblEquipos.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
			buildTableColumnHeader(tblEquipos);

			InputMap im = tblEquipos.getInputMap(JTable.WHEN_FOCUSED);
			ActionMap am = tblEquipos.getActionMap();
			im.put(KeyStroke.getKeyStroke(KeyEvent.VK_DELETE, 0), "delete");
			am.put("delete", new AbstractAction() {
				/**
				 * generated serial version
				 */
				private static final long serialVersionUID = 3273665844020256738L;

				@Override
				public void actionPerformed(ActionEvent e) {
					int index = tblEquipos.getSelectedRow();
					int modelIndex = tblEquipos.convertRowIndexToModel(index);
					equiposTableModel.removeRow(modelIndex);
				}

			});
			tblEquipos.addMouseListener(listener);
			rowSorter = new TableRowSorter<TableModel>(equiposTableModel);
			tblEquipos.setRowSorter(rowSorter);
			tblEquipos.setDefaultRenderer(String.class, new CustomCellRenderer());
		}
		return tblEquipos;
	}

	private void buildTableColumnHeader(JTable table) {
		MultiLineHeaderRenderer renderer = new MultiLineHeaderRenderer();
		Enumeration<TableColumn> colList = table.getColumnModel().getColumns();

		while (colList.hasMoreElements()) {
			TableColumn tableColumn = colList.nextElement();
			tableColumn.setHeaderRenderer(renderer);
			final String tableColumnHeader = tableColumn.getHeaderValue().toString();
			switch (tableColumnHeader) {
			case "ID":
				tableColumn.setPreferredWidth(50);
				break;
			case "CATEGORIA":
				tableColumn.setPreferredWidth(100);
				break;
			case "MARCA":
				tableColumn.setPreferredWidth(100);
				break;
			case "MODELO":
				tableColumn.setPreferredWidth(100);
				break;
			case "ACTIVO":
				tableColumn.setPreferredWidth(50);
				break;
			default:
				tableColumn.setPreferredWidth(150);
				break;
			}
		}
	}

	public JPanel getStatusPanel() {
		if (statusPanel == null) {
			statusPanel = new JPanel();
			statusPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));
			statusPanel.setBackground(Color.BLACK);
			statusPanel.add(getLblNoItems());
		}
		return statusPanel;
	}

	public JLabel getLblNoItems() {
		if (lblNoItems == null) {
			lblNoItems = new JLabel("# items: " + noItems);
			lblNoItems.setOpaque(Boolean.FALSE);
			lblNoItems.setForeground(Color.WHITE);
		}
		return lblNoItems;
	}

	/**
	 * 
	 */
	public void createItem() {
		EquipmentDetail equipmentDetail = new EquipmentDetail();
		EditEquipmentUI det = new EditEquipmentUI(equipmentDetail, this);
		det.setLocationRelativeTo(this);
		det.setModal(Boolean.TRUE);
		det.setVisible(Boolean.TRUE);
	}

	public void editItem() {
		final int row = getTblEquipos().getSelectedRow();
		if (row > -1) {
			EquipmentTableModel aModel = (EquipmentTableModel) getTblEquipos().getModel();
			final BigInteger id = (BigInteger) aModel.getValueAt(row, 0);
			final String categoria = (String) aModel.getValueAt(row, 1);
			final String codigo = (String) aModel.getValueAt(row, 2);
			final String marca = (String) aModel.getValueAt(row, 3);
			final String modelo = (String) aModel.getValueAt(row, 4);
			final BigDecimal capComb = (BigDecimal) aModel.getValueAt(row, 5);
			final BigDecimal capCharge = (BigDecimal) aModel.getValueAt(row, 6);
			final Character active = (Character) aModel.getValueAt(row, 7);

			EquipmentDetail equipmentDetail = new EquipmentDetail();
			equipmentDetail.setId(id);
			equipmentDetail.setActive(active);
			equipmentDetail.setCapCharge(capCharge);
			equipmentDetail.setCapFuel(capComb);
			equipmentDetail.setModel(modelo);
			equipmentDetail.setMark(marca);
			equipmentDetail.setCategory(categoria);
			equipmentDetail.setCode(codigo);
			EditEquipmentUI det = new EditEquipmentUI(equipmentDetail, this);
			det.setLocationRelativeTo(this);
			det.setModal(Boolean.TRUE);
			det.setVisible(Boolean.TRUE);
		} else {
			JOptionPane.showMessageDialog(this, "Please select an item to edit", Constants.MSG_TITLE,
					JOptionPane.ERROR_MESSAGE);
		}
	}

	/**
	 * 
	 */
	public void deleteItem() {
		int row = getTblEquipos().getSelectedRow();
		if (row > -1) {
			int value = JOptionPane.showConfirmDialog(this, "Are you sure you want to delete this item", Constants.MSG_TITLE,
					JOptionPane.YES_NO_OPTION);
			if (JOptionPane.YES_OPTION == value) {
				Long equiId = Long.parseLong(getTblEquipos().getValueAt(row, 0).toString());
				facade.deleteEquipement(equiId);
				EquipmentTableModel model = (EquipmentTableModel) getTblEquipos().getModel();
				model.removeRow(row);
				noItems--;
				getLblNoItems().setText("# items: " + noItems);
			}
		} else {
			JOptionPane.showMessageDialog(this, "Operation cannot be completed!\nPlease select a table item!",
					Constants.MSG_TITLE, JOptionPane.ERROR_MESSAGE);
		}
	}
	
	public void searchTable() {
		final String searchValue = getTxtSearch().getText().trim();
		if (StringUtils.EMPTY.equals(searchValue)) {
			getTblEquipos().setRowSelectionInterval(0, 0);
		} else {
			final int rowCount = getTblEquipos().getRowCount();
			
			for (int row = 0; row < rowCount; row++) {
				for (int col = 1; col < 4; col++) {
					String next = (String) getTblEquipos().getValueAt(row, col);
					if (next.toUpperCase().contains(searchValue)) {
						Map.Entry<Integer, Integer> pair = new AbstractMap.SimpleEntry<>(row, col);
						if (!pairList.contains(pair)) {
							pairList.add(pair);
							showSearchResults(pair.getKey(), pair.getValue());
							return;
						}
					}
				}
			}	
		}
		if(!pairList.isEmpty()) {
			pairList.clear();
			JOptionPane.showMessageDialog(this, "No more items found to match criteria", Constants.MSG_TITLE,JOptionPane.INFORMATION_MESSAGE);
		}else {
			JOptionPane.showMessageDialog(this, "No items found to match criteria", Constants.MSG_TITLE,JOptionPane.INFORMATION_MESSAGE);
		}	
		CustomCellRenderer renderer = (CustomCellRenderer) getTblEquipos().getDefaultRenderer(String.class);
		renderer.setTargetCell(-1, -1);
		getTblEquipos().repaint();
	}

	private void showSearchResults(int row, int col) {
		CustomCellRenderer renderer = (CustomCellRenderer) getTblEquipos().getCellRenderer(row, col);
		renderer.setTargetCell(row, col);
		Rectangle r = getTblEquipos().getCellRect(row, col, false);
		getTblEquipos().scrollRectToVisible(r);
		getTblEquipos().setRowSelectionInterval(row, row);
		getTblEquipos().repaint();
	}
	
	public SmartFacade getSmartFacade() {
		return facade;
	}
}