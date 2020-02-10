import javax.swing.*;
import java.awt.*;

/*
* 游戏窗体
* */
public class SnakeFrame extends JFrame {

    public SnakeFrame()  {
        this.setBounds(400,200,900,725);
        this.setVisible(true);
        this.setResizable(false);//设置窗口大喜不可变
        this.setDefaultCloseOperation(SnakeFrame.EXIT_ON_CLOSE);
    }
}
