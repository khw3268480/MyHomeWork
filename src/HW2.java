
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

class Figure2 {
    String name;
    int x, y;

    double area;

    int width, longWidth;

    int upWidth, downWidth, height;

    int radius;
    int longRadius;

    public Figure2() {

    }


    public String getName() {
        return name;
    }

    public int getY() {
        return y;
    }

    public double getArea() {
        return area;
    }

    public int getWidth() {
        return width;
    }

    public int getLongWidth() {
        return longWidth;
    }

    public int getUpWidth() {
        return upWidth;
    }

    public int getDownWidth() {
        return downWidth;
    }

    public int getHeight() {
        return height;
    }

    public int getRadius() {
        return radius;
    }

    public int getLongRadius() {
        return longRadius;
    }

    public int getX() {
        return x;
    }

    public String toCircleString(boolean isMax, boolean isMin) {
        return String.format((isMax ? "L " : "") + (isMin ? "S " : "") + "%s-[X:%d, Y:%d] 반지름[%d] 면적[%.2f]", getName(), getX(), getY(), getRadius(), getArea());
    }

    public String toRectangleString(boolean isMax, boolean isMin) {
        return String.format((isMax ? "L " : "") + (isMin ? "S " : "") + "%s-[X:%d, Y:%d] 가로[%d] 세로[%d] 면적[%.2f]", getName(), getX(), getY(), getWidth(), getHeight(), getArea());
    }

    public String toTriangleString(boolean isMax, boolean isMin) {
        return String.format((isMax ? "L " : "") + (isMin ? "S " : "") + "%s-[X:%d, Y:%d] 밑변[%d] 높이[%d] 면적[%.2f]", getName(), getX(), getY(), getWidth(), getHeight(), getArea());
    }

    public String toTrapezoidString(boolean isMax, boolean isMin) {
        return String.format((isMax ? "L " : "") + (isMin ? "S " : "") + "%s-[X:%d, Y:%d] 가로[%d] 짧은가로[%d] 높이[%d] 면적[%.2f]", getName(), getX(), getY(), getUpWidth(), getDownWidth(), getHeight(), getArea());
    }

    public String toOvalString(boolean isMax, boolean isMin) {
        return String.format((isMax ? "L " : "") + (isMin ? "S " : "") + "%s-[X:%d, Y:%d] 반지름[%d] 짧은반지름[%d] 면적[%.2f]", getName(), getX(), getY(), getLongRadius(), getRadius(), getArea());
    }

//    public Figure2(int x, int y, int radius){
//        this.radius = radius;
//        this.x = x;
//        this.y = y;
//    }
//    public Figure2(int x, int y, int a, int b){
//        this.x = x;
//        this.y = y;
//        this.width = a;
//        this.height = b;
//        this.longRadius = a;
//        this.radius = b;
//    }
//    public Figure2(int x, int y, int a, int b, int c){
//        this.x = x;
//        this.y = y;
//        this.upWidth = a;
//        this.downWidth = b;
//        this.height = c;
//    }
}


class Triangle2 extends Figure2 {

    int width, height;

    double area;

    public Triangle2(int x, int y, int width, int height) {
        super.x = x;
        super.y = y;
        super.width = width;
        super.height = height;
        super.name = "삼각형";
        this.width = width;
        this.height = height;
        setArea();
    }

    public Triangle2(int x, int y, int width, int height, double area) {
        super.x = x;
        super.y = y;
        super.width = width;
        super.height = (int) area;
        super.name = "사각형";
        this.width = width;
        this.height = height;
        super.area = area;
    }

    public Triangle2(int x, int y, int width, int height, String name) {
        super.x = x;
        super.y = y;
        super.width = width;
        super.height = height;
        super.name = name;
        this.width = width;
        this.height = height;
        super.area = area;
    }


    private void setArea() {
        area = (double) (width * height) / 2;
        super.area = area;

    }

    public double getArea() {
        return this.area;
    }

    public String toString(boolean isMax, boolean isMin) {
        return String.format((isMax ? "L " : "") + (isMin ? "S " : "") + "%s-[X:%d, Y:%d] 밑변[%d] 높이[%d] 면적[%.2f]", getName(), getX(), getY(), width, height, area);
    }

}

class Rectangle2 extends Triangle2 {

