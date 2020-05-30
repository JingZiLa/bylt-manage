package com.mirror.dao;

import com.mirror.domain.Member;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

/**
 * @Author Mirror
 * @CreateDate 2020/3/2.
 */
@Repository
public interface MemberDao {

    @Select("select * from member where id = #{id}")
    public Member findByMemberId(String id);
}
