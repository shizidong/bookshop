package crh.mars.study.mapper;

import crh.mars.study.base.BaseMapper;
import crh.mars.study.enty.Resource;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface ResourceMapper extends BaseMapper<Resource>{
    List<Resource> queryAllForLevel(@Param("res_type")int res_type);
    void deleteRes1(@Param("resourceId")String resourceId);
}
