package io.github.runnlin.dao;

import io.github.runnlin.pojo.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryDAO extends JpaRepository<Category, Integer> {
    // 使用JPA的默认方法
}
