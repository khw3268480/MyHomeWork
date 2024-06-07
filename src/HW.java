import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;
abstract class Person {
    String name, id, birth, address, phoneNumber, type;

    public Person(String id, String name, String birth, String phoneNumber, String address) {
        this.name = name;
        this.id = id;
        this.birth = birth;
        this.address = address;
        this.phoneNumber = phoneNumber;
    }

    public Person() {

    }

    public String getName() {
        return name;
    }

    public String getType() {
        return type;
    }

    public String getId() {
        return id;
    }

    public String getBirth() {
        return birth;
    }

    public String getAddress() {
        return address;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }
}

class Student extends Person {

    String admissionDate;
    String deptName;
    String[] deptList = new String[5];


    Random random = new Random();

    public Student(String id, String name, String birth, String phoneNumber, String address) {
        super(id, name, birth, phoneNumber, address);
        deptName = randomDeptName();
        admissionDate = randomAdmissionDate();
    }

    public String getDeptName() {
        return deptName;
    }

    public String getAdmissionDate() {
        return admissionDate;
    }

    public Student() {
        super();
    }

    public String randomDeptName() {
        deptList[0] = "소프트웨어학과";
        deptList[1] = "컴퓨터공학과";
        deptList[2] = "전자공학과";
        deptList[3] = "전기공학과";
        deptList[4] = "실내건축학과";
        int i = random.nextInt(0, 5);
        return deptList[i];
    }

    public String randomAdmissionDate() {
        String date = "";
        date += String.valueOf(random.nextInt(2015, 2025)) + "-";
        date += String.valueOf(random.nextInt(1, 13)) + "-";
        date += String.valueOf(random.nextInt(1, 30));
        return date;
    }

}

class Staff extends Person {

    String deptName;
    String[] deptList = new String[5];


    Random random = new Random();

    public String getDeptName() {
        return deptName;
    }

    public Staff(String id, String name, String birth, String phoneNumber, String address) {
        super(id, name, birth, phoneNumber, address);
        Student student = new Student();
        deptName = student.randomDeptName();
    }


}

class Under extends Student {


    int grade;
    String club;
    String[] clubList = new String[5];

    public String getClub() {
        return club;
    }

    public int getGrade() {
        return grade;
    }

    public Under(String id, String name, String birth, String phoneNumber, String address) {
        super(id, name, birth, phoneNumber, address);
        randomGrade();
        club = randomClub();
        super.type = "학부";
    }

    public String randomClub() {
        clubList[0] = "풋살동아리";
        clubList[1] = "게임동아리";
        clubList[2] = "밴드동아리";
        clubList[3] = "댄스동아리";
        clubList[4] = "노래동아리";
        int i = random.nextInt(0, 6);
        if (i == 5) {
            return "없음";
        }
        return clubList[i];
    }

    public void randomGrade() {
        this.grade = random.nextInt(1, 5);
    }

}

class Graduate extends Student {


    String research;
    String[] researchList = new String[10];

    public Graduate(String id, String name, String birth, String phoneNumber, String address) {
        super(id, name, birth, phoneNumber, address);
        research = randomResearch();
    }

    public Graduate() {

    }

    public String getResearch() {
        return research;
    }

    public String randomResearch() {
        researchList[0] = "인공지능";
        researchList[1] = "머신러닝";
        researchList[2] = "데이터 과학";
        researchList[3] = "사이버 보안";
        researchList[4] = "클라우드 컴퓨팅";
        researchList[5] = "데이터베이스";
        researchList[6] = "컴퓨터그래픽";
        researchList[7] = "네트워크";
        researchList[8] = "계산이론";
        researchList[9] = "알고리즘";
        int i = random.nextInt(0, 10);
        return researchList[i];
    }

}

class MS extends Graduate {


    int semester;


    public MS(String id, String name, String birth, String phoneNumber, String address) {
        super(id, name, birth, phoneNumber, address);
        randomSemester();
        super.type = "석사";
    }

    public int getSemester() {
        return semester;
    }

    public void randomSemester() {
        this.semester = random.nextInt(1, 5);
    }

}

class Assistant extends Staff {


    String comparator;

    String[] comparatorList = new String[3];

    public String getComparator() {
        return comparator;
    }

    public Assistant(String id, String name, String birth, String phoneNumber, String address) {
        super(id, name, birth, phoneNumber, address);
        comparator = randomComparator();
        super.type = "조교";
    }

    public String randomComparator() {
        comparatorList[0] = "전임";
        comparatorList[1] = "근로";
        comparatorList[2] = "시간제";
        int i = random.nextInt(0, 3);
        return comparatorList[i];
    }

}


class Professor extends Staff {


    String major;
    String labLocation;

    public String getMajor() {
        return major;
    }

    public String getLabLocation() {
        return labLocation;
    }

    public Professor(String id, String name, String birth, String phoneNumber, String address) {
        super(id, name, birth, phoneNumber, address);
        Graduate graduate = new Graduate();
        major = graduate.randomResearch();
        labLocation = createRandomLabLocation();
        super.type = "교수";
    }

