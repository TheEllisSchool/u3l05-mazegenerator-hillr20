

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import javax.swing.JPanel;


public class Cell extends JPanel {
    private static int SIZE = 20;
    public static final int TOP = 0;
    public static final int RIGHT = 1;
    public static final int BOTTOM = 2;
    public static final int LEFT = 3;
    private int row = -1;
    private int col = -1;
    private boolean [] wall = {true, true, true, true};
    
    private boolean current = false;
    private boolean end = false;
    
    private boolean [] path = {false, false, false, false};
    
    
    public Cell(int r, int c){
        row = r;
        col = c;
    }
    
    public int getRow(){
        return row;
    }
    
    public int getCol(){
        return col;
    }
    
    public boolean isWall(int index){
        return wall[index];
    }
    
    public boolean hasAllWalls(){
        boolean allWalls = isWall(TOP) && isWall(BOTTOM) && 
                isWall(LEFT) && isWall(RIGHT);
        return allWalls;
    }
    
    public void removeWall(int w){
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
    
    public void setEnd(boolean e){
        end = e;
        repaint();
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
        g.setColor(Color.WHITE);
        g.fillRect(0, 0, SIZE, SIZE);
        g.setColor(Color.BLACK);
        
        //draw the walls
        
        
        //draw the path
        g.setColor(Color.GREEN);
        
        
        //draw the balls
        
    }
    
    
}
