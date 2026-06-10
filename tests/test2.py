score = int(input("请输入你的成绩："))
if score>100 or score<0:
    print("成绩输入错误！")
elif score>= 90:
    print("成绩优秀！")
elif 80<=score<90:
    print("成绩良好！")
elif 70<=score<80:
    print("成绩中等！")
elif 60<=score<70:
    print("成绩及格！")
else:
    print("成绩不及格！")