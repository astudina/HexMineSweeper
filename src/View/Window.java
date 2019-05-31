package View;

import javax.swing.*;

public class Window {

    public static int width, height;
    private String title;

    private JFrame frame;


    public Window(int width, int height, String title) {
        this.width = width;
        this.height = height;
        this.title = title;
        frame = new JFrame(title);

        frame.setSize(width, height);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.setTitle("HexMinesweeper");

    }


    public void show(){

        GamePanel gp = new GamePanel(width, height);
        frame.setContentPane(gp);
        frame.setVisible(true);

        gp.start();

    }
}
