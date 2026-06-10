# ========== 列表 list ==========

# --- 1. 创建列表 ---
fruits = ["苹果", "香蕉", "橘子", "西瓜"]
numbers = [1, 2, 3, 4, 5]
mixed = ["hello", 42, True, 3.14]  # 列表可以装不同类型

print("水果列表:", fruits)
print("数字列表:", numbers)

# --- 2. 访问列表元素（索引从0开始！）---
#     索引:  0     1     2     3
# fruits = ["苹果", "香蕉", "橘子", "西瓜"]

print("\n第一个水果:", fruits[0])      # "苹果"
print("第二个水果:", fruits[1])      # "香蕉"
print("最后一个:", fruits[-1])       # "西瓜"（-1表示倒数第一个）
print("倒数第二个:", fruits[-2])     # "橘子"

# --- 3. 修改、添加、删除 ---
fruits[1] = "草莓"     # 修改：把香蕉换成草莓
print("\n修改后:", fruits)

fruits.append("葡萄")  # append() 在末尾添加
print("添加后:", fruits)

fruits.insert(1, "蓝莓")  # insert(位置, 元素) 在指定位置插入
print("插入后:", fruits)

fruits.remove("苹果")   # remove(元素) 删除指定元素
print("删除后:", fruits)

popped = fruits.pop()   # pop() 删除并返回最后一个元素
print("弹出的元素:", popped)
print("pop后:", fruits)

# --- 4. 列表常用操作 ---
print("\n===== 列表操作 =====")
print("长度:", len(fruits))         # 列表有几个元素
print("是否包含草莓:", "草莓" in fruits)  # True
print("是否包含菠萝:", "菠萝" in fruits)  # False

# --- 5. 遍历列表（for循环）---
print("\n===== 遍历 =====")
for fruit in fruits:
    print(f"水果: {fruit}")

# --- 6. 列表切片 [start:end] ---
#     [起始:结束] 注意：结束位置不包含！
nums = [0, 1, 2, 3, 4, 5, 6, 7, 8, 9]
print("\n切片演示:")
print("前3个:", nums[:3])       # [0, 1, 2]
print("第3到第6个:", nums[3:7]) # [3, 4, 5, 6]
print("最后3个:", nums[-3:])    # [7, 8, 9]
print("所有偶数索引:", nums[::2]) # [0, 2, 4, 6, 8]
