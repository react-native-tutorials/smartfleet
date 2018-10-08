package com.smartlife.smartfleet.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Rectangle;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.Map;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JToolBar;
import javax.swing.ListSelectionModel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.TableColumn;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.context.support.AbstractRefreshableApplicationContext;

import com.smartlife.smartfleet.domain.Dispositivo;
import com.smartlife.smartfleet.facade.SmartFacade;
import com.smartlife.smartfleet.utils.Constants;

/**
 * @author Marius-Iulian Grigoras
 *
 */
public class DevicePanel extends JPanel {

	/**
	 * generated serial version
	 */
	private static final long serialVersionUID = -8495669078273444290L;
	protected final Log logger = LogFactory.getLog(getClass());

	static final String NEW_CMD = "NEW_OPER";
	static final String ED_CMD = "EDIT_OPER";
	static final String SAV_CMD = "SAVE_OPER";
	static final String DEL_CMD = "DEL_OPER";
	
	private JToolBar operToolbar;
	private JButton btnNew;
	private JButton btnEdit;
	private JButton btnDelete;
	private JScrollPane mainPane;
	private JPanel statusPane;
	private JTable mainTable;
	private JLabel noItemsLabel;
	private JLabel lblSearch;
	private JTextField txtSearch;
	private TableRowSorter<TableModel> rowSorter;
	private JCheckBox chkEnable;

	private AbstractRefreshableApplicationContext context;
	private SmartFacade facade;
	DeviceActionListener listener;
	DeviceTableModel model;
	int noItems = 0;
	List<Map.Entry<Integer, Integer>> pairList = new ArrayList<>();

	/**
	 * Create the statusPane.
	 */
	public DevicePanel(AbstractRefreshableApplicationContext ctx) {
		this.listener = new DeviceActionListener(this);
		this.context = ctx;
		this.facade = (SmartFacade) context.getBean("smartFacade");
		setLayout(new BorderLayout(0, 0));

		add(getOperToolbar(), BorderLayout.NORTH);
		add(getMainPane(), BorderLayout.CENTER);
		add(getStatusPane(), BorderLayout.SOUTH);
	}

	public JToolBar getOperToolbar() {
		if (operToolbar == null) {
			operToolbar = new JToolBar();
			operToolbar.setFloatable(Boolean.FALSE);
			operToolbar.setBackground(Color.BLACK);
			operToolbar.setPreferredSize(new Dimension(this.getWidth(), 25));
			operToolbar.setLayout(null);
			operToolbar.add(getBtnNew());
			operToolbar.add(getBtnEdit());
			operToolbar.add(getBtnDelete());
			operToolbar.add(getChkEnable());
			operToolbar.add(getLblSearch());
			operToolbar.add(getTxtSearch());

		}
		return operToolbar;
	}

	public JButton getBtnNew() {
		if (btnNew == null) {
			btnNew = new JButton("Nuevo");
			btnNew.setBounds(0, 0, 70, 20);
			btnNew.setBackground(Color.BLACK);
			btnNew.setForeground(Color.WHITE);
			btnNew.setBorder(new EmptyBorder(0, 0, 0, 0));
			btnNew.setActionCommand(NEW_CMD);
			btnNew.addActionListener(listener);
		}
		return btnNew;
	}

	public JButton getBtnEdit() {
		if (btnEdit == null) {
			btnEdit = new JButton("Editar");
			btnEdit.setBounds(70, 0, 70, 20);
			btnEdit.setBackground(Color.BLACK);
			btnEdit.setForeground(Color.WHITE);
			btnEdit.setBorder(new EmptyBorder(0, 0, 0, 0));
			btnEdit.setActionCommand(ED_CMD);
			btnEdit.addActionListener(listener);
		}
		return btnEdit;
	}

