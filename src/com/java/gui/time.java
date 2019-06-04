package com.java.gui;

/**
 * @author Juniors
 */
import javax.swing.JFrame;
import javax.swing.JLabel;
import java.util.Calendar;
import java.util.Date;
import javax.swing.JFrame;
import javax.swing.JLabel;
import java.util.Calendar;
import java.util.Date;
public class time
{
    public static void main(String[] args)
    {
        JFrame f=new JFrame("test");
        f.setVisible(true);
        JLabel L=new JLabel();
        f.setLayout(null);
        new Thread(new Time(L)).start();
        L.setBounds(300,300,100,30);
        f.add(L);
        f.validate();
    }
}
class Time implements Runnable
{
    JLabel L;
    public Time(JLabel L)
    {
        this.L=L;
    }
    public void run()
    {
        while(true)
        {
            Calendar C=Calendar.getInstance();
            C.setTime(new Date());
            int h=C.get(Calendar.HOUR);
            int m=C.get(Calendar.MINUTE);
            int s=C.get(Calendar.SECOND);
            L.setText(""+h+"/"+m+"/"+s);
            try
            {
                Thread.sleep(1000);
            }
            catch(Exception e)
            {}
        }
    }
}