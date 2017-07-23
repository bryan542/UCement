package Executable;

import javax.swing.*;
import javax.swing.table.TableColumn;

/**
 * Created by bryan on 7/21/2017.
 */
public class InitialJTableComboBoxSetup{

    public InitialJTableComboBoxSetup(JTable table, JComboBox firstComboBox, String[] firstItems)  {

        TableColumn tableColumn = table.getColumnModel().getColumn(1);
        ComboBoxEditor CBE = new ComboBoxEditor(firstComboBox);
        tableColumn.setCellEditor(CBE); //adds items to the combobox
        tableColumn.setCellRenderer(new ComboBoxRenderer(firstItems)); //finds the index

    }

    public InitialJTableComboBoxSetup(JTable table, JComboBox firstComboBox, String[] firstItems,JComboBox secondCombobox, String[] secondItems, String parameterName) {

        TableColumn tableColumn = table.getColumnModel().getColumn(1);
        ComboBoxEditor CBE = new ComboBoxEditor(firstComboBox);
        tableColumn.setCellEditor(CBE); //adds items to the combobox
        tableColumn.setCellRenderer(new ComboBoxRenderer(firstItems)); //finds the index

        if (parameterName.equalsIgnoreCase("Salt")) {

            TableColumn tableColumnSecond = table.getColumnModel().getColumn(2);
            ComboBoxEditor CBESecond = new ComboBoxEditor(secondCombobox);
            tableColumnSecond.setCellEditor(CBESecond); //adds items to the combobox
            tableColumnSecond.setCellRenderer(new ComboBoxRenderer(secondItems)); //finds the index

        } else if (parameterName.equalsIgnoreCase("Dry") || parameterName.equalsIgnoreCase("Liquid")) {

            TableColumn tableColumnSecond = table.getColumnModel().getColumn(3);
            ComboBoxEditor CBESecond = new ComboBoxEditor(secondCombobox);
            tableColumnSecond.setCellEditor(CBESecond); //adds items to the combobox
            tableColumnSecond.setCellRenderer(new ComboBoxRenderer(secondItems)); //finds the index

        }
    }
}
