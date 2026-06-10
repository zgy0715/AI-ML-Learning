# ========== 第7课：Matplotlib入门 ==========
import matplotlib.pyplot as plt
import numpy as np

# 尝试设置中文字体
plt.rcParams['font.sans-serif'] = ['SimHei', 'Microsoft YaHei', 'DejaVu Sans']# 设置中文字体，SimHei是黑体，Microsoft YaHei是微软雅黑，DejaVu Sans是默认字体的备选
plt.rcParams['axes.unicode_minus'] = False #    解决负号显示问题    

print("===== Matplotlib 数据可视化 =====")
print("图表会保存为图片文件，用看图软件打开\n")


# ========== 1. 折线图（反映趋势）==========
print("1. 生成折线图...")

days = ["周一", "周二", "周三", "周四", "周五", "周六", "周日"]
hours = [2, 1.5, 3, 2.5, 1, 4, 5]

plt.figure(figsize=(8, 5))
plt.plot(days, hours, marker="o", linestyle="-", color="blue", linewidth=2, markersize=8)# marker表示数据点的样式，linestyle表示线条的样式，color表示线条的颜色，linewidth表示线条的宽度，markersize表示数据点的大小

plt.title("一周学习时间分布", fontsize=14)
plt.xlabel("星期", fontsize=12)
plt.ylabel("学习时间(小时)", fontsize=12)
plt.grid(True, alpha=0.3)

plt.savefig("line_chart.png", dpi=100, bbox_inches="tight")
plt.close()
print("  折线图已保存: line_chart.png\n")


# ========== 2. 柱状图（对比大小）==========
print("2. 生成柱状图...")

subjects = ["Python", "数学", "英语", "物理", "体育"]
scores = [92, 85, 78, 90, 88]

plt.figure(figsize=(8, 5))
bars = plt.bar(subjects, scores, color=["#FF6B6B", "#4ECDC4", "#45B7D1", "#96CEB4", "#FFEAA7"])

for bar, score in zip(bars, scores):
    plt.text(bar.get_x() + bar.get_width()/2, bar.get_height() + 1,
             str(score), ha="center", fontsize=12)

plt.title("各科成绩", fontsize=14)
plt.xlabel("科目", fontsize=12)
plt.ylabel("分数", fontsize=12)
plt.ylim(0, 100)

plt.savefig("bar_chart.png", dpi=100, bbox_inches="tight")
plt.close()
print("  柱状图已保存: bar_chart.png\n")


# ========== 3. 散点图（看相关性）==========
print("3. 生成散点图...")

np.random.seed(42)
study_hours = np.random.uniform(1, 8, 30)
exam_scores = study_hours * 10 + np.random.normal(0, 8, 30)

plt.figure(figsize=(8, 5))
plt.scatter(study_hours, exam_scores, color="#FF6B6B", alpha=0.6, s=80)

plt.title("学习时间 vs 考试成绩", fontsize=14)
plt.xlabel("每天学习时间(小时)", fontsize=12)
plt.ylabel("考试成绩", fontsize=12)
plt.grid(True, alpha=0.3)

z = np.polyfit(study_hours, exam_scores, 1)
p = np.poly1d(z)
x_trend = np.linspace(1, 8, 100)
plt.plot(x_trend, p(x_trend), "--", color="gray", alpha=0.7, label=f"趋势线 (y={z[0]:.1f}x+{z[1]:.0f})")
plt.legend()

plt.savefig("scatter_chart.png", dpi=100, bbox_inches="tight")
plt.close()
print("  散点图已保存: scatter_chart.png\n")


# ========== 4. 饼图（看占比）==========
print("4. 生成饼图...")

labels = ["吃饭", "学习", "睡觉", "娱乐", "其他"]
sizes = [8, 6, 8, 4, 2]
colors = ["#FF6B6B", "#4ECDC4", "#45B7D1", "#FFEAA7", "#DDA0DD"]

plt.figure(figsize=(7, 7))
plt.pie(sizes, labels=labels, colors=colors, autopct="%1.1f%%",
        startangle=90, shadow=True)

plt.title("一天时间分配", fontsize=14)
plt.savefig("pie_chart.png", dpi=100, bbox_inches="tight")
plt.close()
print("  饼图已保存: pie_chart.png\n")


# ========== 5. 实战：成绩分析报告 ==========
print("5. 生成成绩分析报告...")

import pandas as pd

np.random.seed(100)
students_data = {
    "姓名": [f"学生{i}" for i in range(1, 31)],
    "数学": np.random.randint(50, 100, 30),
    "英语": np.random.randint(50, 100, 30),
    "物理": np.random.randint(50, 100, 30),
}
df = pd.DataFrame(students_data)
df["总分"] = df["数学"] + df["英语"] + df["物理"]

fig, axes = plt.subplots(2, 2, figsize=(14, 10))

# 1. 成绩分布直方图
axes[0, 0].hist(df["总分"], bins=10, color="#4ECDC4", edgecolor="white", alpha=0.7)
axes[0, 0].axvline(df["总分"].mean(), color="red", linestyle="--", label=f"平均分={df['总分'].mean():.0f}")
axes[0, 0].set_title("总分分布", fontsize=12)
axes[0, 0].set_xlabel("总分")
axes[0, 0].set_ylabel("人数")
axes[0, 0].legend()

# 2. 各科平均分柱状图
avg_scores = [df["数学"].mean(), df["英语"].mean(), df["物理"].mean()]
axes[0, 1].bar(["数学", "英语", "物理"], avg_scores, color=["#FF6B6B", "#45B7D1", "#96CEB4"])
for i, v in enumerate(avg_scores):
    axes[0, 1].text(i, v + 0.5, f"{v:.1f}", ha="center")
axes[0, 1].set_title("各科平均分", fontsize=12)
axes[0, 1].set_ylim(0, 100)

# 3. 数学vs英语散点图
axes[1, 0].scatter(df["数学"], df["英语"], color="#FF6B6B", alpha=0.6, s=60)
axes[1, 0].set_title("数学 vs 英语 成绩", fontsize=12)
axes[1, 0].set_xlabel("数学")
axes[1, 0].set_ylabel("英语")
axes[1, 0].grid(True, alpha=0.3)

# 4. 前十名柱状图
top10 = df.sort_values("总分", ascending=False).head(10)
axes[1, 1].bar(range(10), top10["总分"], color="#45B7D1", alpha=0.8)
axes[1, 1].set_xticks(range(10))
axes[1, 1].set_xticklabels(top10["姓名"], rotation=45)
axes[1, 1].set_title("总分前10名", fontsize=12)
axes[1, 1].set_ylabel("总分")

plt.suptitle("学生成绩分析报告", fontsize=16, fontweight="bold")
plt.tight_layout()
plt.savefig("score_analysis.png", dpi=120, bbox_inches="tight")
plt.close()
print("  成绩分析报告已保存: score_analysis.png")
print("\n所有图表已生成完毕！去文件夹查看图片吧")
