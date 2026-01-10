# Day-3 Spring-Boot + MySQL
## Day-3 微服务
启动：java -jar auth-service-0.0.1-SNAPSHOT.jar  
测试：curl -X POST localhost:8080/api/login -H "Content-Type: application/json" -d '{"username":"inheritor1","password":"123"}'  
期望返回：INHERITOR
## Day-4 上传-审批-下载
# ① 传承人登记上传
curl -X POST http://localhost:8080/api/upload \
  -H "Content-Type: application/json" \
  -d '{"filename":"shoushan-preview.enc","username":"inheritor1"}'
# 回显：UPLOAD_REGISTERED

# ② 政府审批通过
curl -X POST http://localhost:8080/api/approve \
  -H "Content-Type: application/json" \
  -d '{"reviewer":"government1","assetId":1,"decision":"APPROVED","comment":"ok"}'
# 回显：APPROVED

# ③ 企业获取下载路径
curl "http://localhost:8080/api/download/1?username=company1"
# 回显：/heritage/shoushan-preview.enc
## Day-5 全链路审计
#记录谁、何时、下载了什么；用 Isolation Forest 发现异常账号。
