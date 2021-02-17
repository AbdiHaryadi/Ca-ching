package za.co.entelect.challenge.greedy;

import za.co.entelect.challenge.entities.GameState;

public class FormationSelector extends Selector {
    // Implementasikan di sini. Bisa buat atribut baru, method baru, bebas.
    // ...

    private int coorX = currentWorm.position.x;
    private int coorY = currentWorm.position.y;
    private int arahX;
    private int arahY;

    private List<Cell> 
    
    private boolean diagonalRoute(int aX, int aY, int bX. int bY){
        if (Math.abs(aX-bX) == Math.abs(aY-bY)) {
            return true;
        }
        else {
            return false;
        }
    }

    private boolean isDirt(int x, int y) {
        if (isValidCoordinate(x, y)) {
            return this.gameState.map[y][x].type == CellType.DIRT;
            
        } else {
            return false;
            
        }
        
    }

    public moveSolution() {
        profession = gameState.myPlayer.worms[i].profession;

        if (profession == "Commando") {
            if (13 > coorX && 16 > coorY) {
                if (diagonalRoute(coorX, coorY, 13, 16)) {
                    this.arahX = 1;
                    this.arahY = 1;
                    if (isDirt(coorX+arahX,coorY+arahY)) {
                        digCommand(coorX+arahX,coorY+arahY);
                    }
                    else {
                        moveCommand(coorX+arahX,coorY+arahY);
                    }
                }
                else {
                    if (13- coorX > 16-coorY){
                        this.arahX = 1;
                        this.arahY = 0;
                        if (isDirt(coorX+arahX,coorY+arahY)) {
                            digCommand(coorX+arahX,coorY+arahY);
                        }
                        else{
                            moveCommand(coorX+arahX,coorY+arahY);
                        }
                    }
                    else {
                        this.arahX = 0;
                        this.arahY = 1;
                        if (isDirt(coorX+arahX,coorY+arahY)) {
                            digCommand(coorX+arahX,coorY+arahY);
                        }
                        else {
                            moveCommand(coorX+arahX,coorY+arahY);
                        }
                    }
                }
            }
            else if (13>coorX && 16<coorY) {
                if (diagonalRoute(coorX,coorY,13,16)) {
                    this.arahX=1;
                    this.arahY=-1;
                    if (isDirt(coorX+arahX,coorY+arahY)) {
                        digCommand(coorX+arahX,coorY+arahY);
                    }
                    else {
                        moveCommand(coorX+arahX,coorY+arahY);
                    }
                }
                else {
                    if (13-coorX > coorY-16) {
                        this.arahX=1;
                        this.arahY=0;
                        if (isDirt(coorX+arahX,coorY+arahY)){
                            digCommand(coorX+arahX,coorY+arahY);
                        }
                        else {
                            moveCommand(coorX+arahX,coorY+arahY);
                        }
                    }
                    else {
                        this.arahX=0;
                        this.arahY=-1;
                        if(isDirt(coorX+arahX,coorY+arahY)){
                            digCommand(coorX+arahX,coorY+arahY);
                        }
                        else {
                            moveCommand(coorX+arahX,coorY+arahY);
                        }
                    }
                }
            }
            else if (13<coorX && 16 < coorY) {
                if (diagonalRoute(coorX,coorY,13,16)) {
                    this.arahX=-1;
                    this.arahY=-1;
                    if (isDirt(coorX+arahX,coorY+arahY)){
                        digCommand(coorX+arahX,coorY+arahY);
                    }
                    else {
                        moveCommand(coorX+arahX,coorY+arahY);
                    }
                }
                else {
                    if (coorX-13>coorY-16) {
                        this.arahX=-1;
                        this.arahY=0;
                        if(isDirt(coorX+arahX,coorY+arahY)){
                            digCommand(coorX+arahX,coorY+arahY);
                        }
                        else{
                            moveCommand(coorX+arahX,coorY+arahY);
                        }
                    }
                    else {
                        this.arahX=0;
                        this.arahY=-1;
                        if(isDirt(coorX+arahX,coorY+arahY)){
                            digCommand(coorX+arahX,coorY+arahY);
                        }
                        else {
                            moveCommand(coorX+arahX,coorY+arahY);
                        }
                    }
                }
            }
            else {
               if (diagonalRoute(coorX,coorY,13,16)) {
                    this.arahX=-1;
                    this.arahY=1;
                    if (isDirt(coorX+arahX,coorY+arahY)){
                        digCommand(coorX+arahX,coorY+arahY);
                    }
                    else {
                        moveCommand(coorX+arahX,coorY+arahY);
                    }
                }
                else {
                    if (coorX-13>coorY-16) {
                        this.arahX=-1;
                        this.arahY=0;
                        if(isDirt(coorX+arahX,coorY+arahY)){
                            digCommand(coorX+arahX,coorY+arahY);
                        }
                        else{
                            moveCommand(coorX+arahX,coorY+arahY);
                        }
                    }
                    else {
                        this.arahX=0;
                        this.arahY=1;
                        if(isDirt(coorX+arahX,coorY+arahY)){
                            digCommand(coorX+arahX,coorY+arahY);
                        }
                        else {
                            moveCommand(coorX+arahX,coorY+arahY);
                        }
                    }
                } 
            }

        }
    }

    public solutionAttack(){
        this.arahX=arahX;
        this.arahY=arahY;
        commandAttack(coorX+arahX,coorY+arahY);
    }

    public solutionAltRoute() {
        this.arahX=arahX;
        this.arahY=arahY;
        
    }



    public FormationSelector(GameState gs) {
        // Pastikan setelah dibuat, solusinya telah disediakan setidaknya satu
        // (sehingga getSolution() bisa digunakan)
        
    }
    
    public boolean hasNext() {
        
        
    }
    
    public void next() {
        
        
    }

    public Solution getSolution() {
        
        
    }
    
}
