package Main.Controller;

import Model.Hexagon;
import Model.HexagonsManager;

import java.awt.*;
import java.awt.event.MouseEvent;

public class HexagonCtrl {

    private Hexagon[][] hexagons;
    private  HexagonsManager manager;

    public HexagonCtrl(HexagonsManager hexagonsManager) {
        this.hexagons = hexagonsManager.getHexagons();
        this.manager = hexagonsManager;
    }

    public void onClick(MouseEvent e){


            for (int i = 0; i < hexagons.length; i++) {
                for (int j = 0; j < hexagons[0].length; j++) {
                    Hexagon hexagon = hexagons[i][j];
                    Polygon polygon = hexagon.getPolygon();

                    if (polygon.contains(e.getPoint())) {

                        //если левая кнопка мыши
                        if(e.getButton() == 1) {

                            switch (manager.getState()){
                                case HexagonsManager.INIT_STATE:
                                    manager.generateBombs(i, j);
                                    manager.openCell(i, j);
                                    break;
                                case HexagonsManager.DURING_STATE:
                                    manager.openCell(i, j);
                                    break;
                                case HexagonsManager.END_GAME_STATE:

                                    break;
                            }


                        }
                        if(e.getButton() == 3) {
                            if(manager.getState() == HexagonsManager.DURING_STATE) {
                                manager.switchBlockCell(i, j);
                            }
                        }

                    }
                }
            }
    }
}
