package com.translate.ozh2zh.Mapper;

import com.translate.ozh2zh.Pojo.TranslateHistory;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author whs
 * @since 2020-05-27
 */
public interface TranslateHistoryMapper extends BaseMapper<TranslateHistory> {

    List<TranslateHistory> list();
}
