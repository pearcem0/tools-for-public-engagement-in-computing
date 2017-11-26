import java.awt.*;
import javax.swing.*; // for jbutton, jtextfeild etc.
import javax.*;
import java.util.*;

public class Demonstration4 extends java.applet.Applet 
{  
    
   /*
    static String imageFileName = "map.jpg";
    private URL imageSource;
    private MapImage MapImage;
  */

   // Image img = ImageIO.read(new File("background.jpg"));
    

    GraphCanvas graphcanvas = new GraphCanvas(this);
    Buttons Buttons = new Buttons(this); 
    Title title = new Title(this);   
    InfoText infoText = new InfoText();
    
    

    public void init() 
    {
    
	setLayout(new BorderLayout(10, 10));
       
    add("North", title);
	add("Center", graphcanvas);     
	add("East", Buttons);
    add("South", infoText);
     
   

    }

    public static void main(String arg[])
    {   
       
        Demonstration4 graph = new Demonstration4();
        
	    JFrame frame = new JFrame("Tools for Public Engagement in Computing - Michael Pearce");
        //Frame frame = new Frame("Tools for Public Engagement in Computing - Michael Pearce");
        
       

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        frame.setBackground(Color.WHITE);
        graph.init();
        frame.add("Center", graph);
     
        frame.show();
        // go full screen
        frame.setExtendedState(Frame.MAXIMIZED_BOTH);  
        
                            
        
    }

}
class Title extends Panel
{
    JTextField titleField = new JTextField("Shortest Path Problem - Demonstration 4");
    
    Title(Demonstration4 myparent)
    {
    
      add(titleField);
      titleField.setEditable(false);
      Font bigTitleFont = new Font("Serif", Font.PLAIN, 30);
      titleField.setFont(bigTitleFont); 
    
    }
}

class Buttons extends Panel 
{
	boolean messageShown=false;
	 
    
    // create buttons at the top of the screen
    
    Button runButton = new Button("Run"), setButton = new Button ("Set"),exitButton = new Button("Exit");
    Button stepButton = new Button("Step"), nextExampleButton = new Button("Next Example");

    //JButton stepButton = new JButton("Step"); // use jbuttons for tooltips!
   
    Demonstration4 parent;   

    Buttons(Demonstration4 myparent) 
    { /*
      runButton.setToolTipText("Click this button to run the algorithm on the map to see how it finds the shortest path.");
      setButton.setToolTipText("Click this button to show the map on the screen.");
      exitButton.setToolTipText("Click this button to exit the application.");
      stepButton.setToolTipText("Click this button to take one step of the algorithm on the map to see how it finds the shortest path.");
     */
   
	parent = myparent;
	setLayout(new GridLayout(6, 1, 0, 10));
	add(setButton);
          setButton.setBackground(Color.blue);
          setButton.setForeground(Color.white);
	add(runButton); 
          runButton.setBackground(Color.blue);
          runButton.setForeground(Color.white);
          runButton.setEnabled(false);
	add(stepButton);
          stepButton.setBackground(Color.blue);
          stepButton.setForeground(Color.white);
          stepButton.setEnabled(false);
        add(exitButton);
          exitButton.setBackground(Color.blue);
          exitButton.setForeground(Color.white);
   /*add(nextExampleButton);
          nextExampleButton.setBackground(Color.blue);
          nextExampleButton.setForeground(Color.white);
          nextExampleButton.setEnabled(false);*/
   
    }
    
    public void nextExample() // not currently implemented, nowhere to put the method call outside of a loop!
    {
      /*
        Object[] options = {"Next Example", "Exit"};
        JFrame frame = new JFrame("Test");
        int n = JOptionPane.showOptionDialog(frame,
       "The shortest path is via : A B C E\nWould you like to go to the next example?",
       "Finished!",
       JOptionPane.YES_NO_OPTION,
       JOptionPane.PLAIN_MESSAGE,
       null,
       options,
       options[1]); */
       JFrame NextExampleFrame = new JFrame("Next Example");
       int answer = JOptionPane.showConfirmDialog(NextExampleFrame, "Next Example?");
       if (answer == JOptionPane.YES_OPTION) 
       {
         //frame.dispose();
        // new Demo2();
       } 
       else if (answer == JOptionPane.NO_OPTION) 
       {
          // nothing
       }
    }

