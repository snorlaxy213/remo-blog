package com.remo.controller;

import com.remo.pojo.vo.ResponseVo;
import com.remo.service.ITagService;
import com.remo.utils.ResponseUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@Api(tags = "Tag")
@RestController(value = "/tag")
public class TagController {

    @Autowired
    ITagService tagService;

    @ApiOperation(value = "listTags")
    @GetMapping(value = "/listTags")
    public ResponseVo listTags() {
        return ResponseUtil.initSuccessResponse(tagService.listTags());
    }

}
