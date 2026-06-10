import numpy as np
# 生成30个1-100的随机整数
data = np.random.randint(1, 100, size=30)
print("随机数据:", data)


# 1. 计算平均值和标准差
mean = np.mean(data)
std = np.std(data)
print(f"平均值: {mean}, 标准差: {std}")

# 2. 找出所有大于60的元素
filtered_data = data[data > 60]
print("大于60的元素:", filtered_data)

# 3. 保存到 random_data.npy
np.save("random_data.npy", data)