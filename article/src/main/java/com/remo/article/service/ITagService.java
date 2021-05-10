package com.remo.article.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.remo.article.pojo.dto.TagDto;
import com.remo.article.pojo.entity.Tag;

import java.util.List;

public interface ITagService extends IService<Tag> {

    void testRedis();

    List<TagDto> listTags();

    TagDto getTag(Long tagId);

    Long addTag(TagDto tagDto);

    boolean updateTag(TagDto tagDto);

    boolean deleteTag(Long tagId);
}
