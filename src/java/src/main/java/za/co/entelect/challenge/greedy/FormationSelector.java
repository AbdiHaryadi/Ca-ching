package za.co.entelect.challenge.greedy;

import za.co.entelect.challenge.command.*;
import za.co.entelect.challenge.entities.GameState;
import za.co.entelect.challenge.entities.MyWorm;
import za.co.entelect.challenge.enums.CellType;
import za.co.entelect.challenge.enums.Direction;

import java.util.Arrays;

public class FormationSelector extends Selector {
    // Implementasikan di sini. Bisa buat atribut baru, method baru, bebas.
    // ...


    private GameState gameState;
    private int attempt;
    private int coorX;
    private int coorY;
    private int arahX;
    private int arahY;
    private MyWorm currentWorm;

    private MyWorm getCurrentWorm() {
        return Arrays.stream(this.gameState.myPlayer.worms)
                .filter(myWorm -> myWorm.id == this.gameState.currentWormId)
                .findFirst()
                .get();
    }

    private boolean isDirt(int x, int y) {
        if (isValidCoordinate(x, y)) {
            return this.gameState.map[y][x].type == CellType.DIRT;
            
        } else {
            return false;
            
        }
        
    }

    private Command moveDecision(int a, int b) {
        if (isDirt(a,b)) {
            return new DigCommand(a,b);
        }
        else {
            return new MoveCommand(a,b);
        }
    }

    public Command moveSolution() {
        String profession = currentWorm.profession;
    /* bergerak diagonal dahulu baru x atau y */
        if (profession=="Commando") {
            if (coorX != 13 && coorY !=16) {
                if (13 > coorX && 16 > coorY) {
                    this.arahX = 1;
                    this.arahY = 1;
                    return moveDecision(coorX+arahX,coorY+arahY);
                }
                else if (13>coorX && 16<coorY) {
                    this.arahX = 1;
                    this.arahY = -1;
                    return moveDecision(coorX+arahX,coorY+arahY);
                }
                else if (13<coorX && 16<coorY) {
                    this.arahX = -1;
                    this.arahY = -1;
                    return moveDecision(coorX+arahX,coorY+arahY);
                }
                else {
                    this.arahX = -1;
                    this.arahY = 1;
                    return moveDecision(coorX+arahX,coorY+arahY);
                }
            }
            else if (coorX == 13 && coorY !=16) {
                if (coorY>16) {
                    this.arahX = 0;
                    this.arahY = -1;
                    return moveDecision(coorX+arahX,coorY+arahY);
                }
                else {
                    this.arahX = 0;
                    this.arahY = 1;
                    return moveDecision(coorX+arahX,coorY+arahY);
                }
            }
            else {
                if (coorX>13) {
                    this.arahX = -1;
                }
                else {
                    this.arahX = 1;
                }
                this.arahY = 0;
                return moveDecision(coorX+arahX,coorY+arahY);
            }
        }
        else if (profession=="Agent") {
            if (coorX != 17 && coorY !=14) {
                if (17 > coorX && 14 > coorY) {
                    this.arahX = 1;
                    this.arahY = 1;
                    return moveDecision(coorX+arahX,coorY+arahY);
                }
                else if (17>coorX && 14<coorY) {
                    this.arahX = 1;
                    this.arahY = -1;
                    return moveDecision(coorX+arahX,coorY+arahY);
                }
                else if (17<coorX && 14<coorY) {
                    this.arahX = -1;
                    this.arahY = -1;
                    return moveDecision(coorX+arahX,coorY+arahY);
                }
                else {
                    this.arahX = -1;
                    this.arahY = 1;
                    return moveDecision(coorX+arahX,coorY+arahY);
                }
            }
            else if (coorX == 17 && coorY !=14) {
                if (coorY>14) {
                    this.arahX = 0;
                    this.arahY = -1;
                    return moveDecision(coorX+arahX,coorY+arahY);
                }
                else {
                    this.arahX = 0;
                    this.arahY = 1;
                    return moveDecision(coorX+arahX,coorY+arahY);
                }
            }
            else {
                if (coorX>17) {
                    this.arahX = -1;
                }
                else {
                    this.arahX = 1;
                }
                this.arahY = 0;
                return moveDecision(coorX+arahX,coorY+arahY);
            }
        }
        else  { // if (profession=="Technologist")
            if (coorX != 18 && coorY !=19) {
                if (18 > coorX && 19 > coorY) {
                    this.arahX = 1;
                    this.arahY = 1;
                    return moveDecision(coorX+arahX,coorY+arahY);
                }
                else if (18>coorX && 19<coorY) {
                    this.arahX = 1;
                    this.arahY = -1;
                    return moveDecision(coorX+arahX,coorY+arahY);
                }
                else if (18<coorX && 19<coorY) {
                    this.arahX = -1;
                    this.arahY = -1;
                    return moveDecision(coorX+arahX,coorY+arahY);
                }
                else {
                    this.arahX = -1;
                    this.arahY = 1;
                    return moveDecision(coorX+arahX,coorY+arahY);
                }
            }
            else if (coorX == 18 && coorY !=19) {
                if (coorY>19) {
                    this.arahX = 0;
                    this.arahY = -1;
                    return moveDecision(coorX+arahX,coorY+arahY);
                }
                else {
                    this.arahX = 0;
                    this.arahY = 1;
                    return moveDecision(coorX+arahX,coorY+arahY);
                }
            }
            else {
                if (coorX>18) {
                    this.arahX = -1;
                }
                else {
                    this.arahX = 1;
                }
                this.arahY = 0;
                return moveDecision(coorX+arahX,coorY+arahY);
            }
        }
    }
    private Direction resolveDirection(int aX, int aY, int bX, int bY) {
        StringBuilder builder = new StringBuilder();

        int verticalComponent = bY - aY;
        int horizontalComponent = bX - aX;

        if (verticalComponent < 0) {
            builder.append('N');
        } else if (verticalComponent > 0) {
            builder.append('S');
        }

        if (horizontalComponent < 0) {
            builder.append('W');
        } else if (horizontalComponent > 0) {
            builder.append('E');
        }

        return Direction.valueOf(builder.toString());
    }

