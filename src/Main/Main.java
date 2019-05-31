package Main;

import View.Window;

public class Main {
    public static final int WIDTH = 500, HEIGHT = 500;


    public static void main(String[] args) {
        Window w = new Window(WIDTH, HEIGHT, "Hello!");
        w.show();
    }
}
