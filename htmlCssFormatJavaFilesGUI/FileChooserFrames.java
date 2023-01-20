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
	private String[] filters = {"txt Files","txt"};
	private FileNameExtensionFilter filter;

    public FileChooserFrames(JFrame frame){
		this.frame = frame;
		//this.filePath = Paths.get(System.getProperty("user.home"),"Desktop",defaultDirectory).toString();
		this.fileChooser = new JFileChooser(PrepareFiles.filePath);
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
					PrepareFiles.input = file;
				}else if(intention == "output"){
					PrepareFiles.output = file;
					PrepareFiles.outputFileName = file.getAbsolutePath();
				}
				PrepareFiles.writeFilesTextArea();
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
		String outputFileName;
		outputFileName = JOptionPane.showInputDialog(frame,
									textMessage,titleTwo,JOptionPane.QUESTION_MESSAGE);
		if(outputFileName != null){
			String pattern = "[?;.>/,_:~@*\'\"!#=^)(}{<\\s|$|(\\])|(\\[)]|\\]";
			Pattern r = Pattern.compile(pattern);
			Matcher m = r.matcher(outputFileName);
			if(m.find()){
				System.out.println("Special characters has been found...");
				JOptionPane.showMessageDialog(frame, "Special characters has been found... Do not use it.");
			}else{
				outputFileName = MessageFormat.format("{0}.txt", outputFileName);
				Path newFile = PrepareFiles.setWorkFiles(outputFileName);
				PrepareFiles.output = new File(newFile.toString());
				PrepareFiles.outputFileName = newFile.toString();
				PrepareFiles.writeFilesTextArea();
			}
		}
	}
}


