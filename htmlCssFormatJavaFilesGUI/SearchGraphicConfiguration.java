// --------  Graphic configuration Area  -------------

package htmlCssFormatGUI;

import java.awt.GraphicsConfiguration;
import java.awt.GraphicsEnvironment;
import java.awt.GraphicsDevice;
import java.awt.Rectangle;

public class SearchGraphicConfiguration {
    private GraphicsConfiguration gc;
    private int screenWidth;
    private int screenHeight;
 
	public SearchGraphicConfiguration(){
		searchScreenSize();
    }
    
    private GraphicsConfiguration searchScreenSize(){
      GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
      GraphicsDevice gd = ge.getDefaultScreenDevice();
      gc = gd.getDefaultConfiguration();
      Rectangle bounds = gc.getBounds();
      screenWidth = bounds.width;
      screenHeight = bounds.height;
      return gc;
   }
   
   public GraphicsConfiguration getGC(){
	   return gc;
   }
	public int getScreenWidth(){
		    return screenWidth;
	}
	public int getScreenHeight(){
		    return screenHeight;
	}
}