    public String createRandomLabLocation() {
        String result = "";
        int i = random.nextInt(1, 10);
        result += i + "호관 " + i + "0" + random.nextInt(0, 100) + "호";
        return result;
    }


}

class PhD extends Graduate {


    int annual;

    public PhD(String id, String name, String birth, String phoneNumber, String address) {
        super(id, name, birth, phoneNumber, address);
        randomAnnual();
        super.type = "박사";
    }

    public int getAnnual() {
        return annual;
    }

    public void randomAnnual() {
        this.annual = random.nextInt(1, 7);
    }

}

public class HW {

    public static void main(String[] args) throws FileNotFoundException {


        LinkedList<Person> personList = new LinkedList<>();

        File file = new File(args[0]);
        Scanner scanner = new Scanner(file);
        while (scanner.hasNextLine()) {
            Scanner line = new Scanner(scanner.nextLine());
            line.useDelimiter("\\|");
            switch (line.next()) {
                case "학부":
                    Under under = new Under(line.next(), line.next(), line.next(), line.next(), line.next());
                    personList.add(under);
                    break;
                case "석사":
                    MS ms = new MS(line.next(), line.next(), line.next(), line.next(), line.next());
                    personList.add(ms);
                    break;
                case "박사":
                    PhD phd = new PhD(line.next(), line.next(), line.next(), line.next(), line.next());
                    personList.add(phd);
                    break;
                case "조교":
                    Assistant assistant = new Assistant(line.next(), line.next(), line.next(), line.next(), line.next());
                    personList.add(assistant);
                    break;
                case "교수":
                    Professor professor = new Professor(line.next(), line.next(), line.next(), line.next(), line.next());
                    personList.add(professor);
                    break;
            }
        }
        String command = args[1];
        String subcommand = args[2];
        switch (command) {
            case "print":
                if (subcommand.equals("all")) {
                    for (int i = 0; i < personList.size(); i++) {
                        print(i, personList);
                    }
                } else if (subcommand.equals("student")) {
                    for (int i = 0; i < personList.size(); i++) {
                        Person person = personList.get(i);
                        String type = person.getType();
                        if (!type.equals("교수") && !type.equals("조교")) {
                            System.out.printf("[%s] 이름: %s | 생년월일: %s | 전화번호: %s | 주소: %s | 주민등록번호: %s | ", person.getType(), person.getName(), person.getBirth(), person.getPhoneNumber(), person.getAddress(), person.getId());
                        }
                        if (type.equals("학부")) {
                            Under under = (Under) person;
                            System.out.printf("입학년도: %s | 학과명: %s | 학년: %d | 동아리: %s\n", under.getAdmissionDate(), under.getDeptName(), under.getGrade(), under.getClub());
                        } else if (type.equals("석사")) {
                            MS ms = (MS) person;
                            System.out.printf("입학년도: %s | 학과명: %s | 학기: %d\n", ms.getAdmissionDate(), ms.getDeptName(), ms.getSemester());
                        } else if (type.equals("박사")) {
                            PhD phD = (PhD) person;
                            System.out.printf("입학년도: %s | 학과명: %s | 연차: %d\n", phD.getAdmissionDate(), phD.getDeptName(), phD.getAnnual());
                        }

                    }
                } else if (subcommand.equals("staff")) {
                    for (int i = 0; i < personList.size(); i++) {
                        Person person = personList.get(i);
                        String type = person.getType();
                        if (!type.equals("학부") && !type.equals("석사") && !type.equals("박사")) {
                            System.out.printf("[%s] 이름: %s | 생년월일: %s | 전화번호: %s | 주소: %s | 주민등록번호: %s | ", person.getType(), person.getName(), person.getBirth(), person.getPhoneNumber(), person.getAddress(), person.getId());
                        }
                        if (type.equals("조교")) {
                            Assistant assistant = (Assistant) person;
                            System.out.printf("부서명: %s | 구분: %s\n", assistant.getDeptName(), assistant.getComparator());
                        } else if (type.equals("교수")) {
                            Professor professor = (Professor) person;
                            System.out.printf("부서명: %s | 전공분야: %s | 연구실 위치: %s\n", professor.getDeptName(), professor.getMajor(), professor.getLabLocation());
                        }

                    }
                } else if (subcommand.equals("professor")) {
                    for (int i = 0; i < personList.size(); i++) {
                        Person person = personList.get(i);
                        String type = person.getType();
                        if (!type.equals("학부") && !type.equals("석사") && !type.equals("박사") && !type.equals("조교")) {
                            System.out.printf("[%s] 이름: %s | 생년월일: %s | 전화번호: %s | 주소: %s | 주민등록번호: %s | ", person.getType(), person.getName(), person.getBirth(), person.getPhoneNumber(), person.getAddress(), person.getId());
                        }
                        if (type.equals("교수")) {
                            Professor professor = (Professor) person;
                            System.out.printf("부서명: %s | 전공분야: %s | 연구실 위치: %s\n", professor.getDeptName(), professor.getMajor(), professor.getLabLocation());
                        }

                    }
                } else {
                    String[] searchId = new String[2];
                    boolean isPrinted = false;
                    try {
                        Integer.parseInt(subcommand.split("-")[0]);
                        Integer.parseInt(subcommand.split("-")[1]);
                    } catch (Exception e) {
                        System.out.println("주민등록번호의 형식이 올바르지 않거나 올바른 명령어가 아닙니다.");
                        return;
                    }
                    searchId[0] = subcommand.split("-")[0];
                    searchId[1] = subcommand.split("-")[1];
                    if (!(searchId[0].length() == 6 && searchId[1].length() == 7)) {
                        System.out.println("주민등록번호의 형식이 올바르지 않습니다.");
                        return;
                    }
                    for (int i = 0; i < personList.size(); i++) {
                        if (personList.get(i).getId().equals(searchId[0] + "-" + searchId[1])) {
                            print(i, personList);
                            isPrinted = true;
                        }
                    }
                    if (!isPrinted){
                        System.out.println(String.format("%s 주민등록번호를 찾을 수 없습니다.", subcommand));
                    }

                }
                break;
            case "sort":
                if (subcommand.equals("ssno")) {
                    Collections.sort(personList, (o1, o2) -> {
                        String[] id1 = o1.getId().split("-");
                        String[] id2 = o2.getId().split("-");
                        if (Integer.parseInt(id1[0]) > Integer.parseInt(id2[0])) {
                            return 1;
                        } else {
                            if (Integer.parseInt(id1[1]) == Integer.parseInt(id2[1])) {
                                return 0;
                            } else {
                                return -1;
                            }
                        }
                    });
                    for (int i = 0; i < personList.size(); i++) {
                        print(i, personList);
                    }
                } else if (subcommand.equals("name")) {
                    Collections.sort(personList, (o1, o2) -> {
                        String name1 = o1.getName();
                        String name2 = o2.getName();
                        return name1.compareTo(name2);
                    });
                    for (int i = 0; i < personList.size(); i++) {
                        print(i, personList);
                    }
                } else if (subcommand.equals("birthdate")) {
                    Collections.sort(personList, (o1, o2) -> {
                        String[] birth1 = o1.getBirth().split("-");
                        String[] birth2 = o2.getBirth().split("-");
                        int result = Integer.compare(Integer.parseInt(birth1[0]), Integer.parseInt(birth2[0]));
                        if (result != 0) {
                            return result;
                        }
                        result = Integer.compare(Integer.parseInt(birth1[1]), Integer.parseInt(birth2[1]));
                        if (result != 0) {
                            return result;
                        }
                        return Integer.compare(Integer.parseInt(birth1[2]), Integer.parseInt(birth2[2]));
                    });
                    for (int i = 0; i < personList.size(); i++) {
                        print(i, personList);
                    }
                } else if (subcommand.equals("address")) { // 동까지만 정렬
                    Collections.sort(personList, (o1, o2) -> {
                        String[] name1 = o1.getAddress().split(" ");
                        String[] name2 = o2.getAddress().split(" ");

                        int result = name1[0].compareTo(name2[0]);
                        if (result != 0) {
                            return result;
                        }
                        result = name1[1].compareTo(name2[1]);
                        if (result != 0) {
                            return result;
                        }
                        return name1[2].compareTo(name2[2]);
                    });
                    for (int i = 0; i < personList.size(); i++) {
                        print(i, personList);
                    }
                }
                else{
                    System.out.println("옳지 않은 정렬 기준입니다. 사용 가능한 정렬: [sort/name/birthdate/address]");
                    return;
                }
                break;


        }

    }