    int width, height;

    double area;

    public Rectangle2(int x, int y, int width, int height) {
        super(x, y, width, height, "사각형");
        super.x = x;
        super.y = y;
        super.name = "사각형";
        this.width = width;
        this.height = height;
        setArea();
        super.area =area;
    }

    public Rectangle2(int x, int y, int upWidth, int downWidth, int height) {
        super(x, y, upWidth, downWidth, height);
        super.x = x;
        super.y = y;
        super.name = "사각형";
        this.width = upWidth;
        this.height = downWidth;
        super.height = height;
        setArea();
        super.area =area;
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

class Trapezoid2 extends Rectangle2 {

    int upWidth, downWidth, height;

    double area;

    public Trapezoid2(int x, int y, int upWidth, int downWidth, int height) {
        super(x, y, upWidth, downWidth, height);
        super.x = x;
        super.y = y;
        super.name = "사다리꼴";
        super.upWidth = upWidth;
        super.downWidth = downWidth;
        this.upWidth = upWidth;
        this.downWidth = downWidth;
        this.height = height;
        setArea();
        super.area = this.area;
        super.height = height;
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

class Circle2 extends Figure2 {
    final static float PI = 3.1415f;
    int radius;
    double area;

    public Circle2(int xx, int yy, int r) {
        super.radius = r;
        radius = r;
        setArea();
        super.x = xx;
        super.y = yy;
        super.name = "원";
        super.area = area;
    }

    public Circle2(int xx, int yy, double area, int shortRadius) {
        super();
        this.area = area;
        super.x = xx;
        super.y = yy;
        super.area = area;
        super.name = "타원";
        super.radius = shortRadius;
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

    public static float AvgArea(Circle2[] cir) {
        float sum = 0;
        for (Circle2 c : cir) sum += c.area;
        return sum / cir.length;
    }
}

class Oval2 extends Circle2 {

    double area;

    int longRadius, shortRadius;

    public Oval2(int xx, int yy, int longRadius, int shortRadius) {
        super(xx, yy, PI * longRadius * shortRadius, shortRadius);
        super.x = xx;
        super.y = yy;
        super.longRadius = longRadius;
        super.radius = shortRadius;
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

public class HW2 {

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
        String command = args[1];


        // 최대최소조회
        while (scanner.hasNextLine()) {
            Scanner line = new Scanner(scanner.nextLine());
            if (command.equals("print_info")) {
                figureType = line.next();
                switch (figureType) {
                    case "원":
                        Circle2 circle2 = new Circle2(line.nextInt(), line.nextInt(), line.nextInt());
                        area = circle2.getArea();
                        totalArea += area;
                        setMaxMin(area);
                        count += 1;
                        break;
                    case "타원":
                        Oval2 oval2 = new Oval2(line.nextInt(), line.nextInt(), line.nextInt(), line.nextInt());
                        area = oval2.getArea();
                        totalArea += area;
                        setMaxMin(area);
                        count += 1;
                        break;
                    case "사각형":
                        Rectangle2 rectangle2 = new Rectangle2(line.nextInt(), line.nextInt(), line.nextInt(), line.nextInt());
                        area = rectangle2.getArea();
                        totalArea += area;
                        setMaxMin(area);
                        count += 1;
                        break;
                    case "삼각형":
                        Triangle2 triangle2 = new Triangle2(line.nextInt(), line.nextInt(), line.nextInt(), line.nextInt());
                        area = triangle2.getArea();
                        totalArea += area;
                        setMaxMin(area);
                        count += 1;
                        break;
                    case "사다리꼴":
                        Trapezoid2 trapezoid = new Trapezoid2(line.nextInt(), line.nextInt(), line.nextInt(), line.nextInt(), line.nextInt());
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
                                Circle2 circle2 = new Circle2(line.nextInt(), line.nextInt(), line.nextInt());
                                area = circle2.getArea();
                                totalArea += area;
                                setMaxMin(area);
                                count += 1;
                                break;
                            case "삼각형":
                                Triangle2 triangle2 = new Triangle2(line.nextInt(), line.nextInt(), line.nextInt(), line.nextInt());
                                area = triangle2.getArea();
                                totalArea += area;
                                setMaxMin(area);
                                count += 1;
                                break;
                            case "사각형":
                                Rectangle2 rectangle2 = new Rectangle2(line.nextInt(), line.nextInt(), line.nextInt(), line.nextInt());
                                area = rectangle2.getArea();
                                totalArea += area;
                                setMaxMin(area);
                                count += 1;
                                break;
                            case "사다리꼴":
                                Trapezoid2 trapezoid = new Trapezoid2(line.nextInt(), line.nextInt(), line.nextInt(), line.nextInt(), line.nextInt());
                                area = trapezoid.getArea();
                                totalArea += area;
                                setMaxMin(area);
                                count += 1;
                                break;
                            case "타원":
                                Oval2 oval2 = new Oval2(line.nextInt(), line.nextInt(), line.nextInt(), line.nextInt());
                                area = oval2.getArea();
                                totalArea += area;
                                setMaxMin(area);
                                count += 1;
                                break;

                        }
                    }
                }

            } else if (command.equals("sort_coord")) {
                figureType = line.next();
                switch (figureType) {
                    case "원":
                        Circle2 figure = new Circle2(line.nextInt(), line.nextInt(), line.nextInt());
                        area = figure.getArea();
                        totalArea += area;
                        setMaxMin(area);
                        count += 1;
                        break;
                    case "삼각형":
                        Triangle2 triangle2 = new Triangle2(line.nextInt(), line.nextInt(), line.nextInt(), line.nextInt());
                        area = triangle2.getArea();
                        totalArea += area;
                        setMaxMin(area);
                        count += 1;
                        break;
                    case "사각형":
                        Rectangle2 rectangle2 = new Rectangle2(line.nextInt(), line.nextInt(), line.nextInt(), line.nextInt());
                        area = rectangle2.getArea();
                        totalArea += area;
                        setMaxMin(area);
                        count += 1;
                        break;
                    case "사다리꼴":
                        Trapezoid2 trapezoid = new Trapezoid2(line.nextInt(), line.nextInt(), line.nextInt(), line.nextInt(), line.nextInt());
                        area = trapezoid.getArea();
                        totalArea += area;
                        setMaxMin(area);
                        count += 1;
                        break;
                    case "타원":
                        Oval2 oval2 = new Oval2(line.nextInt(), line.nextInt(), line.nextInt(), line.nextInt());
                        area = oval2.getArea();
                        totalArea += area;
                        setMaxMin(area);
                        count += 1;
                        break;
                }
            } else if (command.equals("sort_area")) {
                figureType = line.next();
                switch (figureType) {
                    case "원":
                        Circle2 figure = new Circle2(line.nextInt(), line.nextInt(), line.nextInt());
                        area = figure.getArea();
                        totalArea += area;
                        setMaxMin(area);
                        count += 1;
                        break;
                    case "삼각형":
                        Triangle2 triangle2 = new Triangle2(line.nextInt(), line.nextInt(), line.nextInt(), line.nextInt());
                        area = triangle2.getArea();
                        totalArea += area;
                        setMaxMin(area);
                        count += 1;
                        break;
                    case "사각형":
                        Rectangle2 rectangle2 = new Rectangle2(line.nextInt(), line.nextInt(), line.nextInt(), line.nextInt());
                        area = rectangle2.getArea();
                        totalArea += area;
                        setMaxMin(area);
                        count += 1;
                        break;
                    case "사다리꼴":
                        Trapezoid2 trapezoid = new Trapezoid2(line.nextInt(), line.nextInt(), line.nextInt(), line.nextInt(), line.nextInt());
                        area = trapezoid.getArea();
                        totalArea += area;
                        setMaxMin(area);
                        count += 1;
                        break;
                    case "타원":
                        Oval2 oval2 = new Oval2(line.nextInt(), line.nextInt(), line.nextInt(), line.nextInt());
                        area = oval2.getArea();
                        totalArea += area;
                        setMaxMin(area);
                        count += 1;
                        break;
                }
            } else {
                System.out.println("없는 명령어");
                return;
            }


        }
        Figure2[] figures = new Figure2[count];
        Figure2[] fig = new Figure2[count];
//        fig[0] = new Circle2(3, 5, 7);
//        fig[1] = new Trapezoid2(3, 5, 7, 9, 11);
//        System.out.println(fig[1].getArea());

        Scanner scanner1 = new Scanner(file);

        int ind = 0;

        // 최대최소 확인 후 출력문
        while (scanner1.hasNextLine()) {
            Scanner line = new Scanner(scanner1.nextLine());
            if (command.equals("print_info")) {
                figureType = line.next();
                switch (figureType) {
                    case "원":
                        Circle2 circle2 = new Circle2(line.nextInt(), line.nextInt(), line.nextInt());
                        System.out.println(circle2.toString(circle2.getArea() == maxArea, circle2.getArea() == minArea));
                        break;
                    case "타원":
                        Oval2 oval2 = new Oval2(line.nextInt(), line.nextInt(), line.nextInt(), line.nextInt());
                        System.out.println(oval2.toString(oval2.getArea() == maxArea, oval2.getArea() == minArea));
                        break;
                    case "사각형":
                        Rectangle2 rectangle2 = new Rectangle2(line.nextInt(), line.nextInt(), line.nextInt(), line.nextInt());
                        System.out.println(rectangle2.toString(rectangle2.getArea() == maxArea, rectangle2.getArea() == minArea));
                        break;
                    case "삼각형":
                        Triangle2 triangle2 = new Triangle2(line.nextInt(), line.nextInt(), line.nextInt(), line.nextInt());
                        System.out.println(triangle2.toString(triangle2.getArea() == maxArea, triangle2.getArea() == minArea));
                        break;
                    case "사다리꼴":
                        Trapezoid2 trapezoid = new Trapezoid2(line.nextInt(), line.nextInt(), line.nextInt(), line.nextInt(), line.nextInt());
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
                                Circle2 circle2 = new Circle2(line.nextInt(), line.nextInt(), line.nextInt());
                                System.out.println(circle2.toString(circle2.getArea() == maxArea, circle2.getArea() == minArea));
                                break;
                            case "타원":
                                Oval2 ellipse = new Oval2(line.nextInt(), line.nextInt(), line.nextInt(), line.nextInt());
                                System.out.println(ellipse.toString(ellipse.getArea() == maxArea, ellipse.getArea() == minArea));
                                break;
                            case "사각형":
                                Rectangle2 rectangle2 = new Rectangle2(line.nextInt(), line.nextInt(), line.nextInt(), line.nextInt());
                                System.out.println(rectangle2.toString(rectangle2.getArea() == maxArea, rectangle2.getArea() == minArea));
                                break;
                            case "삼각형":
                                Triangle2 triangle2 = new Triangle2(line.nextInt(), line.nextInt(), line.nextInt(), line.nextInt());
                                System.out.println(triangle2.toString(triangle2.getArea() == maxArea, triangle2.getArea() == minArea));
                                break;
                            case "사다리꼴":
                                Trapezoid2 trapezoid = new Trapezoid2(line.nextInt(), line.nextInt(), line.nextInt(), line.nextInt(), line.nextInt());
                                System.out.println(trapezoid.toString(trapezoid.getArea() == maxArea, trapezoid.getArea() == minArea));
                                break;
                            default:
                                System.out.println("잘못된 형식의 도형이 입력되었습니다. 파일을 확인해주십시오.");
                                break;
                        }
                    }
                }
            } else if (command.equals("sort_coord")) {
                figureType = line.next();
                switch (figureType) {
                    case "원":
                        figures[ind] = new Circle2(line.nextInt(), line.nextInt(), line.nextInt());
                        ind++;
                        break;
                    case "타원":
                        figures[ind] = new Oval2(line.nextInt(), line.nextInt(), line.nextInt(), line.nextInt());
                        ind++;
                        break;
                    case "사각형":
                        figures[ind] = new Rectangle2(line.nextInt(), line.nextInt(), line.nextInt(), line.nextInt());
                        ind++;
                        break;
                    case "삼각형":
                        figures[ind] = new Triangle2(line.nextInt(), line.nextInt(), line.nextInt(), line.nextInt());
                        ind++;
                        break;
                    case "사다리꼴":
                        figures[ind] = new Trapezoid2(line.nextInt(), line.nextInt(), line.nextInt(), line.nextInt(), line.nextInt());
                        ind++;
                        break;
                    default:
                        System.out.println("잘못된 형식의 도형이 입력되었습니다. 파일을 확인해주십시오.");
                        break;
                }
            }
            else if (command.equals("sort_area")) {
                figureType = line.next();
                switch (figureType) {
                    case "원":
                        figures[ind] = new Circle2(line.nextInt(), line.nextInt(), line.nextInt());
                        ind++;
                        break;
                    case "타원":
                        figures[ind] = new Oval2(line.nextInt(), line.nextInt(), line.nextInt(), line.nextInt());
                        ind++;
                        break;
                    case "사각형":
                        figures[ind] = new Rectangle2(line.nextInt(), line.nextInt(), line.nextInt(), line.nextInt());
                        ind++;
                        break;
                    case "삼각형":
                        figures[ind] = new Triangle2(line.nextInt(), line.nextInt(), line.nextInt(), line.nextInt());
                        ind++;
                        break;
                    case "사다리꼴":
                        figures[ind] = new Trapezoid2(line.nextInt(), line.nextInt(), line.nextInt(), line.nextInt(), line.nextInt());
                        ind++;
                        break;
                    default:
                        System.out.println("잘못된 형식의 도형이 입력되었습니다. 파일을 확인해주십시오.");
                        break;
                }
            }


        }

        if (command.equals("sort_coord")) {
            for (int i = 0; i < figures.length; i++) {
                for (int j = 0; j < figures.length - 1; j++) {
                    if (figures[j].getX() > figures[j + 1].getX()) {
                        Figure2 temp = figures[j];
                        figures[j] = figures[j + 1];
                        figures[j + 1] = temp;
                    } else if (figures[j].getX() == figures[j + 1].getX() && figures[j].getY() > figures[j + 1].getY()) {
                        Figure2 temp = figures[j];
                        figures[j] = figures[j + 1];
                        figures[j + 1] = temp;
                    }
                }
            }
            for (int i = 0; i < figures.length; i++) {
                if (figures[i].getName().equals("원")) {
                    System.out.println(figures[i].toCircleString(figures[i].getArea() == maxArea, figures[i].getArea() == minArea));
                }
                if (figures[i].getName().equals("타원")) {
                    System.out.println(figures[i].toOvalString(figures[i].getArea() == maxArea, figures[i].getArea() == minArea));
                }
                if (figures[i].getName().equals("사다리꼴")) {
                    System.out.println(figures[i].toTrapezoidString(figures[i].getArea() == maxArea, figures[i].getArea() == minArea));
                }
                if (figures[i].getName().equals("사각형")) {
                    System.out.println(figures[i].toRectangleString(figures[i].getArea() == maxArea, figures[i].getArea() == minArea));
                }
                if (figures[i].getName().equals("삼각형")) {
                    System.out.println(figures[i].toTriangleString(figures[i].getArea() == maxArea, figures[i].getArea() == minArea));
                }


            }
        }
        if (command.equals("sort_area")) {
            for (int i = 0; i < figures.length; i++) {
                for (int j = 0; j < figures.length - 1; j++) {
                    if (figures[j].getArea() > figures[j + 1].getArea()) {
                        Figure2 temp = figures[j];
                        figures[j] = figures[j + 1];
                        figures[j + 1] = temp;
                    }
                }
            }
            for (int i = 0; i < figures.length; i++) {
                if (figures[i].getName().equals("원")) {
                    System.out.println(figures[i].toCircleString(figures[i].getArea() == maxArea, figures[i].getArea() == minArea));
                }
                if (figures[i].getName().equals("타원")) {
                    System.out.println(figures[i].toOvalString(figures[i].getArea() == maxArea, figures[i].getArea() == minArea));
                }
                if (figures[i].getName().equals("사다리꼴")) {
                    System.out.println(figures[i].toTrapezoidString(figures[i].getArea() == maxArea, figures[i].getArea() == minArea));
                }
                if (figures[i].getName().equals("사각형")) {
                    System.out.println(figures[i].toRectangleString(figures[i].getArea() == maxArea, figures[i].getArea() == minArea));
                }
                if (figures[i].getName().equals("삼각형")) {
                    System.out.println(figures[i].toTriangleString(figures[i].getArea() == maxArea, figures[i].getArea() == minArea));
                }


            }
        }
        System.out.println();
        System.out.printf("평균 면적 : %.2f", totalArea / count);
    }

}