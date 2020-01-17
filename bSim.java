//Name: Edward Latulipe-Kang
//Student ID: 260746475


import java.awt.Button;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;

import acm.util.RandomGenerator;
import acm.graphics.GLabel;
import acm.graphics.GRect;
import acm.program.GraphicsProgram;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import acm.gui.TableLayout;

@SuppressWarnings("serial")
public class bSim extends GraphicsProgram implements ChangeListener, ItemListener { // implements the needed listeners and changers for the drop menu and sliders 

	//defines the parameters and their range of values

	private static final int WIDTH = 1200; 
	private static final int HEIGHT = 600;
	private static final int OFFSET = 200;
	private static final int NUMBALLS = 25;
	private static final double MINSIZE = 1; 
	private static final double MAXSIZE = 25; 
	private static final double XMIN = 1; 
	private static final double XMAX = 200;
	private static final double YMIN = 1;
	private static final double YMAX = 100;
	private static final double EMIN = 0.0; 
	private static final double EMAX = 100.0; 
	private static final double VMIN = 0; 
	private static final double VMAX = 10; 

	gBall[] ball = new gBall[NUMBALLS]; // creates and array to contain gBall objects
	BinaryTree myBT = new BinaryTree(); // Create a new binary tree

	private RandomGenerator rgen = RandomGenerator.getInstance(); // creates a random method that can be accessed by dot operator

	public void moveSort(BinaryTree myBT) { // method to sort the Binary Tree

		double position_size = 0;

		ArrayList<gBall> sorted_balls = new ArrayList<gBall>(); //create ArrayList of type gBall
		sorted_balls = myBT.trace(sorted_balls); //sort the items in myBT and assigned the new list to sorted_balls

		for (int i =0; i< sorted_balls.size(); i++) { //place the balls in terms of their size
			sorted_balls.get(i).Ball.setLocation(position_size,590 -sorted_balls.get(i).Ball.getHeight());
			position_size += sorted_balls.get(i).Ball_size;

		}
	}

	public void isRunning(gBall ball[]) { // checks to see if ball has stopped or not
		for(int i=0; i<NUMBALLS; i++) { // for each ball
			int conditional = 0;
			while(conditional == 0) { //check if ball has stopped
				if (ball[i].check == 1) conditional=1;	
				System.out.println(); //Synchronizes the output stream of our object parameter

			}
		}
	}

	sliderBox numballs; // The following sliderBoxes creates sliderBox objects/ instances for the following:
	sliderBox minsize;
	sliderBox maxsize;
	sliderBox xmin;
	sliderBox xmax;
	sliderBox ymin;
	sliderBox ymax;
	sliderBox lossmin;
	sliderBox lossmax;
	sliderBox xvelmin;
	sliderBox xvelmax;

	int default_numballs=NUMBALLS/2; //The following int is created and initialized to defined the middle(default) point of each slider:
	int default_minsize=(int) (((MAXSIZE-MINSIZE)/2)+MINSIZE);
	int default_maxsize=(int) (((MAXSIZE-MINSIZE)/2)+MINSIZE);
	int default_xmin=(int) (((XMAX-XMIN)/2)+XMIN);
	int default_xmax=(int) (((XMAX-XMIN)/2)+XMIN);
	int default_ymin=(int) (((YMAX-YMIN)/2)+YMIN);
	int default_ymax=(int) (((YMAX-YMIN)/2)+YMIN);
	int default_lossmin=(int) (((EMAX-EMIN)/2)+EMIN);
	int default_lossmax=(int) (((EMAX-EMIN)/2)+EMIN);
	int default_xvelmin=(int) (((VMAX-VMIN)/2)+VMIN);
	int default_xvelmax=(int) (((VMAX-VMIN)/2)+VMIN);

	JComboBox<String> drop_menu; // creates the drop down menu object
	void setChoosers() { // creates the various choosers to select from in the created drop down menu
		drop_menu = new JComboBox<String>();
		drop_menu.addItem("bSim");
		drop_menu.addItem("Run");
		drop_menu.addItem("Clear");
		drop_menu.addItem("Stop");
		drop_menu.addItem("Sort");
		drop_menu.addItem("Quit");
		add(drop_menu,NORTH); // adds the drop down menu to the UI
		addJComboListeners();// allows for detection of what is clicked 

	}

	void addJComboListeners() {
		drop_menu.addItemListener((ItemListener)this); //adds a selection listener to detect what is pressed
	}

