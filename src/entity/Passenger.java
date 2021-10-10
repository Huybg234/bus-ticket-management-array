package entity;

import java.util.Scanner;

public class Passenger extends Person{
    private int id;
    private String passengerType;

    public final static String STUDENT = "Học sinh - sinh viên";
    public final static String INDUSTRY_Staff = "Cán bộ trong ngành";
    public final static String ORDINARY_GUEST = "Khách thông thường";

    private static int AUTO_ID = 10000;

    public Passenger() {
    }

    public Passenger(int id, String passengerType) {
        this.id = id;
        this.passengerType = passengerType;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPassengerType() {
        return passengerType;
    }

    public void setPassengerType(String passengerType) {
        this.passengerType = passengerType;
    }

    public static String getSTUDENT() {
        return STUDENT;
    }

    public static String getINDUSTRY_Staff() {
        return INDUSTRY_Staff;
    }

    public static String getOrdinaryGuest() {
        return ORDINARY_GUEST;
    }

    public static int getAutoId() {
        return AUTO_ID;
    }

    public static void setAutoId(int autoId) {
        AUTO_ID = autoId;
    }

    public void inputInfo(){
        this.setId(Passenger.AUTO_ID);
        super.inputInfo();
        System.out.println("Nhập loại khách hàng: ");
        System.out.println("1.Học sinh - sinh viên");
        System.out.println("2.Cán bộ trong ngành");
        System.out.println("3.Khách hàng thông thường");
        System.out.println("Nhập sự lựa chọn của bạn: ");
        boolean check = true;
        do {
            int choice = 0;
            try {
                choice = new Scanner(System.in).nextInt();
            } catch (Exception e) {
                System.out.print("Không được nhập ký tự khác ngoài số! Nhập lại: ");
                check = false;
                continue;
            }
            if (choice <= 0 || choice > 4) {
                System.out.print("Nhập số trong khoảng từ 1 đến 3! Nhập lại: ");
                check = false;
                continue;
            }
            switch (choice) {
                case 1:
                    this.setPassengerType(Passenger.STUDENT);
                    System.out.println("Học sinh - sinh viên");
                    check = true;
                    break;
                case 2:
                    this.setPassengerType(Passenger.INDUSTRY_Staff);
                    System.out.println("Cán bộ trong ngành");
                    check = true;
                    break;
                case 3:
                    this.setPassengerType(Passenger.ORDINARY_GUEST);
                    System.out.println("khách hàng thông thường");
                    check = true;
                    break;
                default:
                    System.out.println("Nhập sai! Hãy nhập từ 1 đến 3!");
                    check = false;
                    break;
            }
        } while (!check);
        Passenger.AUTO_ID++;
    }

    @Override
    public String toString() {
        return super.toString()+ "Passenger{" +
                "id=" + id +
                ", passengerType='" + passengerType + '\'' +
                '}';
    }
}
