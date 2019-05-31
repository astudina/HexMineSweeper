package Model;

import View.Window;

import java.awt.*;

public class Manager {
    private HexagonsManager hexagonsManager;
    private Button btnRestart;

    public Manager() {
        hexagonsManager = new HexagonsManager(20, 70, Window.width - 40, Window.height - 80);
        hexagonsManager.createField(7, 10);

        int btnWidth = 50, btnHeight = 40;

        Rectangle rect = new Rectangle((Window.width - btnWidth)/2, 15, btnWidth, btnHeight);
        btnRestart = new Button(rect);

    }

    public HexagonsManager getHexagonsManager() {
        return hexagonsManager;
    }

    public Button getBtnRestart() {
        return btnRestart;
    }
}
