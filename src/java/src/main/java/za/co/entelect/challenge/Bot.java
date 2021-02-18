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
    private MyWorm currentWorm;
    
    public Bot(GameState gameState) {
        this.gameState = gameState;
        this.currentWorm = this.getCurrentWorm();
        
    }

    public Command run() {
        Selector selector;
        Command tempCommand;
        Validator validator;
        boolean stop;
        boolean found;

        // update currentWorm
        this.currentWorm = this.getCurrentWorm();

        if (inFixedPosition()) {
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
    
    private boolean inFixedPosition() {
        // Posisi: Commando: (13,16), Agent: (17, 14), Techno: (18, 19)
        String profession;

        profession = this.currentWorm.profession;
        switch (profession) {
            case "Commando":
                return
                        this.currentWorm.position.x == 13
                                && this.currentWorm.position.y == 16;
            case "Agent":
                return
                        this.currentWorm.position.x == 17
                                && this.currentWorm.position.y == 14;
            case "Technologist":
                return
                        this.currentWorm.position.x == 18
                                && this.currentWorm.position.y == 19;
            default:
                throw new IllegalArgumentException(
                        String.format("Unknown profession: %s", profession)
                );

        }

    }

    private MyWorm getCurrentWorm() {
        return Arrays.stream(this.gameState.myPlayer.worms)
                .filter(myWorm -> myWorm.id == this.gameState.currentWormId)
                .findFirst()
                .get();
    }
    
}
