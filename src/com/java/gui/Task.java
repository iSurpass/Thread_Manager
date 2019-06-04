package com.java.gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * @author Juniors
 */
public class Task extends JFrame implements ActionListener {

    static Task frame = new Task();
    JLabel l1 = new JLabel("Number1");
    JLabel l2 = new JLabel("Number2");
    JLabel l3 = new JLabel("Result");
    JTextField text1 = new JTextField();
    JTextField text2 = new JTextField();
    JTextField text3 = new JTextField();
    JButton b1 = new JButton("Add");
    JButton b2 = new JButton("Sub");
    JButton b3 = new JButton("Multiply");
    JButton b4 = new JButton("Divide");
    JPanel p1 = new JPanel();
    JPanel p2 = new JPanel();

    public void display(){b1.addActionListener(frame);
        frame.setTitle("简易计算器");
        frame.setSize(300,100);
        GridLayout grid = new GridLayout(1,6);
        GridLayout grid1 = new GridLayout(1,4);
        frame.setLayout(null);
        frame.setVisible(true);
        text1.setHorizontalAlignment(JTextField.RIGHT);
        text2.setHorizontalAlignment(JTextField.RIGHT);
        text3.setHorizontalAlignment(JTextField.RIGHT);
        p1.setSize(500,30);
        p1.setLayout(grid);
        b1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                double a1 = Double.valueOf(text1.getText());
                double a2 = Double.valueOf(text2.getText());
                double result;
                result = a1 + a2;
                text3.setText(String.valueOf((int)result));
            }
        });
        b2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                double a1 = Double.valueOf(text1.getText());
                double a2 = Double.valueOf(text2.getText());
                double result;
                result = a1 - a2;
                text3.setText(String.valueOf((int)result));
            }
        });
        b3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                double a1 = Double.valueOf(text1.getText());
                double a2 = Double.valueOf(text2.getText());
                double result;
                result = a1 * a2;
                text3.setText(String.valueOf((int)result));
            }
        });
        b4.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                double a1 = Double.valueOf(text1.getText());
                double a2 = Double.valueOf(text2.getText());
                if (a2 == 0){
                    text3.setText("Error");
                }
                double result;
                result = a1 / a2;
                text3.setText(String.valueOf((int)result));
            }
        });
        p1.add(l1);p1.add(text1);p1.add(l2);
        p1.add(text2);p1.add(l3);p1.add(text3);
        frame.add(p1);
        p2.setLayout(grid1);
        p2.setSize(500,50);
        p2.setLocation(0,30);

        p2.add(b1);p2.add(b2);p2.add(b3);p2.add(b4);

        frame.add(p2);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == b1){
            System.out.println("345342");
            int a1 = Integer.parseInt(text1.getText());
            int a2 = Integer.parseInt(text2.getText());
            int result;
            result = a1 + a2;
            text3.setText(String.valueOf(result));
        }

        /*if (e.getSource() == b2){
            int result = a1 - a2;
            text3.setText(String.valueOf(result));
        }
        if (e.getSource() == b3){
            int result = a1 * a2;
            text3.setText(String.valueOf(result));
        }
        if (e.getSource() == b4){
            if (a2 == 0){
                text1.setText("Error");
            }
            int result = a1 / a2;
            text3.setText(String.valueOf(result));
        }*/
    }

    public static void main(String[] args) {
        Task task = new Task();
        task.display();
    }
}
