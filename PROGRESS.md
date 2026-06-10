# 学习进度追踪

> 任何 AI 读取此文件即可了解学员的全部学习进度。
> 最后更新：2026-06-10

---

## 学员信息

- **背景**：大一新生，零编程基础
- **目标**：能独立完成 AI/ML 项目
- **环境**：Windows 11, Python 3.13.3
- **已装库**：NumPy, Pandas, Matplotlib

---

## 阶段进度总览

| 阶段 | 进度 | 课次 |
|:----|:----:|:----:|
| Phase 1: Python基础 | 100% | 第1-4课 |
| Phase 2: 数据科学生态 | 100% | 第5-7课 |
| Phase 3: 机器学习算法 | 进行中 | 第8-14课 |
| Phase 4: 深度学习基础 | 0% | 第15-18课 |
| Phase 5: 项目实战 | 0% | 第19-22课 |

---

## Phase 1: Python基础 ✅ 已完成

### 第1课：第一个程序
- **概念**：print(), 变量, int/float/str/bool 类型, input(), 类型转换
- **文件**：[lesson_1_hello.py](lessons/lesson_1_hello.py), [lesson_1_variables.py](lessons/lesson_1_variables.py), [lesson_1_input.py](lessons/lesson_1_input.py)
- **作业**：[test.py](tests/test.py) — 输入姓名和出生年份，计算年龄
- **测验易错点**：`type(int + float)` = float（不是 str）

### 第2课：条件判断
- **概念**：if/elif/else, 比较运算符, 逻辑运算符 (and/or/not)
- **文件**：[lesson_2_if.py](lessons/lesson_2_if.py)
- **作业**：[test2.py](tests/test2.py) — 成绩等级评定
- **测验易错点**：多个 if 与 if-elif-else 的区别

### 第3课：数据结构与函数
- **概念**：列表(索引/切片/增删), 字典(增删改查), def 函数, 参数/返回值
- **文件**：[lesson_3_list.py](lessons/lesson_3_list.py), [lesson_3_dict.py](lessons/lesson_3_dict.py), [lesson_3_function.py](lessons/lesson_3_function.py)
- **作业**：[test3.py](tests/test3.py) — 判断奇偶（修复了 range(1,21) off-by-one bug）

### 第4课：文件与模块
- **概念**：open() w/r/a, with 自动关闭, try/except, import/from
- **文件**：[lesson_4_file.py](lessons/lesson_4_file.py), [lesson_4_exception.py](lessons/lesson_4_exception.py), [lesson_4_module.py](lessons/lesson_4_module.py)
- **作业**：[test4.py](tests/test4.py) — 猜数字游戏（含异常处理和范围验证）

---

## Phase 2: 数据科学生态 ✅ 已完成

### 第5课：NumPy
- **概念**：np.array, shape/ndim/size, reshape/flatten, 广播, 布尔索引, np.where
- **文件**：[lesson_5_numpy.py](lessons/lesson_5_numpy.py)
- **作业**：[test5.py](tests/test5.py) — 生成随机数组，计算均值/标准差，筛选>60

### 第6课：Pandas
- **概念**：DataFrame, head/tail/info/describe, iloc/loc, groupby, fillna, to_csv/read_csv
- **文件**：[lesson_6_pandas.py](lessons/lesson_6_pandas.py)
- **作业**：[test6.py](tests/test6.py) — 学生成绩表（修复了 pandas CoW inplace 问题）

### 第7课：Matplotlib
- **概念**：plot(折线), bar(柱状), scatter(散点), pie(饼图), subplots, 中文显示配置
- **文件**：[lesson_7_matplotlib.py](lessons/lesson_7_matplotlib.py)
- **作业**：[test7.py](tests/test7.py) — AQI 柱状图（条件颜色：>80红色，其余绿色）
- **测验易错点**：subplots(2,2) = 4个子图

---

## Phase 3: 机器学习算法 🔄 进行中

### 第8课：机器学习概念 ✅ 已完成
- **概念**：传统编程 vs ML, 监督/无监督/强化学习, 特征/标签/模型, ML完整流程
- **文件**：[lesson_8_ml_intro.md](lessons/lesson_8_ml_intro.md)
- **测验**：满分通过

### 第9课：线性回归 ✅ 已完成
- **概念**：y = wx + b, 损失函数(MSE), 梯度下降, 学习率, 特征归一化, 从零实现
- **文件**：[lesson_9_linear_regression.md](lessons/lesson_9_linear_regression.md), [lesson_9_linear_regression.py](lessons/lesson_9_linear_regression.py)
- **学到的规律**：价格 = 0.49 * 面积 + 16.22（真实: 0.50 * 面积 + 15）
- **测验**：需巩固梯度下降概念

### 第10课：K近邻 ✅ 已完成
- **概念**：分类算法, 欧氏距离, K值选择(过拟合vs欠拟合), 懒惰学习, 决策边界
- **文件**：[lesson_10_knn.md](lessons/lesson_10_knn.md), [lesson_10_knn.py](lessons/lesson_10_knn.py)
- **测验**：❌ 概念待巩固——KNN全称、欧氏距离、K值影响
- **代码运行结果**：生成 knn_data.png, knn_k3_decision.png, knn_k5_decision.png, knn_decision_boundary.png

### 第11课：模型评估（待学习）
- **概念准备教**：训练集/测试集划分, 准确率, 精确率/召回率, 混淆矩阵, 交叉验证

### 第12课：决策树（待学习）
### 第13课：朴素贝叶斯（待学习）
### 第14课：逻辑回归（待学习）

---

## 学员常见易错点（新AI请注意）

1. **Windows GBK 编码**：终端不支持 emoji（如✅❌🎯），print 中不能用
2. **pandas CoW**：现代 pandas `inplace=True` 已废弃，需用 `df = df.xxx` 或 `df["col"] = df["col"].xxx`
3. **NumPy 广播**：矩阵 + 向量会自动扩展维度
4. **CLAUDE.md / MEMORY.md**：项目记忆文件存在 `.claude` 目录下
5. **测验风格**：学员可能先口头回答一部分，需要引导他把所有题目答完
