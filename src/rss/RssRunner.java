package rss;

import java.sql.Connection;
import java.util.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.text.SimpleDateFormat;

public class RssRunner {

    public static void main(String[] args) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/ign_rss?useSSL=false",
                    "ign", "ignroot");

            PreparedStatement prepState =
                    conn.prepareStatement("INSERT INTO ign_rss.feed_entries" +
                            "(id,title, description, link, tags, category, pubDate) VALUES (DEFAULT,?,?,?,?,?,?)");

            RssFeedReader parser = new RssFeedReader(
                    "https://ign-apis.herokuapp.com/content/feed.rss");
            RssFeed feed = parser.readFeed();
            SimpleDateFormat sdf = new SimpleDateFormat("EEE, dd MMM YYYY hh:mm:ss +SSSS");
            Date d;
            java.sql.Date sDate;
            for (RssMessage message : feed.getEntries()) {

                prepState.setString(1,message.getTitle());
                prepState.setString(2,message.getDescription());
                prepState.setString(3, message.getLink());
                prepState.setString(4, message.getTags());
                prepState.setString(5, message.getCategory());
                d = sdf.parse(message.getPubDate());
                sDate = new java.sql.Date(d.getTime());
                prepState.setDate(6, sDate);
                prepState.addBatch();

            }
            prepState.executeBatch();
        } catch(Exception e) {
            e.printStackTrace();
        }

    }
}

