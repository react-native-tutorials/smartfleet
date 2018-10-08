/**
 * 
 */
package com.smartlife.smartfleet.gui.geo;
import static com.smartlife.smartfleet.gui.geo.ShowGpsFrame.CHOOSE_COLOR;
import static com.smartlife.smartfleet.gui.geo.ShowGpsFrame.DRAW_LOCATION;
import static com.smartlife.smartfleet.gui.geo.ShowGpsFrame.DRAW_LINE;
import static com.smartlife.smartfleet.gui.geo.ShowGpsFrame.DRAW_CIRCLE;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * @author Marius Iulian Grigoras
 *
 */
public class ShowGpsFrameActionListener implements ActionListener {

	ShowGpsFrame frame;
	
	public ShowGpsFrameActionListener(ShowGpsFrame param) {
		this.frame = param;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		final String actionCommand = e.getActionCommand();
		switch (actionCommand) {
		case CHOOSE_COLOR:
			frame.chooseColor();
			break;
		case DRAW_LOCATION:
			frame.drawPoint();
			break;
		case DRAW_LINE:
			frame.drawLine();
			break;
		case DRAW_CIRCLE:
			break;
		default:
			break;
		}
	}

}
