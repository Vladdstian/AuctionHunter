package com.vladnichifor.auctionhunter.mappers;

public interface Mapper<T,E>{
    E toEntity(T dto);
    T toDto(E entity);
}