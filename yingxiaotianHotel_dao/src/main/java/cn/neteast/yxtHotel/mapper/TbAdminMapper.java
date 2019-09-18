package cn.neteast.yxtHotel.mapper;

import cn.neteast.yxtHotel.pojo.TbAdmin;
import cn.neteast.yxtHotel.pojo.TbAdminExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface TbAdminMapper {
    long countByExample(TbAdminExample example);

    int deleteByExample(TbAdminExample example);

    int deleteByPrimaryKey(Long adminId);

    int insert(TbAdmin record);

    int insertSelective(TbAdmin record);

    List<TbAdmin> selectByExample(TbAdminExample example);

    TbAdmin selectByPrimaryKey(Long adminId);

    int updateByExampleSelective(@Param("record") TbAdmin record, @Param("example") TbAdminExample example);

    int updateByExample(@Param("record") TbAdmin record, @Param("example") TbAdminExample example);

    int updateByPrimaryKeySelective(TbAdmin record);

    int updateByPrimaryKey(TbAdmin record);
}