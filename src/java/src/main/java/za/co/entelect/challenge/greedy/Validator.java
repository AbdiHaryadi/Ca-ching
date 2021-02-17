package za.co.entelect.challenge.greedy;

import za.co.entelect.challenge.entities.*;
import za.co.entelect.challenge.enums.*;

import java.util.*;
import java.util.stream.Collectors;

public class Validator {
    public GameState gameState;
    public static int bananaCount = 3;
    public static int snowballCount = 3;
    
    public Validator(GameState gs) {
        this.gameState = gs;
        
    }
    
    public boolean isValidSolution(Command command) {
        MyWorm currentWorm = this.getCurrentWorm();
        
        if (command instanceof MoveCommand) {
            return this.isAir(command.x, command.y)
                    && (!this.hasMyWorm(command.x, command.y))
                    && (!this.hasOpponentWorm(command.x, command.y))
                    && euclideanSquareDistance(currentWorm.position.x,
                                                currentWorm.position.y,
                                                command.x, command.y) <= 2
                    && this.isValidCoordinate(command.x, command.y);
            
        } else if (command instanceof ShootCommand) {
            return !this.isFriendlyFire(currentWorm.position.x,
                                         currentWorm.position.y,
                                         command.direction);
            
        } else if (command instanceof BananaBombCommand) {
            if (bananaCount > 0) {
                bananaCount--;
                return currentWorm.profession == "Agent"
                        && this.getMinPlayerWormSquareDistance(command.x,
                                                                command.y) > 4
                        && euclideanSquareDistance(currentWorm.position.x,
                                                    currentWorm.position.y,
                                                    command.x, command.y) <= 25
                        && this.isValidCoordinate(command.x, command.y);
                
            } else {
                return false;
                
            }
            
        } else if (command instanceof SnowballCommand) {
            if (snowballCount > 0) {
                snowballCount--;
                return currentWorm.profession == "Technologist"
                        && this.getMinPlayerWormSquareDistance(command.x,
                                                                command.y) > 2
                        && euclideanSquareDistance(currentWorm.position.x,
                                                    currentWorm.position.y,
                                                    command.x, command.y) <= 25
                        && this.isValidCoordinate(command.x, command.y);
                
            } else {
                return false;
                
            }
            
        } else if (command instanceof DigCommand) {
            return this.isDirt(command.x, command.y) // termasuk validasi koor.
                    && euclideanSquareDistance(currentWorm.position.x,
                                                currentWorm.position.y,
                                                command.x, command.y) <= 2;
            
        } else if (command instanceof DoNothingCommand) {
            return this.gameState.consecutiveDoNothingCount < 12;
            
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
        
        currX = x + d.x;
        currY = y + d.y;
        found = false;
        while (this.isValidCoordinate(currX, currY)
                && (!this.hasOpponentWorm(currX, currY)) && (!found)) {
            if (this.hasMyWorm(currX, currY)) {
                found = true;
                
            } else {
                currX += d.x;
                currY += d.y;
                
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
