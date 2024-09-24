package com.company;

import java.io.*;

public class FileOutputstream {
    FileOutputStream fop = null;
    File file;
    FileWriter fileWriter;
    PrintWriter printWriter;
    private String filePath;
    public FileOutputstream(String filePath){
        this.filePath = filePath;
    }

    public void output(String URl , String name) {
        try {
            file = new File(filePath);
            fop = new FileOutputStream(file , true);
            fileWriter = new FileWriter(filePath , true);
            printWriter = new PrintWriter(fileWriter);
            // if file doesnt exists, then create it
            if (!file.exists()) {
                file.createNewFile();
            }
            URl = "URL: " + URl;
            name = "name: " + name + "               ";
            // get the content in bytes
            byte[] nm = name.getBytes();
            byte[] url = URl.getBytes();
            printWriter.println();
            fop.write(nm);
            printWriter.println();
            fop.write(url);
            printWriter.println("*********************************************************************");
            fop.flush();
            fop.close();
            printWriter.flush();
            printWriter.close();
            System.out.println("Done");
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (fop != null) {
                    fop.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
