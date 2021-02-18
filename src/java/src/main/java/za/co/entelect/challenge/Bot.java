package za.co.entelect.challenge;

import za.co.entelect.challenge.command.*;
import za.co.entelect.challenge.entities.*;
// import za.co.entelect.challenge.enums.CellType;
// import za.co.entelect.challenge.enums.Direction;
import za.co.entelect.challenge.greedy.*;

import java.util.*;
import java.util.stream.Collectors;

public class Bot {
    private GameState gameState;
    
    public Bot(GameState gameState) {
        this.gameState = gameState;
        
    }

    public Command run() {
        Selector selector;
        Command tempCommand;
        Validator validator;
        boolean stop;
        boolean found;
        
        if (isAllPlayerWormsInFixedPosition()) {
            selector = new DefenseSelector(this.gameState);
            
        } else {
            selector = new FormationSelector(this.gameState);
            
        }

        validator = new Validator(this.gameState);
        
        stop = false;
        do {
            tempCommand = selector.getSolution();
            found = validator.isValidSolution(tempCommand);

            if (found || (!selector.hasNext())) {
                stop = true;
                
            } else {
                selector.next();
                
            }
            
        } while (!stop);
        
        if (found) {
            return tempCommand;
            
        } else {
            return new DoNothingCommand();
            
        }
        
    }
    
    private boolean isAllPlayerWormsInFixedPosition() {
        // Posisi: Commando: (13,16), Agent: (17, 14), Techno: (18, 19)
        int totalWorm;
        int i;
        String profession;
        boolean inFixedPosition;
        
        totalWorm = 3;
        inFixedPosition = true;
        i = 0;
        while (i < totalWorm && inFixedPosition) {
            profession = gameState.myPlayer.worms[i].profession;
            switch (profession) {
                case "Commando":
                    inFixedPosition =
                        gameState.myPlayer.worms[i].position.x == 13
                        && gameState.myPlayer.worms[i].position.x == 16;
                    break;
                case "Agent":
                    inFixedPosition =
                        gameState.myPlayer.worms[i].position.x == 17
                        && gameState.myPlayer.worms[i].position.x == 14;
                    break;
                case "Technologist":
                    inFixedPosition =
                        gameState.myPlayer.worms[i].position.x == 18
                        && gameState.myPlayer.worms[i].position.x == 19;
                    break;
                default:
                    throw new IllegalArgumentException(
                        String.format("Unknown profession: %s", profession)
                    );
            }
            
            i++;
            
        }
        
        return inFixedPosition;
        
        
        
    }
    
}
