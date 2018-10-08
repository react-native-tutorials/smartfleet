/**
 * 
 */
package com.smartlife.smartfleet.ui.common;

import java.util.Arrays;
import java.util.Collections;
import java.util.Vector;

import javax.swing.ComboBoxModel;
import javax.swing.DefaultComboBoxModel;

/**
 * @author Marius Iulian Grigoras
 *
 * @date 16 jul. 2018
 */
public class SortedComboBoxModel extends DefaultComboBoxModel<Object> implements ComboBoxModel<Object>{

	/**
	 * generated serial version
	 */
	private static final long serialVersionUID = -4251160806425787821L;
	
	public SortedComboBoxModel() {
        super();
    }

    public SortedComboBoxModel(Object[] items) {
        Arrays.sort(items);
        int size = items.length;
        for (int i = 0; i < size; i++) {
            super.addElement(items[i]);
        }
        setSelectedItem(items[0]);
    }

    @SuppressWarnings({ "unchecked", "rawtypes" })
	public SortedComboBoxModel(Vector items) {
        Collections.sort(items);
        int size = items.size();
        for (int i = 0; i < size; i++) {
            super.addElement(items.elementAt(i));
        }
        setSelectedItem(items.elementAt(0));
    }

    @Override
    public void addElement(Object element) {
        insertElementAt(element, 0);
    }

    @SuppressWarnings({ "unchecked", "rawtypes" })
    @Override
    public void insertElementAt(Object element, int index) {
        int size = getSize();
        //  Determine where to insert element to keep model in sorted order            
        for (index = 0; index < size; index++) {
            Comparable c = (Comparable) getElementAt(index);
            if (c.compareTo(element) > 0) {
                break;
            }
        }
        super.insertElementAt(element, index);
    }

}
