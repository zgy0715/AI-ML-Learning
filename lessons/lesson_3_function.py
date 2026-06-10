# ========== 函数 def ==========

# --- 1. 定义和调用函数 ---
# def 函数名(参数):
#     """函数说明"""
#     代码

def say_hello():
    """打印问候语"""
    print("你好！欢迎来到Python世界！")

# 调用函数
say_hello()
say_hello()  # 可以重复调用


# --- 2. 带参数的函数 ---
def greet(name):
    """向指定的人问好"""
    print(f"{name}同学，你好！")

greet("张三")
greet("李四")
greet("小明")


# --- 3. 带返回值的函数（return）---
def add(a, b):
    """返回两数之和"""
    result = a + b
    return result

# 把返回值存到变量里
sum_result = add(3, 5)
print(f"\n3 + 5 = {sum_result}")

# 可以直接用
print(f"10 + 20 = {add(10, 20)}")


# --- 4. 多个参数 ---
def calculate_bmi(weight, height):
    """计算BMI指数：体重(kg) / 身高(m)的平方"""
    bmi = weight / (height ** 2)
    return bmi

bmi = calculate_bmi(70, 1.75)
print(f"\nBMI指数: {bmi:.1f}")

# 判断BMI
if bmi < 18.5:
    print("偏瘦")
elif bmi < 24:
    print("正常")
elif bmi < 28:
    print("偏胖")
else:
    print("肥胖")


# --- 5. 函数可以调用函数 ---
def get_grade(score):
    """根据分数返回等级"""
    if score >= 90:
        return "优秀"
    elif score >= 80:
        return "良好"
    elif score >= 70:
        return "中等"
    elif score >= 60:
        return "及格"
    else:
        return "不及格"

def print_score_report(scores_dict):
    """打印成绩报告"""
    print("\n===== 成绩报告 =====")
    for name, score in scores_dict.items():
        grade = get_grade(score)  # 调用另一个函数
        print(f"{name}: {score}分 → {grade}")
    print("===================")

# 使用
students = {"张三": 92, "李四": 78, "王五": 55, "赵六": 88}
print_score_report(students)


# --- 6. 参数默认值 ---
def power(base, exp=2):
    """计算base的exp次方，默认平方"""
    return base ** exp

print(f"\n3的平方: {power(3)}")      # 使用默认值exp=2
print(f"3的立方: {power(3, 3)}")     # 指定exp=3
print(f"2的10次方: {power(2, 10)}")
