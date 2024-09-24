package com.company;

import javax.swing.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

/**
 * a class for mouse handling
 */
public class Mouse implements MouseListener{
    private String name , URL , speed , size;
    private int percent;
    public Mouse(String name  , String speed , String size , int percent){
        this.size = size;
        this.name = name;
        this.URL = URL;
        this.speed = speed;
        this.percent = percent;
    }
    public Mouse(){

    }
    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {
        if (e.getModifiers() == MouseEvent.BUTTON3_MASK)                    //right click
        {
            JFrame jFrame = new JFrame();
            jFrame.setBounds(500 , 400 , 400 , 400);
            JTextArea textArea = new JTextArea("\n\n"+"   name: "+ name+"\n\n"+ "   size: " + size + "\n\n" + "   speed: " + speed+"\n\n" + "   URL: " +URL +"\n\n" + "   time: "+ "00:00\n\n" + "   percent: " + percent );
            jFrame.setLayout(null);
            textArea.setBounds(5 , 5 , 400 , 400);
            jFrame.add(textArea);
            jFrame.setVisible(true);
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        if (e.getClickCount() == 2){                                        //double click
            JFrame f = new JFrame();
            f.setLayout(null);
            JOptionPane.showMessageDialog(f,"playing...");
        }
    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}
