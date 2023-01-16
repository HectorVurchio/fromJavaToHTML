import java.io.FileReader;
import java.io.FileWriter;
import java.io.BufferedReader;
import java.io.PrintWriter;
import java.io.IOException;
import java.text.MessageFormat;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Stream;
import java.util.Map;
import java.util.HashMap;
import java.util.Iterator;
// this app reads one line at the time

public class Code {
	private char[] marks = {';',':',',','.','(',')','{','}','=','>','<','?','[',']'};
	private String[] reservedW = {"true","false","new","null","throws","import","while","switch","case","return","for","if","else","finally"};
	private String[] reserved = {"package","Public","private","class","private","int","char","boolean"};
	private boolean comment = false;
	private boolean keepGoing = true;
	
	private String punctuacionMarks(String line){
		String c;
		String newLine = "";
		char d;
		for(int i = 0; i < line.length(); i++){
			d = line.charAt(i);
			c = String.valueOf(d);
			//search for punctuation mark
			for (char elm : marks) {
				// is punctuation mark
				if (d == elm) { 
					c = "<span class=\"marks\">"+c+"</span>";
					break;
				}
			}
			newLine += c;
		}
		return "<code>"+newLine+"</code>";
	}
	private String getReservedW(){
		String str = "";
		for(int i = 0; i<reservedW.length; i++){
			str += MessageFormat.format("\\b{0}\\b[^(\"{0}\")]", reservedW[i]);
			str += (i == reservedW.length - 1) ? "" : "|";
		}
		return str;
	}
	private String reservedwords(String line){
		String pattern = getReservedW();
		Pattern r = Pattern.compile(pattern);
		Matcher m = r.matcher(line);
		Map<String,String> replacement = new HashMap<String,String>();
		Stream<String> mstream = m.results().map(mr -> mr.group());
		mstream.forEach(k -> {
			final String suspicious = "<";
			String lastChar = String.valueOf(k.charAt(k.length()-1));
			if(suspicious.compareTo(lastChar) == 0){
				replacement.put(k,MessageFormat.format("<span class=\"reserved\">{0}</span><", k.substring(0,k.length()-1)));
			}else{
				replacement.put(k,MessageFormat.format("<span class=\"reserved\">{0}</span>", k));
			}
		});
		System.out.println(replacement);
		Iterator<String> it = replacement.keySet().iterator();
		String keyFromMap = "";
		String change = "";
		while(it.hasNext()){
			keyFromMap = it.next();
			change = MessageFormat.format(keyFromMap, replacement.get(keyFromMap));
			//line = m.replaceAll(change);
			line = line.replaceAll(keyFromMap, replacement.get(keyFromMap));
			//System.out.println(keyFromMap+"  "+replacement.get(keyFromMap));
		}
		return line;
	}

	private String isComment(String line){
		//begin a comented paragraph
		if(line.strip().startsWith("/*")){
			comment = true;
		}
		//is a commented line
		if(line.strip().startsWith("//") || comment){ 
			//begin a comented paragraph
			if(line.strip().endsWith("*/")){
				comment = false;
			}
			keepGoing = false;
			return "<code><span class=\"comment\">"+line+"</span></code>";
		}
		keepGoing = true;
		return line;
	}
	
    public static void main(String[] args) throws IOException {
        BufferedReader inputStream = null;
        PrintWriter outputStream = null;
		String line;
		Code code = new Code();
        try {
            inputStream = new BufferedReader(new FileReader("CodeIn.txt"));
            outputStream = new PrintWriter(new FileWriter("CodeOut.txt"));
            while ((line = inputStream.readLine()) != null) {
				line = code.isComment(line);
				if(code.keepGoing){
					line = code.punctuacionMarks(line);
					line = code.reservedwords(line);
				}
                outputStream.println(line);
				code.keepGoing = true;
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