    public boolean action(Event evt, Object arg) 
    {
	if (evt.target instanceof Button) 
        {
	  if (((String)arg).equals("Run")) 
          {
	     parent.graphcanvas.runapp();
        // parent.infoText.append("Running\n");
         runButton.setEnabled(false);
         setButton.setLabel("Reset");
         stepButton.setEnabled(false);
         parent.infoText.append("Update the distances (In the circles)\n");
         parent.infoText.append("Go to the closest neighbouring node(city), "+
         "and do the same!\n");
         parent.infoText.append("(When a node(city) has been visited " +
         "and the distance is final and minimal, it will turn yellow)\n\n");
	  }
	  if (((String)arg).equals("Set")) 
          {
            setButton.setLabel("Reset");
	        stepButton.setLabel("Step");   
            runButton.setEnabled(true);
            stepButton.setEnabled(true);
            parent.graphcanvas.setgraph();
            parent.infoText.append("Look at the neighbouring nodes "+
            "(next city on the path)\n" +
            "Calculate their distance by adding " +
          "the current distance to the distance it \ntakes to get there."+
          "\n\n");
	  } 
	  if (((String)arg).equals("Exit")) 
          { 
	     System.exit(0);
	  }
          if (((String)arg).equals("Step"))
          {
          //  parent.infoText.append("Stepping through\n");
            runButton.setEnabled(false);
            stepButton.setLabel("Next step");
            //stepButton.setToolTipText("Click this button to take the next step of ..."); // only works for jbuttons!
            setButton.setLabel("Reset");
     	    parent.graphcanvas.step();
     	    parent.infoText.append("Update the distances\n");
	  } 
          if (((String)arg).equals("Next step"))
          { 
			   
		    if (!messageShown)
		    {
			  parent.infoText.append("Go to the closest neighbouring " +
			  "node(city), and do the same!\n");
			   parent.infoText.append("(When a node(city) has been visited " +
               "and the distance is final and minimal, it will turn " +
               "yellow)\n\n");
	           parent.graphcanvas.nextstep();
	           messageShown = true;
	        }
	        else{parent.graphcanvas.nextstep();}
	        
          }
      if (((String)arg).equals("Reset")) 
          { 
	        stepButton.setLabel("Step");   
            runButton.setEnabled(true);
            setButton.setEnabled(true);
            exitButton.setEnabled(true);
            stepButton.setEnabled(true);
            parent.infoText.setText("");
            InfoText infoText = new InfoText(); // because you cant
            // access dynamic content from static context
            parent.infoText.append(infoText.initialText);
            parent.graphcanvas.setgraph();
            parent.infoText.append("Look at the neighbouring nodes "+
            "(next city on the path)\n" +
            "Calculate their distance by adding " +
          "the current distance to the distance it \ntakes to get there."+
          "\n\n");
	      }
      if (((String)arg).equals("Next Example"))
      {
        nextExample();
      }
	}  
                 
	return true; 
    }
} // end of buttons

class InfoText extends TextArea 
{
	
	
    final String initialText = new String("Goal: Here you will see how "+
    "Sat navs work in action. They use complex 'Algorithms' to find " +
    "the shortest or most optimal path to the \ndestination. " +
    "Click the set button to show the map, and the run button to see " +
    "the algorithm in action. You can also use the step button \nto " +
    "walk through the process one step at a time.\n\n");
    
    
         

    final String fullInfo = initialText; // + any other Strings I declare later
   
    InfoText() 
    {
	  super(10, 2);
	  setText(fullInfo);
	  Font bigFrameFont = new Font("Serif", Font.PLAIN, 20);
      setFont(bigFrameFont);  
      setEditable(false);
      ;
      
	  
    }
}

