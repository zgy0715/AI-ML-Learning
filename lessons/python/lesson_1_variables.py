# ========== 变量和数据类型 ==========

# --- 1. 整数 int ---
age = 18
year = 2024
count = -5

print("年龄:", age)
print("年份:", year)

# --- 2. 小数 float ---
pi = 3.14159
price = 9.99
temperature = -2.5

print("圆周率:", pi)
print("价格:", price)

# --- 3. 字符串 str (文本) ---
name = "张三"
greeting = '你好呀！'  # 单引号也可以
sentence = "I'm learning Python"  # 包含单引号就用双引号

print("名字:", name)
print("问候:", greeting)

# --- 4. 布尔值 bool ---
is_student = True
has_homework = False

print("是学生吗?", is_student)
print("有作业吗?", has_homework)

# --- 5. 用 type() 查看数据类型 ---
print("\n===== 查看数据类型 =====")
print(type(age))       # <class 'int'>
print(type(pi))        # <class 'float'>
print(type(name))      # <class 'str'>
print(type(is_student))  # <class 'bool'>
