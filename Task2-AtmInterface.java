package com.oasisinfobyte;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String id;
        int pin;
        System.out.print("Enter the login Id: ");
        id = sc.nextLine();
        System.out.print("Enter the pin: ");
        pin = sc.nextInt();
        int ans = 0;
        AtmInterface a = new AtmInterface(id,pin);
        boolean check = a.login();

        if(check==true){
        while(ans !=5){
            System.out.println("1. Transactions History\n2. Withdraw\n3. Deposit");
            System.out.println("4. Transfer\n5. Quit");
            System.out.println("Enter the operation: ");
            ans = sc.nextInt();
            switch (ans){
                case 1:
                    a.transactionHistory();
                    break;
                case 2:
                    a.withdraw();
                    break;
                case 3:
                    a.deposit();
                    break;
                case 4:
                    a.transfer();
                    break;
                case 5:
                    ans = 5;
                    System.out.println("Visit Again");
                    break;
            }
        }
      }
    }
}

class LoginDetails {
    private String id;
    private int pin;
    public LoginDetails(String id,int pin){
        this.id = id;
        this.pin = pin;
    }
} 
class AtmInterface {
    private  String id;
    private int pin;
    private static double balance;
    private ArrayList<LoginDetails> details = new ArrayList<>();
    static Scanner sc = new Scanner(System.in);
    private static ArrayList<String> transaction = new ArrayList<>();
    private LoginDetails log;
    public AtmInterface(String id,int pin){
        log = new LoginDetails(id,pin);
        details.add(log);
        balance = 0;
    }
    public boolean login(){
        if(details.contains(log)){
            System.out.println("Welcome!");
            return true;
        }
        else{
            Scanner tc = new Scanner(System.in);Scanner fc = new Scanner(System.in);
            int ans;
            System.out.println("No account details found");
            System.out.print("If you don't have an account kindly Register(1/0): ");
            ans = sc.nextInt();
            if(ans==1){
                register(log);
                System.out.println("Now you can login with these credentials");
                return true;
            }
            return false;
        }
    }
    public void register(LoginDetails l){
        if(details.contains(l)){
            System.out.println("Account already exists!");
        }else {
            details.add(l);
            System.out.println("Congratulations you are registered successfully");
        }
    }
    public void withdraw(){
        double wd;
        System.out.println("Enter the amount to withdraw: ");
        wd = sc.nextDouble();
        double ref = balance;
        if(ref - wd < 0 || balance == 0)
            System.out.println("Insufficient Balance");
        else{
            balance -= wd;
            System.out.println("Amount withdrawn successfully");
            System.out.println("Balance: "+balance);
            transaction.add("Withdraw: "+wd+" -> Balance: "+balance);
        }
    }
    public void deposit(){
        double dp;
        System.out.println("Enter the amount to deposit");
        dp = sc.nextDouble();
        balance+=dp;
        System.out.println("Amount deposited successfully");
        System.out.println("Balance: "+balance);
        transaction.add("Deposit: "+dp+" -> Balance: "+balance);
    }
    public void transfer(){
        System.out.println("Enter the amount to transfer: ");
        double t;int acc;
        t = sc.nextDouble();
        double ref = balance;
        System.out.println("Enter receivers Account number: ");
        acc = sc.nextInt();
        if(ref - t < 0 || balance == 0){
            System.out.println("Insufficient balance to transfer funds");
        }else{
            balance-=t;
            System.out.println("Amount transferred successfully");
            System.out.println("Balance: "+balance);
            transaction.add("Fund Transfer of "+t+" to Account No."+acc+" -> Balance: "+balance);
        }
    }
    public void transactionHistory(){
        System.out.println("----------Transaction History----------");
        if(transaction.size()==0)
            System.out.println("No transactions");
        else{
        for(int i=0;i<transaction.size();i++){
            System.out.println(transaction.get(i));
            }
        }
    }
}
