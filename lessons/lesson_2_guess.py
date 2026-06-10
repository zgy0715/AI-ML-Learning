# ========== 猜数字互动 ==========

# 设定一个秘密数字
secret = 7

guess = int(input("猜一个1-10之间的数字: "))

if guess == secret:
    print("哇！你猜对了！太厉害了！")
elif guess > secret:
    print("猜大了，再小一点~")
else:
    print("猜小了，再大一点~")

print("游戏结束！")
