package com.company;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.*;
import java.util.ArrayList;

import static javax.swing.UIManager.addPropertyChangeListener;
import static javax.swing.UIManager.setLookAndFeel;

/**
 * gui part of a download manager program
 * @author mahdie ghorbanian
 * @version 97.2.29
 */
public class JDM {
    private DownloadTask downloadTask;
    boolean filterFlag = false;
    public static JSlider slider;
    private TrayIcon trayIcon;
    private SystemTray tray;
    private static int downloadLimit;               //shows number of downloads that are permitted
    private static ArrayList<DownloadBanner> downloadBanners = new ArrayList<>();              //panel contains progressbar
    private static ArrayList<DownloadBanner> queueList = new ArrayList<>();
    private JComboBox comboBox;
    private JRadioButton radioButton , radioButton1 , radioButton2;
    private JPanel panel5 = new JPanel();
    private static int a = 20;
    private static int x = 20;
    private JFrame frame  , frame1 , settingFrame , frame2 , fileChooser , newDownloadFrame;        //different frame that will be opened by clicking
    private JTextArea textArea;
    private static GradientPanel panel;
    private static GradientPanel completePanel;
    private static GradientPanel queuePanel;
    private static GradientPanel historyPanel;
    private JToolBar toolbar;                   //toolbar at the top
    private JMenuBar mb;                        //menu bar
    private JMenu menu  , menu1;                    //download menu and help menu
    private JMenuItem i1, i2, i3, i4, i5  ,i6 , i7 , i8;
    private JPanel panel1;
    private JButton b , b1 , b2 , b3 , b4 , b5 , folder , ok , cancel , ok1 , button1 , button2 , button3 , button4 , button5;
    private JLabel pic , lookAndFeel1;
    private JPanel panel2 , startPanel;
    private Font  f1, f2 ;
    private JTextField  addressBar;
    private SavingData savingData;
    final JComboBox cb;
    private JComboBox sort;

