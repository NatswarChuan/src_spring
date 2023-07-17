package com.example.demo.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.Commond.LogType;
import com.example.demo.Entities.Log;
import com.example.demo.Repositories.LogRepository;

/**
 * Dịch vụ ghi log.
 */
@Service
public class LogService {

    @Autowired
    LogRepository logRepository;

    /**
     * Ghi log cảnh báo.
     *
     * @param api         API liên quan đến log.
     * @param description Mô tả log.
     */
    public void warning(String api, String description) {
        this.save(api, description, LogType.WARNING);
    }

    /**
     * Ghi log lỗi.
     *
     * @param api         API liên quan đến log.
     * @param description Mô tả log.
     */
    public void error(String api, String description) {
        this.save(api, description, LogType.ERROR);
    }

    /**
     * Ghi log thông tin.
     *
     * @param api         API liên quan đến log.
     * @param description Mô tả log.
     */
    public void log(String api, String description) {
        this.save(api, description, LogType.LOG);
    }

    /**
     * Ghi log thành công.
     *
     * @param api         API liên quan đến log.
     * @param description Mô tả log.
     */
    public void success(String api, String description) {
        this.save(api, description, LogType.SUCCESS);
    }

    /**
     * Ghi log ngoại lệ.
     *
     * @param api         API liên quan đến log.
     * @param description Mô tả log.
     */
    public void exception(String api, String description) {
        this.save(api, description, LogType.EXCEPTION);
    }

    /**
     * Lưu log vào cơ sở dữ liệu.
     *
     * @param api         API liên quan đến log.
     * @param description Mô tả log.
     * @param type        Loại log.
     */
    private void save(String api, String description, String type) {
        Log log = new Log();
        log.setApi(api);
        log.setDescription(description);
        log.setType(type);
        try {
            logRepository.save(log);
        } catch (Exception e) {
            exception(api, e.getMessage());
        }
    }
}
