package crawler;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegexUtils {
    private final Pattern pattern;
    
    private final Matcher matcher;
    
    private final String regex;
    
    public RegexUtils(String regex, String input) {
        pattern = Pattern.compile(regex);
        matcher = pattern.matcher(input);
        this.regex = regex;
    }
    
    public String getRegex() {
        return regex;
    }

    public String group(int i) {
        return matcher.group(i);
    }
    
    public String next() {
        if (matcher.find()) {
            return matcher.group();
        } else {
            return null;
        }
    }
    
    public String next(int i) {
        if (matcher.find()) {
            return matcher.group(i);
        } else {
            return null;
        }
    }
    public static void main(String[] args) {
        RegexUtils t = new RegexUtils("[^ \\t\\r\\n]", "a\t\r\n bAB");
        String res = null;
        while ((res = t.next()) != null) {
            System.out.println(res);
        }
                
        
    }
    
}
