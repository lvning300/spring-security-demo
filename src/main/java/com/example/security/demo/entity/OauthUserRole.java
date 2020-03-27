package com.example.security.demo.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author author
 */
@TableName
@Data
@AllArgsConstructor
@NoArgsConstructor
public class OauthUserRole implements Serializable {


    /**
     * 主键
     * <p>
     * isNullAble:0
     */
    private Integer id;

    /**
     * isNullAble:1
     */
    private Integer uid;

    /**
     * isNullAble:1
     */
    private Integer rid;

}
