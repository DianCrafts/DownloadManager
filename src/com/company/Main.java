package com.company;

import javax.swing.*;
import java.awt.*;
/**
 * run class
 * gui part of a download manager program
 * @author mahdie ghorbanian
 * @version 97.2.29
 */
public class Main {

    public static void main(String[] args) throws ClassNotFoundException, UnsupportedLookAndFeelException, InstantiationException, IllegalAccessException {
        SavingData savingData = new SavingData();
        UIManager.setLookAndFeel("javax.swing.plaf.metal.MetalLookAndFeel");
        JDM jdm = new JDM();
        savingData.settingInputStream();
        System.out.println(SavingData.getDownloadLimit());
        System.out.println(SavingData.getLookAndFeel());
        try {
            if (SavingData.getDownloadLimit() != 0)
                JDM.setDownloadLimit(SavingData.getDownloadLimit());
//                JDM.getSlider().setValue(SavingData.getDownloadLimit());
        }catch (NullPointerException e){
            System.out.println("");
        }
        if (SavingData.getLookAndFeel() != null)
            switch (SavingData.getLookAndFeel()){
                case "metal":
                    System.out.println("@@@@");
                    UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
                    break;
                case "windows":
                    UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsClassicLookAndFeel");
                    break;
            }
    }
}
