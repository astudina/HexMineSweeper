package View;

import Main.Interfaces.Renderable;
import Model.Hexagon;

import java.awt.*;

public class HexagonView implements Renderable {

    private static final Color openedBackgroundColor = Color.WHITE;
    private static final Color closedBackgroundColor = Color.GRAY;
    private static final Color boundsColor = Color.BLACK;
    private Hexagon[][] hexagons;
    private int width;
    private int height;
    private Font font;
    private int maxSize = 0;
    private static final Color[] textColor = {Color.WHITE, Color.BLUE, Color.BLUE, Color.GREEN.darker(), Color.GREEN.darker(),
                                              Color.RED, Color.RED.darker()};
    private static final Color bombs = Color.RED;


    public HexagonView(Hexagon[][] hexagons) {
        this.hexagons = hexagons;
    }

    @Override
    public void render(Graphics2D g) {
        //подбираем размер шрифта
        int size = 0;
        while(maxSize < hexagons[0][0].getRadius()) {
            font = new Font("Arial", Font.PLAIN, size);
            width = (int) g.getFontMetrics(font).getStringBounds("0", g).getWidth();
            height = (int) g.getFontMetrics(font).getStringBounds("0", g).getHeight();
            maxSize = width > height ? width : height;
            size++;
        }

        //отрисовываем все шестиугольники с текстом
        for (int i = 0; i < hexagons.length; i++) {
            for (int j = 0; j < hexagons[0].length; j++) {
                Polygon polygon = hexagons[i][j].getPolygon();
                g.setColor(hexagons[i][j].isClosed() ? closedBackgroundColor : openedBackgroundColor);
                g.fillPolygon(polygon);
                g.setColor(boundsColor);
                g.drawPolygon(polygon);

                int x = hexagons[i][j].getX();
                int y = hexagons[i][j].getY();
//                String text = i + " " + j;
                String text = "";
                if(hexagons[i][j].isBlocked()){
                    text = "|>";
                }
                if(hexagons[i][j].getValue() != 0 & !hexagons[i][j].isClosed()){
                   text = "" + (hexagons[i][j].getValue() ==  -1 ? "*" : hexagons[i][j].getValue());
                }


                //выбираем цвет для текста
                if(hexagons[i][j].isBlocked()){
                    g.setColor(Color.RED.darker());
                } else if (hexagons[i][j].getValue() < 0) {
                    g.setColor(bombs);
                } else {
                    g.setColor(textColor[hexagons[i][j].getValue()]);
                }

                g.setFont(font);
                g.drawString(text, x - width/2, y + height / 3);



            }
        }
    }
}
