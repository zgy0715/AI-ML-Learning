import matplotlib.pyplot as plt

# 设置中文字体（和lesson_7一样）
plt.rcParams['font.sans-serif'] = ['SimHei', 'Microsoft YaHei', 'DejaVu Sans']
plt.rcParams['axes.unicode_minus'] = False
cities = ["北京", "上海", "广州", "深圳", "成都"]
aqi = [85, 72, 68, 55, 90]  # 空气质量指数
#要求： 
#用柱状图展示各城市 AQI

#标题为"各城市空气质量指数"

#AQI > 80 的柱子标红色，其余标绿色

#保存为 aqi_chart.png
plt.figure(figsize=(8, 5))
colors = ["red" if value > 80 else "green" for value in aqi]
bars = plt.bar(cities, aqi, color=colors)

# 在每个柱子上方标出具体数值
for bar, value in zip(bars, aqi):
    plt.text(bar.get_x() + bar.get_width() / 2, bar.get_height() + 1,
             str(value), ha="center", fontsize=11)

# 标题和标签
plt.title("各城市空气质量指数", fontsize=14)
plt.xlabel("城市", fontsize=12)
plt.ylabel("AQI", fontsize=12)
plt.ylim(0, max(aqi) + 10)

# 保存
plt.savefig("aqi_chart.png", dpi=100, bbox_inches="tight")
plt.close()

print("图表已生成！打开 aqi_chart.png 看看效果")