//Name: Edward Latulipe-Kang
//Student ID: 260746475

import java.awt.Color;
import acm.graphics.GOval;

public class gBall extends Thread{

	// define the parameters that define gBall	
	
	double Position_x;
	double Position_initial;
	double Ball_size;
	Color Ball_color;
	double Ball_e_loss;
	double Ball_velocity;	
	GOval Ball;
	int check = 0; //checks to see if ball if falling or not
	
	double bottom = 590; // top surface of the floor defined in bSim

	public gBall(double position_x, double position_initial, double ball_size, Color ball_color, double ball_e_loss,
			double ball_velocity) { // creates a constructor with the given parameters for gBall to streamline the object creation process
		super();
		Position_x = position_x;
		Position_initial = position_initial;
		Ball_size = ball_size;
		Ball_color = ball_color;
		Ball_e_loss = ball_e_loss;
		Ball_velocity = ball_velocity;

		Ball = new GOval(Position_x, Position_initial, Ball_size, Ball_size);	//creates ball object with given parameters
		Ball.setFilled(true); // following two lines sets color
		Ball.setColor(Ball_color);
	}

	public void run(){ // physics used from Assignment 1, and adjusted to work with Assignment 2

		double G=9.8;
		double time_interval = 0.1;
		double time=0;
		double position_final=0;
		double position_tracker=0;
		boolean going_up=false;
		double speed=0;

		while(true) { // true replaced time_ind<time_limit, program is always true, it is stopped by an if statement at the end of this class utilizing the stop() function

			time+=time_interval;

			try { // pause for 50 milliseconds
				Thread.sleep(50);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

			if(going_up==false) {	

				position_final=Position_initial+G*time*time*10/2;

				Ball.setLocation(0,position_final);

				if(Ball.getY()+Ball_size>=bottom) {

					going_up=true;
					
					position_final=bottom-Ball_size;
					speed = Math.sqrt(2*G*10*(1-Ball_e_loss)*(Math.abs(position_final-Position_initial)));
					Position_initial=position_final;
					
					time=0;
				}
			}

			else {

				position_tracker=position_final;
				position_final=Position_initial-(speed*time)+(G*time*time*10/2);
				
				Ball.setLocation(0,position_final);

				if(position_tracker<=position_final){

					Position_initial=position_final;
					going_up=false;
					time=0;
				}			
			}

			if(position_final>bottom-Ball_size) position_final=bottom-Ball_size;

			Position_x+=Ball_velocity;
			Ball.setLocation(Position_x,position_final);
			
			if(going_up==false && position_final >= bottom-Ball_size) {
				check = 1; //sets ball falling (check) = 1
				break; // Stops movement for the ball when the final and initial position remain the same; stops thread run() process
			}
		}
	}
}
