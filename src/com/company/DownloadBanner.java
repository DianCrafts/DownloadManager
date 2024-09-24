package com.company;
import javax.swing.*;
import java.awt.*;

public class DownloadBanner extends JPanel {
    private boolean flag;
    private int check;
    private boolean list;
    JRadioButton radioButton;
    public JProgressBar jb;
    JLabel label2 , sizeLabel , speed  , speedLabel;
    String address;
    private static int y;
    private static int counter = 0;
    public DownloadBanner(){

    }

    public DownloadBanner(String name , String address){
        y = 60;
        check = 0;
        flag = false;
        this.address = address;
        radioButton = new JRadioButton();
        radioButton.setBounds(645 , 10 , 20 , 20);
        radioButton.setBackground(Color.white);
        this.add(radioButton);
        counter ++;
        label2 = new JLabel(name);
        speedLabel = new JLabel("speed:");
        speed = new JLabel("0/00Mb");

        sizeLabel = new JLabel("24Mb");
        this.setBackground(Color.white);
        JLabel label = new JLabel(new ImageIcon(((new ImageIcon(
                "D:\\download.JPG").getImage()
                .getScaledInstance(25 , 25,
                        Image.SCALE_SMOOTH)))));
        label.setBackground(Color.white);
        this.setLayout(null);
        label2.setForeground(Color.black);
        sizeLabel.setForeground(Color.black);
        label2.setBackground(Color.black);
        sizeLabel.setBackground(Color.black);
        sizeLabel.setBounds(540 , 25 , 40 , 15);
        speedLabel.setBounds(600 , 0 , 50 , 15);
        speed.setBounds(600 , 25 , 50 , 15);
        speedLabel.setForeground(Color.black);
        speed.setForeground(Color.black);
        this.add(speed);
        this.add(speedLabel);
        this.add(sizeLabel);
        this.setBackground(new Color(50 , 50 , 50));
        label2.setBounds(540 , 2 , 40 , 15);
        jb = new JProgressBar(0 , 100);
        jb.setBounds(50 , 10 , 480 , 20);
        jb.setBackground(new Color(171 , 241 , 120));
        label.setBounds(0 , 0 , 45 , 45);
        jb.setValue(0);
        jb.setStringPainted(true);
        this.add(jb);
        this.add(label);
        this.add(label2);
    }
    public DownloadBanner(int limit , String name){
        counter ++;
        if (counter <= limit) {
            flag = true;
            JLabel label = new JLabel(new ImageIcon(((new ImageIcon(
                    "D:\\javascode\\midterm\\Picture\\download1.JPG").getImage()
                    .getScaledInstance(25, 25,
                            Image.SCALE_SMOOTH)))));
            this.setLayout(null);
            label.setBackground(Color.white);
            label.setForeground(Color.white);
            this.setBackground(new Color(50, 50, 50));
            jb = new JProgressBar(0, 100);
            jb.setBounds(50, 10, 530, 20);
            jb.setBackground(new Color(171, 241, 120));
            label.setBounds(0, 0, 45, 45);
            jb.setValue(0);
            jb.setStringPainted(true);
            this.add(jb);
            this.add(label);
        }
        else flag = false;
    }
    public void setSize(long a){
        sizeLabel = new JLabel(a + "");
        System.out.println(sizeLabel);
    }

    public boolean getFlag(){
        return flag;
    }

    public void isFlag(Boolean listCheck){
        if (listCheck == true)
            list = true;
        else
            list = false;
    }

//    @Override
//    public void run() {
//        try {
//            for (int i = jb.getValue(); i <= 100; i++) {
//                if (check == 2) {
//                    jb.setValue(0);
//                }
//                if (check == 1) {
//                    break;
//                }
//                if (check == 0) {
//                    jb.setValue(i);
//                    try {
//                        Thread.sleep(200);
//                    } catch (InterruptedException e) {
//                        e.printStackTrace();
//                    }
//                }
//            }
//        }catch (NullPointerException e){
//            System.out.println("q");
//        }
//        if (check == 0){
//            this.setBounds(30, y, 680, 40);
//            if (list == true) {
//                JDM.getPanel().remove(this);
//                JDM.getCompletePanel().add(this);
//                JDM.getPanel().repaint();
//                JDM.getCompletePanel().repaint();
//                for(int i = 0 ; i < JDM.getDownloadbannners().size() ; i++){
//                    JDM.getDownloadbannners().get(i).setBounds(30, JDM.getDownloadbannners().get(i).getBounds().y - 45, 680, 40);
//                }
//                JDM.setA(JDM.getA() - 45);
//            }
//            else if (list == false){
//                JDM.getQueuePanel().remove(this);
//                JDM.getCompletePanel().add(this);
//                JDM.getQueuePanel().repaint();
//                JDM.getCompletePanel().repaint();
//                for(int i = 0 ; i < JDM.getQueueList().size() ; i++){
//                    JDM.getQueueList().get(i).setBounds(30, JDM.getQueueList().get(i).getBounds().y - 45, 680, 40);
//                }
//                JDM.setX(JDM.getX() - 45);
//            }
//            y +=45;
//        }
//    }
    public void complete() {
        this.setBounds(30, y, 680, 40);
        if (list == true) {
            JDM.getPanel().remove(this);
            JDM.getCompletePanel().add(this);
            JDM.getPanel().repaint();
            JDM.getCompletePanel().repaint();
            for (int i = 0; i < JDM.getDownloadbannners().size(); i++) {
                JDM.getDownloadbannners().get(i).setBounds(30, JDM.getDownloadbannners().get(i).getBounds().y - 45, 680, 40);
            }
            JDM.setA(JDM.getA() - 45);
        } else if (list == false) {
            JDM.getQueuePanel().remove(this);
            JDM.getCompletePanel().add(this);
            JDM.getQueuePanel().repaint();
            JDM.getCompletePanel().repaint();
            for (int i = 0; i < JDM.getQueueList().size(); i++) {
                JDM.getQueueList().get(i).setBounds(30, JDM.getQueueList().get(i).getBounds().y - 45, 680, 40);
            }
            JDM.setX(JDM.getX() - 45);
        }
        y += 45;
    }
}
