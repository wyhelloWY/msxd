package com.landian.mashangxiadan.service;

import org.springframework.web.multipart.MultipartFile;

/**
 * @author Yu W
 * @date 2020/10/13 16:39
 */
public interface UploadService {
    /**
     * 上传文件
     * @param file
     * @return
     * @throws Exception
     */
    boolean getExcel(MultipartFile file) throws Exception;
}
