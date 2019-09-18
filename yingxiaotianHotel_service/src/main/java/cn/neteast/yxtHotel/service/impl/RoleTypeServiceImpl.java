package cn.neteast.yxtHotel.service.impl;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import cn.neteast.yxtHotel.mapper.TbRoleTypeMapper;
import cn.neteast.yxtHotel.pojo.TbRoleType;
import cn.neteast.yxtHotel.pojo.TbRoleTypeExample;
import cn.neteast.yxtHotel.pojo.TbRoleTypeExample.Criteria;
import cn.neteast.yxtHotel.service.RoleTypeService;

import entity.PageResult;
import org.springframework.stereotype.Service;

/**
 * 服务实现层
 * @author Administrator
 *
 */
@Service
public class RoleTypeServiceImpl implements RoleTypeService {

	@Autowired
	private TbRoleTypeMapper roleTypeMapper;
	
	/**
	 * 查询全部
	 */
	@Override
	public List<TbRoleType> findAll() {
		return roleTypeMapper.selectByExample(null);
	}

	/**
	 * 按分页查询
	 */
	@Override
	public PageResult findPage(int pageNum, int pageSize) {
		PageHelper.startPage(pageNum, pageSize);		
		Page<TbRoleType> page=   (Page<TbRoleType>) roleTypeMapper.selectByExample(null);
		return new PageResult(page.getTotal(), page.getResult());
	}

	/**
	 * 增加
	 */
	@Override
	public void add(TbRoleType roleType) {
		roleTypeMapper.insert(roleType);		
	}

	
	/**
	 * 修改
	 */
	@Override
	public void update(TbRoleType roleType){
		roleTypeMapper.updateByPrimaryKey(roleType);
	}	
	
	/**
	 * 根据ID获取实体
	 * @param id
	 * @return
	 */
	@Override
	public TbRoleType findOne(Long id){
		return roleTypeMapper.selectByPrimaryKey(id);
	}

	/**
	 * 批量删除
	 */
	@Override
	public void delete(Long[] ids) {
		for(Long id:ids){
			roleTypeMapper.deleteByPrimaryKey(id);
		}		
	}
	
	
		@Override
	public PageResult findPage(TbRoleType roleType, int pageNum, int pageSize) {
		PageHelper.startPage(pageNum, pageSize);
		
		TbRoleTypeExample example=new TbRoleTypeExample();
		Criteria criteria = example.createCriteria();
		
		if(roleType!=null){			
						if(roleType.getRoleType()!=null && roleType.getRoleType().length()>0){
				criteria.andRoleTypeLike("%"+roleType.getRoleType()+"%");
			}
			if(roleType.getRoleDesc()!=null && roleType.getRoleDesc().length()>0){
				criteria.andRoleDescLike("%"+roleType.getRoleDesc()+"%");
			}
	
		}
		
		Page<TbRoleType> page= (Page<TbRoleType>)roleTypeMapper.selectByExample(example);		
		return new PageResult(page.getTotal(), page.getResult());
	}
	
}
