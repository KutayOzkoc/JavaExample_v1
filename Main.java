package com.company;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Main extends JFrame implements ActionListener {
    private static int i = 1;
    private JLabel lb1;
    private JLabel lb;
    private JButton Threadbutton;
    private JButton but1;
    private JButton but2;
    private JButton but3;
    public Main(){
        lb = new JLabel("Label");
        Threadbutton = new JButton("Thread-Button");
        lb1 = new JLabel("Thread-Label");
        but1 = new JButton("Buton1");
        but2 = new JButton("Buton2");
        but3 = new JButton("cıkıs");
        Container c = getContentPane();
        setLayout(new FlowLayout());
        c.add(lb);
        c.add(but1);
        c.add(but2);
        c.add(but3);

        c.add(lb1);
        c.add(Threadbutton);

        but1.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){ // Annonym Inner Class
                lb.setText("1");
            }
        });
        myEventHandler meh = new myEventHandler(lb);
        but2.addActionListener(meh);
        but3.addActionListener(this);
        Threadbutton.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                tred t = new tred(lb1);
                    t.start();
            }
        });
    }

    public static class myEventHandler implements ActionListener{//Named inner Class
        private JLabel label;
        public myEventHandler(JLabel lb) {
            this.label = lb;
        }

        public void actionPerformed(ActionEvent e){
            label.setText("2");

        }
    }
    public static class tred extends Thread implements Runnable{
        private JLabel lb1;
        private int number;
        public tred(JLabel lb1){
            this.lb1 = lb1;
        }
        public void run(){
            int i = 0;
            try{
                while(i<5){
                    number = (int) (Math.random()*100);
                    lb1.setText(String.valueOf(number));
                    Thread.sleep(1000);
                    i++;
                }
            }
            catch (InterruptedException e){
                e.printStackTrace();
            }
        }
    }


    public void actionPerformed(ActionEvent e){
        if(e.getSource() == but3){
            System.exit(0);
        }
    }
    public static void main(String[] args) {
	Main m = new Main();
	m.setSize(200,200);
	m.addWindowListener(
	        new WindowAdapter(){// Annonim inner Class
	            public void windowClosing(WindowEvent e)
                {
                    System.exit(0);
                }
                public void windowDeactivated(WindowEvent e){
                    System.out.println(i);
	                i++;
                }
            }
    );

	m.setVisible(true);
    }
}
