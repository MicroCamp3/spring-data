package com.comarch.bootcamp.jdbc.jpa.mapper;

public interface EntityMapper<D, E> {
    E toEntity(D dto);
    D toDto(E entnity);
}
