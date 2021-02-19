package com.translate.ozh2zh.Service;

import java.util.List;
import java.util.Map;

/**
 * @author whs
 * @date 2020/4/16 0016 上午 10:24
 */
public interface TranslateService {
    String translate(String words);


    Map<String,Object> Participle(String words, boolean tranHelp, String fileName);

    Map<String, Object> biaodian(String text);
}
