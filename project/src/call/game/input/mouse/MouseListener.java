package call.game.input.mouse;

/**
 * A listener for mouse events
 * 
 * @author Callum
 */
public interface MouseListener 
{
	
	/**
	 * Called on a mouse click event
	 * 
	 * @param x The mouse's X coordinate
	 * @param y The mouse's Y coordinate
	 * @param state The state of the button
	 * @param button The button on the mouse
	 */
	boolean onClick(int x, int y, int state, int button);
	
	
	/**
	 * Called on a mouse move event
	 * 
	 * @param x The new X coordinate of the mouse
	 * @param y The new Y coordinate of the mouse
	 */
	void onMove(int x, int y);
}
