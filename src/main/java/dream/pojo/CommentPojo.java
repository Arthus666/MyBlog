package dream.pojo;

public class CommentPojo {

    private int article_id;
    private int user_id;
    private long comment_time;
    private String user_comment;

    public CommentPojo() {
    }

    public CommentPojo(int article_id, int user_id, long comment_time, String user_comment) {
        this.article_id = article_id;
        this.user_id = user_id;
        this.comment_time = comment_time;
        this.user_comment = user_comment;
    }

    public int getArticle_id() {
        return article_id;
    }

    public void setArticle_id(int article_id) {
        this.article_id = article_id;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public long getComment_time() {
        return comment_time;
    }

    public void setComment_time(long comment_time) {
        this.comment_time = comment_time;
    }

    public String getUser_comment() {
        return user_comment;
    }

    public void setUser_comment(String user_comment) {
        this.user_comment = user_comment;
    }

    @Override
    public String toString() {
        return "CommentPojo{" +
                "article_id=" + article_id +
                ", user_id=" + user_id +
                ", comment_time=" + comment_time +
                ", user_comment='" + user_comment + '\'' +
                '}';
    }

}
