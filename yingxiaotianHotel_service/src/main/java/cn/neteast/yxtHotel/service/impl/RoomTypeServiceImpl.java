package cn.neteast.yxtHotel.service.impl;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import cn.neteast.yxtHotel.mapper.TbRoomTypeMapper;
import cn.neteast.yxtHotel.pojo.TbRoomType;
import cn.neteast.yxtHotel.pojo.TbRoomTypeExample;
import cn.neteast.yxtHotel.pojo.TbRoomTypeExample.Criteria;
import cn.neteast.yxtHotel.service.RoomTypeService;

import entity.PageResult;
import org.springframework.stereotype.Service;

/**
 * 服务实现层
 * @author Administrator
 *
 */
@Service
public class RoomTypeServiceImpl implements RoomTypeService {

	@Autowired
	private TbRoomTypeMapper roomTypeMapper;
	
	/**
	 * 查询全部
	 */
	@Override
	public List<TbRoomType> findAll() {
		return roomTypeMapper.selectByExample(null);
	}

	/**
	 * 按分页查询
	 */
	@Override
	public PageResult findPage(int pageNum, int pageSize) {
		PageHelper.startPage(pageNum, pageSize);		
		Page<TbRoomType> page=   (Page<TbRoomType>) roomTypeMapper.selectByExample(null);
		return new PageResult(page.getTotal(), page.getResult());
	}

	/**
	 * 增加
	 */
	@Override
	public void add(TbRoomType roomType) {
		roomTypeMapper.insert(roomType);		
	}

	
	/**
	 * 修改
	 */
	@Override
	public void update(TbRoomType roomType){
		roomTypeMapper.updateByPrimaryKey(roomType);
	}	
	
	/**
	 * 根据ID获取实体
	 * @param id
	 * @return
	 */
	@Override
	public TbRoomType findOne(Long id){
		return roomTypeMapper.selectByPrimaryKey(id);
	}

	/**
	 * 批量删除
	 */
	@Override
	public void delete(Long[] ids) {
		for(Long id:ids){
			roomTypeMapper.deleteByPrimaryKey(id);
		}		
	}
	
	
		@Override
	public PageResult findPage(TbRoomType roomType, int pageNum, int pageSize) {
		PageHelper.startPage(pageNum, pageSize);
		
		TbRoomTypeExample example=new TbRoomTypeExample();
		Criteria criteria = example.createCriteria();
		
		if(roomType!=null){			
						if(roomType.getRoomTypeDesc()!=null && roomType.getRoomTypeDesc().length()>0){
				criteria.andRoomTypeDescLike("%"+roomType.getRoomTypeDesc()+"%");
			}
	
		}
		
		Page<TbRoomType> page= (Page<TbRoomType>)roomTypeMapper.selectByExample(example);		
		return new PageResult(page.getTotal(), page.getResult());
	}
	
}
