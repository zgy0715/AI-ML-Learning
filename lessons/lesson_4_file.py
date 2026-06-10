# ========== 文件操作 ==========

# --- 1. 写文件（'w' = write）---
# open(文件名, 模式) 返回一个文件对象

print("===== 写入文件 =====")

# 'w' 模式：写入（会覆盖原有内容）
file = open("notes.txt", "w", encoding="utf-8")
file.write("今天是2026年6月10日\n")  # \n 是换行符
file.write("我正在学习Python！\n")
file.write("第4课：文件操作\n")
file.close()  # 一定要关闭！

print("文件已写入！")

# --- 2. 读文件（'r' = read）---
print("\n===== 读取文件 =====")
file = open("notes.txt", "r", encoding="utf-8")
content = file.read()  # 读取全部内容
print(content)
file.close()

# --- 3. 逐行读取（大文件时需要）---
print("===== 逐行读取 =====")
file = open("notes.txt", "r", encoding="utf-8")
for line in file:      # 直接遍历文件对象，一行一行读
    print(f"行内容: {line.strip()}")  # strip() 去掉换行符
file.close()

# --- 4. 追加写入（'a' = append）---
print("\n===== 追加写入 =====")
file = open("notes.txt", "a", encoding="utf-8")
file.write("我学会了文件操作！\n")
file.close()

# 验证追加成功
file = open("notes.txt", "r", encoding="utf-8")
print(file.read())
file.close()

# --- 5. with 语句（推荐！自动关闭文件）---
print("===== with 语句 =====")
# 用 with 就不用手动 close() 了
with open("notes.txt", "a", encoding="utf-8") as f:
    f.write("用with写文件更方便！\n")

# 验证
with open("notes.txt", "r", encoding="utf-8") as f:
    print(f.read())
