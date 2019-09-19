package cn.neteast.yxtHotel.pojoGroup;

import cn.neteast.yxtHotel.pojo.TbOrder;
import cn.neteast.yxtHotel.pojo.TbRoom;

import java.io.Serializable;

public class Room implements Serializable {
    private TbRoom tbRome;
    private TbOrder order;

    public Room() {
    }

    public Room(TbRoom tbRome, TbOrder order) {
        this.tbRome = tbRome;
        this.order = order;
    }

    public TbRoom getTbRome() {
        return tbRome;
    }

    public void setTbRome(TbRoom tbRome) {
        this.tbRome = tbRome;
    }

    public TbOrder getOrder() {
        return order;
    }

    public void setOrder(TbOrder order) {
        this.order = order;
    }
}