    public  Command solutionAttack(){
        Direction direction = resolveDirection(0,0,arahX,arahY);
        return new ShootCommand(direction);
    }

    public Command solutionAltRoute() {
        String profession = currentWorm.profession;
        if (profession=="Commando") {
            if (arahX != 0 && arahY!=0) {
                return new MoveCommand(coorX+arahX,coorY+arahY-1);
            }
            else if (arahX!=0 && arahY==0) {
                if (coorY>16) {
                    return new MoveCommand(coorX+arahX,coorY+arahY-1);
                }
                else {
                    return new MoveCommand(coorX+arahX,coorY+arahY+1);
                }
            }
            else  { //if (arahX==0 && arahY!=0)
                if (coorX>13) {
                    return new MoveCommand(coorX+arahX-1,coorY+arahY);
                }
                else {
                    return new MoveCommand(coorX+arahX+1,coorY+arahY);
                }
            }
        }
        else if (profession=="Agent") {
            if (arahX != 0 && arahY!=0) {
                return new MoveCommand(coorX+arahX,coorY+arahY-1);
            }
            else if (arahX!=0 && arahY==0) {
                if (coorY>14) {
                    return new MoveCommand(coorX+arahX,coorY+arahY-1);
                }
                else {
                    return new MoveCommand(coorX+arahX,coorY+arahY+1);
                }
            }
            else  { //if (arahX==0 && arahY!=0)
                if (coorX>17) {
                    return new MoveCommand(coorX+arahX-1,coorY+arahY);
                }
                else {
                    return new MoveCommand(coorX+arahX+1,coorY+arahY);
                }
            }
        }
        else  { //(profession=="Technologist")
            if (arahX != 0 && arahY!=0) {
                return new MoveCommand(coorX+arahX,coorY+arahY-1);
            }
            else if (arahX!=0 && arahY==0) {
                if (coorY>19) {
                    return new MoveCommand(coorX+arahX,coorY+arahY-1);
                }
                else {
                    return new MoveCommand(coorX+arahX,coorY+arahY+1);
                }
            }
            else  { //if (arahX==0 && arahY!=0)
                if (coorX>18) {
                    return new MoveCommand(coorX+arahX-1,coorY+arahY);
                }
                else {
                    return new MoveCommand(coorX+arahX+1,coorY+arahY);
                }
            }
        }
    }



    public FormationSelector(GameState gs) {
        this.gameState = gs;
        this.currentWorm = this.getCurrentWorm();
        this.attempt = 1;
        this.coorX= currentWorm.position.x;
        this.coorY= currentWorm.position.y;
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

