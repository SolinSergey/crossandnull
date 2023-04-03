import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class MainWindowGame extends JFrame {
    public static char[][] map;
    public static final int SIZE = 3;
    public static final char DOT_EMPTY = '•';
    public static final char DOT_X = 'X';
    public static final char DOT_O = 'O';
    public static JButton[][] buttons=new JButton[3][3];
    public MainWindowGame(){
        clearMap();
        setTitle("Крестики-нолики");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setBounds(300, 300, 400, 400);
        setResizable(false);
        setLayout(new GridLayout(SIZE, SIZE));
        Font fontKey = new Font("Arial", Font.BOLD, 72);
        //Создаем массив кнопок и рисуем их в окне
        for (int i = 0; i < SIZE; i++) {
            for (int j=0;j<SIZE;j++){
                buttons[i][j]=new JButton(String.valueOf(DOT_EMPTY));
                buttons[i][j].setFont(fontKey);
                add(buttons[i][j]);
            }
        }
        //Добавляем обработчики событий
        buttons[0][0].addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                buttons[0][0].setText(String.valueOf(DOT_X));
                buttons[0][0].setEnabled(false);
                stepHuman(0,0);
            }
        });
        buttons[0][1].addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                buttons[0][1].setText(String.valueOf(DOT_X));
                buttons[0][1].setEnabled(false);
                stepHuman(0,1);
            }
        });
        buttons[0][2].addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                buttons[0][2].setText(String.valueOf(DOT_X));
                buttons[0][2].setEnabled(false);
                stepHuman(0,2);
            }
        });
        buttons[1][0].addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                buttons[1][0].setText(String.valueOf(DOT_X));
                buttons[1][0].setEnabled(false);
                stepHuman(1,0);
            }
        });
        buttons[1][1].addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                buttons[1][1].setText(String.valueOf(DOT_X));
                buttons[1][1].setEnabled(false);
                stepHuman(1,1);
            }
        });
        buttons[1][2].addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                buttons[1][2].setText(String.valueOf(DOT_X));
                buttons[1][2].setEnabled(false);
                stepHuman(1,2);
            }
        });
        buttons[2][0].addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                buttons[2][0].setText(String.valueOf(DOT_X));
                buttons[2][0].setEnabled(false);
                stepHuman(2,0);
            }
        });
        buttons[2][1].addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                buttons[2][1].setText(String.valueOf(DOT_X));
                buttons[2][1].setEnabled(false);
                stepHuman(2,1);
            }
        });
        buttons[2][2].addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                buttons[2][2].setText(String.valueOf(DOT_X));
                buttons[2][2].setEnabled(false);
                stepHuman(2,2);
            }
        });
        setVisible(true);
   }

    public static void clearMap() {
        map = new char[3][3];
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                map[i][j] = DOT_EMPTY;
            }
        }
    }

    public static void stepHuman(int x, int y){
        boolean fillStatus=false;
        boolean status=false;
        map[x][y]=DOT_X;
        status=checkWin();
        boolean winner=true;
        if (status){
            modalWin(status,winner);
        }
        fillStatus=checkFillMap();
        if (!fillStatus){
            winner=false;
            stepAI();
            status=checkWin();
            if (status){
                modalWin(status,winner);
            }

        }
        else{
            modalFill();
        }
    }

    public static void modalFill(){
        JDialog dialogWin=new JDialog();
        dialogWin.setTitle("INFO!!!");
        dialogWin.setBounds(400,300,400,200);
        dialogWin.setModal(true);
        dialogWin.setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
        JButton modalButton=new JButton("Ничья!!! Нажмите для продолжения!!!");
        modalButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                System.exit(0);
            }

        });
        dialogWin.add(modalButton);
        dialogWin.setVisible(true);
    }

    public static void modalWin(boolean status, boolean winner){
        if (status && winner){
            JDialog dialogWin=new JDialog();
            dialogWin.setTitle("INFO!!!");
            dialogWin.setBounds(400,300,400,200);
            dialogWin.setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
            dialogWin.setModal(true);
            JButton modalButton=new JButton("Вы выиграли!!! Нажмите для продолжения!!!");
            modalButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent actionEvent) {
                    System.exit(0);
                }
            });
            dialogWin.add(modalButton);
            dialogWin.setVisible(true);
        }
        else{
            if (status & (!winner)){
                JDialog dialogWin=new JDialog();
                dialogWin.setTitle("INFO!!!");
                dialogWin.setBounds(400,300,400,200);
                dialogWin.setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
                dialogWin.setModal(true);
                JButton modalButton=new JButton("Выиграл компьютер!!! Нажмите для продолжения!!!");
                modalButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent actionEvent) {
                        System.exit(0);
                    }
                });
                dialogWin.add(modalButton);
                dialogWin.setVisible(true);
            }
        }

    }

    public static boolean checkWin() {
        boolean flag=false;
        int summ_left_diagonal = 0;
        int summ_right_diagonal = 0;
        int summ_horizontal;
        int summ_vertical;
        int[][] temp_array = new int [SIZE][SIZE];
        for (int i=0;i<SIZE;i++){
            for (int j=0;j<SIZE;j++){
                if (map[i][j]==DOT_X){
                    temp_array[i][j]=1;
                }
                else{
                    if (map[i][j]==DOT_O){
                        temp_array[i][j]=-1;
                    }
                    else{
                        temp_array[i][j]=0;
                    }
                }
            }
        }
        for (int i=0;i<SIZE;i++){
            summ_horizontal=0;
            summ_vertical=0;
            for (int j=0;j<SIZE;j++){
                summ_horizontal+=temp_array[i][j];
                summ_vertical+=temp_array[j][i];
                if (i==j){
                    summ_left_diagonal+=temp_array[i][j];
                }
            }
            summ_right_diagonal+=temp_array[i][SIZE-i-1];
            if (summ_horizontal==3 || summ_horizontal==-3 || summ_vertical==3 || summ_vertical==-3 || summ_left_diagonal==3 || summ_left_diagonal==-3
                    || summ_right_diagonal==3 || summ_right_diagonal==-3){
                flag=true;
                return flag;
            }
        }
        return flag;
    }

    public static boolean checkFillMap (){
        boolean statusfill = true;
        for (int i=0;i<SIZE;i++){
            for (int j=0;j<SIZE;j++){
                if (map[i][j]==DOT_EMPTY){
                    statusfill=false;
                    return statusfill;
                }
            }
        }
        return statusfill;
    }
    public static void stepAI(){
        int[] summ_x = {0,0,0};
        int[] summ_y = {0,0,0};
        int summ_left_diagonal = 0;
        int summ_right_diagonal = 0;
        int[][] temp_array = new int [SIZE][SIZE];
        for (int i=0;i<SIZE;i++){
            for (int j=0;j<SIZE;j++){
                if (map[i][j]==DOT_X){
                    temp_array[i][j]=1;
                }
                else{
                    if (map[i][j]==DOT_O){
                        temp_array[i][j]=-1;
                    }
                    else{
                        temp_array[i][j]=0;
                    }
                }
            }
        }
        for (int i=0;i<SIZE;i++){
            for(int j=0;j<SIZE;j++){
                summ_x[i]+=temp_array[i][j];
                summ_y[i]+=temp_array[j][i];
                if (i==j){
                    summ_left_diagonal+=temp_array[i][j];
                }
            }
            summ_right_diagonal+=temp_array[i][SIZE-i-1];
        }

        int max_x_summ=max_summ(summ_x);
        int max_y_summ=max_summ(summ_y);
        int max_x_pos=max_pos(summ_x);
        int max_y_pos=max_pos(summ_y);

        if (max_x_summ==2){
            for (int i=0;i<SIZE;i++){
                if (map[max_x_pos][i]==DOT_EMPTY){
                    map[max_x_pos][i]=DOT_O;
                    buttons[max_x_pos][i].setText(String.valueOf(DOT_O));
                    buttons[max_x_pos][i].setEnabled(false);
                    return;
                }
            }
        }
        if (max_y_summ==2){
            for (int i=0;i<SIZE;i++){
                if (map[i][max_y_pos]==DOT_EMPTY){
                    map[i][max_y_pos]=DOT_O;
                    buttons[i][max_y_pos].setText(String.valueOf(DOT_O));
                    buttons[i][max_y_pos].setEnabled(false);
                    return;
                }
            }
        }
        if (summ_left_diagonal==2){
            for (int i=0;i<SIZE;i++){
                for (int j=0;j<SIZE;j++){
                    if (i==j && map[i][j]==DOT_EMPTY){
                        map[i][j]=DOT_O;
                        buttons[i][j].setText(String.valueOf(DOT_O));
                        buttons[i][j].setEnabled(false);
                        return;
                    }
                }
            }
        }
        if (summ_right_diagonal==2){
            for (int i=0;i<SIZE;i++){
                for (int j=SIZE-1;j>=0;j--){
                    if (j==SIZE-1-i && map[i][j]==DOT_EMPTY){
                        map[i][j]=DOT_O;
                        buttons[i][j].setText(String.valueOf(DOT_O));
                        buttons[i][j].setEnabled(false);
                        return;
                    }
                }
            }
        }
        for (int i=0;i<SIZE;i++){
            for (int j=0;j<SIZE;j++)
                if (map[i][j]==DOT_EMPTY && summ_y[j]>0){
                    map[i][j]=DOT_O;
                    buttons[i][j].setText(String.valueOf(DOT_O));
                    buttons[i][j].setEnabled(false);
                    return;
                }
        }

        for (int j=0;j<SIZE;j++) {
            for (int i = 0; i < SIZE; i++) {
                if (map[i][j]==DOT_EMPTY  && summ_x[i]>0){
                    map[i][j]=DOT_O;
                    buttons[i][j].setText(String.valueOf(DOT_O));
                    buttons[i][j].setEnabled(false);
                    return;
                }
            }
        }

    }
    public static int max_pos(int[] arr){
        int max_summ=-SIZE;
        int pos=-1;
        for (int i=0;i<arr.length;i++){
            if (max_summ<arr[i]){
                max_summ=arr[i];
                pos=i;
            }
        }
        return pos;
    }

    public static int max_summ(int[] arr){
        int max=-SIZE;
        for (int i=0;i< arr.length;i++){
            if (max<arr[i]){
                max=arr[i];
            }
        }
        return max;
    }

   public static void main(String[] args) {
        new MainWindowGame();
    }
}


