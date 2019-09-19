package cn.neteast.yxtHotel.service.impl;

import java.util.List;

import cn.neteast.yxtHotel.pojo.TbRoom;
import org.springframework.beans.factory.annotation.Autowired;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import cn.neteast.yxtHotel.mapper.TbOrderMapper;
import cn.neteast.yxtHotel.pojo.TbOrder;
import cn.neteast.yxtHotel.pojo.TbOrderExample;
import cn.neteast.yxtHotel.pojo.TbOrderExample.Criteria;
import cn.neteast.yxtHotel.service.OrderService;

import entity.PageResult;
import org.springframework.stereotype.Service;

/**
 * 服务实现层
 *
 * @author Administrator
 */
@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private TbOrderMapper orderMapper;

    /**
     * 查询全部
     */
    @Override
    public List<TbOrder> findAll() {
        return orderMapper.selectByExample(null);
    }

    /**
     * 按分页查询
     */
    @Override
    public PageResult findPage(int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        Page<TbOrder> page = (Page<TbOrder>) orderMapper.selectByExample(null);
        return new PageResult(page.getTotal(), page.getResult());
    }

    /**
     * 增加
     */
    @Override
    public void add(TbOrder order) {
        orderMapper.insert(order);
    }


    /**
     * 修改
     */
    @Override
    public void update(TbOrder order) {
        orderMapper.updateByPrimaryKey(order);
    }

    /**
     * 根据ID获取实体
     *
     * @param id
     * @return
     */
    @Override
    public TbOrder findOne(Long id) {
        return orderMapper.selectByPrimaryKey(id);
    }

    /**
     * 批量删除
     */
    @Override
    public void delete(Long[] ids) {
        for (Long id : ids) {
            orderMapper.deleteByPrimaryKey(id);
        }
    }


    @Override
    public PageResult findPage(TbOrder order, int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);

        TbOrderExample example = new TbOrderExample();
        Criteria criteria = example.createCriteria();

        if (order != null) {
            if (order.getCustomerName() != null && order.getCustomerName().length() > 0) {
                criteria.andCustomerNameLike("%" + order.getCustomerName() + "%");
            }
            if (order.getCustomerIdcard() != null && order.getCustomerIdcard().length() > 0) {
                criteria.andCustomerIdcardLike("%" + order.getCustomerIdcard() + "%");
            }

        }

        Page<TbOrder> page = (Page<TbOrder>) orderMapper.selectByExample(example);
        return new PageResult(page.getTotal(), page.getResult());
    }

    /**
     * 根据房间号查找该房间当前订单
     *
     * @param tbRoom
     * @return
     */
    @Override
    public TbOrder findOrderByRoom(TbRoom tbRoom) {
        Long roomId = tbRoom.getRoomId();
        TbOrderExample example = new TbOrderExample();
        Criteria criteria = example.createCriteria();
        criteria.andRoomIdEqualTo(roomId);
        criteria.andOrderStatusEqualTo((long) 1);
        List<TbOrder> tbOrders = orderMapper.selectByExample(example);
        if (tbOrders != null && tbOrders.size() > 0) {
            return tbOrders.get(0);
        } else {
            return null;
        }
    }
}
