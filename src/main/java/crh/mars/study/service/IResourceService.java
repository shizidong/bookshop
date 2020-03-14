package crh.mars.study.service;

import java.util.List;

import crh.mars.study.base.BaseException;
import crh.mars.study.enty.Resource;

public interface IResourceService {

	public List<Resource> queryAllRes() throws BaseException;

	public List<Resource> queryResource(int res_type)throws BaseException;

	public Resource queryResource(Resource resource) throws BaseException;

	public boolean deleteRes(String resourceId) throws BaseException;

	public boolean saveRes(Resource resource) throws BaseException;

	public boolean updateRes(Resource resource) throws BaseException;

	//自定义删除资源文件
	public boolean deleteRes1(String resourceId) throws BaseException;

}
