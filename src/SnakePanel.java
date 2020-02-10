import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/*
* 6游戏面板
* */
public class SnakePanel extends JPanel implements KeyListener, ActionListener {

    private Integer delay = 500;
    private Integer overScore = 0;
    //定时器
    Timer timer = new Timer(delay,this);//每一百毫秒执行一次

    public SnakePanel() {
        super();
        this.setBackground(Color.white);
        //初始化蛇的位置
        App.init();
        //初始化食物的位置
        App.foodInit();
        //获得焦点和键盘事件
        this.setFocusable(true);
        this.addKeyListener(this);
        timer.start();//一开始就启动

    }



    @Override
    public void paint(Graphics g) {
        super.paint(g);
        g.fillRect(25,75,850,600);
        //顶部广告栏
        g.drawImage(App.img(Data.headerUrl),25,0,850,55,null);

        g.setColor(Color.GREEN);
        g.setFont(new Font("宋体",Font.ITALIC,26));
        g.drawString("长度: "+Data.lenght,700,22);
        g.drawString("积分: "+Data.score,700,45);
        //食物
        g.drawImage(App.img(Data.foodUrl),Data.foodX,Data.foodY,25,25,null);

        switch (Data.fx){
            case "R":g.drawImage(App.img(Data.rightUrl),Data.snakeX[0],Data.snakeY[0],25,25,null);//头部
                break;
            case "D":g.drawImage(App.img(Data.downUrl),Data.snakeX[0],Data.snakeY[0],25,25,null);//头部
                break;
            case "U":g.drawImage(App.img(Data.upUrl),Data.snakeX[0],Data.snakeY[0],25,25,null);//头部
                break;
            case "L":g.drawImage(App.img(Data.leftUrl),Data.snakeX[0],Data.snakeY[0],25,25,null);//头部
                break;
            default:

        }

        g.drawImage(App.img(Data.rightUrl),Data.snakeX[0],Data.snakeY[0],25,25,null);//头部
        for (int i = 1; i < Data.lenght; i++) {
            g.drawImage(App.img(Data.bodyUrl),Data.snakeX[i],Data.snakeY[i],25,25,null);//第一个身体，
        }
        if (!Data.isGame){

            g.setColor(Color.RED);
            g.setFont(new Font("楷体",Font.BOLD,40));
            g.drawString("GAME OVER 得分"+overScore,270,200);
            repaint();
        }

        if (!Data.isStart){
            g.setColor(Color.white);
            g.setFont(new Font("楷体",Font.BOLD,40));
            g.drawString("单击空格开始游戏",260,300);
            repaint();
        }
    }
    //判断方向，左转右，右转左，上转下，下转上，死亡，结束游戏
    private void isFail() {
            Data.isGame = false;
            //Data.isStart = false;
            overScore = Data.score;
            App.init();
            repaint();
    }
    //键盘监听事件，按下空格开始暂停
    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode()==KeyEvent.VK_SPACE){
            Data.isStart = !Data.isStart;
            Data.isGame = true; //在游戏中没有失败
            repaint();
        }
        if (Data.isStart){
            //上下左右，控制方向
            switch (e.getKeyCode()){
                case KeyEvent.VK_RIGHT:
                case KeyEvent.VK_D:
                    //if (isFail("L")) break;
                    Data.fx = "R";
                    break;
                case KeyEvent.VK_DOWN:
                case KeyEvent.VK_S:
                    //if (isFail("U")) break;
                    Data.fx = "D";
                    break;
                case KeyEvent.VK_LEFT:
                case KeyEvent.VK_A:
                    //if (isFail("R")) break;
                    Data.fx = "L";
                    break;
                case KeyEvent.VK_UP:
                case KeyEvent.VK_W:
                   // if (isFail("D")) break;
                    Data.fx = "U";
                    break;
                default:

            }
        }

    }


    @Override
    public void actionPerformed(ActionEvent e) {
        //监听游戏开始
        if (Data.isStart){
            //吃食物
            if (Data.snakeX[0].equals(Data.foodX) && Data.snakeY[0].equals(Data.foodY)){
                //食长大，
                Data.lenght++;
                Data.score +=10 ;
                if (Data.score<=1000){
                    delay = 1050-Data.score;
                }
                System.out.println(delay);
                //再次随机食物
                App.foodInit();
            }

            for (int i = Data.lenght-1; i >0; i--) {
                Data.snakeX[i]=Data.snakeX[i-1];
                Data.snakeY[i]=Data.snakeY[i-1];
            }
            //方向向右
            if ("R".equals(Data.fx)){
                Data.snakeX[0]+=25;//头部向前移动25；
                if (Data.snakeX[0]>=875){
                    //游戏失败
                    isFail();
                    //Data.snakeX[0]=25;
                }
            }
            //方向向左
            if ("L".equals(Data.fx)){
                Data.snakeX[0]-=25;//头部向前移动25；
                if (Data.snakeX[0]<=0){
                    //游戏失败
                    isFail();
                    //Data.snakeX[0]=850;
                }
            }
            //方向向上
            if ("U".equals(Data.fx)){
                Data.snakeY[0]-=25;//头部向前移动25；
                if (Data.snakeY[0]<=50){
                    //游戏失败
                    isFail();
                    //Data.snakeY[0]=650;
                }
            }
            //方向向下
            if ("D".equals(Data.fx)){
                Data.snakeY[0]+=25;//头部向前移动25；
                if (Data.snakeY[0]>=675){
                    //游戏失败
                    isFail();
                    //Data.snakeY[0]=75;
                }
            }
            //撞到自己失败
            for (int i = 1; i < Data.lenght; i++) {
                if (Data.snakeX[0].equals(Data.snakeX[i]) && Data.snakeY[0].equals(Data.snakeY[i])){
                    //游戏失败
                    isFail();
                }
            }

            //Data.snakeX[0]+=0;
            repaint();
        }
        timer.start();
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }

    @Override
    public void keyTyped(KeyEvent e) {

    }


}
