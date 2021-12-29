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
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;

/**
 * 注册LogonServlet
 */
public class LogonServlet extends HttpServlet {

    public OperateDataDao operateDataDao;

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
        String confirm = req.getParameter("confirm");
        String email = req.getParameter("email");
        String captcha_code = req.getParameter("captcha_code");
        String token = (String) req.getSession().getAttribute("captcha");

        //获取Sqlsession
        SqlSession sqlSession= GetDatabaseInfo.getSqlSession();

        //动态代理
        this.operateDataDao = sqlSession.getMapper(OperateDataDao.class);
        this.selectDataDao=sqlSession.getMapper(SelectDataDao.class);

        //使用json回传json对象
        JSONObject object = new JSONObject();

        //判断输入数据是否合法
        if (username.equals("") || password.equals("") || confirm.equals("") || email.equals("") || captcha_code.equals("")) {

            //放入json信息
            object.put("responseWord", "输入框不能为空！");
            object.put("consequence", 0);

        } else if (username.matches("^[a-zA-Z0-9_]{3,16}$") == false) {

            object.put("responseWord", "账户为英文字母、数字、下划线、长度3~16位！");
            object.put("consequence", 0);

        } else if (password.matches("^[a-zA-Z0-9_]{6,16}$") == false) {

            object.put("responseWord", "密码为英文字母、数字、下划线长度6~16位！！");
            object.put("consequence", 0);

        } else if (password.equals(confirm)==false) {

            object.put("responseWord", "确认密码不一致！");
            object.put("consequence", 0);

        } else if (email.matches("^\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*$")==false) {

            object.put("responseWord", "邮箱格式错误！");
            object.put("consequence", 0);

        }else if (token.toUpperCase().equals(captcha_code) == false) {

            req.getSession().removeAttribute("captcha");//删除当前验证码的值
            object.put("responseWord", "验证码错误！");
            object.put("consequence", 0);

        } else if (this.selectDataDao.selectUserByUsername(username) != null) {

            object.put("responseWord", "该账户已存在！");
            object.put("consequence", 0);

        } else if (this.selectDataDao.selectUserByEmail(email)!=null) {

            object.put("responseWord", "该邮箱已被注册！");
            object.put("consequence", 0);

        } else {

            //保存报名用户到数据库
            operateDataDao.insertUserPojo(new UserPojo(0, username, password, email, (byte) 0));

            //放入json信息
            object.put("responseWord", "注册成功！");
            object.put("consequence", 1);

        }

        //回传json对象
        resp.getWriter().print(object);
        sqlSession.close();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }
}
