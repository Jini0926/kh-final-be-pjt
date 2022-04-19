package com.popcon.khfinalbpopcon.repository;

import com.popcon.khfinalbpopcon.model.Content;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ContentRepository extends JpaRepository<Content, Long> {

    public Content findByContentCode(Long contentCode);
}
