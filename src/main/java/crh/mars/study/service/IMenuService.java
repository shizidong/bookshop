package crh.mars.study.service;

import java.util.List;

import crh.mars.study.base.BaseException;
import crh.mars.study.enty.Menu;

public interface IMenuService {

	public List<Menu> queryAllMenu () throws BaseException;

	public Menu queryMenu(Menu menu) throws BaseException;

	public boolean deleteMenu(String menuId) throws BaseException;

	public boolean saveMenu(Menu menu)throws BaseException;

	public boolean updateMenu(Menu menu)throws BaseException;

}
