package com.company;

import javax.swing.*;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class DownloadTask extends SwingWorker<Void,Integer> {
    private float totalRead;                   //size of read file
    private static boolean flag;
    private int percent;                    //percent of read file
    private String fileName;                //name of file
    private String extention;
    private HttpURLConnection connection;
    private String address;
    private URL url;
    private static int check;
    private DownloadBanner downloadBanner;
    private JPanel panel;
    private int flag1;
    private ArrayList list;
    public DownloadTask(String address , DownloadBanner downloadBanner , String fileName , JPanel panel , int flag1 , ArrayList list){
        check = 0;
        this.address = address;
        this.downloadBanner = downloadBanner;
        this.fileName = fileName;
        this.panel = panel;
        this.flag1 = flag1;
        this.list = list;
    }
    public void setCheck(int a){
        if (check != 2){
            if (a == 1) check = 1;
            if (a == 2)
                check = 2;
            if (a == 0)
                check = 0;
        }
    }

    @Override
    protected Void doInBackground() throws Exception {
        {
            try {
                url = new URL(address);
                extention = address.substring(address.lastIndexOf(".") + 1 ,address.lastIndexOf(".")+4 );
            } catch (MalformedURLException e) {
                panel.remove(downloadBanner);
                list.remove(downloadBanner);
                if (flag1 == 1) {
                    JDM.setA(JDM.getA() - 45);
                }
                if (flag1 == 2)
                    JDM.setX(JDM.getX() - 45);
            }
        }
        try {
            if ("http".equals(url.getProtocol())) {
                connection = (HttpURLConnection) url.openConnection();
            } else if ("https".equals(url.getProtocol())) {
                connection = (HttpURLConnection) url.openConnection();
            } else {
                System.out.println("no supported");
            }
            connection.connect();
            if (connection.getResponseCode() / 100 != 2) {
                throw new IOException(connection.getResponseMessage());
            }
        } catch (IOException ex) {
            System.err.println(ex);
        }
        File file = new File("D:\\javascode\\code25\\file\\" + fileName+ "." + extention);
        long contetLength = connection.getContentLength();
       // downloadBanner.setSize(contetLength);
        try (InputStream in = connection.getInputStream(); FileOutputStream out = new FileOutputStream(file)) {
            totalRead = 0;
            byte[] buffer = new byte[100000];
            while (check == 0) {
                while (totalRead < contetLength) {

                    int read = in.read(buffer);
                    if (read == -1)
                        break;
                    out.write(buffer, 0, read);
                    totalRead += read;
                    percent = (int) ((float) (totalRead * 100 / contetLength));
                    Thread.sleep(2000);
                    publish(percent);
                    if (percent == 100){
                       downloadBanner.complete();
                    }
                }
            }
            if (check == 1){
                publish(percent);
            }
            if (check == 2){
                downloadBanner.jb.setValue(0);
                this.cancel(true);
            }
            System.out.println("download finished");
        } catch (IOException ex) {
            System.err.println(ex);
        }
        return null;
    }

    @Override
    protected void process(List< Integer > chunks) {
        if (check == 0)
        downloadBanner.jb.setValue(chunks.get(chunks.size() - 1));

    }

    @Override
    protected void done() {
            downloadBanner.jb.setValue(100);
        if (check == 2)
            downloadBanner.jb.setValue(0);
        super.done();
    }
}

