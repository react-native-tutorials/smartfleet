package com.smartlife.smartfleet.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.util.Enumeration;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JToolBar;
import javax.swing.ListSelectionModel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.TableColumn;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.context.support.AbstractRefreshableApplicationContext;

import com.smartlife.smartfleet.dto.StateDetail;
import com.smartlife.smartfleet.facade.SmartFacade;
import com.smartlife.smartfleet.utils.Constants;

/**
 * @author Marius-Iulian Grigoras
 *
 */
public class StatePanel extends JPanel {

	/**
	 * generated serial version
	 */
	private static final long serialVersionUID = 8317807375089334653L;
	protected final Log logger = LogFactory.getLog(getClass());
	static final String NEW_CMD = "NEW_CMD";
	static final String ED_CMD = "ED_CMD";
	static final String DEL_CMD = "DEL_CMD";

	private JToolBar stateToolbar;
	private JButton btnNewState;
	private JButton btnEditSaveState;
	private JButton btnDeleteState;
	private JScrollPane statePane;
	private JTable stateTable;
	private StateTableModel stateTableModel;

	private AbstractRefreshableApplicationContext context;
	private SmartFacade facade;
	StateActionListener listener;

	int noItems = 0;
	private JLabel lblNoItems;
	private JPanel statusPanel;
	private TableRowSorter<TableModel> rowSorter;

	/**
	 * Create the panel.
	 */
	public StatePanel(AbstractRefreshableApplicationContext ctx) {
		this.listener = new StateActionListener(this);
		this.context = ctx;
		this.facade = (SmartFacade) context.getBean("smartFacade");
		setBackground(Color.BLACK);
		setForeground(Color.WHITE);
		final BorderLayout paneLayout = new BorderLayout(0, 0);
		setLayout(paneLayout);
		add(getStateToolbar(), BorderLayout.NORTH);
		add(getStatePane(), BorderLayout.CENTER);
		add(getStatusPanel(), BorderLayout.SOUTH);
	}

	public JToolBar getStateToolbar() {
		if (stateToolbar == null) {
			stateToolbar = new JToolBar();
			stateToolbar.setFloatable(Boolean.FALSE);
			stateToolbar.setBackground(Color.BLACK);
			stateToolbar.setLayout(null);
			stateToolbar.setPreferredSize(new Dimension(this.getWidth(), 25));
			stateToolbar.add(getBtnNewState());
			stateToolbar.add(getBtnEditSaveState());
			stateToolbar.add(getBtnDeleteState());
		}
		return stateToolbar;
	}

	public JButton getBtnNewState() {
		if (btnNewState == null) {
			btnNewState = new JButton("Nuevo");
			btnNewState.setBounds(0, 0, 70, 20);
			btnNewState.setBackground(Color.BLACK);
			btnNewState.setForeground(Color.WHITE);
			btnNewState.setBorder(new EmptyBorder(0, 0, 0, 0));
			btnNewState.setActionCommand(NEW_CMD);
			btnNewState.addActionListener(listener);
		}
		return btnNewState;
	}

	public JButton getBtnEditSaveState() {
		if (btnEditSaveState == null) {
			btnEditSaveState = new JButton("Editar");
			btnEditSaveState.setBounds(70, 0, 70, 20);
			btnEditSaveState.setBackground(Color.BLACK);
			btnEditSaveState.setForeground(Color.WHITE);
			btnEditSaveState.setBorder(new EmptyBorder(0, 0, 0, 0));
			btnEditSaveState.setActionCommand(ED_CMD);
			btnEditSaveState.addActionListener(listener);
		}
		return btnEditSaveState;
	}

	public JButton getBtnDeleteState() {
		if (btnDeleteState == null) {
			btnDeleteState = new JButton("Borrar");
			btnDeleteState.setBounds(140, 0, 70, 20);
			btnDeleteState.setBackground(Color.BLACK);
			btnDeleteState.setForeground(Color.WHITE);
			btnDeleteState.setBorder(new EmptyBorder(0, 0, 0, 0));
			btnDeleteState.setActionCommand(DEL_CMD);
			btnDeleteState.addActionListener(listener);
		}
		return btnDeleteState;
	}

	public JScrollPane getStatePane() {
		if (statePane == null) {
			statePane = new JScrollPane();
			statePane.setViewportView(getStateTable());
			statePane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
			statePane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
		}
		return statePane;
	}

