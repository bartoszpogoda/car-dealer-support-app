package lab04;

import java.awt.Font;
import java.util.Set;

import javax.swing.UIManager;

public class FontSizeChanger {
	
	private FontSizeChanger() {};
	
	public static void setDefaultSize(int size) {

	    Set<Object> keySet = UIManager.getLookAndFeelDefaults().keySet();
	    Object[] keys = keySet.toArray(new Object[keySet.size()]);

	    for (Object key : keys) {

	        if (key != null && key.toString().toLowerCase().contains("font")) {

	            Font font = UIManager.getDefaults().getFont(key);
	            if (font != null) {
	                font = font.deriveFont((float)size);
	                UIManager.put(key, font);
	            }

	        }

	    }

	}
}
