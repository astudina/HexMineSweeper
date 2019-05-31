package Model;


public class HexagonsManager {

    private final int bomb = 4;
    private Hexagon[][] hexagons;
    private  BombsManager bombsManager;
    private int x, y, width, height;

    private long startRoundTime = 0;
    private int elapsedRoundTime = 0;
    private int countBlockedCells = 0;
    private int countOpenedCells = 0;


    public static final int INIT_STATE = 1;
    public static final int DURING_STATE = 2;
    public static final int END_GAME_STATE = 3;
    private int state;


    public HexagonsManager(int x, int y, int width, int height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        bombsManager = new BombsManager(bomb);
        countBlockedCells = bomb;

        state = INIT_STATE;
    }

    /**
     * генерирует поле, избегая ячейки i j в качестве бомбы
     * @param i
     * @param j
     */
    public void generateBombs(int i, int j){
        bombsManager.generate(i, j);
        state = DURING_STATE;
        for (int k = 0; k < hexagons.length; k++) {
            for (int l = 0; l < hexagons[0].length; l++) {
                hexagons[k][l].setValue(bombsManager.getBombs()[k][l]);
            }
        }
        startRoundTime = System.currentTimeMillis();
    }

    public void restart(){
        for (int k = 0; k < hexagons.length; k++) {
            for (int l = 0; l < hexagons[0].length; l++) {
                hexagons[k][l].restart();
            }
        }
        countOpenedCells = 0;
        countBlockedCells = bomb;
        startRoundTime = 0;
        state = INIT_STATE;
    }


    public void openCell(int i, int j){
        /*
        1 - проверить существование
        2 - проверить что она закрыта
        3 - если она 0, открыть её и все соседние
            иначе, открыть только её
         */

        if(isClosed(i, j)){
            if(hexagons[i][j].getValue() == -1){
                //end game (lose)
                explodeAllBombs(i, j);
                stopGame();
            }
            if(hexagons[i][j].getValue() >= 0  & !hexagons[i][j].isBlocked()){
                if (hexagons[i][j].getValue() == 0) {
                    hexagons[i][j].open();
                    openNeighbors(i, j);
                } else {
                    hexagons[i][j].open();
                }
                countOpenedCells++;
                if(checkWin()){
                    //end game (win)
                    stopGame();
                }
            }
        }
    }

    public boolean checkWin(){
        return countOpenedCells == hexagons.length * hexagons[0].length - bomb;
    }


    private void explodeAllBombs(int i, int j) {
        for (int k = 0; k < hexagons.length; k++) {
            for (int l = 0; l < hexagons[0].length; l++) {
                if(hexagons[k][l].getValue() == -1){
                    hexagons[k][l].open();
                }
            }
        }
    }

    public void stopGame(){
        state = END_GAME_STATE;
    }

    private void openNeighbors(int i, int j){
        if (isClosed(i - 1, j)) openCell(i - 1, j);
        if (isClosed(i + 1, j)) openCell(i + 1, j);

        if (isClosed(i , j - 1)) openCell(i, j - 1);
        if (isClosed(i, j + 1)) openCell(i, j + 1);

        if(j % 2 == 0){
            if (isClosed(i - 1, j - 1)) openCell(i - 1, j - 1);
            if (isClosed(i - 1, j + 1)) openCell(i - 1, j + 1);
        } else {
            if (isClosed(i + 1, j - 1)) openCell(i + 1, j - 1);
            if (isClosed(i + 1, j + 1)) openCell(i + 1, j + 1);
        }
    }

    private boolean isClosed(int i, int j){
        return i >= 0 && i < hexagons.length && j >= 0 && j < hexagons[0].length && hexagons[i][j].isClosed();
    }

    public void createField(int row, int col){
        int minW = width > height ? height : width;
        int maxC = row + 1 > col ? row + 1 : col;
        int radius = (int) (minW / maxC / 1.45) ;
        int delta = (int) (radius - Math.sqrt(radius * radius - (radius / 2f) * (radius / 2f)));

        bombsManager.setRow(row);
        bombsManager.setCol(col);
        hexagons = new Hexagon[row][col];

        for (int j = 0; j < hexagons[0].length; j++) {
            int y = 1;
            int tmp = j % 2;
            for (int i = 0; i < hexagons.length; i++) {
                double stepX = 1.5f * j + 1;
                int stepY = y + tmp;
                Hexagon hexagon = new Hexagon(x + (int)(radius * stepX),
                        this.y + radius * stepY - delta * stepY, radius);
                hexagons[i][j] = hexagon;
                y += 2;
            }
        }
    }

    public Hexagon[][] getHexagons() {
        return hexagons;
    }

    public void switchBlockCell(int i, int j) {
        boolean blocked = hexagons[i][j].isBlocked();

        if(blocked){
            hexagons[i][j].setBlocked(false);
            countBlockedCells++;
        }

        if(!blocked & hexagons[i][j].isClosed()){
            hexagons[i][j].setBlocked(true);
            countBlockedCells--;
        }


    }

    public long getStartRoundTime() {
        return startRoundTime;
    }

    public int getCountBlockedCells() {
        return countBlockedCells;
    }

//    public boolean isRunningRound() {
//        return runningRound;
//    }


    public int getElapsedRoundTimeSec() {
        if(startRoundTime == 0){
            return 0;
        }

//        if(!runningRound){
//            return elapsedRoundTime;
//        }
        if(state != DURING_STATE){
            return elapsedRoundTime;
        }

        elapsedRoundTime = (int) ((System.currentTimeMillis() - startRoundTime) / 1000);

        return elapsedRoundTime;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }
}
