package com.translate.ozh2zh.Service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.translate.ozh2zh.Mapper.ArticleMapper;
import com.translate.ozh2zh.Pojo.Article;
import com.translate.ozh2zh.Pojo.SysUser;
import com.translate.ozh2zh.Service.ArticleService;
import com.translate.ozh2zh.Service.SysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.*;
import java.util.List;
import java.util.Map;

/**
 * @author whs
 * @date 2020/5/6 0006 上午 9:12
 */
@Service
public class ArticleServiceImpl implements ArticleService {
    @Autowired
    private ArticleMapper articleMapper;
    @Autowired
    private SysUserService sysUserService;

    @Override
    public PageInfo<Article> getAeticleList(Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum,pageSize);
        List<Article> result= articleMapper.getAeticleList();

        return new PageInfo<Article>(result);
    }

    @Override
    public String[] getArticle(String fileName) {
        String path="D:\\myProject\\文言文翻译\\5156Data\\";
        File file = new File(path+fileName+".txt");
        String result="";
        try {
            InputStreamReader isr = new InputStreamReader(new FileInputStream(file), "GBK");
            BufferedReader br=new BufferedReader(isr);
            String line;
            while((line=br.readLine())!=null)
            {
                result+=line+"\n";
            }
        } catch (FileNotFoundException e) {
//            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        String[] resultList = result.split("原文|译文");
        return resultList;
    }

    @Override
    public Integer addArticle(String title, String yuan, String yi) {
        String path="D:\\myProject\\文言文翻译\\5156Data";
        File file = new File(path+"\\"+title+".txt");

        // 写入文件
        try {
            BufferedWriter bw= new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file), "GBK"));
            String data = null;
            bw.write("原文\n"+yuan+"\n译文\n"+yi);
            bw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        // 记录写入数据库

        // 获取当前请求用户
        SysUser user = sysUserService.currentUser();

        Integer result1 = articleMapper.addArticle(title);

//        // 获取存入的id
//        Integer id=articleMapper.getMaxId();


        Integer result2 = articleMapper.addExamine(title,user.getUsername());
        return result1+result2==2?1:0;
    }

    @Override
    public Integer delate(String fileName) {


        // 删除文章
        String path="D:\\myProject\\文言文翻译\\5156Data";
        File file = new File(path+"\\"+fileName+".txt");

        if (file.isFile() && file.exists()) {
            file.delete();
        }

        // 删除记录

        Integer count=articleMapper.delate(fileName);
        return count;
    }

    @Override
    public PageInfo<Map<String, Object>> examineList(Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum,pageSize);
        List<Map<String,Object>> result = articleMapper.examineList(pageNum,pageSize);
        return new PageInfo<>(result);
    }

    @Override
    public Integer examine(String fileName, Integer status, String reason) {

        String userName = sysUserService.currentUser().getUsername();

        Integer result=articleMapper.examine(fileName,status,reason,userName);

        if (status==0){
            delate(fileName);
        }

        return result;
    }


}
