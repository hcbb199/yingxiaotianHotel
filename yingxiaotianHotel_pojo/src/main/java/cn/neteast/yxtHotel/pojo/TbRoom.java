package cn.neteast.yxtHotel.pojo;

import java.io.Serializable;

public class TbRoom  implements Serializable {
    private Long roomId;

    private String roomNumber;

    private Long roomTypeId;

    private String roomDesc;

    private String roomPic;

    private Integer roomPrice;

    private Long roomAvailable;

    public Long getRoomId() {
        return roomId;
    }

    public void setRoomId(Long roomId) {
        this.roomId = roomId;
    }

    public String getRoomNumber() {
        return roomNumber;
    }

    public void setRoomNumber(String roomNumber) {
        this.roomNumber = roomNumber == null ? null : roomNumber.trim();
    }

    public Long getRoomTypeId() {
        return roomTypeId;
    }

    public void setRoomTypeId(Long roomTypeId) {
        this.roomTypeId = roomTypeId;
    }

    public String getRoomDesc() {
        return roomDesc;
    }

    public void setRoomDesc(String roomDesc) {
        this.roomDesc = roomDesc == null ? null : roomDesc.trim();
    }

    public String getRoomPic() {
        return roomPic;
    }

    public void setRoomPic(String roomPic) {
        this.roomPic = roomPic == null ? null : roomPic.trim();
    }

    public Integer getRoomPrice() {
        return roomPrice;
    }

    public void setRoomPrice(Integer roomPrice) {
        this.roomPrice = roomPrice;
    }

    public Long getRoomAvailable() {
        return roomAvailable;
    }

    public void setRoomAvailable(Long roomAvailable) {
        this.roomAvailable = roomAvailable;
    }
}