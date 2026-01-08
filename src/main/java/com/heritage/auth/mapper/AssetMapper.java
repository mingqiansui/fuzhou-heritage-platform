package com.heritage.auth.mapper;

import com.heritage.auth.entity.Asset;
import org.apache.ibatis.annotations.*;
import java.util.Map;
@Mapper
public interface AssetMapper {

    @Insert("INSERT INTO asset(filename,uploader,enc_path) VALUES(#{filename},#{uploader},#{encPath})")
void insertAsset(@Param("filename") String filename,
                 @Param("uploader") String uploader,
                 @Param("encPath") String encPath);

@Insert("INSERT INTO approval(asset_id,reviewer,decision,comment) VALUES(#{assetId},#{reviewer},#{decision},#{comment})")
void insertApproval(@Param("assetId") Long assetId,
                    @Param("reviewer") String reviewer,
                    @Param("decision") String decision,
                    @Param("comment") String comment);

@Update("UPDATE asset SET status=#{status} WHERE id=#{assetId}")
void updateAssetStatus(@Param("assetId") Long assetId,
                       @Param("status") String status);

@Select("SELECT * FROM asset WHERE id=#{assetId}")
Asset findAssetById(@Param("assetId") Long assetId);
}
