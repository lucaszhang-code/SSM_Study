package com.atguigu.pojo;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;

@TableName
@Data
public class User {
    @TableId(type = IdType.AUTO)    // 主键自增长，前提是主键设置了自增长
    private Long id;
    private String name;
    private Integer age;
    private String email;
    @TableLogic // 0是未删除，1是删除，查询数据时会默认只查询deleted=0
    private Integer deleted;
    @Version
    private Integer version;
}
