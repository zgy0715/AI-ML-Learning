"""
第9课：线性回归 -- 从零实现房价预测
用 NumPy 实现第一个 ML 模型！
"""

import numpy as np
import matplotlib.pyplot as plt

plt.rcParams['font.sans-serif'] = ['SimHei', 'Microsoft YaHei', 'DejaVu Sans']
plt.rcParams['axes.unicode_minus'] = False

print("=" * 50)
print("第9课：线性回归 -- 房价预测")
print("=" * 50)


# ============================================================
# 第一部分：生成数据
# ============================================================
print("\n1. 准备数据")
print("-" * 30)

np.random.seed(42)
area = np.random.uniform(30, 150, 50)  # 50套房，30~150平米
price = 0.5 * area + 15 + np.random.normal(0, 8, 50)

print("  生成了 50 套房的数据")
print("  面积范围:", area.min(), "~", area.max(), "平米")
print("  价格范围:", price.min(), "~", price.max(), "万元")

plt.figure(figsize=(8, 5))
plt.scatter(area, price, alpha=0.6, color="#FF6B6B")
plt.title("房屋面积 vs 价格（原始数据）", fontsize=14)
plt.xlabel("面积（平米）")
plt.ylabel("价格（万元）")
plt.grid(True, alpha=0.3)
plt.savefig("housing_data.png", dpi=100, bbox_inches="tight")
plt.close()
print("  数据散点图已保存: housing_data.png")


# ============================================================
# 第二部分：特征归一化（关键！）
# ============================================================
print("\n\n2. 特征归一化（Feature Scaling）")
print("-" * 30)
print("""
  为什么需要归一化？
  面积是 30~150 这么大的数字，w 的梯度会非常大
  → 导致参数爆炸，训练失败

  归一化：把数据缩放到均值为0、标准差为1的范围
  X_norm = (X - X.mean()) / X.std()
""")

area_mean = area.mean()
area_std = area.std()
area_norm = (area - area_mean) / area_std

price_mean = price.mean()
price_std = price.std()
price_norm = (price - price_mean) / price_std

print(f"  原始面积: 均值={area_mean:.1f}, 标准差={area_std:.1f}")
print(f"  归一化后: 均值={area_norm.mean():.2f}, 标准差={area_norm.std():.2f}")
print(f"  归一化后的数据范围: {area_norm.min():.2f} ~ {area_norm.max():.2f}")


# ============================================================
# 第三部分：线性回归原理
# ============================================================
print("\n\n3. 线性回归原理")
print("-" * 30)
print("""
  y = wx + b

  房价 = w * 面积 + b

  w（权重/斜率）：面积每增加1平米，房价涨多少
  b（偏置/截距）：面积为0时的基础价格

  我们的目标：找到最合适的 w 和 b
""")


# ============================================================
# 第四部分：损失函数
# ============================================================
print("\n4. 损失函数（衡量模型的好坏）")
print("-" * 30)
print("""
  均方误差（Mean Squared Error，MSE）:

  loss = 1/n * sum((预测值 - 真实值)^2)
""")


def compute_loss(w, b, X, Y):
    """计算均方误差"""
    n = len(X)
    y_pred = w * X + b
    loss = np.sum((y_pred - Y) ** 2) / n
    return loss


# ============================================================
# 第五部分：梯度下降
# ============================================================
print("\n5. 梯度下降（核心！模型怎么学习？）")
print("-" * 30)
print("""
  想象你站在山顶，闭着眼往山下走：
  哪边最陡就往哪边迈一步，反复走，直到山底

  梯度 = 最陡的方向
  学习率 = 每步迈多大

  w = w - 学习率 * dw
  b = b - 学习率 * db
""")


def gradient_descent(w, b, X, Y, lr):
    """一步梯度下降"""
    n = len(X)
    y_pred = w * X + b

    dw = -2 * np.sum(X * (Y - y_pred)) / n
    db = -2 * np.sum(Y - y_pred) / n

    w = w - lr * dw
    b = b - lr * db
    return w, b, dw, db


# ============================================================
# 第六部分：训练循环
# ============================================================
print("\n6. 开始训练...")
print("-" * 30)

w, b = 0.0, 0.0
learning_rate = 0.5
epochs = 30

print(f"  学习率: {learning_rate}")
print(f"  训练轮数: {epochs}\n")

# 注意：在归一化后的数据上训练
X_train = area_norm
Y_train = price_norm

