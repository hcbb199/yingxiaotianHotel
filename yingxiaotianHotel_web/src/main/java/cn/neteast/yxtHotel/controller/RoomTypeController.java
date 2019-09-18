package cn.neteast.yxtHotel.controller;
import java.util.List;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.alibaba.dubbo.config.annotation.Reference;
import cn.neteast.yxtHotel.pojo.TbRoomType;
import cn.neteast.yxtHotel.service.RoomTypeService;

import entity.PageResult;
import entity.Result;
/**
 * controller
 * @author Administrator
 *
 */
@RestController
@RequestMapping("/roomType")
public class RoomTypeController {

	@Reference
	private RoomTypeService roomTypeService;
	
	/**
	 * 返回全部列表
	 * @return
	 */
	@RequestMapping("/findAll")
	public List<TbRoomType> findAll(){			
		return roomTypeService.findAll();
	}
	
	
	/**
	 * 返回全部列表
	 * @return
	 */
	@RequestMapping("/findPage")
	public PageResult  findPage(int page,int rows){			
		return roomTypeService.findPage(page, rows);
	}
	
	/**
	 * 增加
	 * @param roomType
	 * @return
	 */
	@RequestMapping("/add")
	public Result add(@RequestBody TbRoomType roomType){
		try {
			roomTypeService.add(roomType);
			return new Result(true, "增加成功");
		} catch (Exception e) {
			e.printStackTrace();
			return new Result(false, "增加失败");
		}
	}
	
	/**
	 * 修改
	 * @param roomType
	 * @return
	 */
	@RequestMapping("/update")
	public Result update(@RequestBody TbRoomType roomType){
		try {
			roomTypeService.update(roomType);
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
	public TbRoomType findOne(Long id){
		return roomTypeService.findOne(id);		
	}
	
	/**
	 * 批量删除
	 * @param ids
	 * @return
	 */
	@RequestMapping("/delete")
	public Result delete(Long [] ids){
		try {
			roomTypeService.delete(ids);
			return new Result(true, "删除成功"); 
		} catch (Exception e) {
			e.printStackTrace();
			return new Result(false, "删除失败");
		}
	}
	
		/**
	 * 查询+分页
	 * @param brand
	 * @param page
	 * @param rows
	 * @return
	 */
	@RequestMapping("/search")
	public PageResult search(@RequestBody TbRoomType roomType, int page, int rows  ){
		return roomTypeService.findPage(roomType, page, rows);		
	}
	
}
