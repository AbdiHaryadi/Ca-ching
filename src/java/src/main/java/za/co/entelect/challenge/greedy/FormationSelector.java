package za.co.entelect.challenge.greedy;

import za.co.entelect.challenge.entities.GameState;

public class FormationSelector extends Selector {
    // Implementasikan di sini. Bisa buat atribut baru, method baru, bebas.
    // ...

    private GameState gameState;
    private int attempt;
    private int coorX = currentWorm.position.x;
    private int coorY = currentWorm.position.y;
    private int arahX;
    private int arahY;

    private boolean isDirt(int x, int y) {
        if (isValidCoordinate(x, y)) {
            return this.gameState.map[y][x].type == CellType.DIRT;
            
        } else {
            return false;
            
        }
        
    }

    private Solution moveDecision(int a, int b) {
        if (isDirt(a,b)) {
            digCommand(a,b);
        }
        else {
            moveCommand(a,b);
        }
    }

    public Solution moveSolution() {
        profession = gameState.myPlayer.worms[i].profession;
    /* bergerak diagonal dahulu baru x atau y */
        if (profession=="Commando") {
            if (coorX != 13 && coorY !=16) {
                if (13 > coorX && 16 > coorY) {
                    this.arahX = 1;
                    this.arahY = 1;
                    moveDecision(coorX+arahX,coorY+arahY);
                }
                else if (13>coorX && 16<coorY) {
                    this.arahX = 1;
                    this.arahY = -1;
                    moveDecision(coorX+arahX,coorY+arahY);
                }
                else if (13<coorX && 16<coorY) {
                    this.arahX = -1;
                    this.arahY = -1;
                    moveDecision(coorX+arahX,coorY+arahY);
                }
                else {
                    this.arahX = -1;
                    this.arahY = 1;
                    moveDecision(coorX+arahX,coorY+arahY);
                }
            }
            else if (coorX == 13 && coorY !=16) {
                if (coorY>16) {
                    this.arahX = 0;
                    this.arahY = 1;
                    moveDecision(coorX+arahX,coorY+arahY);
                }
                else {
                    this.arahX = 0;
                    this.arahY = -1;
                    moveDecision(coorX+arahX,coorY+arahY);
                }
            }
            else {
                if (coorX>13) {
                    this.arahX = 1;
                    this.arahY = 0;
                    moveDecision(coorX+arahX,coorY+arahY);
                }
                else {
                    this.arahX = -1;
                    this.arahY = 0;
                    moveDecision(coorX+arahX,coorY+arahY);
                }
            }
        }
        else if (profession=="Agent") {
            if (coorX != 17 && coorY !=14) {
                if (17 > coorX && 14 > coorY) {
                    this.arahX = 1;
                    this.arahY = 1;
                    moveDecision(coorX+arahX,coorY+arahY);
                }
                else if (17>coorX && 14<coorY) {
                    this.arahX = 1;
                    this.arahY = -1;
                    moveDecision(coorX+arahX,coorY+arahY);
                }
                else if (17<coorX && 14<coorY) {
                    this.arahX = -1;
                    this.arahY = -1;
                    moveDecision(coorX+arahX,coorY+arahY);
                }
                else {
                    this.arahX = -1;
                    this.arahY = 1;
                    moveDecision(coorX+arahX,coorY+arahY);
                }
            }
            else if (coorX == 17 && coorY !=14) {
                if (coorY>14) {
                    this.arahX = 0;
                    this.arahY = 1;
                    moveDecision(coorX+arahX,coorY+arahY);
                }
                else {
                    this.arahX = 0;
                    this.arahY = -1;
                    moveDecision(coorX+arahX,coorY+arahY);
                }
            }
            else {
                if (coorX>17) {
                    this.arahX = 1;
                    this.arahY = 0;
                    moveDecision(coorX+arahX,coorY+arahY);
                }
                else {
                    this.arahX = -1;
                    this.arahY = 0;
                    moveDecision(coorX+arahX,coorY+arahY);
                }
            }
        }
        else if (profession=="Technologist") {
            if (coorX != 18 && coorY !=19) {
                if (18 > coorX && 19 > coorY) {
                    this.arahX = 1;
                    this.arahY = 1;
                    moveDecision(coorX+arahX,coorY+arahY);
                }
                else if (18>coorX && 19<coorY) {
                    this.arahX = 1;
                    this.arahY = -1;
                    moveDecision(coorX+arahX,coorY+arahY);
                }
                else if (18<coorX && 19<coorY) {
                    this.arahX = -1;
                    this.arahY = -1;
                    moveDecision(coorX+arahX,coorY+arahY);
                }
                else {
                    this.arahX = -1;
                    this.arahY = 1;
                    moveDecision(coorX+arahX,coorY+arahY);
                }
            }
            else if (coorX == 18 && coorY !=19) {
                if (coorY>19) {
                    this.arahX = 0;
                    this.arahY = 1;
                    moveDecision(coorX+arahX,coorY+arahY);
                }
                else {
                    this.arahX = 0;
                    this.arahY = -1;
                    moveDecision(coorX+arahX,coorY+arahY);
                }
            }
            else {
                if (coorX>18) {
                    this.arahX = 1;
                    this.arahY = 0;
                    moveDecision(coorX+arahX,coorY+arahY);
                }
                else {
                    this.arahX = -1;
                    this.arahY = 0;
                    moveDecision(coorX+arahX,coorY+arahY);
                }
            }
        }
    }