	public JButton getBtnDelete() {
		if (btnDelete == null) {
			btnDelete = new JButton("Borrar");
			btnDelete.setBounds(140, 0, 70, 20);
			btnDelete.setForeground(Color.WHITE);
			btnDelete.setBackground(Color.black);
			btnDelete.setBorder(new EmptyBorder(0, 0, 0, 0));
			btnDelete.setActionCommand(DEL_CMD);
			btnDelete.addActionListener(listener);
		}
		return btnDelete;
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

	public JScrollPane getMainPane() {
		if (mainPane == null) {
			mainPane = new JScrollPane();
			mainPane.setViewportView(getMainTable());
		}
		return mainPane;
	}

	public JTable getMainTable() {
		if (mainTable == null) {

			List<Dispositivo> allDevices = facade.findAllDevices();
			model = new DeviceTableModel(allDevices);
			noItems = model.getRowCount();
			mainTable = new JTable(model);
			rowSorter = new TableRowSorter<>(mainTable.getModel());
			mainTable.setOpaque(Boolean.TRUE);
			mainTable.setBackground(Color.BLACK);
			mainTable.setForeground(Color.WHITE);
			mainTable.setFillsViewportHeight(Boolean.TRUE);
			mainTable.setAutoResizeMode(JTable.AUTO_RESIZE_LAST_COLUMN);
			mainTable.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
			setTableHeader(mainTable);
			mainTable.addMouseListener(listener);
			mainTable.setRowSorter(rowSorter);
			mainTable.setDefaultRenderer(String.class, new CustomCellRenderer());
		}
		return mainTable;
	}

	private void setTableHeader(JTable table) {

		MultiLineHeaderRenderer headerRenderer = new MultiLineHeaderRenderer();
		Enumeration<TableColumn> columnList = table.getColumnModel().getColumns();
		while (columnList.hasMoreElements()) {
			TableColumn tableColumn = columnList.nextElement();
			tableColumn.setHeaderRenderer(headerRenderer);
			final String tableColumnHeader = tableColumn.getHeaderValue().toString();
			switch (tableColumnHeader) {
			case "ID":
				tableColumn.setPreferredWidth(20);
				break;
			case "IP":
				tableColumn.setPreferredWidth(100);
				break;
			case "PORT":
				tableColumn.setPreferredWidth(100);
				break;
			case "MAC":
				tableColumn.setPreferredWidth(100);
				break;
			case "GATEWAY":
				tableColumn.setPreferredWidth(200);
				break;
			case "TIPO":
				tableColumn.setPreferredWidth(300);
				break;
			default:
				break;
			}
		}
	}

	public JPanel getStatusPane() {
		if (statusPane == null) {
			statusPane = new JPanel();
			statusPane.setBackground(Color.BLACK);
			statusPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			statusPane.add(getNoItemsLabel());
		}
		return statusPane;
	}

	public JLabel getNoItemsLabel() {
		if (noItemsLabel == null) {
			noItemsLabel = new JLabel(Constants.LBL_ITEMS_TITLE + noItems);
			noItemsLabel.setOpaque(Boolean.FALSE);
			noItemsLabel.setForeground(Color.WHITE);
		}
		return noItemsLabel;
	}

	public SmartFacade getSmartFacade() {
		return facade;
	}

	/**
	 * show new item enter frame
	 */
	public void createItem() {
		Dispositivo device = new Dispositivo();
		EditDeviceUI det = new EditDeviceUI(device, this);
		det.setLocationRelativeTo(this);
		det.setModal(Boolean.TRUE);
		det.setVisible(Boolean.TRUE);
	}

	/**
	 * show editor frame
	 */
	public void editItem() {
		int row = getMainTable().getSelectedRow();
		if (row > -1) {
			DeviceTableModel aModel = (DeviceTableModel) getMainTable().getModel();
			final Long id = (Long) aModel.getValueAt(row, 0);
			final String ipDisp = (String) aModel.getValueAt(row, 1);
			final String portDisp = (String) aModel.getValueAt(row, 2);
			final String macDisp = (String) aModel.getValueAt(row, 3);
			final String gateway = (String) aModel.getValueAt(row, 4);
			final String tipoDisp = (String) aModel.getValueAt(row, 5);

			Dispositivo dispDetail = new Dispositivo();
			dispDetail.setId(id);
			dispDetail.setIpDispositivo(ipDisp);
			dispDetail.setPortDisp(portDisp);
			dispDetail.setMacDispositivo(macDisp);
			dispDetail.setGateway(gateway);
			dispDetail.setTipoDispositivo(tipoDisp);
			
			EditDeviceUI det = new EditDeviceUI(dispDetail, this);
			det.setLocationRelativeTo(this);
			det.setModal(Boolean.TRUE);
			det.setVisible(Boolean.TRUE);
		} else {
			JOptionPane.showMessageDialog(this, "Please select an item to edit", Constants.MSG_TITLE,
					JOptionPane.ERROR_MESSAGE);
		}
	}

	public void deleteItem() {
		int row = getMainTable().getSelectedRow();
		if (row > -1) {
			int value = JOptionPane.showConfirmDialog(this, "Are you sure you want to delete this item", Constants.MSG_TITLE,
					JOptionPane.YES_NO_OPTION);
			if (JOptionPane.YES_OPTION == value) {
				Long deviceId = Long.parseLong(getMainTable().getValueAt(row, 0).toString());
				facade.deleteDevice(deviceId);
				DeviceTableModel dispTableModel = (DeviceTableModel) getMainTable().getModel();
				dispTableModel.removeRow(row);
				noItems--;
				getNoItemsLabel().setText(Constants.LBL_ITEMS_TITLE + noItems);
			}
		} else {
			JOptionPane.showMessageDialog(this, "Operation cannot be completed!\nPlease select a table item!",
					Constants.MSG_TITLE, JOptionPane.ERROR_MESSAGE);
		}
	}

	public void searchTable() {
		final String searchValue = getTxtSearch().getText().trim();
		if (StringUtils.EMPTY.equals(searchValue)) {
			getMainTable().setRowSelectionInterval(0, 0);
		} else {
			final int rowCount = getMainTable().getRowCount();
			final int columnCount = getMainTable().getColumnCount();

			for (int row = 0; row < rowCount; row++) {
				for (int col = 1; col < columnCount; col++) {
					String next = (String) getMainTable().getValueAt(row, col);
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
		CustomCellRenderer renderer = (CustomCellRenderer) getMainTable().getDefaultRenderer(String.class);
		renderer.setTargetCell(-1, -1);
		getMainTable().repaint();
	}

	private void showSearchResults(int row, int col) {
		CustomCellRenderer renderer = (CustomCellRenderer) getMainTable().getCellRenderer(row, col);
		renderer.setTargetCell(row, col);
		Rectangle r = getMainTable().getCellRect(row, col, false);
		getMainTable().scrollRectToVisible(r);
		getMainTable().setRowSelectionInterval(row, row);
		getMainTable().repaint();
	}
}
