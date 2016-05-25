import javafx.scene.image.Image;
public class Asteroid extends GraphicsObject{
	private double size;
	public Asteroid(double x, double y, double xVel, double yVel, double size){
		super(new Image("asteriod.png", 50*size, 50*size, true, true),x, y);
		this.size = size;
		this.setVelocity(xVel, yVel);
	}
	public double getSize(){
		return size;
	}
	public void setSize(double s){
		this.size = s;
	}
}