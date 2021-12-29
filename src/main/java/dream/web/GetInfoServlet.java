package dream.web;

import com.alibaba.fastjson.JSONObject;
import com.sun.scenario.effect.impl.sw.sse.SSEBlend_SRC_OUTPeer;
import dream.dao.OperateDataDao;
import dream.dao.SelectDataDao;
import dream.pojo.BlogPojo;
import dream.pojo.CommentPojo;
import dream.pojo.UserPojo;
import dream.service.GetDatabaseInfo;
import org.apache.ibatis.session.SqlSession;
import org.omg.PortableInterceptor.INACTIVE;
import sun.applet.AppletAudioClip;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

public class GetInfoServlet extends HttpServlet {

    public SelectDataDao selectDataDao;

    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        //设置编码格式为UTF-8
        req.setCharacterEncoding("utf-8");
        resp.setCharacterEncoding("utf-8");
        resp.setContentType("text/html");

        String getInfo = req.getParameter("getInfo");
        String param = req.getParameter("param");

        if ("getUserNameById".equals(getInfo)) {

            UserPojo userPojo = getUserById(param);

            //使用json回传json对象
            JSONObject object = new JSONObject();

            if (userPojo != null) {

                //放入json信息
                object.put("getUsername", userPojo.getUsername());

            }

            //回传json对象
            resp.getWriter().print(object);

        } else if ("getBlogById".equals(getInfo)) {

            ArrayList<BlogPojo> blogs = getBlogById(param);

            //使用json回传json对象
            JSONObject object = new JSONObject();

            if (blogs != null) {

                //放入json信息
                object.put("getBlogs", blogs);

            }

            //回传json对象
            resp.getWriter().print(object);

        } else if ("getBlogByArticleId".equals(getInfo)) {

            BlogPojo blog = getBlogByArticleId(param);

            //使用json回传json对象
            JSONObject object = new JSONObject();

            if (blog != null) {

                //放入json信息
                object.put("getBlog", blog);

            }

            //回传json对象
            resp.getWriter().print(object);

        } else if ("getAllBlogs".equals(getInfo)) {

            ArrayList<BlogPojo> blogs = getAllBlogs(Integer.parseInt(param));

            //使用json回传json对象
            JSONObject object = new JSONObject();

            if (blogs != null) {

                //放入json信息
                object.put("getAllBlogs", blogs);

            }

            //回传json对象
            resp.getWriter().print(object);

        } else if ("getSearchBlogs".equals(getInfo)) {

            String search = "%" + param + "%";

            ArrayList<BlogPojo> blogs = getSearchBlogs(search);

            //使用json回传json对象
            JSONObject object = new JSONObject();

            if (param == "") {

                object.put("getSearchBlogs", null);

            } else if (blogs != null) {

                //放入json信息
                object.put("getSearchBlogs", blogs);

            }

            //回传json对象
            resp.getWriter().print(object);

        } else if ("getTagsById".equals(getInfo)) {

            int[] tags = getTagsById(param);

            //使用json回传json对象
            JSONObject object = new JSONObject();

            //放入json信息
            object.put("getTags", tags);

            //回传json对象
            resp.getWriter().print(object);

        } else if ("getCommentsByArticleId".equals(getInfo)) {

            ArrayList<CommentPojo> commentPojos = getCommentsByArticleId(param);

            //使用json回传json对象
            JSONObject object = new JSONObject();

            //放入json信息
            object.put("getComments", commentPojos);

            //回传json对象
            resp.getWriter().print(object);
        }

    }

    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    private UserPojo getUserById(String id) throws IOException {

        //获取Sqlsession
        SqlSession sqlSession = GetDatabaseInfo.getSqlSession();

        this.selectDataDao = sqlSession.getMapper(SelectDataDao.class);

        UserPojo userPojo = this.selectDataDao.selectUserById(id);

        sqlSession.close();

        return userPojo;

    }

    private ArrayList<BlogPojo> getBlogById(String id) throws IOException {

        //获取Sqlsession
        SqlSession sqlSession = GetDatabaseInfo.getSqlSession();

        this.selectDataDao = sqlSession.getMapper(SelectDataDao.class);

        ArrayList<BlogPojo> blogs = this.selectDataDao.selectBlogById(id);

        sqlSession.close();

        return blogs;
    }

    private BlogPojo getBlogByArticleId(String article_id) throws IOException {

        //获取Sqlsession
        SqlSession sqlSession = GetDatabaseInfo.getSqlSession();

        this.selectDataDao = sqlSession.getMapper(SelectDataDao.class);

        BlogPojo blog = this.selectDataDao.selectBlogByArticleId(article_id);

        sqlSession.close();

        return blog;
    }

    private ArrayList<BlogPojo> getAllBlogs(int index) throws IOException {

        //获取Sqlsession
        SqlSession sqlSession = GetDatabaseInfo.getSqlSession();

        this.selectDataDao = sqlSession.getMapper(SelectDataDao.class);

        ArrayList<BlogPojo> blogs = this.selectDataDao.selectAllBlogs(index);

        sqlSession.close();

        return blogs;
    }

    private ArrayList<BlogPojo> getSearchBlogs(String search) throws IOException {

        //获取Sqlsession
        SqlSession sqlSession = GetDatabaseInfo.getSqlSession();

        this.selectDataDao = sqlSession.getMapper(SelectDataDao.class);

        ArrayList<BlogPojo> blogs = this.selectDataDao.selectBlogsBySearch(search);

        sqlSession.close();

        return blogs;
    }

    private int[] getTagsById(String id) throws IOException {

        //获取Sqlsession
        SqlSession sqlSession = GetDatabaseInfo.getSqlSession();

        //每次执行完后需要重新获取Mapper映射
        this.selectDataDao = sqlSession.getMapper(SelectDataDao.class);
        int c = this.selectDataDao.selectTag_CByUserId(id);

        this.selectDataDao = sqlSession.getMapper(SelectDataDao.class);
        int cpp = this.selectDataDao.selectTag_CppByUserId(id);

        this.selectDataDao = sqlSession.getMapper(SelectDataDao.class);
        int java = this.selectDataDao.selectTag_JavaByUserId(id);

        this.selectDataDao = sqlSession.getMapper(SelectDataDao.class);
        int python = this.selectDataDao.selectTag_PythonByUserId(id);

        int[] tags = new int[4];

        tags[0] = c;
        tags[1] = cpp;
        tags[2] = java;
        tags[3] = python;

        sqlSession.close();

        return tags;
    }

    private ArrayList<CommentPojo> getCommentsByArticleId(String article_id) throws IOException {

        //获取Sqlsession
        SqlSession sqlSession = GetDatabaseInfo.getSqlSession();

        this.selectDataDao = sqlSession.getMapper(SelectDataDao.class);

        ArrayList<CommentPojo> commentPojos = this.selectDataDao.selectCommentsByArticleId(article_id);

        sqlSession.close();

        return commentPojos;
    }

}
