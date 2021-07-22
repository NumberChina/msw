package com.saic.msw.model;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author zzz
 * @since 2021-07-22
 */
@TableName("t_demo")
public class Demo extends Model<Demo> {

    private static final long serialVersionUID=1L;

    private Integer id;

    private String name;

    private String sex;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

    @Override
    public String toString() {
        return "Demo{" +
        "id=" + id +
        ", name=" + name +
        ", sex=" + sex +
        "}";
    }
}
