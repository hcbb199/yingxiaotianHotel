package cn.neteast.yxtHotel.controller;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cn.neteast.yxtHotel.pojo.TbAdmin;
import cn.neteast.yxtHotel.service.AdminService;

import entity.PageResult;
import entity.Result;
/**
 * controller
 * @author Administrator
 *
 */
@RestController
@RequestMapping("/admin")
public class AdminController {

	@Autowired
	private AdminService adminService;
	
	/**
	 * 返回全部列表
	 * @return
	 */
	@RequestMapping("/findAll")
	public List<TbAdmin> findAll(){			
		return adminService.findAll();
	}
	
	
	/**
	 * 返回全部列表
	 * @return
	 */
	@RequestMapping("/findPage")
	public PageResult  findPage(int page,int rows){			
		return adminService.findPage(page, rows);
	}
	
	/**
	 * 增加
	 * @param admin
	 * @return
	 */
	@RequestMapping("/add")
	public Result add(@RequestBody TbAdmin admin){
		try {
			String name = SecurityContextHolder.getContext().getAuthentication().getName();
			TbAdmin adminLogin = adminService.findOneByUsername(name);
			if (adminLogin.getRoleTypeId() == 0) {
				adminService.add(admin);
				return new Result(true, "增加成功");
			} else {
				return new Result(false, "当前管理员权限不足");
			}
		} catch (Exception e) {
			e.printStackTrace();
			return new Result(false, "增加失败");
		}
	}
	
	/**
	 * 修改
	 * @param admin
	 * @return
	 */
	@RequestMapping("/update")
	public Result update(@RequestBody TbAdmin admin){
		try {
			adminService.update(admin);
			return new Result(true, "修改成功");
		} catch (Exception e) {
			e.printStackTrace();
			return new Result(false, "修改失败");
		}
	}	
	
	/**
	 * 获取实体
	 * @param id
	 * @return
	 */
	@RequestMapping("/findOne")
	public TbAdmin findOne(Long id){
		return adminService.findOne(id);		
	}
	
	/**
	 * 批量删除
	 * @param ids
	 * @return
	 */
	@RequestMapping("/delete")
	public Result delete(Long [] ids){
		try {
			String name = SecurityContextHolder.getContext().getAuthentication().getName();
			TbAdmin adminLogin = adminService.findOneByUsername(name);
			if (adminLogin.getRoleTypeId() == 0) {
				adminService.delete(ids);
				return new Result(true, "删除成功");
			} else {
				return new Result(false, "当前管理员权限不足");
			}

		} catch (Exception e) {
			e.printStackTrace();
			return new Result(false, "删除失败");
		}
	}
	
		/**
	 * 查询+分页
	 * @param admin
	 * @param page
	 * @param rows
	 * @return
	 */
	@RequestMapping("/search")
	public PageResult search(@RequestBody TbAdmin admin, int page, int rows  ){
		return adminService.findPage(admin, page, rows);		
	}

	/**
	 * 校验用户名是否已存在
	 * @param username
	 * @return
	 */
	@RequestMapping("/checkUsername")
	public Result checkUsername(String username) {

		try {
			Boolean flag = adminService.checkUsername(username);
			if (flag) {
				return new Result(true, "用户名可用");
			} else {
				return new Result(false, "用户名已存在, 不可用");
			}

		} catch (Exception e) {
			e.printStackTrace();
			return new Result(false, "校验失败");
		}
	}

}
