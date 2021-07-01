package com.zut.boot.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author 梁贵莹
 * @create 2021/7/1 下午 5:06
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Student  {
    private Integer sid;
    private String sname;
    private String sex;
    private Float score;
}