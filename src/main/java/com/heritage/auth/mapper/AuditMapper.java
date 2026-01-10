package com.heritage.auth.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface AuditMapper {
    @Insert("INSERT INTO audit_log(user_name,action,asset_id,ip,user_agent) VALUES(#{user},#{action},#{assetId},#{ip},#{userAgent})")
    void insert(@Param("user") String user,
                @Param("action") String action,
                @Param("assetId") Long assetId,
                @Param("ip") String ip,
                @Param("userAgent") String userAgent);
}
