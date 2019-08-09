package core;

import java.io.IOException;

import crawler.Crawler;
import crawler.SkillsNameFilter;
import database.Insert;

public class SkillNameInsert {
    public static void main(String[] args) {
        
        try {
            Crawler<String[]> skillsName = new Crawler<>("https://www.serebii.net/attackdex-sm/", new SkillsNameFilter());
            String[] data = skillsName.getData();
            Insert inserSkillsName = new Insert("skill", data.length, "skillname");
            inserSkillsName.setOneColumnData(data);
            inserSkillsName.finishQuery();
            inserSkillsName.execute();
        } catch (IOException e) {
            e.printStackTrace();
        }
        
    }
}
