package crh.mars.study.service.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import crh.mars.study.base.BaseException;
import crh.mars.study.enty.Menu;
import crh.mars.study.mapper.MenuMapper;
import crh.mars.study.service.IMenuService;

@Service("menuService")
@Transactional
public class MenuServiceImpl implements IMenuService {

	@Autowired
	private MenuMapper menuMapper;

	@Override
	public List<Menu> queryAllMenu() throws BaseException {
		return menuMapper.selectAll();
	}

	@Override
	public Menu queryMenu(Menu menu) throws BaseException {
		return menuMapper.selectOne(menu);
	}

	@Override
	public boolean deleteMenu(String menuId) throws BaseException {
		int result = menuMapper.deleteByPrimaryKey(menuId);
		if (result == 1) {
			return true;
		} else {
			return false;
		}
	}

	@Override
	public boolean saveMenu(Menu menu) throws BaseException {
		int result = menuMapper.insert(menu);
		if (result == 1) {
			return true;
		} else {
			return false;
		}
	}

	@Override
	public boolean updateMenu(Menu menu) throws BaseException {
		int result = menuMapper.updateByPrimaryKey(menu);
		if (result == 1) {
			return true;
		} else {
			return false;
		}
	}

}
