package cn.neteast.yxtHotel.controller;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import cn.neteast.yxtHotel.pojo.TbRoleType;
import cn.neteast.yxtHotel.service.RoleTypeService;

import entity.PageResult;
import entity.Result;
/**
 * controller
 * @author Administrator
 *
 */
@RestController
@RequestMapping("/roleType")
public class RoleTypeController {

	@Autowired
	private RoleTypeService roleTypeService;
	
	/**
	 * 返回全部列表
	 * @return
	 */
	@RequestMapping("/findAll")
	public List<TbRoleType> findAll(){			
		return roleTypeService.findAll();
	}
	
	
	/**
	 * 返回全部列表
	 * @return
	 */
	@RequestMapping("/findPage")
	public PageResult  findPage(int page,int rows){			
		return roleTypeService.findPage(page, rows);
	}
	
	/**
	 * 增加
	 * @param roleType
	 * @return
	 */
	@RequestMapping("/add")
	public Result add(@RequestBody TbRoleType roleType){
		try {
			roleTypeService.add(roleType);
			return new Result(true, "增加成功");
		} catch (Exception e) {
			e.printStackTrace();
			return new Result(false, "增加失败");
		}
	}
	
	/**
	 * 修改
	 * @param roleType
	 * @return
	 */
	@RequestMapping("/update")
	public Result update(@RequestBody TbRoleType roleType){
		try {
			roleTypeService.update(roleType);
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
	public TbRoleType findOne(Long id){
		return roleTypeService.findOne(id);		
	}
	
	/**
	 * 批量删除
	 * @param ids
	 * @return
	 */
	@RequestMapping("/delete")
	public Result delete(Long [] ids){
		try {
			roleTypeService.delete(ids);
			return new Result(true, "删除成功"); 
		} catch (Exception e) {
			e.printStackTrace();
			return new Result(false, "删除失败");
		}
	}
	
		/**
	 * 查询+分页
	 * @param roleType
	 * @param page
	 * @param rows
	 * @return
	 */
	@RequestMapping("/search")
	public PageResult search(@RequestBody TbRoleType roleType, int page, int rows  ){
		return roleTypeService.findPage(roleType, page, rows);		
	}
	
}
