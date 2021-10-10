package entity;

import java.util.Scanner;

public class Ticket {
    private int id;
    private String description;
    private float price;

    private final static String PRIVATE_LINE = "Tuyến riêng";
    private final static String SOME_LINE = "Một số tuyến";
    private final static String INTERLINE = "Liên tuyến";

    private static int AUTO_ID = 100;

    public Ticket() {
    }

    public Ticket(int id, String description, float price) {
        this.id = id;
        this.description = description;
        this.price = price;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public static String getPrivateLine() {
        return PRIVATE_LINE;
    }

    public static String getSomeLine() {
        return SOME_LINE;
    }

    public static String getINTERLINE() {
        return INTERLINE;
    }

    public static int getAutoId() {
        return AUTO_ID;
    }

    public static void setAutoId(int autoId) {
        AUTO_ID = autoId;
    }

    public void inputTicketInfo(){
        this.setId(Ticket.AUTO_ID);
        System.out.println("Nhập mô tả: ");
        System.out.println("1.Xe có tuyến riêng");
        System.out.println("2.Xe có một số tuyến");
        System.out.println("3.Xe liên tuyến");
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
                    this.setDescription(Ticket.PRIVATE_LINE);
                    System.out.println("Tuyến riêng");
                    check = true;
                    break;
                case 2:
                    this.setDescription(Ticket.SOME_LINE);
                    System.out.println("Một số tuyến");
                    check = true;
                    break;
                case 3:
                    this.setDescription(Ticket.INTERLINE);
                    System.out.println("Liên tuyến");
                    check = true;
                    break;
                default:
                    System.out.println("Nhập sai! Hãy nhập từ 1 đến 3!");
                    check = false;
                    break;
            }
        } while (!check);
        Ticket.AUTO_ID++;
        System.out.println("Nhập giá bán: ");
        do {
            try {
                this.price = new Scanner(System.in).nextFloat();
                check = true;
            } catch (Exception e) {
                System.out.print("Không được nhập ký tự khác ngoài số! Nhập lại: ");
                check = false;
                continue;
            }
            if (this.price <= 0) {
                System.out.print("Giá bán không được âm! Nhập lại: ");
                check = false;
            }
        } while (!check);
    }

    @Override
    public String toString() {
        return "Ticket{" +
                "id=" + id +
                ", description='" + description + '\'' +
                ", price=" + price +
                '}';
    }
}
