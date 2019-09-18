package cn.neteast.yxtHotel.mapper;

import cn.neteast.domain.TbRoleType;
import cn.neteast.domain.TbRoleTypeExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

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