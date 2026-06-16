# ========== 第2课：条件判断 ==========
# 注意缩进！Python用缩进表示"属于哪个代码块"

# --- 示例1：最简单的 if ---
age = 18

if age >= 18:
    print("你已经成年了！")   # 这行前面有缩进（Tab或4个空格）
    print("可以考驾照了")

print("这句没有缩进，所以不管怎样都会执行\n")

# --- 示例2：if-else ---
score = 75

if score >= 60:
    print(f"成绩{score}分，及格！[通过]")
else:
    print(f"成绩{score}分，不及格！[不通过]")

# --- 示例3：if-elif-else（多个条件）---
# 注意：elif 是 "else if" 的缩写

def grade(score):
    if score >= 90:
        print(f"{score}分 → 优秀 A")
    elif score >= 80:
        print(f"{score}分 → 良好 B")
    elif score >= 70:
        print(f"{score}分 → 中等 C")
    elif score >= 60:
        print(f"{score}分 → 及格 D")
    else:
        print(f"{score}分 → 不及格 F")

grade(95)   # A
grade(82)   # B
grade(55)   # F

# 判断顺序很重要！从最上面的条件开始检查
# 一旦找到一个满足的，就执行对应的代码块，然后跳出

# --- 示例4：逻辑运算符 and / or / not ---
print("\n===== 逻辑运算符 =====")

height = 175
weight = 70

if height > 170 and weight > 60:
    print("身高 > 170 并且 体重 > 60")

is_weekend = True
is_holiday = False
if is_weekend or is_holiday:
    print("可以睡懒觉！")

is_raining = False
if not is_raining:
    print("不下雨，可以出去玩")
