package za.co.entelect.challenge.greedy;

import za.co.entelect.challenge.command.*;
import za.co.entelect.challenge.entities.*;
import za.co.entelect.challenge.enums.CellType;
import za.co.entelect.challenge.enums.Direction;

import java.util.*;
import java.util.stream.Collectors;

public class DefenseSelector extends Selector {

    private int attempt;
    private GameState gameState;
    private Opponent opponent;
    private MyWorm currentWorm;

    public DefenseSelector(GameState gameState) {
        this.attempt = 1;
        this.gameState = gameState;
        this.opponent = gameState.opponents[0];
        this.currentWorm = getCurrentWorm(gameState);
    }

    private MyWorm getCurrentWorm(GameState gameState) {
        return Arrays.stream(gameState.myPlayer.worms)
                .filter(myWorm -> myWorm.id == gameState.currentWormId)
                .findFirst()
                .get();
    }

    private Worm getFirstWormInRange() {

        ArrayList<Cell> cells = new ArrayList<>();
        for (int i=-5;i<=5;i++){
            for(int j=-5;j<=5;j++){
            	if(euclideanDistance(0, 0, i, j)<6)
	  	    cells.add(gameState.map[currentWorm.position.x+i][currentWorm.position.y+j]);
	    }
	}

        for (Worm enemyWorm : opponent.worms) {
            Cell enemyPosition = gameState.map[(enemyWorm.position.x)][(enemyWorm.position.y)];
            if (cells.contains(enemyPosition)) {
                return enemyWorm;
            }
        }

        return null;
    }

    private boolean isValidCoordinate(int x, int y) {
        return x >= 0 && x < gameState.mapSize
                && y >= 0 && y < gameState.mapSize;
    }
    
    private List<Cell> getSurroundingCells(int x, int y) {
        ArrayList<Cell> cells = new ArrayList<>();
        for (int i = x - 1; i <= x + 1; i++) {
            for (int j = y - 1; j <= y + 1; j++) {
                // Don't include the current position
                if (i != x && j != y && isValidCoordinate(i, j)) {
                    cells.add(gameState.map[j][i]);
                }
            }
        }

        return cells;
    }

    private int euclideanDistance(int aX, int aY, int bX, int bY) {
        return (int) (Math.sqrt(Math.pow(aX - bX, 2) + Math.pow(aY - bY, 2)));
    }

    private Direction resolveDirection(Position a, Position b) {
        StringBuilder builder = new StringBuilder();

        Cell enemyCell = gameState.map[(b.x)][(b.y)];
        List<Cell> northDir = NorthArea(a);
        List<Cell> northEastDir = NorthEastArea(a);
        List<Cell> eastDir = EastArea(a);
        List<Cell> southEastDir = SouthEastArea(a);
        List<Cell> southDir = SouthArea(a);
        List<Cell> southWestDir = SouthWestArea(a);
        List<Cell> westDir = WestArea(a);
        List<Cell> northWestDir = NorthWestArea(a);
        if (northDir.contains(enemyCell) == true) {
            builder.append('N');
        } 
        else if (northEastDir.contains(enemyCell)==true) {
            builder.append("NE");
        }
	else if (eastDir.contains(enemyCell)==true){
	    builder.append('E');
	}
	else if (southEastDir.contains(enemyCell)==true){
	    builder.append("SE");
	}
	else if (southDir.contains(enemyCell)==true){
	    builder.append('S');
	}
	else if (southWestDir.contains(enemyCell)==true){
	    builder.append("SW");
	}
	else if (westDir.contains(enemyCell)==true){
	    builder.append('W');
	}
	else if (northWestDir.contains(enemyCell)==true){
	    builder.append("NW");
	}
	
        return Direction.valueOf(builder.toString());
    }
    
    private Command BombEnemy(){
    	Worm enemyWorm = getFirstWormInRange();
        if (enemyWorm != null) {
            return new BananaBombCommand(enemyWorm.position.x, enemyWorm.position.y);
        }
        else
        {
            return new DoNothingCommand();        	
        }
        
    }
    	
    private Command FreezeEnemy(){
    	Worm enemyWorm = getFirstWormInRange();
    	int a=currentWorm.position.x;
    	int b=currentWorm.position.y;
    	int c=enemyWorm.position.x;
    	int d=enemyWorm.position.y;
    	
        if (enemyWorm != null && (a-c==0 || b-d==0 || a-c==b-d ||a-c==-(b-d)) && (enemyWorm.roundsUntilUnfrozen==0)) {
            return new SnowballCommand(enemyWorm.position.x, enemyWorm.position.y);
        }
        else
        {
            return new DoNothingCommand();        	
        }
    }
    
