package com.example.demo.Interfaces;

/**
 * Giao diện đại diện cho một DTO (Data Transfer Object).
 *
 * @param <E> Kiểu dữ liệu của entity tương ứng với DTO.
 */
public interface IDto<E> {

    /**
     * Chuyển đổi DTO thành entity.
     *
     * @return Entity tương ứng với DTO.
     */
    public E toEntity();

    /**
     * Chuyển đổi entity thành DTO.
     *
     * @param entity Entity cần chuyển đổi thành DTO.
     */
    public void toDto(E entity);
}
