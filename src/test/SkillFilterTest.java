package test;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;

import crawler.Crawler;
import crawler.CrawlerFilter;
import crawler.SkillsNameFilter;

public class SkillFilterTest {
    public static void main(String[] args) {
        CrawlerFilter<String[]> filter = new SkillsNameFilter();
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("C:\\Users\\Sagi\\Desktop\\ass\\db\\my\\pokemoncenter\\001.txt"))){
            Crawler<String[]> skill = new Crawler<>("https://www.serebii.net/attackdex-sm/", filter);
            System.out.println(skill.getRawData());
            writer.write(skill.getRawData());
            System.out.println(skill.getData());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
