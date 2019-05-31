package View;

import Main.Interfaces.Renderable;

import java.awt.*;
import Model.Button;

public class ButtonsViewer implements Renderable {

    private Color prime = Color.GRAY;
    private Color pressed = prime.darker();
    private Color textColor = Color.WHITE;
    private Button button;

    public ButtonsViewer(Button button) {
        this.button = button;
    }

    @Override
    public void render(Graphics2D g) {
        Color color = button.isPressed() ? pressed : prime;
        g.setColor(color);
        g.fillRect(button.getR().x,
                button.getR().y,
                button.getR().width,
                button.getR().height);
        g.setColor(textColor);
        g.drawString(button.getText(), button.getR().x + 8, button.getR().y + button.getR().height - 10);
    }
}
