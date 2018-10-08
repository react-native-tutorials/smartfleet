/**
 * 
 */
package com.smartlife.smartfleet.ui.common;

import java.awt.Component;

import javax.swing.DefaultCellEditor;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JTable;

/**
 * @author Marius Iulian Grigoras
 *
 */
public class CustomComboBoxEditor extends DefaultCellEditor {

	/**
	 * generated serial version
	 */
	private static final long serialVersionUID = 8124302829554723857L;

	// Declare a model that is used for adding the elements to the `Combo box`
	  @SuppressWarnings({ "rawtypes", "unused" })
	private DefaultComboBoxModel model;
	
	@SuppressWarnings("rawtypes")
	public CustomComboBoxEditor(JComboBox comboBox) {
		super(comboBox);
		this.model = (DefaultComboBoxModel)((JComboBox)getComponent()).getModel();
	}
	
	@Override
	  public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
	      // Add the elements which you want to the model.
	      // Here I am adding elements from the orderList(say) which you can pass via constructor to this class.
	      //model.addElement(orderList.get(i));

	      //finally return the component.
	      return super.getTableCellEditorComponent(table, value, isSelected, row, column);
	  } 
}
