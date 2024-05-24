
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

class Figure {
    String name;
    int x, y;

    public String getName() {
        return name;
    }

    public int getY() {
        return y;
    }

    public int getX() {
        return x;
    }
}


class Triangle extends Figure {

    int width, height;

    double area;

    public Triangle(int x, int y, int width, int height) {
        super.x = x;
        super.y = y;
        super.name = "삼각형";
        this.width = width;
        this.height = height;
        setArea();
    }

    public Triangle() {

    }

    private void setArea() {
        area = (double) (width * height) / 2;
    }

    public double getArea() {
        return this.area;
    }

    public String toString(boolean isMax, boolean isMin) {
        return String.format((isMax ? "L " : "") + (isMin ? "S " : "") + "%s-[X:%d, Y:%d] 밑변[%d] 높이[%d] 면적[%.2f]", getName(), getX(), getY(), width, height, area);
    }

}

class Rectangle extends Triangle {

    int width, height;

    double area;

    public Rectangle(int x, int y, int width, int height) {
        super();
        super.x = x;
        super.y = y;
        super.name = "사각형";
        this.width = width;
        this.height = height;
        setArea();
    }

    public Rectangle() {
        super();
    }

    private void setArea() {
        area = (double) (width * height);
    }

    public double getArea() {
        return this.area;
    }

    public String toString(boolean isMax, boolean isMin) {
        return String.format((isMax ? "L " : "") + (isMin ? "S " : "") + "%s-[X:%d, Y:%d] 가로[%d] 세로[%d] 면적[%.2f]", getName(), getX(), getY(), this.width, this.height, area);
    }

}

class Trapezoid extends Rectangle {

    int upWidth, downWidth, height;

    double area;

    public Trapezoid(int x, int y, int upWidth, int downWidth, int height) {
        super();
        super.x = x;
        super.y = y;
        super.name = "사다리꼴";
        this.upWidth = upWidth;
        this.downWidth = downWidth;
        this.height = height;
        setArea();
    }

    private void setArea() {
        area = (double) (upWidth + downWidth) * height / 2;
    }

    public double getArea() {
        return this.area;
    }

    public String toString(boolean isMax, boolean isMin) {
        return String.format((isMax ? "L " : "") + (isMin ? "S " : "") + "%s-[X:%d, Y:%d] 가로[%d] 짧은가로[%d] 면적[%.2f]", getName(), getX(), getY(), this.upWidth, this.downWidth, area);
    }

}

class Circle extends Figure {
    final static float PI = 3.1415f;
    int radius;
    double area;

    public Circle(int xx, int yy, int r) {
        radius = r;
        setArea();
        super.x = xx;
        super.y = yy;
        super.name = "원";
    }

    public Circle(int xx, int yy, double area) {
        this.area = area;
        super.x = xx;
        super.y = yy;
        super.name = "타원";

    }

    private void setArea() {
        area = PI * radius * radius;
    }

    public double getArea() {
        return this.area;
    }

    public String toString(boolean isMax, boolean isMin) {
        return String.format((isMax ? "L " : "") + (isMin ? "S " : "") + "%s-[X:%d, Y:%d] 반지름[%d] 면적[%.2f]", getName(), getX(), getY(), radius, area);
    }

    public static float AvgArea(Circle[] cir) {
        float sum = 0;
        for (Circle c : cir) sum += c.area;
        return sum / cir.length;
    }
}

class Oval extends Circle {

    double area;

    int longRadius, shortRadius;

    public Oval(int xx, int yy, int longRadius, int shortRadius) {
        super(xx, yy, PI * longRadius * shortRadius);
        this.area = PI * longRadius * shortRadius;
        this.longRadius = longRadius;
        this.shortRadius = shortRadius;
    }

    public String toString(boolean isMax, boolean isMin) {
        return String.format((isMax ? "L " : "") + (isMin ? "S " : "") + "%s-[X:%d, Y:%d] 반지름[%d] 짧은반지름[%d] 면적[%.2f]", getName(), getX(), getY(), this.longRadius, this.shortRadius, area);
    }

