package cn.neteast.yxtHotel.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import cn.neteast.yxtHotel.mapper.TbRoomMapper;
import cn.neteast.yxtHotel.pojo.TbRoom;
import cn.neteast.yxtHotel.pojo.TbRoomExample;
import cn.neteast.yxtHotel.pojoGroup.RoomAndOrder;
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

    @Autowired
    private TbRoomMapper roomMapper;
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
    @Override
    public void checkIn(TbOrder order) {

       /* System.out.println(order.toString());
        System.out.println("调用入住方法");
        System.out.println("前端传入的order类 "+order);*/

        order.setOrderStatus(1L);
        order.setCreateTime(new Date());
        order.setCheckinDate(new Date());
        orderMapper.insert(order);
        TbRoom room = roomMapper.selectByPrimaryKey(order.getRoomId());
        room.setRoomAvailable(0L);
        roomMapper.updateByPrimaryKey(room);
    }

    /**
     * 退房方法
     * @param room 传入对应的Room类房间信息
     *             ---此处注意，传入的Room类必须有房间编号(RoomId)成员---
     */
    @Override
    public void checkOut(TbRoom room) {

        System.out.println("调用退房方法");
        System.out.println("前端传入的room类 "+room);

        TbOrderExample example = new TbOrderExample();
        TbOrderExample.Criteria criteria = example.createCriteria();
        criteria.andRoomIdEqualTo(room.getRoomId());
        List<TbOrder> orders = orderMapper.selectByExample(example);
        for (TbOrder order : orders) {
            if(order.getOrderStatus()==1L){
                Long orderId = order.getOrderId();
                System.out.println(orderId);
                order.setOrderStatus(0L);
                orderMapper.updateByPrimaryKey(order);
            }
        }

        TbRoom tbRoom = roomMapper.selectByPrimaryKey(room.getRoomId());
        tbRoom.setRoomAvailable(1L);
        roomMapper.updateByPrimaryKey(tbRoom);
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
    @Override
    public TbRoom findByNameOrRoomId(String nameOrRoomNum) {

        System.out.println("调用条件查找房间方法");
        System.out.println("前端传入的条件: "+nameOrRoomNum);

        for (int i = 1; i <= 9; i++) {
            if(nameOrRoomNum.startsWith(i+"")){
                //进入此if则表示传入的查询条件是房号
                TbRoomExample roomExample = new TbRoomExample();
                TbRoomExample.Criteria criteria = roomExample.createCriteria();
                criteria.andRoomNumberEqualTo(nameOrRoomNum);
                List<TbRoom> tbRooms = roomMapper.selectByExample(roomExample);
                TbRoom room = null;
                try {
                    room = tbRooms.get(0);
                } catch (IndexOutOfBoundsException e) {
                    System.out.println("根据房号查询,未查询到数据");
                    break;
                }
                System.out.println("根据房号查到房间 "+room);
                return room;
            }
        }

        //运行至此则表明传入的查询条件是客户姓名
        TbOrderExample orderExample = new TbOrderExample();
        TbOrderExample.Criteria criteria = orderExample.createCriteria();
        criteria.andCustomerNameEqualTo(nameOrRoomNum);
        List<TbOrder> orders = orderMapper.selectByExample(orderExample);
        for (TbOrder order : orders) {
            if(order.getOrderStatus().equals(1L)){
                Long roomId = order.getRoomId();

                System.out.println("根据客户姓名查到房间编号为: "+roomId);

                TbRoom room = roomMapper.selectByPrimaryKey(roomId);

                System.out.println("根据房间编号(ID)查到的房间: "+room);
                return room;
            }
        }
        //未查询到结果
        System.out.println("未查询到结果");
        return null;
    }

    /**
     * 需求 2
     * 展示总房间数及房间状态
     * @return 返回值为一个JSON字符串
     *
     * ---返回之前会打印到控制台，请验证JSON---
     */
    @Override
    public String showRoomTotalCount() {
        System.out.println("调用查询房间数方法");
        //查询空房数
        TbRoomExample roomExample = new TbRoomExample();
        TbRoomExample.Criteria criteria = roomExample.createCriteria();
        criteria.andRoomAvailableEqualTo(1L);
        long emptyRoomCount = roomMapper.countByExample(roomExample);

        //查询已入住的房间数
        TbRoomExample roomExample1 = new TbRoomExample();
        TbRoomExample.Criteria criteria1 = roomExample1.createCriteria();
        criteria1.andRoomAvailableEqualTo(0L);
        long checkInRoomCount = roomMapper.countByExample(roomExample1);

        //相加获得总房间数
        long allRoomCount = emptyRoomCount + checkInRoomCount;
        System.out.println("空房间数: "+emptyRoomCount);
        System.out.println("已入住房间数: "+checkInRoomCount);

        //返回JSON字符串
        String roomCount = "{\"roomCount\":[{\"emptyRoomCount\":"+emptyRoomCount+"},{\"checkInRoomCount\":"+checkInRoomCount+"},{\"allRoomCount\":"+allRoomCount+"}]}";
        System.out.println("已拼接好的JSON字符串: "+roomCount);
        return roomCount;
    }

    /**
     * 需求 3
     * 展示所有在住客人的列表
     * @return 返回所有在住客人的列表
     */
    @Override
    public List<RoomAndOrder> showAllCheckInCustomer() {

        System.out.println("调用查询在住客户方法");

        //创建组合实体类集合
        List<RoomAndOrder> resultList = new ArrayList<RoomAndOrder>();

        //查询在住客人列表
        TbOrderExample orderExample = new TbOrderExample();
        TbOrderExample.Criteria criteria = orderExample.createCriteria();
        criteria.andOrderStatusEqualTo(1L);
        List<TbOrder> orders = orderMapper.selectByExample(orderExample);

        System.out.println("在住客户列表↓");

        for (TbOrder order : orders) {
            System.out.println(order);
        }

        System.out.println();
        System.out.println("组合实体类列表↓");

        for (TbOrder order : orders) {
            TbRoom room = roomMapper.selectByPrimaryKey(order.getRoomId());
            RoomAndOrder roomAndOrder = new RoomAndOrder();
            roomAndOrder.setRoom(room);
            roomAndOrder.setOrder(order);
            resultList.add(roomAndOrder);
            System.out.println(roomAndOrder);
        }

        return resultList;
    }

    /**
     * 需求 3
     * 展示历史客户列表(不包含在住客户)
     * @return 返回历史客户列表
     */
    @Override
    public List<RoomAndOrder> showAllHistoryCustomer() {

        System.out.println("调用查询历史客户方法");

        //创建组合实体类集合
        List<RoomAndOrder> resultList = new ArrayList<>();

        TbOrderExample orderExample = new TbOrderExample();
        TbOrderExample.Criteria criteria = orderExample.createCriteria();
        criteria.andOrderStatusEqualTo(0L);
        List<TbOrder> orders = orderMapper.selectByExample(orderExample);

        System.out.println("历史客户列表↓");

        for (TbOrder order : orders) {
            System.out.println(order);
        }

        System.out.println();
        System.out.println("组合实体类列表↓");

        for (TbOrder order : orders) {
            TbRoom room = roomMapper.selectByPrimaryKey(order.getRoomId());
            RoomAndOrder roomAndOrder = new RoomAndOrder();
            roomAndOrder.setRoom(room);
            roomAndOrder.setOrder(order);
            resultList.add(roomAndOrder);
            System.out.println(roomAndOrder);
        }

        return resultList;
    }

    /**
     * 需求 4
     * 展示所有的房间。如果是已入住房间，返回到期时间信息
     * @return 返回一个组合实体类，空房只返回room，入住房返回room和order
     */
    @Override
    public List<RoomAndOrder> showAllRoom(){

        System.out.println("调用展示所有房间的方法");

        //创建组合实体类集合
        List<RoomAndOrder> resultList = new ArrayList<RoomAndOrder>();
        //查询所有房间
        TbRoomExample roomExample = new TbRoomExample();
        TbRoomExample.Criteria criteria = roomExample.createCriteria();
        criteria.andRoomNumberIsNotNull();
        List<TbRoom> rooms = roomMapper.selectByExample(roomExample);

        System.out.println("查询到所有房间的数据↓");

        for (TbRoom room : rooms) {
            System.out.println(room);
        }

        //此处开始筛选入住房间和空房
        for (TbRoom room : rooms) {
            if(room.getRoomAvailable().equals(0L)){
                System.out.println("入住房+1");
                //进入此处为入住房，传回room和order对象
                TbOrderExample orderExample = new TbOrderExample();
                TbOrderExample.Criteria criteria1 = orderExample.createCriteria();
                criteria1.andRoomIdEqualTo(room.getRoomId());
                List<TbOrder> orders = orderMapper.selectByExample(orderExample);
                RoomAndOrder roomAndOrder = new RoomAndOrder();
                roomAndOrder.setOrder(orders.get(0));
                roomAndOrder.setRoom(room);
                resultList.add(roomAndOrder);
            }else {
                //进入此处为空房，只传回room
                System.out.println("空房+1");
                RoomAndOrder roomAndOrder = new RoomAndOrder();
                roomAndOrder.setRoom(room);
                resultList.add(roomAndOrder);
            }
        }
        return resultList;
    }

    /**
     * 需求 5
     * 展示所有的空房间
     * @return 返回包含有所有空房间的列表
     */
    @Override
    public List<TbRoom> showAllEmptyRoom(){

        System.out.println("调用展示所有空房间的方法");

        TbRoomExample roomExample = new TbRoomExample();
        TbRoomExample.Criteria criteria = roomExample.createCriteria();
        criteria.andRoomAvailableEqualTo(1L);
        List<TbRoom> rooms = roomMapper.selectByExample(roomExample);

        System.out.println("查询到的所有空房间数据↓");

        for (TbRoom room : rooms) {
            System.out.println(room);
        }

        return rooms;
    }

    /**
     * 查询单个房间组合订单的信息(主要在前端的续住功能回显数据上使用)
     * @param roomId 传入一个房间ID，请提前在前端页面隐藏域中绑定
     * @return 返回一个房间和订单的组合实体类
     */
    @Override
    public RoomAndOrder findOneRoom(Long roomId) {

        System.out.println("调用查询单个房间组合类的方法");

        //创建组合实体类
        RoomAndOrder roomAndOrder = new RoomAndOrder();

        TbRoom room = roomMapper.selectByPrimaryKey(roomId);

        TbOrderExample orderExample = new TbOrderExample();
        TbOrderExample.Criteria criteria = orderExample.createCriteria();
        criteria.andRoomIdEqualTo(roomId);
        List<TbOrder> orders = orderMapper.selectByExample(orderExample);

        roomAndOrder.setOrder(orders.get(0));
        roomAndOrder.setRoom(room);

        System.out.println("查询到的组合类: "+roomAndOrder);

        return roomAndOrder;
    }

    /**
     * 前端传回修改好的数据，对数据库进行修改的方法(用于续住功能)
     * @param order 传入一个订单类
     *              ---注意---
     *              roomId 房间ID
     *              checkOutDate 退房日期
     *              ---以上两个成员必须传入!!!---
     */
    @Override
    public void changeDate(TbOrder order) {

        System.out.println("调用修改房间日期方法");

        /*TbOrderExample orderExample = new TbOrderExample();
        TbOrderExample.Criteria criteria = orderExample.createCriteria();
        criteria.andRoomIdEqualTo(order.getRoomId());
        List<TbOrder> orders = orderMapper.selectByExample(orderExample);
        for (TbOrder tbOrder : orders) {
            if(tbOrder.getOrderStatus().equals(1L)){
                System.out.println("未修改的数据: "+tbOrder);
                tbOrder.setCheckoutDate(order.getCheckoutDate());
                System.out.println("修改后的数据: "+tbOrder);
                orderMapper.updateByPrimaryKey(tbOrder);
            }
        }*/

        Date checkoutDate = order.getCheckoutDate();
        TbOrder tbOrder = orderMapper.selectByPrimaryKey(order.getOrderId());
        tbOrder.setCheckoutDate(checkoutDate);
        orderMapper.updateByPrimaryKey(order);
    }

}
