package com.java.gui;

/**
 * @author Juniors
 */
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TableDemo extends JFrame implements ActionListener {
    private JTable table;
    private DefaultTableModel model;
    private String[] columnNames = { "No" };
    private JScrollPane scrollPane;
    private JTextField textField;
    public TableDemo() {
        getContentPane().setLayout(null);
        scrollPane = new JScrollPane();
        scrollPane.setBounds(12, 69, 370, 273);
        getContentPane().add(scrollPane);
        model = new DefaultTableModel(columnNames, 0);
        table = new JTable(model);
        scrollPane.setViewportView(table);
        textField = new JTextField();
        textField.setBounds(12, 22, 96, 19);
        getContentPane().add(textField);
        textField.setColumns(10);
        JButton btnNewButton = new JButton("add");
        btnNewButton.setBounds(134, 21, 91, 21);
        btnNewButton.addActionListener(this);
        getContentPane().add(btnNewButton);
        JButton btnNewButton_1 = new JButton("get");
        btnNewButton_1.setBounds(237, 21, 91, 21);
        btnNewButton_1.addActionListener(this);
        getContentPane().add(btnNewButton_1);
        initTableData();
// 窗口属性的设置
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setSize(400, 380);
        setResizable(false);
        setVisible(true);
    }
    private void initTableData() {
        Object[] row = new Object[1];
        row[0] = "111";
        model.addRow(row);
    }
    public static void main(String[] args) {
        new TableDemo();
    }
    public void actionPerformed(ActionEvent e) {
        JButton btn = (JButton) e.getSource();
        if ("add".equals(btn.getText())) {
            Object[] row = new Object[1];
            row[0] = textField.getText();
            model.addRow(row);
        }
        if ("get".equals(btn.getText())) {
            int column = table.getSelectedColumn();
            int row = table.getSelectedRow();
            textField.setText((String) table.getValueAt(row, column));
        }
    }
}