    public double getArea() {
        return area;
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

        int argLength = args.length;
        String[] searchingKey = new String[argLength - 2];

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
            if (command.equals("print_info")) {
                figureType = line.next();
                switch (figureType) {
                    case "원":
                        Circle circle = new Circle(line.nextInt(), line.nextInt(), line.nextInt());
                        area = circle.getArea();
                        totalArea += area;
                        setMaxMin(area);
                        count += 1;
                        break;
                    case "타원":
                        Oval oval = new Oval(line.nextInt(), line.nextInt(), line.nextInt(), line.nextInt());
                        area = oval.getArea();
                        totalArea += area;
                        setMaxMin(area);
                        count += 1;
                        break;
                    case "사각형":
                        Rectangle rectangle = new Rectangle(line.nextInt(), line.nextInt(), line.nextInt(), line.nextInt());
                        area = rectangle.getArea();
                        totalArea += area;
                        setMaxMin(area);
                        count += 1;
                        break;
                    case "삼각형":
                        Triangle triangle = new Triangle( line.nextInt(), line.nextInt(), line.nextInt(), line.nextInt());
                        area = triangle.getArea();
                        totalArea += area;
                        setMaxMin(area);
                        count += 1;
                        break;
                    case "사다리꼴":
                        Trapezoid trapezoid = new Trapezoid(line.nextInt(), line.nextInt(), line.nextInt(), line.nextInt(), line.nextInt());
                        area = trapezoid.getArea();
                        totalArea += area;
                        setMaxMin(area);
                        count += 1;
                        break;
                    default:
                        System.out.println("잘못된 형식의 도형이 입력되었습니다. 파일을 확인해주십시오.");
                        break;
                }

////                case "print":
////                    figureType = line.next();
////                    String keyFirst = args[1];
////                    String keySecond = args[2];
////                    String keyThird = args[3];
////                    String keyFourth = args[4];
            } else if (command.equals("print")) {
                for (int i = 2; i < argLength; i++) {
                    if (!(args[i].equals("원") || args[i].equals("타원") || args[i].equals("삼각형") || args[i].equals("사각형") || args[i].equals("사다리꼴"))) {
                        System.out.println("도형 종류 오류");
                        return;
                    }
                    searchingKey[i - 2] = args[i];
                }
                figureType = line.next();
                for (int i = 0; i < searchingKey.length; i++) {
                    if (figureType.equals(searchingKey[i])) {
                        switch (figureType) {
                            case "원":
                                Circle circle = new Circle(line.nextInt(), line.nextInt(), line.nextInt());
                                area = circle.getArea();
                                totalArea += area;
                                setMaxMin(area);
                                count += 1;
                                break;
                            case "삼각형":
                                Triangle triangle = new Triangle( line.nextInt(), line.nextInt(), line.nextInt(), line.nextInt());
                                area = triangle.getArea();
                                totalArea += area;
                                setMaxMin(area);
                                count += 1;
                                break;
                            case "사각형":
                                Rectangle rectangle = new Rectangle(line.nextInt(), line.nextInt(), line.nextInt(), line.nextInt());
                                area = rectangle.getArea();
                                totalArea += area;
                                setMaxMin(area);
                                count += 1;
                                break;
                            case "사다리꼴":
                                Trapezoid trapezoid = new Trapezoid(line.nextInt(), line.nextInt(), line.nextInt(), line.nextInt(), line.nextInt());
                                area = trapezoid.getArea();
                                totalArea += area;
                                setMaxMin(area);
                                count += 1;
                                break;
                            case "타원":
                                Oval oval = new Oval(line.nextInt(), line.nextInt(), line.nextInt(), line.nextInt());
                                area = oval.getArea();
                                totalArea += area;
                                setMaxMin(area);
                                count += 1;
                                break;

                        }
                    }
                }
            } else {
                System.out.println("없는 명령어");
                return;
            }

        }
        Scanner scanner1 = new Scanner(file);

        // 최대최소 확인 후 출력문
        while (scanner1.hasNextLine()) {
            Scanner line = new Scanner(scanner1.nextLine());
            String command = args[1];
            if (command.equals("print_info")) {
                figureType = line.next();
                switch (figureType) {
                    case "원":
                        Circle circle = new Circle(line.nextInt(), line.nextInt(), line.nextInt());
                        System.out.println(circle.toString(circle.getArea() == maxArea, circle.getArea() == minArea));
                        break;
                    case "타원":
                        Oval oval = new Oval(line.nextInt(), line.nextInt(), line.nextInt(), line.nextInt());
                        System.out.println(oval.toString(oval.getArea() == maxArea, oval.getArea() == minArea));

                        break;
                    case "사각형":
                        Rectangle rectangle = new Rectangle( line.nextInt(), line.nextInt(), line.nextInt(), line.nextInt());
                        System.out.println(rectangle.toString(rectangle.getArea() == maxArea, rectangle.getArea() == minArea));
                        break;
                    case "삼각형":
                        Triangle triangle = new Triangle( line.nextInt(), line.nextInt(), line.nextInt(), line.nextInt());
                        System.out.println(triangle.toString(triangle.getArea() == maxArea, triangle.getArea() == minArea));
                        break;
                    case "사다리꼴":
                        Trapezoid trapezoid = new Trapezoid( line.nextInt(), line.nextInt(), line.nextInt(), line.nextInt(), line.nextInt());
                        System.out.println(trapezoid.toString(trapezoid.getArea() == maxArea, trapezoid.getArea() == minArea));
                        break;
                    default:
                        System.out.println("잘못된 형식의 도형이 입력되었습니다. 파일을 확인해주십시오.");
                        break;
                }
            } else if (command.equals("print")) {
                figureType = line.next();
                for (int i = 0; i < searchingKey.length; i++) {
                    if (searchingKey[i].equals(figureType)) {
                        switch (figureType) {
                            case "원":
                                Circle circle = new Circle(line.nextInt(), line.nextInt(), line.nextInt());
                                System.out.println(circle.toString(circle.getArea() == maxArea, circle.getArea() == minArea));
                                break;
                            case "타원":
                                Oval ellipse = new Oval( line.nextInt(), line.nextInt(), line.nextInt(), line.nextInt());
                                System.out.println(ellipse.toString(ellipse.getArea() == maxArea, ellipse.getArea() == minArea));
                                break;
                            case "사각형":
                                Rectangle rectangle = new Rectangle( line.nextInt(), line.nextInt(), line.nextInt(), line.nextInt());
                                System.out.println(rectangle.toString(rectangle.getArea() == maxArea, rectangle.getArea() == minArea));
                                break;
                            case "삼각형":
                                Triangle triangle = new Triangle( line.nextInt(), line.nextInt(), line.nextInt(), line.nextInt());
                                System.out.println(triangle.toString(triangle.getArea() == maxArea, triangle.getArea() == minArea));
                                break;
                            case "사다리꼴":
                                Trapezoid trapezoid = new Trapezoid( line.nextInt(), line.nextInt(), line.nextInt(), line.nextInt(), line.nextInt());
                                System.out.println(trapezoid.toString(trapezoid.getArea() == maxArea, trapezoid.getArea() == minArea));
                                break;
                            default:
                                System.out.println("잘못된 형식의 도형이 입력되었습니다. 파일을 확인해주십시오.");
                                break;
                        }
                    }

                }
            }

        }

        System.out.println();
        System.out.printf("평균 면적 : %.2f", totalArea / count);
    }

}