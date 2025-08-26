package com.zzu.exam.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.*;


@RestController
@RequestMapping("/common")
@Tag(name = "通用接口")
public class CommonController {

    @Value("${file.upload.path}")
    private String uploadPath;

    // 上传文件
    @PostMapping("/file")
    public String uploadFile(MultipartFile file) {
        try {
            // 确保上传目录存在
            Path uploadDir = Paths.get(uploadPath);
            if (!Files.exists(uploadDir)) {
                Files.createDirectories(uploadDir);
            }

            // 生成唯一文件名（防止重名覆盖）
            String originalFilename = file.getOriginalFilename();
            String extension = "";
            if (originalFilename != null && originalFilename.contains(".")) {
                extension = originalFilename.substring(originalFilename.lastIndexOf("."));
            }
            String newFilename = System.currentTimeMillis() + extension;

            // 保存文件
            Path filePath = Paths.get(uploadPath + newFilename);
            Files.copy(file.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);

            return "文件上传成功：" + newFilename;
        } catch (IOException e) {
            e.printStackTrace();
            return "文件上传失败";
        }
    }

    // 获取上传文件
    @GetMapping("/file/{filename}")
    public Resource downloadFile(@PathVariable String filename) {
        try {
            Path filePath = Paths.get(uploadPath + filename);
            Resource resource = new UrlResource(filePath.toUri());
            if (resource.exists() || resource.isReadable()) {
                return resource;
            } else {
                throw new RuntimeException("无法读取文件: " + filename);
            }
        } catch (MalformedURLException e) {
            throw new RuntimeException("文件路径错误: " + filename, e);
        }
    }
}
