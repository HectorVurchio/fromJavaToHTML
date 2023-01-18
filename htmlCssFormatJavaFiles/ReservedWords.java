package htmlCssFormat;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Stream;
import java.util.Map;
import java.util.HashMap;
import java.util.Iterator;
import java.text.MessageFormat;

public class ReservedWords{
	private String[] reserved = {"true","false","new","null","throws","import","while","switch","case","break","return","for","if","else","finally"};
	private String[] imported = {"class","package","public","private","private","int","char","boolean"};
	private String[][] allReserved = {reserved,imported};
	private String[] classNames = {"reserved","orange"};
	String pattern;
	Pattern r;
	Matcher m;
	Map<String,String> replacement;
	Stream<String> mstream;
	ReservedWords(){
		this.replacement = new HashMap<String,String>();
	}
	
	private String getClassName(int index, String name){
		String response = "";
		String[] suspicious = {"<","="};
		String lastChar = String.valueOf(name.charAt(name.length()-1));
		if(suspicious[0].compareTo(lastChar) == 0){
			response = MessageFormat.format("<span class=\"{0}\">{1}</span><", classNames[index],name.substring(0,name.length()-1));
		}else if(suspicious[1].compareTo(lastChar) == 0){
			response = MessageFormat.format("{0}",name);
		}else{
			response = MessageFormat.format("<span class=\"{0}\">{1}</span>", classNames[index],name);
		}
		return response;
	}
	
	private String getReservedW(String[] arrRes){
		String str = "";
		for(int i = 0; i<arrRes.length; i++){
			str += MessageFormat.format("\\b{0}\\b[^(\"{0}\")]", arrRes[i]);
			str += (i == arrRes.length - 1) ? "" : "|";
		}
		return str;
	}
	public String setReserved(String line){
		int[] index = {0};
		for(String[] resElm : allReserved){
			pattern = getReservedW(resElm);
			r = Pattern.compile(pattern);
			m = r.matcher(line);
			mstream = m.results().map(mr -> mr.group());
			mstream.forEach(k -> {
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
		return line;
	}
}