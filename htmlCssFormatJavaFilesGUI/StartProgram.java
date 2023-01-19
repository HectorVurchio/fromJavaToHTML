package htmlCssFormatGUI;
import javax.swing.SwingUtilities;


public class StartProgram {
     public static void main (String[] args){
          javax.swing.SwingUtilities.invokeLater(new Runnable(){
               public void run() {
					new HtmlCssFormatFrame().createGUI();
               }
          });
     }

}

/*

C:\Users\Heclau\Desktop\htmlCssFormatJavaFiles

javac -d . htmlCssFormatJavaFilesGUI/*.java

java htmlCssFormatGUI.StartProgram

*/
