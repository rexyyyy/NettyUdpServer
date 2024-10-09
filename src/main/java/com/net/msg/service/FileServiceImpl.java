package com.net.msg.service;

import com.net.msg.dao.FileService;
import com.net.msg.enums.ResultCodeEnum;
import com.net.msg.exception.ParameterValidateExcepiton;
import com.net.msg.util.FileUtil;
import com.net.msg.util.ResultUtil;
import com.net.msg.vo.ResultVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Slf4j    // 日志注解
@Service
public class FileServiceImpl implements FileService {
    @Override
    public ResultVo<String> uploadFile(MultipartFile file) {
        if (file.isEmpty()) {
            log.error("上传的文件为空");
            throw new ParameterValidateExcepiton(ResultCodeEnum.PARAMETER_ERROR.getCode(), "上传的文件为空");
        }

        try {
            FileUtil.uploadFile(file);

        } catch (IOException e) {
            log.error("文件{}上传失败", file);
            return ResultUtil.error("上传失败");
        }

        log.info("文件上传成功");
        return ResultUtil.success("上传成功!");
    }

    @Override
    public ResultVo<String> downloadFile(String fileName, HttpServletResponse response) {
        if (fileName.isEmpty()) {
            log.error("文件名为空");
            throw new ParameterValidateExcepiton(ResultCodeEnum.PARAMETER_ERROR.getCode(), "文件名为空");
        }

        return FileUtil.downloadFile(fileName, response);
    }

}
