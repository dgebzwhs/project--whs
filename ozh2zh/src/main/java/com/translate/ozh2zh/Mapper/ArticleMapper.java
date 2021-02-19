package com.translate.ozh2zh.Mapper;

import com.translate.ozh2zh.Pojo.Article;
import org.apache.ibatis.annotations.*;
import org.springframework.data.annotation.Id;

import java.util.List;
import java.util.Map;

/**
 * @author whs
 * @date 2020/5/6 0006 上午 9:13
 */

public interface ArticleMapper {
    @Insert("insert into `article` (file_name,createTime) values (#{fileName},now())")
    Integer addArticle(@Param("fileName") String fileName);



    @Select("SELECT article.id,article.file_name,article.createTime,examine_article.examine  FROM article LEFT JOIN examine_article ON article.file_name=examine_article.article_name and examine_article.status=1")
    List<Article> getAeticleList();

    @Update("update `article` set `url`=#{url} where file_name=#{fileName}")
    Integer updateArticle(@Param("url")String url,@Param("fileName")String fileName);

    @Insert("INSERT INTO `examine_article`(`article_name`, `upload`, `status`, `create_time`) VALUES (#{title}, #{upload}, 0, now())")
    Integer addExamine(@Param("title") String title, String upload);

    @Select("SELECT MAX(article.id) FROM article")
    Integer getMaxId();

    @Delete("Delete from `article` where file_name=#{fileName}")
    Integer delate(String fileName);

    @Select("SELECT examine_article.article_name,examine_article.upload, examine_article.examine, examine_article.`status`, examine_article.create_time, examine_article.update_time FROM examine_article ORDER BY create_time desc")
    @Results(
            value = {
                    @Result(column = "article_name", property = "name"),
                    @Result(column = "examine", property = "examine"),
                    @Result(column = "upload", property = "upload"),
                    @Result(column = "create_time", property = "uploadTime"),
                    @Result(column = "update_time", property = "examineTime"),
                    @Result(column = "status", property = "status")
            })
    List<Map<String, Object>> examineList(Integer pageNum, Integer pageSize);


    @Update("UPDATE `examine_article` SET status=#{status},reason=#{reason},examine=#{userName},update_time=now()  where article_name=#{fileName}")
    Integer examine(String fileName, Integer status, String reason, String userName);

//    @Update("UPDATE `examine_article` SET `articleName` = (SELECT article.file_name FROM article WHERE article.id = #{id}) WHERE `id` = #{id}")
//    Integer wybl(Integer id);
}
