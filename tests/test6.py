import pandas as pd
import numpy as np
data = {
    "姓名": ["A", "B", "C", "D", "E"],
    "数学": [85, 92, None, 78, 95],
    "英语": [88, 76, 90, None, 85],
    "物理": [90, 88, 85, 92, 80]
}
#要求：

#创建DataFrame
df = pd.DataFrame(data)
#把缺失的数学和英语成绩用该科平均分填充
df["数学"] = df["数学"].fillna(df["数学"].mean())
df["英语"] = df["英语"].fillna(df["英语"].mean())
#添加一列"总分"（数学+英语+物理）
df["总分"] = df["数学"] + df["英语"] + df["物理"]
#按总分从高到低排序，输出排名
df.sort_values("总分", ascending=False, inplace=True)
print("排名:")
print(df[["姓名", "总分"]])