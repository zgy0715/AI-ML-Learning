def is_even(num):
    return num % 2 == 0
for i in range(1,20):
    if is_even(i):
        print(f"{i} 是偶数")
    else:
        print(f"{i} 是奇数")