package htmlCssFormatGUI;

import java.net.URL;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import java.lang.ClassNotFoundException;



public class IconSetter {
   //  URL imagenIcono;
 
     public IconSetter(){  // CHANGE
		
     }

    public ImageIcon createIcono(String clase, String fileName) {  //CHANGE
		//Loading Class
		 ClassLoader classLoad;
		 Class loadClase = null;
		 ImageIcon icono = null;
		 URL imagenIcono = null;
		 try{
			classLoad = ClassLoader.getSystemClassLoader();
			loadClase = classLoad.loadClass(clase);
			imagenIcono = loadClase.getResource(fileName);
		 }
		 catch (ClassNotFoundException | NullPointerException e) {
		   //System.err.println("Couldn't find class for specified ClassLoader:");
		   String message = "No se pudo cargar ClassLoader o " + fileName;
		   JOptionPane.showMessageDialog(new HtmlCssFormatFrame().getFrame(),message);
		   
		 } 
		// se crea el icono
        if (imagenIcono != null) {
           //loading external resources
            icono = new ImageIcon(imagenIcono);
        } else {
            System.err.println("Couldn't find file: " + fileName);
			String messageTwo = "No se encontro " + fileName;
	        JOptionPane.showMessageDialog(new HtmlCssFormatFrame().getFrame(),messageTwo);
            icono =  null;
        }
		return icono;
     }
}