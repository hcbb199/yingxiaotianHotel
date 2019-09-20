package cn.neteast.yxtHotel.service;
import java.util.List;
import cn.neteast.yxtHotel.pojo.TbOrder;

import cn.neteast.yxtHotel.pojo.TbRoom;
import cn.neteast.yxtHotel.pojoGroup.RoomAndOrder;
import entity.PageResult;
/**
 * 服务层接口
 * @author Administrator
 *
 */
public interface OrderService {

	/**
	 * 返回全部列表
	 * @return
	 */
	public List<TbOrder> findAll();
	
	
	/**
	 * 返回分页列表
	 * @return
	 */
	public PageResult findPage(int pageNum, int pageSize);
	
	
	/**
	 * 增加
	*/
	public void add(TbOrder order);
	
	
	/**
	 * 修改
	 */
	public void update(TbOrder order);
	

	/**
	 * 根据ID获取实体
	 * @param id
	 * @return
	 */
	public TbOrder findOne(Long id);
	
	
	/**
	 * 批量删除
	 * @param ids
	 */
	public void delete(Long[] ids);

	/**
	 * 分页
	 * @param pageNum 当前页 码
	 * @param pageSize 每页记录数
	 * @return
	 */
	public PageResult findPage(TbOrder order, int pageNum, int pageSize);

	/**
	 * 根据房间号查找该房间当前订单
	 * @param tbRoom
	 * @return
	 */
	TbOrder findOrderByRoom(TbRoom tbRoom);


	/**
	 * 办理入住用方法
	 * @param order 传入对应的Order类客人信息
	 */
	public void checkIn(TbOrder order);

	/**
	 * 办理退房的方法
	 * @param room 传入对应的Order类客人信息
	 */
	public void checkOut(TbRoom room);

	/**
	 * 根据姓名或者房间号来查询房间信息
	 * @param nameOrRoomId 传入姓名或者房间号
	 * @return
	 */
	public TbRoom findByNameOrRoomId(String nameOrRoomId);

	/**
	 * 展示总房间数，入住房间和未入住房间数
	 * @return 返回值为一个JSON字符串
	 */
	public String showRoomTotalCount();

	/**
	 * 展示当前所有的入住客户列表
	 * @return 返回一个包含所有入住客户的列表
	 */
	public List<RoomAndOrder> showAllCheckInCustomer();

	/**
	 * 展示所有历史入住过的客户(不包括当前入住的)
	 * @return 返回一个包含所有历史客户的列表
	 */
	public List<RoomAndOrder> showAllHistoryCustomer();

	/**
	 * 展示所有的房间。如果是已入住房间，返回到期时间信息。用于前端的展示列表
	 * @return 返回一个组合实体类，空房只返回room，入住房返回room和order
	 */
	public List<RoomAndOrder> showAllRoom();

	/**
	 * 展示所有的空房间，用于前端页面展示空房间列表
	 * @return 返回一个包含所有空房间的集合
	 */
	public List<TbRoom> showAllEmptyRoom();

	/**
	 * 根据房间ID来查询一个房间的信息(在前端用于续住时的数据回显)
	 * @param roomId 传入一个房间ID，请提前在前端页面隐藏域中绑定
	 * @return 返回一个房间信息和客人信息的实体类
	 */
	public RoomAndOrder findOneRoom(Long roomId);

	/**
	 *前端传回修改好的数据，对数据库进行修改的方法(用于续住功能)
	 * @param order 传入一个订单类
	 */
	public void changeDate(TbOrder order);
}
