package graphics;

import static org.lwjgl.opengl.GL11.GL_MODELVIEW;
import static org.lwjgl.opengl.GL11.GL_PROJECTION;
import static org.lwjgl.opengl.GL11.GL_QUADS;
import static org.lwjgl.opengl.GL11.GL_TEXTURE_2D;
import static org.lwjgl.opengl.GL11.GL_BLEND;
import static org.lwjgl.opengl.GL11.glBegin;
import static org.lwjgl.opengl.GL11.glEnable;
import static org.lwjgl.opengl.GL11.glEnd;
import static org.lwjgl.opengl.GL11.glLoadIdentity;
import static org.lwjgl.opengl.GL11.glMatrixMode;
import static org.lwjgl.opengl.GL11.glOrtho;
import static org.lwjgl.opengl.GL11.glTexCoord2f;
import static org.lwjgl.opengl.GL11.glTranslatef;
import static org.lwjgl.opengl.GL11.glVertex2f;
import static org.lwjgl.opengl.GL11.glBlendFunc;
import static org.lwjgl.opengl.GL11.GL_SRC_ALPHA;
import static org.lwjgl.opengl.GL11.GL_ONE_MINUS_SRC_ALPHA;

import java.io.IOException;
import java.io.InputStream;

import main.Boot;
import main.Player;
import map.TileGrid;
import map.TileType;

import org.lwjgl.LWJGLException;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import org.newdawn.slick.opengl.Texture;
import org.newdawn.slick.opengl.TextureLoader;
import org.newdawn.slick.util.ResourceLoader;

/**
 * 
 * @author eshinig
 *
 */
public class Designer {
	
	public static int WIDTH, HEIGHT;
	static int  blockSize = 32;
	private static int noOfBlocksOnRight=5;
	
	
	public static void beginSession(int numRows,int numCols){
		HEIGHT= numRows*blockSize;
		WIDTH= (numCols+noOfBlocksOnRight)*blockSize;
		try {
			Display.setTitle("Tower Defence Game");
			Display.setDisplayMode(new DisplayMode(WIDTH, HEIGHT));
			Display.create();
		} catch (LWJGLException e) {
			e.printStackTrace();
		}
		glMatrixMode(GL_PROJECTION);
		glLoadIdentity();
		glOrtho(0, WIDTH, HEIGHT, 0, 1, -1);
		glMatrixMode(GL_MODELVIEW);
		glEnable(GL_TEXTURE_2D);
		glEnable(GL_BLEND);
		glBlendFunc(GL_SRC_ALPHA, GL_ONE_MINUS_SRC_ALPHA);
	
		}
	
/**
 * 
 * @param tex
 * @param x
 * @param y
 * @param width
 * @param height
 */
	public static void drawQuadTex(Texture tex, float x, float y, float width, float height){

		tex.bind();
		glTranslatef(x,y,0);
		glBegin(GL_QUADS);
		glTexCoord2f(0, 0);
		glVertex2f(0, 0);
		glTexCoord2f(1, 0);
		
		glVertex2f(width, 0);
		glTexCoord2f(1, 1);
		glVertex2f(width, height);
		glTexCoord2f(0, 1);
		glVertex2f(0, height);
		glEnd();
		glLoadIdentity();
		
	}
	
	/**
	 * 
	 * @param path
	 * @param fileType
	 * @return
	 */
	public static Texture loadTexture(String path, String fileType)
	{
		Texture tex=null;
		InputStream in= ResourceLoader.getResourceAsStream(path);
		try {
			tex=TextureLoader.getTexture(fileType, in);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return tex;
	}
	
	/**
	 * 
	 * @param name
	 * @return
	 */
	public static Texture quickTexture(String name)
	{
		Texture tex=null;
		tex=loadTexture("res/"+name+".png","PNG");
		
		return tex;
	}
	
}
