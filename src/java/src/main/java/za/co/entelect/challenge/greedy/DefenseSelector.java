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

        Set<String> cells = constructFireDirectionLines(currentWorm.weapon.range)
                .stream()
                .flatMap(Collection::stream)
                .map(cell -> String.format("%d_%d", cell.x, cell.y))
                .collect(Collectors.toSet());

        for (Worm enemyWorm : opponent.worms) {
            String enemyPosition = String.format("%d_%d", enemyWorm.position.x, enemyWorm.position.y);
            if (cells.contains(enemyPosition)) {
                return enemyWorm;
            }
        }

        return null;
    }	
	
    private Worm getFirstWormInArea() {

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

    private int euclideanDistance(int aX, int aY, int bX, int bY) {
        return (int) (Math.sqrt(Math.pow(aX - bX, 2) + Math.pow(aY - bY, 2)));
    }
    
    private Command BombEnemy(){
    	Worm enemyWorm = getFirstWormInArea();
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
    	int c=0,d=0;
    	if (enemyWorm != null) {
			c = enemyWorm.position.x;
			d = enemyWorm.position.y;
		}
    	
        if (enemyWorm != null  && (enemyWorm.roundsUntilUnfrozen==0)) {
            return new SnowballCommand(enemyWorm.position.x, enemyWorm.position.y);
        }
        else
        {
            return new DoNothingCommand();        	
        }
    }
    
    private Command ShootEnemy(){
	Worm enemyWorm = getFirstWormInRange();
	Direction enemyDir=null;
	if (enemyWorm != null){
		enemyDir = resolveDirection(currentWorm.position, enemyWorm.position);
	}

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
            		return result;
            	}
            	else if(block2.type==CellType.DIRT)
	            {
			        result = new DigCommand((currentWorm.position.x),(currentWorm.position.y+i));
			        return result;
		        }
	    	    else if(block3.type==CellType.DIRT)
            	{
            	    result = new DigCommand((currentWorm.position.x+i),(currentWorm.position.y+i));
            	    return result;
		        }
		        else if(block4.type==CellType.DIRT)
        	    {
		            result = new DigCommand((currentWorm.position.x-i),(currentWorm.position.y+i));
		            return result;
        	    }
		        else if(block5.type==CellType.DIRT)
        	    {
		        result = new DigCommand((currentWorm.position.x+i),(currentWorm.position.y-i));
		        return result;
		        }
	        }
        }

        return new DoNothingCommand();
    }

    private Command RandomShoot(){
		StringBuilder builder = new StringBuilder();
		builder.append('N');
		Direction d=Direction.valueOf(builder.toString());
		return new ShootCommand(d);
    }    
	
    private List<List<Cell>> constructFireDirectionLines(int range) {
        List<List<Cell>> directionLines = new ArrayList<>();
        for (Direction direction : Direction.values()) {
            List<Cell> directionLine = new ArrayList<>();
            for (int directionMultiplier = 1; directionMultiplier <= range; directionMultiplier++) {

                int coordinateX = currentWorm.position.x + (directionMultiplier * direction.x);
                int coordinateY = currentWorm.position.y + (directionMultiplier * direction.y);

                if (!isValidCoordinate(coordinateX, coordinateY)) {
                    break;
                }

                if (euclideanDistance(currentWorm.position.x, currentWorm.position.y, coordinateX, coordinateY) > range) {
                    break;
                }

                Cell cell = gameState.map[coordinateY][coordinateX];
                if (cell.type != CellType.AIR) {
                    break;
                }

                directionLine.add(cell);
            }
            directionLines.add(directionLine);
        }

        return directionLines;
    }
    
    private boolean isValidCoordinate(int x, int y) {
        return x >= 0 && x < gameState.mapSize
                && y >= 0 && y < gameState.mapSize;
    }

    private Direction resolveDirection(Position a, Position b) {
        StringBuilder builder = new StringBuilder();

        int verticalComponent = b.y - a.y;
        int horizontalComponent = b.x - a.x;

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
