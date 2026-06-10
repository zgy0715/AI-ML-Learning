# ========== 第6课：Pandas入门 ==========
import pandas as pd
import numpy as np

# ========== 1. 创建DataFrame（类似Excel表格）==========
print("===== 创建DataFrame =====")

# 从字典创建
data = {
    "姓名": ["张三", "李四", "王五", "赵六", "小明"],
    "年龄": [18, 20, 19, 21, 20],
    "成绩": [92, 78, 88, 65, 95],
    "城市": ["北京", "上海", "广州", "深圳", "北京"]
}

df = pd.DataFrame(data)
print(df)
print("\n" + "="*40)


# ========== 2. 查看数据 ==========
print("\n===== 查看数据 =====")

# 只看前几行
print("前3行:\n", df.head(3))

# 只看后几行
print("\n后2行:\n", df.tail(2))

# 基本信息
print("\n基本信息:")
print(df.info())

# 统计描述
print("\n统计描述:")
print(df.describe())


# ========== 3. 选择数据 ==========
print("\n===== 选择数据 =====")

# 选择一列（返回Series）
print("姓名列:")
print(df["姓名"])

# 选择多列
print("\n姓名和成绩:")
print(df[["姓名", "成绩"]])

# 选择行（通过位置：iloc）
print("\n第2行（iloc[1]）:", df.iloc[1].tolist())

# 选择行（通过条件）
print("\n成绩 >= 90 的学生:")
print(df[df["成绩"] >= 90])

# 多个条件
print("\n成绩 >= 80 且 年龄 >= 20:")
print(df[(df["成绩"] >= 80) & (df["年龄"] >= 20)])

# loc：通过标签/条件选择
print("\nloc 选择姓名和成绩列:")
print(df.loc[0:2, ["姓名", "成绩"]])  # 前3行的姓名和成绩


# ========== 4. 添加/删除列 ==========
print("\n===== 添加/删除列 =====")

# 添加新列（基于现有列计算）
df["等级"] = df["成绩"].apply(lambda x:
    "优秀" if x >= 90 else
    "良好" if x >= 80 else
    "中等" if x >= 70 else
    "及格" if x >= 60 else "不及格"
)
print("添加等级列:\n", df)

# 添加常量列
df["是否通过"] = df["成绩"] >= 60
print("\n添加是否通过列:\n", df)


# ========== 5. 排序和分组 ==========
print("\n===== 排序 =====")
print("按成绩降序排序:\n", df.sort_values("成绩", ascending=False))

print("\n===== 分组统计 =====")
# 按城市分组，计算平均成绩
print("各城市平均成绩:")
print(df.groupby("城市")["成绩"].mean())

print("\n各城市学生人数:")
print(df.groupby("城市")["姓名"].count())


# ========== 6. 读取CSV文件 ==========
print("\n===== 读写CSV =====")

# 保存到CSV
df.to_csv("students.csv", index=False, encoding="utf-8-sig")
print("已保存到 students.csv")

# 读取CSV
df_read = pd.read_csv("students.csv", encoding="utf-8-sig")
print("读取成功:\n", df_read)


# ========== 7. 处理缺失值 ==========
print("\n===== 缺失值处理 =====")

# 制造一些缺失数据
df_with_na = df.copy()
df_with_na.loc[1, "成绩"] = np.nan  # 把李四的成绩设为缺失
df_with_na.loc[3, "城市"] = np.nan  # 把赵六的城市设为缺失
print("有缺失的数据:\n", df_with_na)

# 检查缺失值
print("\n缺失值数量:")
print(df_with_na.isna().sum())

# 填充缺失值
df_filled = df_with_na.fillna({"成绩": df_with_na["成绩"].mean(), "城市": "未知"})
print("\n填充后的数据:\n", df_filled)

# 删除有缺失值的行
df_dropped = df_with_na.dropna()
print("\n删除缺失行后（只剩3行）:\n", df_dropped)
