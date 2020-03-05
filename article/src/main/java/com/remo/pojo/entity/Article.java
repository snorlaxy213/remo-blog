package com.remo.pojo.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@Accessors(chain = true)
@TableName("article")
public class Article implements Serializable {

    private static final long serialVersionUID = 8365686186585051797L;

    /**
     * 主键，自增
     */
    @TableId(value = "id",type = IdType.AUTO)
    private Long id;

    /**
     * 文章id
     */
    @TableField(value = "article_id", fill = FieldFill.INSERT)
    private Long articleId;

    /**
     * 作者
     */
    @TableField(value = "author_name")
    private String author;

    /**
     * 文章原作者
     */
    @TableField("original_author_name")
    private String originalAuthor;

    /**
     * 文章标题
     */
    @TableField("article_title")
    private String articleTitle;

    /**
     * 文章内容
     */
    @TableField("article_content")
    private String articleContent;

    /**
     * 文章类型
     */
    @TableField("article_type")
    private String articleType;

    /**
     * 文章url
     */
    @TableField("article_url")
    private String articleUrl;

    /**
     * 文章摘要
     */
    @TableField("article_tabloid")
    private String articleTabloid;

    /**
     * 文章喜欢数
     */
    @TableField("likes")
    private Integer likes;

    /**
     * 上一篇文章id
     */
    @TableField("last_article_id")
    private Long lastArticleId;

    /**
     * 下一篇文章id
     */
    @TableField("next_article_id")
    private Long nextArticleId;

    /**
     * 创建时间
     */
    @TableField(value = "create_time", fill = FieldFill.INSERT)
    private Date createTime;

    /**
     * 创建人
     */
    @TableField(value = "create_user", fill = FieldFill.INSERT)
    private String createUser;

    /**
     * 更新时间
     */
    @TableField(value = "update_time", fill = FieldFill.UPDATE)
    private Date updateTime;

    /**
     * 更新人
     */
    @TableField(value = "update_user", fill = FieldFill.UPDATE)
    private String updateUser;

    /**
     * 是否删除:1-以删除;0-存在
     */
    @TableField(value = "is_delete")
    private Integer isDelete;

    /**
     * 乐观锁
     */
    @TableField("version")
    private Integer version;
}
