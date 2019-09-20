package cn.neteast.yxtHotel.controller;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import cn.neteast.yxtHotel.pojo.TbAdmin;
import cn.neteast.yxtHotel.pojo.TbRoom;
import cn.neteast.yxtHotel.pojoGroup.RoomAndOrder;
import cn.neteast.yxtHotel.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import cn.neteast.yxtHotel.pojo.TbOrder;
import cn.neteast.yxtHotel.service.OrderService;

import entity.PageResult;
import entity.Result;
/**
 * controller
 * @author Administrator
 *
 */
@RestController
@RequestMapping("/order")
public class OrderController {

	@Autowired
	private OrderService orderService;

	@Autowired
	private AdminService adminService;
	
	/**
	 * 返回全部列表
	 * @return
	 */
	@RequestMapping("/findAll")
	public List<TbOrder> findAll(){			
		return orderService.findAll();
	}
	
	
	/**
	 * 返回全部列表
	 * @return
	 */
	@RequestMapping("/findPage")
	public PageResult  findPage(int page,int rows){			
		return orderService.findPage(page, rows);
	}
	
	/**
	 * 增加
	 * @param order
	 * @return
	 */
	@RequestMapping("/add")
	public Result add(@RequestBody TbOrder order){
		try {
			orderService.add(order);
			return new Result(true, "增加成功");
		} catch (Exception e) {
			e.printStackTrace();
			return new Result(false, "增加失败");
		}
	}
	
	/**
	 * 修改
	 * @param order
	 * @return
	 */
	@RequestMapping("/update")
	public Result update(@RequestBody TbOrder order){
		try {
			orderService.update(order);
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
	public TbOrder findOne(Long id){
		return orderService.findOne(id);		
	}
	
	/**
	 * 批量删除
	 * @param ids
	 * @return
	 */
	@RequestMapping("/delete")
	public Result delete(Long [] ids){
		try {
			orderService.delete(ids);
			return new Result(true, "删除成功"); 
		} catch (Exception e) {
			e.printStackTrace();
			return new Result(false, "删除失败");
		}
	}
	
		/**
	 * 查询+分页
	 * @param order
	 * @param page
	 * @param rows
	 * @return
	 */
	@RequestMapping("/search")
	public PageResult search(@RequestBody TbOrder order, int page, int rows  ){
		return orderService.findPage(order, page, rows);		
	}
	/**
	 * 入住方法
	 * @param order 传入对应的Order类客人信息
	 *
	 *              ---这边注意order类的---
	 *              roomId 房间编号 (通过按钮绑定id传入，不需手动输入)
	 *              customerName 客人姓名
	 *              customerIdCard 客人身份证号
	 *              checkoutDate 离店时间
	 *              adminId 业务员ID
	 *              ---以上成员变量需要从网页前台传入---
	 */
	@RequestMapping("/checkin")
	public Result checkIn (@RequestBody TbOrder order){
		order.setCheckoutDate(new Date(order.getCheckoutDate().getTime()+12*3600*1000));
		String name = SecurityContextHolder.getContext().getAuthentication().getName();
		TbAdmin oneByUsername = adminService.findOneByUsername(name);
		if (oneByUsername != null) {
			order.setAdminId(oneByUsername.getAdminId());
		}
		try {
			orderService.checkIn(order);
			return new Result(true, "办理入住成功");
		} catch (Exception e) {
			e.printStackTrace();
			return new Result(false, "办理入住失败");
		}

	}

	/**
	 * 退房
	 * @param roomId 前端传入一个roomId即可
	 */
	@RequestMapping("/checkout")
	public Result checkOut(String roomId){
		TbRoom room = new TbRoom();
		room.setRoomId(new Long(roomId));

		try {
			orderService.checkOut(room);
			return new Result(true, "退房成功");
		} catch (Exception e) {
			e.printStackTrace();
			return new Result(false, "退房失败");
		}
	}
	/**
	 * 需求 1
	 * 根据传入的条件查询房间号
	 * @param nameOrRoomNum 传入姓名或者房间号
	 * @return 注意:如果传入的条件查询不到，则会返回一个null值，查询到会返回一个房间类
	 *
	 * ---注意:根据房间号查询的话，即使是空房也能查询的到房间信息,所以建议根据房间状态在前台显示“此房为空房”
	 * ---根据姓名查找的话，只能精准查询，并且只查得到正在入住状态的客人
	 */
	@RequestMapping("/findByNameOrRoomNum")
	public void findByNameOrRoomNum(String nameOrRoomNum){
		orderService.findByNameOrRoomId(nameOrRoomNum);
	}

	/**
	 * 需求 2
	 * 展示总房间数及房间状态
	 * @return 返回值为一个JSON字符串
	 *
	 * ---返回之前会打印到控制台，请验证JSON---
	 */
	@RequestMapping("/showRoomTotalCount")
	public String showRoomTotalCount(){
		return orderService.showRoomTotalCount();
	}

	/**
	 * 需求 3
	 * 展示所有在住客人的列表
	 * @return 返回所有在住客人的列表
	 */
	@RequestMapping("/showAllCheckInCustomer")
	public List<RoomAndOrder> showAllCheckInCustomer(){
		return orderService.showAllCheckInCustomer();
	}

	/**
	 * 需求 3
	 * 展示历史客户列表(不包含在住客户)
	 * @return 返回历史客户列表
	 */
	@RequestMapping("/showAllHistoryCustomer")
	public List<RoomAndOrder> showAllHistoryCustomer() {
		return orderService.showAllHistoryCustomer();
	}

	/**
	 * 需求 4
	 * 展示所有的房间。如果是已入住房间，返回到期时间信息
	 * @return 返回一个组合实体类，空房只返回room，入住房返回room和order
	 */
	@RequestMapping("/showAllRoom")
	public List<RoomAndOrder> showAllRoom(){
		return orderService.showAllRoom();
	}

	/**
	 * 需求 5
	 * 展示所有的空房间
	 * @return 返回包含有所有空房间的列表
	 */
	@RequestMapping("/showAllEmptyRoom")
	public List<TbRoom> showAllEmptyRoom(){
		return orderService.showAllEmptyRoom();
	}

	/**
	 * 查询单个房间组合订单的信息(主要在前端的续住功能回显数据上使用)
	 * @param roomId 传入一个房间ID，请提前在前端页面隐藏域中绑定
	 * @return 返回一个房间和订单的组合实体类
	 */
	@RequestMapping("/findOneRoom")
	public RoomAndOrder findOneRoom(String roomId) {
		return orderService.findOneRoom(new Long(roomId));
	}

	/**
	 * 前端传回修改好的数据，对数据库进行修改的方法(用于续住功能)
	 * @param orderId 传入一个订单类
	 *              ---注意---
	 *              roomId 房间ID
	 *              checkOutDate 退房日期
	 *              ---以上两个成员必须传入!!!---
	 */
	@RequestMapping("/changeDate")
	public Result changeDate(String orderId, String checkoutDate) throws ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date parse = sdf.parse(checkoutDate);
		TbOrder order = orderService.findOne(Long.valueOf(orderId));
		order.setCheckoutDate(new Date(parse.getTime()+12*3600*1000));
		try {
			orderService.changeDate(order);
			return new Result(true, "办理入住成功");
		} catch (Exception e) {
			e.printStackTrace();
			return new Result(false, "办理入住失败");
		}

	}
}