class GraphCanvas extends Canvas implements Runnable 
{
    
    // graph canvas
    final int Total_Nodes = 20,MAX = Total_Nodes+1;
    // added in limit of nodes for loops sake
    final int NODEROOT = 13,NODESIZE = 26,DIJKSTRA = 1; // djistra = 'true'
 
    // graph info
    int weight[][] = new int[MAX][MAX];     // represent the weight of an edge
    Point node[] = new Point[MAX];          // node of type Point
    Point edge[][] = new Point[MAX][MAX];  // current position of arrowhead
    Point startp[][] = new Point[MAX][MAX]; // start and
    Point endpoint[][] = new Point[MAX][MAX];   // endpoint of edge
    float dir_x[][] = new float[MAX][MAX],dir_y[][] = new float[MAX][MAX];  // direction of edge
    Point arrow[][] = new Point[MAX][MAX];  // current position of line
    // graph information while running Algorithm
    int dist[] = new int[MAX], finaldist[] = new int[MAX], numchanged =0,neighbours=0, step=0;   
    boolean algedge[][] = new boolean[MAX][MAX], changed[] = new boolean[MAX];   // indicates distance change during Algorithm   
    Color colournode[] = new Color[MAX];
    // information used by the Algorithm to find the next 
    // node with minimum distance
    int mindist, minnode, minstart, minend;
    int numnodes=0;      // number of nodes
    int emptyspots=0;    // empty spots in array node[] (due to node deletion)
    int startgraph=0;    // start of graph
    int node1, node2;    // numbers of nodes involved in current action

        int hitnode;         // for when someone clicks the node
        boolean finished=false;

    Point thispoint=new Point(0,0),oldpoint=new Point(0, 0); 

    // current action
    boolean newarrow = false, movearrow = false, movestart = false, performalg = false;
    
    boolean clicked = false, paintNodeYellow = false, paintLineYellow = false;
    boolean correctPath=false; // did they click the correct path?
    
    // fonts
     
    Font bigFont = new Font("Serif", Font.PLAIN, 20);
    FontMetrics fmetrics = getFontMetrics(bigFont);
    int h = (int)fmetrics.getHeight()/3;

    // for double buffering
    private Image offScreenImage;
    private Graphics offScreenGraphics;
    private Dimension offScreenSize;

    // for run option
    Thread algrthm;

    int Algorithm;
    Demonstration4 parent;
    
    GraphCanvas(Demonstration4 myparent) 
    {
	  parent = myparent;
	  
      init();
	  Algorithm=DIJKSTRA;
      
      
    }

    public void start(){}

    public void init() 
    {
      
    
	for (int i=0;i<Total_Nodes;i++)
        {
	  colournode[i]=Color.lightGray;
	  for (int j=0; j<Total_Nodes;j++)
	      {
                 algedge[i][j]=false;
              }
	}

	colournode[startgraph]=Color.green;
	performalg = false;
    }
    
    public void clear() 
    {
        // removes graph from screen - will need this too set the graph
	startgraph=0;
	numnodes=0;
	emptyspots=0;
	init();
	for(int i=0; i<Total_Nodes; i++) 
        {
	  node[i]=new Point(0, 0);
	  for (int j=0; j<Total_Nodes;j++)
	      weight[i][j]=0;
	}
	
	repaint();
    }
    
    public void runapp() 
    {
    
	initalg();
	performalg = true;
	algrthm = new Thread(this);
	algrthm.start();
     
    }
   

    public void step() 
    {
        initalg();
	performalg = true;
	nextstep();
    }

    public void initalg() 
    {
		
        init();
	for(int i=0; i<Total_Nodes; i++) 
        {
	  dist[i]=-1;
	  finaldist[i]=-1;
	  for (int j=0; j<Total_Nodes;j++)
              algedge[i][j]=false;
	}
        dist[startgraph]=0;
	finaldist[startgraph]=0;
	step=0;

    }

