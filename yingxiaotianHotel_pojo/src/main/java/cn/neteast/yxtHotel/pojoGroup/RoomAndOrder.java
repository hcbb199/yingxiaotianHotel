package cn.neteast.yxtHotel.pojoGroup;

import cn.neteast.yxtHotel.pojo.TbOrder;
import cn.neteast.yxtHotel.pojo.TbRoom;


public class RoomAndOrder {
    private TbRoom room;
    private TbOrder order;

    public TbRoom getRoom() {
        return room;
    }

    @Override
    public String toString() {
        return "RoomAndOrder{" +
                "room=" + room +
                ", order=" + order +
                '}';
    }

    public void setRoom(TbRoom room) {
        this.room = room;
    }

    public TbOrder getOrder() {
        return order;
    }

    public void setOrder(TbOrder order) {
        this.order = order;
    }
}
