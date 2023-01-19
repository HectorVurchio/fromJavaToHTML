package htmlCssFormatGUI;

// http://www.iconninja.com

import java.awt.Dimension;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import javax.swing.text.JTextComponent;
import javax.swing.text.DefaultCaret;
import javax.swing.JOptionPane;

public class BarraMenu {

	private JMenuBar menuBarra;
	private JMenu menuFile;
	private int width;
	private int height;
	private JFrame frame;
	private String title;
	private int menuFileKeyEvent;
	private String className;
	
	public BarraMenu(JFrame frame,int width, int height){
		this.width = width;
		this.height = height;
		this.frame = frame;
		this.menuBarra = new JMenuBar();
		this.menuFile = new JMenu("Config     ");
		this.menuFileKeyEvent = KeyEvent.VK_C;
		this.className = "htmlCssFormatGUI.HtmlCssFormatFrame";
	}

     public JMenuBar createMenuBar(){
		 Double menuBarHeightDouble = height*.06;
		 int menuBarHeightInt = menuBarHeightDouble.intValue();
		 menuBarra.setPreferredSize(new Dimension(width,menuBarHeightInt));
		 menuBarra.setOpaque(true);
		 menuBarra.add(createMenuFile());
		 return menuBarra;
     }
	 
	 private JMenu createMenuFile (){
		 menuFile.setMnemonic(menuFileKeyEvent);
		 menuFile.add(createMenuItem("Input File",KeyEvent.VK_S,"Server.png","get IP Server",0,"Select Input File","input"));
		 menuFile.addSeparator();
		 menuFile.add(createMenuItem("Output File",KeyEvent.VK_E,"Exit.png","get exit port",0,"Select Output File","output"));
		 menuFile.addSeparator();
		 menuFile.add(createMenuItem("Build Output",KeyEvent.VK_E,"helmet.png","build your output",2,"name of Output File","build"));
		 menuFile.addSeparator();
		 return menuFile;
     }
     
	 private JMenuItem createMenuItem(String title, 
						int keyEvent,  
						String fileName,
						String eventName, int action,String dialogTitle,String intention){
		 JMenuItem menuItem = new JMenuItem (title,keyEvent);
		 menuItem.setIcon(new IconSetter().createIcono(className,fileName));
		 FileChooserFrames fileChooser = new FileChooserFrames(frame);
		 menuItem.addActionListener(new ActionListener(){
			  public void actionPerformed(ActionEvent bp) {
				  if(action == 0){
					  //new MyDialogCreator().getIPServer();  
					fileChooser.createOpenChooser(dialogTitle,intention);
					System.out.println(eventName);
				  }else if(action == 1){
					fileChooser.createSaveChooser(dialogTitle);
					System.out.println(eventName);
				  }else if(action == 2){
					  fileChooser.setOutputFile();	
				  }
			  }
		 });
		 return menuItem;
	 }
}