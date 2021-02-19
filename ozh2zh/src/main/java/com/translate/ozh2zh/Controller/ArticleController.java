package com.translate.ozh2zh.Controller;

import com.github.pagehelper.PageInfo;
import com.translate.ozh2zh.Pojo.Article;
import com.translate.ozh2zh.Pojo.SysResult;
import com.translate.ozh2zh.Service.ArticleService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.RolesAllowed;
import java.util.List;
import java.util.Map;

/**
 * @author whs
 * @date 2020/5/6 0006 上午 9:11
 */
@RestController
@RequestMapping("/article")
public class ArticleController {
    @Autowired
    private ArticleService articleService;

    @RolesAllowed("Role_admins")
    @PostMapping("/getList")
    public PageInfo<Article> getAeticleList(@RequestParam(defaultValue = "1")Integer pageNum, @RequestParam(defaultValue = "20")Integer pageSize){
        PageInfo<Article> returnThings=articleService.getAeticleList(pageNum,pageSize);
        return returnThings;
    }

    @PostMapping("/getArticle")
    public String[] getArticle(String fileName){
        String[] text=articleService.getArticle(fileName);
        return text;
    }

    @PostMapping("/add")
    public Integer addArticle(String title,String yuan,String yi){
        Integer returnThings=articleService.addArticle(title,yuan,yi);
        return returnThings;
    }

    @PostMapping("/delate/{fileName}")
    public SysResult delate(@PathVariable("fileName") String fileName){
        Integer count = articleService.delate(fileName);
        return SysResult.ok(count);
    }


    @PostMapping("/examineList")
    public PageInfo<Map<String,Object>> examineList(@RequestParam(defaultValue = "1")Integer pageNum, @RequestParam(defaultValue = "20")Integer pageSize){
        PageInfo<Map<String,Object>> result = articleService.examineList(pageNum,pageSize);
        return result;
    }

    @PostMapping("/examine")
    public SysResult examine(String fileName,Integer status,String reason){
        Integer result = articleService.examine(fileName,status,reason);
        return SysResult.ok(result);
    }

}
