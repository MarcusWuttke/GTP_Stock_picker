package Forum;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PostParser {

    public PostParser(){

    }

    public Post parsePost(String raw, String postId){
        if (raw.contains("post-clamped-text")){
            return parseResponsePost(raw, postId);
        } else{
            return parseNormalPost(raw, postId);
        }
    }

    public List<String> getPostIdsFromPage(String raw){
        List<String> postIds = new ArrayList<>();
        String startSign = "post_message_";
        while (raw.contains(startSign)){
            int start = raw.indexOf(startSign) + startSign.length();
            int stop = raw.substring(start).indexOf("\"")+start;
            postIds.add(raw.substring(start, stop));
            raw = raw.substring(stop);
        }
        return postIds;
    }

    public int getNumberOfPagesInThread(String raw){
        String startSign = "data-total-pages=\"";
        String stopSign = "\"";
        String startOfNumber = raw.substring(raw.indexOf(startSign) + startSign.length());
        return Integer.parseInt(startOfNumber.substring(0, startOfNumber.indexOf(stopSign)));
    }

    private LocalDate getPostDate(String raw){
        String startSign = "<i class=\"fa fa-file\"></i>";
        int start = raw.indexOf(startSign) + startSign.length();
        int stop = raw.substring(start).indexOf(",")+start;
        String postDate = cleanTabAndNewLines(raw.substring(start, stop));

        if(postDate.startsWith("Idag")){
            return LocalDate.now();
        } else if(postDate.startsWith("Ig&ar")){
            return LocalDate.now().minusDays(1);
        }
        return LocalDate.parse(postDate);
    }

    private Post parseNormalPost(String raw, String postId){
        String startSign = "post_message_" + postId;
        int start = raw.indexOf(startSign) + startSign.length() + 2;
        int stop = raw.substring(start).indexOf("</div>")+start;

        return cleanStringAndCreatePost(postId, raw, raw, start, stop);
    }

    private Post parseResponsePost(String raw, String postId){
        String firstStartSign = "post-clamped-text";
        String secondStartSign = "</div></div></div>";
        String endSign = "</div>";
        int firstStartIndex = raw.indexOf(firstStartSign)+firstStartSign.length();
        String originalAndResponse = raw.substring(firstStartIndex);

        String clean = cleanTabAndNewLines(originalAndResponse);

        int secondStartIndex = clean.indexOf(secondStartSign)+secondStartSign.length();
        String response = clean.substring(secondStartIndex);
        int finalEndIndex = response.indexOf(endSign);

        return cleanStringAndCreatePost(postId, raw, response, 0, finalEndIndex);
    }

    private Post cleanStringAndCreatePost (String postId, String raw, String cutText, int start, int stop){
        String postContentIncStyling = cutText.substring(start, stop);
        postContentIncStyling = cleanTabAndNewLines(postContentIncStyling);
        postContentIncStyling = removeHtmlTags(postContentIncStyling);
        postContentIncStyling = replaceSpecialCharacters(postContentIncStyling);

        Post post = new Post(postId);
        post.setPostContent(postContentIncStyling);
        post.setPostTime(getPostDate(raw));

        return post;
    }

    private String cleanTabAndNewLines(String unclean){
        String cleanString = unclean;
        cleanString = cleanString.replace("\t", "");
        cleanString = cleanString.replace("<br />", "");
        return cleanString;
    }

    private String removeHtmlTags(String input) {
        // Define the regular expression pattern to match HTML tags
        Pattern pattern = Pattern.compile("<[^>]*>");
        Matcher matcher = pattern.matcher(input);

        // Replace HTML tags with an empty string
        String output = matcher.replaceAll("");

        return output;
    }

    private String replaceSpecialCharacters (String input){
        String output = input.replaceAll("&quot;", "\"");
        output = output.replaceAll("&amp;", ";");
        return output;
    }



}
