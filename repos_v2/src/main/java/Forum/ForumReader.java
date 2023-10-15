package Forum;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.List;

public class ForumReader {

    private ForumType forumType;
    private PostParser postParser;

    public ForumReader (ForumType forumType){
        this.forumType = forumType;
        this.postParser = new PostParser();
    }

    public List<String> getPostIdsFromForumPage(String forumPageId){
        StringBuffer content = getConnectionToFlashBack("https://www.flashback.org/"+forumPageId);
        return postParser.getPostIdsFromPage(content.toString());
    }

    public int getNumberOfPagesInThread(String forumPageId){
        StringBuffer content = getConnectionToFlashBack("https://www.flashback.org/"+forumPageId);
        return postParser.getNumberOfPagesInThread(content.toString());
    }

    public Post getTextFromPost(String postId, ForumType forumType){
        StringBuffer content = getConnectionToFlashBack("https://www.flashback.org/sp"+postId);
        return postParser.parsePost(content.toString(), postId);
    }

    private StringBuffer getConnectionToFlashBack(String urlString){
        URL url;
        HttpURLConnection con;
        StringBuffer content = new StringBuffer();

        try {
            url = new URL(urlString);
            con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("GET");
            BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream(), StandardCharsets.ISO_8859_1));
            String inputLine;
            while ((inputLine = in.readLine()) != null) {
                content.append(inputLine);
            }
            in.close();
        } catch (Exception e){
            System.out.println(e);
        }

        return content;
    }
}
