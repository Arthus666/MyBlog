package dream.pojo;

public class BlogPojo {

    private int article_id;
    private int id;
    private String first_title;
    private String second_title;
    private String tag;
    private long time;
    private String content;

    public BlogPojo() {
    }

    public BlogPojo(int article_id, int id, String first_title, String second_title, String tag, long time, String content) {
        this.article_id = article_id;
        this.id = id;
        this.first_title = first_title;
        this.second_title = second_title;
        this.tag = tag;
        this.time = time;
        this.content = content;
    }

    public int getArticle_id() {
        return article_id;
    }

    public void setArticle_id(int article_id) {
        this.article_id = article_id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirst_title() {
        return first_title;
    }

    public void setFirst_title(String first_title) {
        this.first_title = first_title;
    }

    public String getSecond_title() {
        return second_title;
    }

    public void setSecond_title(String second_title) {
        this.second_title = second_title;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public String toString() {
        return "BlogPojo{" +
                "article_id=" + article_id +
                ", id=" + id +
                ", first_title='" + first_title + '\'' +
                ", second_title='" + second_title + '\'' +
                ", tag='" + tag + '\'' +
                ", time=" + time +
                ", content='" + content + '\'' +
                '}';
    }
}
