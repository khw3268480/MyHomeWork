import javax.sound.midi.Soundbank;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Random;
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

    public void setMoney(int coin) {
        this.money = coin;
    }

    public String getBankbookNumber() {
        return bankbookNumber;
    }

    public String getUuid() {
        return uuid;
    }

    public String getSex() {
        return sex;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getAddress() {
        return address;
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

    public boolean isEnough(int cost) {
        return getMoney() >= cost;
    }

    public boolean isEnough(Account account, int cost) {
        return account.getMoney() >= cost;
    }

    public int withdraw(int coin) {
        if (!isEnough(coin)) {
            System.out.println(String.format("출금: %d원 | 계좌번호: %s-%s | 잔액: %d원 : 잔액부족", coin, getBankbookNumber(), getPurpose(), getMoney()));
            return -1;
        }
        this.money -= coin;
        System.out.println(String.format("출금: %d원 | 계좌번호: %s-%s | 잔액: %d원", coin, getBankbookNumber(), getPurpose(), getMoney()));
        return this.money;
    }

    public int deposit(int coin) {
        this.money += coin;
        System.out.println(String.format("입금: %d원 | 계좌번호: %s-%s | 잔액: %d원", coin, getBankbookNumber(), getPurpose(), getMoney()));
        return this.money;
    }

    public int transfer(Account account, int coin) {
        // account는 돈을 보낸 사람의 계좌
        if (!isEnough(account, coin)) {
            System.out.println(String.format("이체: %d원 | (계좌번호: %s-%s | 잔액: %d원) -> (계좌번호: %s-%s | 잔액: %d원) : 잔액부족", coin, account.getBankbookNumber(), account.getPurpose(), account.getMoney(), getBankbookNumber(), getPurpose(), getMoney()));
            return -1;
        }
        account.setMoney(account.getMoney() - coin);
        this.money += coin;
        System.out.println(String.format("이체: %d원 | (계좌번호: %s-%s | 잔액: %d원) -> (계좌번호: %s-%s | 잔액: %d원)", coin, account.getBankbookNumber(), account.getPurpose(), account.getMoney(), getBankbookNumber(), getPurpose(), getMoney()));
        return this.money;
    }

    public String notice() {
        return String.format("%s-%s | 개설일: %s | 잔액: %s원 | 계좌주: %s[%s](%s) | 전화번호: %s | 주소: %s", getBankbookNumber(), getPurpose(), getCreateDate(), getMoney(), getName(), getUuid(), (getSex().equals("m")) ? "남" : "여", getPhoneNumber(), getAddress());
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
//                    System.out.println(i);
                    if (banks[i].getBankbookNumber().equals(banknum)) {
//                        System.out.println(String.format("%s의 계좌번호는 %s이름의 계좌입니다. i = %d", banknum, name, i));
                        accounts[i] = new Account(uuid, name, sex, phoneNumber, address, banks[i]);
                        break;
                    }
                }
            }
        }

        int r = getRandomAccount(accounts);


        for (Account value : accounts) {
            System.out.println(value.notice());
        }
        System.out.println();

        for (int i = 0; i < 5; i++) {
            int randomAccount = getRandomAccount(accounts);
            accounts[randomAccount].deposit(getRandomCost());
        }
        accounts[r].withdraw(2147483647);

        for (int i = 0; i < 4; i++) {
            int randomAccount = getRandomAccount(accounts);
            accounts[randomAccount].withdraw(getRandomCost());
        }
        for (int i = 0; i < 5; i++) {
            int randomAccount = getRandomAccount(accounts);
            int randomAccount2 = getRandomAccount(accounts);
            accounts[randomAccount].transfer(accounts[randomAccount2], getRandomCost());
        }
        System.out.println();

        for (Account account : accounts) {
            System.out.println(account.notice());
        }

    }

    public static int getRandomAccount(Account[] accounts) {
        Random random = new Random();
        return random.nextInt(accounts.length);
    }

    public static int getRandomCost() {
        Random random = new Random();
        return random.nextInt(0, 100000);
    }


}