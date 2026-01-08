package com.heritage.auth.controller;
import com.heritage.auth.entity.*;
import com.heritage.auth.mapper.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.Map;
@RestController
@RequestMapping("/api")
public class AuthController {
    @Autowired
    UserMapper userMapper;
    @Autowired          // 新增
    AssetMapper assetMapper; 
    @PostMapping("/login")
    public String login(@RequestBody User u){
        User db = userMapper.findByUsername(u.getUsername());
        if(db!=null && db.getPassword().equals(u.getPassword()))
            return db.getRole();
        return "FAIL";
    }
    // 上传登记
   @PostMapping("/upload")
   public String upload(@RequestBody Map<String,String> m){
    String filename = m.get("filename");
    String user   = m.get("username");
    // 简单鉴权：必须是 INHERITOR
    User u = userMapper.findByUsername(user);
    if(u==null || !u.getRole().equals("INHERITOR")) return "NO_PERMISSION";
    // 登记到 asset 表
    assetMapper.insertAsset(filename, user, "/heritage/"+filename);
    return "UPLOAD_REGISTERED";
}

// 政府审批
   @PostMapping("/approve")
   public String approve(@RequestBody Map<String,String> m){
    String reviewer = m.get("reviewer");
    Long assetId = Long.valueOf(m.get("assetId"));
    String decision = m.get("decision");   // APPROVED / REJECTED
    // 简单鉴权：必须是 GOVERNMENT
    User u = userMapper.findByUsername(reviewer);
    if(u==null || !u.getRole().equals("GOVERNMENT")) return "NO_PERMISSION";
    assetMapper.insertApproval(assetId, reviewer, decision, m.get("comment"));
    assetMapper.updateAssetStatus(assetId, decision);
    return decision;
}

// 企业下载（只返回加密文件路径）
   @GetMapping("/download/{assetId}")
   public String download(@PathVariable("assetId") Long assetId,
                       @RequestParam("username") String username){
    User u = userMapper.findByUsername(username);
    if(u==null || !u.getRole().equals("COMPANY")) return "NO_PERMISSION";
    Asset a = assetMapper.findAssetById(assetId);
    if(a==null || !"APPROVED".equals(a.getStatus())) return "NOT_APPROVED";
    return a.getEncPath();   // 仅给路径，前端再去 MinIO 拿
}
}
