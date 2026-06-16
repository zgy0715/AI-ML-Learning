# ========== 字典 dict ==========

# --- 1. 创建字典 ---
student = {
    "name": "张三",      # 键(key): 值(value)
    "age": 18,
    "score": 92.5,
    "is_graduated": False
}

print("学生信息:", student)
print("类型:", type(student))

# --- 2. 访问字典（通过键）---
print("\n===== 访问 =====")
print("姓名:", student["name"])       # 方法1：方括号
print("年龄:", student.get("age"))     # 方法2：.get()（推荐，不会报错）

# .get() 的妙用：键不存在时返回默认值
print("身高:", student.get("height", "无此信息"))  # 安全访问

# 如果用方括号访问不存在的键 → 报错！
# print(student["height"])  # 这句会报错！

# --- 3. 修改和添加 ---
student["score"] = 95        # 修改已有键的值
student["city"] = "北京"     # 添加新的键值对

print("\n修改后:", student)

# --- 4. 删除 ---
del student["is_graduated"]   # 删除指定键
print("删除后:", student)

# --- 5. 遍历字典 ---
print("\n===== 遍历 =====")

# 遍历所有键值对
for key, value in student.items():
    print(f"{key}: {value}")

# 遍历所有键
print("\n所有键:", list(student.keys()))

# 遍历所有值
print("所有值:", list(student.values()))

# --- 6. 字典的实际应用 ---
print("\n===== 实用案例 =====")

# 学生成绩表
scores = {
    "张三": 92,
    "李四": 78,
    "王五": 88,
    "赵六": 65
}

# 查询成绩
name = input("\n请输入学生姓名查询成绩: ")
score = scores.get(name)
if score:
    print(f"{name}的成绩是: {score}分")
else:
    print(f"没有找到{name}的信息")

# 统计平均分
total = 0
for s in scores.values():
    total += s
avg = total / len(scores)
print(f"\n全班平均分: {avg:.1f}")
