package com.java.gui;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Juniors
 */
public class Frist extends JFrame{

    public static List<Produce> produces = new ArrayList<>();
    public static Map<Integer,Produce> produceMap = new HashMap<>();


    public static Object[] columsNames1;
    public static JTable tablePre;
    public static JPanel panelBb;
    public static DefaultTableModel model2;

    public static DefaultTableModel model1;

    public static void main(String[] args) {


        JFrame frame = new JFrame("进程管理与调度模拟程序");
        FlowLayout flow = new FlowLayout();
        frame.setLayout(null);
        frame.setSize(1500,800);
        JMenuBar bar = new JMenuBar();
        JMenu menu = new JMenu("设置");
        bar.add(menu);
        JMenu menu1 = new JMenu("关于");
        bar.add(menu1);
        JMenuItem item = new JMenuItem("Faker must be dead!");
        menu1.add(item);
        frame.setJMenuBar(bar);

        JPanel panel1 = new JPanel();
        panel1.setLayout(flow);
        panel1.setSize(200,40);
        JButton b1 = new JButton("创建进程");
        b1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new CreateProduceWindow();
            }
        });
        JButton b2 = new JButton("停止进程");
        frame.add(panel1);
        panel1.add(b1);
        panel1.add(b2);


        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(1,3));
        panel.setLocation(0,40);
        panel.setSize(1450,800);
        JPanel panelA = new JPanel();
        panelA.setBorder(new TitledBorder("运行态"));
        panelA.setSize(220,220);
        JPanel panelAa = new JPanel();
        panelAa.setSize(40,20);
        panelAa.add(new JButton("停止"));
        panelAa.add(new JButton("阻塞"));
        panelA.add(panelAa);
        JPanel panelAb = new JPanel();
        panelAb.setSize(220,200);
        panelA.add(panelAb);

        Object[] columsNames = {"PID","名称","时间片","剩余运行时间"};
        model1 = new DefaultTableModel(columsNames,0);
        JTable tableRun = new JTable(model1);
        panelAb.add(new JScrollPane(tableRun));


        JPanel panelB = new JPanel();
        panelB.setBorder(new TitledBorder("就绪态"));
        panelB.setSize(120,220);
        JPanel panelBa = new JPanel();
        panelBa.setSize(40,20);
        panelBa.add(new JButton("停止"));
        panelB.add(panelBa);
        panelBb = new JPanel();
        panelBb.setSize(220,200);
        panelB.add(panelBb);

        columsNames1 = new Object[]{"PID", "名称", "剩余运行时间"};
        model2 = new DefaultTableModel(columsNames1,0);
        tablePre = new JTable(model2);

        panelBb.add(new JScrollPane(tablePre));


        JPanel panelC = new JPanel();
        panelC.setBorder(new TitledBorder("阻塞态"));
        panelC.setSize(120,220);
        JPanel panelCa = new JPanel();
        panelCa.setSize(40,20);
        panelCa.add(new JButton("停止"));
        panelCa.add(new JButton("激活"));
        panelC.add(panelCa);
        JPanel panelCb = new JPanel();
        panelCb.setSize(220,200);
        panelC.add(panelCb);

        Object[][] disData = {{5,"harden"}};
        Object[] columsNames2 = {"PID","名称"};
        JTable tableDis = new JTable(disData,columsNames2);
        panelCb.add(new JScrollPane(tableDis));

        panel.add(panelA);
        panel.add(panelB);
        panel.add(panelC);
        frame.add(panel);


        frame.setVisible(true);

    }


}
