package mars.bookstore.mapper;

import org.apache.ibatis.annotations.Param;

import mars.bookstore.base.BaseMapper;
import mars.bookstore.enty.User;

public interface UserMapper extends BaseMapper<User>{

	User getUserByUserName(@Param("userName")String userName);

}
