package ap.exercises.finalExam.modeel;


import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class productTools {
    public List<Products> objects;

    public productTools(List<Products> objects) {
        this.objects = objects;
    }

    public static void main(String[] args) {
        List<Products> objects = new ArrayList<>();

        objects.add(new Book("book kind - 01", "big java", "unknown", 400));
        objects.add(new Book("book kind - 01", "big java", "unknown", 400));
        objects.add(new Pen("pen kind - 01", 500, Pen.Color.BLUE));
        objects.add(new Pen("pen kind - 02", 500, Pen.Color.RED));
        objects.add(new Pen("pen kind - 03", 500, Pen.Color.GREEN));
        objects.add(new Pen("pen kind - 03", 500, Pen.Color.GREEN));

        print(method1(objects));

        System.out.println("-----");

        print(method2(objects));

        System.out.println("-----");

        List<Pen> pens = objects.stream()
                .filter(obj -> obj instanceof Pen)
                .map(obj -> (Pen) obj)
                .collect(Collectors.toList());
        System.out.println(method3(pens));

    }

    public static List<Products> method1(List<Products> objects) {
        return objects.stream().distinct().sorted(Comparator.comparing(Products::getPrice))
                .collect(Collectors.toList());
    }

    public static List<Products> method2(List<Products> objects) {
        List<Products> result = new ArrayList<>();

        objects.stream()
                .filter(obj -> obj instanceof Pen)
                .max(Comparator.comparing(Products::getPrice))
                .ifPresent(result::add);

        objects.stream()
                .filter(obj -> obj instanceof Book)
                .max(Comparator.comparing(Products::getPrice))
                .ifPresent(result::add);

        return result;
    }

    public static Map<Pen.Color, Long> method3(List<Pen> pens) {
        return pens.stream()
                .collect(Collectors.groupingBy(
                        Pen::getColor,
                        Collectors.counting()
                ));
    }

    public static void print(List<Products> objects) {
        for (Object object : objects) {
            System.out.println(object.toString());
        }
    }

}
