package musicsorter;

import java.applet.Applet;
import java.applet.AudioClip;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.net.MalformedURLException;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;

/**
 *
 * @author brorie3
 */
public class Window {

    private static JFrame frame;
    private static JPanel panel;
    private static JButton[] list = new JButton[50];
    private static File wavFile = new File("C:\\Users\\brorie3\\Documents\\wavfiles\\alarm.wav");
    private static AudioClip sound;

    public Window() {

        frame = new JFrame();
        frame.setSize(new Dimension(756, 500));
        frame.setTitle("Music Sorter");
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //set layout
        panel = new JPanel();
        panel.setBackground(Color.PINK);
        panel.setLayout(null);

        //setup 
        setupButtons();
        frame.add(panel);
        setupMenu();

        try {
            sound = Applet.newAudioClip(wavFile.toURL());
        } catch (MalformedURLException ex) {
            System.out.println("exception" + ex);
        }

        frame.setVisible(true);

    }

    public static void setupButtons() {
        int x = 0;
        for (int i = 0; i < 50; i++) {
            int random = (int) (Math.random() * 450);
            JButton button = new JButton();
            button.setSize(15, random);
            button.setLocation(x, 0);
            button.setBackground(Color.LIGHT_GRAY);
            panel.add(button);
            list[i] = button;
            x += 15;
        }
    }

    public static void setupMenu() {
        JMenuBar menuBar = new JMenuBar();
        JMenu sortOptions = new JMenu("Sort");

        JMenuItem bubble = new JMenuItem("Bubble Sort");

        bubble.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //method to bubble sort
                bubbleSort();

            }
        });

        JMenuItem selection = new JMenuItem("Selection Sort");

        selection.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //method to selection sort
                selectionSort();

            }
        });

        JMenuItem insertion = new JMenuItem("Insertion Sort");

        insertion.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //method to insertion sort
                insertionSort();
            }
        });

        JMenuItem shell = new JMenuItem("Shell Sort");

//        shell.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                //method to shell sort
//                shellSort();
//            }
//        });
        JMenu refresh = new JMenu("Refresh");
        JMenuItem randomrefresh = new JMenuItem("Random Refresh");

        randomrefresh.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //method to refresh
                randomRefresh();

            }
        });

        JMenu sounds = new JMenu("Sound Menu");

        JMenuItem noSound = new JMenuItem("No Sound");
        noSound.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                wavFile = new File("");
                try {
                    sound = Applet.newAudioClip(wavFile.toURL());
                } catch (MalformedURLException ex) {
                    System.out.println("exception" + ex);
                }
            }

        });

        JMenuItem sound1 = new JMenuItem("Sound 1");

        sound1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //sound1      
                wavFile = new File("C:\\Users\\brorie3\\Documents\\wavfiles\\beep-3.wav");
                try {
                    sound = Applet.newAudioClip(wavFile.toURL());
                } catch (MalformedURLException ex) {
                    System.out.println("exception" + ex);
                }
            }
        });

        JMenuItem sound2 = new JMenuItem("Sound 2");

        sound2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //sound2   
                wavFile = new File("C:\\Users\\brorie3\\Documents\\wavfiles\\alarm.wav");
                try {
                    sound = Applet.newAudioClip(wavFile.toURL());
                } catch (MalformedURLException ex) {
                    System.out.println("exception" + ex);
                }
            }
        });

        JMenuItem sound3 = new JMenuItem("Sound 3");

        sound3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //sound3      
                wavFile = new File("C:\\Users\\brorie3\\Documents\\wavfiles\\sound53.wav");
                try {
                    sound = Applet.newAudioClip(wavFile.toURL());
                } catch (MalformedURLException ex) {
                    System.out.println("exception" + ex);
                }
            }
        });

        refresh.add(randomrefresh);
        sounds.add(noSound);
        sounds.add(sound1);
        sounds.add(sound2);
        sounds.add(sound3);
        sortOptions.add(bubble);
        sortOptions.add(selection);
        sortOptions.add(insertion);
//        sortOptions.add(shell);
        menuBar.add(refresh);
        menuBar.add(sortOptions);
        menuBar.add(sounds);
        frame.setJMenuBar(menuBar);
    }

    public static void randomRefresh() {
        Thread thread = new Thread(() -> {
            for (int i = 0; i < 50; i++) {
                int random = (int) (Math.random() * 450);
                try {
                    sound.play();
                    Thread.sleep(5);
                } catch (InterruptedException ex) {
                    System.out.println("exception " + ex);
                }
                list[i].setSize(15, random);
            }
        });
        thread.start();
    }

    public static void bubbleSort() {

        Thread thread = new Thread(() -> {

            for (int i = 0; i < list.length - 1; i++) {
                for (int j = 0; j < list.length - i - 1; j++) {
                    if (list[j].getHeight() > list[j + 1].getHeight()) {
                        JButton temp = list[j];
                        list[j] = list[j + 1];
                        list[j + 1] = temp;
                        //swap locations
                        Point temploc = list[j].getLocation();
                        try {
                            sound.play();
                            Thread.sleep(50);
                        } catch (InterruptedException ex) {
                            System.out.println("exception caught: " + ex);
                        } catch (Exception e) {

                        }
                        list[j].setLocation(list[j + 1].getLocation());
                        list[j + 1].setLocation(temploc);
                    }
                }
            }
        });
        thread.start();

    }

    public static void selectionSort() {
        Thread thread = new Thread(() -> {
            int n = list.length;

            for (int i = 0; i < n - 1; i++) {
                int minIdx = i;
                for (int j = i + 1; j < n; j++) {
                    if (list[j].getHeight() < list[minIdx].getHeight()) {
                        minIdx = j;
                    }
                }
                // Swap the found minimum element with the first 
                // element 
                JButton temp = list[minIdx];
                list[minIdx] = list[i];
                list[i] = temp;

                Point temploc = list[minIdx].getLocation();
                try {
                    sound.play();
                    Thread.sleep(150);
                } catch (InterruptedException ex) {
                    System.out.println("exception caught: " + ex);
                }
                list[minIdx].setLocation(list[i].getLocation());
                list[i].setLocation(temploc);

            }

        });
        thread.start();
    }

    public static void insertionSort() {
        Thread thread = new Thread(() -> {
            int n = list.length;

            for (int i = 0; i < n; i++) {
                JButton temp = list[i];
                int j = i;
                while (j > 0 && temp.getHeight() < list[j - 1].getHeight()) {
                    JButton t = list[j];
                    list[j] = list[j - 1];
                    list[j - 1] = t;

                    Point temploc = list[j].getLocation();
                    try {
                        sound.play();
                        Thread.sleep(50);
                    } catch (InterruptedException ex) {
                        System.out.println("exception caught: " + ex);
                    }
                    list[j].setLocation(list[j - 1].getLocation());
                    list[j - 1].setLocation(temploc);

                    j = j - 1;
                }

            }

        });
        thread.start();
    }

//    public static void shellSort() {
//        Thread thread = new Thread(() -> {
//            int n = list.length;
//
//            for (int gap = n / 2; gap > 0; gap /= 2) {
//                for (int i = gap; i < n; i++) {
//                    JButton key = list[i];
//                    int j = i;
//                    while (j >= gap && list[j - gap].getHeight() > key.getHeight()) {
//                        list[j] = list[j - gap];
//
//                        list[j].setLocation(list[j - gap].getLocation());
//                        j -= gap;
//                    }
//                    list[j] = key;
//                    list[j].setLocation(key.getLocation());
//                }
//            }
//
//        });
//        thread.start();
//    }
}
