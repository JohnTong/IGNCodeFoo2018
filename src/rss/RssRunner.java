package rss;

public class RssRunner {

    public static void main(String[] args) {
        RssFeedReader parser = new RssFeedReader(
                "https://ign-apis.herokuapp.com/content/feed.rss");
        RssFeed feed = parser.readFeed();
        System.out.println(feed);
        for (RssMessage message : feed.getEntries()) {
            System.out.println(message);

        }

    }
}

