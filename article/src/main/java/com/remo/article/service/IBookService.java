package com.remo.article.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.remo.article.pojo.dto.BookDto;
import com.remo.article.pojo.entity.Book;
import org.springframework.web.multipart.MultipartFile;

public interface IBookService extends IService<Book> {

    String uploadBook(BookDto bookDto, MultipartFile bookFile);
}
