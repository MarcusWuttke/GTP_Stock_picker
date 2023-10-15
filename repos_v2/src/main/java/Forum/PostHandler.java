package Forum;

import Database.DatabaseConnector;

import java.util.ArrayList;
import java.util.List;


public class PostHandler {

    public PostHandler(){

    }

    public void printSinglePost (String postId) {
        Post post = getSinglePost(postId);
        System.out.println(post.getPostContent());
        System.out.println(post.getPostTime());
    }

    public void printAllPostInForumThread(String basePageId){
        ForumReader forumReader = new ForumReader(ForumType.FLASHBACK);
        for(int i = 1; i<4; i++){
            String pageId;

            pageId = i == 0 ? basePageId : basePageId + "p" + i;

            for (String postId : forumReader.getPostIdsFromForumPage(pageId)){
                Post post = forumReader.getTextFromPost(postId, ForumType.FLASHBACK);
                System.out.println("");
                System.out.println(post.getPostContent());
                System.out.println(post.getPostTime());
                System.out.println("Post-id: "+postId);
                try{
                    Thread.sleep(3000);
                } catch (Exception e){
                    System.out.println(e);
                }
            }
            i++;
        }
    }

    public void saveSinglePost(String postId, String ticker) throws Exception{
        DatabaseConnector databaseConnector = new DatabaseConnector();
        databaseConnector.saveSinglePost(getSinglePost(postId), ticker);
    }

    public List<String> saveAllPostsInForumThread(String basePageId, String ticker){
        ForumReader forumReader = new ForumReader(ForumType.FLASHBACK);
        int numberOfPagesInThread = forumReader.getNumberOfPagesInThread(basePageId);
        List<String> failedPostIds = new ArrayList<>();
        for(int i = 1; i<=numberOfPagesInThread-2; i++){
            System.out.println("Saving posts from page: "+i);
            String pageId;

            pageId = i == 1 ? basePageId : basePageId + "p" + i;

            for (String postId : forumReader.getPostIdsFromForumPage(pageId)){
                try{
                    saveSinglePost(postId, ticker);
                    System.out.println("Saved post with id: "+postId);
                    Thread.sleep(5000);
                } catch (Exception e){
                    System.out.println("Save didn't work, exception: ");
                    System.out.println(e);
                    System.out.println("Retrying to save post with id: "+postId);
                    try{
                        Thread.sleep(5000);
                        saveSinglePost(postId, ticker);
                    } catch (Exception ex){
                        System.out.println("Retry didn't work for post with id: " + postId + " exception was:");
                        System.out.println(e);
                        failedPostIds.add(postId);
                    }
                }
            }
            System.out.println("Done saving posts from page: "+i);
        }
        return failedPostIds;
    }

    private Post getSinglePost(String postId){
        ForumReader forumReader = new ForumReader(ForumType.FLASHBACK);
        return forumReader.getTextFromPost(postId, ForumType.FLASHBACK);
    }

}
