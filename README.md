# fuzhou-heritage-platform
## Day-1 成果
视频直链（浏览器可直接播放）：  
http://8.138.87.115:9000/heritage/shoushan.mp4

播放截图：  
![播放截图](play.png)
## Day-2 国密流加密
- 原视频：shoushan.mp4  
- 预览流（30 s / 600 kbps）：http://8.138.87.115:9000/heritage/preview.enc  
- 解密命令：gmssl sms4 -d -in preview.enc -out preview_dec.mp4 -k 1234567890abcdef1234567890abcdef  
- 截图：  
![抽帧示例](frames/001.jpg)