    public void nextstep() 
    {
        // calculates a step in the Algorithm (finds a shortest 
        // path to a next node).
        
         
          
        
        finaldist[minend]=mindist;
	    algedge[minstart][minend]=true;
	    colournode[minend]=Color.orange;
        // build more information to display on info text panel
	    step++;
	    repaint();
        try { algrthm.sleep(1000); } 
	        catch (InterruptedException e) {}
	     
    }

    public void run() 
    {
      
     
      for(int i=0; i<(numnodes-emptyspots); i++)
      {
	  nextstep();
	  try { algrthm.sleep(2000); } // 4000
	  catch (InterruptedException e) {}
      }
      algrthm = null;
    }

    public void setgraph() 
    {
    
    // draws a graph on the screen
	int w, h;
	clear();
	init();
	numnodes=9;
	emptyspots=0;
	for(int i=0; i<Total_Nodes; i++) 
        {
	  node[i]=new Point(0, 0);
	  for (int j=0; j<Total_Nodes;j++)
	      weight[i][j]=0;
	}
 w=this.size().width/8;
	h=this.size().height/8;     
    node[0]=new Point(w, 7*h);    
    node[1]=new Point(3*w, h);   
	node[2]=new Point(5*w, h);   
	node[3]=new Point(3*w, 4*h); 
	node[4]=new Point(7*w, 7*h);
    //  -- extra nodes - see how many it can handle
    node[5]=new Point(4*w, h);
    node[6]=new Point(6*w,h);
    node[7]=new Point(2*w,7*h);
    node[8]=new Point(3*w, 7*h);
   
       
        weight[0][1]=5;
        weight[1][3]=4;
        weight[2][6]=14;
        weight[0][7]=4;
        weight[7][8]=3;
        weight[7][3]=2;
        // -- extra nodes
        weight[3][8]=27;
        weight[8][4]=91;
        weight[2][4]=44;
        weight[5][2]=19;
        weight[6][4]=8;
        weight[3][5]=10;
        weight[8][2]=12;
	
	
	
	  

	for (int i=0;i<numnodes;i++)
	   for (int j=0;j<numnodes;j++)
	      if (weight[i][j]>0)
              {
	         arrowupdate(i, j, weight[i][j]);               
              }
	repaint();
    }

    public void arrowupdate(int p1, int p2, int w) 
    {
        // make a new edge from node p1 to p2 with weight w, or change
        // the weight of the existing edge to w, calculate the resulting 
        // position of the arrowhead
	int dx, dy;
	float l;
	weight[p1][p2]=w;

	// direction line between p1 and p2
	dx = node[p2].x-node[p1].x;
	dy = node[p2].y-node[p1].y;

	// distance between p1 and p2
	l = (float)( Math.sqrt((float)(dx*dx + dy*dy)));
	dir_x[p1][p2]=dx/l;
	dir_y[p1][p2]=dy/l;

	// calculate the start and endpoints of the edge,
	// adjust startpoints if there also is an edge from p2 to p1
	if (weight[p2][p1]>0) 
        {
	   startp[p1][p2] = new Point((int)(node[p1].x-5*dir_y[p1][p2]), 
				      (int)(node[p1].y+5*dir_x[p1][p2]));
	   endpoint[p1][p2] = new Point((int)(node[p2].x-5*dir_y[p1][p2]), 
				    (int)(node[p2].y+5*dir_x[p1][p2]));
	}
	else 
        {
	   startp[p1][p2] = new Point(node[p1].x, node[p1].y);
	   endpoint[p1][p2] = new Point(node[p2].x, node[p2].y);
	}
         
	// range for arrowhead is not all the way to the start/endpoints
	int diff_x = (int)(Math.abs(20*dir_x[p1][p2])) , diff_y = (int)(Math.abs(20*dir_y[p1][p2]));
	
	// calculate new x-position arrowhead
	if (startp[p1][p2].x>endpoint[p1][p2].x) 
        {
	   edge[p1][p2] = new Point(endpoint[p1][p2].x + diff_x +
		(Math.abs(endpoint[p1][p2].x-startp[p1][p2].x) - 2*diff_x )*(100-w)/100 , 0);
	}
	else 
        {
	   edge[p1][p2] = new Point(startp[p1][p2].x + diff_x +
		(Math.abs(endpoint[p1][p2].x-startp[p1][p2].x) - 2*diff_x )*w/100, 0);
	}

	// calculate new y-position arrowhead
	if (startp[p1][p2].y>endpoint[p1][p2].y) 
        {
	   edge[p1][p2].y=endpoint[p1][p2].y + diff_y +
		(Math.abs(endpoint[p1][p2].y-startp[p1][p2].y) - 2*diff_y )*(100-w)/100;
	}
	else 
        {
	   edge[p1][p2].y=startp[p1][p2].y + diff_y +
		(Math.abs(endpoint[p1][p2].y-startp[p1][p2].y) - 2*diff_y )*w/100;
	}
    
    }
   
