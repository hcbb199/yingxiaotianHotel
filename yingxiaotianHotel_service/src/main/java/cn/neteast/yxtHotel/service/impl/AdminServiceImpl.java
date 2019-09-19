package cn.neteast.yxtHotel.service.impl;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import cn.neteast.yxtHotel.mapper.TbAdminMapper;
import cn.neteast.yxtHotel.pojo.TbAdmin;
import cn.neteast.yxtHotel.pojo.TbAdminExample;
import cn.neteast.yxtHotel.pojo.TbAdminExample.Criteria;
import cn.neteast.yxtHotel.service.AdminService;

import entity.PageResult;
import org.springframework.stereotype.Service;

/**
 * 服务实现层
 * @author Administrator
 *
 */
@Service
public class AdminServiceImpl implements AdminService {

	@Autowired
	private TbAdminMapper adminMapper;
	
	/**
	 * 查询全部
	 */
	@Override
	public List<TbAdmin> findAll() {
		return adminMapper.selectByExample(null);
	}

	/**
	 * 按分页查询
	 */
	@Override
	public PageResult findPage(int pageNum, int pageSize) {
		PageHelper.startPage(pageNum, pageSize);		
		Page<TbAdmin> page=   (Page<TbAdmin>) adminMapper.selectByExample(null);
		return new PageResult(page.getTotal(), page.getResult());
	}

	/**
	 * 增加
	 */
	@Override
	public void add(TbAdmin admin) {
		adminMapper.insert(admin);		
	}

	
	/**
	 * 修改
	 */
	@Override
	public void update(TbAdmin admin){
		adminMapper.updateByPrimaryKey(admin);
	}	
	
	/**
	 * 根据ID获取实体
	 * @param id
	 * @return
	 */
	@Override
	public TbAdmin findOne(Long id){
		return adminMapper.selectByPrimaryKey(id);
	}

	/**
	 * 批量删除
	 */
	@Override
	public void delete(Long[] ids) {
		for(Long id:ids){
			adminMapper.deleteByPrimaryKey(id);
		}		
	}
	
	
		@Override
	public PageResult findPage(TbAdmin admin, int pageNum, int pageSize) {
		PageHelper.startPage(pageNum, pageSize);
		
		TbAdminExample example=new TbAdminExample();
		Criteria criteria = example.createCriteria();
		
		if(admin!=null){			
						if(admin.getUsername()!=null && admin.getUsername().length()>0){
				criteria.andUsernameLike("%"+admin.getUsername()+"%");
			}
			if(admin.getPassword()!=null && admin.getPassword().length()>0){
				criteria.andPasswordLike("%"+admin.getPassword()+"%");
			}
	
		}
		
		Page<TbAdmin> page= (Page<TbAdmin>)adminMapper.selectByExample(example);		
		return new PageResult(page.getTotal(), page.getResult());
	}

	@Override
	public TbAdmin findOneByUsername(String username) {
		TbAdminExample example = new TbAdminExample();
		Criteria criteria = example.createCriteria();
		criteria.andUsernameEqualTo(username);
		List<TbAdmin> tbAdmins = adminMapper.selectByExample(example);
		if (tbAdmins != null && tbAdmins.size() > 0) {
			return tbAdmins.get(0);
		} else {
			return null;
		}
	}
}
