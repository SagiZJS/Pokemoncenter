package crawler;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class AbilityNameFilter implements CrawlerFilter<String[]> {

    @Override
    public String[] filterRule(String rawData) {

        List<String> output = new ArrayList<>();
        Pattern pattern = Pattern.compile("<option value=\".+\">(.+)</option>");
        Matcher matcher = pattern.matcher(rawData);
        while (matcher.find()) {
            if (!output.contains(matcher.group(1))) {
                output.add(matcher.group(1));
            }
        }

        return output.toArray(new String[0]);
    }

}
