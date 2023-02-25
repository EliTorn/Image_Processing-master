import javax.swing.*;
import java.awt.*;

public class ErrorWindow extends JFrame {

    public static final int WINDOW_WIDTH = 450;
    public static final int WINDOW_HEIGHT = 250;
    private static final int X = 10;
    private static final int Y = 80;
    private static final int width = 400;
    private static final int componentHeight = 30;
    private static final int milliSecondToSleep = 1000;

    public ErrorWindow(String str) {
        Thread thread = new Thread(()->{
            try {
                Thread.sleep(milliSecondToSleep * 2L);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

            this.setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
            this.setLocationRelativeTo(null);
            this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
            this.setResizable(true);
            this.setLayout(null);

            JLabel label = new JLabel(str);
            label.setBounds(X, Y, width, componentHeight);
            label.setFont(new Font("Arial", Font.BOLD, componentHeight-10));
            this.add(label);

            this.setVisible(true);
            nextFunc();
        });
        thread.start();
    }

    public void nextFunc(){
        try {
            Thread.sleep(milliSecondToSleep * 5L);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        this.dispose();
        PanelWindow panelWindow = new PanelWindow();
    }
}
