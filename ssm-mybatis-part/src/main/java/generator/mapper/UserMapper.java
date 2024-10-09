package generator.mapper;

import generator.com.itguigu.pojo.User;

/**
* @author Lucas
* @description 针对表【user】的数据库操作Mapper
* @createDate 2024-10-07 14:42:41
* @Entity generator.com.itguigu.pojo.User
*/
public interface UserMapper {

    int deleteByPrimaryKey(Long id);

    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);

}
