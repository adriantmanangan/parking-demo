package com.example.demo.base.constants;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Size {

    LARGE(3),
    MEDIUM(2),
    SMALL(1);

    private final Integer value;

}
