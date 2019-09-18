package cn.neteast.yxtHotel.service;
import java.util.List;
import cn.neteast.yxtHotel.pojo.TbAdmin;

import entity.PageResult;
/**
 * 服务层接口
 * @author Administrator
 *
 */
public interface AdminService {

	/**
	 * 返回全部列表
	 * @return
	 */
	public List<TbAdmin> findAll();
	
	
	/**
	 * 返回分页列表
	 * @return
	 */
	public PageResult findPage(int pageNum, int pageSize);
	
	
	/**
	 * 增加
	*/
	public void add(TbAdmin admin);
	
	
	/**
	 * 修改
	 */
	public void update(TbAdmin admin);
	

	/**
	 * 根据ID获取实体
	 * @param id
	 * @return
	 */
	public TbAdmin findOne(Long id);
	
	
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
	public PageResult findPage(TbAdmin admin, int pageNum, int pageSize);
	
}
