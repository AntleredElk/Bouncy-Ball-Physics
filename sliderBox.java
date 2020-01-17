//Name: Edward Latulipe-Kang
//Student ID: 260746475

import java.awt.Color;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;

import acm.gui.TableLayout;

public class sliderBox {
	
	JPanel myPanel;
	JLabel nameLabel;
	JLabel minLabel;
	JLabel maxLabel;
	JSlider mySlider;
	JLabel sReadout;
	Integer imin;
	Integer imax;
	
	public sliderBox(String name, double min, double dValue, double max) { // constructor class that defines sliderBox by the below mentioned parameters, and adds certain JObjects to the sliderBox object
		myPanel = new JPanel();
		nameLabel = new JLabel(name);
		minLabel = new JLabel(min+"");
		maxLabel = new JLabel(max+"");
		mySlider = new JSlider((int)min,(int)max,(int)dValue); // sliders take the listed inputs
		sReadout = new JLabel(dValue+""); // used to read out or display the current value of slider
		
		sReadout.setForeground(Color.blue); // set color of the readout value
		myPanel.setLayout(new TableLayout(1,5)); // creates a horizontal grid
		myPanel.add(nameLabel,"width=100"); // adds various objects to the panel myPanel
		myPanel.add(minLabel,"width=25");
		myPanel.add(mySlider,"width=100");
		myPanel.add(maxLabel,"width=100");
		myPanel.add(sReadout,"width=80");
		}
	
	public Integer getISlider() { // get the value of the slider
		return mySlider.getValue();
	}
	
	public void setISlider(int val) { //set the value of a variable to value of slider position 
		mySlider.setValue(val);
		sReadout.setText(val+"");

	}	

}
