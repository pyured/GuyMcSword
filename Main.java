import javax.swing.*;
import java.util.*;
public class Main {
    public static void main(String[] args) {
        JFrame window = new JFrame();
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setResizable(false);
        window.setTitle("Guy McSword's Revenge");
        window.setIconImage(new ImageIcon("src\\UIAssets\\logop2.png").getImage());
        
        GamePanel gamePanel = new GamePanel();
        window.add(gamePanel);

        window.pack();

        window.setLocationRelativeTo(null);
        window.setVisible(true);

        gamePanel.setUpGame();
        gamePanel.startGameThread();


        //requirements
        System.out.println(RecursiveStuff.fibonacci(12));
        RecursiveStuff.printOneByOne("APCSA");
        int[] ints = new int[]{1, 10, 100, 1000};
        RecursiveStuff.printArray(ints, 0);
        ArrayList<Integer> intsList = new ArrayList<Integer>();
        intsList.add(1);
        intsList.add(10);
        intsList.add(100);
        intsList.add(1000);
        RecursiveStuff.printArray(intsList, 0);
        ints = new int[]{2, 4, 1, 6, 9, 3};
        intsList.clear();
        intsList = new ArrayList<Integer>(Arrays.asList(2, 4, 1, 6, 9, 3));
        RecursiveStuff.mergeSort(ints);
        RecursiveStuff.mergeSort(intsList);
        System.out.println(RecursiveStuff.recursiveBinarySearch(ints, 0, ints.length, 2));
    }
}
