package Main.Controller;

import Model.Manager;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class Controller implements MouseListener {

    private HexagonCtrl hexagonCtrl;
    private  ButtonsCtrl buttonsCtrl;
    private Manager manager;

    private boolean state3;


    public Controller(Manager manager) {
        state3 = false;
        this.manager = manager;
        this.hexagonCtrl = new HexagonCtrl(manager.getHexagonsManager());
        buttonsCtrl = new ButtonsCtrl(manager);

    }

    @Override
    public void mouseClicked(MouseEvent e) {
        hexagonCtrl.onClick(e);
        buttonsCtrl.mouseClick(e);
    }

    @Override
    public void mousePressed(MouseEvent e) {
        buttonsCtrl.mousePressed(e);
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        buttonsCtrl.mouseReleased(e);
    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}
