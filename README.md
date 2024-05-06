# project
2048小游戏
******
java日志2024.5.4 byfjy
上传所有文件，并搭建github的库，加入collabortor，实现双方idea互传
******
java日志2024.5.5 byfjy
完成界面初始化initialNumbers方法，随机一个位置生成一个2，另一个位置生成4的方法
实现了moveright的底层逻辑，以及判断是否为有效移动的checkValidMove方法
但是目前还有bug，会导致数组越界
******
java日志2024.5.5 bywzh
搭建了四个方向的移动框架，具体需要在gridnumber中实现，
添加了每一步生成随机位置一个2或4的方法addRandomNumber
******
java日志2024.5.6 bywzh

调整了moveright中的大部分代码逻辑：

1.使得现在moveright应该没有bug了

2.更改了判断移动的逻辑把check方法移到gamepanel里面从而可以进行步数的联动

3.更改addRandomNumber方法逻辑在check之后才能添加数字

4.一些小细节的逻辑优化

******
java日志2024.5.7 byFJY

1、在GridNumber里面完成了move四个方向的逻辑

2、在GamePanel里面完成了四个方向的移动逻辑和有效移动判断， 以及正确修改移动步数

warning：但是目前仍然存在一些数组越界的bug
