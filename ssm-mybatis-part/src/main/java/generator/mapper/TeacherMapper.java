package generator.mapper;

import generator.com.itguigu.pojo.Teacher;

/**
* @author Lucas
* @description 针对表【teacher】的数据库操作Mapper
* @createDate 2024-10-07 14:42:41
* @Entity generator.com.itguigu.pojo.Teacher
*/
public interface TeacherMapper {

    int deleteByPrimaryKey(Long id);

    int insert(Teacher record);

    int insertSelective(Teacher record);

    Teacher selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Teacher record);

    int updateByPrimaryKey(Teacher record);

}
