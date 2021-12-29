package dream.web;

import com.alibaba.fastjson.JSONObject;
import dream.dao.OperateDataDao;
import dream.dao.SelectDataDao;
import dream.pojo.BlogPojo;
import dream.pojo.CommentPojo;
import dream.pojo.UserPojo;
import dream.service.GetDatabaseInfo;
import org.apache.ibatis.session.SqlSession;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;

public class OperateInfoServlet extends HttpServlet {

    public OperateDataDao operateDataDao;

    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        //设置编码格式为UTF-8
        req.setCharacterEncoding("utf-8");
        resp.setCharacterEncoding("utf-8");
        resp.setContentType("text/html");

        String operateInfo = req.getParameter("operateInfo");

        if ("insertBlog".equals(operateInfo)) {

            String id = req.getParameter("id");
            String fist_title = req.getParameter("first_title_Input");
            String second_title = req.getParameter("second_title_Input");
            String tag = req.getParameter("tag_Input");
            String content = req.getParameter("content");

            BlogPojo blogPojo = new BlogPojo(0, Integer.parseInt(id), fist_title,second_title,tag, new Date().getTime(), content);

            //使用json回传json对象
            JSONObject object = new JSONObject();

            if ("".equals(fist_title) ||"".equals(second_title)||"".equals(tag)|| "".equals(content)) {

                //放入json信息
                object.put("consequence", "标题、标签和内容不能为空！");

                //回传json对象
                resp.getWriter().print(object);

            } else if (fist_title.length()>16||second_title.length()>16||tag.length()>16) {

                //放入json信息
                object.put("consequence", "标题和标签最大长度为16！");

                //回传json对象
                resp.getWriter().print(object);

            } else {

                try {

                    insertBlog(blogPojo);

                } catch (Exception e) {

                    //放入json信息
                    object.put("consequence", "发表失败！");

                    //回传json对象
                    resp.getWriter().print(object);
                    return;
                }

                //放入json信息
                object.put("consequence", "发表成功！");

                //回传json对象
                resp.getWriter().print(object);
            }

        } else if ("insertComment".equals(operateInfo)) {

            //使用json回传json对象
            JSONObject object = new JSONObject();

            String article_id = req.getParameter("article_id");
            String userId = req.getParameter("userId");
            String comment_contents = req.getParameter("comment_contents");

            CommentPojo commentPojo=new CommentPojo(Integer.parseInt(article_id),Integer.parseInt(userId),new Date().getTime(),comment_contents);

            if (comment_contents.length()>255) {

                //放入json信息
                object.put("consequence", "评论内容最大长度为255个字符！");

                //回传json对象
                resp.getWriter().print(object);

            } else {
                try {
                    insertComment(commentPojo);
                } catch (Exception e) {

                    //放入json信息
                    object.put("consequence", "发表失败！");

                    //回传json对象
                    resp.getWriter().print(object);
                    return;
                }
                //放入json信息
                object.put("consequence", "发表成功！");

                //回传json对象
                resp.getWriter().print(object);
            }

        }

    }

    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    private void insertBlog(BlogPojo pojo) throws IOException {

        //获取Sqlsession
        SqlSession sqlSession = GetDatabaseInfo.getSqlSession();

        this.operateDataDao = sqlSession.getMapper(OperateDataDao.class);

        this.operateDataDao.insertBlogPojo(pojo);

        sqlSession.close();

    }

    private void insertComment(CommentPojo pojo) throws IOException {

        //获取Sqlsession
        SqlSession sqlSession = GetDatabaseInfo.getSqlSession();

        this.operateDataDao = sqlSession.getMapper(OperateDataDao.class);

        this.operateDataDao.insertCommentPojo(pojo);

        sqlSession.close();

    }
}
