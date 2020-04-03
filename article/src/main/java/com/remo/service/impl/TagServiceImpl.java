package com.remo.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.remo.common.exception.utils.ServiceUtil;
import com.remo.mapper.TagMapper;
import com.remo.pojo.dto.TagDto;
import com.remo.pojo.entity.Tag;
import com.remo.service.ITagService;
import org.dozer.DozerBeanMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Service("tagServiceImpl")
public class TagServiceImpl extends ServiceImpl<TagMapper, Tag> implements ITagService {

    @Resource
    @Qualifier("dozerBeanMapper")
    DozerBeanMapper dozerMapper;

    @Override
    public List<TagDto> listTags() {
        List<Tag> tags = this.list();
        List<TagDto> tagDtos = new ArrayList<>();
        tags.forEach(tag -> tagDtos.add(dozerMapper.map(tag, TagDto.class)));
        return tagDtos;
    }

    @Override
    public TagDto getTag(Long tagId) {
        Tag tag = this.getById(tagId);
        TagDto tagDto = new TagDto();
        BeanUtils.copyProperties(tag, tagDto);
        return tagDto;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Long addTag(TagDto tagDto) {
        Tag tag = new Tag();
        BeanUtils.copyProperties(tagDto, tag);
        ServiceUtil.initEntity(tag, true);
        this.save(tag);
        return tag.getTagId();
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean updateTag(TagDto tagDto) {
        Tag tag = new Tag();
        BeanUtils.copyProperties(tagDto, tag);
        ServiceUtil.initEntity(tag, false);
        return this.updateById(tag);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean deleteTag(Long tagId) {
        this.getBaseMapper().deleteResolutionByTagId(tagId);
        return this.removeById(tagId);
    }
}
