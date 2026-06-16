# AI & ML Learning

从零基础到独立完成 AI 项目，一步一个脚印。

## 学习路线图

```
Phase 1: Python基础 ────────────────────── 第1-4课
  ├── 第1课：第一个程序、变量、数据类型、输入输出
  ├── 第2课：条件判断、循环、逻辑运算符
  ├── 第3课：列表、字典、元组、集合、函数
  └── 第4课：文件读写、异常处理、模块导入

Phase 2: 数据科学生态 ──────────────────── 第5-7课
  ├── 第5课：NumPy数组运算、广播、索引
  ├── 第6课：Pandas数据清洗、筛选、分组
  └── 第7课：Matplotlib折线图、柱状图、散点图、饼图

Phase 3: 机器学习算法 ──────────────────── 第8-14课
  ├── 第8课：机器学习概念、类型、完整流程
  ├── 第9课：线性回归 —— 梯度下降从零实现
  ├── 第10课：K近邻（KNN）—— 分类算法
  ├── 第11课：模型评估 —— 训练集/测试集、准确率、混淆矩阵
  ├── 第12课：决策树 —— 直观的分类规则
  ├── 第13课：朴素贝叶斯 —— 文本分类
  └── 第14课：逻辑回归 —— 二分类问题

Phase 4: 深度学习基础 ──────────────────── 第15-18课
  ├── 第15课：神经网络本质 —— 感知机、激活函数、多层网络
  ├── 第16课：PyTorch入门 —— 张量、自动求导
  ├── 第17课：卷积神经网络 —— 图像识别入门
  └── 第18课：迁移学习 —— 站在巨人的肩膀上

Phase 5: 项目实战 ──────────────────────── 第19-22课
  ├── 第19课：手写数字识别器（MNIST）
  ├── 第20课：电影评论情感分析
  ├── 第21课：房价预测完整项目（含数据处理+特征工程）
  └── 第22课：自主完成个人AI项目
```

## 项目结构

```
├── lessons/
│   ├── python/           # Python课程（理论MD + 代码PY）
│   │   ├── lesson_1_hello.py
│   │   ├── lesson_5_numpy.py
│   │   ├── lesson_9_linear_regression.py
│   │   └── ...
│   └── java/             # Java课程（理论MD + 代码Java）
│       ├── lesson_1_java_basics.md
│       ├── Lesson1_Basics.java
│       └── ...
├── JAVA/                 # Java期末复习（9章代码 + 练习题）
│   ├── chapter1/         # 基础
│   ├── chapter2/         # 面向对象
│   ├── chapter3/         # API
│   ├── chapter4/         # 集合
│   ├── chapter5/         # IO
│   ├── chapter6/         # 多线程
│   ├── chapter7/         # 网络
│   ├── chapter8/         # 数据库
│   ├── chapter9/         # 反射
│   └── exercises/        # 每章练习题
├── tests/                # 课后作业
└── data/                 # 数据文件
    ├── images/           # 生成的图表
    ├── students.csv
    └── notes.txt
```

## 运行方式

### Python
```bash
cd lessons/python
python lesson_1_hello.py
```

### Java
```bash
cd JAVA
javac -encoding UTF-8 chapter1/DataTypeDemo.java
java chapter1.DataTypeDemo
```

## 进度追踪

| 阶段 | 状态 |
|:----|:----:|
| Phase 1: Python基础 | 已完成 |
| Phase 2: 数据科学生态 | 已完成 |
| Phase 3: 机器学习算法 | 进行中 |
| Phase 4: 深度学习基础 | 未开始 |
| Phase 5: 项目实战 | 未开始 |

## 运行环境

- Python 3.13+（NumPy, Pandas, Matplotlib, scikit-learn, PyTorch）
- Java 8+（编译时加 `-encoding UTF-8`）
