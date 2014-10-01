package delta.prismaga.gfx;

import java.awt.image.BufferedImage;

import delta.prismaga.main.Game;

public class SpriteSheet {
	
	private BufferedImage image;
	private int gridX, gridY;
	
	public SpriteSheet(BufferedImage image) {
		this.image = image;
		this.gridX = Game.TILESIZE;
		this.gridY = Game.TILESIZE;
	}
	
	public SpriteSheet(BufferedImage image, int gx, int gy) {
		this.image = image;
		this.gridX = gx;
		this.gridY = gy;
	}
	
	public BufferedImage crop(int x, int y, int w, int h) {
		return image.getSubimage(x * gridX, y * gridY, w, h);
	}
	
	public int getXGrid() { return gridX; }
	public int getYGrid() { return gridY; }
	public BufferedImage getImage() { return image; }
}
