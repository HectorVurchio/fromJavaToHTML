package htmlCssFormatGUI;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.Files;
import java.io.IOException;
import java.nio.file.FileAlreadyExistsException;
import java.io.File;
import javax.swing.JTextArea;

public class PrepareFiles{
		public static File input;
		public static File output;
		public static String outputFileName;
		public static String defaultDirectory  = "HJDefault";
		public static String filePath;
		public static JTextArea textArea;
		
		static {
			input = new File(setWorkFiles("CodeIn.txt").toString());
			output = new File(setWorkFiles("CodeOut.txt").toString());
			outputFileName = output.toString(); 
			filePath = Paths.get(System.getProperty("user.home"),"Desktop",defaultDirectory).toString();
		}
		
		public PrepareFiles(){
			/*This is not necessary but I wanted see it here*/
		}
		
		public static Path setWorkFiles(String outputFileName){
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
					newFile = Path.of(ioe.getMessage(),outputFileName);
				}catch(IOException e){
					System.out.println("Problems with file");
				}
			}catch(IOException e){
				System.out.println("Diferent Reason");
			}
		return newFile;
	}
	public static void writeFilesTextArea(){
		textArea.selectAll();
		textArea.replaceSelection("");
		textArea.append("Input File: "+"\n");
		textArea.append("  "+PrepareFiles.input.toString()+"\n");
		textArea.append("Output File: File: "+"\n");
		textArea.append("  "+PrepareFiles.output.toString()+"\n");
	}
}