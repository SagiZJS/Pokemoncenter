package crawler;

public interface CrawlerFilter<T> {
     T filterRule(String rawData);
}
