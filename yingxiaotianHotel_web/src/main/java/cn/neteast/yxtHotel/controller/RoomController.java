package cn.neteast.yxtHotel.controller;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cn.neteast.yxtHotel.pojo.TbRoom;
import cn.neteast.yxtHotel.service.RoomService;

import entity.PageResult;
import entity.Result;
/**
 * controller
 * @author Administrator
 *
 */
@RestController
@RequestMapping("/room")
public class RoomController {

	@Autowired
	private RoomService roomService;
	
	/**
	 * 返回全部列表
	 * @return
	 */
	@RequestMapping("/findAll")
	public List<TbRoom> findAll(){			
		return roomService.findAll();
	}
	
	
	/**
	 * 返回全部列表
	 * @return
	 */
	@RequestMapping("/findPage")
	public PageResult  findPage(int page,int rows){			
		return roomService.findPage(page, rows);
	}
	
	/**
	 * 增加
	 * @param room
	 * @return
	 */
	@RequestMapping("/add")
	public Result add(@RequestBody TbRoom room){
		try {
			roomService.add(room);
			return new Result(true, "增加成功");
		} catch (Exception e) {
			e.printStackTrace();
			return new Result(false, "增加失败");
		}
	}
	
	/**
	 * 修改
	 * @param room
	 * @return
	 */
	@RequestMapping("/update")
	public Result update(@RequestBody TbRoom room){
		try {
			roomService.update(room);
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
	public TbRoom findOne(Long id){
		return roomService.findOne(id);		
	}
	
	/**
	 * 批量删除
	 * @param ids
	 * @return
	 */
	@RequestMapping("/delete")
	public Result delete(Long [] ids){
		try {
			roomService.delete(ids);
			return new Result(true, "删除成功"); 
		} catch (Exception e) {
			e.printStackTrace();
			return new Result(false, "删除失败");
		}
	}
	
		/**
	 * 查询+分页
	 * @param room
	 * @param page
	 * @param rows
	 * @return
	 */
	@RequestMapping("/search")
	public PageResult search(@RequestBody TbRoom room, int page, int rows  ){
		return roomService.findPage(room, page, rows);		
	}
	
}
