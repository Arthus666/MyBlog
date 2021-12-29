package dream.dao;

import dream.pojo.*;

public interface OperateDataDao {

    public int insertUserPojo(UserPojo pojo);

    public void insertBlogPojo(BlogPojo pojo);

    public void insertCommentPojo(CommentPojo pojo);
}
