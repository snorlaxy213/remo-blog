package com.remo.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.remo.pojo.dto.TagDto;
import com.remo.pojo.entity.Tag;

import java.util.List;

public interface ITagService extends IService<Tag> {

    List<TagDto> listTags();

    TagDto getTag(Long tagId);

    Long addTag(TagDto tagDto);

    boolean updateTag(TagDto tagDto);

    boolean deleteTag(Long tagId);
}
