package cn.neteast.yxtHotel.service.impl;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import cn.neteast.yxtHotel.mapper.TbRoomMapper;
import cn.neteast.yxtHotel.pojo.TbRoom;
import cn.neteast.yxtHotel.pojo.TbRoomExample;
import cn.neteast.yxtHotel.pojo.TbRoomExample.Criteria;
import cn.neteast.yxtHotel.service.RoomService;

import entity.PageResult;
import org.springframework.stereotype.Service;

/**
 * 服务实现层
 * @author Administrator
 *
 */
@Service
public class RoomServiceImpl implements RoomService {

	@Autowired
	private TbRoomMapper roomMapper;
	
	/**
	 * 查询全部
	 */
	@Override
	public List<TbRoom> findAll() {
		return roomMapper.selectByExample(null);
	}

	/**
	 * 按分页查询
	 */
	@Override
	public PageResult findPage(int pageNum, int pageSize) {
		PageHelper.startPage(pageNum, pageSize);		
		Page<TbRoom> page=   (Page<TbRoom>) roomMapper.selectByExample(null);
		return new PageResult(page.getTotal(), page.getResult());
	}

	/**
	 * 增加
	 */
	@Override
	public void add(TbRoom room) {
		roomMapper.insert(room);		
	}

	
	/**
	 * 修改
	 */
	@Override
	public void update(TbRoom room){
		roomMapper.updateByPrimaryKey(room);
	}	
	
	/**
	 * 根据ID获取实体
	 * @param id
	 * @return
	 */
	@Override
	public TbRoom findOne(Long id){
		return roomMapper.selectByPrimaryKey(id);
	}

	/**
	 * 批量删除
	 */
	@Override
	public void delete(Long[] ids) {
		for(Long id:ids){
			roomMapper.deleteByPrimaryKey(id);
		}		
	}
	
	
		@Override
	public PageResult findPage(TbRoom room, int pageNum, int pageSize) {
		PageHelper.startPage(pageNum, pageSize);
		
		TbRoomExample example=new TbRoomExample();
		Criteria criteria = example.createCriteria();
		
		if(room!=null){			
						if(room.getRoomNumber()!=null && room.getRoomNumber().length()>0){
				criteria.andRoomNumberLike("%"+room.getRoomNumber()+"%");
			}
			if(room.getRoomDesc()!=null && room.getRoomDesc().length()>0){
				criteria.andRoomDescLike("%"+room.getRoomDesc()+"%");
			}
			if(room.getRoomPic()!=null && room.getRoomPic().length()>0){
				criteria.andRoomPicLike("%"+room.getRoomPic()+"%");
			}
	
		}
		
		Page<TbRoom> page= (Page<TbRoom>)roomMapper.selectByExample(example);		
		return new PageResult(page.getTotal(), page.getResult());
	}
	
}
