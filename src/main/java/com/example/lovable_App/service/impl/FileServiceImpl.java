package com.example.lovable_App.service.impl;

import com.example.lovable_App.dto.project.FileContentResponse;
import com.example.lovable_App.dto.project.FileNode;
import com.example.lovable_App.service.FileService;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class FileServiceImpl implements FileService {
    @Override
    public List<FileNode> getFileTree(Long projectId, Long userId) {
        return List.of();
    }

    @Override
    public FileContentResponse getFileContent(Long projectId, String path, Long userId) {
        return null;
    }
}
