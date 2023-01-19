package htmlCssFormatGUI;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import java.awt.Dimension;

public class HtmlCssFormatFrame{
	private SearchGraphicConfiguration sgc;
	private Double frameWidth;
    private Double frameHeight;
    private Double frameX;
    private Double frameY;
	private JFrame frame;
	private String title = "Format HTML-CSS Script";
	String errorMessage = "Icon file not found.";
	
	public HtmlCssFormatFrame(){
		sgc = new SearchGraphicConfiguration();
		frameWidth = sgc.getScreenWidth() * 0.33;
		frameHeight = sgc.getScreenHeight() * 0.35;
		frameX = sgc.getScreenWidth() * 0.1;
		frameY = sgc.getScreenHeight() * 0.15;
		frame = new JFrame(sgc.getGC());
	}
	
	public JFrame createGUI(){
		frame.setTitle(title);
		try{
			frame.setIconImage(new IconSetter().createIcono("htmlCssFormatGUI.HtmlCssFormatFrame","HJValueIcon.png").getImage());
		}catch(NullPointerException e){
			JOptionPane.showMessageDialog(frame,errorMessage);
		}
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocation(frameX.intValue(), frameY.intValue());    
		frame.setMinimumSize(new Dimension(frameWidth.intValue(),frameHeight.intValue()));
		frame.setResizable(false);
		frame.setJMenuBar(new BarraMenu(frame,frameWidth.intValue(),frameHeight.intValue()).createMenuBar());
		frame.addWindowListener(new MainFrameEvent());
		// Insert the main panel
		MainPanel CMP = new MainPanel(frame,frameWidth,frameHeight);
		CMP.addMainFramePaneComponents();
		//Shows the frame and its content
		 frame.pack();
		 frame.setVisible(true);
		 return frame;
	}
	public JFrame getFrame(){
		return frame;
    }
}