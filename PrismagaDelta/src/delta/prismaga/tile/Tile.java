package delta.prismaga.tile;

import java.awt.image.BufferedImage;

import delta.prismaga.main.FileUtils;
import delta.prismaga.main.Game;

public class Tile {
	
	private BufferedImage image;
	private boolean isSolid;
	
	public static Tile grassPlatform = new Tile(FileUtils.loadAsImage("/sprite.png"), true),
						airTile = new Tile(null, false),
						dirtTile = new Tile(FileUtils.loadAsImage("/dirt.png"),true);
	
	public Tile(BufferedImage image, boolean isSolid) {
		this.image = image;
		this.isSolid = isSolid;
	}
	
	public void tick() {}
	
	public void render(int x, int y) {
		if(this.image != null)
			Game.g.drawImage(this.image, x, y, this.image.getWidth() * Game.SCALE, this.image.getHeight() * Game.SCALE, null);
	}
	
	public boolean isSolid() {
		return isSolid;
	}
}
