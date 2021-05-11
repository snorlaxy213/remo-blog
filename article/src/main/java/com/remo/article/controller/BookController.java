package com.remo.article.controller;

import com.remo.article.common.util.ResponseUtil;
import com.remo.article.pojo.dto.BookDto;
import com.remo.article.pojo.vo.ResponseVo;
import com.remo.article.service.IBookService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;

@Api(tags = "Book")
@RestController("/book")
@RequestMapping("/book")
public class BookController {

    @Resource
    IBookService bookService;

    @ApiOperation(value = "upload Book")
    @PostMapping(value = "/upload")
    public ResponseVo uploadBook(@RequestPart BookDto bookDto, @RequestPart MultipartFile bookFile) {
        if (bookFile.isEmpty()) {
            return ResponseUtil.initSuccessResponse("上传失败，请选择文件");
        }

        return ResponseUtil.initSuccessResponse(bookService.uploadBook(bookDto, bookFile));
    }
}
