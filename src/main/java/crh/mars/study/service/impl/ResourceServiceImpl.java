package crh.mars.study.service.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import crh.mars.study.base.BaseException;
import crh.mars.study.enty.Resource;
import crh.mars.study.mapper.ResourceMapper;
import crh.mars.study.service.IResourceService;

@Service("ResourceService")
@Transactional
public class ResourceServiceImpl implements IResourceService {

	@Autowired
	private ResourceMapper resourceMapper;
	@Autowired

	@Override
	public List<Resource> queryAllRes() throws BaseException {
		return resourceMapper.selectAll();
	}

	@Override
	public List<Resource> queryResource(int res_type) throws BaseException {
		return resourceMapper.queryAllForLevel(res_type);
	}

	@Override
	public Resource queryResource(Resource resource) throws BaseException {
		return resourceMapper.selectOne(resource);
	}

	@Override
	public boolean deleteRes(String ResId) throws BaseException {
		int result = resourceMapper.deleteByPrimaryKey(ResId);
		if (result == 1) {
			return true;
		} else {
			return false;
		}
	}

	@Override
	public boolean saveRes(Resource Res) throws BaseException {
		int result = resourceMapper.insert(Res);
		if (result == 1) {
			return true;
		} else {
			return false;
		}
	}

	@Override
	public boolean updateRes(Resource Res) throws BaseException {
		int result = resourceMapper.updateByPrimaryKey(Res);
		if (result == 1) {
			return true;
		} else {
			return false;
		}
	}

	@Override
	public boolean deleteRes1(String resourceId) throws BaseException {
		resourceMapper.deleteRes1(resourceId);
		return false;
	}

}
