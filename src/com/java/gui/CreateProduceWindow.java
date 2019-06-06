package com.java.gui;

import com.sun.scenario.effect.impl.prism.PrReflectionPeer;

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

    public static boolean flag = false;

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
            JOptionPane.showMessageDialog(null,"已创建PID为"+pid+"的进程");
            /*int res=JOptionPane.showConfirmDialog(null, "已创建进程1", "信息", JOptionPane.YES_OPTION);
            if(res==JOptionPane.YES_OPTION){
            }*/
            System.out.println("234234");
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
            int index = model2.getDataVector().indexOf(row);
            index++;
            System.out.println(index);

            //如果运行队列的进程数小于1时，方可进入队列
            if (model1.getRowCount() < 1){
                model2.removeRow(index);
                row[0] = produce.getPID();
                row[1] = produce.getName();
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


                        if (model1.getRowCount() == 0){
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
                        }


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

                        if (produce1.getTimeRest() == 0){
                            model1.removeRow(0);
                        }

                        if (timeSlice == 0){
                            rowPre[2] = produce1.getTimeRest();
                            model1.removeRow(0);
                            model2.addRow(rowPre);
                        }
                        //int index = model1.getDataVector().indexOf(row);
                        //index++;
                        /*model1.removeRow(0);
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
                            //flag = true;
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
                        }
                        produce.setTimeRest(String.valueOf(time));
                        produce.setTimeSlice(silce);*/
                    }
                };
                Timer timer = new Timer(delay,taskPerformer);
                timer.start();
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e1) {
                    e1.printStackTrace();
                }
                /*int delay1=1000;    //时间间隔，单位为毫秒
                ActionListener taskPerformer1=new ActionListener()
                {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        model1.getDataVector().remove(row);
                    }
                };
                Timer timer1 = new Timer(delay1,taskPerformer1);
                timer1.start();
                if (flag){
                    timer.stop();
                    timer1.stop();
                }*/
            }
            this.setVisible(false);
        }
        else if(e.getSource() == b2){
            this.setVisible(false);
        }
    }

    public void recycle(Produce produce){
            int i = 0;
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
                    int time = produce.getTimeRest();//Integer.parseInt(produce.getTimeRest());
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
                    produce.setTimeRest(time);
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
        }
    }

