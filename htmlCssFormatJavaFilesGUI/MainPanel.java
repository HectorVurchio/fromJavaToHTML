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

import java.io.IOException;
import java.nio.file.Paths;
import java.nio.file.Path;

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
	 
	 
	 
	 
     public MainPanel(JFrame frame, Double mainFrameWidth, Double mainFrameHeight){
		 this.frame = frame;
		 this.mainFrameWidth = mainFrameWidth;
		 this.mainFrameHeight = mainFrameHeight;
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
		JScrollPane listScrollPane = new JScrollPane();
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
		 connectButton = new JButton();
		 connectButton.setText("Apply");
		 connectButton.addActionListener(e -> {
			System.out.println("Connection button");
			System.out.println(FileChooserFrames.input);
			System.out.println(FileChooserFrames.output);
			System.out.println(FileChooserFrames.outputFileName);
			
			try{
				new StartWithComments(FileChooserFrames.input,FileChooserFrames.output).startProgram();
			}catch(IOException ex){
				System.out.println(ex);
			}
			
			/*
			System.out.println("OS-Name: "+System.getProperty("os.name"));
			System.out.println("User-Home: "+System.getProperty("user.home"));
			System.out.println("User-Name: "+System.getProperty("user.name"));
			System.out.println("User-Dir: "+System.getProperty("user.dir"));
			*/
			/*
			Path sourceFile = Paths.get(System.getProperty("user.dir"),FileChooserFrames.outputFileName); 
			System.out.println(sourceFile);
			*/
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