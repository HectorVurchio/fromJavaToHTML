package htmlCssFormat;
import java.text.MessageFormat;

public class PunctuationMarks{
	private char[] marks = {';',':',',','.','(',')','{','}','=','>','<','?','!','[',']'};
	private boolean singleQuotes;
	private boolean doubleQuotes;
	private char b,d;
	private String c;
	//single quoted words
	private String sqword;
	//double quoted words
	private String dqword;
	public PunctuationMarks(){
		this.singleQuotes = false;
		this.doubleQuotes = false;
		this.sqword = "";
		this.dqword = "";
	}
	
	String setMarks(String line){
		String newLine = "";
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
}