    public final synchronized void update(Graphics graph) 
    {
        // prepare new image offscreen
    
	Dimension d=size();
	if ((offScreenImage == null) || (d.width != offScreenSize.width) || (d.height != offScreenSize.height))  
        {
	    offScreenImage = createImage(d.width, d.height);
	    offScreenSize = d;
	    offScreenGraphics = offScreenImage.getGraphics();
	}

	offScreenGraphics.setColor(Color.white);
	offScreenGraphics.fillRect(0, 0, d.width, d.height);
	paint(offScreenGraphics);
	graph.drawImage(offScreenImage, 0, 0, null);
    }

    public void insertArrow(Graphics graph, int i, int j) 
    {
    // draw edge between node i and node j
	
	// if edge already chosen by Algorithm change colour
	if (algedge[i][j]) graph.setColor(Color.orange);
	// draw edge
	graph.drawLine(startp[i][j].x, startp[i][j].y, endpoint[i][j].x, endpoint[i][j].y);
	
	// write weight of edge at an appropriate position
	
	String str = new String("" + weight[i][j]);
	graph.setColor(Color.black);
	graph.drawString( str, edge[i][j].x, edge[i][j].y);
    }
	
    public void DijkstraAlgorithm(Graphics graph, int i, int j) 
    {
        // check edge between node i and node j is amongst the arrows to
        // choose from during this step of the Algorithm
        // check if node j has the next minimal distance to the startnode
	if ( (finaldist[i]!=-1) && (finaldist[j]==-1) ) 
        {
	  graph.setColor(Color.red);
	  if ( (dist[j]==-1) || (dist[j]>=(dist[i]+weight[i][j])) ) 
          {
             if ( (dist[i]+weight[i][j])<dist[j] ) 
             {
                 changed[j]=true;
                 // add something here for book keeping
                 parent.infoText.append("\nCity " + j + " was updated! \n");
                 //System.out.println("Debug: distance changed");
                 numchanged++;
             }
	     dist[j] = dist[i]+weight[i][j];
	     colournode[j]=Color.red;             
	     if ( (mindist==0) || (dist[j]<mindist) ) 
             {
	        mindist=dist[j];
	        minstart=i;
	        minend=j;
	     }
	  }
	}
 
	else graph.setColor(Color.lightGray);
    } // end of DijkstraAlgorithm

    public void endstepDijkstra(Graphics graph) 
    {
        // displays current and final distances of nodes, sets the final distance
        // for the node that had minimal distance in this step
        for (int i=0; i<numnodes; i++)
	  if ( (node[i].x>0) && (dist[i]!=-1) ) 
          {
	     String str = new String(""+dist[i]);
	     graph.drawString(str, node[i].x - (int)fmetrics.stringWidth(str)/2 -1, node[i].y + h);
	     
	     if (finaldist[i]==-1) 
         {
	        neighbours++;  
	        
	     } 
	  }
        
        
	if (neighbours>1) 
        { 
	  //check if there are other paths to look at 
	  int newpaths=0;
	  for (int i=0; i<numnodes; i++) 
	     if ( (node[i].x>0) && (weight[i][minend]>0) && ( finaldist[i] == -1 ) )
	        {
                  newpaths++; 
                }
	  
	}
	else 
        {
           boolean morenodes=false;
           for (int i=0; i<numnodes; i++) 
	     if ( ( node[i].x>0 ) && ( finaldist[i] == -1 ) && ( weight[i][minend]>0 ) )
	        {
                  morenodes=true; 
                }
 
           boolean bridge=false;
           for (int i=0; i<numnodes; i++) 
	     if ( ( node[i].x>0 ) && ( finaldist[i] == -1 ) && ( weight[minend][i]>0 ) )
	        {
                  bridge=true; 
                }
         
        }

    } //end of endstepDujisktra

