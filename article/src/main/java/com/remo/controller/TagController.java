package com.remo.controller;

import com.remo.common.exception.utils.ResponseUtil;
import com.remo.common.group.Insert;
import com.remo.common.group.Update;
import com.remo.pojo.dto.TagDto;
import com.remo.pojo.vo.ResponseVo;
import com.remo.service.ITagService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

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

    @ApiOperation(value = "deleteTag")
    @DeleteMapping(value = "/deleteTag/{tagId}")
    public ResponseVo deleteTag(@PathVariable("tagId") Long tagId) {
        return ResponseUtil.initSuccessResponse(tagService.deleteTag(tagId));
    }
}
