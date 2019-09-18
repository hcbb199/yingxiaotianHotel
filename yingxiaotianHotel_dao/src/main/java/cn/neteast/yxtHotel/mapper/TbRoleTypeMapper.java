package cn.neteast.yxtHotel.mapper;

import cn.neteast.yxtHotel.pojo.TbRoleType;
import cn.neteast.yxtHotel.pojo.TbRoleTypeExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface TbRoleTypeMapper {
    long countByExample(TbRoleTypeExample example);

    int deleteByExample(TbRoleTypeExample example);

    int deleteByPrimaryKey(Long roleTypeId);

    int insert(TbRoleType record);

    int insertSelective(TbRoleType record);

    List<TbRoleType> selectByExample(TbRoleTypeExample example);

    TbRoleType selectByPrimaryKey(Long roleTypeId);

    int updateByExampleSelective(@Param("record") TbRoleType record, @Param("example") TbRoleTypeExample example);

    int updateByExample(@Param("record") TbRoleType record, @Param("example") TbRoleTypeExample example);

    int updateByPrimaryKeySelective(TbRoleType record);

    int updateByPrimaryKey(TbRoleType record);
}