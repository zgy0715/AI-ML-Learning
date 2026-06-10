# ========== input() 用户输入 ==========

# input() 会让程序停下来等用户打字
# 用户输入的内容会以 字符串 形式保存

name = input("请输入你的名字: ")        # 用户输入
age = input("请输入你的年龄: ")          # 输入的内容是字符串

print("\n====== 你的信息 ======")
print("你好,", name, "!")
print("你今年", age, "岁")

# 注意：input() 返回的一律是 字符串
# 如果需要数字，要用 int() 或 float() 转换
age_number = int(age)   # 把字符串"18"转成数字18
print("5年后你", age_number + 5, "岁")

# 简写方式：一步到位
height = float(input("请输入你的身高(cm): "))
print("你的身高是:", height, "cm")
