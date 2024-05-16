import javax.sound.midi.Soundbank;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

class Account {

    String uuid, name, sex, phoneNumber, address, createDate, purpose;
    String bankbookNumber;

    Account account;
    int money;

    public Account(String purpose, String bankBookNumber, String createDate, int money) {
        this.money = money;
        this.bankbookNumber = bankBookNumber;
        this.createDate = createDate;
        this.purpose = purpose;
    }

    public Account(String uuid, String name, String sex, String phoneNumber, String address, Account accounts) {
        this.uuid = uuid;
        this.name = name;
        this.sex = sex;
        this.phoneNumber = phoneNumber;
        this.address = address;
        this.purpose = accounts.getPurpose();
        this.bankbookNumber = accounts.getBankbookNumber();
        this.money = accounts.getMoney();
        this.createDate = accounts.getCreateDate();
    }

    public int getMoney() {
        return money;
    }

    public String getBankbookNumber() {
        return bankbookNumber;
    }
    public String getCreateDate() {
        return createDate;
    }
    public String getPurpose() {
        return purpose;
    }

    public String getName() {
        return name;
    }
}

public class HW2 {
    public static void main(String[] args) throws FileNotFoundException {

        File accountFile = new File(args[0]);
        File customerFile = new File(args[1]);

        int accountInfoCount = 0;

        if (!accountFile.exists() || !customerFile.exists()) {
            System.out.println("잘못된 파일");
            return;
        }

        Scanner scanner = new Scanner(accountFile);

        // customer 정보 파일 줄 수
        while (scanner.hasNextLine()) {
            accountInfoCount++;
            scanner.nextLine();
        }

//        int dataLength = customerInfoCount;
        Account[] banks = new Account[accountInfoCount];
        Account[] accounts = new Account[accountInfoCount];

        String[] bankBooks = new String[accountInfoCount];

        Scanner accountScanner = new Scanner(accountFile);

        int a = 0;

        while (accountScanner.hasNextLine()) {
            Scanner accountLine = new Scanner(accountScanner.nextLine());
            String purpose = accountLine.next();
            String bankBookNumber = accountLine.next();
            String createDate = accountLine.next();
            String money = accountLine.next();
            banks[a] = new Account(purpose, bankBookNumber, createDate, Integer.parseInt(money));
            bankBooks[a] = bankBookNumber;
            a += 1;
        }

        Scanner customerScanner = new Scanner(customerFile);

        while (customerScanner.hasNextLine()) {
            Scanner customerLine = new Scanner(customerScanner.nextLine());
            String uuid = customerLine.next();
            String name = customerLine.next();
            String sex = customerLine.next();
            String phoneNumber = customerLine.next();
            String address = customerLine.next();
//            System.out.println(String.format("%s %s %s %s %s", uuid, name, sex ,phoneNumber, address));
            while (customerLine.hasNext()) {
                String banknum = customerLine.next();
                for (int i = 0; i < banks.length; i++) {
                    if (banks[i].getBankbookNumber().equals(banknum)) {
//                        System.out.println(String.format("%s의 계좌번호는 %s이름의 계좌입니다. i = %d", banknum, name, i));
                        accounts[i] = new Account(uuid, name, sex, phoneNumber, address, banks[i]);
                    }
                }
            }
        }
        System.out.println(accounts[2].getPurpose());
    }

    /** 유저 정보 입력 완료 */

}