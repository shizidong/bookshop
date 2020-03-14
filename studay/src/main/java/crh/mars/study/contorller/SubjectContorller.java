package crh.mars.study.contorller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import crh.mars.study.base.BaseException;
import crh.mars.study.enty.Subject;
import crh.mars.study.service.ISubjectService;
import crh.mars.study.utils.LogUtil;
import crh.mars.study.utils.WebResult;

@RequestMapping("/study/subject")
@RestController
public class SubjectContorller {

	@Autowired
	private ISubjectService SubjectService;
	// 获取所有科目
	@RequestMapping(value = "/query/allsubject", method = RequestMethod.POST)
	public @ResponseBody List<Subject> queryAllSubject() {
		List<Subject> SubjectList = new ArrayList<>();
		try {
			SubjectList = SubjectService.queryAllSubject();
		} catch (BaseException e) {
			LogUtil.info("查询所有科目失败！");
		}
		return SubjectList;
	}

	// 根据科目属性查询科目
	@RequestMapping(value = "/query/subject", method = RequestMethod.POST)
	public WebResult<Subject> querySubject(Subject Subject) {
		WebResult<Subject> webResult = new WebResult<>();
		Subject resultSubject=new Subject();
		try {
			resultSubject = SubjectService.querySubject(Subject);
		} catch (BaseException e) {
			LogUtil.info("根据科目属性查询科目失败！");
			return webResult.fail(e);
		}
		return webResult.success("查科目询数据成功！", resultSubject);
	}

	// 根据科目ID的删除科目
	@RequestMapping(value = "/delete/subject", method = RequestMethod.POST)
	public WebResult<String> deleteSubject(String SubjectId) {
		WebResult<String> webResult = new WebResult<>();
		boolean result = true;
		try {
			result = SubjectService.deleteSubject(SubjectId);
		} catch (BaseException e) {
			LogUtil.info("删除科目失败！");
			result = false;
			return webResult.fail(e);
		}
		return webResult.success("删除科目成功！", String.valueOf(result));
	}

	// 新增科目
	@RequestMapping(value = "/save/subject", method = RequestMethod.POST)
	public WebResult<String> saveSubject(Subject Subject) {
		WebResult<String> webResult = new WebResult<>();
		boolean result = true;
		try {
			result = SubjectService.saveSubject(Subject);
		} catch (BaseException e) {
			LogUtil.info("新增科目失败！");
			result = false;
			return webResult.fail(e);
		}
		return webResult.success("新增科目成功！", String.valueOf(result));
	}

	// 修改科目
	@RequestMapping(value = "/update/subject", method = RequestMethod.POST)
	public WebResult<String> updateSubject(Subject Subject) {
		WebResult<String> webResult = new WebResult<>();
		boolean result = true;
		try {
			result = SubjectService.updateSubject(Subject);
		} catch (BaseException e) {
			LogUtil.info("更新科目失败！");
			result = false;
			return webResult.fail(e);
		}
		return webResult.success("更新科目成功！", String.valueOf(result));
	}

}
