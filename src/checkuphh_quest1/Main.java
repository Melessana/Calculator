package checkuphh_quest1;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Main {

    private static int[][] panelArray = new int[4][3];
    public static void main(String[] args) {
        setPanelArray();
        change(readArray());
    }
    //       Создание панели ввода кода
    public static void setPanelArray(){
        int k=1;
        for (int i=0; i<3; i++){
            for (int j=0; j<3; j++){
                panelArray[i][j]= k;
                k++;
            }
            System.out.println();
        }
        panelArray[3][0]=-1;
        panelArray[3][2]=-1;
    }

    //Считывание вводимого кода и преобразование строки в массив цифр
    private static int[] readArray(){
        Scanner input = new Scanner(System.in);
//        System.out.println("Введите предполагаемый код: ");
        String inputCode = input.next();
        int[] inputArray = Arrays.stream(inputCode.split("")).mapToInt(Integer::parseInt).toArray();
        if (inputArray.length > 8) {
            System.out.println("Вводимый код не может быть больше 8ми знаков");
            System.exit(0);
        }
        return inputArray;
    }
//    Замена цифр кода на возмыжные варианты
    private static void change(int arr[]){
        int[] codeArray = Arrays.copyOf(arr, arr.length);
//        System.out.println("Варианты для данного кода:");
        var(codeArray, "");
        System.out.print("\b");
    }

    public static void var(int codeArray[], String str){
        if (codeArray.length == 1){
            ArrayList numberArray = numberOption(codeArray, 0);
            int[] numbArray = numberArray.stream().mapToInt(i -> (int) i).toArray();
            for (int k = 0; k < numbArray.length; k++) {
                System.out.print( str + numbArray[k] + ",");
            }
        }
        else {
            ArrayList numberArray = numberOption(codeArray, 0);
            int[] numbArray = numberArray.stream().mapToInt(i -> (int) i).toArray();
            for(int m=0; m<numbArray.length;m++) {
                int t = numbArray[m];
                String str_t = str + t;
                int[] ret = new int[codeArray.length - 1];
                for (int i=0; i < ret.length; i++) {
                    ret[i] = codeArray[i+1];
                }
                var(ret, str_t);
            }
        }
    }
//Метод поиска вариантов значений выбранной цифры, принимает массив(код) и номер позиции цифры в коде
    private static ArrayList numberOption(int arr[], int p){
        ArrayList numberArray = new ArrayList();
        int [] codeArray = Arrays.copyOf(arr, arr.length);
        int x = search(arr[p])[0];
        int y = search(arr[p])[1];

//            Поиск вариантов цифры по вертикали
        for(int m=-1; m<2; m++){
            int y1 = y + m;
            if ((y1>-1) && (y1<3) && (panelArray[x][y1] > -1)){
                numberArray.add(panelArray[x][y1]);
                codeArray[p] = panelArray[x][y1];
            }
        }
//            Поиск вариантов цифры по горизонтали, минуя центр - начальное значение
        for (int k=-1; k<2; k+=2) {
            int x1 = x + k;
            if ((x1 > -1) && (x1 < 4) && (panelArray[x1][y] > -1)) {
                numberArray.add(panelArray[x1][y]);
                codeArray[p] = panelArray[x1][y];
            }
        }
        return numberArray;
    }
    //Метод поиска позиции символа на панели
    private static int[] search(int c){
        int[] coord = new int[2];
        for (int i=0; i<4; i++){
            for (int j=0; j<3; j++){
                if (c==panelArray[i][j]) {
                    coord[0] = i;
                    coord[1] = j;
                    return coord;
                }
            }
        }
        return coord;
    }
}
