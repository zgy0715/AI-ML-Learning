"""
第10课：K近邻（K-Nearest Neighbors）
最直观的分类算法 -- "近朱者赤，近墨者黑"
"""

import numpy as np
import matplotlib.pyplot as plt
from collections import Counter
import os

# 图片输出目录（相对于脚本位置）
IMG_DIR = os.path.join(os.path.dirname(os.path.abspath(__file__)), "..", "..", "data", "images")
os.makedirs(IMG_DIR, exist_ok=True)

plt.rcParams['font.sans-serif'] = ['SimHei', 'Microsoft YaHei', 'DejaVu Sans']
plt.rcParams['axes.unicode_minus'] = False

print("=" * 50)
print("第10课：KNN -- K近邻分类算法")
print("=" * 50)


# ============================================================
# 第一部分：KNN 原理
# ============================================================
print("\n1. KNN 原理（一句话）")
print("-" * 30)
print("""
  要判断一个新数据属于哪一类：
  → 找它最近的 K 个邻居
  → 看这些邻居中哪一类最多
  → 新数据就属于那一类

  比如：你是你身边最亲近的 5 个朋友的平均值
""")

print("""
  KNN 的三个核心问题：
  ① K 选多大？    → K=3, K=5 是常用值
  ② 怎么算"近"？ → 欧氏距离（两点间直线距离）
  ③ 分类还是回归？→ 分类投票，回归取平均
""")


# ============================================================
# 第二部分：造数据 + 可视化
# ============================================================
print("\n2. 准备数据")
print("-" * 30)

# 生成两类数据：A类（红色）和 B类（蓝色）
np.random.seed(42)
class_a = np.random.randn(15, 2) * 1.5 + np.array([2, 3])   # 中心在 (2,3)
class_b = np.random.randn(15, 2) * 1.5 + np.array([6, 2])   # 中心在 (6,2)

X_train = np.vstack([class_a, class_b])  # 合并所有数据
y_train = np.array([0] * 15 + [1] * 15)  # 标签：0=A类，1=B类

print(f"  训练数据：共 {len(X_train)} 个样本")
print(f"  A类（红色）: {np.sum(y_train == 0)} 个")
print(f"  B类（蓝色）: {np.sum(y_train == 1)} 个")

# 画散点图
plt.figure(figsize=(8, 6))
plt.scatter(class_a[:, 0], class_a[:, 1], c="red", s=80, label="A类", edgecolors="black")
plt.scatter(class_b[:, 0], class_b[:, 1], c="blue", s=80, label="B类", edgecolors="black")
plt.title("训练数据分布", fontsize=14)
plt.xlabel("特征1")
plt.ylabel("特征2")
plt.legend()
plt.grid(True, alpha=0.3)
plt.savefig(os.path.join(IMG_DIR, "knn_data.png"), dpi=100, bbox_inches="tight")
plt.close()
print("  数据图已保存: data/images/knn_data.png")


# ============================================================
# 第三部分：手动实现 KNN
# ============================================================
print("\n\n3. 从零实现 KNN")
print("-" * 30)


def euclidean_distance(x1, x2):
    """计算两个点之间的欧氏距离"""
    return np.sqrt(np.sum((x1 - x2) ** 2))


def knn_predict(X_train, y_train, x_new, k=3):
    """
    KNN 预测一个样本的类别

    步骤：
    1. 计算新点到所有训练点的距离
    2. 按距离从小到大排序
    3. 取前 K 个最近的邻居
    4. 统计邻居中各类别的数量
    5. 返回数量最多的类别
    """

    # 步骤1：计算新点到所有训练点的距离
    distances = [euclidean_distance(x_new, x_train) for x_train in X_train]

    # 步骤2：获取距离最近的 K 个点的索引
    # np.argsort 返回排序后的索引位置
    k_indices = np.argsort(distances)[:k]

    # 步骤3：获取这些邻居的标签
    k_labels = [y_train[i] for i in k_indices]
    print(f"    K={k} 个邻居的标签: {k_labels}")

    # 步骤4：统计投票
    vote_count = Counter(k_labels)
    print(f"    投票统计: {dict(vote_count)}")

    # 步骤5：返回得票最多的类别
    predicted = vote_count.most_common(1)[0][0]
    return predicted


# 测试：预测一个新点
test_point = np.array([4, 3])
print(f"\n  预测新点 ({test_point[0]}, {test_point[1]}) 的类别：")

for k in [1, 3, 5, 7]:
    pred = knn_predict(X_train, y_train, test_point, k=k)
    class_name = "A类" if pred == 0 else "B类"
    print(f"  结果: K={k} -> {class_name}\n")


# ============================================================
# 第四部分：可视化 KNN 决策过程
# ============================================================
print("\n4. 可视化 KNN 分类过程")
print("-" * 30)


