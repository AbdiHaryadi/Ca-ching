package za.co.entelect.challenge;

import za.co.entelect.challenge.command.*;
import za.co.entelect.challenge.entities.*;
import za.co.entelect.challenge.enums.CellType;
import za.co.entelect.challenge.enums.Direction;

import java.util.*;
import java.util.stream.Collectors;

public class Bot {
    private Random random;
    private GameState gameState;
    
    public Bot(Random random, GameState gameState) {
        this.random = random;
        this.gameState = gameState;
        
    }

    public Command run() {
        return new DoNothingCommand();
        
    }
    
}
