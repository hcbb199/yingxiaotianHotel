package cn.neteast.yxtHotel.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import cn.neteast.yxtHotel.mapper.TbOrderMapper;
import cn.neteast.yxtHotel.pojo.TbOrder;
import cn.neteast.yxtHotel.pojo.TbOrderExample;
import cn.neteast.yxtHotel.pojoGroup.Room;
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
 *
 * @author Administrator
 */
@Service
public class RoomServiceImpl implements RoomService {

    @Autowired
    private TbRoomMapper roomMapper;
    @Autowired
    private TbOrderMapper orderMapper;

    /**
     * 查询全部
     */
    @Override
    public List<TbRoom> findAll() {

        TbRoomExample example = new TbRoomExample();
        //按照状态降序, 保证第一个为已入住的
        example.setOrderByClause("room_available desc");
        return roomMapper.selectByExample(example);


    }

    /**
     * 按分页查询
     */
    @Override
    public PageResult findPage(int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        Page<TbRoom> page = (Page<TbRoom>) roomMapper.selectByExample(null);
        return new PageResult(page.getTotal(), page.getResult());
    }

    /**
     * 增加
     */
    @Override
    public void add(TbRoom room) {
        room.setRoomAvailable((long) 1);
        roomMapper.insert(room);
    }


    /**
     * 修改
     */
    @Override
    public void update(TbRoom room) {
        roomMapper.updateByPrimaryKey(room);
    }

    /**
     * 根据ID获取实体
     *
     * @param id
     * @return
     */
    @Override
    public TbRoom findOne(Long id) {
        return roomMapper.selectByPrimaryKey(id);
    }

    /**
     * 批量删除
     */
    @Override
    public void delete(Long[] ids) {
        for (Long id : ids) {
            roomMapper.deleteByPrimaryKey(id);
        }
    }


    @Override
    public PageResult findPage(TbRoom room, int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);

        TbRoomExample example = new TbRoomExample();
        Criteria criteria = example.createCriteria();

        if (room != null) {
            if (room.getRoomNumber() != null && room.getRoomNumber().length() > 0) {
                criteria.andRoomNumberLike("%" + room.getRoomNumber() + "%");
            }
            if (room.getRoomDesc() != null && room.getRoomDesc().length() > 0) {
                criteria.andRoomDescLike("%" + room.getRoomDesc() + "%");
            }
            if (room.getRoomPic() != null && room.getRoomPic().length() > 0) {
                criteria.andRoomPicLike("%" + room.getRoomPic() + "%");
            }

        }

        Page<TbRoom> page = (Page<TbRoom>) roomMapper.selectByExample(example);
        return new PageResult(page.getTotal(), page.getResult());
    }

    /**
     * 查询所有房间及其当前订单
     *
     * @return
     */
    @Override
    public List<Room> findAllRoomAndOrder() {
        List<Room> roomsList = new ArrayList<>();
        TbRoomExample example = new TbRoomExample();
        //按照状态降序, 保证第一个为已入住的
        example.setOrderByClause("room_available asc");
        List<TbRoom> roomList = roomMapper.selectByExample(example);


        if (roomList != null && roomList.size() > 0) {
            for (TbRoom tbRoom : roomList) {
                if (tbRoom.getRoomAvailable() == 0) {

                    Long roomId = tbRoom.getRoomId();
                    TbOrderExample orderExample = new TbOrderExample();
                    TbOrderExample.Criteria criteria = orderExample.createCriteria();
                    criteria.andRoomIdEqualTo(roomId);
                    criteria.andOrderStatusEqualTo((long) 1);
                    List<TbOrder> tbOrders = orderMapper.selectByExample(orderExample);
                    if (tbOrders != null && tbOrders.size() > 0) {
                        Long leftTime = (tbOrders.get(0).getCheckoutDate().getTime()) - (new Date().getTime());
                        roomsList.add(new Room(tbRoom, tbOrders.get(0), (long) Math.floor(leftTime/1000)));
                    } else {
                        roomsList.add(new Room(tbRoom, null, (long) 0));
                    }

                } else {
                    roomsList.add(new Room(tbRoom, null, (long) 0));
                }

            }
        }
        return roomsList;
    }

}
