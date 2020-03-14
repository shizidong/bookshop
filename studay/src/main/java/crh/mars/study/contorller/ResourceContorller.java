package crh.mars.study.contorller;

import java.util.ArrayList;
import java.util.List;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import crh.mars.study.base.BaseException;
import crh.mars.study.enty.Resource;
import crh.mars.study.service.IResourceService;
import crh.mars.study.utils.LogUtil;
import crh.mars.study.utils.WebResult;

@RequestMapping("/study/resource")
@RestController
public class ResourceContorller {

	@Autowired
	private IResourceService resourceService;
	@Autowired
	//查询四级资料
	@RequestMapping(value = "/query/sijiziliao", method = RequestMethod.POST)
	public @ResponseBody JSONObject queryAllForLevel(){
		int res_type=11;
		JSONObject jsonObject=backJson(res_type);
		return jsonObject;

	}
	//查询四级视频
	@RequestMapping(value = "/query/sijishiping", method = RequestMethod.POST)
	public @ResponseBody JSONObject querysijishiping(){
		int res_type=12;
		JSONObject jsonObject=backJson(res_type);
		return jsonObject;

	}
	//查询六级资料
	@RequestMapping(value = "/query/liujiziliao", method = RequestMethod.POST)
	public @ResponseBody JSONObject queryAllForSixLevel(){
		int res_type=21;
		JSONObject jsonObject=backJson(res_type);
		return jsonObject;
	}
	//查询六级资料
	@RequestMapping(value = "/query/liujishiping", method = RequestMethod.POST)
	public @ResponseBody JSONObject queryliujishiping(){
		int res_type=22;
		JSONObject jsonObject=backJson(res_type);
		return jsonObject;
	}
	//查询计算机资源
	@RequestMapping(value = "/query/jisuanjiziliao", method = RequestMethod.POST)
	public @ResponseBody JSONObject queryComputerZiLiao(){
		int res_type=31;
		JSONObject jsonObject=backJson(res_type);
		return jsonObject;
	}
	//查询数学资源
	@RequestMapping(value = "/query/shuxueziliao", method = RequestMethod.POST)
	public @ResponseBody JSONObject queryshuxueziliao(){
		int res_type=41;
		JSONObject jsonObject=backJson(res_type);
		return jsonObject;
	}
	//查询数学视频
	@RequestMapping(value = "/query/shuxueship", method = RequestMethod.POST)
	public @ResponseBody JSONObject queryshuxueship(){
		int res_type=42;
		JSONObject jsonObject=backJson(res_type);
		return jsonObject;
	}
	// 查询所有资源
	@RequestMapping(value = "/query/allRes", method = RequestMethod.POST)
	public @ResponseBody List<Resource> queryAllRes() {
		List<Resource> resList = new ArrayList<>();
		try {
			resList = resourceService.queryAllRes();
		} catch (BaseException e) {
			LogUtil.info("查询所有资源失败！");
		}
		return resList;
	}

	// 根据资源属性查询资源
	@RequestMapping(value = "/query/resource", method = RequestMethod.POST)
	public WebResult<Resource> queryResource(Resource resource) {
		WebResult<Resource> webResult = new WebResult<>();
		Resource resultRes = new Resource();
		try {
			resultRes = resourceService.queryResource(resource);
		} catch (BaseException e) {
			LogUtil.info("根据资源属性查询资源失败！");
			return webResult.fail(e);
		}
		return webResult.success("查询资源数据成功！", resultRes);
	}

	// 根据资源ID的删除资源
	@RequestMapping(value = "/delete/resource", method = RequestMethod.POST)
	@ResponseBody
	public WebResult<String> deleteRes(String resourceId) {
		System.out.print("已经到"+resourceId);
		WebResult<String> webResult = new WebResult<>();
		boolean result = true;
		try {
			result = resourceService.deleteRes1(resourceId);
		} catch (BaseException e) {
			LogUtil.info("删除资源失败！");
			result = false;
			return webResult.fail(e);
		}
		return webResult.success("删除资源成功！", String.valueOf(result));
	}
	@RequestMapping(value = "/delete/resourceSelf", method = RequestMethod.POST)
	@ResponseBody
	//自定义删除资源
	public WebResult<String> deleteRes1(String resourceId) {
		System.out.print("已经到"+resourceId);
		WebResult<String> webResult = new WebResult<>();
		boolean result = true;
		try {
			result = resourceService.deleteRes1(resourceId);
		} catch (BaseException e) {
			LogUtil.info("删除资源失败！");
			result = false;
			return webResult.fail(e);
		}
		return webResult.success("删除资源成功！", String.valueOf(result));
	}

	// 新增资源
	@RequestMapping(value = "/save/Res", method = RequestMethod.POST)
	public WebResult<String> saveRes(Resource resource) {
		resource.setResId(RandomStringUtils.randomAlphanumeric(32));
		WebResult<String> webResult = new WebResult<>();
		boolean result = true;
		try {
			result = resourceService.saveRes(resource);
		} catch (BaseException e) {
			LogUtil.info("新增资源失败！");
			result = false;
			return webResult.fail(e);
		}
		return webResult.success("新增资源成功！", String.valueOf(result));
	}

	// 修改资源
	@RequestMapping(value = "/update/Res", method = RequestMethod.POST)
	@ResponseBody
/*	public String updateRes(String RES_ID,String RES_NAME, int RES_TYPE,String RES_ADDRESS,String RES_DESC) {*/
	public String updateRes(Resource resource) {
		/*Resource resource=new Resource();
		resource.setResId(RES_ID);
		resource.setResAddress(RES_ADDRESS);
		resource.setResDesc(RES_DESC);
		resource.setResType(RES_TYPE);
		resource.setResName(RES_NAME);
		resource.setResCode(1);
		resource.setResStatus(1);*/
		WebResult<String> webResult = new WebResult<>();
      //  webResult.success("更新资源成功！", String.valueOf(result))
		boolean result = true;
		try {
			result = resourceService.updateRes(resource);
		} catch (BaseException e) {
			LogUtil.info("更新资源失败！");
			result = false;
			return "failure";
		}
		return "success";
	}
	//通用的返回一个数据表
	public JSONObject backJson(int res_type){
		JSONArray jsonArray=new JSONArray();
		JSONObject jsonObject=new JSONObject();
		jsonObject.put("code",0);
		jsonObject.put("msg","");
		List<Resource> resourceList =new ArrayList<>();
		try {
			resourceList=resourceService.queryResource(res_type);
			//开始json转换
			jsonArray =JSONArray.fromObject(resourceList);
			jsonObject.put("count",resourceList.size());
			jsonObject.put("data",resourceList);
		} catch (BaseException e) {
			e.printStackTrace();
		}
		return jsonObject;
	}
}
