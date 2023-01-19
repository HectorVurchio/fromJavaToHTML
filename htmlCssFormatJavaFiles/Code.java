package htmlCssFormat;
import java.io.IOException;
import java.io.File;
// this app reads one line at the time
public class Code {
	private boolean comment = false;	
    public static void main(String[] args){
		try{
			new StartWithComments(new File("CodeIn.txt"),new File("CodeOut.txt")).startProgram();
		}catch(IOException e){
			System.out.println(e);
		}
		
    }
}

/*

C:\Users\Heclau\Desktop\htmlCssFormatJavaFiles

javac -d . htmlCssFormatJavaFiles/*.java

java htmlCssFormat.Code

*/
