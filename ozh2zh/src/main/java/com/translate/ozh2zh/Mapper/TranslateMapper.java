package com.translate.ozh2zh.Mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

/**
 * @author whs
 * @date 2020/4/16 0016 上午 10:26
 */
@Mapper
public interface TranslateMapper {
    @Insert("INSERT INTO `translate_history`(`file_name`, `text`, `create_time`) VALUES (#{fileName}, #{text}, now())")
    Integer addHistory(String fileName, String text);
}
