package io.github.runnlin.dao;

import io.github.runnlin.pojo.Book;
import io.github.runnlin.pojo.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BookDAO extends JpaRepository<Book, Integer> {
    // findAllByCategory 之所以可以实现，是因为在Book中有注解
    List<Book> findByCategory(Category category);
    // 这个方法名字不需要实现，因为他的起名遵循JPA的规则，在by后面添加参数可以自动生成对应方法
    List<Book> findByTitleLikeOrAuthorLike(String keyword1, String keyword2);
}
