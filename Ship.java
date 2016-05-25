import javafx.scene.image.Image;
public class Ship extends GraphicsObject {
	private int lives;
	private int asteriodsDestroyed;
	public Ship(){
		super(new Image("ship.png", 50, 50, true, true), 400, 300);
		setAsteriodsDestroyed(0);
		setLives(3);
	}
	public Ship(double x, double y){
		super(new Image("ship.png", 50, 50, true, true), x, y);
		setAsteriodsDestroyed(0);
		setLives(3);
	}
	public int getLives() {
		return lives;
	}
	public void setLives(int lives) {
		this.lives = lives;
	}
	public int getAsteriodsDestroyed() {
		return asteriodsDestroyed;
	}
	public void setAsteriodsDestroyed(int asteriodsDestroyed) {
		this.asteriodsDestroyed = asteriodsDestroyed;
	}
}
