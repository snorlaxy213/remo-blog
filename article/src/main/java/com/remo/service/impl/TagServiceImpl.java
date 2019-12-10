package com.remo.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.remo.mapper.TagMapper;
import com.remo.pojo.dto.TagDto;
import com.remo.pojo.entity.Tag;
import com.remo.service.ITagService;
import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TagServiceImpl extends ServiceImpl<TagMapper, Tag> implements ITagService {

    @Autowired
    Mapper dozerMapper;

    @Override
    public List<TagDto> listTags() {
        List<Tag> tags = this.selectList(new EntityWrapper<>());
        List<TagDto> tagDtos = new ArrayList<>();
        tags.forEach(tag -> tagDtos.add(dozerMapper.map(tag, TagDto.class)));
        return tagDtos;
    }
}
