package Model;

import java.awt.*;

public class Hexagon {
    private static final int sides = 6;
    private static final double turn = 0.001;
    private int x;
    private int y;
    private int radius;
    private int value;
    private boolean closed;
    private boolean blocked;


    public Hexagon(int x, int y, int radius)  {
        this.x = x;
        this.y = y;
        this.radius = radius;
        this.closed = true;
        blocked = false;
    }

    public Polygon getPolygon() {
        Polygon polygon = new Polygon();
        double theta = 2 * Math.PI / sides;
        for (int j = 0; j < sides; j++) {
            int xval = (int) (getX() + getRadius() * Math.cos(j * theta - turn));
            int yval = (int) (getY() + getRadius() * Math.sin(j * theta - turn));
            polygon.addPoint(xval, yval);
        }
        return polygon;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getRadius() {
        return radius;
    }

    public boolean isClosed() {
        return closed;
    }


    public void setClosed(boolean closed){
        this.closed = closed;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public void open() {
        if(!blocked | (blocked & value == -1)) {
            closed = false;
        }
    }

    public boolean isBlocked() {
        return blocked;
    }

    public void setBlocked(boolean blocked) {
        if(closed) {
            this.blocked = blocked;
        }
    }

    public void restart(){
        closed = true;
        blocked = false;
    }
}
