# ========== 模块导入 ==========

# --- 1. 导入整个模块 ---
import math  # 数学模块

print("===== math 模块 =====")
print("圆周率 pi:", math.pi)
print("sin(90度):", math.sin(math.pi / 2))
print("平方根 √16:", math.sqrt(16))
print("向上取整 ceil(3.2):", math.ceil(3.2))
print("向下取整 floor(3.8):", math.floor(3.8))


# --- 2. 导入特定函数 ---
from random import randint, choice

print("\n===== random 模块 =====")
print("随机数 1-10:", randint(1, 10))

fruits = ["苹果", "香蕉", "橘子", "西瓜"]
print("随机选一个水果:", choice(fruits))


# --- 3. 给模块起别名 ---
import datetime as dt

print("\n===== datetime 模块 =====")
now = dt.datetime.now()
print("现在时间:", now)
print("当前年份:", now.year)
print("当前月份:", now.month)
print("当前日期:", now.day)


# --- 4. 实战：用模块做一个功能 ---
print("\n===== 实战：随机密码生成器 =====")

import string  # 字符串常量模块

def generate_password(length=8):
    """生成随机密码"""
    # string.ascii_letters = 所有大小写字母
    # string.digits = 所有数字 0-9
    chars = string.ascii_letters + string.digits
    password = ""
    for _ in range(length):
        password += choice(chars)
    return password

# 生成5个随机密码
for i in range(5):
    print(f"密码{i+1}: {generate_password(10)}")


# --- 5. 自己写的文件也是模块！---
# 我们可以 import 自己写的函数

# 假设我在 lesson_3_function.py 里写了 get_grade 函数
# 导入方式：
# from lesson_3_function import get_grade
# print(get_grade(85))  # 输出 "良好"

# 但是要注意：导入时会把整个文件执行一遍
# 所以通常用 if __name__ == "__main__" 来保护


print("\n===== 总结：三种导入方式 =====")
print("1. import 模块名          → 用 math.pi")
print("2. from 模块名 import 函数  → 直接 pi")
print("3. import 模块名 as 别名    → 用别名 np")
