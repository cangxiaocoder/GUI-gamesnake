import sun.security.util.Length;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Random;

public class App {

    public static BufferedImage img (String path){

        File directory = new File("");//设定为当前文件夹

        try {
            return ImageIO.read(App.class.getResource(path));
            //return ImageIO.read(new File(directory.getCanonicalPath()+path));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new BufferedImage(1,1,1);
    }

    //蛇的初始化方法
    public static void init (){
        Data.lenght = 3;
        Data.snakeX[0] = 100;Data.snakeY[0] = 100;//头位/
        Data.snakeX[1] = 75;Data.snakeY[1] = 100;//第一个身体位置
        Data.snakeX[2] = 50;Data.snakeY[2] = 100;//第二个头位置
        Data.fx="R";        //初始方向
        Data.isStart = false;
        Data.score = 0;

    }

    //初始化食物
    public static void foodInit (){
        Random random = new Random();
        Data.foodX = 25+25*random.nextInt(34);
        Data.foodY = 75+25*random.nextInt(24);
    }
}
