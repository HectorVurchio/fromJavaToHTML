package htmlCssFormatGUI;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.io.File;
import java.awt.Desktop; 
import java.text.MessageFormat;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.Files;
import java.io.IOException;
import java.nio.file.FileAlreadyExistsException;

public class FileChooserFrames {
    final String newline = "\n";
	private JFrame frame;
	private JFileChooser fileChooser;
	private String filePath;
	private String[] filters = {"txt Files","txt"};
	private FileNameExtensionFilter filter;
	private static String defaultDirectory  = "HJDefault";
	public static File input;
	public static File output;
	public static String outputFileName;
	
	static {
        input = new File(setWorkFiles("CodeIn.txt").toString());
		output = new File(setWorkFiles("CodeOut.txt").toString());
		outputFileName = output.toString(); 
    }
    public FileChooserFrames(JFrame frame){
		this.frame = frame;
		this.filePath = Paths.get(System.getProperty("user.home"),"Desktop",defaultDirectory).toString();
		this.fileChooser = new JFileChooser(filePath);
		this.filter = new FileNameExtensionFilter(this.filters[0],this.filters[1]);
		this.fileChooser.setFileFilter(this.filter);
		this.fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
		this.fileChooser.setAcceptAllFileFilterUsed(false);
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
					outputFileName = file.getAbsolutePath();
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
		if(outputFileName != null){
			String pattern = "[?.>/,_:~@*\'\"!#=^)(}{<\\s|$|(\\])|(\\[)]|\\]";
			Pattern r = Pattern.compile(pattern);
			Matcher m = r.matcher(outputFileName);
			if(m.find()){
				System.out.println("Special characters has been found...");
			}else{
				outputFileName = MessageFormat.format("{0}.txt", outputFileName);
				Path newFile = setWorkFiles(outputFileName);
				output = new File(newFile.toString());
				outputFileName = newFile.toString();
			}
		}
	}
	private static Path setWorkFiles(String outputFileName){
		Path newFile = Path.of(outputFileName);
		Path absolutePath = Paths.get(System.getProperty("user.home"),"Desktop",defaultDirectory,outputFileName);
		try{
			Path newPath = Files.createDirectory(absolutePath.getParent());
			newFile = Files.createFile(absolutePath);
		}catch(FileAlreadyExistsException ioe){
			System.out.println("Error cause "+ioe.getCause());
			System.out.println("Error Reason "+ioe.getReason());
			System.out.println("Message "+ioe.getMessage());
			try{
				newFile = Files.createFile(absolutePath);
			}catch(FileAlreadyExistsException fae){
				System.out.println("file "+outputFileName+" already exists in "+ioe.getMessage());
			}catch(IOException e){
				System.out.println("Problems with file");
			}
		}catch(IOException e){
			System.out.println("Diferent Reason");
		}
		return newFile;
	}
}


