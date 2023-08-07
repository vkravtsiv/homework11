package homework11;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class HomeWork11 {
    static List<String> namesList = Arrays.asList("Vira", "Ivan", "Ann", "Petro", "Mykola", "Vasyl");
    static String[] arr={"1, 2, 0", "4, 5"};
    static Stream<String>firstStream=Stream.of("Vira","Oksana","Olesya");
    static Stream<String> secondStream=Stream.of("Ivan","Petro","Vasyl");


    /*
    Завдання 1.
    Метод приймає на вхід список імен. Необхідно повернути рядок вигляду
    1. Ivan, 3. Peter... лише з тими іменами, що стоять під непарним індексом (1, 3 тощо)
     */
    public static String oddIndexesList(List<String> listNames) {
        return IntStream.range(0, namesList.size()).boxed().filter(i -> i % 2 == 1).map(i -> i + "."
                + namesList.get(i)).collect(Collectors.joining(", "));
    }

    /*
    Завдання 2.
    Метод приймає на вхід список рядків (можна взяти список із Завдання 1).
    Повертає список цих рядків у верхньому регістрі, і відсортованих за спаданням (від Z до A).
    */
    public static List<String> toUpperCaseAndSort(List<String> namesList) {
        return namesList.stream().map(strings -> strings.toUpperCase())
                .sorted(Comparator.reverseOrder()).collect(Collectors.toList());
    }

    /*
    Завдання 3
    Є масив:["1, 2, 0", "4, 5"] Необхідно отримати з масиву всі числа,
    і вивести їх у відсортованому вигляді через кому ,, наприклад: "0, 1, 2, 4, 5"
     */
    public static String takeNumbers(String[]arr) {
        List<String> list=Arrays.asList(arr);
        return list.stream()
                .map(string->Arrays.asList(string.split(" *, *")))
                .flatMap(number->number.stream())
                .mapToInt(str->Integer.valueOf(str))
                .sorted()
                .mapToObj(x->String.valueOf(x))
                .collect(Collectors.joining(", "));
    }
    /*
    Завдання 4.
    Використовуючи Stream.iterate, створіть безкінечний стрім випадкових чисел,
    але не використовуючи Math.random().
    Реалізуйте свій лінійний конгруентний генератор. Для цього почніть з x[0] = seed,
    і далі кожний наступний елемент рахуйте за формулою на зразок x[n + 1] = 1 (a x[n] + c) % m
    для коректних значень a, c, та m.
    Необхідно імплементувати метод, що приймає на вхід параметри a, c, та m,
    і повертає Stream<Long>.
    Для тесту використовуйте такі дані:
    a = 25214903917
    c = 11
    m = 2^48 (2в степені48`)
     */
    static List<Long> lcg(long seed, long a, long c, long m) {

        final List<Long> randomnumbers = Stream.iterate(seed, x -> (a * x + c) % m)
                .limit(100)
                .peek(System.out::println)
                .collect(Collectors.toList());
        return randomnumbers;
    }
    /*
    Напишіть метод public static <T> Stream<T> zip(Stream<T> first, Stream<T> second)
    який "перемішує" елементи зі стрімів first та second, зупиняючись тоді,
    коли у одного зі стрімів закінчаться елементи.
     */
    public static <T> Stream<T> zip(Stream<T> first, Stream<T> second) {
        Iterator<T> i1 = first.iterator();
        Iterator<T> i2 = second.iterator();
        Stream<T> resultStream = Stream.empty();
        while (i1.hasNext() & i2.hasNext()){
            resultStream = Stream.concat(resultStream, Stream.of(i1.next(), i2.next()));
        }
        return resultStream;
    }

    public static void main(String[] args) {
        // Завдання 1
        oddIndexesList(namesList);
        System.out.println(oddIndexesList(namesList));
        System.out.println();

        //Завдання 2
        System.out.println(toUpperCaseAndSort(namesList));
        System.out.println();

        //Завдання 3
        System.out.println(takeNumbers(arr));
//        System.out.print(str+", ");
        System.out.println("\b\b");

        //Завдання 4
        long seed=1024578l;
        long a = 25214903917l;
        long c = 11l;
        long m=(long)Math.pow(2,48);
//        System.out.println("m = " + m);

        lcg(seed,a,c,m);
//        System.out.println(lcg(seed,a,c,m));
        System.out.println();

        //Завдання 5.
        System.out.println("zip(firstStream,firstStream).collect(Collectors.toList()) = "
                + zip(firstStream, secondStream).collect(Collectors.toList()));


    }
}
