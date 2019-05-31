package Main.Controller;

import Model.Button;
import Model.HexagonsManager;
import Model.Manager;

import java.awt.event.MouseEvent;

public class ButtonsCtrl {

    Button btnRestart;
    Manager manager;

    public ButtonsCtrl(Manager manager) {
        this.btnRestart = manager.getBtnRestart();
        this.manager = manager;
    }

    public void mouseClick(MouseEvent e) {
        if (btnRestart.getR().contains(e.getPoint())) {
            manager.getHexagonsManager().restart();
        }
    }

    public void mousePressed(MouseEvent e) {
        if (btnRestart.getR().contains(e.getPoint())) {
            btnRestart.setPressed(true);
        }

        btnRestart.setText("O.O");
    }

    public void mouseReleased(MouseEvent e) {
        btnRestart.setPressed(false);
        if(manager.getHexagonsManager().getState() == HexagonsManager.END_GAME_STATE){
            Thread t = new Thread(new Runnable() {
                @Override
                public void run() {
                    if(manager.getHexagonsManager().checkWin()){
                        btnRestart.setText("$.$");
                    }else {
                        btnRestart.setText("X.X");
                    }
                }
            });
            t.start();

        } else {
            btnRestart.setText("o.o");
        }
    }

}
