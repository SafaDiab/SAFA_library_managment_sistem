package com.library.managment.library_managment_sistem.repositry;

import com.library.managment.library_managment_sistem.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MemberRepository extends JpaRepository<Member, Long> {
    @Query("SELECT m FROM Member m WHERE (:name IS NULL OR m.name LIKE %:name%) AND (:age IS NULL OR m.age = :age)")
    List<Member> searchMembers(@Param("name") String name, @Param("age") Integer age);
}
