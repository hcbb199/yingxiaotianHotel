package cn.neteast.domain;

public class TbRoomType {
    private Long roomTypeId;

    private String roomTypeDesc;

    public Long getRoomTypeId() {
        return roomTypeId;
    }

    public void setRoomTypeId(Long roomTypeId) {
        this.roomTypeId = roomTypeId;
    }

    public String getRoomTypeDesc() {
        return roomTypeDesc;
    }

    public void setRoomTypeDesc(String roomTypeDesc) {
        this.roomTypeDesc = roomTypeDesc == null ? null : roomTypeDesc.trim();
    }
}