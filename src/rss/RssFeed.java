package rss;

import java.util.ArrayList;
import java.util.List;

public class RssFeed {

    final String title;
    final String description;
    final String link;
    final String copyright;
    final String language;

    final List<RssMessage> entries = new ArrayList<>();

    public RssFeed(String title, String description, String link, String language, String copyright) {
        this.title = title;
        this.description = description;
        this.link = link;
        this.language = language;
        this.copyright = copyright;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public String getLink() {
        return link;
    }

    public String getCopyright() {
        return copyright;
    }

    public List<RssMessage> getEntries() {
        return entries;
    }

    @Override
    public String toString() {
        return "Feed: [title = " + title + " description = " + description + " link = " + link + " copyright = " +
                copyright + "]";
    }
}
