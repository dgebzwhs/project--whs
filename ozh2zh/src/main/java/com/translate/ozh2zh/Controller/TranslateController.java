package com.translate.ozh2zh.Controller;

import com.alibaba.fastjson.JSONArray;
import com.translate.ozh2zh.Service.TranslateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

/**
 * @author whs
 * @date 2020/4/16 0016 上午 10:23
 */
@RestController
public class TranslateController {
    @Autowired
    private TranslateService translateService;

    /**
     * 翻译
     */
    @RequestMapping("/translate")
    public String translate(@RequestParam String words){
        return words.equals("")?null:translateService.translate(words);
    }

    /**
     *  分词
     * @param words
     * @return
     */
    @RequestMapping("/Participle")
    public Map<String,Object> Participle(@RequestParam String words){
        return words.equals("")?null:translateService.Participle(words,false,"");
    }

//
//    @PostMapping("/cibiao")
//    public String[] cibiao(List<String> wordList){
//        System.out.println(wordList);
//        return null;
////        return translateService.cibiao(wordList);
//    }


    @PostMapping("/biaodian")
    public Map<String,Object> biaodian(String text){
        return translateService.biaodian(text);
    }
}
