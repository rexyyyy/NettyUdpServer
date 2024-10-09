package com.net.msg.util;

import com.net.msg.enums.ResultCodeEnum;
import com.net.msg.vo.ResultVo;
import lombok.extern.slf4j.Slf4j;


import org.springframework.util.StreamUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.*;

@Slf4j
public class FileUtil {
     //上传路径
    private static final String FILE_UPLOAD_PATH = "upload" + File.separator;
    // 文件下载路径
    private static final String FILE_DOWNLOAD_PATH = "download" + File.separator;
    // 日期路径
    private static final String DATE_PATH = DateUtil.getNowStr() + File.separator;
    // 根路径
    private static final String ROOT_PATH = "E:" + File.separator;
    // 下划线
    private static final String UNDER_LINE = "_";
    // 默认字符集
    private static final String DEFAULT_CHARSET = "utf-8";

    /**
     *每次上传文件前需要清空目录
     */
    public static boolean deletesDir(String path){
        File file=new  File(path);
        if(!file.exists()){
            return false;
        }
        String[]content=file.list();
        for(String name:content){
            File temp=new File(path,name);
            if(temp.isDirectory()){
                deletesDir(temp.getAbsolutePath());
            }
            else {
                temp.delete();
            }
        }
        return true;

    }
    // 上传文件   添加文件重名判断（重名删除）
    public static String uploadFile(MultipartFile file) throws IOException {
        // 获取上传的文件名称（包含后缀名）
        String oldFileName = file.getOriginalFilename();
        // 获取文件后缀名，将小数点“.” 进行转译
        String[] split = oldFileName.split("\\.");

        // 文件名
        String fileName = null;
        StringBuilder builder = new StringBuilder();
        if (split.length > 0) {
            String suffix = split[split.length - 1];
            for (int i = 0; i < split.length -1; i++) {
                builder.append(split[i]).append(UNDER_LINE);
            }
            // 防止文件名重复
//            fileName = builder.append(System.nanoTime()).append(".").append(suffix).toString();
            fileName=oldFileName;
        } else {
            fileName = builder.append(oldFileName).append(UNDER_LINE).append(System.nanoTime()).toString();

        }

        // 上传文件的存储路径
        String filePath = ROOT_PATH + FILE_UPLOAD_PATH + DATE_PATH;
        // 生成文件夹
        mkdirs(filePath);
        //删除之前上传文件
        deletesDir(filePath);
        // 文件全路径
        String fileFullPath = filePath + fileName;
        log.info("上传的文件：" + file.getName() + "，" + file.getContentType() + "，保存的路径为：" + fileFullPath);

        // 转存文件
        StreamUtils.copy(file.getInputStream(), new FileOutputStream(fileFullPath));
        //Streams.copy(file.getInputStream(), new FileOutputStream(fileFullPath), true);

        return fileFullPath;
    }

    // 根据文件名下载文件
    public static ResultVo<String> downloadFile(String fileName, HttpServletResponse response) {
        InputStream in = null;
        OutputStream out = null;
        try {
            // 获取输出流
            out = response.getOutputStream();
            setResponse(fileName, response);

            String downloadPath = new StringBuilder().append(ROOT_PATH).append(FILE_DOWNLOAD_PATH).append(fileName).toString();
            File file = new File(downloadPath);
            if (!file.exists()) {
                log.error("下载附件失败，请检查文件" + downloadPath + "是否存在");
                return ResultUtil.error("下载附件失败，请检查文件" + downloadPath + "是否存在");
            }

            // 获取输入流
            in = new FileInputStream(file);
            if (null == in) {
                log.error("下载附件失败，请检查文件" + fileName + "是否存在");
                throw new FileNotFoundException("下载附件失败，请检查文件" + fileName + "是否存在");
            }
            // 复制
            StreamUtils.copy(in, response.getOutputStream());
            //  IOUtils.copy(in, response.getOutputStream());
            response.getOutputStream().flush();

            try {
                close(in, out);
            } catch (IOException e) {
                log.error("关闭流失败");
                return ResultUtil.error(ResultCodeEnum.CLOSE_FAILD.getCode(), "关闭流失败");
            }
        } catch (IOException e) {
            log.error("响应对象response获取输出流错误");
            return ResultUtil.error("响应对象response获取输出流错误");
        }
        return ResultUtil.success("文件下载成功");
    }

    // 设置响应头
    public static void setResponse(String fileName, HttpServletResponse response) {
        // 清空输出流
        response.reset();
        response.setContentType("application/x-download;charset=GBK");
        try {
            response.setHeader("Content-Disposition", "attachment;filename=" + new String(fileName.getBytes(DEFAULT_CHARSET), "iso-8859-1"));
        } catch (UnsupportedEncodingException e) {
            log.error("文件名{}不支持转换为字符集{}", fileName, DEFAULT_CHARSET);
        }
    }

    // 关闭流
    public static void close(InputStream in, OutputStream out) throws IOException{
        if (null != in) {
            in.close();
        }
        if (null != out) {
            out.close();
        }
    }

    // 根据目录路径生成文件夹
    public static void mkdirs(String path) {
        File file = new File(path);
        if(!file.exists() || !file.isDirectory()) {
            file.mkdirs();
        }
    }

}
