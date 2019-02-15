package com.shiroboot.shirotest.model;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableName;
import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author dxjfyx
 * @since 2019-01-29
 */
@TableName("tab_authority")
public class TabAuthority extends Model<TabAuthority> {

    private static final long serialVersionUID = 1L;

    private Integer id;
    /**
     * 权限名称
     */
    @TableField("col_name")
    private String colName;
    /**
     * 状态  0:正常,1:冻结,2:删除
     */
    @TableField("col_status")
    private Integer colStatus;
    /**
     * 备用
     */
    @TableField("col_code")
    private Integer colCode;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getColName() {
        return colName;
    }

    public void setColName(String colName) {
        this.colName = colName;
    }

    public Integer getColStatus() {
        return colStatus;
    }

    public void setColStatus(Integer colStatus) {
        this.colStatus = colStatus;
    }

    public Integer getColCode() {
        return colCode;
    }

    public void setColCode(Integer colCode) {
        this.colCode = colCode;
    }

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

    @Override
    public String toString() {
        return "TabAuthority{" +
        "id=" + id +
        ", colName=" + colName +
        ", colStatus=" + colStatus +
        ", colCode=" + colCode +
        "}";
    }
}
