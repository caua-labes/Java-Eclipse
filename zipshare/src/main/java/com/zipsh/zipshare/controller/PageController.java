package com.zipsh.zipshare.controller;

import com.zipsh.zipshare.dto.PageDto;
import com.zipsh.zipshare.mapper.MapPage;
import com.zipsh.zipshare.model.Page;
import com.zipsh.zipshare.service.ServicePage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.swing.text.html.parser.Entity;
import java.security.Provider;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.UUID;

@RestController
@RequestMapping("zipsh/page")
public class PageController {

    @Autowired
    private final ServicePage service;

    public PageController(ServicePage service){
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<PageDto> postPage(@RequestBody PageDto pageDto){
        PageDto pageSave = service.postPage(pageDto);
        return new ResponseEntity<>(pageSave, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<PageDto>> getPage(){
        List<PageDto> PagesList = service.getPage();
        return new ResponseEntity<>(PagesList, HttpStatus.OK);
    }

    @CrossOrigin(origins = "http://127.0.0.1:5500")
    @GetMapping("{code}")
    public ResponseEntity<PageDto> getCodePage(@PathVariable("code") String code){
            PageDto page = service.getPageCode(code);
            return new ResponseEntity<>(page, HttpStatus.FOUND);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<HttpStatus> deletePageById(@PathVariable("id") UUID id){
        service.delPage(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}