loss_history = []
w_history = []
b_history = []

for epoch in range(epochs):
    w, b, dw, db = gradient_descent(w, b, X_train, Y_train, learning_rate)
    loss = compute_loss(w, b, X_train, Y_train)

    if epoch % 5 == 0:
        print(f"  第 {epoch:2d} 轮 | w={w:.4f} | b={b:.4f} | 损失={loss:.4f}")

    loss_history.append(loss)
    w_history.append(w)
    b_history.append(b)

final_loss = compute_loss(w, b, X_train, Y_train)
print(f"\n  训练完成！最终损失: {final_loss:.4f}")


# ============================================================
# 第七部分：把规律转换回原始单位
# ============================================================
print("\n\n7. 从归一化转换回原始单位")
print("-" * 30)

# 在归一化空间里：y_pred_norm = w * x_norm + b
# 反归一化：y_pred = y_pred_norm * price_std + price_mean
#         x_norm = (x - area_mean) / area_std
# 代入：y_pred = (w * (x - area_mean) / area_std + b) * price_std + price_mean
#              = (w * price_std / area_std) * x + (b * price_std + price_mean - w * price_std * area_mean / area_std)

w_real = w * price_std / area_std
b_real = b * price_std + price_mean - w * price_std * area_mean / area_std

print(f"  原始单位下的规律：")
print(f"  价格 = {w_real:.2f} * 面积 + {b_real:.2f}")
print(f"  （真实规律: 价格 = 0.50 * 面积 + 15.00）")


# ============================================================
# 第八部分：预测
# ============================================================
print("\n\n8. 预测房价")
print("-" * 30)

test_areas = [50, 80, 100, 120, 150]
print("  面积 -> 预测价格")
for a in test_areas:
    # 先归一化，再预测，再反归一化
    a_norm = (a - area_mean) / area_std
    pred_norm = w * a_norm + b
    pred = pred_norm * price_std + price_mean
    real_price = 0.5 * a + 15
    print(f"  {a:3d}平米 -> {pred:.1f}万元（真实值: {real_price:.0f}万元）")


# ============================================================
# 第九部分：可视化
# ============================================================
print("\n\n9. 生成训练图表...")
print("-" * 30)

fig, axes = plt.subplots(1, 3, figsize=(15, 4))

# 图1：损失下降
axes[0].plot(range(epochs), loss_history, color="#FF6B6B", linewidth=2)
axes[0].set_title("损失下降曲线", fontsize=13)
axes[0].set_xlabel("训练轮数")
axes[0].set_ylabel("损失（MSE）")
axes[0].grid(True, alpha=0.3)

# 图2：w 和 b 变化
axes[1].plot(range(epochs), w_history, label="w（权重）", color="#4ECDC4")
axes[1].plot(range(epochs), b_history, label="b（偏置）", color="#45B7D1")
axes[1].set_title("参数变化曲线", fontsize=13)
axes[1].set_xlabel("训练轮数")
axes[1].legend()
axes[1].grid(True, alpha=0.3)

# 图3：拟合效果
axes[2].scatter(area, price, alpha=0.6, color="#FF6B6B", label="真实数据")
x_line = np.linspace(20, 160, 100)
y_line = w_real * x_line + b_real
axes[2].plot(x_line, y_line, color="#4ECDC4", linewidth=2, label="拟合直线")
axes[2].set_title("线性回归结果", fontsize=13)
axes[2].set_xlabel("面积（平米）")
axes[2].set_ylabel("价格（万元）")
axes[2].legend()
axes[2].grid(True, alpha=0.3)

plt.suptitle("线性回归训练过程", fontsize=15, fontweight="bold")
plt.tight_layout()
plt.savefig("linear_regression_result.png", dpi=120, bbox_inches="tight")
plt.close()
print("  图表已保存: linear_regression_result.png")


# ============================================================
# 总结
# ============================================================
print("\n" + "=" * 50)
print("第9课总结")
print("=" * 50)
print(f"""
  你刚刚亲手实现了一个机器学习模型！

  核心三步：
  1. 前向传播：y = wx + b（预测）
  2. 计算损失：MSE（衡量好坏）
  3. 梯度下降：更新 w 和 b（学习）

  你学到的规律：价格 = {w_real:.2f} * 面积 + {b_real:.2f}
  真实规律：价格 = 0.50 * 面积 + 15

  模型已经很接近了！
""")
