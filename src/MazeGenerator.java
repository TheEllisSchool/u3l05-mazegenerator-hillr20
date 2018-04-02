
import java.awt.BorderLayout;
import java.awt.Font;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Random;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.UIManager;


public class MazeGenerator extends JFrame {
    
    private int rows = 20;
    private int cols = 20;
    private Cell [][] grid = new Cell[rows][cols];
    private JPanel mazePanel = new JPanel();
    private int row = 0;
    private int col = 0;
    private int endRow = rows - 1;
    private int endCol = cols - 1;

    public MazeGenerator(){
        initGUI();
        
        setTitle("Maze");
        setResizable(false);
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
        //setSize(200, 200);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        
        

        
        
        
    }
    
    private void initGUI(){
        //title 
        JPanel titlePanel = new JPanel(); 
        titlePanel.setBackground(Color.BLACK);
        add(titlePanel, BorderLayout.PAGE_START);
        Font titleFont = new Font("Fish&Chips", Font.BOLD, 32);
        JLabel titleLabel = new JLabel("Maze");
        titleLabel.setBackground(Color.BLACK);
        titleLabel.setOpaque(true);
        titleLabel.setForeground(Color.MAGENTA);
        titleLabel.setFont(titleFont);
        titleLabel.setHorizontalAlignment(JLabel.CENTER);
        titlePanel.add(titleLabel);
        
        //center panel
        JPanel centerPanel = new JPanel();
        centerPanel.setBackground(Color.MAGENTA);
        //OMG I'M MISSING CODE HERE AND I DON'T KNOW WHAT IT IS!!!
        add(centerPanel, BorderLayout.CENTER);
        
        //maze panel
        newMaze();
        centerPanel.add(mazePanel);
        
        //button panel
        JPanel buttonPanel = new JPanel();
        buttonPanel.setBackground(Color.BLACK);
        add(buttonPanel, BorderLayout.PAGE_END);
        JButton newMazeButton = new JButton("New Maze");
        JButton newMazeButton2 = new JButton("Quit Game");
        newMazeButton.setFocusable(false);
        newMazeButton2.setFocusable(false);
        buttonPanel.add(newMazeButton, BorderLayout.CENTER);
        buttonPanel.add(newMazeButton2, BorderLayout.CENTER);
        
        
        
        //listeners
        newMazeButton.addActionListener(new ActionListener(){ //only action listener is on the 'New Maze Button' in the bottom
           public void actionPerformed(ActionEvent e){
               newMaze();
           } 
        });
        
        newMazeButton2.addActionListener(new ActionListener(){ //only action listener is on the 'New Maze Button' in the bottom
        	@Override
            public void actionPerformed(ActionEvent e){
                //quit();
            	System.exit(0);
            	
            	
            } 
         });
        
        
        
        addKeyListener(new KeyAdapter() {
            public void keyReleased(KeyEvent e){ //waits for a key to be pressed; remembers which key
                char c = e.getKeyChar();
                System.out.println(c);
                int keyCode = e.getKeyCode();
                moveBall(keyCode);
            }       
        }); 
        
    }
    
    
    private void moveBall(int direction){
        switch (direction){
            case KeyEvent.VK_UP:
            	if (!grid[row][col].isWall(Cell.TOP)){//! is important, means 'not'
            		moveTo(row - 1, col, Cell.TOP, Cell.BOTTOM); //takes next row, next column, 
            	}
                
                break;
            case KeyEvent.VK_DOWN:
            	//move one cell down if there's no wall
            	if (!grid[row][col].isWall(Cell.BOTTOM)){//! is important, means 'not'
            		moveTo(row + 1, col, Cell.BOTTOM, Cell.TOP); //takes next row, next column, 
            	}
            	
                
                break;
            case KeyEvent.VK_RIGHT:
            	if (!grid[row][col].isWall(Cell.RIGHT)){//! is important, means 'not'
            		moveTo(row, col+1, Cell.RIGHT, Cell.LEFT); //takes next row, next column, 
            	}
                
                break;
            case KeyEvent.VK_LEFT:
            	if (!grid[row][col].isWall(Cell.LEFT)){//! is important, means 'not'
            		moveTo(row, col-1, Cell.LEFT, Cell.RIGHT); //takes next row, next column, 
            		
            	}
                
                break;
        }
        
        //puzzle solved?
        if (row == endRow && col == endCol){
            String message = "You won!";
            JOptionPane.showMessageDialog(null, message);
            newMaze();
        }
                
        
        
    }
    
