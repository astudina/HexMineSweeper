package Model;

import java.awt.*;

public class Button {

    private Rectangle r;
    private String text;
    private boolean pressed;

    public Button(Rectangle r) {
        pressed = false;
        this.r = r;
        text = "о.о";
    }

    public Rectangle getR() {
        return r;
    }

    public boolean isPressed() {
        return pressed;
    }

    public void setPressed(boolean pressed) {
        this.pressed = pressed;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getText() {
        return text;
    }
}
