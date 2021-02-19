package com.translate.ozh2zh.Service.impl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.translate.ozh2zh.Mapper.SysUserMapper;
import com.translate.ozh2zh.Pojo.TranslateHistory;
import com.translate.ozh2zh.Mapper.TranslateHistoryMapper;
import com.translate.ozh2zh.Service.SysUserService;
import com.translate.ozh2zh.Service.TranslateHistoryService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.translate.ozh2zh.Uitls.GetRunTimeStream;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author whs
 * @since 2020-05-27
 */
@Service
public class TranslateHistoryServiceImpl extends ServiceImpl<TranslateHistoryMapper, TranslateHistory> implements TranslateHistoryService {

    @Autowired
    private TranslateHistoryMapper translateHistoryMapper;
    @Autowired
    private SysUserService sysUserService;

    @Override
    public PageInfo<TranslateHistory> page(Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum,pageSize);
        List<TranslateHistory>  result=translateHistoryMapper.list();
        return new PageInfo<>(result);
    }

    @Override
    public List<String> main(Integer id) {

        // 查出id 对应的文件名

        String filePath="D:\\myProject\\pythonProject\\decoder\\";
        String fileName = translateHistoryMapper.selectById(id).getFileName();
        String question = GetRunTimeStream.readFile(filePath + fileName + ".q");
        String answer = GetRunTimeStream.readFile(filePath + fileName + ".a");
        return Arrays.asList(new String[]{question,answer});
    }

    @Override
    public Integer yes(String yuan, String yi, Integer id) {


        TranslateHistory translateHistory = new TranslateHistory();
        translateHistory.setStatus(true);
        translateHistory.setExpert(sysUserService.currentUser().getUsername());

        // 写入状态
        Integer result = translateHistoryMapper.update(translateHistory,new QueryWrapper<TranslateHistory>().eq("id",id));

        // 写入语料
        String filePath="D:\\myProject\\pythonProject\\corpus\\";

        GetRunTimeStream.writeFile(filePath+"Ozh.corpus",yuan);
        GetRunTimeStream.writeFile(filePath+"Zh.corpus",yi);

        return result;
    }
}
