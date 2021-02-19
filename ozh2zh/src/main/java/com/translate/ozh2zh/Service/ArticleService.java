package com.translate.ozh2zh.Service;

import com.github.pagehelper.PageInfo;
import com.translate.ozh2zh.Pojo.Article;

import java.util.Map;

/**
 * @author whs
 * @date 2020/5/6 0006 上午 9:12
 */
public interface ArticleService {
    PageInfo<Article> getAeticleList(Integer pageNum, Integer pageSize);

    String[] getArticle(String fileName);

    Integer addArticle(String title, String yuan, String yi);

    Integer delate(String fileName);

    PageInfo<Map<String, Object>> examineList(Integer pageNum, Integer pageSize);

    Integer examine(String fileName, Integer status, String reason);
}