	public JTable getStateTable() {
		if (stateTable == null) {
			List<StateDetail> details = facade.findStateDetail();
			stateTableModel = new StateTableModel(details);
			noItems = stateTableModel.getRowCount();
			stateTable = new JTable(stateTableModel);
			stateTable.setOpaque(Boolean.TRUE);
			stateTable.setBackground(Color.BLACK);
			stateTable.setForeground(Color.WHITE);
			stateTable.setAutoResizeMode(JTable.AUTO_RESIZE_LAST_COLUMN);
			stateTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
			stateTable.setFillsViewportHeight(Boolean.TRUE);
			setTableHeader(stateTable);
			
			stateTable.addMouseListener(listener);
			rowSorter = new TableRowSorter<TableModel>(stateTableModel);
			stateTable.setRowSorter(rowSorter);
		}
		return stateTable;
	}

	/**
	 * @param table
	 */
	private void setTableHeader(JTable table) {
		StateTableRenderer tableRender = new StateTableRenderer();
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
			case "CATEGORIA":
				tableColumn.setPreferredWidth(100);
				break;
			case "ESTADO":
				tableColumn.setPreferredWidth(100);
				break;
			case "COLOR":
				tableColumn.setPreferredWidth(100);
				tableColumn.setCellRenderer(tableRender);
				break;
			case "RAZON":
				tableColumn.setPreferredWidth(400);
				break;
			}
		}
	}

	public JLabel getLblNoItems() {
		if (lblNoItems == null) {
			lblNoItems = new JLabel(Constants.LBL_ITEMS_TITLE + noItems);
			lblNoItems.setOpaque(Boolean.FALSE);
			lblNoItems.setForeground(Color.WHITE);
		}
		return lblNoItems;
	}

	public JPanel getStatusPanel() {
		if (statusPanel == null) {
			statusPanel = new JPanel();
			statusPanel.setBackground(Color.BLACK);
			statusPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));
			statusPanel.add(getLblNoItems());
		}
		return statusPanel;
	}

	public void createItem() {
		StateDetail stateDetail = new StateDetail();
		EditStateUI frame = new EditStateUI(stateDetail, this);
		frame.setLocationRelativeTo(this);
		frame.setModal(Boolean.TRUE);
		frame.setVisible(Boolean.TRUE);
	}

	public void editItem() {

		final int row = getStateTable().getSelectedRow();
		if (row > -1) {
			StateTableModel aModel = (StateTableModel) getStateTable().getModel();
			final Long razonId = (Long) aModel.getValueAt(row, 0);
			final String categ = (String) aModel.getValueAt(row, 1);
			final String state = (String) aModel.getValueAt(row, 2);
			final Integer color = (Integer) aModel.getValueAt(row, 3);
			final String razon = (String) aModel.getValueAt(row, 4);

			StateDetail detail = new StateDetail();
			detail.setId(razonId);
			detail.setCategoria(categ);
			detail.setEstado(state);
			detail.setColor(color);
			detail.setRazon(razon);

			EditStateUI editStateUI = new EditStateUI(detail, this);
			editStateUI.setLocationRelativeTo(this);
			editStateUI.setModal(Boolean.TRUE);
			editStateUI.setVisible(Boolean.TRUE);
		} else {
			JOptionPane.showMessageDialog(this, "Please select an item to edit", Constants.MSG_TITLE,
					JOptionPane.ERROR_MESSAGE);
		}

	}

	public void deleteItem() {
		final int selectedRow = getStateTable().getSelectedRow();
		if (selectedRow > -1) {
			int value = JOptionPane.showConfirmDialog(this, "Are you sure you want to delete this item", Constants.MSG_TITLE,
					JOptionPane.YES_NO_OPTION);
			if (JOptionPane.YES_OPTION == value) {
				Long reasonId = Long.parseLong(getStateTable().getValueAt(selectedRow, 0).toString());
				facade.deleteReason(reasonId);
				StateTableModel model = (StateTableModel) getStateTable().getModel();
				model.removeRow(selectedRow);
				noItems--;
				getLblNoItems().setText(Constants.LBL_ITEMS_TITLE + noItems);
			}
		} else {
			JOptionPane.showMessageDialog(this, "Operation cannot be completed!\nPlease select a table item!",
					Constants.MSG_TITLE, JOptionPane.ERROR_MESSAGE);
		}
	}

	public SmartFacade getSmartFacade() {
		return facade;
	}
}
