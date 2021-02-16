package za.co.entelect.challenge.greedy;

import za.co.entelect.challenge.entities.GameState;

public class DefenseSelector extends Selector {
    // Implementasikan di sini. Bisa buat atribut baru, method baru, bebas.
    // ...
    private GameState gameState;
    
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
    
    private List<Cell> BananaAreaInFixedPosition(){
    	ArrayList<Cell> cells = new ArrayList<>();
    	cells.add(gameState.map[16][12]);
    	cells.add(gameState.map[15][13]);
    	cells.add(gameState.map[14][13]);
    	cells.add(gameState.map[14][14]);
    	cells.add(gameState.map[15][15]);
    	cells.add(gameState.map[16][16]);
    	cells.add(gameState.map[18][16]);
    	cells.add(gameState.map[15][17]);
    	cells.add(gameState.map[16][17]);
    	cells.add(gameState.map[17][17]);
    	cells.add(gameState.map[14][18]);
    	cells.add(gameState.map[15][18]);
    	cells.add(gameState.map[16][18]);
    	cells.add(gameState.map[14][19]);
    	cells.add(gameState.map[15][19]);
    	cells.add(gameState.map[16][20]);
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
	else if (WestDir.contains(enemyCell)==true){
	    builder.append('W');
	}
	else if (northWestDir.contains(enemyCell)==true){
	    builder.append("NW");
	}
	
        return Direction.valueOf(builder.toString());
    }
    
    public DefenseSelector(GameState gameState) {
        // Pastikan setelah dibuat, solusinya telah disediakan setidaknya satu
        // (sehingga getSolution() bisa digunakan)
        
    }
    
    public Solution getSolution() {
        
        
    }
    
}
