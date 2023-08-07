package com.example.demo.interfaces;

import java.util.List;

import org.springframework.data.domain.Page;

import com.example.demo.commond.HttpException;

/**
 * Giao diện dịch vụ chung (service) cho các thực thể (entity).
 *
 * @param <E> Kiểu dữ liệu của thực thể.
 * @param <ID> Kiểu dữ liệu của khóa chính (ID) của thực thể.
 */
public interface IService<E, ID> {
    /**
     * Lấy danh sách tất cả các thực thể.
     *
     * @return Danh sách tất cả các thực thể.     
     * @param dtoClass Kiểu dữ liệu của DTO.
     * @throws HttpException Ngoại lệ nếu có lỗi xảy ra.
     */
    public <S> List<S> findAll(Class<S> dtoClass) throws HttpException;

    /**
     * Lấy thực thể theo khóa chính (ID).
     *
     * @param id Khóa chính (ID) của thực thể.
     * @param dtoClass Kiểu dữ liệu của DTO.
     * @return Thực thể tìm thấy hoặc null nếu không tìm thấy.
     * @throws HttpException Ngoại lệ nếu có lỗi xảy ra.
     */
    public <S> S findById(ID id,Class<S> dtoClass) throws HttpException;

    /**
     * Lấy danh sách tất cả các thực thể.
     *
     * @return Danh sách tất cả các thực thể.
     * @throws HttpException Ngoại lệ nếu có lỗi xảy ra.
     */
    public List<E> findAll() throws HttpException;

    /**
     * Lấy danh sách các thực thể theo trang.
     *
     * @param page Số trang cần lấy.
     * @param size Số lượng thực thể trên mỗi trang.
     * @return Đối tượng trang chứa danh sách các thực thể.
     * @throws HttpException Ngoại lệ nếu có lỗi xảy ra.
     */
    public Page<E> findAll(int page, int size) throws HttpException;

    /**
     * Lấy thực thể theo khóa chính (ID).
     *
     * @param id Khóa chính (ID) của thực thể.
     * @return Thực thể tìm thấy hoặc null nếu không tìm thấy.
     * @throws HttpException Ngoại lệ nếu có lỗi xảy ra.
     */
    public E findById(ID id) throws HttpException;

    /**
     * Tạo mới thực thể.
     *
     * @param newEntity Đối tượng DTO (Data Transfer Object) chứa thông tin thực thể mới.
     * @throws HttpException Ngoại lệ nếu có lỗi xảy ra.
     */
    public <S> void create(S newEntity,Class<E> entityClass) throws HttpException;

    /**
     * Cập nhật thông tin của thực thể.
     *
     * @param updateEntity Đối tượng DTO (Data Transfer Object) chứa thông tin cập nhật.
     * @param id Khóa chính (ID) của thực thể cần cập nhật.
     * @throws HttpException Ngoại lệ nếu có lỗi xảy ra.
     */
    public <S> void update(S updateEntity, ID id,Class<E> entityClass) throws HttpException;

    /**
     * Xóa thực thể.
     *
     * @param deleteEntity Đối tượng DTO (Data Transfer Object) chứa thông tin thực thể cần xóa.
     * @param id Khóa chính (ID) của thực thể cần xóa.
     * @throws HttpException Ngoại lệ nếu có lỗi xảy ra.
     */
    public <S> void delete(S deleteEntity, ID id,Class<E> entityClass) throws HttpException;
}
