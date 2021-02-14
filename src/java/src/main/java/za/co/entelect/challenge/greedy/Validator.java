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
    
    public boolean isValidSolution(Solution s) {
        MyWorm currentWorm = this.getCurrentWorm(s.wormId);
        String solutionType = s.solutionType;
        switch (solutionType) {
            case "move":
                return this.isAir(s.x, s.y) && (!this.hasMyWorm(s.x, s.y))
                        && (!this.hasOpponentWorm(s.x, s.y))
                        && euclideanSquareDistance(currentWorm.position.x,
                                                    currentWorm.position.y,
                                                    s.x, s.y) <= 2
                        && this.isValidCoordinate(s.x, s.y);
                
            case "shoot":
                return !this.isFriendlyFire(s.x, s.y, s.direction);
                
            case "banana":
                if (bananaCount > 0) {
                    bananaCount--;
                    return currentWorm.profession == "Agent"
                            && this.getMinPlayerWormSquareDistance(s.x, s.y)
                                > 4
                            && euclideanSquareDistance(currentWorm.position.x,
                                                        currentWorm.position.y,
                                                        s.x, s.y) <= 25
                            && this.isValidCoordinate(s.x, s.y);
                    
                } else {
                    return false;
                    
                }
                
                
            case "snowball":
                if (snowballCount > 0) {
                    snowballCount--;
                    return currentWorm.profession == "Technologist"
                            && this.getMinPlayerWormSquareDistance(s.x, s.y)
                                > 2
                            && euclideanSquareDistance(currentWorm.position.x,
                                                        currentWorm.position.y,
                                                        s.x, s.y) <= 25
                            && this.isValidCoordinate(s.x, s.y);
                    
                } else {
                    return false;
                    
                }
                
                
            case "dig":
                return this.isDirt(s.x, s.y) // termasuk validasi koordinat
                        && euclideanSquareDistance(currentWorm.position.x,
                                                    currentWorm.position.y,
                                                    s.x, s.y) <= 2;
                
            case "nothing":
                return this.gameState.consecutiveDoNothingCount < 12;
                
            default:
                throw new IllegalArgumentException(
                    String.format("Unknown command: %s", s.solutionType)
                );
           
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
    
    // starter-bot function
    private MyWorm getCurrentWorm(int wormId) {
        return Arrays.stream(this.gameState.myPlayer.worms)
                .filter(myWorm -> myWorm.id == wormId)
                .findFirst()
                .get();
    }
    
    private static int euclideanSquareDistance(int aX, int aY, int bX, int bY) {
        return (int) (Math.pow(aX - bX, 2) + Math.pow(aY - bY, 2));
    }

    private boolean isValidCoordinate(int x, int y) {
        return x >= 0 && x < gameState.mapSize
                && y >= 0 && y < gameState.mapSize;
                
    }
    
}
