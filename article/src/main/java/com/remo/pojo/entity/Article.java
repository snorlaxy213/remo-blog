package com.remo.pojo.entity;

import java.util.Date;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.annotations.Version;
import com.baomidou.mybatisplus.enums.FieldFill;
import com.baomidou.mybatisplus.enums.IdType;
import lombok.Data;

import java.io.Serializable;

/**
 * <p>
 * 文章实体类
 * </p>
 *
 * @author vino
 * @since 2019-08-26
 */
@Data
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
    @TableField(value = "articleId", fill = FieldFill.INSERT)
    private Long articleId;

    /**
     * 作者
     */
    private String author;

    /**
     * 文章原作者
     */
    @TableField("originalAuthor")
    private String originalAuthor;

    /**
     * 文章标题
     */
    @TableField("articleTitle")
    private String articleTitle;

    /**
     * 文章内容
     */
    @TableField("articleContent")
    private String articleContent;

    /**
     * 文章标签
     */
    @TableField("articleTags")
    private String articleTags;

    /**
     * 文章类型
     */
    @TableField("articleType")
    private String articleType;

    /**
     * 文章分类
     */
    @TableField("articleCategories")
    private String articleCategories;

    /**
     * 发布文章日期
     */
    @TableField("publishDate")
    private String publishDate;

    /**
     * 更新文章日期
     */
    @TableField("updateDate")
    private String updateDate;

    /**
     * 文章url
     */
    @TableField("articleUrl")
    private String articleUrl;

    /**
     * 文章摘要
     */
    @TableField("articleTabloid")
    private String articleTabloid;

    /**
     * 文章喜欢数
     */
    private Integer likes;

    /**
     * 上一篇文章id
     */
    @TableField("lastArticleId")
    private Long lastArticleId;

    /**
     * 下一篇文章id
     */
    @TableField("nextArticleId")
    private Long nextArticleId;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 创建人
     */
    private String createUser;

    /**
     * 更新时间
     */
    private Date updateTime;

    /**
     * 更新人
     */
    private String updateUser;

    /**
     * 乐观锁
     */
    @Version
    private Integer version;
}