    public Solution solutionAttack(){
        Direction direction(arahX,arahY);
        attackCommand(direction);
    }

    public Solution solutionAltRoute() {
        profession = gameState.myPlayer.worms[i].profession
        this.arahX=arahX;
        this.arahY=arahY;
        /* */
        if (profession=="Commando") {
            if (arahX != 0 && arahY!=0) {
                moveCommand(coorX+arahX,coorY+arahY-1);
            }
            else if (arahX!=0 && arahY==0) {
                if (coorY>16) {
                    moveCommand(coorX+arahX,coorY+arahY-1);
                }
                else {
                    moveCommand(coorX+arahX,coorY+arahY+1);
                }
            }
            else if (arahX==0 && arahY!=0) {
                if (coorX>13) {
                    moveCommand(coorX+arahX-1,coorY+arahY);
                }
                else {
                    moveCommand(coorX+arahX+1,coorY+arahY);
                }
            }
        }
        else if (profession=="Agent") {
            if (arahX != 0 && arahY!=0) {
                moveCommand(coorX+arahX,coorY+arahY-1);
            }
            else if (arahX!=0 && arahY==0) {
                if (coorY>14) {
                    moveCommand(coorX+arahX,coorY+arahY-1);
                }
                else {
                    moveCommand(coorX+arahX,coorY+arahY+1);
                }
            }
            else if (arahX==0 && arahY!=0) {
                if (coorX>17) {
                    moveCommand(coorX+arahX-1,coorY+arahY);
                }
                else {
                    moveCommand(coorX+arahX+1,coorY+arahY);
                }
            }
        }
        else if (profession=="Technologist") {
            if (arahX != 0 && arahY!=0) {
                moveCommand(coorX+arahX,coorY+arahY-1);
            }
            else if (arahX!=0 && arahY==0) {
                if (coorY>19) {
                    moveCommand(coorX+arahX,coorY+arahY-1);
                }
                else {
                    moveCommand(coorX+arahX,coorY+arahY+1);
                }
            }
            else if (arahX==0 && arahY!=0) {
                if (coorX>18) {
                    moveCommand(coorX+arahX-1,coorY+arahY);
                }
                else {
                    moveCommand(coorX+arahX+1,coorY+arahY);
                }
            }
        }
    }



    public FormationSelector(GameState gs) {
        this.gameState = gs;
        this.attempt = 1;
        
        this.arahX = 0;
        this.arahY = 0;
    }
    
    public boolean hasNext() {
        return this.attempt < 3;
        
    }
    
    public void next() {
        this.attempt++;
        
    }

    public Command getSolution() {
         if (this.attempt == 1) {
            return moveSolution();
            
        } 
        else if (this.attempt == 2) {
            return solutionAttack();
            
        } 
        else { // attempt == 3
            return solutionAltRoute();
            
        }
        
    }

    private boolean isValidCoordinate(int x, int y) {
        return x >= 0 && x < this.gameState.mapSize
                && y >= 0 && y < this.gameState.mapSize;

    }
    
}
