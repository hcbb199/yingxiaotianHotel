package cn.neteast.yxtHotel.mapper;

import cn.neteast.yxtHotel.pojo.TbRoomType;
import cn.neteast.yxtHotel.pojo.TbRoomTypeExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface TbRoomTypeMapper {
    long countByExample(TbRoomTypeExample example);

    int deleteByExample(TbRoomTypeExample example);

    int deleteByPrimaryKey(Long roomTypeId);

    int insert(TbRoomType record);

    int insertSelective(TbRoomType record);

    List<TbRoomType> selectByExample(TbRoomTypeExample example);

    TbRoomType selectByPrimaryKey(Long roomTypeId);

    int updateByExampleSelective(@Param("record") TbRoomType record, @Param("example") TbRoomTypeExample example);

    int updateByExample(@Param("record") TbRoomType record, @Param("example") TbRoomTypeExample example);

    int updateByPrimaryKeySelective(TbRoomType record);

    int updateByPrimaryKey(TbRoomType record);
}