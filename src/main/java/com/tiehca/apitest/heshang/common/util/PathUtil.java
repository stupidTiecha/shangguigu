package com.tiehca.apitest.heshang.common.util;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.FileNotFoundException;

/**
 * @author chen
 */
public class PathUtil {

    private static File projectPath;

    private static File upload;

    public static File getProjectPath() {
        if (projectPath == null) {
            try {
                projectPath = new File(ResourceUtils.getURL("classpath:").getPath());
            } catch (FileNotFoundException e) {
                e.printStackTrace();
                throw new RuntimeException("系统异常");
            }
        }
        return projectPath;
    }

    public static File getUploadPath () {
        if (upload == null) {
            upload = new File(getProjectPath().getAbsolutePath(), "static/upload/images");
            if (!upload.exists()) {
                boolean mkdirs = upload.mkdirs();
                if (!mkdirs) {
                    throw new RuntimeException("创建上传文件夹失败！");
                }
            }
        }
        return upload;
    }
}
