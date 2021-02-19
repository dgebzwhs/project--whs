package com.translate.ozh2zh.Service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.translate.ozh2zh.Mapper.TranslateMapper;
import com.translate.ozh2zh.Service.TranslateService;
import com.translate.ozh2zh.Uitls.GetRunTimeStream;
import com.translate.ozh2zh.Uitls.MD5Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.*;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * @author whs
 * @date 2020/4/16 0016 上午 10:24
 */
@Service
public class TranslateServiceImpl implements TranslateService {
    @Autowired
    private TranslateMapper translateMapper;



    @Override
    public String translate(String words)  {
        // 根据当前时间戳和输入文字 加密构建不重复的串

        String text = "";
        if(words.length()<20){
            text=words;
        }else if(words.length()>=20){
            text=words.substring(0,20);
        }
        String fileName = MD5Utils.md5(text+System.currentTimeMillis());

        System.out.println(fileName);
        // 先调用分词
        this.Participle(words,true,fileName);

        // 然后调用翻译命令
        Runtime run=Runtime.getRuntime();
        // 拼接cmd命令
        String pythonLocation="D:\\DevelopmentTools\\anaconda\\envs\\threesix\\python.exe";
        // String packageName="I:\\biyelunwen\\pythonProject\\";
        String packageName="D:\\myProject\\pythonProject\\";
        String cmdRun=pythonLocation+" D:\\DevelopmentTools\\anaconda\\envs\\threesix\\Scripts\\t2t-decoder " +
                "--data_dir="+packageName+"trainData " +
                "--problem=my_problem " +
                "--t2t_usr_dir="+packageName+"/uset2t " +
                "--model=transformer --hparams_set=transformer_small " +
                "--output_dir="+packageName+"/trainCheckpoint " +
                "--decode_from_file="+packageName+"/decoder/"+fileName+".q " +
                "--decode_to_file="+packageName+"/decoder/"+fileName+".a " +
                "--checkpoint_path="+packageName+"/trainCheckpoint/model.ckpt-3000";
        try {
            // 获取输入流
            Process proc=run.exec(cmdRun);
            if (proc!=null){
                proc.getOutputStream().close();
            }
            String s = GetRunTimeStream.getErrorText(proc);
        }catch(IOException e){
            e.printStackTrace();
        }

        // 翻译完成后，写入表中
        Integer count = translateMapper.addHistory(fileName,text);

        // 读取翻译完成的内容，返回前端页面
        String result="";
        try {
            BufferedReader br= new BufferedReader(new FileReader(packageName+"/decoder/"+fileName+".a"));
            String data = null;
            while((data = br.readLine())!=null)
            {
                result+=data+"\n";
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        result = result.trim();
        return result.equals("")?null:result;
    }

    @Override
    public Map<String,Object> Participle(String words, boolean tranHelp,String fileName) {

        words=words.replace("\n","\\n");
        Runtime run=Runtime.getRuntime();

        String pythonLocation="D:\\DevelopmentTools\\anaconda\\envs\\threesix\\python.exe";
//        String pyPath=" I:\\biyelunwen\\pythonProject\\preHandle\\testjiayan.py";
        String pyPath=" D:\\myProject\\pythonProject\\preHandle\\testjiayan.py";
        String cmdRun="cmd /c   "+pythonLocation+pyPath+" --text="+words
                +" --type=tokenizing ";
        if (tranHelp){
            cmdRun+="--trorto --fileName="+fileName;
        }

        String result="";
        try {
            // 获取输入流
            Process proc=run.exec(cmdRun);
            result = GetRunTimeStream.getInputText(proc);
            System.out.println(GetRunTimeStream.getErrorText(proc));
        }catch(IOException e){
            e.printStackTrace();
        }
        Map<String,Object> map= (Map<String, Object>) JSONObject.parse(result);



        return tranHelp?null:map;
    }

    @Override
    public Map<String, Object> biaodian(String text) {

        Runtime run=Runtime.getRuntime();

        String pythonLocation="D:\\DevelopmentTools\\anaconda\\envs\\threesix\\python.exe";
//        String pyPath=" I:\\biyelunwen\\pythonProject\\preHandle\\testjiayan.py";
        String pyPath=" D:\\myProject\\pythonProject\\preHandle\\testjiayan.py";
        String cmdRun="cmd /c   "+pythonLocation+pyPath+" --text="+text
                +" --type=CRFPunctuator ";

        String result="";
        try {
            // 获取输入流
            Process proc=run.exec(cmdRun);
            result = GetRunTimeStream.getInputText(proc);
            System.out.println(GetRunTimeStream.getErrorText(proc));
        }catch(IOException e){
            e.printStackTrace();
        }
        Map<String,Object> map= (Map<String, Object>) JSONObject.parse(result);


        return map;
    }
}
