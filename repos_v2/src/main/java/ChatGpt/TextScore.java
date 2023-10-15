package ChatGpt;

public class TextScore {

    private String postId;
    private int relevanceScore;
    private int positivityScore;

    // Getter for postId
    public String getPostId() {
        return postId;
    }

    // Setter for postId
    public void setPostId(String postId) {
        this.postId = postId;
    }

    // Getter for relevanceScore
    public int getRelevanceScore() {
        return relevanceScore;
    }

    // Setter for relevanceScore
    public void setRelevanceScore(int relevanceScore) {
        this.relevanceScore = relevanceScore;
    }

    // Getter for positivityScore
    public int getPositivityScore() {
        return positivityScore;
    }

    // Setter for positivityScore
    public void setPositivityScore(int positivityScore) {
        this.positivityScore = positivityScore;
    }

}
