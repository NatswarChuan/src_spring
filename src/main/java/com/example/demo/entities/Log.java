package com.example.demo.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.UUID;

import lombok.*;

/**
 * Đại diện cho một bản ghi log trong hệ thống.
 *
 * @Data: Annotation Lombok để tự động tạo các getter, setter, equals, hashCode, toString.
 * @Entity: Annotation JPA để đánh dấu lớp này là một thực thể (entity) trong cơ sở dữ liệu.
 * @Table: Annotation JPA để chỉ định tên bảng và schema cho thực thể này trong cơ sở dữ liệu.
 */
@Data
@Entity
@Table(name = "rtls_logs", schema = "relationship")
public class Log implements Serializable {

    /**
     * Id duy nhất của log.
     *
     * @Id: Annotation JPA để đánh dấu trường này là khóa chính (primary key).
     * @Column: Annotation JPA để chỉ định tên cột và các thuộc tính khác của cột.
     * @GeneratedValue: Annotation JPA để chỉ định cách sinh giá trị cho trường logId.
     */
    @Id
    @Column(name = "log_id", nullable = false)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID logId;

    /**
     * Loại log.
     *
     * @Column: Annotation JPA để chỉ định tên cột và các thuộc tính khác của cột.
     */
    @Column(name = "type")
    private String type;

    /**
     * API liên quan đến log này.
     *
     * @Column: Annotation JPA để chỉ định tên cột và các thuộc tính khác của cột.
     */
    @Column(name = "api")
    private String api;

    /**
     * Thời điểm tạo log.
     *
     * @Column: Annotation JPA để chỉ định tên cột và các thuộc tính khác của cột.
     */
    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt = LocalDateTime.now();

    /**
     * Mô tả của log.
     *
     * @Column: Annotation JPA để chỉ định tên cột và các thuộc tính khác của cột.
     */
    @Column(name = "description")
    private String description;
}