	void setSliders() {// defines and creates all the J objects
		JPanel container_panel = new JPanel();
		container_panel.setLayout(new TableLayout(25,1));
		
		JLabel instructions_1 = new JLabel(); //Creates JLabel objects to instruct users
		JLabel instructions_2 = new JLabel("Instructions: ");
		JLabel instructions_3 = new JLabel("''Clear'':    Clicking this will clear all items on the screen");
		JLabel instructions_4 = new JLabel("''bSim'':    Clicking this will add the objects with the new parameters without running them");
		JLabel instructions_5 = new JLabel("''Run'':    Clicking this will run the objects that are currently displayed on the screen");
		JLabel instructions_6 = new JLabel("''Stop'':    Clicking this will permanently stop or pause the balls in their respective positions");
		JLabel instructions_7 = new JLabel("''Sort'':    Clicking this will sort the balls in order of smallest to largest; this can only be used once the balls have stopped");
		JLabel instructions_8 = new JLabel("Use the items in the order as shown above for optimal user experience. ''Quit'' can be used at any time.");

		JLabel gen_sim = new JLabel("General Simulation Parameters");
		container_panel.add(gen_sim);

		numballs = new sliderBox("NUMBALLS:", 1, default_numballs, NUMBALLS);
		minsize = new sliderBox("MINSIZE:", MINSIZE, default_minsize, MAXSIZE);
		maxsize = new sliderBox("MAXSIZE:", MINSIZE, default_maxsize, MAXSIZE);
		xmin = new sliderBox("X MIN:", XMIN, default_xmin, XMAX);
		xmax = new sliderBox("X MAX:", XMIN, default_xmax, XMAX);
		ymin = new sliderBox("Y MIN:", YMIN, default_ymin, YMAX);
		ymax = new sliderBox("Y MAX:", YMIN, default_ymax, YMAX);
		lossmin = new sliderBox("LOSS MIN:", EMIN, default_lossmin, EMAX);
		lossmax = new sliderBox("LOSS MAX:", EMIN, default_lossmax, EMAX);
		xvelmin = new sliderBox("X VEL MIN:", VMIN, default_xvelmin, VMAX);
		xvelmax = new sliderBox("X VEL MAX:", VMIN, default_xvelmax, VMAX);

		container_panel.add(numballs.myPanel); // The following adds slider panels and other miscellaneous objects to the grid style TableLayout
		container_panel.add(minsize.myPanel);
		container_panel.add(maxsize.myPanel);
		container_panel.add(xmin.myPanel);
		container_panel.add(xmax.myPanel);
		container_panel.add(ymin.myPanel);
		container_panel.add(ymax.myPanel);
		container_panel.add(lossmin.myPanel);
		container_panel.add(lossmax.myPanel);
		container_panel.add(xvelmin.myPanel);
		container_panel.add(xvelmax.myPanel);
		container_panel.add(instructions_1);
		container_panel.add(instructions_2);
		container_panel.add(instructions_3);
		container_panel.add(instructions_4);
		container_panel.add(instructions_5);
		container_panel.add(instructions_6);
		container_panel.add(instructions_7);
		container_panel.add(instructions_8);

		numballs.mySlider.addChangeListener((ChangeListener) this); // tracks the movement of the mouse or slider
		minsize.mySlider.addChangeListener((ChangeListener) this);
		maxsize.mySlider.addChangeListener((ChangeListener) this);
		xmin.mySlider.addChangeListener((ChangeListener) this);
		xmax.mySlider.addChangeListener((ChangeListener) this);
		ymin.mySlider.addChangeListener((ChangeListener) this);
		ymax.mySlider.addChangeListener((ChangeListener) this);
		lossmin.mySlider.addChangeListener((ChangeListener) this);
		lossmax.mySlider.addChangeListener((ChangeListener) this);
		xvelmin.mySlider.addChangeListener((ChangeListener) this);
		xvelmax.mySlider.addChangeListener((ChangeListener) this);

		add(container_panel); //adds the container_panel to the UI
	}

	public void stateChanged(ChangeEvent e) {   // Allows the tracking of slider or mouse
		JSlider source = (JSlider)e.getSource(); //

		if (source==numballs.mySlider) { //if this slider if selected, then 
			default_numballs=numballs.getISlider(); //get slider value at a certain point
			numballs.setISlider(default_numballs); //set slider display at that certain point (repeat for all those below) 
		}
		else if( source== minsize.mySlider){
			default_minsize = minsize.getISlider();
			minsize.setISlider(default_minsize);
		}
		else if( source== maxsize.mySlider){
			default_maxsize = maxsize.getISlider();
			maxsize.setISlider(default_maxsize);
		}
		else if( source== xmin.mySlider){
			default_xmin = xmin.getISlider();
			xmin.setISlider(default_xmin);
		}
		else if( source== xmax.mySlider){
			default_xmax = xmax.getISlider();
			xmax.setISlider(default_xmax);
		}
		else if( source== ymin.mySlider){
			default_ymin = ymin.getISlider();
			ymin.setISlider(default_ymin);
		}
		else if( source== ymax.mySlider){
			default_ymax = ymax.getISlider();
			ymax.setISlider(default_ymax);
		}
		else if( source== lossmin.mySlider){
			default_lossmin = lossmin.getISlider();
			lossmin.setISlider(default_lossmin);
		}
		else if( source== lossmax.mySlider){
			default_lossmax = lossmax.getISlider();
			lossmax.setISlider(default_lossmax);
		}
		else if( source== xvelmin.mySlider){
			default_xvelmin = xvelmin.getISlider();
			xvelmin.setISlider(default_xvelmin);
		}
		else if( source== xvelmax.mySlider){
			default_xvelmax = xvelmax.getISlider();
			xvelmax.setISlider(default_xvelmax);
		}
	}


