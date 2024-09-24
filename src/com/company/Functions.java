package com.company;

import javax.swing.*;
import java.io.File;
import java.util.ArrayList;

import static javax.swing.UIManager.setLookAndFeel;

public class Functions {
    private ArrayList queue;
    private static ArrayList completeList;
    private int temp1 , temp2;
    private static String fileLookAndFeel;
    private static JPanel panel1;
    private static int z;
    public Functions(JPanel panel){
        z = 20;
        completeList = new ArrayList();
        queue = new ArrayList();
        this.panel1 = panel;
    }
    public void trash(ArrayList<DownloadBanner> downloadBanners , JPanel panel , int a){
        for (int i = 0 ; i < downloadBanners.size() ; i++) {
            if (downloadBanners.get(i).radioButton.isSelected()) {
                FileOutputstream fileOutputstream = new FileOutputstream("remove.jdm");
                fileOutputstream.output(downloadBanners.get(i).address , downloadBanners.get(i).label2.getText());
                panel.remove(downloadBanners.get(i));
                downloadBanners.remove((i));
                for (int j = i; j < downloadBanners.size(); j++) {
                    a -= 45;
                    downloadBanners.get(j).setBounds(30, downloadBanners.get(j).getBounds().y - 45, 680, 40);
                    temp1 = a;
                }
            }
        }
    }
    public int getA(){
        return temp1;
    }
    public int getX(){
        return temp2;
    }
//    public static void complete(DownloadBanner downloadBanner){
//        downloadBanner.setBounds(30 , z , 680 , 40);
//        z =+ 45;
//        completeList.add(downloadBanner);
//        panel1.add(downloadBanner);
//        panel1.repaint();
//    }
    public void queueTrash(ArrayList<DownloadBanner> queueList , JPanel queuePanel , int x){
        for (int i = 0 ; i < queueList.size() ; i++) {
            if (queueList.get(i).radioButton.isSelected()) {
                FileOutputstream fileOutputstream = new FileOutputstream("remove.jdm");
                fileOutputstream.output(queueList.get(i).address , queueList.get(i).label2.getText());
                queuePanel.remove(queueList.get(i));
                queueList.remove((i));
                for (int j = i; j < queueList.size(); j++) {
                    x -= 45;
                    queueList.get(j).setBounds(30, queueList.get(j).getBounds().y - 45, 680, 40);
                    temp2 = x;
                }
            }
        }
    }
    public void fileChooserAction(JFileChooser fc , JTextField textField1 , JTextField addressBar , JFrame fileChooser){
        int returnValue = fc.showOpenDialog(null);
        if (returnValue == JFileChooser.APPROVE_OPTION) {
            File selectedFile = fc.getSelectedFile();
            textField1.setText(selectedFile.getName());
            addressBar.setText(selectedFile.getPath());
            fileChooser.dispose();

        } else if (returnValue == JFileChooser.CANCEL_OPTION)
            fileChooser.dispose();

    }
    public void LookAndFeel(JComboBox lookAndFeel   , JFrame frame){
        if (lookAndFeel.getSelectedIndex() == 0) {
            fileLookAndFeel = "metal";
            try {
                setLookAndFeel("javax.swing.plaf.metal.MetalLookAndFeel");
            } catch (ClassNotFoundException e1) {
                e1.printStackTrace();
            } catch (InstantiationException e1) {
                e1.printStackTrace();
            } catch (IllegalAccessException e1) {
                e1.printStackTrace();
            } catch (UnsupportedLookAndFeelException e1) {
                e1.printStackTrace();
            }
        }
        if (lookAndFeel.getSelectedIndex() == 1) {
            fileLookAndFeel = "nimbus";
            try {
                UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
            } catch (ClassNotFoundException e1) {
                e1.printStackTrace();
            } catch (InstantiationException e1) {
                e1.printStackTrace();
            } catch (IllegalAccessException e1) {
                e1.printStackTrace();
            } catch (UnsupportedLookAndFeelException e1) {
                e1.printStackTrace();
            }
            frame.setVisible(true);
        }
        if (lookAndFeel.getSelectedIndex() == 2) {
            fileLookAndFeel = "windows";
            try {
                UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsClassicLookAndFeel");
            } catch (ClassNotFoundException e1) {
                e1.printStackTrace();
            } catch (InstantiationException e1) {
                e1.printStackTrace();
            } catch (IllegalAccessException e1) {
                e1.printStackTrace();
            } catch (UnsupportedLookAndFeelException e1) {
                e1.printStackTrace();
            }
        }
        frame.repaint();
    }
    public void forbidden(String filterString){

    }
    public String getFileLookAndFeel(){
        return fileLookAndFeel;
    }

}
