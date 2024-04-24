package Models;

import java.awt.Color;
import java.awt.Graphics2D;

import SwingShapes.Rectangle;
import Utils.Direction;
import Utils.Vector;

public class Ball {
	private Rectangle ball;
	// note: 
	//	swing only uses integers to determine where to show the pixels of an shape
	//	the vectors use floating point components to represent the exact movement directions
	// 	to better calculate the position of the ball, the exact position of the ball should be tracked
	// 	and used when calculating movements, and then rounded for the rendering.
	private Vector trueLocation;
	private Vector movementVector;
	private int speed = 4;
	
	public Ball() {
		ball = new Rectangle();
		ball.setColor(Color.black);
		ball.setSize(10,  10);
		trueLocation = new Vector(ball.getXLocation(), ball.getYLocation());
		movementVector = new Vector(Direction.LEFT.getVelocity(), Direction.UP.getVelocity());
	}
	
	public int getXLocation() {
		return ball.getXLocation();
	}
	
	public void setXLocation(int xLocation) {
		ball.setLocation(xLocation, ball.getYLocation());
	}
	
	public int getYLocation() {
		return ball.getYLocation();
	}
	
	public void setYLocation(int yLocation) {
		ball.setLocation(ball.getXLocation(), yLocation);
	}
	
	// determines the direction based on the movement vector
	public Direction getXDirection() {
		return movementVector.getXComponent() < 0 ? Direction.DOWN : Direction.UP;
	}

	// performs a strictly elastic collision sending the ball off in the desired direction
	public void setXDirection(Direction xDirection) {
		// reverse the directional component if not going in the desired direction
		if (getXDirection() != xDirection) movementVector.setXComponent(-1 * movementVector.getXComponent());
	}

	// determines the direction based on the movement vector
	public Direction getYDirection() {
		return movementVector.getYComponent() < 0 ? Direction.DOWN : Direction.UP;
	}

	// performs a strictly elastic collision sending the ball off in the desired direction
	public void setYDirection(Direction yDirection) {
		// reverse the directional component if not going in the desired direction
		if (getYDirection() != yDirection) movementVector.setYComponent(-1 * movementVector.getYComponent());
	}
	
	public int getWidth() {
		return ball.getWidth();
	}
	
	public int getHeight() {
		return ball.getHeight();
	}
	
	public int getSpeed() {
		return speed;
	}
	
	public void moveX() {
		double amountToMoveX = movementVector.getXComponent() * speed;
		trueLocation.setXComponent(trueLocation.getXComponent() + amountToMoveX);

		// move the ball as close to the true position as possible
		ball.setLocation(trueLocation.getXComponentRounded(), ball.getYLocation());
	}
	
	public void moveY() {
		double amountToMoveY = movementVector.getYComponent() * speed;
		trueLocation.setYComponent(trueLocation.getYComponent() + amountToMoveY);

		// move the ball as close to the true position as possible
		ball.setLocation(ball.getXLocation(), trueLocation.getYComponentRounded());
	}
	
	public void draw(Graphics2D g) {
		ball.paint(g);
	}

	public boolean intersects(Paddle paddle) {
		return paddle.getXLocation() < ball.getXLocation() + ball.getWidth() && paddle.getXLocation() + paddle.getWidth() > ball.getXLocation() && 
			paddle.getYLocation() < ball.getYLocation() + ball.getHeight() && paddle.getYLocation() + paddle.getHeight() > ball.getYLocation();
	}

	public boolean intersects(Brick brick) {
		return brick.getXLocation() < ball.getXLocation() + ball.getWidth() && brick.getXLocation() + brick.getWidth() > ball.getXLocation() && 
			brick.getYLocation() < ball.getYLocation() + ball.getHeight() && brick.getYLocation() + brick.getHeight() > ball.getYLocation();
	}
}
