package View;

import Main.Interfaces.Renderable;
import Model.HexagonsManager;
import Model.Manager;

import java.awt.*;

public class UIView implements Renderable {
    private Color textColor = Color.WHITE;


    private Manager manager;
    private String timeRound = "0";

    public UIView(Manager manager) {
        this.manager = manager;
    }

    @Override
    public void render(Graphics2D g) {
        HexagonsManager manager = this.manager.getHexagonsManager();
        String countBlockedCells = String.valueOf(manager.getCountBlockedCells());

        timeRound = String.valueOf(manager.getElapsedRoundTimeSec());
        g.setColor(textColor);
        g.drawString(countBlockedCells, 30, 40);
        g.drawString(timeRound, Window.width - 100, 40);



    }
}
