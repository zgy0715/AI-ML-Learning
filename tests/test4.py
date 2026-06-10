import random
print("猜数字游戏开始！(1-100)")
number = random.randint(1, 100)
count = 0
while True:
    try:
      guess = int(input("请输入你的猜测: "))
    except ValueError:
        print("请输入一个有效的整数！")
        continue
    if guess < 1 or guess > 100:
        print("请输入1-100之间的数字！")
        continue
    count += 1
    if guess < number:
        print("小了！")
    elif guess > number:
        print("大了！")
    else:
        print(f"恭喜你，猜对了！你总共猜了 {count} 次。")
        break