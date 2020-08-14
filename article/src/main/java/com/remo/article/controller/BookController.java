package com.remo.article.controller;

import com.remo.article.common.utils.ResponseUtil;
import com.remo.article.pojo.dto.BookDto;
import com.remo.article.pojo.vo.ResponseVo;
import com.remo.article.service.IBookService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;

@RestController()
@Api(tags = "Book")
public class BookController {

    @Resource
    IBookService bookService;

    @ApiOperation(value = "uploadBook")
    @PostMapping(value = "/book/upload")
    public ResponseVo uploadBook(@RequestPart BookDto bookDto, @RequestPart MultipartFile bookFile) {
        if (bookFile.isEmpty()) {
            return ResponseUtil.initSuccessResponse("上传失败，请选择文件");
        }

        return ResponseUtil.initSuccessResponse(bookService.uploadBook(bookDto, bookFile));
    }
}
