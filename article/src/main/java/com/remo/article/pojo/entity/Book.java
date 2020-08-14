package com.remo.article.pojo.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;

import java.io.Serializable;
import java.util.Objects;

public class Book extends Base implements Serializable {

    private static final long serialVersionUID = 8365686186565851797L;

    /**
     * 书籍ID
     */
    @TableId(value = "book_id", type = IdType.AUTO)
    private Long bookId;

    /**
     * 书籍简介
     */
    @TableField(value = "desc")
    private String description;

    /**
     * 书籍保存位置
     */
    @TableField(value = "path")
    private String path;

    /**
     * 乐观锁
     */
    @TableField("version")
    private Integer version;

    public Book() {
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public Long getBookId() {
        return bookId;
    }

    public void setBookId(Long bookId) {
        this.bookId = bookId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Book book = (Book) o;
        return bookId.equals(book.bookId) &&
                Objects.equals(description, book.description) &&
                path.equals(book.path) &&
                Objects.equals(version, book.version);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), bookId, description, path, version);
    }
}
