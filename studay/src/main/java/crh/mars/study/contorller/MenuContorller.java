package crh.mars.study.contorller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import crh.mars.study.base.BaseException;
import crh.mars.study.enty.Menu;
import crh.mars.study.service.IMenuService;
import crh.mars.study.utils.LogUtil;
import crh.mars.study.utils.WebResult;

@RequestMapping("/study/menu")
@RestController
public class MenuContorller {

	@Autowired
	private IMenuService menuService;

	// 获取所有菜单
	@RequestMapping(value = "/query/allMenu", method = RequestMethod.POST)
	public @ResponseBody List<Menu> queryAllMenu() {
		List<Menu> menuList = new ArrayList<>();
		try {
			menuList = menuService.queryAllMenu();
		} catch (BaseException e) {
			LogUtil.info("查询所有菜单失败！");
		}
		return menuList;
	}

	// 根据菜单属性查询菜单
	@RequestMapping(value = "/query/menu", method = RequestMethod.POST)
	public WebResult<Menu> queryMenu(Menu menu) {
		WebResult<Menu> webResult = new WebResult<>();
		Menu resultMenu=new Menu();
		try {
			resultMenu = menuService.queryMenu(menu);
		} catch (BaseException e) {
			LogUtil.info("根据菜单属性查询菜单失败！");
			return webResult.fail(e);
		}
		return webResult.success("查菜单询数据成功！", resultMenu);
	}

	// 根据菜单ID的删除菜单
	@RequestMapping(value = "/delete/menu", method = RequestMethod.POST)
	public WebResult<String> deleteMenu(String menuId) {
		WebResult<String> webResult = new WebResult<>();
		boolean result = true;
		try {
			result = menuService.deleteMenu(menuId);
		} catch (BaseException e) {
			LogUtil.info("删除菜单失败！");
			result = false;
			return webResult.fail(e);
		}
		return webResult.success("删除菜单成功！", String.valueOf(result));
	}

	// 新增菜单
	@RequestMapping(value = "/save/menu", method = RequestMethod.POST)
	public WebResult<String> saveMenu(Menu menu) {
		WebResult<String> webResult = new WebResult<>();
		boolean result = true;
		try {
			result = menuService.saveMenu(menu);
		} catch (BaseException e) {
			LogUtil.info("新增菜单失败！");
			result = false;
			return webResult.fail(e);
		}
		return webResult.success("新增菜单成功！", String.valueOf(result));
	}

	// 修改菜单
	@RequestMapping(value = "/update/menu", method = RequestMethod.POST)
	public WebResult<String> updateMenu(Menu menu) {
		WebResult<String> webResult = new WebResult<>();
		boolean result = true;
		try {
			result = menuService.updateMenu(menu);
		} catch (BaseException e) {
			LogUtil.info("更新菜单失败！");
			result = false;
			return webResult.fail(e);
		}
		return webResult.success("更新菜单成功！", String.valueOf(result));
	}

}
