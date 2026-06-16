# ========== 第5课：NumPy入门 ==========
import numpy as np

# ========== 1. 创建数组 ==========
print("===== 创建数组 =====")

# 从列表创建
arr1 = np.array([1, 2, 3, 4, 5])
print("一维数组:", arr1)

# 二维数组（类似矩阵）
arr2 = np.array([[1, 2, 3], [4, 5, 6]])
print("二维数组:\n", arr2)

# 特殊数组
zeros = np.zeros((3, 3))      # 3x3 全0矩阵
ones = np.ones((2, 4))        # 2x4 全1矩阵
identity = np.eye(3)          # 3x3 单位矩阵（对角线为1）
print("\n全0矩阵:\n", zeros)
print("全1矩阵:\n", ones)
print("单位矩阵:\n", identity)

# arange（类似range，但返回数组）
print("\narange:")
print(np.arange(10))           # 0-9
print(np.arange(2, 10, 2))     # 2, 4, 6, 8（从2到9，步长2）

# linspace（等间隔的N个数）
print("\nlinspace:")
print(np.linspace(0, 1, 5))   # 0到1之间等分5个数: [0, 0.25, 0.5, 0.75, 1]


# ========== 2. 数组的形状 ==========
print("\n===== 数组形状 =====")
arr = np.array([[1, 2, 3], [4, 5, 6], [7, 8, 9], [10, 11, 12]])
print("数组:\n", arr)
print("形状 shape:", arr.shape)     # (4, 3) → 4行3列
print("维度 ndim:", arr.ndim)       # 2
print("元素个数 size:", arr.size)   # 12

# reshape：改变形状
print("\nreshape 成 2x6:\n", arr.reshape(2, 6))
print("reshape 成 3x4:\n", arr.reshape(3, 4))

# reshape 成 1维
print("展开成一维:", arr.flatten())


# ========== 3. 数组运算（核心！）==========
print("\n===== 数组运算 =====")
a = np.array([1, 2, 3, 4])
b = np.array([10, 20, 30, 40])

print("a:", a)
print("b:", b)
print("a + b:", a + b)      # 对应位置相加
print("a * b:", a * b)      # 对应位置相乘
print("a ** 2:", a ** 2)    # 每个元素平方
print("a * 10:", a * 10)    # 广播：标量乘以每个元素

# 广播机制（重要概念）
print("\n广播机制:")
matrix = np.array([[1, 2, 3], [4, 5, 6], [7, 8, 9]])
row = np.array([10, 20, 30])
print("矩阵:\n", matrix)
print("行向量:", row)
print("矩阵 + 行向量:\n", matrix + row)


# ========== 4. 索引和切片 ==========
print("\n===== 索引和切片 =====")
arr = np.array([[1, 2, 3, 4], [5, 6, 7, 8], [9, 10, 11, 12]])
print("数组:\n", arr)

print("第一行:", arr[0])           # 取第一行
print("第一列:", arr[:, 0])        # 取第一列
print("前两行:\n", arr[:2])        # 取前两行
print("第2-3列:\n", arr[:, 1:3])   # 取第2-3列
print("中间2x2块:\n", arr[1:, 2:]) # 从第2行第3列开始


# ========== 5. 统计运算 ==========
print("\n===== 统计运算 =====")
data = np.array([85, 92, 78, 90, 88, 76, 95, 83])

print("数据:", data)
print("平均值 mean:", np.mean(data))
print("中位数 median:", np.median(data))
print("标准差 std:", np.std(data))
print("最大值 max:", np.max(data))
print("最小值 min:", np.min(data))
print("总和 sum:", np.sum(data))


# ========== 6. 条件筛选 ==========
print("\n===== 条件筛选 =====")
scores = np.array([85, 92, 78, 90, 88, 76, 95, 83])

# 找出所有 >= 90 的分数
high_scores = scores[scores >= 90]
print("90分以上的:", high_scores)

# 大于80分的个数
print("80分以上的个数:", np.sum(scores > 80))

# 把不及格的改成60分
fixed = np.where(scores < 60, 60, scores)
print("修正后:", fixed)
