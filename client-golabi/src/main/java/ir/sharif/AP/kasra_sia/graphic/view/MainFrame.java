package ir.sharif.AP.kasra_sia.graphic.view;



import javax.swing.*;
import ir.sharif.AP.kasra_sia.util.*;

public class MainFrame extends JFrame implements Commons {
    public static MainFrame instance = null;
//    private MainPanel mainPanel = new MainPanel();
    private Loop loop;
    public MainFrame(MainPanel mainPanel) {
        super("Golabi");
        this.setSize(FRAME_WIDTH, FRAME_HEIGHT);
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setContentPane(mainPanel);
        instance = this;
        loop = new Loop(60,MainFrame::refreshFrame);
    }

    public static void refreshFrame(){
//        SwingUtilities.updateComponentTreeUI(instance);
        instance.repaint();
        instance.revalidate();
    }
    @Override
    public void setVisible(boolean b) {
        if (b) loop.start();
        else loop.stop();
        super.setVisible(b);
    }
}




