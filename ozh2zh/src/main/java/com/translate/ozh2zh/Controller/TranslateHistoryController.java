package com.translate.ozh2zh.Controller;


import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.translate.ozh2zh.Pojo.TranslateHistory;
import com.translate.ozh2zh.Service.TranslateHistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author whs
 * @since 2020-05-27
 */
@RestController
@RequestMapping("/expert")
public class TranslateHistoryController {
    @Autowired
    private TranslateHistoryService translateHistoryService;

    @PostMapping("/list")
    public PageInfo<TranslateHistory> list(@RequestParam(defaultValue = "1")Integer pageNum, @RequestParam(defaultValue = "20")Integer pageSize){
//        PageHelper.startPage(pageNum,pageSize);

//        PageInfo<TranslateHistory> result = new PageInfo<>(translateHistoryService.list());
        PageInfo<TranslateHistory> result =   translateHistoryService.page(pageNum,pageSize);
        return result;
    }

    @PostMapping("/main")
    public List<String> main(Integer id){
        List<String> result = translateHistoryService.main(id);
        return result;
    }

    @PostMapping("/yes")
    public Integer yes(String yuan,String yi,Integer id){
        Integer result = translateHistoryService.yes(yuan,yi,id);
        return result;
    }
}
