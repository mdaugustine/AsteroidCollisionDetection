package asteroidanimation;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.BasicGame;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Circle;


public class AsteroidAnimation extends BasicGame
{
	private Circle asteroid = null;
	private boolean collides = false;
	private float[] shipPosition = null;
	private Circle shipCollider = null; //to test if the asteroid and ship collide

	public AsteroidAnimation(String title)
	{
		super(title);
	}

	@Override
	public void init(GameContainer container) throws SlickException
	{
		asteroid = new Circle(200,0,25); //start the asteroid in the top center
		shipPosition = new float[]{200,600}; //start the ship in the bottom center
		shipCollider = new Circle(shipPosition[0], shipPosition[1], 5); //set ship collider to ship's location
	}

	@Override
	public void update(GameContainer container, int delta) throws SlickException
	{
		Input input = container.getInput();
		
		asteroid.setCenterY(asteroid.getCenterY() + 1); //move the asteroid one pixel down every frame
		
		if (input.isKeyDown(Input.KEY_A)) shipPosition[0]--; //A moves ship to the left
		if (input.isKeyDown(Input.KEY_D)) shipPosition[0]++; //D moves ship to the right
		
		shipCollider.setLocation(shipPosition[0], shipPosition[1]); //update location of ship collider
		
		collides = asteroid.intersects(shipCollider) || asteroid.contains(shipCollider); //test if ship and asteroid collide
	}

	@Override
	public void render(GameContainer container, Graphics g) throws SlickException
	{
		//draw asteroid and ship
		g.draw(asteroid);
		g.drawString("#", shipPosition[0], shipPosition[1]);
		
		//if collides write collision
		if (collides) g.drawString("Collision", 150, 10);
	}
	
	public static void main(String[] args) throws SlickException
	{
		
		//set up app container
		AppGameContainer app = new AppGameContainer(new AsteroidAnimation("Asteroid Animation"));
		
		app.setDisplayMode(400, 640, false);
		app.setAlwaysRender(true);
		app.setTargetFrameRate(60);
		
		app.start();
	}

}
