package com.zipsh.zipshare.service.implservice;


import com.zipsh.zipshare.dto.PageDto;
import com.zipsh.zipshare.exceptions.NotFound;
import com.zipsh.zipshare.mapper.MapPage;
import com.zipsh.zipshare.model.Page;
import com.zipsh.zipshare.repository.PageContentRepository;
import com.zipsh.zipshare.repository.PageRepository;
import com.zipsh.zipshare.service.ServicePage;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class ImplServicePage implements ServicePage {

    private PageRepository pageRepository;
    private PageContentRepository contentRepository;
    public ImplServicePage(PageRepository repository, PageContentRepository repositoryContent){
        this.pageRepository = repository;
        this.contentRepository = repositoryContent;
    }
    //Falta finalizar a logica e os metodos
    @Override
    public PageDto postPage(PageDto pageDto) {
        Page newPage = new Page(UUID.randomUUID(),pageDto.getCodePage(),pageDto.getDatePage());
        if(pageRepository.getBycodePage(pageDto.getCodePage()) != null ){
            throw new IllegalArgumentException("Já existe uma paginá com este codigo!");
        }
        else {
            Page pageSave = pageRepository.save(newPage);
            return MapPage.mapToDto(pageSave);
        }
    }

    @Override
    public List<PageDto> getPage() {
        List<Page> pageList = pageRepository.findAll();
        return pageList.stream().map((page) -> MapPage.mapToDto(page)).collect(Collectors.toList());
    }

    @Override
    public PageDto getPageCode(String code) {
        if (code == null) {
            throw new IllegalArgumentException("Código não pode ser null");
        } else {
            LocalDate datePage = LocalDate.now();
            Page page = pageRepository.getBycodePage(code);
            if (page == null) {
                Page newPage = new Page(UUID.randomUUID(), code, datePage);
                return MapPage.mapToDto(pageRepository.save(newPage));
            }
            deletePageByDate(MapPage.mapToDto(page));
            return MapPage.mapToDto(page);

            //return mapPage.mapToDto(pageRepository.findByString(code));

        }
    }

    @Override
    public void delPage(UUID id) {
        Page page = pageRepository.findById(id).orElseThrow(() -> new NotFound("Erro ao deletar a pagina"));
        ImplServicePageContent servicePageContent = new ImplServicePageContent(contentRepository);
        servicePageContent.delPageContentByPage(page.getId());
        pageRepository.delete(page);
    }

    @Override
    public void deletePageByDate(PageDto page) {
        LocalDate date = LocalDate.now();
        if(date.getDayOfMonth() >= page.getDatePage().getDayOfMonth() && date.getMonth().compareTo(page.getDatePage().getMonth()) >0){
            delPage(page.getId());
        }
    }

}
