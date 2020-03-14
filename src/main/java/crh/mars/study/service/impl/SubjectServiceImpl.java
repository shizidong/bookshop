package crh.mars.study.service.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import crh.mars.study.base.BaseException;
import crh.mars.study.enty.Subject;
import crh.mars.study.mapper.SubjectMapper;
import crh.mars.study.service.ISubjectService;

@Service("SubjectService")
@Transactional
public class SubjectServiceImpl implements ISubjectService {

	@Autowired
	private SubjectMapper SubjectMapper;

	@Override
	public List<Subject> queryAllSubject() throws BaseException {
		return SubjectMapper.selectAll();
	}

	@Override
	public Subject querySubject(Subject Subject) throws BaseException {
		return SubjectMapper.selectOne(Subject);
	}

	@Override
	public boolean deleteSubject(String SubjectId) throws BaseException {
		int result = SubjectMapper.deleteByPrimaryKey(SubjectId);
		if (result == 1) {
			return true;
		} else {
			return false;
		}
	}

	@Override
	public boolean saveSubject(Subject Subject) throws BaseException {
		int result = SubjectMapper.insert(Subject);
		if (result == 1) {
			return true;
		} else {
			return false;
		}
	}

	@Override
	public boolean updateSubject(Subject Subject) throws BaseException {
		int result = SubjectMapper.updateByPrimaryKey(Subject);
		if (result == 1) {
			return true;
		} else {
			return false;
		}
	}

}
