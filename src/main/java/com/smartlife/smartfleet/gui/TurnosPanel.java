package com.smartlife.smartfleet.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Rectangle;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.sql.Time;
import java.text.SimpleDateFormat;
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

import com.smartlife.smartfleet.domain.Turno;
import com.smartlife.smartfleet.facade.SmartFacade;
import com.smartlife.smartfleet.utils.Constants;

/**
 * @author Marius-Iulian Grigoras
 *
 */
public class TurnosPanel extends JPanel {

	/**
	 * generated serial version
	 */
	private static final long serialVersionUID = -8495669078273444290L;
	protected final Log logger = LogFactory.getLog(getClass());

	static final String NEW_CMD = "NEW_TUR";
	static final String ED_CMD = "EDIT_TUR";
	static final String SAV_CMD = "SAVE_TUR";
	static final String DEL_CMD = "DEL_TUR";

	private JToolBar mainToolbar;
	private JButton btnNew;
	private JButton btnEdit;
	private JButton btnDelete;
	private JScrollPane mainPane;
	private JPanel statusPane;
	private JTable mainTable;
	private JLabel lblNoItems;
	private JLabel lblSearch;
	private JTextField txtSearch;
	private TableRowSorter<TableModel> rowSorter;
	private JCheckBox chkEnable;

	private AbstractRefreshableApplicationContext context;
	private SmartFacade facade;
	TurnosActionListener listener;
	TurnosTableModel model;
	int noItems = 0;
	List<Map.Entry<Integer, Integer>> pairList = new ArrayList<>();

	/**
	 * Create the statusPane.
	 */
	public TurnosPanel(AbstractRefreshableApplicationContext ctx) {
		this.listener = new TurnosActionListener(this);
		this.context = ctx;
		this.facade = (SmartFacade) context.getBean("smartFacade");
		setLayout(new BorderLayout(0, 0));

		add(getMainToolbar(), BorderLayout.NORTH);
		add(getMainPane(), BorderLayout.CENTER);
		add(getStatusPane(), BorderLayout.SOUTH);
	}

	public JToolBar getMainToolbar() {
		if (mainToolbar == null) {
			mainToolbar = new JToolBar();
			mainToolbar.setFloatable(Boolean.FALSE);
			mainToolbar.setBackground(Color.BLACK);
			mainToolbar.setPreferredSize(new Dimension(this.getWidth(), 25));
			mainToolbar.setLayout(null);
			mainToolbar.add(getBtnNew());
			mainToolbar.add(getBtnEdit());
			mainToolbar.add(getBtnDelete());
			mainToolbar.add(getChkEnable());
			mainToolbar.add(getLblSearch());
			mainToolbar.add(getTxtSearch());

		}
		return mainToolbar;
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

			List<Turno> allTurnos = facade.findAllTurnos();
			model = new TurnosTableModel(allTurnos);
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
			mainTable.setDefaultRenderer(Time.class, new CustomCellRenderer());
			mainTable.setDefaultRenderer(Integer.class, new CustomCellRenderer());
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
			case "NOMBRE":
				tableColumn.setPreferredWidth(100);
				break;
			case "HORA INICIO":
				tableColumn.setPreferredWidth(100);
				break;
			case "HORA FIN":
				tableColumn.setPreferredWidth(300);
				break;
			case "DURACIÓN":
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
		if (lblNoItems == null) {
			lblNoItems = new JLabel(Constants.LBL_ITEMS_TITLE + noItems);
			lblNoItems.setOpaque(Boolean.FALSE);
			lblNoItems.setForeground(Color.WHITE);
		}
		return lblNoItems;
	}

	public SmartFacade getSmartFacade() {
		return facade;
	}

	/**
	 * show new item enter frame
	 */
	public void createItem() {
		Turno turno = new Turno();
		EditTurnoUI det = new EditTurnoUI(turno, this);
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
			TurnosTableModel aModel = (TurnosTableModel) getMainTable().getModel();
			final Long id = (Long) aModel.getValueAt(row, 0);
			final String nombre = (String) aModel.getValueAt(row, 1);
			final Time horaIni = (Time) aModel.getValueAt(row, 2);
			final Time horaFin = (Time) aModel.getValueAt(row, 3);
			final Integer duracion = (Integer) aModel.getValueAt(row, 4);

			Turno turnoDetail = new Turno();
			turnoDetail.setId(id);
			turnoDetail.setNomTurno(nombre);
			turnoDetail.setHoraIni(horaIni);
			turnoDetail.setHoraFin(horaFin);
			turnoDetail.setHoras(duracion);

			EditTurnoUI det = new EditTurnoUI(turnoDetail, this);
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
				Long turnoId = Long.parseLong(getMainTable().getValueAt(row, 0).toString());
				facade.deleteTurno(turnoId);
				TurnosTableModel turnoTableModel = (TurnosTableModel) getMainTable().getModel();
				turnoTableModel.removeRow(row);
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
					Object value = getMainTable().getValueAt(row, col);
					String nextValue;
					if(value instanceof Time) {
						SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm");
						nextValue = timeFormat.format(value);
					}else {
						nextValue = String.valueOf(value);
					}
					if (nextValue.toUpperCase().contains(searchValue)) {
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
