package crh.mars.study.enty;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "STUDY_MENU")
public class Menu {
	// 菜单ID
	@Id
	private String menuId;
	// 菜单名称
	private String menuName;
	// 菜单编码
	private String menuCode;
	// 菜单可见 1用户可见 2管理员可见
	private String menuVisible;
	// 菜单状态 0正常 1不可用
	private String menuStatus;
	// 菜单级别
	private Integer menuLevel;
	// 菜单父Id
	private String menuParentId;
	// 菜单类型
	private Integer menuType;
	// 菜单描述
	private String menuDesc;
	// 菜单路径
	private String menuAddress;

	public String getMenuId() {
		return menuId;
	}

	public void setMenuId(String menuId) {
		this.menuId = menuId;
	}

	public String getMenuName() {
		return menuName;
	}

	public void setMenuName(String menuName) {
		this.menuName = menuName;
	}

	public String getMenuCode() {
		return menuCode;
	}

	public void setMenuCode(String menuCode) {
		this.menuCode = menuCode;
	}
	
	public String getMenuVisible() {
		return menuVisible;
	}

	public void setMenuVisible(String menuVisible) {
		this.menuVisible = menuVisible;
	}

	public String getMenuStatus() {
		return menuStatus;
	}

	public void setMenuStatus(String menuStatus) {
		this.menuStatus = menuStatus;
	}

	public Integer getMenuLevel() {
		return menuLevel;
	}

	public void setMenuLevel(Integer menuLevel) {
		this.menuLevel = menuLevel;
	}

	public String getMenuParentId() {
		return menuParentId;
	}

	public void setMenuParentId(String menuParentId) {
		this.menuParentId = menuParentId;
	}

	public Integer getMenuType() {
		return menuType;
	}

	public void setMenuType(Integer menuType) {
		this.menuType = menuType;
	}

	public String getMenuDesc() {
		return menuDesc;
	}

	public void setMenuDesc(String menuDesc) {
		this.menuDesc = menuDesc;
	}

	public String getMenuAddress() {
		return menuAddress;
	}

	public void setMenuAddress(String menuAddress) {
		this.menuAddress = menuAddress;
	}

}
