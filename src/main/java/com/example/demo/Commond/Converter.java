package com.example.demo.commond;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import lombok.Getter;

@Component
@Getter
public class Converter{
    @Autowired
    private ModelMapper modelMapper;

    public <S,T> List<T> converterList(List<S> src, Class<T> targetClass) {
        return src.stream().map(srcItem -> modelMapper.map(srcItem, targetClass)).collect(Collectors.toList());
    }
}