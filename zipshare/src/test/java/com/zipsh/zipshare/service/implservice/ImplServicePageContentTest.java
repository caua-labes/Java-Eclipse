package com.zipsh.zipshare.service.implservice;

import com.zipsh.zipshare.dto.PageContentDto;
import com.zipsh.zipshare.dto.PageDto;
import com.zipsh.zipshare.exceptions.NotFound;
import com.zipsh.zipshare.mapper.MapPage;
import com.zipsh.zipshare.mapper.MapPageContent;
import com.zipsh.zipshare.model.Page;
import com.zipsh.zipshare.model.PageContent;
import com.zipsh.zipshare.repository.PageContentRepository;
import com.zipsh.zipshare.repository.PageRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

class ImplServicePageContentTest {

    @Mock
    private PageContentRepository mockRepository;

    @Mock
    private PageRepository mockPageRepository;

    @InjectMocks
    private ImplServicePageContent servicePageContent;

    @InjectMocks
    private ImplServicePage servicePage;

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void  postPageContent() {

    }

    @Test
    void getPageContent() {
    }

    @Test
    void getByIdPageContent() {
    }

    @Test
    void putPageContent() {
    }

    @Test
    void delPageContent() {
    }

    @Test
    void delPageContentByPage() {
        Page mainPageTest = new Page(UUID.randomUUID(),"mainPageTest",LocalDate.now());
        when(mockPageRepository.save(any(Page.class))).thenReturn(mainPageTest);
        PageDto savedPage = servicePage.postPage(MapPage.mapToDto(mainPageTest));
        assertNotNull(savedPage);
        Page savedPageEnt = MapPage.mapToEnt(savedPage);
            PageContent contenTest = new PageContent(UUID.randomUUID(), savedPageEnt, "contentTest");
            when(mockRepository.save(any(PageContent.class))).thenReturn(contenTest);
            PageContentDto pageContentDto = servicePageContent.postPageContent(MapPageContent.mapToDto(contenTest));
            assertNotNull(pageContentDto);
        when(mockRepository.findAll()).thenReturn(List.of(new PageContent()));
        assertEquals(savedPageEnt, servicePageContent.getByIdPageContent(contenTest.getId()));


    }
}