package cn.neteast.yxtHotel.service;
import java.util.List;
import cn.neteast.yxtHotel.pojo.TbRoom;

import cn.neteast.yxtHotel.pojoGroup.Room;
import entity.PageResult;
/**
 * 服务层接口
 * @author Administrator
 *
 */
public interface RoomService {

	/**
	 * 返回全部列表
	 * @return
	 */
	public List<TbRoom> findAll();
	
	
	/**
	 * 返回分页列表
	 * @return
	 */
	public PageResult findPage(int pageNum, int pageSize);
	
	
	/**
	 * 增加
	*/
	public void add(TbRoom room);
	
	
	/**
	 * 修改
	 */
	public void update(TbRoom room);
	

	/**
	 * 根据ID获取实体
	 * @param id
	 * @return
	 */
	public TbRoom findOne(Long id);
	
	
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
	public PageResult findPage(TbRoom room, int pageNum, int pageSize);

	/**
	 * 查询所有房间及其当前订单
	 * @return
	 */
	List<Room> findAllRoomAndOrder();
}
