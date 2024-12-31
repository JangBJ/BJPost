package com.bjpost.repository;

import com.bjpost.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PostRepository extends JpaRepository<Post, Long>{

    // id가 후순위인것부터 출력시키면 그것이 최근 등록일 순으로 만드는 것 아닌가
    @Query("SELECT p FROM Post p ORDER BY p.id desc limit 10")
    List<Post> findAllOrderByIdDesc();

}
