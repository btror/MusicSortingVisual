package musicsorter;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.tools.DocumentationTool.Location;

/**
 *
 * @author brorie3
 */
public class Window {

    private static JFrame frame;
    private static JPanel panel;
    private static JButton[] list = new JButton[50];

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

        JMenuItem quick = new JMenuItem("Quick Sort");

        quick.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //method to quick sort
            }
        });

        sortOptions.add(bubble);
        sortOptions.add(quick);
        menuBar.add(sortOptions);
        frame.setJMenuBar(menuBar);
    }

    public static void bubbleSort() {
        Thread thread = new Thread(() -> {
            for (int i = 0; i < list.length - 1; i++) {
                for (int j = 0; j < list.length - i - 1; j++) {
                    if (list[j].getHeight() < list[j + 1].getHeight()) {
                        JButton temp = list[j];
                        list[j] = list[j + 1];
                        list[j + 1] = temp;
                        //swap locations
                        Point temploc = list[j].getLocation();
                        try {
                            Thread.sleep(10);
                        } catch (InterruptedException ex) {
                            System.out.println("exception caught: " + ex);
                        }
                        list[j].setLocation(list[j + 1].getLocation());
                        list[j + 1].setLocation(temploc);
                    }
                }
            }
        });
        thread.start();
    }
}
