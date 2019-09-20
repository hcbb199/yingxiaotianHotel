package cn.neteast.yxtHotel.pojoGroup;

import cn.neteast.yxtHotel.pojo.TbOrder;
import cn.neteast.yxtHotel.pojo.TbRoom;

import java.io.Serializable;

public class Room implements Serializable {
    private TbRoom tbRoom;
    private TbOrder order;
    private Long leftTime;
    public Room() {
    }

    public Room(TbRoom tbRoom, TbOrder order, Long leftTime) {
        this.tbRoom = tbRoom;
        this.order = order;
        this.leftTime = leftTime;
    }

    public void setTbRoom(TbRoom tbRoom) {
        this.tbRoom = tbRoom;
    }

    public void setOrder(TbOrder order) {
        this.order = order;
    }

    public void setLeftTime(Long leftTime) {
        this.leftTime = leftTime;
    }

    public TbRoom getTbRoom() {
        return tbRoom;
    }

    public TbOrder getOrder() {
        return order;
    }

    public Long getLeftTime() {
        return leftTime;
    }
}
