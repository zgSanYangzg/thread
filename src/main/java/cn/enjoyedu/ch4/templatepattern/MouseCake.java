package cn.enjoyedu.ch4.templatepattern;

/**
 * @author Mark老师   享学课堂 https://enjoy.ke.qq.com
 * 往期课程咨询芊芊老师  QQ：2130753077 VIP课程咨询 依娜老师  QQ：2133576719
 * 类说明：
 */
public class MouseCake extends AbstractCake {

    @Override
    protected void shape() {
        System.out.println("慕斯蛋糕造型");
    }

    @Override
    protected void apply() {
        System.out.println("慕斯蛋糕涂抹");
    }

    @Override
    protected void brake() {
        System.out.println("慕斯蛋糕烘焙");
    }
}
