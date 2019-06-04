package com.java.gui;

import javax.swing.*;
import javax.swing.event.TableModelEvent;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static com.java.gui.Frist.*;


/**
 * @author Juniors
 */
public class CreateProduceWindow extends JFrame implements ActionListener {

    public static int pid = 0;

    private JButton b1 = new JButton("确定");

    private JButton b2 = new JButton("取消");

    private JTextField textField = new JTextField();

    private JTextField textField1 = new JTextField();

    public static Object[] row = new Object[4];

    public CreateProduceWindow(){
        this.setSize(400,400);
        this.setVisible(true);
        this.setTitle("创建进程");
        GridLayout g1 = new GridLayout(1,2);
        GridLayout g2 = new GridLayout(1,2);
        JPanel panel = new JPanel(g1);
        panel.setSize(400,30);
        JPanel panel1 = new JPanel(g2);
        panel1.setSize(400,30);
        panel1.setLocation(0,30);
        JPanel panel2 = new JPanel();
        panel2.setSize(400,30);
        panel2.setLocation(0,30);
        JLabel label = new JLabel("进程名称",JLabel.LEFT);
        JLabel label1 = new JLabel("运行时间",JLabel.LEFT);
        b1.addActionListener(this);
        b2.addActionListener(this);
        panel.add(label);
        panel.add(textField);
        panel1.add(label1);
        panel1.add(textField1);
        panel2.add(b1);
        panel2.add(b2);
        this.add(panel);
        this.add(panel1);
        this.add(panel2);
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == b1){
            System.out.println("234234");
            String name = textField.getText();
            String runTime = textField1.getText();
            int PID = pid++;
            Produce produce = new Produce();
            produce.setName(name);
            produce.setPID(PID);
            produce.setStatus(Status.RUNNING);
            produce.setTimeSlice(20);
            produce.setTimeRest(runTime);

            produces.add(produce);
            produceMap.put(produce.getPID(),produce);

            row[0] = produce.getPID();
            row[1] = produce.getName();
            row[2] = produce.getTimeRest();
            model2.addRow(row);
            int index = model2.getDataVector().indexOf(row);
            index++;
            System.out.println(index);
            if (model1.getRowCount() < 1){
                model2.removeRow(index);
                row[2] = produce.getTimeSlice();
                row[3] = produce.getTimeRest();
                try {
                    model1.addRow(row);
                    Thread.sleep(1000);
                } catch (InterruptedException e1) {
                    e1.printStackTrace();
                }
                int delay=5000;    //时间间隔，单位为毫秒
                ActionListener taskPerformer=new ActionListener()
                {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        int index = model1.getDataVector().indexOf(row);
                        index++;
                        model1.removeRow(index);
                        row[0] = produce.getPID();
                        row[1] = produce.getName();
                        row[2] = produce.getTimeSlice();
                        row[3] = produce.getTimeRest();
                        model1.addRow(row);
                        int time = Integer.parseInt(produce.getTimeRest());
                        int silce = produce.getTimeSlice();
                        silce -= 5;
                        if (silce == 0){
                            model1.getDataVector().removeElementAt(index);
                            row[2] = produce.getTimeRest();
                            model2.addRow(row);
                            silce = 20;
                            int nextIndex = (int) model2.getValueAt(0,0);
                            Produce produceNext = produceMap.get(nextIndex);
                            Object[] rowNext = new Object[4];
                            rowNext[0] = produceNext.getPID();
                            rowNext[1] = produceNext.getName();
                            rowNext[2] = produceNext.getTimeSlice();
                            rowNext[3] = produceNext.getTimeRest();
                            model2.getDataVector().removeElementAt(nextIndex);
                            model1.addRow(rowNext);
                            //new Timer(delay,taskPerformer).start();
                        }
                        time -= 5;
                        if (time < 0){
                            model1.removeRow(0);
                        }
                        produce.setTimeRest(String.valueOf(time));
                        produce.setTimeSlice(silce);
                    }
                };
                new Timer(delay,taskPerformer).start();
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
                        model1.getDataVector().remove(row);
                    }
                };
                new Timer(delay1,taskPerformer1).start();
            }
            this.setVisible(false);
        }
        else if(e.getSource() == b2){
            this.setVisible(false);
        }
    }
}
