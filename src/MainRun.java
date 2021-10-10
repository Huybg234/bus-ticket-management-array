import entity.Passenger;
import entity.Ticket;
import service.TicketSale;

import java.util.Scanner;

public class MainRun {
    private static int countPassenger;
    private static Passenger[] passengers;
    private static Ticket[] tickets;
    private static TicketSale[] ticketSales;

    public static void main(String[] args) {
        menu();
    }

    private static void menu() {
        do {
            int functionChoice = functionChoice();
            switch (functionChoice) {
                case 1:
                    createNewPassenger();
                    break;
                case 2:
                    createNewTicket();
                    break;
                case 3:
                    ticketSaleList();
                    break;
                case 4:
                    sortTicketSaleList();
                    break;
                case 5:
                    calculatorTicketSale();
                    break;
                case 6:
                    System.exit(0);
            }

        } while (true);
    }

    private static void calculatorTicketSale() {
        if (ticketSales == null || ticketSales.length == 0) {
            System.out.println("Bạn cần nhập danh sách bạn đọc và đầu sách trước khi cho mượn!");
            return;
        }
        float price;
        for (TicketSale ticketSale : ticketSales) {
            if (ticketSale.getPassenger().getPassengerType().equals(Passenger.STUDENT)) {
                price = ticketSale.getTicketPrice() * 50 / 100;
            } else if (ticketSale.getPassenger().getPassengerType().equals(Passenger.INDUSTRY_Staff)) {
                price = ticketSale.getTicketPrice() * 70 / 100;
            } else price = ticketSale.getTicketPrice();
            System.out.println(price);
        }
    }

    private static void sortTicketSaleList() {
        boolean check = true;
        if (ticketSales == null || ticketSales.length == 0) {
            System.out.println("Bạn cần nhập danh sách bạn đọc và đầu sách trước khi cho mượn!");
            return;
        }
        do {
            int sortChoice = 0;
            System.out.println("---------- SẮP XẾP DANH SÁCH THỐNG KÊ BÁN VÉ ---------");
            System.out.println("1. Theo tên hành khách.");
            System.out.println("2. Theo số lượng loại vé bán.");
            System.out.println("3. Thoát chức năng sắp xếp.");
            System.out.print("Xin mời chọn chức năng: ");
            do {
                try {
                    sortChoice = new Scanner(System.in).nextInt();
                    check = true;
                } catch (Exception e) {
                    System.out.println("Không được nhập ký tự khác ngoài số! Nhập lại: ");
                    check = false;
                    continue;
                }
                if (sortChoice < 1 || sortChoice > 3) {
                    System.out.print("Nhập trong khoảng từ 1 đến 3! Nhập lại: ");
                    check = false;
                }
            } while (!check);
            switch (sortChoice) {
                case 1:
                    sortBorrowListByPassenderName();
                    break;
                case 2:
                    sortBorrowListByTicketAmount();
                    break;
                case 3:
                    return;
            }
        } while (true);
    }

    private static void sortBorrowListByTicketAmount() {
        for (int i=0; i<ticketSales.length;i++){
            for (int j=i+1; j<ticketSales.length;j++){
                if (ticketSales[i].getTicketNumber()>ticketSales[j].getTicketNumber()){
                    TicketSale tmp = ticketSales[i];
                    ticketSales[i] = ticketSales[j];
                    ticketSales[j] = tmp;
                }
            }
        }
        for (TicketSale ticketSale : ticketSales){
            System.out.println(ticketSale);
        }
    }

    private static void sortBorrowListByPassenderName() {
        for (int i=0; i<ticketSales.length;i++){
            for (int j=i+1; j<ticketSales.length;j++){
                if (ticketSales[i].getPassenger().getName().compareTo(ticketSales[j].getPassenger().getName())>0){
                    TicketSale tmp = ticketSales[i];
                    ticketSales[i] = ticketSales[j];
                    ticketSales[j] = tmp;
                }
            }
        }
        for (TicketSale ticketSale : ticketSales){
            System.out.println(ticketSale);
        }
    }

    public static boolean isValidPassengerAndTicket() {
        return passengers != null && tickets != null && passengers.length != 0 && tickets.length != 0;
    }

