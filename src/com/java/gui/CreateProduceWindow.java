package com.java.gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static com.java.gui.Frist.*;


/**
 * 创建进程窗口类
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
        this.setSize(400,300);
        this.setVisible(true);
        this.setTitle("创建进程");
        GridLayout g1 = new GridLayout(1,2);
        GridLayout g2 = new GridLayout(1,2);
        JPanel panel = new JPanel(g1);
        panel.setSize(400,50);
        JPanel panel1 = new JPanel(g2);
        panel1.setSize(400,50);
        panel1.setLocation(0,50);
        JPanel panel2 = new JPanel();
        panel2.setSize(400,50);
        panel2.setLocation(0,50);
        JLabel label = new JLabel("进程名称",JLabel.LEFT);
        label.setFont(new java.awt.Font("Dialog", 1, 15));
        JLabel label1 = new JLabel("运行时间",JLabel.LEFT);
        label1.setFont(new java.awt.Font("Dialog", 1, 15));
        b1.addActionListener(this);
        b2.addActionListener(this);


        panel.setLayout(g1);
        panel1.setLayout(g2);
        JPanel pan1 = new JPanel();
        JPanel pan2 = new JPanel();
        pan1.setLayout(new FlowLayout());
        pan2.setLayout(new FlowLayout());
        pan1.add(label);
        pan1.add(b1);
        pan2.add(b2);

        panel.add(label);
        panel.add(textField);
        panel1.add(label1);
        panel1.add(textField1);
        panel2.add(b1);
        panel2.add(b2);
        this.setLayout(new GridLayout(6,1));
        JPanel pan3 = new JPanel();
        JPanel pan4 = new JPanel();
        this.add(pan3);
        this.add(panel);
        this.add(pan4);
        this.add(panel1);
        this.add(panel2);
    }


    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == b1){
            if (textField.getText() == null || textField.getText().equals("")){
                JOptionPane.showMessageDialog(null,"您进程号输入为空");
                return;
            }
            if (textField1.getText() == null || textField1.getText().equals("")){
                JOptionPane.showMessageDialog(null,"您时间输入为空");
                return;
            }

            JOptionPane.showMessageDialog(null,"已创建PID为"+pid+"的进程");

            if (pid == 5){
                JOptionPane.showMessageDialog(null,"抱歉,您创建进程已满");
                return;
            }

            String name = textField.getText();
            String runTime = textField1.getText();
            int PID = pid++;
            Produce produce = new Produce();
            produce.setName(name);
            produce.setPID(PID);
            produce.setStatus(Status.RUNNING);
            produce.setTimeSlice(20);
            produce.setTimeRest(Integer.parseInt(runTime));

            //将新创建的进程put进map中
            produces.add(produce);
            produceMap.put(produce.getPID(),produce);

            //新创建的进程预先加入到就绪队列中
            row[0] = produce.getPID();
            row[1] = produce.getName();
            row[2] = produce.getTimeRest();
            model2.addRow(row);

            //如果运行队列的进程数小于1时，方可进入队列
            if (model1.getRowCount() < 1){
                model2.removeRow(0);
                row[0] = produce.getPID();
                row[1] = produce.getName();
                row[2] = 20;
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

                            //当 CPU 未占用时
                            if (model1.getRowCount() == 0){
                            //当就绪队列无进程时
                            if (model2.getRowCount() == 0){
                                return;
                            }

                            Object[] row = new Object[4];
                            row[0] = model2.getValueAt(0,0);
                            row[1] = model2.getValueAt(0,1);
                            Produce produce1 = new Produce();
                            produce1.setPID((Integer) model2.getValueAt(0,0));
                            produce1.setName((String) model2.getValueAt(0,1));
                            produce1.setTimeRest((Integer) model2.getValueAt(0,2));

                            row[2] = 20;
                            row[3] = produce1.getTimeRest();
                            model2.removeRow(0);
                            model1.addRow(row);
                            return;
                        }

                        //将新创建的进程加入到运行队列中
                        Produce produce1 = new Produce();
                        int PID = (Integer)model1.getValueAt(0,0);
                        produce1.setPID((Integer) model1.getValueAt(0,0));
                        produce1.setName((String) model1.getValueAt(0,1));
                        produce1.setTimeRest((Integer)model1.getValueAt(0,3));
                        produceMap.put(PID,produce1);
                        int timeSlice = (int) model1.getValueAt(0,2);
                        timeSlice -= 5;
                        model1.setValueAt(timeSlice,0,2);
                        int restTime = (int) model1.getValueAt(0,3);
                        restTime -= 5;
                        produce1.setTimeRest(restTime);
                        model1.setValueAt(restTime,0,3);

                        Object[] rowPre = new Object[4];
                        rowPre[0] = model1.getValueAt(0,0);
                        rowPre[1] = model1.getValueAt(0,1);
                        rowPre[2] = model1.getValueAt(0,2);
                        rowPre[3] = model1.getValueAt(0,3);

                        if (produce1.getTimeRest() <= 0){
                            model1.removeRow(0);
                        }

                        if (timeSlice == 0){
                            rowPre[2] = produce1.getTimeRest();
                            model1.removeRow(0);
                            model2.addRow(rowPre);
                        }
                    }
                };
                Timer timer = new Timer(delay,taskPerformer);
                timer.start();
            }
            this.setVisible(false);
        }
        else if(e.getSource() == b2){
            this.setVisible(false);
        }
    }
    }

