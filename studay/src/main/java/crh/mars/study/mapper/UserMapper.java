package crh.mars.study.mapper;

import org.apache.ibatis.annotations.Param;

import crh.mars.study.base.BaseMapper;
import crh.mars.study.enty.User;


public interface UserMapper extends BaseMapper<User>{

	User getUserByUserName(@Param("userName")String userName);

}
