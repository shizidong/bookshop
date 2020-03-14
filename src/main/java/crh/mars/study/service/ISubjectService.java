package crh.mars.study.service;

import java.util.List;

import crh.mars.study.base.BaseException;
import crh.mars.study.enty.Subject;

public interface ISubjectService {

	public List<Subject> queryAllSubject () throws BaseException;

	public Subject querySubject(Subject Subject) throws BaseException;

	public boolean deleteSubject(String SubjectId) throws BaseException;

	public boolean saveSubject(Subject Subject)throws BaseException;

	public boolean updateSubject(Subject Subject)throws BaseException;

}
