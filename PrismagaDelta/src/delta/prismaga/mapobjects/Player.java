package delta.prismaga.mapobjects;

import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;

import delta.prismaga.main.FileUtils;
import delta.prismaga.main.Game;
import delta.prismaga.main.Input;

public class Player extends MapObject {
	
	private int x, y, xspeed, yspeed;
	public int xOffset;
	public  int yOffset;
	private BufferedImage image; 
	private Rectangle bounds; //Collision Box
	private Input input = Game.getInput();
	
	public Player() {
		image = FileUtils.loadAsImage("/player.png");
		
		bounds = new Rectangle(x, y, image.getWidth() * Game.SCALE, image.getHeight() * Game.SCALE);
		
		this.xOffset = 0;
		this.yOffset = 0;
		
		this.x = Game.WIDTH * Game.SCALE / 2;
		this.y = Game.HEIGHT * Game.SCALE / 2;
	}
	
	public void tick() {
		bounds.setLocation(xOffset, yOffset);
		
		xspeed = 0;
		yspeed = 0;
		
		if(input.keys[KeyEvent.VK_UP]) {
			yspeed -= 3;
		} else if(input.keys[KeyEvent.VK_DOWN]){
			yspeed += 3;
		}
		
		if(input.keys[KeyEvent.VK_LEFT]) {
			xspeed -= 3;
		} else if(input.keys[KeyEvent.VK_RIGHT]) {
			xspeed += 3;
		}
		
		move(xspeed, yspeed);
	}
	
	public void move(int xs, int ys) {
		if(!collision(xs, 0)) {xOffset += xs;}
		if(!collision(0, ys)) {yOffset += ys;}
	}
	
	public void render() {
		Game.g.drawImage(image, x, y, image.getWidth() * Game.SCALE, image.getHeight() * Game.SCALE, null);
	}
	
	public boolean collision(int xs, int ys) {
		if(Game.level.getTile((xOffset + xs + x) / (Game.TILESIZE * Game.SCALE), 
				(yOffset + ys + y) / (Game.TILESIZE * Game.SCALE)).isSolid()) {
			return true;
		}
		
		if(Game.level.getTile((xOffset + xs + x + getWidth() * Game.SCALE - 1) / (Game.TILESIZE * Game.SCALE), 
				(yOffset + ys + y) / (Game.TILESIZE * Game.SCALE)).isSolid()) {			
			return true;
		}
		
		if(Game.level.getTile((xOffset + xs + x) / (Game.TILESIZE * Game.SCALE), 
				(yOffset + ys + y + getHeight() * Game.SCALE - 1) / (Game.TILESIZE * Game.SCALE)).isSolid()) {
			return true;
		}
		
		if(Game.level.getTile((xOffset + xs + x + getWidth() * Game.SCALE - 1) / (Game.TILESIZE * Game.SCALE), 
				(yOffset + ys + y + getHeight() * Game.SCALE - 1) / (Game.TILESIZE * Game.SCALE)).isSolid()) {
			return true;
		}
		
		return false;
	}
	
	
	
	public Rectangle getBounds() {
		return bounds;
	}
	
	public int getWidth() { return image.getWidth(); }
	public int getHeight() { return image.getHeight(); }
}
