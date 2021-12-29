package dream.web;

import com.alibaba.fastjson.JSONObject;
import dream.dao.OperateDataDao;
import dream.dao.SelectDataDao;
import dream.pojo.UserPojo;
import dream.service.GetDatabaseInfo;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * 登录LoginServlet
 */
public class LoginServlet extends HttpServlet {

    public SelectDataDao selectDataDao;

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        //设置编码格式为UTF-8
        req.setCharacterEncoding("utf-8");
        resp.setCharacterEncoding("utf-8");
        resp.setContentType("text/html");

        //获取表单提交参数
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        String captcha_code = req.getParameter("captcha_code").toUpperCase();
        String token = (String) req.getSession().getAttribute("captcha");


        //获取SqlSession
        SqlSession sqlSession= GetDatabaseInfo.getSqlSession();

        //动态代理
        this.selectDataDao = sqlSession.getMapper(SelectDataDao.class);

        //使用json回传json对象
        JSONObject object = new JSONObject();

        //登录验证
        if (username.equals("") || password.equals("") || captcha_code.equals("")) {

            //放入json信息
            object.put("responseWord", "输入框不能为空！");
            object.put("consequence", 0);

        } else if (!token.toUpperCase().equals(captcha_code)) {

            req.getSession().removeAttribute("captcha");//删除当前验证码的值
            object.put("responseWord", "验证码错误！");
            object.put("consequence", 0);

        } else if (!username.matches("^[a-zA-Z0-9_]{3,16}$") && !username.matches("^\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*$")) {

            object.put("responseWord", "账户或密码错误！");
            object.put("consequence", 0);

        } else if (!password.matches("^[a-zA-Z0-9_]{6,16}$")) {

            object.put("responseWord", "账户或密码错误！");
            object.put("consequence", 0);

        } else {

            if (username.matches("^[a-zA-Z0-9_]{3,16}$")) {

                System.out.println("用户名登录");

                //用户名验证
                UserPojo userPojo = this.selectDataDao.selectUserByUsername(username);

                if (userPojo!=null&&password.equals(userPojo.getPassword())) {

                    String word=null;
                    try {
                        MessageDigest digest = MessageDigest.getInstance("md5");
                        digest.update(("fuckYou" + userPojo.getId()).getBytes());
                        word = new BigInteger(1, digest.digest()).toString(16);
                    } catch (NoSuchAlgorithmException e) {
                        e.printStackTrace();
                    }

                    Cookie cookie = new Cookie("word", word);
                    cookie.setMaxAge(60 * 60);
                    cookie.setHttpOnly(true);
                    resp.addCookie(cookie);

                    Cookie cookie1 = new Cookie("id", String.valueOf(userPojo.getId()));
                    cookie.setMaxAge(60 * 60);
                    cookie.setHttpOnly(true);
                    resp.addCookie(cookie1);

                    //放入json信息
                    object.put("responseWord", "登录成功！");
                    object.put("consequence", 1);

                } else {
                    object.put("responseWord", "账户或密码错误！");
                    object.put("consequence", 0);
                }
            } else {

                System.out.println("邮箱登录");

                //邮箱验证
                UserPojo userPojo = this.selectDataDao.selectUserByEmail(username);

                if (userPojo!=null&&password.equals(userPojo.getPassword())) {

                    String word=null;
                    try {
                        MessageDigest digest = MessageDigest.getInstance("md5");
                        digest.update(("fuckYou" + userPojo.getId()).getBytes());
                        word = new BigInteger(1, digest.digest()).toString(16);
                    } catch (NoSuchAlgorithmException e) {
                        e.printStackTrace();
                    }

                    Cookie cookie = new Cookie("word", word);
                    cookie.setMaxAge(60 * 60);
                    cookie.setHttpOnly(true);
                    resp.addCookie(cookie);

                    Cookie cookie1 = new Cookie("id", String.valueOf(userPojo.getId()));
                    cookie.setMaxAge(60 * 60);
                    cookie.setHttpOnly(true);
                    resp.addCookie(cookie1);

                    //放入json信息
                    object.put("responseWord", "登录成功！");
                    object.put("consequence", 1);

                } else {
                    object.put("responseWord", "账户或密码错误！");
                    object.put("consequence", 0);
                }
            }


        }

        //回传json对象
        resp.getWriter().print(object);

    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }
}
