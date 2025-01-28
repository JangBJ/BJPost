package com.bjpost.repository;

import com.bjpost.dto.response.PostResponseDto;
import com.bjpost.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PostRepository extends JpaRepository<Post, Long>{

    // id가 후순위인것부터 출력시키면 그것이 최근 등록일 순으로 만드는 것 아닌가
    List<Post> findTop10ByOrderByIdDesc();

    // 검색을 위한 JPQL
    // @Query("SELECT p FROM Post p WHERE p.title like concat('%', :searchText, '%') order by p.id desc limit 100")
    List<Post> findTop100ByTitleLike(@Param("searchText") String searchText);

}
