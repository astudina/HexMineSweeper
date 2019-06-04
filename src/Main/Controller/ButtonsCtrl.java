package Main.Controller;

import Model.Button;
import Model.HexagonsManager;
import Model.Manager;

import java.awt.event.MouseEvent;

public class ButtonsCtrl {

    private Button btnRestart;
    private Manager manager;

    public ButtonsCtrl(Manager manager) {
        this.btnRestart = manager.getBtnRestart();
        this.manager = manager;
    }

    public void mouseClick(MouseEvent e) {
        if (btnRestart.getR().contains(e.getPoint())) {
            manager.getHexagonsManager().restart();
        }
        if(manager.getHexagonsManager().getState() == HexagonsManager.States.END_GAME_STATE){
            if(manager.getHexagonsManager().checkWin()){
                btnRestart.setText("$.$");
            }else {
                btnRestart.setText("X.X");
            }


        } else {
            btnRestart.setText("o.o");
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

    }

}
