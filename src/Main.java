
import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        System.out.println("Введите выражение. Например 6+2; X-III");
        Scanner sc = new Scanner(System.in);
        String data = sc.nextLine();
        String[] actions = {"/", "+", "*", "-"};
        String[] actions2 = {"/", "\\+", "\\*", "-"};
        int index = -1;
        int i = 0;
        while (index < 0 && i < 4) {
            index = data.indexOf(actions[i]);
            i++;
        }

        if (index == -1) {
            System.out.println("Неверное выражение");
            return;
        }
        String razd = actions2[i - 1];
        String[] subStr;
        subStr = data.split(razd); // Разделения строки str с помощью метода split()

        int index2=-1;
        i=0;
        while (index2 < 0 && i < 4) {
            index2 = subStr[1].indexOf(actions[i]);
            i++;
        }
        if(index2!=-1|subStr.length>2){System.out.print("Формат математической операции не удовлетворяет заданию - два операнда и один оператор (+, -, /, *)");
            return;}

        char action;
        action = data.charAt(index);

        int A, B;

        boolean h[] = new boolean[subStr[0].length()];
        boolean h1[] = new boolean[subStr[1].length()];
        //проверка строк на числа
        for (int p = 0; p < subStr[0].length(); p++) {
            h[p] = Character.isDigit(subStr[0].charAt(p));
        }
        for (int p = 0; p < subStr[1].length(); p++) {
            h1[p] = Character.isDigit(subStr[1].charAt(p));
        }
        int R = 0;
        try{
            if (checkArray(h)&&checkArray(h1)){
                A = Integer.parseInt(subStr[0]);
                B = Integer.parseInt(subStr[1]);
            }else {
                A = Roman.valueOf(subStr[0]).toInt();
                B = Roman.valueOf(subStr[1]).toInt();
                R=1;
            }}
        catch (IllegalArgumentException e){
            System.out.println("Неверный формат данных");
            return;
        }

        if (A<1|A>10|B<1|B>10){ System.out.println("Неверный формат данных(значения вышли из диапазона)");
            return;
        };

        int answer = 0;
        switch (action) {
            case ('/'):
                answer = A / B;
                break;
            case ('*'):
                answer = A * B;
                break;
            case ('+'):
                answer = A + B;
                break;
            case ('-'):
                answer = A - B;
                break;
        }
        Roman answer2 = null;
        if(R==1){answer2=getByValue(answer);System.out.println(answer2);}
        else System.out.println(answer);
    }

    public static boolean checkArray(boolean[] checked) {
        for (boolean b : checked) {
            if (!b) {
                return false;
            }
        }
        return true;
    }

    public enum Roman {
        I(1),II(2),III(3),IV(4), V(5),VI(6),VII(7),VIII(8),IX(9), X(10),
        XI(11),XII(12),XIII(13),XIV(14),XV(15),XVI(16),XVII(17),XVIII(18),XIX(19),XX(20);
        private final int value;

        private Roman(int value) {
            this.value = value;
        }

        public int toInt() {
            return value;
        }

        public int getValue() {return value;
        }
    }
    public static Roman getByValue(int value) {
        for (Roman rn : Roman.values()) {
            if (rn.getValue() == value)
                return rn;
        }
        throw new IllegalArgumentException("Нет римского числа со значением" + value);
    }
}