    private void moveTo(int nextRow, int nextCol, //write this
            int firstDirection, int secondDirection){
    	grid[row][col].setCurrent(false);
        grid[row][col].addPath(firstDirection);//now in new cell
        
        row = nextRow;
        col = nextCol;
        
        grid[row][col].setCurrent(true);
        grid[row][col].addPath(secondDirection);
        
        
    }
    
    private void newMaze (){ //write this
        mazePanel.removeAll();
        mazePanel.setLayout(new GridLayout(rows, cols));
        grid = new Cell [rows][cols];
        
        for (int r = 0; r < rows; r++){
            for (int c = 0; c < cols; c++){
                grid[r][c] = new Cell (r, c);
                mazePanel.add(grid[r][c]);
            }
        }
        
        generateMaze(); //write this? //think about algorithm that generates the maze
        row = 0;
        col = 0;
        endRow = rows - 1;
        endCol = cols - 1;
        grid[row][col].setCurrent(true);
        grid[endRow][endCol].setEnd(true);
        mazePanel.revalidate();
    }
    
    private void generateMaze(){
        ArrayList<Cell> tryLaterCells = new ArrayList();
        int totalCells = rows * cols;
        int visitedCells = 1;
        
        //start at a random cell
        Random rand = new Random();
        int r = rand.nextInt(rows);
        int c = rand.nextInt(cols);
        
        //while not all cells have yet been visited
        while (visitedCells < totalCells){
            //find all neighbors with all walls intact
            ArrayList<Cell> neighbors = new ArrayList();
            
            if ((isAvailable(r -1, c))) {//top neighbor
            	neighbors.add(grid[r-1][c]); //add to the list of neighbors
            }
            
            if ((isAvailable(r + 1, c))) {
            	neighbors.add(grid[r+1][c]);
            }
            
            if ((isAvailable(r, c - 1))) {
            	neighbors.add(grid[r][c-1]);
            }
            
            if ((isAvailable(r, c +1))) {
            	neighbors.add(grid[r][c+1]);
            }
          
        
            //if one or more found
            if (neighbors.size() > 0) {
            
                //if more than 1 found add this cell to the list and try again
            	if (neighbors.size() > 1) {
            		tryLaterCells.add(grid[r][c]); //add to trylatercells
            	}
                
                //pick a neighbor and remove the wall
                int pick = rand.nextInt(neighbors.size());
                Cell neighbor = neighbors.get(pick);
                //remove wall
                grid[r][c].openTo(neighbor);
        
                //go to the neighbor and incrememnt the number visited
                
                //if none was found, go to one of the cells that was saved 
                //to try later
                r = neighbor.getRow();
                c = neighbor.getCol();
                visitedCells++;            	
            }
            else {
            	//if none were found, go to one of the cells that were saved
            	//to try later
            	Cell nextCell = tryLaterCells.remove(0);
            	r = nextCell.getRow();
            	c = nextCell.getCol();
            }
               
        
        }
    }
    
    private boolean isAvailable(int r, int c){
        boolean available = false;
        if (r < rows && r >= 0 && c < cols && c >= 0){//check if it's a valid spot
            if (grid[r][c].hasAllWalls()){//checks if spot has all of its walls
                available = true;
            }
        }
        return available;
    }
    
    public static void main(String[] args) {
        try {
            String className = UIManager.getCrossPlatformLookAndFeelClassName();
            UIManager.setLookAndFeel(className);
        } catch ( Exception e) {}
        
        EventQueue.invokeLater(new Runnable (){
            @Override
            public void run() {
                new MazeGenerator();
                //newMaze();
            }   
        });
    }
    
}