	public void run() {

		setChoosers(); // adds the drop down menu to the UI
		setSliders();  // adds the sliders to the UI
		
		int i;


		this.resize(WIDTH,HEIGHT+OFFSET); // creates canvas

		GRect floor=new GRect(0,HEIGHT-10,3600,10); //create floor above the offset
		floor.setFilled(true);
		floor.setColor(Color.black);
		add(floor);


		for (i=0; i<NUMBALLS; i++) { // sets random values for the defined parameters of the object	
			double Position_x = rgen.nextDouble(XMIN, XMAX);
			double Position_initial = rgen.nextDouble(YMIN, YMAX);
			double Ball_size = rgen.nextDouble(MINSIZE, MAXSIZE);
			Color Ball_color = rgen.nextColor();
			double Ball_e_loss = rgen.nextDouble((double)EMIN/100, (double) EMAX/100);
			double Ball_velocity = rgen.nextDouble(VMIN, VMAX);

			ball[i] = new gBall(Position_x, Position_initial, Ball_size, Ball_color, Ball_e_loss, Ball_velocity); // creates ith gBall object and stores it in ith position of array

			add(ball[i].Ball); // adds ith ball to graphics
			myBT.insert(ball[i]);// insert balls into BTree
		}

		isRunning(ball); // checks to see if balls have stopped or not

		// once all balls have stopped,prompt the user with the following

		GLabel prompt = new GLabel("Click mouse to sort:",920,HEIGHT-30); //add label "Click mouse to sort: "
		prompt.setFont("SansSerif-24");
		prompt.setColor(Color.red);
		add(prompt);

		waitForClick(); //Wait for user click before Sorting Balls

		moveSort(myBT);// method to sort the Binary Tree

		prompt.setLabel("All sorted!"); //Change label to "All sorted!"
		prompt.setLocation(1000,HEIGHT-30);
	}

	@Override
	public void itemStateChanged(ItemEvent e) { // following code detects changes in the drop down menu
		// TODO Auto-generated method stub
		if(e.getStateChange() == ItemEvent.SELECTED) {
			JComboBox source = (JComboBox)e.getSource();
			if (source==drop_menu) {
				if (drop_menu.getSelectedIndex()==0) { // if bSim is selected

					for (int i=0; i<default_numballs; i++) { // sets random values for the defined parameters of the object	
						double Position_x = rgen.nextDouble(default_xmin, default_xmax);
						double Position_initial = rgen.nextDouble(default_ymin, default_ymax);
						double Ball_size = rgen.nextDouble(default_minsize, default_maxsize);
						Color Ball_color = rgen.nextColor();
						double Ball_e_loss = rgen.nextDouble(default_lossmin, default_lossmax);
						double Ball_velocity = rgen.nextDouble(default_xvelmin, default_xvelmax);

						ball[i] = new gBall(Position_x, Position_initial, Ball_size, Ball_color, (double)Ball_e_loss/100, Ball_velocity); // creates ith gBall object and stores it in ith position of array

						add(ball[i].Ball); // adds ith ball to graphics
						myBT.insert(ball[i]);// insert balls into BTree
						
					}
				}
				else if(drop_menu.getSelectedIndex()==1){ // if run is selected
					for (int i=0; i<NUMBALLS; i++) ball[i].start(); //starts the thread process for any ball[i[
				}
				else if(drop_menu.getSelectedIndex()==2){ // if clear is selected
					removeAll();// removes all objects on canvas
					myBT.remove(myBT); //removes all nodes in myBT
					GRect floor=new GRect(0,HEIGHT-10,3600,10); //create floor above the offset after removing all objects 
					floor.setFilled(true);
					floor.setColor(Color.black);
					add(floor);
				}
				else if(drop_menu.getSelectedIndex()==3){ // if stop is selected
					for (int i=0; i<NUMBALLS; i++) { //Stops the thread process for any ball[i]
						ball[i].stop(); 
					}
				}
				else if(drop_menu.getSelectedIndex()==4){ // if sort is selected
					moveSort(myBT); //This piece of codes sorts the balls 
				}
				else if(drop_menu.getSelectedIndex()==5){ // if quit is selected
					System.exit(0);	//exists the program

				}

			}
		}
	}

}
