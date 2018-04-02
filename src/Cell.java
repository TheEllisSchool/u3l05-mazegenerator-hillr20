

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import javax.swing.JPanel;


public class Cell extends JPanel {
	//one cell = one little block
	//we're going to be drawing in each little cell
	//JPanel has paint component that allows
    private static int SIZE = 20; //the length and width of each cell
    public static final int TOP = 0;
    public static final int RIGHT = 1;
    public static final int BOTTOM = 2; //used to find whether the cell has a wall on the bottom (check out arrays below)
    public static final int LEFT = 3;
    private int row = -1;
    private int col = -1; //curently initialized to negative one
    private boolean [] wall = {true, true, true, true};
    
    private boolean current = false; 
    private boolean end = false;
    
    private boolean [] path = {false, false, false, false}; //currently no path
    
    
    public Cell(int r, int c){ //the constructor, makes the cell and assigns row and column
        row = r;
        col = c;
    }
    
    public int getRow(){
        return row;
    }
    
    public int getCol(){
        return col;
    }
    
    public boolean isWall(int index){ //takes input of index(0,1,2,3)
        return wall[index];
    }
    
    public boolean hasAllWalls(){ //checks if there is a wall
        boolean allWalls = isWall(TOP) && isWall(BOTTOM) && 
                isWall(LEFT) && isWall(RIGHT);
        return allWalls;
    }
    
    public void removeWall(int w){ //use if there are four walls, this will tell us which to remove
        wall[w] = false;
        repaint();
    }
    
    
    public void openTo(Cell neighbor){
        if (row < neighbor.getRow()){
            removeWall(BOTTOM);
            neighbor.removeWall(TOP);
        } else if (row > neighbor.getRow()){
            removeWall(TOP);
            neighbor.removeWall(BOTTOM);
        } else if (col < neighbor.getCol()){
            removeWall(RIGHT);
            neighbor.removeWall(LEFT);
        } else if (col > neighbor.getCol()){
            removeWall(LEFT);
            neighbor.removeWall(RIGHT);
        }
    }
    
    public void setCurrent(boolean curr){
        current = curr;
        repaint();
    }
    
    public void setEnd(boolean e){ //is the end position gonna move? no //going to put it in the bottom right
        end = e; 
        repaint(); //update what that cell looks like by calling Paint component function
    }
    
    public void addPath(int side){
        path[side] = true;
        repaint();
    }
    
    @Override
    public Dimension getPreferredSize(){
        return new Dimension (SIZE, SIZE);
    }
    
    @Override
    public void paintComponent(Graphics g){
        //draw the background
        g.setColor(Color.BLACK);
        g.fillRect(0, 0, SIZE, SIZE);
        g.setColor(Color.MAGENTA);
       
        
        //draw the walls
        if (isWall(TOP)) {
        	g.drawLine(0, 0, SIZE, 0);//top line
        }
        if (isWall(LEFT)) {
        	g.drawLine(0, 0, 0, SIZE);//left line (right line doesn't work)
        }
        
        
        //draw the path
        g.setColor(Color.cyan);
        if (path[BOTTOM]) {
        	g.drawLine(SIZE/2, SIZE/2, SIZE/2, SIZE);
        }
        if (path[TOP]) {
        	g.drawLine(SIZE/2, 0, SIZE/2, SIZE/2);//begin at place where we ended, end in the middle of a cell
        }
      
        if (path[LEFT]) {
        	g.drawLine(0, SIZE/2, SIZE/2, SIZE/2);
        }
        if (path[RIGHT]) {
        	g.drawLine(SIZE, SIZE/2, SIZE/2, SIZE/2);//begin at place where we ended, end in the middle of a cell
        }
        
        
        
        //draw the balls
        if (current == true) {
        	g.setColor(Color.cyan);
            g.fillOval(SIZE/4, SIZE/4, SIZE - (SIZE/2), SIZE - (SIZE/2));
        }
        
        if (end == true) {
        	g.setColor(Color.PINK);
            g.fillOval(SIZE/4, SIZE/4, SIZE - (SIZE/2), SIZE - (SIZE/2));
        }
    }
    //public void reset() {
		//setBackground(null);
	//}
    
    
}
