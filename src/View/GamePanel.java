package View;

import Main.Controller.Controller;
import Main.Main;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

import Model.Manager;

public class GamePanel extends JPanel implements Runnable {
    private BufferedImage image;

    private Manager manager;
    private HexagonView hexagonView;
    private ButtonsViewer buttonsViewer;
    private UIView uiView;

    private final Color backgroundColor = Color.BLACK;

    public GamePanel(int width, int height) {
        super();

        setSize(width, height);

        image = new BufferedImage(Main.WIDTH, Main.HEIGHT, BufferedImage.TYPE_INT_ARGB);


        manager = new Manager();


        Controller controller = new Controller(manager);

        addMouseListener(controller);

        hexagonView = new HexagonView(manager.getHexagonsManager().getHexagons());
        uiView = new UIView(manager);
        buttonsViewer = new ButtonsViewer(manager.getBtnRestart());


    }


    @Override
    public void run() {
        while (true) {
            Graphics2D g = (Graphics2D) image.getGraphics();
            g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);


            g.setColor(Color.WHITE);
            g.fillRect(0, 0, Main.WIDTH, Main.HEIGHT);

            render(g);

            swapImage();

            try {
                Thread.sleep(1000 / 30);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private void render(Graphics2D g) {
        drawBackground(g);
        hexagonView.render(g);
        uiView.render(g);
        buttonsViewer.render(g);
    }

    public void start() {
        Thread t = new Thread(this);
        t.start();
    }

    private void drawBackground(Graphics2D g) {
        g.setColor(backgroundColor);
        g.fillRect(0, 0, Main.WIDTH, Main.HEIGHT);
    }

    private void swapImage() {
        Graphics g = this.getGraphics();
        g.drawImage(image, 0, 0, null);
    }
}
