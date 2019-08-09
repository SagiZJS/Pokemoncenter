package crawler;

public class PokemonFilter implements CrawlerFilter<String[]> {

    @Override
    public String[] filterRule(String rawData) {
        basicInfoFilt(rawData);
        return null;
    }

    private String[] basicInfoFilt(String rawData) {
        String name = "<table class=\"dextable\">[\\w\\W]+?(Name)<.*?>[\\w\\W]+?Capture Rate[\\w\\W]+?</table>";

        RegexUtils basicInfoBlock = new RegexUtils(name, rawData);
        String rawDexData = null;
        if ((rawDexData = basicInfoBlock.next()) != null) {
                System.out.println(rawDexData);
            dex1Filt(rawDexData);
        }
//        if ((rawDexData = basicInfoBlock.next()) != null) {
//                System.out.println(rawDexData);
//            dex2Filt(rawDexData);
//        }
        return null;
    }
    
    private String dex2Filt(String rawData) {
        return null;
    }

    private String dex1Filt(String rawData) {
        boolean alola = false;
        String tr1 = "<tr>([\\w\\W]+?)</tr>";
        RegexUtils tr1Block = new RegexUtils(tr1, rawData);
        String tr1Data = null;
        
        String name = null;
        String type1 = null;
        String type2 = null;
        String classification = null;
        String height = null;
        String weight = null;
        int captureRate = 0;
        int steps = 0;
        String genderRation = null;
        
        int count = 0;
//        if ((tr1Data = tr1Block.next()) != null) {
//            System.out.println(tr1Data);
//            System.out.println("------------------------");
        RegexUtils nameRegex = new RegexUtils("<td class.+?>([\\w\\W]+?)</td>", rawData);
        while ((tr1Data = nameRegex.next(1)) != null) {
            // 9 name
            // 12 gender ratio
            // 13 type
            // 19 classification
            // 20 height
            // 21 weight
            // 22 capture rate
            // 23 steps
            count++;
            System.out.println(""+count+":"+tr1Data);
            switch (count) {
            case 9:
                name = tr1Data;
//                System.out.println("name: "+name);
                break;
            case 13:
                RegexUtils type = new RegexUtils("alt=\"(\\w+)-type\"", tr1Data);
                type1 = type.next(1);
                type2 = type.next(1);
//                System.out.println("type: "+type1+" "+type2);
                break;
            case 19:
                RegexUtils classificationRegex = new RegexUtils("(\\w+) ", tr1Data);
                classification = classificationRegex.next()+"Pokemon";
                break;
            case 20:
                RegexUtils heightRegex = new RegexUtils("br />[ \\t\\r\\n]+([\\d.]+m)", tr1Data);
                height = heightRegex.next(1);
                break;
            case 21:
                RegexUtils weightRegex = new RegexUtils("br />[ \\t\\r\\n]+([\\d.]+kg)", tr1Data);
                weight = weightRegex.next(1);
                break;
            case 22:
                captureRate = Integer.valueOf(tr1Data);
                break;
            case 23:
                steps = Integer.valueOf(tr1Data.replaceAll(",", ""));
                break;
            default:
                break;
            }
        }
        RegexUtils genderBlock = new RegexUtils("Male[\\w\\W]+?<td>([\\d.]+)%</td>[\\w\\W]+?<td>([\\d.]+)%</td></tr></table>", rawData);
        genderRation = "N/A";
        if (genderBlock.next() != null) {
            int male = (int) Math.round(Double.valueOf(genderBlock.group(1)));
            int female = (int) Math.round(Double.valueOf(genderBlock.group(2)));
            genderRation = ""+male+":"+female;
        }
        System.out.println(String.format("name: %s type: %s, %s gender ration: %s class: %s height: %s weight: %s cap.rate: %s steps: %s\n", 
                name, type1, type2, genderRation, classification, height, weight, captureRate, steps));
//        }
//        String tr2 = "<tr>(([\\w\\W]+?)(type)([\\w\\W]+?))</tr>";
//        RegexUtils tr2Block = new RegexUtils(tr2, rawData);
//        String tr2Data = null;
//        if ((tr2Data = tr2Block.next()) != null) {
//            System.out.println(tr2Data);
//        }
        return null;
    }

    private String nameFilt(String rawData) {
        return null;
    }

}
