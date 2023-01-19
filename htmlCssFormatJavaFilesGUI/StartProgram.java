package htmlCssFormatGUI;
import javax.swing.SwingUtilities;


public class StartProgram {
	
	public StartProgram(){}
	
	public void show(){
		new HtmlCssFormatFrame().createGUI();
	}
     public static void main (String[] args){
          javax.swing.SwingUtilities.invokeLater(new Runnable(){
               public void run() {
					new StartProgram().show();
               }
          });
     }

}

/*

C:\Users\Heclau\Desktop\htmlCssFormatJavaFiles

javac -d . htmlCssFormatJavaFilesGUI/*.java

java htmlCssFormatGUI.StartProgram

*/
