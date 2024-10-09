package com.net.msg.dao;

import com.net.msg.vo.ResultVo;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;

public interface FileService {
    public ResultVo<String> uploadFile(MultipartFile file);
    public ResultVo<String> downloadFile(String fileName, HttpServletResponse response);

}
