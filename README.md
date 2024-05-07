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
java日志2024.5.6 byfjy

1、在GridNumber里面完成了move四个方向的逻辑

2、在GamePanel里面完成了四个方向的移动逻辑和有效移动判断， 以及正确修改移动步数

warning：但是目前仍然存在一些数组越界的bug
******
## 2048开发日志
******
版本v1.0.0 2024.5.6 bywzh

游戏基本逻辑已经完成，无bug出现。

目前直观问题：

1. 动画合成：需要表明哪两个方块完成了合成

2. 生成方块动画：需要生成方块的动画效果，而不是直接凭空出现（很容易让人反应不过来）

3. 数字颜色待改变（最好方块的背景色能一起变，尝试找图片素材并建立对应联系）
******
版本v1.1.0 2024.5.6 bywzh

游戏基本逻辑已经完成，无bug出现。

完成了加载存档与读档方法，可以保存游戏进度。（只能保存一个目前，可以进一步添加）

******
版本v1.2.0 2024.5.7 bywzh

游戏基本逻辑已经完成，无bug出现。

接下来需要改变main中的运行逻辑，先调出menu界面，再进入游戏界面。

完成了menu界面，可以选择开始游戏，查看存档，退出游戏、游戏设置四个button。

开始游戏：弹出子dialog，可以选择游戏size4x4、5x5、6x6，然后进入游戏界面。

剩下三个还没写

可以在游戏界面在添加一个暂停功能（在研究JDialog的时候发现模态对话框暂停母窗口功能）

学习小问题：

1.GameFrame42行：gamePanel.requestFocusInWindow();//启用键盘事件监听？？？？

2.Menu的titleLabel为什么说已被分配，从未被访问，显示灰色

3.对比Menu、GameFrame、ChooseSize为什么有的JButton显示灰色








