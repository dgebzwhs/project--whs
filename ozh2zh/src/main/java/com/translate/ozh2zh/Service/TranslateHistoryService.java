package com.translate.ozh2zh.Service;

import com.github.pagehelper.PageInfo;
import com.translate.ozh2zh.Pojo.TranslateHistory;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author whs
 * @since 2020-05-27
 */
public interface TranslateHistoryService extends IService<TranslateHistory> {

    PageInfo<TranslateHistory> page(Integer pageNum, Integer pageSize);

    List<String> main(Integer id);

    Integer yes(String yuan, String yi, Integer id);
}
