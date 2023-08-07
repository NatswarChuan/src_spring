package com.example.demo.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.commond.Converter;
import com.example.demo.commond.HttpException;
import com.example.demo.interfaces.IService;

/**
 * Lớp abstract đại diện cho một dịch vụ cơ bản.
 *
 * @param <E>  Kiểu dữ liệu của thực thể (entity).
 * @param <ID> Kiểu dữ liệu của khóa chính (ID).
 */
@Service
@Transactional(rollbackFor = HttpException.class)
public abstract class AbService<E, ID> implements IService<E, ID> {
    @Autowired
    protected MongoRepository<E, ID> repository;
    @Autowired
    protected Converter converter;

    /**
     * {@inheritDoc}
     *
     * @throws HttpException nếu danh sách thực thể trống.
     */
    @Override
    public <S> List<S> findAll(Class<S> dtoClass) throws HttpException {
        List<E> data = repository.findAll();
        if (data.size() < 1) {
            throw new HttpException(HttpStatus.BAD_REQUEST, "Danh sách thực thể trống");
        }
        List<S> result = converter.converterList(data, dtoClass);
        return result;
    }

    /**
     * {@inheritDoc}
     *
     * @throws HttpException nếu không tìm thấy thực thể với id tương ứng.
     */
    @Override
    public <S> S findById(ID id, Class<S> dtoClass) throws HttpException {
        Optional<E> result = repository.findById(id);
        if (result.isEmpty()) {
            throw new HttpException(HttpStatus.BAD_REQUEST, "Không tìm thấy thực thể với id= " + id);
        }
        try {
            S s = converter.getModelMapper().map(result.get(), dtoClass);
            return s;
        } catch (Exception ex) {
            throw new HttpException(HttpStatus.INTERNAL_SERVER_ERROR, ex.getMessage());
        }
    }

    /**
     * {@inheritDoc}
     *
     * @throws HttpException nếu danh sách thực thể trống.
     */
    @Override
    public List<E> findAll() throws HttpException {
        List<E> result = repository.findAll();
        if (result.size() < 1) {
            throw new HttpException(HttpStatus.BAD_REQUEST, "Danh sách thực thể trống");
        }
        return result;
    }

    /**
     * {@inheritDoc}
     *
     * @throws HttpException nếu danh sách thực thể trống.
     */
    @Override
    public Page<E> findAll(int page, int size) throws HttpException {
        Pageable paging = PageRequest.of(page, size);
        Page<E> result = repository.findAll(paging);
        if (result.isEmpty()) {
            throw new HttpException(HttpStatus.BAD_REQUEST, "Danh sách thực thể trống");
        }
        return result;
    }

    /**
     * {@inheritDoc}
     *
     * @throws HttpException nếu không tìm thấy thực thể với id tương ứng.
     */
    @Override
    public E findById(ID id) throws HttpException {
        Optional<E> result = repository.findById(id);
        if (result.isEmpty()) {
            throw new HttpException(HttpStatus.BAD_REQUEST, "Không tìm thấy thực thể với id= " + id);
        }
        return result.get();
    }

    /**
     * {@inheritDoc}
     *
     * @throws HttpException nếu có lỗi khi tạo thực thể mới.
     */
    @Override
    public <S> void create(S newEntity,Class<E> entityClass) throws HttpException { 
        E ent = converter.getModelMapper().map(newEntity, entityClass);
        repository.save(ent);
    }

    /**
     * {@inheritDoc}
     *
     * @throws HttpException nếu có lỗi khi cập nhật thực thể.
     */
    @Override
    public <S> void update(S updateEntity, ID id,Class<E> entityClass) throws HttpException {
        this.findById(id);
        E ent = converter.getModelMapper().map(updateEntity, entityClass);
        repository.save(ent);
    }

    /**
     * {@inheritDoc}
     *
     * @throws HttpException nếu có lỗi khi xóa thực thể.
     */
    @Override
    public <S> void delete(S deleteEntity, ID id,Class<E> entityClass) throws HttpException {
        this.findById(id);
        E ent = converter.getModelMapper().map(deleteEntity, entityClass);
        repository.delete(ent);
    }

    public E save(E entity) {
        return repository.save(entity);
    }
}
