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
	private char[] marks = {';',':',',','.','(',')','{','}','=','>','<','?','!','[',']'};
	private String[] reserved = {"true","false","new","null","throws","import","while","switch","case","return","for","if","else","finally"};
	private String[] imported = {"package","public","private","private","int","char","boolean"};
	private String[][] allReserved = {reserved,imported};
	private String[] classNames = {"reserved","orange"};
	private boolean comment = false;
	private boolean keepGoing = true;
	

	private String punctuacionMarks(String line){
		String c;
		String newLine = "";
		char b,d;
		boolean singleQuotes = false;
		boolean doubleQuotes = false;
		String sqword = "";
		String dqword = "";
		for(int i = 0; i < line.length(); i++){
			b = (i > 0) ? line.charAt(i-1) : 'a';
			d = line.charAt(i);
			c = String.valueOf(d);
			//Find single quoted
			if(Character.compare('\'',d) == 0){
				if(singleQuotes){
					if(Character.compare('\u002f',b) == 0){
						sqword += "&apos;";
						continue;
					}else{
						singleQuotes = false;
						//write the last character
						sqword += c;
						//System.out.println(sqword);
						newLine += MessageFormat.format("<span class=\"singlequoted\">{0}</span>", sqword);
						continue;
					}
					
				}else{
					singleQuotes = true;
					//reset sqword
					sqword = "";
				}
			}else if(Character.compare('\u0022',d) == 0){
				//find Doublequoted
				if(doubleQuotes){
					if(Character.compare('\\',b) == 0){
						dqword += "&quot;";
						continue;
					}else{
						doubleQuotes = false;
						dqword += c;
						newLine += MessageFormat.format("<span class=\"doublequoted\">{0}</span>", dqword);
						continue;
					}
				}else{
					if(!singleQuotes){
						doubleQuotes = true;
						//reset dqword
						dqword = "";
					}
					
				}
			}
			if(singleQuotes){
				//building single quote format
				switch(c){
					case "<":
						c = "&lt;";
						break;
					case ">":
						c = "&gt;";
						break;
					case "\'":
						c = "&apos;";
						break;
					case "\"":
						c = "&quot;";
						break;
					case "\\":
					System.out.println(c);
						c = "&bsol;";
						break;
				}
				sqword += c;
			}else if(doubleQuotes){
				//building double quote format
				switch(c){
					case "<":
						c = "&lt;";
						break;
					case ">":
						c = "&gt;";
						break;
					case "\'":
						c = "&apos;";
						break;
					case "\"":
						c = "&quot;";
						break;
				}
				dqword += c;
			}else{
				//search for punctuation mark
				for (char elm : marks) {
					// is punctuation mark
					if (d == elm) { 
						c = MessageFormat.format("<span class=\"marks\">{0}</span>", c);
						break;
					}
				}
				newLine += c;
			}	
		}
		return MessageFormat.format("<code>{0}</code>", newLine);
	}
	private String getReservedW(String[] arrRes){
		String str = "";
		for(int i = 0; i<arrRes.length; i++){
			str += MessageFormat.format("\\b{0}\\b[^(\"{0}\")]", arrRes[i]);
			str += (i == arrRes.length - 1) ? "" : "|";
		}
		return str;
	}
	private String getClassName(int index, String name){
		String response = "";
		String[] suspicious = {"<","="};
		String lastChar = String.valueOf(name.charAt(name.length()-1));
		if(suspicious[0].compareTo(lastChar) == 0){
			response = MessageFormat.format("<span class=\"{0}\">{1}</span><", classNames[index],name.substring(0,name.length()-1));
		}else{
			response = MessageFormat.format("<span class=\"{0}\">{1}</span>", classNames[index],name);
		}
		return response;
	}
	
	private String reservedwords(String line){
		Map<String,String> replacement = new HashMap<String,String>();
		String pattern;
		Pattern r;
		Matcher m;
		Stream<String> mstream;
		int[] index = {0};
		for(String[] resElm : allReserved){
			pattern = getReservedW(resElm);
			r = Pattern.compile(pattern);
			m = r.matcher(line);
			mstream = m.results().map(mr -> mr.group());
			//System.out.println(line);
			mstream.forEach(k -> {
				//System.out.println(k);
				replacement.put(k,getClassName(index[0], k));
			});
			index[0]++;
		}
		Iterator<String> it = replacement.keySet().iterator();
		String keyFromMap = "";
		String change = "";
		while(it.hasNext()){
			keyFromMap = it.next();
			change = MessageFormat.format(keyFromMap, replacement.get(keyFromMap));
			line = line.replaceAll(keyFromMap, replacement.get(keyFromMap));
		}
		//System.out.println(replacement);
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
			return MessageFormat.format("<code><span class=\"comment\">{0}</span></code>", line);
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