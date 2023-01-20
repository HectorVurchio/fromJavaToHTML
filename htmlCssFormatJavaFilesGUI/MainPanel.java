package htmlCssFormatGUI;

import java.awt.Container;
import java.awt.Color;
import java.awt.Insets;
import java.awt.Dimension;
import java.awt.BorderLayout;
import javax.swing.JFrame; 
import javax.swing.JPanel;
import javax.swing.JLabel; 
import javax.swing.JTextField;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.Box;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import java.io.IOException;
import java.nio.file.Paths;
import java.nio.file.Path;
import java.awt.Font;
import htmlCssFormat.StartWithComments;
  
public class MainPanel{
     private JFrame frame;
     private Double mainFrameWidth;
     private Double mainFrameHeight;
     private JPanel panelOne, panelTwo, panelThree;
     private int panelOneHeightInt,panelOneWidthInt; 
     private int panelTwoHeightInt,panelTwoWidthInt; 
     private int panelThreeHeightInt,panelThreeWidthInt;
     private Container mainFramePaneComponents;
	 public JButton connectButton;
	 public JLabel connectLabel;
	 private JTextArea textArea;
	 
     public MainPanel(JFrame frame, Double mainFrameWidth, Double mainFrameHeight){
		 this.frame = frame;
		 this.mainFrameWidth = mainFrameWidth;
		 this.mainFrameHeight = mainFrameHeight;
		 this.textArea = new JTextArea();
		 this.textArea.setEditable(false);
		 this.textArea.setFont(new Font("Serif", Font.BOLD, 16));
     }

     public Container addMainFramePaneComponents() { 
		 mainFramePaneComponents = frame.getContentPane();
		 mainFramePaneComponents.setLayout(null);
		 Insets insets = mainFramePaneComponents.getInsets();
	  // creating the panels    
		 mainFramePaneComponents.add(createPanelTwo(insets));
		 mainFramePaneComponents.add(createPanelThree(insets));
		 return mainFramePaneComponents;
     }

     private JPanel createPanelTwo(Insets insets){
		 panelTwo = new JPanel();
		 panelTwo.setLayout(new BorderLayout());
		 panelTwo.setOpaque(true);
		 panelTwo.setBackground(Color.CYAN);
		 Double panelTwoWidth = mainFrameWidth*.95;
		 Double panelTwoHeight = (mainFrameHeight*.8)*.7;
		 panelTwoHeightInt = panelTwoHeight.intValue();
		 panelTwoWidthInt = panelTwoWidth.intValue();
		 Double locationX = mainFrameWidth*.025;
		 Double locationY = (mainFrameHeight*.8)*.04;
		 int locX = locationX.intValue();
		 int locY = 2*locationY.intValue()+panelOneHeightInt;
		 panelTwo.setBounds(insets.left+locX,insets.top+locY,panelTwoWidthInt,panelTwoHeightInt);
		JScrollPane listScrollPane = new JScrollPane(textArea);
		PrepareFiles.textArea = textArea;
		 panelTwo.add(listScrollPane,BorderLayout.CENTER); 
		 return panelTwo;
     }


     private JPanel createPanelThree(Insets insets){
		 panelThree = new JPanel();
		 panelThree.setOpaque(true);
		 //panelThree.setBackground(Color.YELLOW);
		 Double panelThreeWidth = mainFrameWidth*.95;
		 Double panelThreeHeight = (mainFrameHeight*.8)*.1;
		 panelThreeHeightInt = panelThreeHeight.intValue();
		 panelThreeWidthInt = panelThreeWidth.intValue();
		 Double locationX = mainFrameWidth*.025;
		 Double locationY = (mainFrameHeight*.8)*.04;
		 int locX = locationX.intValue();
		 int locY = 3*locationY.intValue()+panelOneHeightInt+panelTwoHeightInt;
		 panelThree.setBounds(insets.left+locX,insets.top+locY,panelThreeWidthInt,panelThreeHeightInt);
		 // adding components to this panel 
		 connectLabel = new JLabel();
		 connectLabel.setText("www.hjvalue.com");
		 connectLabel.setFont(new Font("Serif", Font.BOLD, 16));
		 connectButton = new JButton();
		 connectButton.setText("Apply");
		 connectButton.addActionListener(e -> {
			 System.out.println(PrepareFiles.input);
			 System.out.println(PrepareFiles.output);
			 System.out.println(PrepareFiles.outputFileName);
			 
			try{
				new StartWithComments(PrepareFiles.input,PrepareFiles.output).startProgram();
			}catch(IOException ex){
				System.out.println(ex);
			}
		 });
		 panelThree.setLayout(new BoxLayout(panelThree,BoxLayout.LINE_AXIS));
		 panelThree.add(connectLabel);
		 Dimension minSize = new Dimension(panelThreeWidthInt-200, panelThreeHeightInt);
		 Dimension prefSize = new Dimension(panelThreeWidthInt-200, panelThreeHeightInt);
		 Dimension maxSize = new Dimension(Short.MAX_VALUE, panelThreeHeightInt);
		 panelThree.add(new Box.Filler(minSize, prefSize, maxSize));
		 panelThree.add(connectButton);     
		 return panelThree;
     }

}