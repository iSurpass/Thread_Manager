package com.java.gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * @author Juniors
 */
public class ComputeTest extends JFrame implements ActionListener {

    static ComputeTest frame = new ComputeTest();
    static JButton[] b = new JButton[10];
    static JButton bp,ba,bs,bm,bd,br,bsqrt,by,bx1,bz,Back,C;
    static JTextField text;
    static boolean fristDigit = true;
    static String operator;
    static double lastDigit = 0;

    static int i = 0;

    public static void main(String[] args) {
        for (;i<=9;i++){
            b[i] = new JButton(""+i);
        }
        b[0].addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                text.setText(text.getText()+"0");
            }
        });b[1].addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                text.setText(text.getText()+"1");
            }
        });b[2].addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                text.setText(text.getText()+"2");
            }
        });b[3].addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                text.setText(text.getText()+"3");
            }
        });b[4].addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                text.setText(text.getText()+"4");
            }
        });b[5].addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                text.setText(text.getText()+"5");
            }
        });b[6].addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                text.setText(text.getText()+"6");
            }
        });b[7].addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                text.setText(text.getText()+"7");
            }
        });b[8].addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                text.setText(text.getText()+"8");
            }
        });b[9].addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                text.setText(text.getText()+"9");
            }
        });
        bp = new JButton(".");bp.addActionListener(frame);ba = new JButton("+");ba.addActionListener(frame);bs = new JButton("-");bs.addActionListener(frame);bm = new JButton("x");bm.addActionListener(frame);
        bd = new JButton("÷");bd.addActionListener(frame);br = new JButton("=");br.addActionListener(frame);bsqrt = new JButton("sqrt");bsqrt.addActionListener(frame);by = new JButton("%");by.addActionListener(frame);
        bx1 = new JButton("1/x");bx1.addActionListener(frame);bz = new JButton("+/-");bz.addActionListener(frame);Back = new JButton("Back");C = new JButton("C");
        Back.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String resText = text.getText();
                int i = resText.length();
                if (i > 0){
                    resText = resText.substring(0,i-1);
                    text.setText(resText);
                }
            }
        });
        C.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                text.setText("");
                fristDigit = true;
                operator = "";
            }
        });
        frame.setTitle("Compute");frame.setLayout(null);frame.setSize(600,1000);

        JMenuBar bar = new JMenuBar();JMenu jMenu = new JMenu("File");JMenu jMenu1 = new JMenu("Help");JMenuItem item = new JMenuItem("About");

        bar.add(jMenu);
        bar.add(jMenu1);
        jMenu1.add(item);
        frame.setJMenuBar(bar);

        text = new JTextField();
        text.setSize(500,20);
        text.setHorizontalAlignment(JTextField.RIGHT);
        frame.add(text);

        JPanel panel1 = new JPanel();
        panel1.setSize(500,40);panel1.setLocation(0,20);panel1.setLayout(new GridLayout(1,2));panel1.add(Back);panel1.add(C);
        frame.add(panel1);

        GridLayout grid = new GridLayout(5,4);
        JPanel panel = new JPanel();panel.setSize(500,400);panel.setLayout(grid);panel.setLocation(0,60);
        frame.add(panel);
        panel.add(b[7]);panel.add(b[8]);panel.add(b[9]);panel.add(bd);panel.add(b[4]);panel.add(b[5]);panel.add(b[6]);panel.add(bm);panel.add(b[1]);panel.add(b[2]);panel.add(b[3]);panel.add(bs);panel.add(b[0]);panel.add(bz);panel.add(bp);panel.add(ba);panel.add(bx1);panel.add(by);panel.add(bsqrt);panel.add(br);


        frame.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String target = e.getActionCommand();
        operate(target);
    }

    private double getNumberFromText() {
        double result = 0;
        try {
            result = Double.valueOf(text.getText()).doubleValue();
        } catch (NumberFormatException e) {
        }
        return result;
    }

    public void operate(String Text){
        double x = getNumberFromText();
        if (Text.equals("sqrt")){
            x = Math.sqrt(x);
        }else if (Text.equals("+/-")){
            x = x * (-1);
        }else if(Text.equals("1/x")){
            if (x == 0.0){
                text.setText("Error");
            }else {
                x = 1 / x ;
            }
        }
        else if(!Text.equals("="))
        {
            lastDigit = x;
            operator = Text;
        }
        else {
            if(operator.equals("+")) {
                x += lastDigit;
            }
            else if(operator.equals("-")) {
                x = lastDigit - x;
            }
            else if(operator.equals("x")) {
                x *= lastDigit;
            }
            else if(operator.equals("÷"))
            {
                if(x==0.0)
                {
                    text.setText("Error");
                    return ;
                }
                else
                    x = lastDigit/x;
            }

        }
        fristDigit = true;
        setText(x);
    }

    //更新文本框显示
    public void setText(double res)
    {
        long t1;
        double t2;
        t1 = (long) res;
        t2 = res - t1;
        if (t2 == 0) {
            text.setText(String.valueOf(t1));
        } else {
            text.setText(String.valueOf(res));
        }
    }

}
