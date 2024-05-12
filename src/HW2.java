import javax.sound.midi.Soundbank;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class HW2 {
    public static void main(String[] args) throws FileNotFoundException {

        File file = new File(args[0]);

        int lineCount = 0;

        if (!file.exists()){
            System.out.printf("데이터 파일(%s)을 찾을 수 없음 !!!", args[0]);
            return;
        }

        Scanner in = new Scanner(file);

        // 줄 갯수 확인
        while (in.hasNextLine()) {
            Scanner scn = new Scanner(in.nextLine());
            lineCount++;
        }

        String[][] studentInfo = new String[lineCount][6];

        Scanner in2 = new Scanner(new File(args[0]));

        double sum = 0, korean = 0, english = 0, mathematics = 0;

        for (int i = 0; i < lineCount; i++) {
            Scanner scanner2 = new Scanner(in2.nextLine());
            for (int j = 0; j < 5; j++) {
                studentInfo[i][j] = scanner2.next();
                if (j == 2 || j == 3 || j == 4) {
                    sum += Double.parseDouble(studentInfo[i][j]);
                }
                if (j == 2) {
                    korean += Double.parseDouble(studentInfo[i][2]);
                }
                if (j == 3) {
                    english += Double.parseDouble(studentInfo[i][3]);
                }
                if (j == 4) {
                    mathematics += Double.parseDouble(studentInfo[i][4]);
                }
                if (j == 4) {
                    studentInfo[i][5] = String.valueOf(sum / 3);
                }
            }
            sum = 0;
        }
        korean /= lineCount;
        english /= lineCount;
        mathematics /= lineCount;

        double maxAverage = 0;
        double minAverage = 0;
        double temp = 0;
        int maxCount = 0, minCount = 0;

        System.out.println("학번 \t이름 국어 영어 수학 평균");

        for (int i = 0; i < lineCount; i++) {
            for (int j = 0; j < 5; j++) {
                System.out.print(String.format("%s ", studentInfo[i][j]));
            }
            System.out.printf("%.2f", Double.parseDouble(studentInfo[i][5]));
            System.out.println();
        }
        maxAverage = Double.parseDouble(studentInfo[0][5]);
        minAverage = Double.parseDouble(studentInfo[0][5]);
        System.out.println(String.format("과목 평균   %.2f %.2f %.2f", korean, english, mathematics));
        for (int i = 0; i < lineCount; i++) {
            if (maxAverage <= Double.parseDouble(studentInfo[i][5])) {
                maxAverage = Double.parseDouble(studentInfo[i][5]);
            }
            if (minAverage >= Double.parseDouble(studentInfo[i][5])) {
                minAverage = Double.parseDouble(studentInfo[i][5]);
            }
        }
        for (int i = 0; i < lineCount; i++) {
            if (maxAverage == Double.parseDouble(studentInfo[i][5])) {
                maxCount++;
            }
            if (minAverage == Double.parseDouble(studentInfo[i][5])) {
                minCount++;
            }
        }

        String[] maxAverageArray = new String[maxCount];
        String[] minAverageArray = new String[minCount];

        int tempVar =0 ;

        for (int i = 0; i < lineCount; i++){
            if (maxAverage == Double.parseDouble(studentInfo[i][5])) {
                maxAverageArray[tempVar] = String.format("%s(%s)", studentInfo[i][0],studentInfo[i][1]);
                tempVar++;
            }
        }
        System.out.print(String.format("최고 평균 : "));
        for (int i = 0; i < maxCount; i++){
            if ( i == maxCount-1){
                System.out.printf("%s", maxAverageArray[i]);

            }
            else{
                System.out.printf("%s, ", maxAverageArray[i]);

            }
        }

        tempVar = 0;
        for (int i = 0; i < lineCount; i++){
            if (minAverage == Double.parseDouble(studentInfo[i][5])) {
                minAverageArray[tempVar] = String.format("%s(%s)", studentInfo[i][0],studentInfo[i][1]);
                tempVar++;
            }
        }
        System.out.print(String.format("\n최저 평균 : "));

        for (int i = 0; i < minCount; i++){
            if ( i == minCount-1){
                System.out.printf("%s", minAverageArray[i]);

            }
            else{
                System.out.printf("%s, ", minAverageArray[i]);

            }
        }
    }
}