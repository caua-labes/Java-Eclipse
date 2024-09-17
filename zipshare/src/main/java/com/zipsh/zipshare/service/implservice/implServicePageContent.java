package com.zipsh.zipshare.service.implservice;

import com.zipsh.zipshare.dto.pageContentDto;
import com.zipsh.zipshare.mapper.mapPageContent;
import com.zipsh.zipshare.model.page;
import com.zipsh.zipshare.model.pageContent;
import com.zipsh.zipshare.repository.pageContentRepository;
import com.zipsh.zipshare.service.servicePageContent;

import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

public class implServicePageContent implements servicePageContent {
    private pageContentRepository pageContentRepository;
    public implServicePageContent(pageContentRepository pageContentRepository){
        this.pageContentRepository = pageContentRepository;
    }
    @Override
    public pageContentDto postPageContent(pageContentDto pageContentDto) {
        pageContent page = pageContentRepository.save(mapPageContent.mapToEnt(pageContentDto) );
        return mapPageContent.mapToDto(page);
    }

    @Override
    public List<pageContentDto> getPageContent() {
        return pageContentRepository.findAll().stream().map((content) -> mapPageContent.mapToDto(content)).collect(Collectors.toList());
    }

    @Override
    public pageContentDto getByIdPageContent(Long id) {
        return null;
    }

    @Override
    public pageContentDto putPageContent(Long id, pageContentDto pageContent) {
        return null;
    }

    @Override
    public void delPageContent(Long id) {

    }
}