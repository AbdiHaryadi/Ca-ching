package za.co.entelect.challenge.greedy;

import za.co.entelect.challenge.command.*;
import za.co.entelect.challenge.entities.Position;
import za.co.entelect.challenge.enums.Direction;

public class Solution {
    public String solutionType;
    public int x;
    public int y;
    public Direction direction;
    public int wormId;
    
    public Solution(String st, int x, int y, Direction d, int w) {
        this.solutionType = st;
        this.x = x;
        this.y = y;
        this.direction = d;
        this.wormId = w;
        
    }
    
    public Command toCommand() {
        solutionType = this.solutionType;
        switch (solutionType) {
            case "move":
                return new MoveCommand(this.x, this.y);
            case "shoot":
                return new ShootCommand(this.direction);
            case "banana":
                return new BananaBombCommand(this.x, this.y);
            case "snowball":
                return new SnowballCommand(this.x, this.y);
            case "dig":
                return new DigCommand(this.x, this.y);
            case "nothing":
                return new DoNothingCommand();
            default:
                throw new IllegalArgumentException(
                    String.format("Unknown command: %s", this.solutionType)
                );
            
        }
        
    }

}