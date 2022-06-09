package com.lfalero.hellojava;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class UxResponseDto {

    private String type;
    private BsResponseDto response;
}
