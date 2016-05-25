import javafx.scene.image.Image;
import javafx.geometry.Rectangle2D;
public class GraphicsObject{
	private Image Image;
	private double X, Y;
	private double XVelocity, YVelocity;
	private double Rotation;
	private double height, width;
	
	public GraphicsObject( Image image, double x, double y){
		this.setImage(image);
		this.X = x;
		this.Y = y;
		this.setRotation(0);
		XVelocity = 0; YVelocity = 0;
		height = image.getHeight();
		width = image.getWidth();
	}
	public GraphicsObject(){
		
	}
	public void update(double xVelocity, double yVelocity){
		this.XVelocity = xVelocity;
		this.YVelocity = yVelocity;
		X += XVelocity;
		Y += YVelocity;
	}
	public void update(){
		X+=XVelocity;
		Y+=YVelocity;
	}
	public Rectangle2D getHitbox(){
		Rectangle2D r = new Rectangle2D(X, Y, height, width);
		return r;
	}
	public boolean overlaps(GraphicsObject g){
		return getHitbox().intersects(g.getHitbox());
	}
	public Image getImage() {
		return Image;
	}
	public void setImage(Image image) {
		Image = image;
	}
	public double getRotation() {
		return Rotation;
	}
	public void setRotation(double rotation) {
		Rotation = rotation;
	}
	public double getX() {
		return X;
	}
	public void setX(double x) {
		this.X = x;
	}
	public double getY() {
		return Y;
	}
	public void setY(double y) {
		this.Y = y;
	}
	public double getXVelocity() {
		return XVelocity;
	}
	public void setXVelocity(double x) {
		this.XVelocity = x;
	}
	public double getYVelocity() {
		return YVelocity;
	}
	public void setYVelocity(double y) {
		this.YVelocity = y;
	}
	public void setVelocity(double x, double y){
		this.XVelocity = x;
		this.YVelocity = y;
	}
	public double getHeight() {
		return height;
	}
	public double getWidth() {
		return width;
	}
	public void setHeight(double h) {
		this.height = h;
	}
	public void setWidth(double w) {
		this.width = w;
	}
}