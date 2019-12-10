package com.remo.service;

import com.baomidou.mybatisplus.service.IService;
import com.remo.pojo.dto.TagDto;
import com.remo.pojo.entity.Tag;

import java.util.List;

public interface ITagService  extends IService<Tag> {

    List<TagDto> listTags();
}
