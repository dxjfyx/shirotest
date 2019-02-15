package com.shiroboot.shirotest.model;

import com.baomidou.mybatisplus.enums.IdType;
import com.baomidou.mybatisplus.annotations.TableId;
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
@TableName("tab_user")
public class TabUser extends Model<TabUser> {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;
    /**
     * 用户名
     */
    @TableField("col_name")
    private String colName;
    /**
     * 密码
     */
    @TableField("col_password")
    private String colPassword;
    /**
     * 加密密码的盐
     */
    @TableField("col_salt")
    private String colSalt;
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

    public String getColPassword() {
        return colPassword;
    }

    public void setColPassword(String colPassword) {
        this.colPassword = colPassword;
    }

    public String getColSalt() {
        return colSalt;
    }

    public void setColSalt(String colSalt) {
        this.colSalt = colSalt;
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
        return "TabUser{" +
        "id=" + id +
        ", colName=" + colName +
        ", colPassword=" + colPassword +
        ", colSalt=" + colSalt +
        ", colStatus=" + colStatus +
        ", colCode=" + colCode +
        "}";
    }
}
