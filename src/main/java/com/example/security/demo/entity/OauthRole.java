package com.example.security.demo.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

/**
 * @author author
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class OauthRole implements Serializable {

    private static final long serialVersionUID = 1585185544992L;


    /**
     * 主键
     * <p>
     * isNullAble:0
     */
    private Integer id;

    /**
     * isNullAble:1
     */
    private String name;

    /**
     * isNullAble:1
     */
    private String nameZh;


}
