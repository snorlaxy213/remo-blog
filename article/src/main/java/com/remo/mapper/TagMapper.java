package com.remo.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.remo.pojo.entity.Tag;
import org.apache.ibatis.annotations.Delete;

public interface TagMapper extends BaseMapper<Tag> {

    @Delete("delete from article_tag where tagId = #{tagId}")
    int deleteResolutionByTagId(Long tagId);
}