    private Command ShootEnemy(){
	Worm enemyWorm = getFirstWormInRange();
	Direction enemyDir = resolveDirection(currentWorm.position, enemyWorm.position);
    	
        if (enemyWorm != null && enemyDir != null) {
            return new ShootCommand(enemyDir);
        }
        else
        {
	    return new DoNothingCommand();
	}
    }
    
    private Command DigDirt(){
    	Command result = null;

        for (int i=-1;i<=1;i++){
            if (i!=0){
            	Cell block1=gameState.map[currentWorm.position.x+i][currentWorm.position.y];
            	Cell block2=gameState.map[currentWorm.position.x][currentWorm.position.y+i];
            	Cell block3=gameState.map[currentWorm.position.x+i][currentWorm.position.y+i];
            	Cell block4=gameState.map[currentWorm.position.x-i][currentWorm.position.y+i];
            	Cell block5=gameState.map[currentWorm.position.x+i][currentWorm.position.y-i];
            	if (block1.type==CellType.DIRT) {
            		result = new DigCommand((currentWorm.position.x+i),(currentWorm.position.y));
            	}
            	else if(block2.type==CellType.DIRT)
	            {
			result = new DigCommand((currentWorm.position.x),(currentWorm.position.y+i));
		    }
		else if(block3.type==CellType.DIRT)
        	{
		    result = new DigCommand((currentWorm.position.x+i),(currentWorm.position.y+i));
		}
		else if(block4.type==CellType.DIRT)
        	{
		    result = new DigCommand((currentWorm.position.x-i),(currentWorm.position.y+i));
		}
		else if(block5.type==CellType.DIRT)
        	{
		    result = new DigCommand((currentWorm.position.x+i),(currentWorm.position.y-i));
		}
	        else
        	{
	    	    result = new DoNothingCommand();
		}
	    }
        }

        return result;
    }
    