    private static void ticketSaleList() {
        if (!isValidPassengerAndTicket()) {
            System.out.println("Bạn cần nhập hành khách và vé trước khi thống kê! ");
            return;
        }
        ticketSales = new TicketSale[countPassenger];
        float sumPrice;
        for (int i = 0; i < passengers.length; i++) {
            sumPrice = 0;
            int count = 0;
            System.out.println("Thống kê cho hành khách " + passengers[i].getName());
            System.out.println("Nhập số loại vé mà hành khách " + passengers[i].getName() + " muốn mua: ");
            int ticketTypeNumber = 0;
            boolean check = true;
            do {
                try {
                    ticketTypeNumber = new Scanner(System.in).nextInt();
                    check = true;
                } catch (Exception e) {
                    System.out.println("Không được nhập ký tự khác ngoài số! Nhập lại: ");
                    check = false;
                    continue;
                }
                if (ticketTypeNumber <= 0 || ticketTypeNumber > tickets.length) {
                    System.out.print("Số loại vé khách muốn mua phải lớn hơn 0 và nhỏ hơn tổng loại vé! Nhập lại: ");
                    check = false;
                }
            } while (!check);
            Ticket[] ticketList = new Ticket[ticketTypeNumber];
            float price;
            for (int j = 0; j < ticketTypeNumber; j++) {
                price = 0;
                System.out.println("Nhập id của loại vé thứ " + (j + 1) + " mà khách hàng " + passengers[i].getName() + " muốn mua");
                int tmpId;
                do {
                    try {
                        tmpId = new Scanner(System.in).nextInt();
                        check = true;
                    } catch (Exception e) {
                        System.out.println("Không được nhập ký tự khác ngoài số! Nhập lại: ");
                        check = false;
                        continue;
                    }
                    Ticket ticket = searchTicket(tmpId);
                    int total = 0;
                    if (ticket != null) {
                        System.out.println("Nhập số lượng vé hành khách muốn mua của loại vé " + ticket.getDescription());
                        do {
                            try {
                                total = new Scanner(System.in).nextInt();
                                check = true;
                            } catch (Exception e) {
                                System.out.println("Không được nhập ký tự khác ngoài số! Nhập lại: ");
                                check = false;
                                continue;
                            }
                            if (total != 1) {
                                System.out.println("Số lượng vé mua 1 loại không lớn hơn 1! nhập lại: ");
                                check = false;
                            }
                        } while (!check);
                        price = total*tickets[j].getPrice();
                        sumPrice += price;
                        ticketList[j] = ticket;
                        count++;
                        break;
                    }
                    System.out.print("Không có vé nào có ID vừa nhập, vui lòng nhập lại: ");
                } while (true);
            }
            TicketSale ticketSale = new TicketSale(passengers[i],ticketList,count);
            ticketSales[i] = ticketSale;
            ticketSales[i].setTicketPrice(sumPrice);
        }
        for (TicketSale ticketSale: ticketSales){
            System.out.println(ticketSale);
        }
    }

    private static Ticket searchTicket(int tmpId) {
        for (Ticket ticket : tickets) {
            if (ticket.getId() == tmpId) {
                return ticket;
            }
        }
        return null;
    }

    private static void createNewTicket() {
        System.out.println("Nhập số lượng vé: ");
        boolean check = true;
        int countTicket = 0;
        do {
            try {
                countTicket = new Scanner(System.in).nextInt();
                check = true;
            } catch (Exception e) {
                System.out.println("Không được nhập ký tự khác ngoài số! Nhập lại: ");
                check = false;
                continue;
            }
            if (countTicket <= 0) {
                System.out.print("Số lượng vé phải lớn hơn 0! Nhập lại: ");
                check = false;
            }
        } while (!check);
        tickets = new Ticket[countTicket];
        for (int i = 0; i < tickets.length; i++) {
            Ticket ticket = new Ticket();
            ticket.inputTicketInfo();
            tickets[i] = ticket;
        }
        for (Ticket ticket : tickets) {
            System.out.println(ticket);
        }
    }

    private static void createNewPassenger() {
        System.out.println("Nhập số lượng hành khách: ");
        boolean check = true;
        do {
            try {
                countPassenger = new Scanner(System.in).nextInt();
                check = true;
            } catch (Exception e) {
                System.out.println("Không được nhập ký tự khác ngoài số! Nhập lại: ");
                check = false;
                continue;
            }
            if (countPassenger <= 0) {
                System.out.print("Số lượng hành khách phải lớn hơn 0! Nhập lại: ");
                check = false;
            }
        } while (!check);
        passengers = new Passenger[countPassenger];
        for (int i = 0; i < passengers.length; i++) {
            Passenger passenger = new Passenger();
            passenger.inputInfo();
            passengers[i] = passenger;
        }
        for (Passenger passenger : passengers) {
            System.out.println(passenger);
        }
    }

    private static int functionChoice() {
        System.out.println("--------Quản lý bán vé tháng xe buýt--------");
        System.out.println("1.Nhập danh sách hành khách");
        System.out.println("2.Nhập danh sách loại vé");
        System.out.println("3.Bảng thống kê bán vé");
        System.out.println("4.Sắp xếp bảng thống kê bán vé");
        System.out.println("5.Tính tiền phải trả cho mỗi khách hàng");
        System.out.println("6.Thoát");
        int functionChoice = 0;
        boolean checkFunction = true;
        do {
            try {
                functionChoice = new Scanner(System.in).nextInt();
                checkFunction = true;
            } catch (Exception e) {
                System.out.println("Không được nhập ký tự khác ngoài số! Nhập lại: ");
                checkFunction = false;
                continue;
            }
            if (functionChoice <= 0 || functionChoice > 6) {
                System.out.print("Nhập số trong khoảng từ 1 đến 6! Nhập lại: ");
                checkFunction = false;
            }
        } while (!checkFunction);
        return functionChoice;
    }
}
