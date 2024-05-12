import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.WeakHashMap;

class Ellipse {
    final static float PI = 3.1415f;
    String name = "None";
    int x, y, longRadius, shortRadius;
    double area;

    public Ellipse() {
        area = x = y = longRadius = shortRadius = 0;
    }

    public Ellipse(int x, int y) {
        this();
        this.x = x;
        this.y = y;
    }

    public Ellipse(String n, int xx, int yy, int longRadius, int shortRadius) {
        this(xx, yy);
        this.longRadius = longRadius;
        this.shortRadius = shortRadius;
        setArea();
        name = n;
    }

    private void setArea() {
        area = PI * longRadius * shortRadius;
    }

    public double getArea() {
        return this.area;
    }

    public String toString(boolean isMax, boolean isMin) {
        return String.format((isMax ? "L " : "") + (isMin ? "S " : "") + "%s-[X:%d, Y:%d] 반지름[%d] 짧은반지름[%d] 면적[%.2f]", name, x, y, longRadius, shortRadius, area);
    }

    public static float AvgArea(Circle[] cir) {
        float sum = 0;
        for (Circle c : cir) sum += c.area;
        return sum / cir.length;
    }
}

class Circle {
    final static float PI = 3.1415f;
    String name = "None";
    int x, y, radius;
    double area;

    public Circle() {
        area = x = y = radius = 0;
    }

    public Circle(int x, int y) {
        this();
        this.x = x;
        this.y = y;
    }

    public Circle(String n, int xx, int yy, int r) {
        this(xx, yy);
        radius = r;
        setArea();
        name = n;
    }

    private void setArea() {
        area = PI * radius * radius;
    }

    public double getArea() {
        return this.area;
    }

    public String toString(boolean isMax, boolean isMin) {
        return String.format((isMax ? "L " : "") + (isMin ? "S " : "") + "%s-[X:%d, Y:%d] 반지름[%d], 면적[%.2f]", name, x, y, radius, area);
    }

    public static float AvgArea(Circle[] cir) {
        float sum = 0;
        for (Circle c : cir) sum += c.area;
        return sum / cir.length;
    }
}

class Triangle {
    String name = "None";
    int x, y, width, height;
    double area;

    public Triangle() {
        area = x = y = width = height = 0;
    }

    public Triangle(int x, int y) {
        this();
        this.x = x;
        this.y = y;
    }

    public Triangle(String n, int xx, int yy, int w, int h) {
        this(xx, yy);
        height = h;
        width = w;
        setArea();
        name = n;
    }

    private void setArea() {
        area = (double) (width * height) / 2;
    }

    double getArea() {
        return this.area;
    }

    public String toString(boolean isMax, boolean isMin) {
        return String.format((isMax ? "L " : "") + (isMin ? "S " : "") + "%s-[X:%d, Y:%d] 밑변[%d] 높이[%d] 면적[%.2f]", name, x, y, width, height, area);
    }
}

class Trapezoid {
    String name = "None";
    int x, y, upSide, downSide, height;
    double area;

    public Trapezoid() {
        area = x = y = upSide = downSide = height = 0;
    }

    public Trapezoid(int x, int y) {
        this();
        this.x = x;
        this.y = y;
    }

    public Trapezoid(String n, int xx, int yy, int up, int down, int h) {
        this(xx, yy);
        height = h;
        upSide = up;
        downSide = down;
        setArea();
        name = n;
    }

    private void setArea() {
        area = (double) ((upSide + downSide) * height) / 2;
    }

    double getArea() {
        return this.area;
    }

    public String toString(boolean isMax, boolean isMin) {
        return String.format((isMax ? "L " : "") + (isMin ? "S " : "") + "%s-[X:%d, Y:%d] 가로[%d] 짧은가로[%d] 높이[%d] 면적[%.2f]", name, x, y, upSide, downSide, height, area);
    }
}

class Rectangle {
    String name = "None";
    int x, y, width, height;
    double area;

    public Rectangle() {
        area = x = y = width = height = 0;
    }

    public Rectangle(int x, int y) {
        this();
        this.x = x;
        this.y = y;
    }

    public Rectangle(String n, int xx, int yy, int w, int h) {
        this(xx, yy);
        height = h;
        width = w;
        setArea();
        name = n;
    }

    private void setArea() {
        area = width * height;
    }

    double getArea() {
        return this.area;
    }

    public String toString(boolean isMax, boolean isMin) {
        return String.format((isMax ? "L " : "") + (isMin ? "S " : "") + "%s-[X:%d, Y:%d] 가로[%d] 세로[%d] 면적[%.2f]", name, x, y, width, height, area);
    }
}

public class HW1 {

    static boolean isMaxSet = false;
    static boolean isMinSet = false;

