package com.remo.controller;

import com.remo.pojo.dto.TagDto;
import com.remo.pojo.vo.ResponseVo;
import com.remo.service.ITagService;
import com.remo.utils.ResponseUtil;
import com.remo.validation.group.Insert;
import com.remo.validation.group.Update;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@Api(tags = "Tag")
@RestController(value = "/tag")
public class TagController {

    @Resource
    @Qualifier("tagServiceImpl")
    ITagService tagService;

    @ApiOperation(value = "listTags")
    @GetMapping(value = "/listTags")
    public ResponseVo listTags() {
        return ResponseUtil.initSuccessResponse(tagService.listTags());
    }

    @ApiOperation(value = "getTag")
    @GetMapping(value = "/getTag/{tagId}")
    public ResponseVo getTag(@PathVariable(value = "tagId") Long tagId) {
        return ResponseUtil.initSuccessResponse(tagService.getTag(tagId));
    }

    @ApiOperation(value = "addTag")
    @PostMapping(value = "/addTag")
    public ResponseVo addTag(@Validated(Insert.class) @RequestBody TagDto tagDto, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return ResponseUtil.initFailResponse(bindingResult);
        } else {
            return ResponseUtil.initSuccessResponse(tagService.addTag(tagDto));
        }
    }

    @ApiOperation(value = "updateTag")
    @PostMapping(value = "/updateTag")
    public ResponseVo updateTag(@Validated(Update.class) @RequestBody TagDto tagDto, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return ResponseUtil.initFailResponse(bindingResult);
        } else {
            return ResponseUtil.initSuccessResponse(tagService.updateTag(tagDto));
        }
    }
}
