package com.company;

import javax.swing.*;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;

public class Sort {
    private String sortMethod;
    private DownloadBanner temp;
    private JPanel panel;
    private String[] str;
    private ArrayList<DownloadBanner> list;
    private ArrayList<DownloadBanner> list1;

    public Sort(String sortMethod, ArrayList list, JPanel panel) {
        this.sortMethod = sortMethod;
        this.list = list;
        this.panel = panel;
        temp = new DownloadBanner();
        str = new String[list.size()];
        list1 = new ArrayList<>();
    }

    public Sort() {
    }

    public void setSortTime() {
        for (int i = 0; i < list.size(); i++) {
            str[i] = list.get(i).sizeLabel.getText();
        }
        Arrays.sort(str);
        for (int i = 0; i < list.size(); i++) {
            for (int j = 0; j < list.size(); j++) {
                if (str[i].equals(list.get(j).label2.getText()))
                    list1.add(list.get(j));
            }
        }
    }

    public void setSortName() {
        for (int i = 0; i < list.size(); i++) {
            str[i] = list.get(i).label2.getText();
        }
        Arrays.sort(str);
        for (int i = 0; i < list.size(); i++) {
            for (int j = 0; j < list.size(); j++) {
                if (str[i].equals(list.get(j).label2.getText()))
                    list1.add(list.get(j));
            }
        }
    }

    public void sort() {
        int x = 20;
        if (sortMethod.equals("name")) {
            setSortName();
        }
        if (sortMethod.equals("size")) {
            setSortTime();
        }
        for (int i = 0; i < list.size(); i++) {
          //  System.out.println(list.get(i).label2.getText());
            panel.remove(list.get(i));
            panel.repaint();
        }
        for (int i = 0; i < list.size(); i++) {
            list1.get(i).setBounds(30, x, 680, 40);
            panel.add(list1.get(i));
            x += 45;
            panel.repaint();
        }
    }
}
