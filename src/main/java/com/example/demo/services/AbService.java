package com.example.demo.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.commond.HttpException;
import com.example.demo.interfaces.IDto;
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
    protected JpaRepository<E, ID> repository;

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
    public <S extends IDto<E>> void create(S newEntity) throws HttpException {
        E ent = newEntity.toEntity();
        repository.save(ent);
    }

    /**
     * {@inheritDoc}
     *
     * @throws HttpException nếu có lỗi khi cập nhật thực thể.
     */
    @Override
    public <S extends IDto<E>> void update(S updateEntity, ID id) throws HttpException {
        this.findById(id);
        E ent = updateEntity.toEntity();
        repository.save(ent);
    }

    /**
     * {@inheritDoc}
     *
     * @throws HttpException nếu có lỗi khi xóa thực thể.
     */
    @Override
    public <S extends IDto<E>> void delete(S deleteEntity, ID id) throws HttpException {
        this.findById(id);
        E ent = deleteEntity.toEntity();
        repository.delete(ent);
    }

    /**
     * {@inheritDoc}
     *
     * @throws HttpException nếu có lỗi khi tạo thực thể mới.
     */
    @Override
    public void create(E newEntity) throws HttpException {
        repository.save(newEntity);
    }

    /**
     * {@inheritDoc}
     *
     * @throws HttpException nếu có lỗi khi cập nhật thực thể.
     */
    @Override
    public void update(E updateEntity, ID id) throws HttpException {
        this.findById(id);
        repository.save(updateEntity);
    }

    /**
     * {@inheritDoc}
     *
     * @throws HttpException nếu có lỗi khi xóa thực thể.
     */
    @Override
    public void delete(E deleteEntity, ID id) throws HttpException {
        this.findById(id);
        repository.delete(deleteEntity);
    }

    public E save(E entity) {
        return repository.save(entity);
    }
}
