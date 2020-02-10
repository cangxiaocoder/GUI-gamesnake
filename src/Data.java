

public class Data {


    public static String headerUrl = "/static/img/header.jpg";
    public static String upUrl = "/static/img/head-u.jpg";
    public static String downUrl = "/static/img/head-d.jpg";
    public static String leftUrl = "/static/img/head-l.jpg";
    public static String rightUrl = "/static/img/head-r.jpg";
    public static String foodUrl = "/static/img/food.jpg";
    public static String bodyUrl = "/static/img/body.jpg";

    //蛇的长度
    public static Integer lenght;
    public static Integer[] snakeX = new Integer[600];
    public static Integer[] snakeY = new Integer[500];
    //初始方向
    public static String fx;
    //初始状态，未开始
    public static Boolean isStart = false;
    //是否死亡，是否在游戏中
    public static Boolean isGame = true;
    //食物
    public static Integer foodX;
    public static Integer foodY;
    //游戏积分
    public static Integer score = 0;

}
