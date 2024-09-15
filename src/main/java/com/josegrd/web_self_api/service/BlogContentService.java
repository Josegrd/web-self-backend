package com.josegrd.web_self_api.service;

import com.josegrd.web_self_api.dto.BlogContentDTO;

import java.util.List;

public interface BlogContentService {
    List<BlogContentDTO> createBulk(List<BlogContentDTO> blogContentDTOS);
}
