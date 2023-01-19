package htmlCssFormatGUI;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.io.File;
import java.awt.Desktop; 
import java.io.IOException;
import java.text.MessageFormat;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FileChooserFrames {
    final String newline = "\n";
	private JFrame frame;
	private JFileChooser fileChooser;
	private String filePath = "C:/Users/Heclau/Desktop/fromJavaToHTML";
	private String[] filters = {"txt Files","txt"};
	private FileNameExtensionFilter filter;
	public static File input;
	public static File output;
	public static String outputFileName;
	
    public FileChooserFrames(JFrame frame){
		this.frame = frame;
		this.fileChooser = new JFileChooser(filePath);
		this.filter = new FileNameExtensionFilter(this.filters[0],this.filters[1]);
		this.fileChooser.setFileFilter(this.filter);
		this.fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
		this.fileChooser.setAcceptAllFileFilterUsed(false);
		this.input = new File("CodeIn.txt");
		this.output = new File("CodeOut.txt");
		this.outputFileName = "CodeOut.txt";
    }
	
	public void createOpenChooser(String title,String intention){
		fileChooser.setDialogTitle(title);
		int returnVal = fileChooser.showOpenDialog(frame);
		try{
			if (returnVal == JFileChooser.APPROVE_OPTION) {
				File file = fileChooser.getSelectedFile();
				Desktop desktop = Desktop.getDesktop();
				desktop.open(file); 
				System.out.println(" ");
				System.out.println("Opening: " + file.getName() + "." + newline);
				if(intention == "input"){
					input = file;
				}else if(intention == "output"){
					output = file;
					outputFileName = file.getName();
				}
			} else {
				System.out.println("Open command cancelled by user." + newline);
			}
		}catch(IOException IOE){
			System.out.println(IOE);
		}
	}
	//this method does nothing here so far so it is possible its remotion 
    public void createSaveChooser(String title){
		fileChooser.setDialogTitle(title);
		fileChooser.setApproveButtonText("Save");
        int returnVal = fileChooser.showSaveDialog(frame);
        if (returnVal == JFileChooser.APPROVE_OPTION) {
            File file = fileChooser.getSelectedFile();
                if(file.exists()==true){
                    int dialog = JOptionPane.showConfirmDialog(frame,
                                                               "El Archivo "+file.getName()+" Ya Existe. Desea Reemplazarlo?",
                                                               "Advertencia",
                                                                JOptionPane.YES_NO_OPTION);

                    switch (dialog){
                        case JOptionPane.YES_OPTION:    System.out.println("Accept");
                                                        //new LeftPanelMiddlePanel().saveDataTableToAFile(file.getName()+".oam");
														//new SaveDataTableToAFile(LeftPanelMiddlePanel.table).saveDataTable(file.getName()+".oam");                  
                                                        break;
                        case JOptionPane.NO_OPTION:     System.out.println("Reject");
                                                        break;
                        case JOptionPane.CLOSED_OPTION: System.out.println("Closed");
                                                        break;                           
                        case JOptionPane.CANCEL_OPTION: System.out.println("cancel"); 
                                                        break;                           
                    }
                }else{
                    System.out.println("Accept");
                   // new LeftPanelMiddlePanel().saveDataTableToAFile(file.getName()+".oam");  
                   //new SaveDataTableToAFile(LeftPanelMiddlePanel.table).saveDataTable(file.getName()+".oam");                  
                }
            } else {
                System.out.println("Save command cancelled by user." + newline);
            }
    }

	public void setOutputFile(){
		String textMessage = "Insert Your Desired File Name";
		String titleTwo = "Create Output File";
		outputFileName = JOptionPane.showInputDialog(frame,
									textMessage,titleTwo,JOptionPane.QUESTION_MESSAGE);
		String pattern = "[?.>/,_:~@*\'\"!#=^)(}{<\\s|$|(\\])|(\\[)]|\\]";
		Pattern r = Pattern.compile(pattern);
		Matcher m = r.matcher(outputFileName);
		if(m.find()){
			System.out.println("Special characters has been found...");
		}else{
			try{
				outputFileName = MessageFormat.format("{0}.txt", outputFileName);
				File newFile = new File(outputFileName);
				if (newFile.createNewFile()) {
					System.out.println("File created: " + newFile.getName());
					output = newFile;
				} else {
					System.out.println("File already exists.");
				}
			}catch(IOException e){
				System.out.println("An error occurred.");
				e.printStackTrace();
			}
		}
		
	}
}


