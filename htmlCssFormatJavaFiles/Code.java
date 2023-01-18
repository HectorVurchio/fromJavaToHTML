package htmlCssFormat;
import java.io.IOException;
// this app reads one line at the time
public class Code {
	private boolean comment = false;	
    public static void main(String[] args){
		try{
			new StartWithComments().startProgram();
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