def plot_knn_decision(X_train, y_train, x_new, k=3):
    """画出 KNN 的决策过程"""
    # 计算所有距离
    distances = [euclidean_distance(x_new, x) for x in X_train]
    k_indices = np.argsort(distances)[:k]

    plt.figure(figsize=(8, 6))

    # 画所有训练点
    colors = ["red" if y == 0 else "blue" for y in y_train]
    for i in range(len(X_train)):
        xi = X_train[i]
        ci = colors[i]
        # 如果是 K 个邻居之一，放大显示
        if i in k_indices:
            plt.scatter(xi[0], xi[1], c=ci, s=200, edgecolors="black", linewidth=2, alpha=0.8)
            # 画连线
            plt.plot([x_new[0], xi[0]], [x_new[1], xi[1]], "gray", linestyle="--", alpha=0.5)
        else:
            plt.scatter(xi[0], xi[1], c=ci, s=60, alpha=0.3)

    # 画新点
    plt.scatter(x_new[0], x_new[1], c="green", s=200, marker="*", edgecolors="black",
                linewidth=2, zorder=5, label="待分类点")

    # 用圆标出 K 个邻居的范围
    max_dist = sorted(distances)[k-1]
    circle = plt.Circle(x_new, max_dist, fill=False, color="green", linestyle="--", linewidth=2)
    plt.gca().add_patch(circle)

    plt.title(f"KNN 分类过程（K={k}）", fontsize=14)
    plt.xlabel("特征1")
    plt.ylabel("特征2")
    plt.legend()
    plt.grid(True, alpha=0.3)
    plt.axis("equal")
    plt.savefig(os.path.join(IMG_DIR, f"knn_k{k}_decision.png"), dpi=100, bbox_inches="tight")
    plt.close()
    print(f"  K={k} 决策图已保存: data/images/knn_k{k}_decision.png")


plot_knn_decision(X_train, y_train, test_point, k=3)
plot_knn_decision(X_train, y_train, test_point, k=5)


# ============================================================
# 第五部分：K 值对结果的影响
# ============================================================
print("\n\n5. K 值的选择（调参）")
print("-" * 30)
print("""
  K 值的影响：

  K 太小（K=1）：
    → 只考虑最近的一个邻居
    → 容易受噪音影响（过拟合）
    → 好比只凭一个朋友就定义你的为人

  K 适中（K=5）：
    → 综合考虑多个邻居
    → 抗噪音能力强
    → 最常用的选择

  K 太大（K=全体）：
    → 只看全局多数
    → 忽略了局部信息（欠拟合）
    → 好比问全校人"你像谁"，不管你的朋友圈
""")

# 演示 K 值不同导致分类结果不同
print("  演示不同 K 值对同一个点的分类结果：")
edge_point = np.array([4.5, 2.0])
for k in [1, 3, 5, 9, 15]:
    pred = knn_predict(X_train, y_train, edge_point, k=k)
    class_name = "A类" if pred == 0 else "B类"
    print(f"    K={k:2d} -> {class_name}（投票结果见上）")
    print()


# ============================================================
# 第六部分：绘制决策边界
# ============================================================
print("6. 绘制决策边界")
print("-" * 30)
print("  决策边界 = 两类数据的分界线")
print("  正在生成，这需要一点时间...")

# 在平面上均匀取点，每个点用 KNN 预测
x_min, x_max = X_train[:, 0].min() - 1, X_train[:, 0].max() + 1
y_min, y_max = X_train[:, 1].min() - 1, X_train[:, 1].max() + 1

# 生成网格点
xx, yy = np.meshgrid(np.arange(x_min, x_max, 0.1),
                     np.arange(y_min, y_max, 0.1))

# 预测每个网格点的类别
Z = np.array([knn_predict(X_train, y_train, np.array([x, y]), k=5)
              for x, y in zip(xx.ravel(), yy.ravel())])
Z = Z.reshape(xx.shape)

# 画决策边界
plt.figure(figsize=(10, 8))
plt.contourf(xx, yy, Z, alpha=0.3, cmap=plt.cm.RdYlBu_r)
plt.scatter(class_a[:, 0], class_a[:, 1], c="red", s=80, label="A类", edgecolors="black")
plt.scatter(class_b[:, 0], class_b[:, 1], c="blue", s=80, label="B类", edgecolors="black")
plt.title("KNN 决策边界（K=5）", fontsize=14)
plt.xlabel("特征1")
plt.ylabel("特征2")
plt.legend()
plt.grid(True, alpha=0.3)
plt.savefig(os.path.join(IMG_DIR, "knn_decision_boundary.png"), dpi=100, bbox_inches="tight")
plt.close()
print("  决策边界图已保存: data/images/knn_decision_boundary.png")
print("  红色区域 = 模型认为属于A类")
print("  蓝色区域 = 模型认为属于B类")
print("  中间分界线就是决策边界\n")


# ============================================================
# 第七部分：评价 KNN
# ============================================================
print("\n7. KNN 的优缺点")
print("-" * 30)
print("""
  KNN 优点：
  + 简单直观，容易理解
  + 不需要训练（直接拿数据预测）
  + 对异常数据不敏感
  + 适合多分类问题

  KNN 缺点：
  - 预测慢（要算所有点的距离）
  - 需要大量内存存所有数据
  - 对特征的尺度敏感（需要归一化）
  - 维度灾难（特征太多效果差）
""")


# ============================================================
# 第八部分：总结
# ============================================================
print("\n" + "=" * 50)
print("第10课总结")
print("=" * 50)
print("""
  KNN 核心思想：
  1. 算距离（欧氏距离）
  2. 找邻居（最近的 K 个）
  3. 投票决定（少数服从多数）

  K 是关键参数：
  → 小 K 容易过拟合，大 K 容易欠拟合
  → 一般用交叉验证选最优 K

  适用场景：
  → 小数据集
  → 低维数据（特征不多）
  → 分类问题

  下节课预告：模型评估
  学会了算法，怎么知道它好不好？
  用准确率、混淆矩阵来评估！
""")