    public static void print(int i, LinkedList<Person> personList) {
        Person person = personList.get(i);
        String type = person.getType();
        System.out.printf("[%s] 이름: %s | 생년월일: %s | 전화번호: %s | 주소: %s | 주민등록번호: %s | ", person.getType(), person.getName(), person.getBirth(), person.getPhoneNumber(), person.getAddress(), person.getId());

        if (type.equals("학부")) {
            Under under = (Under) person;
            System.out.printf("입학년도: %s | 학과명: %s | 학년: %d | 동아리: %s\n", under.getAdmissionDate(), under.getDeptName(), under.getGrade(), under.getClub());
        } else if (type.equals("석사")) {
            MS ms = (MS) person;
            System.out.printf("입학년도: %s | 학과명: %s | 학기: %d\n", ms.getAdmissionDate(), ms.getDeptName(), ms.getSemester());
        } else if (type.equals("박사")) {
            PhD phD = (PhD) person;
            System.out.printf("입학년도: %s | 학과명: %s | 연차: %d\n", phD.getAdmissionDate(), phD.getDeptName(), phD.getAnnual());
        } else if (type.equals("조교")) {
            Assistant assistant = (Assistant) person;
            System.out.printf("부서명: %s | 구분: %s\n", assistant.getDeptName(), assistant.getComparator());
        } else if (type.equals("교수")) {
            Professor professor = (Professor) person;
            System.out.printf("부서명: %s | 전공분야: %s | 연구실 위치: %s\n", professor.getDeptName(), professor.getMajor(), professor.getLabLocation());
        }
    }
}