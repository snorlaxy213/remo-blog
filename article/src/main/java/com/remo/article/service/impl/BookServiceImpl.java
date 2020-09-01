package com.remo.article.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.remo.article.common.util.ServiceUtil;
import com.remo.article.mapper.BookMapper;
import com.remo.article.pojo.dto.BookDto;
import com.remo.article.pojo.entity.Book;
import com.remo.article.service.IBookService;
import org.dozer.DozerBeanMapper;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.File;
import java.io.IOException;

@Service("bookService")
@Transactional(propagation = Propagation.NOT_SUPPORTED, readOnly = true)
public class BookServiceImpl extends ServiceImpl<BookMapper, Book> implements IBookService {

    @Resource
    @Qualifier("dozerBeanMapper")
    DozerBeanMapper dozerMapper;

    @Override
    public String uploadBook(BookDto bookDto, MultipartFile bookFile) {
        String filePath = "/file/";
        String fileName = bookFile.getOriginalFilename();

        Book book = dozerMapper.map(bookDto, Book.class);
        book.setPath(filePath + fileName);
        ServiceUtil.initEntity(book, true);
        this.save(book);

        File dest = new File(filePath + fileName);
        try {
            bookFile.transferTo(dest);
            return "上传成功";
        } catch (IOException e) {
            e.printStackTrace();
        }

        return "上传失败!";
    }
}
