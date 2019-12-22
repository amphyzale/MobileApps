package com.enfor.myapp.carcrashmap.repository;

import com.enfor.myapp.carcrashmap.domain.Comments;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentsRepository extends JpaRepository<Comments, Long> {
}
