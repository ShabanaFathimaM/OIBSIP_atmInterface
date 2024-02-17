
import java.util.Scanner;

class BankAccount {
    String name;
    String userName;
    String password;
    String accountNo;
    float balance = 100000f;
    int transactions = 0;
    String transactionHistory = "";

    public void register() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter your name:");
        this.name = sc.nextLine();
        System.out.println("Enter your username:");
        this.userName = sc.nextLine();
        System.out.println("Enter your password:");
        this.password = sc.nextLine();
        System.out.println("Enter your account number:");
        this.accountNo = sc.nextLine();
        System.out.println("Registration completed, kindly login.");
    }

    public boolean login() {
        boolean isLogin = false;
        Scanner sc = new Scanner(System.in);
        while (!isLogin) {
            System.out.println("Enter your username:");
            String username = sc.nextLine();
            if (username.equals(userName)) {
                while (!isLogin) {
                    System.out.println("Enter your password:");
                    String password = sc.nextLine();
                    if (password.equals(this.password)) {
                        System.out.println("Login successful");
                        isLogin = true;
                    } else {
                        System.out.println("Incorrect password");
                    }
                }
            } else {
                System.out.println("Username not found");
            }
        }
        return isLogin;
    }

    public void withdraw() {
        System.out.println("Enter amount to withdraw:");
        Scanner sc = new Scanner(System.in);
        float amount = sc.nextFloat();
        try {
            if (balance >= amount) {
                transactions++;
                balance -= amount;
                System.out.println("Withdraw Successful");
                String str = amount + " Rs withdrawn\n";
                transactionHistory = transactionHistory.concat(str);
            } else {
                System.out.println("Insufficient balance");
            }
        } catch (Exception e) {
            System.out.println("Invalid input");
        }
    }

    public void deposit() {
        System.out.println("Enter amount to deposit:");
        Scanner sc = new Scanner(System.in);
        float amount = sc.nextFloat();
        try {
            if (amount <= 100000f) {
                transactions++;
                balance += amount;
                System.out.println("Successfully deposited");
                String str = amount + " Rs deposited\n";
                transactionHistory = transactionHistory.concat(str);
            } else {
                System.out.println("Sorry...Limit is 100000.00");
            }
        } catch (Exception e) {
            System.out.println("Invalid input");
        }
    }

    public void transfer() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter recipient's name:");
        String recipient = sc.nextLine();
        System.out.println("Enter amount to transfer:");
        float amount = sc.nextFloat();
        try {
            if (balance >= amount) {
                if (amount <= 50000f) {
                    transactions++;
                    balance -= amount;
                    System.out.println("Successfully transferred to " + recipient);
                    String str = amount + " Rs transferred to " + recipient + "\n";
                    transactionHistory = transactionHistory.concat(str);
                } else {
                    System.out.println("Sorry..limit is 50000.00");
                }
            } else {
                System.out.println("Insufficient balance");
            }
        } catch (Exception e) {
            System.out.println("Invalid input");
        }
    }

    public void checkBalance() {
        System.out.println("Balance: " + balance + " Rs");
    }

    public void transHistory() {
        if (transactions == 0) {
            System.out.println("Transaction history is empty");
        } else {
            System.out.println("Transaction History:\n" + transactionHistory);
        }
    }
}

public class myatm {
    public static int takeIntegerInput(int limit) {
        int input = 0;
        boolean flag = false;
        while (!flag) {
            try {
                Scanner sc = new Scanner(System.in);
                input = sc.nextInt();
                flag = true;
                if (input > limit || input < 1) {
                    System.out.println("Choose a number between 1 to " + limit);
                    flag = false;
                }
            } catch (Exception e) {
                System.out.println("Enter only an integer value");
                flag = false;
            }
        }
        return input;
    }

    public static void main(String[] args) {
        System.out.println("Welcome to mybank");
        System.out.println("Enter your choice:");
        int choice = takeIntegerInput(2);
        if (choice == 1) {
            BankAccount b = new BankAccount();
            b.register();
            while (true) {
                System.out.println("\n1. Login \n2. Exit");
                System.out.println("Enter your choice:");
                int ch = takeIntegerInput(2);
                if (ch == 1) {
                    if (b.login()) {
                        System.out.println("Welcome back " + b.name);
                        boolean isFinished = false;
                        while (!isFinished) {
                            System.out.println("\n1. Withdraw \n2. Deposit \n3. Transfer \n4. Check Balance \n5. Transaction History \n6. Logout");
                            System.out.println("Enter your choice:");
                            int c = takeIntegerInput(6);
                            switch (c) {
                                case 1:
                                    b.withdraw();
                                    break;
                                case 2:
                                    b.deposit();
                                    break;
                                case 3:
                                    b.transfer();
                                    break;
                                case 4:
                                    b.checkBalance();
                                    break;
                                case 5:
                                    b.transHistory();
                                    break;
                                case 6:
                                    isFinished = true;
                                    break;
                            }
                        }
                    }
                } else {
                    System.exit(0);
                }
            }
        } else {
            System.exit(0);
        }
    }
}
