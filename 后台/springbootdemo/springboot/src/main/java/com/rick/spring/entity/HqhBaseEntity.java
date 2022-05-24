package com.rick.spring.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.Sort;

import java.io.Serializable;

/**
 * @author 小松
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
public class HqhBaseEntity implements Serializable {
    private static final long serialVersionUID = 1L;
    private int page;
    private int size;
    private Sort sort;


}

