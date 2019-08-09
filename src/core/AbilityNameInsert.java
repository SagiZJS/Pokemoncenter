package core;

import java.io.IOException;

import crawler.AbilityNameFilter;
import crawler.Crawler;
import database.Insert;

public class AbilityNameInsert {
    public static void main(String[] args) {
        
        try {
            Crawler<String[]> skillsName = new Crawler<>("https://www.serebii.net/abilitydex/", new AbilityNameFilter());
            String[] data = skillsName.getData();
            Insert inserSkillsName = new Insert("ability", data.length, "abilityname");
            inserSkillsName.setOneColumnData(data);
            inserSkillsName.finishQuery();
            inserSkillsName.execute();
        } catch (IOException e) {
            e.printStackTrace();
        }
        
    }
}
