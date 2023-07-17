package com.example.demo.Interfaces;

import java.util.List;

import org.springframework.data.domain.Page;

import com.example.demo.Commond.HttpException;

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
    public <S extends IDto<E>> void create(S newEntity) throws HttpException;

    /**
     * Cập nhật thông tin của thực thể.
     *
     * @param updateEntity Đối tượng DTO (Data Transfer Object) chứa thông tin cập nhật.
     * @param id Khóa chính (ID) của thực thể cần cập nhật.
     * @throws HttpException Ngoại lệ nếu có lỗi xảy ra.
     */
    public <S extends IDto<E>> void update(S updateEntity, ID id) throws HttpException;

    /**
     * Xóa thực thể.
     *
     * @param deleteEntity Đối tượng DTO (Data Transfer Object) chứa thông tin thực thể cần xóa.
     * @param id Khóa chính (ID) của thực thể cần xóa.
     * @throws HttpException Ngoại lệ nếu có lỗi xảy ra.
     */
    public <S extends IDto<E>> void delete(S deleteEntity, ID id) throws HttpException;

    /**
     * Tạo mới thực thể.
     *
     * @param newEntity Thực thể mới cần tạo.
     * @throws HttpException Ngoại lệ nếu có lỗi xảy ra.
     */
    public void create(E newEntity) throws HttpException;

    /**
     * Cập nhật thông tin của thực thể.
     *
     * @param updateEntity Thực thể chứa thông tin cập nhật.
     * @param id Khóa chính (ID) của thực thể cần cập nhật.
     * @throws HttpException Ngoại lệ nếu có lỗi xảy ra.
     */
    public void update(E updateEntity, ID id) throws HttpException;

    /**
     * Xóa thực thể.
     *
     * @param deleteEntity Thực thể cần xóa.
     * @param id Khóa chính (ID) của thực thể cần xóa.
     * @throws HttpException Ngoại lệ nếu có lỗi xảy ra.
     */
    public void delete(E deleteEntity, ID id) throws HttpException;
}