    public void endstepalg(Graphics graph) 
    { 
      
      if (Algorithm==DIJKSTRA)
	    endstepDijkstra(graph);
	if ( ( performalg ) && (mindist==0) ) 
        {
	   if (algrthm != null) algrthm.stop();
	   int nreachable = 0;
	   for (int i=0; i<numnodes; i++)
	      if (finaldist[i] > 0)
	      {
		     nreachable++;
            	   
             parent.Buttons.stepButton.setEnabled(false);   
          }
          
           parent.infoText.append("Finished. \n" + 
            	    "The shortest path is via : A H I C G E\n"); 
          // parent.Buttons.nextExampleButton.setEnabled(true);
           return;
           
            	    // a b c e hardcoded for now, for demo sake, 
            	    // will add real path when book keeping is implemented
          //JOptionPane.showMessageDialog(null, "Shortest distance to E is "
          // + "The shortest path is ");	
         
	  } 
           //finished=true;
           //nextExample(finished);
     
    } // end of endstepalg
    
   
    public void paint(Graphics graph) 
    {
      // graph.drawImage(img, 0, 0, null);

        mindist=0;
	minnode=Total_Nodes;
	minstart=Total_Nodes;
	minend=Total_Nodes;
        for(int i=0; i<Total_Nodes; i++) 
           {
             changed[i]=false;
           }
        numchanged=0;
        neighbours=0;
	graph.setFont(bigFont);
	graph.setColor(Color.black);
        
	// draw all arrows
	for (int i=0; i<numnodes; i++)
	  for (int j=0; j<numnodes; j++)
	     if (weight [i][j]>0) 
             {
	        // if Algorithm is running then perform next step for this edge
	        if (performalg)
                {
	          DijkstraAlgorithm(graph, i, j);
                }
	        insertArrow(graph, i, j);
	     }

	// if arrowhead has been dragged to 0, draw it anyway, so the user
	// will have the option to make it positive again
	if (movearrow && weight[node1][node2]==0) 
        {
	  insertArrow(graph, node1, node2);
	  graph.drawLine(startp[node1][node2].x, startp[node1][node2].y, 
		     endpoint[node1][node2].x, endpoint[node1][node2].y);
	}

	// draw the nodes
	for (int i=0; i<numnodes; i++)
	  if (node[i].x>0) 
          {
	     graph.setColor(colournode[i]);
	     graph.fillOval(node[i].x-NODEROOT, node[i].y-NODEROOT, NODESIZE, NODESIZE);
	  }

	
	graph.setColor(Color.blue);
	

	graph.setColor(Color.black);
	// finish this step of the Algorithm
	if (performalg)
          { 
            endstepalg(graph);
          }

	// draw black circles around nodes, write their names to the screen
	graph.setFont(bigFont);
	for (int i=0; i<numnodes; i++)
	  if (node[i].x>0) 
          {
	     graph.setColor(Color.black);
	     graph.drawOval(node[i].x-NODEROOT, node[i].y-NODEROOT, NODESIZE, NODESIZE);
	     graph.setColor(Color.black);
	     graph.drawString(intToString(i), node[i].x-14, node[i].y-14);
	  }
      //parent.graphcanvas.setgraph();
    } // end of paint method

    public String intToString(int i) 
    {
      char c=(char)((int)'a'+i);
      return ""+c;
    }
 }
