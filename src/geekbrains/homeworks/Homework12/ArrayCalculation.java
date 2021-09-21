package geekbrains.homeworks.Homework12;

import java.util.Arrays;

public class ArrayCalculation {

    static final int SIZE = 10000000;
    static final int HALF = SIZE / 2;
    float[] arr = new float[SIZE];

    public void count() {
        Arrays.fill(arr, 1);

        long start = System.currentTimeMillis(); //засекается время выполнения

        float[] arr1 = new float[HALF];
        float[] arr2 = new float[HALF];

        System.arraycopy(arr, 0, arr1, 0, HALF); //деление одного массива на два
        System.arraycopy(arr, HALF, arr2, 0, HALF);    //деление одного массива на два

        Thread t1 = new Thread(() -> { // создание потока, для подсчета операции над половиной массива
            count(arr1);
        });
        Thread t2 = new Thread(() -> { // создание потока, для подсчета операции над половиной массива
            count(arr2);
        });

        t1.start(); // запуск потока
        t2.start(); // запуск другого потока

        try {       // ожидание завершения потоков т1 и т2
            t1.join();
            t2.join();
        } catch (Exception e) {
            e.printStackTrace();
        }

        System.arraycopy(arr1, 0, arr, 0, HALF); // склейка из 2-х массивов в 1 массив
        System.arraycopy(arr2, 0, arr, HALF, HALF);     // склейка из 2-х массивов в 1 массив

        System.out.println(System.currentTimeMillis() - start); // вывод рабочего времени в консоль
    }

    private void count(float[] arr) { // вычисление значений массива
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (float) (arr[i] * Math.sin(0.2f + i / 5) * Math.cos(0.2f + i / 5) * Math.cos(0.4f + i / 2));
        }
    }

}
