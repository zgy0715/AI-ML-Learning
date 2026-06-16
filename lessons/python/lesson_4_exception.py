# ========== 异常处理 ==========

# --- 1. 程序为什么会崩溃？---
print("===== 没有异常处理 =====")

# 用户输入 "abc"，转换成 int 会出错
# num = int(input("请输入一个数字: "))  # 如果输入"abc"，程序崩溃！


# --- 2. try-except 捕获异常 ---
print("===== try-except =====")

try:
    num = int(input("请输入一个数字: "))
    result = 100 / num
    print(f"100 ÷ {num} = {result}")
except ValueError:
    print("错误：输入的不是有效数字！")
except ZeroDivisionError:
    print("错误：不能除以0！")

print("程序继续执行...\n")


# --- 3. 捕获所有异常 + else + finally ---
print("===== 完整的异常处理 =====")

try:
    file = open("不存在的文件.txt", "r", encoding="utf-8")
    content = file.read()
    file.close()
except FileNotFoundError:
    print("错误：文件不存在！")
except Exception as e:
    print(f"其他错误: {e}")
else:
    # 没有异常时执行
    print("文件读取成功！")
finally:
    # 不管有没有异常都会执行
    print("finally：这段代码一定会执行")


# --- 4. 实际应用：安全的用户输入 ---
print("\n===== 实用案例：安全的计算器 =====")

def safe_input_number(prompt):
    """安全地获取用户输入的数字"""
    while True:
        try:
            num = float(input(prompt))
            return num
        except ValueError:
            print("输入无效，请输入数字！")

def calculator():
    """简单的计算器"""
    print("\n--- 简单计算器 ---")
    print("操作: +, -, *, /")

    try:
        a = safe_input_number("请输入第一个数: ")
        b = safe_input_number("请输入第二个数: ")
        op = input("请输入运算符(+, -, *, /): ")

        if op == "+":
            result = a + b
        elif op == "-":
            result = a - b
        elif op == "*":
            result = a * b
        elif op == "/":
            if b == 0:
                raise ZeroDivisionError("除数不能为0！")
            result = a / b
        else:
            raise ValueError(f"不支持的运算符: {op}")

        print(f"{a} {op} {b} = {result}")

    except ZeroDivisionError as e:
        print(f"数学错误: {e}")
    except ValueError as e:
        print(f"输入错误: {e}")
    except Exception as e:
        print(f"未知错误: {e}")
    else:
        print("计算成功完成！")
    finally:
        print("感谢使用计算器～")

calculator()
