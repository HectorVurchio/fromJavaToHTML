package htmlCssFormat;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.BufferedReader;
import java.io.PrintWriter;
import java.io.IOException;
import java.io.File;

public class StartWithComments{
	private BufferedReader inputStream;
	private PrintWriter outputStream;
	//1. if keepGoing == false, we are  still in a commented paragraph.
	private boolean keepGoing;
	private String line;
	private FileReader reader;
	private FileWriter writer;
	public StartWithComments(File reader, File writer) throws IOException{
		//System.out.println(String.format("\\u%04x", (int) '\''));
        this.inputStream = null;
        this.outputStream = null;
		this.keepGoing = true;
		this.reader = new FileReader(reader);
		this.writer = new FileWriter(writer);
	}
	
	public void startProgram()throws IOException{
		Comment comment = new Comment();
		try {
            inputStream = new BufferedReader(reader);
            outputStream = new PrintWriter(writer);
            while ((line = inputStream.readLine()) != null) {
				//0. are we behind the presense of a commented line?
				line = comment.checkComment(line);
				//1. if keepGoing == false, we are  still in a commented paragraph.
				if(comment.stillKeepGoing()){
					//2. set punctuation marks
					line = new PunctuationMarks().setMarks(line);
					//3. set reserved words
					line = new ReservedWords().setReserved(line);
				}
				//4. write the line down the output file
                outputStream.println(line);
				//5. we're still out from commented paragrap so, keep it this way
				comment.setKeepGoing(true);
            }
        } finally {
            if (inputStream != null) {
                inputStream.close();
            }
            if (outputStream != null) {
                outputStream.close();
            }
        }
	}
}