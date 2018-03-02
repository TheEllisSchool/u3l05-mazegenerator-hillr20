
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
    
    private int rows = 5;
    private int cols = 5;
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
        titleLabel.setForeground(Color.WHITE);
        titleLabel.setFont(titleFont);
        titleLabel.setHorizontalAlignment(JLabel.CENTER);
        titlePanel.add(titleLabel);
        
        //center panel
        JPanel centerPanel = new JPanel();
        centerPanel.setBackground(Color.BLACK);
        add(centerPanel, BorderLayout.CENTER);
        
        //maze panel
        newMaze();
        centerPanel.add(mazePanel);
        
        //button panel
        JPanel buttonPanel = new JPanel();
        buttonPanel.setBackground(Color.BLACK);
        add(buttonPanel, BorderLayout.PAGE_END);
        JButton newMazeButton = new JButton("New Maze");
        newMazeButton.setFocusable(false);
        buttonPanel.add(newMazeButton, BorderLayout.CENTER);
        
        
        
        //listeners
        newMazeButton.addActionListener(new ActionListener(){
           public void actionPerformed(ActionEvent e){
               newMaze();
           } 
        });
        
        addKeyListener(new KeyAdapter() {
            public void keyReleased(KeyEvent e){
                //char c = e.getKeyChar();
                //System.out.println(c);
                int keyCode = e.getKeyCode();
                //moveBall(keyCode);
            }       
        });        
    }
    
    private void moveBall(int direction){
        switch (direction){
            case KeyEvent.VK_UP:
                
                
                break;
            case KeyEvent.VK_DOWN:
                
                
                break;
            case KeyEvent.VK_LEFT:
                
                
                break;
            case KeyEvent.VK_RIGHT:
                
                
                break;
        }
        
        //puzzle solved?
        if (row == endRow && col == endCol){
            String message = "You won!";
            JOptionPane.showMessageDialog(null, message);
        }
                
        
        
    }
    
    private void moveTo(int nextRow, int nextCol, 
            int firstDirection, int secondDirection){
        
    }
    
    private void newMaze (){
        mazePanel.removeAll();
        mazePanel.setLayout(new GridLayout(rows, cols));
        grid = new Cell [rows][cols];
        
        for (int r = 0; r < rows; r++){
            for (int c = 0; c < cols; c++){
                grid[r][c] = new Cell (r, c);
                mazePanel.add(grid[r][c]);
            }
        }
        
        //generateMaze();
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
          
        
            //if one or more found
            
                //if more than 1 found add this cell to the list and try again
                
                //pick a neighbor and remove the wall
                
        
                //go to the neighbor and incrememnt the number visited
                
                //if none was found, go to one of the cells that was saved 
                //to try later
               
        
        }
    }
    
    private boolean isAvailable(int r, int c){
        boolean available = false;
        if (r < rows && r >= 0 && c < cols && c >= 0){
            if (grid[r][c].hasAllWalls()){
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
            }   
        });
    }
    
}
