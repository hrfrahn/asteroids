import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.SnapshotParameters;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import java.util.Random;
import java.util.ArrayList;
public class Main extends Application{
	static double rotation = 0; 
	static int score = 0;
	static boolean collided = false;
	public static void main(String [] args){
		launch(args);
	}
	public void start(Stage s){
		Random r = new Random();
		s.setTitle("Asteroids");
		Ship ship = new Ship(500, 300);
		Image space = new Image("space.png");
		Group root = new Group();
		Scene theScene = new Scene(root);
		s.setScene(theScene);
		Canvas canvas = new Canvas(800, 600);
		root.getChildren().add(canvas);
		GraphicsContext gc = canvas.getGraphicsContext2D();
		gc.drawImage(space, 0, 0);
		gc.setFill(Color.WHITE);
  	  	gc.setStroke(Color.BLACK);
		Font f = Font.font("Times New Roman", FontWeight.BOLD, 48);
		Font font = Font.font("Times New Roman",FontWeight.BOLD, 20);
		gc.setFont(font);
    	ArrayList<String> input = new ArrayList<String>();
    	ArrayList<Bullet> bullets = new ArrayList<Bullet>();
    	ArrayList<Asteroid> roids = new ArrayList<Asteroid>();
    	for(int i = 0; i < 10; i++){
    		int x = r.nextInt(800), y = r.nextInt(600);
			while(Math.abs(x-ship.getX())<100||Math.abs(y-ship.getY())<100){
				x = r.nextInt(800); y = r.nextInt(600);
			}
    		roids.add(new Asteroid(x, y, -2+r.nextDouble()*4, -2+r.nextDouble()*4, 1));
    	}
    	theScene.setOnKeyPressed(new EventHandler<KeyEvent>(){
    			public void handle(KeyEvent ke){
    				if(!input.contains(ke.getCode().toString())){
    					input.add(ke.getCode().toString());
    				}
    			}
    		}	
    	);
    	theScene.setOnKeyReleased(new EventHandler<KeyEvent>(){
    			public void handle(KeyEvent ke){
    				if(input.contains(ke.getCode().toString())){
    					input.remove(ke.getCode().toString());
    				}
    			}
    		}		
    	);
    	ImageView i = new ImageView(ship.getImage());
  	  	SnapshotParameters sp = new SnapshotParameters();
  	  	sp.setFill(Color.TRANSPARENT);
  	  	gc.setLineWidth(2);
    	new AnimationTimer(){
    		public void handle(long now) {
				if(input.contains("A")){
					  Image rotated  = i.snapshot(sp, null);
					  ship.setRotation(i.getRotate()%360);
			    	  i.setRotate((ship.getRotation()-5)%360);
			    	  rotation = (i.getRotate());
			    	  gc.drawImage(space, 0, 0);
			    	  gc.drawImage(rotated, ship.getX(), ship.getY());
			    	  gc.drawImage(roids.get(0).getImage(), roids.get(0).getX(), roids.get(0).getY());
				}
				if(input.contains("D")){
			    	  Image rotated  = i.snapshot(sp, null);
			    	  ship.setRotation((i.getRotate()));
			    	  i.setRotate((ship.getRotation()+5));
			    	  rotation = i.getRotate()%360;
			    	  gc.drawImage(space, 0, 0);
			    	  gc.drawImage(rotated, ship.getX(), ship.getY());
			    	  gc.drawImage(roids.get(0).getImage(), roids.get(0).getX(), roids.get(0).getY());
				}
				if(input.contains("W")){
					  double dx = Math.cos(Math.toRadians(rotation));
					  double dy = Math.sin(Math.toRadians(rotation));
					  ship.update(dx*6, dy*6);
					  Image rotated  = i.snapshot(sp, null);
			    	  gc.drawImage(space, 0, 0);
			    	  gc.drawImage(rotated, ship.getX(), ship.getY());
			    	  gc.drawImage(roids.get(0).getImage(), roids.get(0).getX(), roids.get(0).getY());
				}
				if(input.contains("Q")){
					System.out.println("Score: "+(score*ship.getAsteriodsDestroyed()));
					Platform.exit();
				}
    			for(int x = 0; x < roids.size(); x++){
    				if(roids.get(x).getX()>800){
    					roids.get(x).setX(0);
    				}
    				else if(roids.get(x).getX()<0){
    					roids.get(x).setX(800);
    				}
    				else if(roids.get(x).getY()<0){
    					roids.get(x).setY(800);
    				}
    				else if(roids.get(x).getY()>800){
    					roids.get(x).setY(0);
    				}
    				else if(roids.get(x).overlaps(ship)){
    					roids.remove(x);
    					collided = true;
    					ship.setAsteriodsDestroyed(ship.getAsteriodsDestroyed() + 1);
    				}
    				else{
    					for(int j = 0; j < bullets.size(); j++){
    						if(bullets.get(j).overlaps(roids.get(x))){
    							bullets.remove(j);
    							if(roids.get(x).getSize()>0.75){
    								roids.add(new Asteroid(roids.get(x).getX(), roids.get(x).getY(), -1/roids.get(x).getXVelocity(), -roids.get(x).getXVelocity(), 0.75));
    								roids.add(new Asteroid(roids.get(x).getX(), roids.get(x).getY(), -1/roids.get(x).getXVelocity(), roids.get(x).getXVelocity(), 0.75));
    								roids.remove(x);
    								ship.setAsteriodsDestroyed(ship.getAsteriodsDestroyed() + 1);
    							}
    							else{
    								roids.remove(x);
    								ship.setAsteriodsDestroyed(ship.getAsteriodsDestroyed() + 1);
    							}
    						}
    					}
    					try{roids.get(x).update();}
    					catch(java.lang.IndexOutOfBoundsException e){
    						roids.get(x-1).update();
    					}
    					gc.drawImage(space, 0, 0);
    					Image rotated = i.snapshot(sp, null);
    					gc.drawImage(rotated, ship.getX(), ship.getY());
    					for(int j = x; j >= 0; j--){
    						try{
    							gc.drawImage(roids.get(j).getImage(), roids.get(j).getX(), roids.get(j).getY());
    						}
    						catch(java.lang.IndexOutOfBoundsException e){
    							gc.drawImage(roids.get(j-1).getImage(), roids.get(j-1).getX(), roids.get(j-1).getY());
        					}
    					}
    					gc.fillText("Score: "+ score*ship.getAsteriodsDestroyed(), 50, 50);
						gc.fillText("Lives: "+ ship.getLives(), 700, 50);

    				}
    			
    			}
    			if(collided){
    				if(ship.getLives()>1){
    					ship.setLives(ship.getLives() - 1);
    					ship.setX(400);
    					ship.setY(300);
    					collided = false;
    				}
    				else{
    					gc.setFont(f);
    					gc.drawImage(space, 0, 0);
    					gc.fillText("Score: "+((int)score*ship.getAsteriodsDestroyed()), 400, 300);
    					stop();
    				}
    			}
    			if(roids.size()<10){
    				int x = r.nextInt(800), y = r.nextInt(600);
    				while(Math.abs(x-ship.getX())<100||Math.abs(y-ship.getY())<100){
    					x = r.nextInt(800); y = r.nextInt(600);
    				}
    				roids.add(new Asteroid(x, y, -2+Math.random()*4, -2+Math.random()*4, 1));
    			}
    			for(int x = 0; x < bullets.size(); x++){
					if(bullets.get(x).getX()>800|| bullets.get(x).getY()>600||bullets.get(x).getX()<0||bullets.get(x).getY()<0){
						bullets.remove(x);
					}
					else{
						bullets.get(x).update();
						gc.drawImage(space, 0, 0);
						Image rotated = i.snapshot(sp, null);
						gc.drawImage(rotated, ship.getX(), ship.getY());
						for(int j = x; j >= 0; j--){    				
							gc.drawImage(bullets.get(j).getImage(), bullets.get(j).getX(), bullets.get(j).getY());
						}
						for(int j = roids.size()-1; j >= 0; j--){
							gc.drawImage(roids.get(j).getImage(), roids.get(j).getX(), roids.get(j).getY());
						}
						gc.fillText("Score: "+ score*ship.getAsteriodsDestroyed(), 50, 50);
						gc.fillText("Lives: "+ ship.getLives(), 700, 50);
					}
				}
    			if(collided){
    				if(ship.getLives()>1){
    					ship.setLives(ship.getLives() - 1);
    					ship.setX(400);
    					ship.setY(300);
    					collided = false;
    				}
    				else{
    					gc.setFont(f);
    					gc.drawImage(space, 0, 0);
    					gc.fillText("Score: "+((int)score*ship.getAsteriodsDestroyed()), 400, 300);
    					stop();
    				}
    			}
			}
    		
    	}.start();
    	new AnimationTimer(){
    		long last = 0;
			public void handle(long now) {
				if(now-last>=750000000){
					if(input.contains("SPACE")){
						if(bullets.size()<10){
							double dx = Math.cos(Math.toRadians(rotation));
							double dy = Math.sin(Math.toRadians(rotation));
							bullets.add(new Bullet(ship.getX(), ship.getY(), dx*10, dy*10));
						}
					}
					score++;
					last = now;
				}
				if(input.contains("Q")){
					Platform.exit();
				}
			}
    	}.start();
		s.show();
	}
}