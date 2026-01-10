import pandas as pd
from sklearn.ensemble import IsolationForest

# 1. 读文件
df = pd.read_csv('audit.csv')

# 2. 简单特征：每小时下载次数
df['hour'] = pd.to_datetime(df['created_at']).dt.hour
feat = df.groupby(['user_name', 'hour']).size().reset_index(name='down_cnt')
feat = feat.set_index('user_name')

# 3. Isolation Forest 判异常
clf = IsolationForest(contamination=0.1, random_state=42)
feat['anomaly'] = clf.fit_predict(feat[['down_cnt']])
anomaly_users = feat[feat['anomaly'] == -1].index.unique()
print(df.columns.tolist())
print("疑似异常账号：", list(anomaly_users))
