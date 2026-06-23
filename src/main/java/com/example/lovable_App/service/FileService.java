package com.example.lovable_App.service;

import com.example.lovable_App.dto.project.FileContentResponse;
import com.example.lovable_App.dto.project.FileNode;

import java.util.List;

public interface FileService {
    List<FileNode> getFileTree(Long projectId, Long userId);

    FileContentResponse getFileContent(Long projectId, String path, Long userId);
}
