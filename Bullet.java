import javafx.scene.image.Image;
public class Bullet extends GraphicsObject{
	public Bullet(double X, double Y, double xVelocity, double yVelovicty){
		super(new Image("bullet.png", 25, 25, true, true), X, Y);
		this.setXVelocity(xVelocity);
		this.setYVelocity(yVelovicty);
	}
}