package dream.dao;

import dream.pojo.BlogPojo;
import dream.pojo.CommentPojo;
import dream.pojo.UserPojo;

import java.util.ArrayList;

public interface SelectDataDao {

    public UserPojo selectUserById(String id);

    public UserPojo selectUserByUsername(String username);

    public UserPojo selectUserByEmail(String email);

    public ArrayList<BlogPojo> selectBlogById(String id);

    public BlogPojo selectBlogByArticleId(String articleId);

    public ArrayList<BlogPojo> selectAllBlogs(int index);

    public ArrayList<BlogPojo> selectBlogsBySearch(String search);

    public int selectTag_CByUserId(String id);

    public int selectTag_CppByUserId(String id);

    public int selectTag_JavaByUserId(String id);

    public int selectTag_PythonByUserId(String id);

    public ArrayList<CommentPojo> selectCommentsByArticleId(String article_id);

}