    static double maxArea = 0;
    static double totalArea = 0;
    static double minArea = 0;


    public static void setMaxMin(double area) {
        if (!isMaxSet) {
            maxArea = area;
            isMaxSet = true;
        }
        if (!isMinSet) {
            minArea = area;
            isMinSet = true;
        }
        if (maxArea < area) {
            maxArea = area;
        }
        if (minArea > area) {
            minArea = area;
        }
    }

    public static void main(String[] args) throws FileNotFoundException {

        File file = new File(args[0]);

        double area = 0;

        String figureType = "";

        int count = 0;

        if (!file.exists()) {
            System.out.printf("%s 파일이 없습니다.", args[0]);
            return;
        }
        Scanner scanner = new Scanner(file);

        // 최대최소조회
        while (scanner.hasNextLine()) {
            Scanner line = new Scanner(scanner.nextLine());
            String command = args[1];
            switch (command) {
                case "print_info":
                    figureType = line.next();
                    switch (figureType) {
                        case "원":
                            Circle circle = new Circle("원", line.nextInt(), line.nextInt(), line.nextInt());
                            area = circle.getArea();
                            totalArea += area;
                            setMaxMin(area);
                            count+=1;
                            break;
                        case "타원":
                            Ellipse ellipse = new Ellipse("타원", line.nextInt(), line.nextInt(), line.nextInt(), line.nextInt());
                            area = ellipse.getArea();
                            totalArea += area;
                            setMaxMin(area);
                            count+=1;
                            break;
                        case "사각형":
                            Rectangle rectangle = new Rectangle("사각형", line.nextInt(), line.nextInt(), line.nextInt(), line.nextInt());
                            area = rectangle.getArea();
                            totalArea += area;
                            setMaxMin(area);
                            count+=1;
                            break;
                        case "삼각형":
                            Triangle triangle = new Triangle("삼각형", line.nextInt(), line.nextInt(), line.nextInt(), line.nextInt());
                            area = triangle.getArea();
                            totalArea += area;
                            setMaxMin(area);
                            count+=1;
                            break;
                        case "사다리꼴":
                            Trapezoid trapezoid = new Trapezoid("사다리꼴", line.nextInt(), line.nextInt(), line.nextInt(), line.nextInt(), line.nextInt());
                            area = trapezoid.getArea();
                            totalArea += area;
                            setMaxMin(area);
                            count+=1;
                            break;
                        default:
                            System.out.println("잘못된 형식의 도형이 입력되었습니다. 파일을 확인해주십시오.");
                            break;
                    }
                case "print":
                    figureType = line.next();
                    String keyFirst = args[1];
                    String keySecond = args[2];
                    String keyThird = args[3];
                    String keyFourth = args[4];
            }
        }
        Scanner scanner1 = new Scanner(file);

        // 최대최소 확인 후 출력문
        while (scanner1.hasNextLine()) {
            Scanner line = new Scanner(scanner1.nextLine());
            String command = args[1];
            switch (command) {
                case "print_info":
                    String figureType = line.next();
                    switch (figureType) {
                        case "원":
                            Circle circle = new Circle("원", line.nextInt(), line.nextInt(), line.nextInt());
                            System.out.println(circle.toString(circle.getArea() == maxArea, circle.getArea() == minArea));
                            break;
                        case "타원":
                            Ellipse ellipse = new Ellipse("타원", line.nextInt(), line.nextInt(), line.nextInt(), line.nextInt());
                            System.out.println(ellipse.toString(ellipse.getArea() == maxArea, ellipse.getArea() == minArea));

                            break;
                        case "사각형":
                            Rectangle rectangle = new Rectangle("사각형", line.nextInt(), line.nextInt(), line.nextInt(), line.nextInt());
                            System.out.println(rectangle.toString(rectangle.getArea() == maxArea, rectangle.getArea() == minArea));

                            break;
                        case "삼각형":
                            Triangle triangle = new Triangle("삼각형", line.nextInt(), line.nextInt(), line.nextInt(), line.nextInt());
                            System.out.println(triangle.toString(triangle.getArea() == maxArea, triangle.getArea() == minArea));
                            break;
                        case "사다리꼴":
                            Trapezoid trapezoid = new Trapezoid("사다리꼴", line.nextInt(), line.nextInt(), line.nextInt(), line.nextInt(), line.nextInt());
                            System.out.println(trapezoid.toString(trapezoid.getArea() == maxArea, trapezoid.getArea() == minArea));

                            break;
                        default:
                            System.out.println("잘못된 형식의 도형이 입력되었습니다. 파일을 확인해주십시오.");
                            break;
                    }

            }

        }

        System.out.println();
        System.out.printf("평균 면적 : %.2f", totalArea/count);
    }

}