    private Command RandomShoot(){
		Direction d;
		Random random = new Random();
    	int number=random.nextInt(8);
        if (number==0) {
            return new ShootCommand(new Direction(0, -1));
        }
        else if (number==1) {
            return new ShootCommand(new Direction(1, -1));
        }
        else if (number==2) {
            return new ShootCommand(new Direction(1, 0));
        }
        else if (number==3) {
            return new ShootCommand(new Direction(1, 1));
        }
        else if (number==4) {
            return new ShootCommand(new Direction(0, 1));
        }
        else if (number==5) {
            return new ShootCommand(new Direction(-1, 1));
        }
        else if (number==6) {
            return new ShootCommand(new Direction(-1, -0);
        }
        else {
            return new ShootCommand(new Direction(-1, -1));
        }
    }    
	
    private List<Cell> NorthArea(Position a){
    	ArrayList<Cell> cells = new ArrayList<>();
    	for (int i=1; i<=5; i++){
    	    if ((a.y)-i>=0){
    	        cells.add(gameState.map[(a.x)][(a.y)-i]);
    	    }
    	    else if (i>1 && (a.y)-i>=0 && (a.x)+1<=32){
		cells.add(gameState.map[(a.x)+1][(a.y)-i]);
	    }
	    else if (i>1 && (a.y)-i>=0 && (a.x)-1>=0){
		cells.add(gameState.map[(a.x)-1][(a.y)-i]);
	    }
    	}
    	return cells;
    }
    
    private List<Cell> NorthEastArea(Position a){
    	ArrayList<Cell> cells = new ArrayList<>();
    	for (int i=1; i<=4; i++){
    	    if ((a.x)+i<=32 && (a.y)-i>=0){
    	        cells.add(gameState.map[(a.x)+i][(a.y)-i]);
    	    }
    	    else if (i>1 && (a.y)-i>=0 && (a.x)+i+1<=32){
		cells.add(gameState.map[(a.x)+i+1][(a.y)-i]);
	    }
	    else if (i>1 && (a.y)-i-1>=0 && (a.x)+i<=32){
		cells.add(gameState.map[(a.x)+i][(a.y)-i-1]);
	    }
    	}
    	return cells;
    }
    
    private List<Cell> SouthArea(Position a){
    	ArrayList<Cell> cells = new ArrayList<>();
    	for (int i=1; i<=5; i++){
    	    if ((a.y)+i<=32){
    	        cells.add(gameState.map[(a.x)][(a.y)+i]);
    	    }
    	    else if (i>1 && (a.y)+i<=32 && (a.x)+1<=32){
		cells.add(gameState.map[(a.x)+1][(a.y)+i]);
	    }
	    else if (i>1 && (a.y)+i<=32 && (a.x)-1>=0){
		cells.add(gameState.map[(a.x)-1][(a.y)+i]);
	    }
    	}
    	return cells;
    }
    
    private List<Cell> SouthEastArea(Position a){
    	ArrayList<Cell> cells = new ArrayList<>();
    	for (int i=1; i<=4; i++){
    	    if ((a.x)+i<=32 && (a.y)+i<=32){
    	        cells.add(gameState.map[(a.x)+i][(a.y)+i]);
    	    }
    	    else if (i>1 && (a.y)+i<=32 && (a.x)+i+1<=32){
		cells.add(gameState.map[(a.x)+i+1][(a.y)+i]);
	    }
	    else if (i>1 && (a.y)+i+1<=32 && (a.x)+i<=32){
		cells.add(gameState.map[(a.x)+i][(a.y)+i+1]);
	    }
    	}
    	return cells;
    }
    
    private List<Cell> EastArea(Position a){
    	ArrayList<Cell> cells = new ArrayList<>();
    	for (int i=1; i<=5; i++){
    	    if ((a.x)-i>=0){
    	        cells.add(gameState.map[(a.x)-i][(a.y)]);
    	    }
    	    else if (i>1 && (a.x)-i>=0 && (a.y)+1<=32){
		cells.add(gameState.map[(a.x)-i][(a.y)+1]);
	    }
	    else if (i>1 && (a.x)-i>=0 && (a.y)-1>=0){
		cells.add(gameState.map[(a.x)-i][(a.y)-1]);
	    }
    	}
    	return cells;
    }
    
    private List<Cell> SouthWestArea(Position a){
    	ArrayList<Cell> cells = new ArrayList<>();
    	for (int i=1; i<=4; i++){
    	    if ((a.x)-i>=0 && (a.y)+i<=32){
    	        cells.add(gameState.map[(a.x)-i][(a.y)+i]);
    	    }
    	    else if (i>1 && (a.y)+i<=32 && (a.x)-i-1>=0){
		cells.add(gameState.map[(a.x)-i-1][(a.y)+i]);
	    }
	    else if (i>1 && (a.y)+i+1<=32 && (a.x)-i>=0){
		cells.add(gameState.map[(a.x)-i][(a.y)+i+1]);
	    }
    	}
    	return cells;
    }
    
    private List<Cell> WestArea(Position a){
    	ArrayList<Cell> cells = new ArrayList<>();
    	for (int i=1; i<=5; i++){
    	    if ((a.x)+i<=32){
    	        cells.add(gameState.map[(a.x)+i][(a.y)]);
    	    }
    	    else if (i>1 && (a.x)+i<=32 && (a.y)+1<=32){
		cells.add(gameState.map[(a.x)+i][(a.y)+1]);
	    }
	    else if (i>1 && (a.x)+i<=32 && (a.y)-1>=0){
		cells.add(gameState.map[(a.x)+i][(a.y)-1]);
	    }
    	}
    	return cells;
    }
    
    private List<Cell> NorthWestArea(Position a){
    	ArrayList<Cell> cells = new ArrayList<>();
    	for (int i=1; i<=4; i++){
    	    if ((a.x)-i>=0 && (a.y)-i>=0){
    	        cells.add(gameState.map[(a.x)-i][(a.y)-i]);
    	    }
    	    else if (i>1 && (a.y)-i>=0 && (a.x)-i-1>=0){
		cells.add(gameState.map[(a.x)-i-1][(a.y)-i]);
	    }
	    else if (i>1 && (a.y)-i-1>=0 && (a.x)-i>=0){
		cells.add(gameState.map[(a.x)-i][(a.y)-i-1]);
	    }
    	}
    	return cells;
    }
    
    public boolean hasNext() {
        return this.attempt < 5;
    }
    
    public void next() {
        this.attempt++; 
    }

    public Command getSolution() {
        if (this.attempt == 1) {
            return BombEnemy();
        } 
        else if (this.attempt == 2) {
            return FreezeEnemy();
        }
        else if (this.attempt == 3) {
            return ShootEnemy();
        }
        else if (this.attempt == 4) {
            return DigDirt();
        }
        else {
            return RandomShoot();
        }
}
}
