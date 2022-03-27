package com.wang.springframework.beans;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author: whiteandbird
 * @Descripter:
 * @Date: 2022:03:27  20:20
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PropertyValue {
    private String name;

    private Object value;
}
