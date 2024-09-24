package com.company;

import javax.swing.*;
import java.io.*;
import java.util.ArrayList;

/**
 * a class for saving datas in a files
 */
public class SavingData {
    private ArrayList<String> str;
    private static String lookAndFeel;
    private static int downloadLimit;
    private String[] data = {""};
    FileOutputStream fop = null;
    ArrayList<DownloadBanner> history;
    private static int z;

    /**
     * constructor for initializing
     */
    public SavingData() {
        str = new ArrayList<>();
        z = 20;
        history = new ArrayList<>();
    }

    /**
     * method for serializing
     * @param list list of download banners
     * @param filePath
     */
    public void serializeLists(ArrayList<DownloadBanner> list, String filePath) {
        FileOutputStream fs = null;
        try {
            fs = new FileOutputStream(filePath);
        } catch (FileNotFoundException ex) {
            System.err.println(ex);
        }
        try {
            ObjectOutputStream obj = new ObjectOutputStream(fs);
            obj.writeInt(list.size());
            for (DownloadBanner list1 : list) {
                obj.writeObject(list1);
            }
            obj.close();
            fs.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * a method for deserializing
     * @param filePath address of file
     * @param panel a panel that download banner exists in
     */
    public void deserializae(String filePath, JPanel panel) {
        int num;
        try {
            FileInputStream fileIn = new FileInputStream(filePath);
            ObjectInputStream in = new ObjectInputStream(fileIn);
            DownloadBanner downloadBanner;
            num = in.readInt();
            for (int i = 0; i < num; i++) {
                downloadBanner = (DownloadBanner) in.readObject();
                downloadBanner.addMouseListener(new Mouse(downloadBanner.label2.getText(), downloadBanner.sizeLabel.getText(), downloadBanner.speed.getText(), downloadBanner.jb.getValue()));
                history.add(downloadBanner);
                panel.add(downloadBanner);
                downloadBanner.setBounds(30, z, 680, 40);
                z += 45;
            }
            in.close();
            fileIn.close();
        } catch (IOException i) {
            i.printStackTrace();
            return;
        } catch (ClassNotFoundException c) {
            c.printStackTrace();
            return;
        }
    }

    /**
     * a method for saving satteing
     * @param lookAndfeel look and feel of programm
     * @param downloadNumber limit number of downloading
     */
    public void settingOutput(String lookAndfeel, int downloadNumber) {
        FileOutputStream fs = null;
        try {
            fs = new FileOutputStream("setting.jdm");
        } catch (FileNotFoundException ex) {
            System.err.println(ex);
        }
        try {
            ObjectOutputStream obj = new ObjectOutputStream(fs);
            obj.writeObject(lookAndfeel);
            obj.writeObject(downloadNumber);
            obj.close();
            fs.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * a method for reading setting infos
     */
    public void settingInputStream() {
        try {
            FileInputStream fileIn = new FileInputStream("setting.jdm");
            ObjectInputStream in = new ObjectInputStream(fileIn);
            lookAndFeel = (String) in.readObject();
            downloadLimit = (int) in.readObject();
            in.close();
            fileIn.close();
        } catch (IOException i) {
            i.printStackTrace();
            return;
        } catch (ClassNotFoundException c) {
            c.printStackTrace();
            return;
        }
    }

    /**
     * method for reading datas from filter file
     */
    public void filterInputStream() {
        int counter = 0;
        String temp = "";
        try {
            String temp2;
            FileReader fileReader = new FileReader("filter.jdm");
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            while ((bufferedReader.readLine()) != null) {
                counter++;
            }
            FileReader fileReader1 = new FileReader("filter.jdm");
            BufferedReader bufferedReader1 = new BufferedReader(fileReader1);
            temp = bufferedReader1.readLine();
            data = temp.split("\\*");
            bufferedReader1.close();
        } catch (IOException e) {
            System.out.println("");
        }
    }

    /**
     * method for returning filter sites
     * @return array of string
     */
    public String[] getData(){
        return data;
    }

    /**
     * method for saving filter sites
     * @param address
     */
    public void filterOutput(String address) {
        try {
            FileWriter fileWriter;
            BufferedWriter bufferedWriter;
            fileWriter = new FileWriter("filter.jdm", true);
            bufferedWriter = new BufferedWriter(fileWriter);
            bufferedWriter.write(address + "*");
            bufferedWriter.close();
            fileWriter.close();
        }catch (IOException e){
            System.out.println("");
        }
    }

    /**
     * return look and feel
     * @return string
     */
    public static String getLookAndFeel(){
        return lookAndFeel;
    }

    /**
     * returns allowed number of downloads
     * @return
     */
    public static int getDownloadLimit(){
        return downloadLimit;
    }
}
