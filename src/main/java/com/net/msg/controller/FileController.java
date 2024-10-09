package com.net.msg.controller;

import com.net.msg.dao.FileService;

import com.net.msg.vo.ResultVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;


@Controller
@ResponseBody
@RequestMapping("/file")
@CrossOrigin   // 跨域
public class FileController {
    @Autowired
    private FileService fileService;

    // 文件上传
    @RequestMapping("/upload")
    public ResultVo<String> uploadFile(@RequestParam("file") MultipartFile file) {
        return fileService.uploadFile(file);
    }

    // 文件下载
    @RequestMapping("/download")
    public ResultVo<String> downloadFile(@RequestParam("fileName") String fileName, final HttpServletResponse response) {
        return fileService.downloadFile(fileName, response);
    }

}
