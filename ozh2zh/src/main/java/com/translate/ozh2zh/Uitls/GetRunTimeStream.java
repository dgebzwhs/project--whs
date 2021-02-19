package com.translate.ozh2zh.Uitls;

import java.io.*;

/**
 * @author whs
 * @date 2020/5/25 0025 上午 11:35
 */
public class GetRunTimeStream {
    public static String getInputText(Process proc){
        String result="";
        try {
            InputStream inputStream = proc.getInputStream();
            InputStreamReader reader = new InputStreamReader(inputStream,"GBK");
            BufferedReader br = new BufferedReader(reader);
            String line;
            while ((line=br.readLine())!=null){
                result+=line+'\n';
            }
            inputStream.close();
            br.close();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }
    public static String getErrorText(Process proc){
        String result="";
        try {
            InputStream inputStream = proc.getErrorStream();
            InputStreamReader reader = new InputStreamReader(inputStream,"GBK");
            BufferedReader br = new BufferedReader(reader);
            String line;
            while ((line=br.readLine())!=null){
                result+=line+'\n';
            }
            inputStream.close();
            br.close();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }

    public static String readFile(String filePath) {
        String result="";
        try {
            BufferedReader br= new BufferedReader(new FileReader(filePath));
            String data = null;
            while((data = br.readLine())!=null)
            {
                result+=data+"\n";
            }
        } catch (IOException e) {
//            e.printStackTrace();
        }
        return result;
    }
    public static void writeFile(String filePath,String text) {
        try {
            BufferedWriter bw= new BufferedWriter(new OutputStreamWriter(new FileOutputStream(new File(filePath),true),"GBK"));
            String data = null;
            bw.write(text);
            bw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
