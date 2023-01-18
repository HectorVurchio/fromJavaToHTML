package htmlCssFormat;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Stream;
import java.util.Map;
import java.util.HashMap;
import java.util.Iterator;
import java.text.MessageFormat;
public class Comment{
	private boolean comment;
	private boolean keepGoing;
	public Comment(){
		this.comment = false;
		this.keepGoing = true;
	}
	public boolean stillKeepGoing(){
		return this.keepGoing;
	}
	public void setKeepGoing(boolean keepGoing){
		this.keepGoing = keepGoing;
	}
	public String checkComment(String line){
		//begin a comented paragraph
		if(line.strip().startsWith("/*")){
			comment = true;
		}
		//is a commented line
		if(line.strip().startsWith("//")){ 
			keepGoing = false;
			return MessageFormat.format("<code><span class=\"comment\">{0}</span></code>", line);
		}
		if(comment){
			Map<String,String> replacement = new HashMap<String,String>();
			String pattern = "@\\w+";
			Pattern r = Pattern.compile(pattern);
			Matcher m = r.matcher(line);
			Stream<String> mstream = m.results().map(mr -> mr.group());
			mstream.forEach(k -> {
				replacement.put(k,MessageFormat.format("<span class=\"ets\">{0}</span>",k));
			});
			if(line.strip().endsWith("*/")){
				comment = false;
			}
			Iterator<String> it = replacement.keySet().iterator();
			String keyFromMap = "";
			while(it.hasNext()){
				keyFromMap = it.next();
				line = line.replaceAll(keyFromMap, replacement.get(keyFromMap));
			}
			keepGoing = false;
			return MessageFormat.format("<code><span class=\"comment\">{0}</span></code>", line);
		}
		keepGoing = true;
		return line;
	}
}