package com.remo.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.remo.pojo.entity.Tag;

public interface TagMapper extends BaseMapper<Tag> {

    int deleteResolutionByTagId(Long tagId);
}
