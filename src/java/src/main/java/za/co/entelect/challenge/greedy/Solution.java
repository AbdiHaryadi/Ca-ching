package za.co.entelect.challenge.greedy;

import za.co.entelect.challenge.entities.Position;
import za.co.entelect.challenge.enums.Direction;

public class Solution {
    public String solutionType;
    public Position coordinate;
    public Direction direction;
    public int wormId;
    
    public Solution(string st, Position c, Direction d, int id) {
        this.solutionType = st;
        this.coordinate = c;
        this.direction = d;
        this.wormId = id;
        
    }

}