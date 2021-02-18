package za.co.entelect.challenge.greedy;

import za.co.entelect.challenge.entities.*;
import za.co.entelect.challenge.enums.*;
import za.co.entelect.challenge.command.*;

import java.util.*;

public class Validator {
    private final static int adjacentSquareRange = 2;
    private final static int bananaBombDamageSquareRange = 4;
    private final static int bananaBombThrowSquareRange = 25;
    private final static int snowballDamageSquareRange = 2;
    private final static int snowballThrowSquareRange = 25;
    private static int bananaCount = 3;
    private static int snowballCount = 3;

    private final GameState gameState;

    public Validator(GameState gs) {
        this.gameState = gs;

    }

    public boolean isValidSolution(Command command) {
        MyWorm currentWorm = this.getCurrentWorm();
        int x, y;
        Direction direction;

        if (command instanceof MoveCommand) {
            x = ((MoveCommand) command).getX();
            y = ((MoveCommand) command).getY();
            return this.isAir(x, y)
                    && (!this.hasMyWorm(x, y))
                    && (!this.hasOpponentWorm(x, y))
                    && euclideanSquareDistance(currentWorm.position.x,
                                                currentWorm.position.y, x, y)
                        <= adjacentSquareRange
                    && this.isValidCoordinate(x, y);

        } else if (command instanceof ShootCommand) {
            direction = ((ShootCommand) command).getDirection();
            return !this.isFriendlyFire(currentWorm.position.x,
                                         currentWorm.position.y, direction);

        } else if (command instanceof BananaBombCommand) {
            if (bananaCount > 0) {
                bananaCount--;
                x = ((BananaBombCommand) command).getX();
                y = ((BananaBombCommand) command).getY();
                return currentWorm.profession.equals("Agent")
                        && this.getMinPlayerWormSquareDistance(x, y)
                            > bananaBombDamageSquareRange
                        && euclideanSquareDistance(currentWorm.position.x,
                                                    currentWorm.position.y,
                                                    x, y)
                            <= bananaBombThrowSquareRange
                        && this.isValidCoordinate(x, y);
                
            } else {
                return false;
                
            }
            
        } else if (command instanceof SnowballCommand) {

            if (snowballCount > 0) {
                snowballCount--;
                x = ((SnowballCommand) command).getX();
                y = ((SnowballCommand) command).getY();
                return currentWorm.profession.equals("Technologist")
                        && this.getMinPlayerWormSquareDistance(x, y)
                            > snowballDamageSquareRange
                        && euclideanSquareDistance(currentWorm.position.x,
                                                    currentWorm.position.y,
                                                    x, y)
                             <= snowballThrowSquareRange
                        && this.isValidCoordinate(x, y);
                
            } else {
                return false;
                
            }
            
        } else if (command instanceof DigCommand) {
            x = ((DigCommand) command).getX();
            y = ((DigCommand) command).getY();
            return this.isDirt(x, y) // termasuk validasi koor.
                    && euclideanSquareDistance(currentWorm.position.x,
                                                currentWorm.position.y,
                                                x, y) <= 2;
            
        } else if (command instanceof DoNothingCommand) {
            return false;
            
        } else {
            throw new IllegalArgumentException("Unknown command detected!");
            
        }
        
    }
    
    private boolean isAir(int x, int y) {
        if (isValidCoordinate(x, y)) {
            return this.gameState.map[y][x].type == CellType.AIR;
            
        } else {
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
    
    private boolean hasMyWorm(int x, int y) {
        int i;
        int totalWorm;
        boolean found;
        
        totalWorm = 3; // Cacing teman ada 3
        found = false;
        
        i = 0;
        while (i < totalWorm && (!found)) {
            if (this.gameState.myPlayer.worms[i].position.x == x
                 && this.gameState.myPlayer.worms[i].position.y == y) {
                found = true;
                
            } else {
                i++;
                
            }
            
        }
        
        return found;
        
    }
    
    private boolean hasOpponentWorm(int x, int y) {
        int i;
        int totalWorm;
        boolean found;
        
        totalWorm = 3; // Cacing musuh ada 3
        found = false;
        
        i = 0;
        while (i < totalWorm && (!found)) {
            if (this.gameState.opponents[0].worms[i].position.x == x
                 && this.gameState.opponents[0].worms[i].position.y == y) {
                found = true;
                
            } else {
                i++;
                
            }
            
        }
        
        return found;
        
    }
    
    // Melihat apakah ada friendly fire saat cacing di posisi x, y menembak
    // dalam arah d
    private boolean isFriendlyFire(int x, int y, Direction d) {
        int currX;
        int currY;
        boolean found;
        int i;
        final int maxRange = 4; // horizontal, vertikal, maupun diagonal 
        
        i = 0;
        currX = x + d.x;
        currY = y + d.y;
        found = false;
        while ((i < maxRange) && (!this.hasOpponentWorm(currX, currY)) && (!found)) {
            if (this.hasMyWorm(currX, currY)) {
                found = true;
                
            } else {
                currX += d.x;
                currY += d.y;
                i++;
                
            }
            
        }
        
        return found;

    }
    
    private int getMinPlayerWormSquareDistance(int x, int y) {
        int minSquareDistance;
        int totalWorm;
        int i;
        int currSquareDistance;
        
        minSquareDistance = 10000;
        totalWorm = 3; // Cacing teman ada 3
        for (i = 0; i < totalWorm; i++) {
            currSquareDistance = euclideanSquareDistance(
                this.gameState.myPlayer.worms[i].position.x,
                this.gameState.myPlayer.worms[i].position.y, x, y);
                
            if (currSquareDistance < minSquareDistance) {
                minSquareDistance = currSquareDistance;
                
            }
            
        }
        
        return minSquareDistance;
        
    }
    
    // starter-bot functions
    private MyWorm getCurrentWorm() {
        return Arrays.stream(this.gameState.myPlayer.worms)
                .filter(myWorm -> myWorm.id == this.gameState.currentWormId)
                .findFirst()
                .get();
    }

    private static int euclideanSquareDistance(int aX, int aY, int bX, int bY) {
        return (int) (Math.pow(aX - bX, 2) + Math.pow(aY - bY, 2));
    }

    private boolean isValidCoordinate(int x, int y) {
        return x >= 0 && x < this.gameState.mapSize
                && y >= 0 && y < this.gameState.mapSize;
                
    }
    
}