    private boolean limitFlag;
    private JTabbedPane jTabbedPane;
    public JDM(){
        savingData = new SavingData();
        jTabbedPane = new JTabbedPane();
        Functions functions = new Functions(completePanel);
        String look[] = {"nimbus" , "metal" , "motif"};             //look and feel
        JComboBox lookAndFeel = new JComboBox(look);
        lookAndFeel1 = new JLabel("Look and Feel : ");
        cancel = new JButton("cancel");
        ok = new JButton("Ok");
        ok1 = new JButton("Ok");
        comboBox = new JComboBox();
        String limit[]={"Default" , "1" , "2" , "3" , "4" , "5"};
        cb = new JComboBox(limit);
        String sortArray[] = {"name" , "size" , "time"};
        sort = new JComboBox(sortArray);
        sort.setBounds(715 , 0 , 120 , 25);
        radioButton = new JRadioButton("Automatically");
        radioButton1 = new JRadioButton("Manually");
        radioButton2 = new JRadioButton("Queues");
        startPanel = new JPanel();
        frame2 = new JFrame();
        frame2.setLayout(null);
        panel5.setLayout(null);
        JTextField textField = new JTextField();
        JTextField textField1 = new JTextField();
        frame1 = new JFrame();
        settingFrame = new  JFrame("setting");                           //will be opened when you choose setting
        settingFrame.setLayout(null);
        settingFrame.setBounds(600 , 500 , 700 , 700);
        newDownloadFrame = new  JFrame();
        newDownloadFrame.setLayout(null);
        newDownloadFrame.setBounds(600 , 500 , 700 , 700);
        textArea = new JTextArea("  name : mahdie ghorbanian \n\n\n    Student Id:9631057 \n\n\n     start:97/2/22 \n\n\n    finish: 97/2/29 \n\n\n  description: a download manager program using swing");         //about
        textArea.setBounds(0 , 0 , 400 ,400);
        textArea.setBackground(new Color(171 , 241 , 120));
        frame1.add(textArea);
        textArea.setForeground(Color.black);
        frame1.setLayout(null);
        frame = new JFrame("JDM");
        panel = new GradientPanel(Color.darkGray , new Color(150 , 200 , 97));              //a gradient panel
        panel.setBounds(200 , 40 , 1800 , 1200);
        queuePanel = new GradientPanel(Color.darkGray , new Color(150 , 200 , 97));              //a gradient panel
        queuePanel.setBounds(200 , 40 , 1800 , 1200);
        historyPanel = new GradientPanel(Color.darkGray , new Color(150 , 200 , 97));              //a gradient panel
        historyPanel.setBounds(200 , 40 , 1800 , 1200);
        completePanel = new GradientPanel(Color.darkGray , new Color(150 , 200 , 97));
        completePanel.setBounds(200 , 40 , 1800 , 1200);
        frame.setResizable(true);
        frame.add(panel);
        toolbar = new JToolBar();
        toolbar.setRollover(true);
        toolbar.setBackground(new Color(201 , 247 , 168));
        toolbar.addSeparator();
        mb = new JMenuBar();
        menu = new JMenu("Download");
        menu.setMnemonic('d');
        menu.setForeground(Color.black);
        menu1=new JMenu("About");
        menu1.setForeground(Color.black);
        i1=new JMenuItem("New download");           //menu items
        i2=new JMenuItem("Pause");
        i3=new JMenuItem("Resume");
        i4=new JMenuItem("Cancel");
        i5=new JMenuItem("Remove");
        i6=new JMenuItem("Setting");
        i7=new JMenuItem("Exit");
        i8 = new JMenuItem("Help");
        menu1.add(i8);
        menu.add(i1);
        menu.add(i2);
        menu.add(i3);
        menu.add(i4);
        menu.add(i5);
        menu.add(i6);
        menu.add(i7);
        mb.add(menu);
        mb.add(menu1);
        menu.setForeground(Color.white);
        menu1.setForeground(Color.white);
        mb.setBackground(new Color(50 , 50 , 50));
        frame.setJMenuBar(mb);
        frame.setDefaultCloseOperation(WindowConstants.HIDE_ON_CLOSE);
        frame.setBounds(550 , 200 , 1050 , 700);
        panel1 = new JPanel();
        panel1.setBackground(Color.GRAY);
        panel1.setBounds(200 , 0 , 1800 , 40);
        b = new JButton(new ImageIcon(((new ImageIcon(                                  //buttons in the toolbar
                "D:\\javascode\\midterm\\Picture\\new.JPG").getImage()
                .getScaledInstance(30, 30,
                        Image.SCALE_SMOOTH)))));
        b1 = new JButton(new ImageIcon(((new ImageIcon(
                "D:\\javascode\\midterm\\Picture\\pause.JPG").getImage()
                .getScaledInstance(30, 30,
                        Image.SCALE_SMOOTH)))));
        b2 = new JButton(new ImageIcon(((new ImageIcon(
                "D:\\javascode\\midterm\\Picture\\resume.JPG").getImage()
                .getScaledInstance(30, 30,
                        Image.SCALE_SMOOTH)))));
        b3 = new JButton(new ImageIcon(((new ImageIcon(
                "D:\\javascode\\midterm\\Picture\\cancel.JPG").getImage()
                .getScaledInstance(30, 30,
                        Image.SCALE_SMOOTH)))));
        b4 = new JButton(new ImageIcon(((new ImageIcon(
                "D:\\javascode\\midterm\\Picture\\trash.JPG").getImage()
                .getScaledInstance(30, 30,
                        Image.SCALE_SMOOTH)))));
        b5 = new JButton(new ImageIcon(((new ImageIcon(
                "D:\\javascode\\midterm\\Picture\\setting.JPG").getImage()
                .getScaledInstance(25, 25,
                        Image.SCALE_SMOOTH)))));
        panel.setLayout(null);
        b.setBackground(new Color(201 , 247 , 168));
        b1.setBackground(new Color(201 , 247 , 168));
        b2.setBackground(new Color(201 , 247 , 168));
        b3.setBackground(new Color(201 , 247 , 168));
        b4.setBackground(new Color(201 , 247 , 168));
        b5.setBackground(new Color(201 , 247 , 168));

        toolbar.add(b);
        toolbar.add(b1);
        toolbar.add(b2);
        toolbar.add(b3);
        toolbar.add(b4);
        toolbar.add(b5);
        panel1.add(toolbar);
        b.setToolTipText("press for new download");
        b1.setToolTipText("press to stop download");
        b2.setToolTipText("press to resume the download");
        b3.setToolTipText("press to cancel the download");
        b4.setToolTipText("press to remove the download");
        b5.setToolTipText("press for the setting");
        pic = new JLabel(new ImageIcon(((new ImageIcon(
                "D:\\javascode\\midterm\\Picture\\pic.JPG").getImage()
                .getScaledInstance(200 , 200,
                        Image.SCALE_SMOOTH)))));
        panel2 = new JPanel();
        panel2.setBounds(0 , 0 , 200 , 1200);
        panel2.add(pic);
        pic.setBounds(0 , 0 , 200 , 160);
        panel2.setBackground(new Color(50 ,50 ,50));
        f2  = new Font(Font.DIALOG_INPUT,  Font.BOLD	, 17);
        button1 = new JButton("processing");
        button2 = new JButton("completed");
        button3 =new JButton("Queue");
        button4 = new JButton("default");
        button5 = new JButton("history");
        button1.setBounds(0 ,160 , 200 , 40);
        button2.setBounds(0 , 200 , 200 ,40);
        button3.setBounds(0 , 240 , 200 , 40);
        button4.setBounds(0 , 280 , 200 , 40);
        button5.setBounds(0 , 320 , 200 , 40);
        button1.setBackground(new Color(171 , 241 , 120));
        button2.setBackground(new Color(171 , 241 , 120));
        button3.setBackground(new Color(171 , 241 , 120));
        button4.setBackground(new Color(171 , 241 , 120));
        button5.setBackground(new Color(171 , 241 , 120));
        button1.setFont(f2);
        button2.setFont(f2);
        button3.setFont(f2);
        button4.setFont(f2);
        button5.setFont(f2);
        panel2.add(button1);
        panel2.add(button2);
        panel2.add(button3);
        panel2.add(button4);
        panel2.add(button5);
        menu.setFont(f2);
        menu1.setFont(f2);
        menu1.setMnemonic('h');
        panel.setLayout(null);
        panel2.setLayout(null);
        panel1.setLayout(new GridLayout(1 ,6));
        panel.add(sort);
        frame.add(panel1);
        frame.add(panel2);
        frame.setLayout(null);
        frame.setVisible(true);
        /**
         * action listener to set look and feel
         */
        ActionListener lookfeel = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                functions.LookAndFeel(lookAndFeel , frame);
            }
        };
        /**
         * action for button about
         */
        i8.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame1.setLayout(null);
                frame1.setBounds(600, 300 , 400 , 400);
                frame1.setVisible(true);

            }
        });
        button5.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                savingData.deserializae("list.jdm" , historyPanel);
                savingData.deserializae("queue.jdm" , historyPanel);
                frame.remove(completePanel);
                frame.remove(panel);
                frame.remove(queuePanel);
                frame.add(historyPanel);
                frame.repaint();

            }
        });
        /**
         * action for button exit
         */
        i7.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                savingData.serializeLists(downloadBanners , "list.jdm");
                savingData.serializeLists(queueList , "queue.jdm");
                savingData.settingOutput(functions.getFileLookAndFeel() ,downloadLimit);
                System.exit(0);
            }
        });
        ActionListener b1Action = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                for (int i = 0 ; i < downloadBanners.size() ; i++){
                    if (downloadBanners.get(i).radioButton.isSelected()){
                        downloadTask.setCheck(1);
                        downloadTask.execute();
                    }
                }
                for (int i = 0 ; i < queueList.size() ; i++){
                    if (queueList.get(i).radioButton.isSelected()){
                        downloadTask.setCheck(1);
                        downloadTask.execute();
                    }
                }
            }
        };
        b1.addActionListener(b1Action);
        b2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                for (int i = 0 ; i < downloadBanners.size() ; i++){
                    if (downloadBanners.get(i).radioButton.isSelected()){
                        downloadTask.setCheck(0);
                    }
                }
                for (int i = 0 ; i < queueList.size() ; i++){
                    if (queueList.get(i).radioButton.isSelected()){
                        downloadTask.setCheck(0);
                    }
                }
            }

        });
        b3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                for (int i = 0 ; i < downloadBanners.size() ; i++){
                    if (downloadBanners.get(i).radioButton.isSelected()){

                        downloadBanners.get(i).jb.setBackground(Color.gray);
                    }
                }
                for (int i = 0 ; i < queueList.size() ; i++){
                    if (queueList.get(i).radioButton.isSelected()){
                        downloadTask.setCheck(2);
                        queueList.get(i).jb.setBackground(Color.gray);
                    }
                }
            }
        });
        /**
         * action for button setting
         */
        i6.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                settingFrame.setLayout(null);
                settingFrame.setBounds(600, 300 , 500 , 500);
                settingFrame.setVisible(true);
            }
        });
        /**
         * action for button remove
         */
        ActionListener trash = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                functions.trash(downloadBanners , panel , a);
                functions.queueTrash(queueList ,queuePanel , x);
                a = functions.getA();
                x = functions.getX();
                panel.repaint();
                queuePanel.repaint();
            }
        };

        b4.addActionListener(trash);
        i5.addActionListener(trash);
        /**
         * action for button new download
         */
        ActionListener newDownload = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                addressBar = new JTextField();
                addressBar.setBounds(90 , 100 , 365 , 30);
                folder = new JButton(new ImageIcon(((new ImageIcon(
                        "D:\\javascode\\midterm\\Picture\\folder.JPG").getImage()
                        .getScaledInstance(50, 50,
                                Image.SCALE_SMOOTH)))));
                newDownloadFrame.add(addressBar);
                folder.setBounds(440 , 100 , 30 , 30);
                newDownloadFrame.add(folder);
                newDownloadFrame.setLayout(null);
                newDownloadFrame.setBounds(700, 300 , 500 , 450);
                newDownloadFrame.add(textField);
                newDownloadFrame.add(textField1);
                addressBar.setBackground(Color.white);
                textField.setBounds(90 , 20 , 380 , 30);
                textField.setBackground(Color.white);
                textField1.setBounds(90 , 60 , 380 , 30);
                textField1.setBackground(Color.white);
                startPanel.setBounds(70 ,140 , 100 , 200);
                cb.setBounds(220 , 300 , 120 , 20);
                newDownloadFrame.add(cb);
                newDownloadFrame.add(startPanel);
                startPanel.add(radioButton);
                startPanel.add(radioButton1);
                startPanel.add(radioButton2);
                startPanel.setLayout(new GridLayout(3 ,1));
                ok.setBounds(220 , 350 , 80 , 30);
                cancel.setBounds(310 , 350 , 80 , 30);
                JLabel URL = new JLabel("URL: ");
                JLabel Address = new JLabel("Address: ");
                JLabel name = new JLabel("name: ");
                URL.setBounds(20 , 20 ,60 , 30 );
                name.setBounds(20 , 60 , 60 , 30);
                Address.setBounds(20 , 100 , 60 , 30);
                newDownloadFrame.add(name);
                newDownloadFrame.add(Address);
                newDownloadFrame.add(URL);
                newDownloadFrame.add(ok);
                newDownloadFrame.add(cancel);
                newDownloadFrame.setVisible(true);
                folder.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        fileChooser = new JFrame();
                        fileChooser.setLayout(null);
                        fileChooser.setBounds(650 , 200 , 500 , 490);
                        JFileChooser fc = new JFileChooser();
                        fc.setBounds(5 , 5 , 480 , 400);
                        fileChooser.add(fc);
                        fc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
                        fc.setBounds(5, 5, 480, 400);
                        functions.fileChooserAction(fc , textField1 , addressBar , fileChooser);
                    }
                });
                /**
                 * action for button cancel
                 */
                cancel.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        newDownloadFrame.dispose();
                    }
                });
                /**
                 * action for button ok
                 */
                ok.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        filterFlag = false;
                        savingData.filterInputStream();
                        savingData.getData();
                        for (int i = 0 ; i < savingData.getData().length ; i++){
                            if (textField.getText().startsWith(savingData.getData()[i])){
                                System.out.println("filtered!!!");
                                filterFlag = true;
                                break;
                            }
                        }
                        if (filterFlag == false) {
                            if (!textField.getText().equals("") && !textField1.equals("")) {
                                DownloadBanner downloadBanner;
                                if (limitFlag == true) {
                                    downloadBanner = new DownloadBanner(downloadLimit, textField1.getText());
                                    //  downloadBanner.addMouseListener(new Mouse(downloadBanner.label2.getText() , downloadBanner.sizeLabel.getText() , downloadBanner.speed.getText() , downloadBanner.jb.getValue()));
                                    downloadBanner.setBackground(Color.white);
                                    if (radioButton2.isSelected()) {
                                        downloadBanner.setBounds(30, x, 680, 40);
                                        queueList.add(downloadBanner);
                                        queuePanel.add(downloadBanner);
                                        x += 45;
                                        downloadBanner.isFlag(false);
                                            downloadTask = new DownloadTask(textField.getText(), downloadBanner, textField1.getText(), queuePanel, 2, queueList);
                                            downloadTask.execute();
                                            downloadTask.addPropertyChangeListener(
                                                    new PropertyChangeListener() {
                                                        public void propertyChange(PropertyChangeEvent e) {
                                                            // if the changed property is progress,
                                                            // update the progress bar
                                                            if (e.getPropertyName().equals("progress")) {
                                                                int newValue = (Integer) e.getNewValue();
                                                                downloadBanner.jb.setValue(newValue);
                                                            }
                                                        }
                                                    } // end anonymous inner class
                                            ); // end call to addPropertyChangeListener
                                    } else if (downloadBanner.getFlag() == true) {
                                        downloadBanner.setBounds(30, a, 680, 40);
                                        downloadBanners.add(downloadBanner);
                                        panel.add(downloadBanner);
                                        a += 45;
                                        downloadBanner.isFlag(true);
                                        downloadTask = new DownloadTask(textField.getText() , downloadBanner , textField1.getText()  ,panel ,1 , downloadBanners);
                                        downloadTask.execute();
                                        downloadTask.addPropertyChangeListener(
                                                new PropertyChangeListener(){
                                                    public void propertyChange(PropertyChangeEvent e)
                                                    {
                                                        // if the changed property is progress,
                                                        // update the progress bar
                                                        if (e.getPropertyName().equals("progress"))
                                                        {
                                                            int newValue = (Integer) e.getNewValue();
                                                            downloadBanner.jb.setValue(newValue);
                                                        }
                                                    }
                                                } // end anonymous inner class
                                        ); // end call to addPropertyChangeListener
                                    }
                                } else {
                                    downloadBanner = new DownloadBanner(textField1.getText(), textField.getText());
                                    downloadBanner.addMouseListener(new Mouse(downloadBanner.label2.getText(), downloadBanner.sizeLabel.getText(), downloadBanner.speed.getText(), downloadBanner.jb.getValue()));
                                    downloadBanner.setBackground(Color.white);
                                    if (radioButton2.isSelected()) {
                                        downloadBanner.setBounds(30, x, 680, 40);
                                        queueList.add(downloadBanner);
                                        queuePanel.add(downloadBanner);
                                        x += 45;
                                        downloadBanner.isFlag(false);
                                            downloadTask = new DownloadTask(textField.getText(), downloadBanner, textField1.getText(), queuePanel, 2, queueList);
                                            downloadTask.execute();
                                            downloadTask.addPropertyChangeListener(
                                                    new PropertyChangeListener() {
                                                        public void propertyChange(PropertyChangeEvent e) {
                                                            // if the changed property is progress,
                                                            // update the progress bar
                                                            if (e.getPropertyName().equals("progress")) {
                                                                int newValue = (Integer) e.getNewValue();
                                                                downloadBanner.jb.setValue(newValue);
                                                            }
                                                        }
                                                    } // end anonymous inner class
                                            ); // end call to addPropertyChangeListener

                                    } else {
                                        downloadBanner.setBounds(30, a, 680, 40);
                                        downloadBanners.add(downloadBanner);
                                        panel.add(downloadBanner);
                                        a += 45;
                                        downloadBanner.isFlag(true);
                                        downloadTask = new DownloadTask(textField.getText() , downloadBanner   , textField1.getText() , panel ,1 , downloadBanners);
                                        downloadTask.execute();
                                        downloadTask.addPropertyChangeListener(
                                                new PropertyChangeListener() {
                                                    public void propertyChange(PropertyChangeEvent e)
                                                    {
                                                        // if the changed property is progress,
                                                        // update the progress bar
                                                        if (e.getPropertyName().equals("progress"))
                                                        {
                                                            int newValue = (Integer) e.getNewValue();
                                                            downloadBanner.jb.setValue(newValue);
                                                        }
                                                    }
                                                } // end anonymous inner class
                                        ); // end call to addPropertyChangeListener
                                    }
                                }
                                FileOutputstream fileOutputstream = new FileOutputstream("D:\\javascode\\code20\\files\\list.jdm.txt");
                                fileOutputstream.output(textField.getText(), textField1.getText());
                                textField.setText("");
                                textField1.setText("");
                            }
                        }
                        textField.setText("");
                        textField1.setText("");
                        addressBar.setText("");
                        panel.repaint();
                        queuePanel.repaint();
                        newDownloadFrame.dispose();
                    }
                });
            }
        };
        button3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.remove(completePanel);
                frame.remove(panel);
                frame.remove(historyPanel);
                frame.add(queuePanel);
                frame.repaint();
            }
        });
        sort.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Sort sortObject = new Sort();
                switch (sort.getSelectedIndex()){
                    case 0:
                        sortObject = new Sort("name" , downloadBanners , panel);
                        break;
                    case 1:
                        sortObject = new Sort("size" , downloadBanners , panel);
                        break;
                    case 2:
                        sortObject = new Sort("name" , downloadBanners , panel);
                        break;
                }
                sortObject.sort();
            }
        });
        button2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.remove(queuePanel);
                frame.remove(panel);
                frame.remove(historyPanel);
                frame.add(completePanel);
                frame.repaint();
            }
        });
        button1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.remove(completePanel);
                frame.remove(queuePanel);
                frame.remove(historyPanel);
                frame.add(panel);
                frame.repaint();
            }
        });
        b.addActionListener(newDownload);
        i1.addActionListener(newDownload);

        ActionListener setting = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String numberLimit[]={"Default" , "choose"};
                JComboBox numberComboBox = new JComboBox(numberLimit);
                numberComboBox.setBounds(220 , 400 , 100 , 20);
                settingFrame.add(numberComboBox);
                JLabel number = new JLabel("number of downloads:");
                settingFrame.add(number);
                number.setBounds(78, 388, 130, 50);
                numberComboBox.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        if (numberComboBox.getSelectedIndex() == 1) {
                            JButton newButton = new JButton("ok");
                            newButton.setBounds(190 , 100 , 80 , 30);
                            JFrame newFrame = new JFrame();
                            newFrame.setBounds(600, 300, 330, 180);
                            newFrame.setVisible(true);
                            newFrame.setLayout(null);
                            newFrame.add(newButton);
                            slider = new JSlider(JSlider.HORIZONTAL, 0, 10, 5);
                            slider.setMinorTickSpacing(1);
                            slider.setMajorTickSpacing(1);
                            slider.setPaintTicks(true);
                            slider.setPaintLabels(true);
                            slider.setBounds(30, 20, 250, 60);
                            newFrame.add(slider);
                            slider.addChangeListener(new ChangeListener() {
                                public void stateChanged(ChangeEvent e) {
                                    downloadLimit = slider.getValue();
                                }
                            });
                            newButton.addActionListener(new ActionListener() {
                                @Override
                                public void actionPerformed(ActionEvent e) {
                                    newFrame.dispose();
                                    limitFlag = true;
                                }
                            });
                        }
                    }
                });
                lookAndFeel1.setBounds(80 , 360 , 100 , 30);
                lookAndFeel.setBounds(220  , 360 , 120 , 30);
                settingFrame.add(lookAndFeel);
                addressBar = new JTextField();
                addressBar.setBounds(90 , 100 , 365 , 30);
                folder = new JButton(new ImageIcon(((new ImageIcon(
                        "D:\\javascode\\midterm\\Picture\\folder.JPG").getImage()
                        .getScaledInstance(50, 50,
                                Image.SCALE_SMOOTH)))));
                settingFrame.add(addressBar);
                settingFrame.add(lookAndFeel);
                folder.setBounds(440 , 100 , 30 , 30);
                settingFrame.add(folder);
                settingFrame.setLayout(null);
                settingFrame.setBounds(700, 300 , 530 , 580);
                settingFrame.add(textField);
                settingFrame.add(textField1);
                addressBar.setBackground(Color.white);
                JLabel URL = new JLabel("URL: ");
                JLabel Address = new JLabel("Address: ");
                JLabel name = new JLabel("name: ");
                URL.setBounds(20 , 20 ,60 , 30 );
                name.setBounds(20 , 60 , 60 , 30);
                Address.setBounds(20 , 100 , 60 , 30);
                textField.setBounds(90 , 20 , 380 , 30);
                textField.setBackground(Color.white);
                textField1.setBounds(90 , 60 , 380 , 30);
                textField1.setBackground(Color.white);
                startPanel.setBounds(70 ,140 , 100 , 200);
                cb.setBounds(220 , 300 , 120 , 20);
                settingFrame.add(cb);
                settingFrame.add(startPanel);
                settingFrame.add(URL);
                settingFrame.add(name);
                settingFrame.add(Address);
                startPanel.add(radioButton);
                startPanel.add(radioButton1);
                startPanel.add(radioButton2);
                startPanel.setLayout(new GridLayout(3 ,1));
                ok1.setBounds(220 , 490 , 80 , 30);
                cancel.setBounds(310 , 490 , 80 , 30);
                JLabel filter = new JLabel("filter: ");
                JTextField textFilter = new JTextField();
                textFilter.setBounds(90 , 450  , 380 , 30);
                filter.setBounds(30 , 450 , 80 , 30);
                settingFrame.add(filter);
                settingFrame.add(textFilter);
                settingFrame.add(ok1);
                settingFrame.add(cancel);
                settingFrame.add(lookAndFeel1);
                settingFrame.setVisible(true);
                folder.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        fileChooser = new JFrame();
                        fileChooser.setLayout(null);
                        fileChooser.setBounds(650, 200, 500, 490);
                        JFileChooser fc = new JFileChooser();
                        fileChooser.add(fc);
                        fc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
                        fc.setBounds(5, 5, 480, 400);
                        int returnValue = fc.showOpenDialog(null);
                        if (returnValue == JFileChooser.APPROVE_OPTION) {
                            File selectedFile = fc.getSelectedFile();
                            textField1.setText(selectedFile.getName());
                            addressBar.setText(selectedFile.getPath());
                            fileChooser.dispose();

                        } else if (returnValue == JFileChooser.CANCEL_OPTION)
                            fileChooser.dispose();
                    }
                });
                lookAndFeel.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        ok1.addActionListener(lookfeel);
                    }
                });
                ok1.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        //savingData.serializeFilter(textFilter.getText() , "filter.jdm");
                        savingData.filterOutput(textFilter.getText());
                        textFilter.setText("");
                        settingFrame.dispose();
                    }
                });
                ok1 = new JButton("ok");
                cancel.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        settingFrame.dispose();
                    }
                });
            }
        };
        b5.addActionListener(setting);
        i6.addActionListener(setting);
        if (!SystemTray.isSupported())                                                                  //system tray by clicking cancel
            frame.setDefaultCloseOperation(WindowConstants.HIDE_ON_CLOSE);

        tray = SystemTray.getSystemTray();
        BufferedImage img = null;
        try {
            img = ImageIO.read(new File("D:\\javascode\\midterm\\Picture\\pic2.JPG"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        trayIcon = new TrayIcon(img , "TRAY");
        Handler h = new Handler(tray , trayIcon);//can add tooltip
        frame.addWindowStateListener(h);
        frame.addWindowListener(h);
        trayIcon.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Object o = e.getSource();
                if(o.equals(trayIcon)){
                    frame.setVisible(true);
                    tray.remove(trayIcon);
                }
            }
        });

        menu.setToolTipText("press alt+d");
        menu1.setToolTipText("press alt+h");
        i1.setToolTipText("press alt+n");
        i5.setToolTipText("press alt+r");
        i7.setToolTipText("press alt+e");
        i7.setToolTipText("press alt+a");
        i1.setMnemonic('n');
        i1.setAccelerator(KeyStroke.getKeyStroke('n'));
        i5.setMnemonic('r');
        i5.setAccelerator(KeyStroke.getKeyStroke('r'));
        i7.setMnemonic('e');
        i7.setAccelerator(KeyStroke.getKeyStroke('e'));
        i1.setMnemonic('a');
        i1.setAccelerator(KeyStroke.getKeyStroke('a'));
    }
    public static ArrayList<DownloadBanner> getDownloadbannners(){
        return downloadBanners;
    }
    public static JPanel getPanel(){
        return panel;
    }
    public static JPanel getQueuePanel(){
        return queuePanel;
    }
    public static JPanel getCompletePanel(){
        return completePanel;
    }
    public static ArrayList<DownloadBanner> getQueueList(){
        return queueList;
    }
    public static int getX(){
        return x;
    }
    public static int getA(){
        return a;
    }
    public static void setA(int p){
        a = p;
    }
    public static void setX(int q){
        x = q;
    }
    public static JSlider getSlider(){
        return slider;
    }
    public static void setDownloadLimit(int a){
        downloadLimit = a;
    }


    /**
     * class for handling system tray
     */
    private class Handler extends WindowAdapter {
        private final SystemTray tray;
        private final TrayIcon icon;

        public Handler(SystemTray tray, TrayIcon icon) {
            super();
            this.tray = tray;
            this.icon = icon;
        }

        private void addTrayIconDisposeFrame(JFrame frame) {
            try {
                tray.add(icon);
                frame.dispose();
                //frame.setVisible(false);
            } catch (AWTException ex) {
                ex.printStackTrace();
            }
        }

        @Override
        public void windowStateChanged(WindowEvent e) {
            if (e.getNewState() == Frame.ICONIFIED) {
                addTrayIconDisposeFrame((JFrame) e.getSource());
            }
        }

        @Override
        public void windowClosing(WindowEvent e) {
            addTrayIconDisposeFrame((JFrame) e.getSource());
        }

    }
}
