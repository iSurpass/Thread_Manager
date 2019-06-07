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
    public static JTable tableRun;
    public static JTable tableDis;
    public static JPanel panelBb;
    public static DefaultTableModel model2;

    public static DefaultTableModel model1;
    public static DefaultTableModel model3;

    public static Object[] row;
    public static void main(String[] args) {


        JFrame frame = new JFrame("进程管理与调度模拟程序");
        FlowLayout flow = new FlowLayout();
        frame.setLayout(null);
        frame.setSize(1500,800);

        JMenuBar bar = new JMenuBar();
        JMenu menu = new JMenu("设置");
        menu.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showInternalConfirmDialog(frame,
                        "默认设置: 1个CPU 20s时间片", "information",
                        JOptionPane.YES_NO_CANCEL_OPTION,
                        JOptionPane.INFORMATION_MESSAGE);
            }
        });
        bar.add(menu);
        JMenu menu1 = new JMenu("关于");
        menu1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showInternalConfirmDialog(frame,
                        "抄袭必挂！！！", "0v0",
                        JOptionPane.YES_NO_CANCEL_OPTION,
                        JOptionPane.INFORMATION_MESSAGE);
            }
        });
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
        b2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                String pid = JOptionPane.showInputDialog("输入进程号");
                int stopPid = Integer.parseInt(pid);
                if ( model1.getRowCount() > 0){
                    if (stopPid == (int)model1.getValueAt(0,0) ){
                        JOptionPane.showMessageDialog(b2,"确定停止该进程？");
                        model1.removeRow(0);
                    }
                    return;
                }
                for (int i=0;i<model2.getRowCount();i++){
                    if (stopPid == (int)model2.getValueAt(i,0)){
                        JOptionPane.showMessageDialog(b2,"确定停止该进程？");
                        model2.removeRow(i);
                        return;
                    }
                }
                for (int i=0;i<model3.getRowCount();i++){
                    if (stopPid == (int)model3.getValueAt(i,0)){
                        JOptionPane.showMessageDialog(b2,"确定停止该进程？");
                        model2.removeRow(i);
                        return;
                    }
                }
                JOptionPane.showMessageDialog(b2,"不存在此进程");
            }
        });
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
        JButton bs1 = new JButton("停止");
        bs1.addActionListener(new ActionListener() {
          @Override
          public void actionPerformed(ActionEvent e) {
              int res = JOptionPane.showConfirmDialog(null, "是否结束该进程吗？", "是否继续", JOptionPane.YES_NO_OPTION);
              if (res == JOptionPane.YES_OPTION) {

                  model1.removeRow(0);

              }
          }
      });
        panelAa.add(bs1);
        JButton bz1 = new JButton("阻塞");
        bz1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int res=JOptionPane.showConfirmDialog(null, "是否阻塞该进程吗？", "是否继续", JOptionPane.YES_NO_OPTION);
                 if(res==JOptionPane.YES_OPTION){
                     int index = (int) model1.getValueAt(0,0);
                     model1.removeRow(0);
                     Produce produce = produceMap.get(index);
                     Object[] row = new Object[2];
                     row[0] = produce.getPID();
                     row[1] = produce.getName();
                     model3.addRow(row);
                 }else{
                     return;
                 }
            }
        });
        panelAa.add(bz1);
        panelA.add(panelAa);
        JPanel panelAb = new JPanel();
        panelAb.setSize(220,200);
        panelA.add(panelAb);

        Object[] columsNames = {"PID","名称","时间片","剩余运行时间"};
        model1 = new DefaultTableModel(columsNames,0);
        tableRun = new JTable(model1);
        panelAb.add(new JScrollPane(tableRun));


        JPanel panelB = new JPanel();
        panelB.setBorder(new TitledBorder("就绪态"));
        panelB.setSize(120,220);
        JPanel panelBa = new JPanel();
        panelBa.setSize(40,20);
        JButton bs2 = new JButton("停止");
        bs2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int res=JOptionPane.showConfirmDialog(null, "是否结束该进程吗？", "是否结束", JOptionPane.YES_NO_OPTION);
                if(res==JOptionPane.YES_OPTION){
                    int index = tablePre.getSelectedRow();
                    model2.removeRow(index);
                }else{
                    return;
                }
            }
        });
        panelBa.add(bs2);
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
        JButton bs3 = new JButton("停止");
        bs3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int res=JOptionPane.showConfirmDialog(null, "是否结束该进程吗？", "确定继续？", JOptionPane.YES_NO_OPTION);
                if(res==JOptionPane.YES_OPTION){
                    int index = tableDis.getSelectedRow();
                    model3.removeRow(index);
                }else{
                    return;
                }
            }
        });
        panelCa.add(bs3);
        JButton jl = new JButton("激活");
        panelCa.add(jl);
        jl.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                int res=JOptionPane.showConfirmDialog(null, "是否激活该进程吗？", "是否继续", JOptionPane.YES_NO_OPTION);
                if(res==JOptionPane.YES_OPTION){
                    int index = tableDis.getSelectedRow();
                    model3.removeRow(index);
                    Produce produce = produceMap.get(index);
                    Object[] row = new Object[3];
                    row[0] = produce.getPID();
                    row[1] = produce.getName();
                    row[2] = produce.getTimeRest();
                    model2.addRow(row);
                }else{
                    return;
                }
            }
        });
        panelC.add(panelCa);
        JPanel panelCb = new JPanel();
        panelCb.setSize(220,200);
        panelC.add(panelCb);

        Object[] columsNames2 = {"PID","名称"};
        model3 = new DefaultTableModel(columsNames2,0);
        tableDis = new JTable(model3);
        panelCb.add(new JScrollPane(tableDis));

        panel.add(panelA);
        panel.add(panelB);
        panel.add(panelC);
        frame.add(panel);


        frame.setVisible(true);

    }

    /*public void excute(Produce produce){

        int delay=5000;    //时间间隔，单位为毫
        ActionListener taskPerformer=new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e) {
                //int index = model1.getDataVector().indexOf(row);
                //index++;
                System.out.println("3333"+model1.getDataVector().get(0));
                //model1.getDataVector().remove(row);
                model1.removeRow(0);
                row[0] = produce.getPID();
                row[1] = produce.getName();
                row[2] = produce.getTimeSlice();
                row[3] = produce.getTimeRest();
                model1.addRow(row);
                int time = Integer.parseInt(produce.getTimeRest());
                int silce = produce.getTimeSlice();
                time -= 5;
                if (time < 0){
                    model1.removeRow(0);
                }
                silce -= 5;
                if (silce == -5){
                    flag = true;
                    model1.removeRow(0);
                    //model1.getDataVector().removeElementAt(index);
                    row[2] = produce.getTimeRest();
                    silce = 20;
                    int nextIndex = (int) model2.getValueAt(0,0);
                    Produce produceNext = produceMap.get(nextIndex);
                    Object[] rowNext = new Object[4];
                    rowNext[0] = produceNext.getPID();
                    rowNext[1] = produceNext.getName();
                    rowNext[2] = produceNext.getTimeSlice();
                    rowNext[3] = produceNext.getTimeRest();
                    model2.removeRow(0);
                    //model2.getDataVector().removeElementAt(nextIndex);
                    model1.addRow(rowNext);
                    model2.addRow(row);
                    recycle(produceNext);
                    //new Timer(delay,taskPerformer).start();
                }
                produce.setTimeRest(String.valueOf(time));
                produce.setTimeSlice(silce);
            }
        };
        Timer timer = new Timer(delay,taskPerformer);
        timer.start();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e1) {
            e1.printStackTrace();
        }
        int delay1=1000;    //时间间隔，单位为毫秒
        ActionListener taskPerformer1=new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e) {
                //model1.removeRow(0);
                model1.getDataVector().remove(row);
            }
        };
        Timer timer1 =new Timer(delay1,taskPerformer1);
        timer1.start();
        if (flag){
            timer.stop();
            timer1.stop();
        }
    }*/
}
