package test;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;

import crawler.AbilityNameFilter;
import crawler.Crawler;
import crawler.CrawlerFilter;
import crawler.PokemonFilter;
import crawler.SkillsNameFilter;

public class PokemonFilterTest {
    public static void main(String[] args) {
        for (int i = 105; i < 106; i++) {

            CrawlerFilter<String[]> filter = new PokemonFilter();
            try (BufferedWriter writer = new BufferedWriter(
                    new FileWriter("C:\\Users\\Sagi\\Desktop\\ass\\db\\my\\pokemoncenter\\"+intToString(i)+".html"))) {
                Crawler<String[]> pokemon = new Crawler<>("https://www.serebii.net/pokedex-sm/"+intToString(i)+".shtml", filter);
//            System.out.println(skill.getRawData());
                writer.write(pokemon.getRawData());
                pokemon.getData();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    private static String intToString(int i) {
        if (i < 10) {
            return "00"+i;
        }
        if (i < 100) {
            return "0"+i;
        }
        return ""+i;
    }
}
