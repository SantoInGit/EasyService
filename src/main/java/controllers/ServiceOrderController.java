package controllers;

import com.pdfjet.A4;
import com.pdfjet.Cell;
import com.pdfjet.Color;
import com.pdfjet.CoreFont;
import com.pdfjet.Font;
import com.pdfjet.PDF;
import com.pdfjet.Page;
import com.pdfjet.Table;
import com.pdfjet.TextLine;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import ejb.ServiceOrderEJB;
import entities.Customer;
import entities.ServiceOrder;
import entities.ServiceOrderItem;
import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.net.InetAddress;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import javax.faces.context.ExternalContext;

@Named(value = "serviceOrderController")
@RequestScoped
public class ServiceOrderController {

    public ServiceOrderController() {
    }
    @EJB
    private ServiceOrderEJB serviceOrderEJB;
    private static ServiceOrder serviceOrder = new ServiceOrder();
    private ServiceOrder serviceOrderBook = new ServiceOrder();
    private List<ServiceOrder> serviceOrderList = new ArrayList<>();
    private List<ServiceOrder> frontendServiceOrderList = new ArrayList<>();
    private List<String> staffid = new ArrayList<>();

    public List<String> getStaffid() {
        return staffid;
    }

    public ServiceOrder getServiceOrderBook() {
        return serviceOrderBook;
    }

    public void setServiceOrderBook(ServiceOrder serviceOrderBook) {
        this.serviceOrderBook = serviceOrderBook;
    }

    public List<ServiceOrder> getFrontendServiceOrderList() {
//        MailController sender = new MailController();
//        sender.sendSimpleMail();
        // GmailSender sender = new GmailSender();

//        try {
//            sender.setSender("info@noriskwebsolutions.com", "jagat@123");
//            sender.addRecipient("prajapatijagat2009@gmail.com");
//            sender.setSubject("The subject");
//            sender.setBody("The body");
//            // sender.addAttachment("TestFile.txt");
//            sender.send();
//        } catch (MessagingException ex) {
//            Logger.getLogger(ServiceOrderController.class.getName()).log(Level.SEVERE, null, ex);
//        }
        ExternalContext extContext = FacesContext.getCurrentInstance().getExternalContext();
        Map<String, Object> sessionMap = extContext.getSessionMap();
        Customer cst = (Customer) sessionMap.get("customer");

        frontendServiceOrderList = serviceOrderEJB.customerListServiceOrders(cst.getId());
        return frontendServiceOrderList;
    }

    public void setFrontendServiceOrderList(List<ServiceOrder> frontendServiceOrderList) {
        this.frontendServiceOrderList = frontendServiceOrderList;
    }

    public void setStaffid(List<String> staffid) {
        this.staffid = staffid;
    }
    private String search = "";
    private String searchBy = "";

    private int service_id;
    private String service_name;
    private String service_rate;
    private String customer_id;

    public String getServiceItemName(String s) {
        String serOrderItem = s;

        String[] serOrderItemArray = serOrderItem.split(",");
        return serOrderItemArray[0];
    }
    
    public String getServiceItemRate(String s) {
        String serOrderItem = s;

        String[] serOrderItemArray = serOrderItem.split(",");
        return serOrderItemArray[1];
    }

    public String getCustomer_id() {
        return customer_id;
    }

    public void setCustomer_id(String customer_id) {
        this.customer_id = customer_id;
    }

    public String getService_name() {
        return service_name;
    }

    public void setService_name(String service_name) {
        this.service_name = service_name;
    }

    public String getService_rate() {
        return service_rate;
    }

    public void setService_rate(String service_rate) {
        this.service_rate = service_rate;
    }

    public int getService_id() {
        return service_id;
    }

    public void setService_id(int service_id) {
        this.service_id = service_id;
    }

    public ServiceOrder getServiceOrder() {
        return serviceOrder;
    }

    public void setServiceOrder(ServiceOrder serviceOrder) {
        ServiceOrderController.serviceOrder = serviceOrder;
    }

    public List<ServiceOrder> getServiceOrderList() {
        if (this.search.isEmpty()) {
            serviceOrderList = serviceOrderEJB.listServiceOrders();
        }
        return serviceOrderList;
    }

    public void setServiceOrderList(List<ServiceOrder> serviceOrderList) {
        this.serviceOrderList = serviceOrderList;
    }

    public String getSearchBy() {
        return searchBy;
    }

    public void setSearchBy(String searchBy) {
        this.searchBy = searchBy;
    }

    public String getSearch() {
        return search;
    }

    public void setSearch(String search) {
        this.search = search;
    }

    public String doCreateServiceOrder() {

        serviceOrderBook = serviceOrderEJB.addServiceOrder(serviceOrderBook, service_id, service_name, service_rate, customer_id);
        FacesMessage infoMsg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Service Order Created Succefully.", "");
        FacesContext.getCurrentInstance().addMessage(null, infoMsg);
        return "frontendCustomerProfile.xhtml?faces-redirect=true";
    }

    public String doDeleteServiceOrder(Long id) {
        serviceOrderEJB.deleteServiceOrder(id);
        return "listServiceOrders.xhtml?faces-redirect=true";
    }

    public String doCreateInvoice(Long id) throws Exception {
        serviceOrder = serviceOrderEJB.createInvoice(id);

        String machine_name = InetAddress.getLocalHost().getHostName();
        String path_to_desktop = "C:/Documents and Settings/" + machine_name + "/Desktop/";
        PDF pdf = new PDF(
                new BufferedOutputStream(
                        new FileOutputStream(path_to_desktop + "invoice_for_order_no_" + serviceOrder.getServiceOrderId() + ".pdf")));
        DateFormat df = new SimpleDateFormat("dd/MM/yy");
        Date dateobj = new Date();
        Page page = new Page(pdf, A4.PORTRAIT);

        Font f1 = new Font(pdf, CoreFont.HELVETICA_BOLD);
        f1.setSize(7f);

        Font f2 = new Font(pdf, CoreFont.HELVETICA);
        f2.setSize(7f);

        Font f3 = new Font(pdf, CoreFont.HELVETICA_BOLD);
        f3.setSize(20f);

        Font f4 = new Font(pdf, CoreFont.HELVETICA_BOLD);
        f4.setSize(10f);

        TextLine text = new TextLine(f4, "Easy Services");
        text.setPosition(90, 60);
        // text.setColor(Color.dodgerblue);
        text.drawOn(page);

        TextLine text2 = new TextLine(f2,
                "400, Kent Street 2230");
        text2.setPosition(90, 70);
        //text2.setColor(Color.dodgerblue);
        text2.drawOn(page);

        TextLine text3 = new TextLine(f2,
                "Phone No:30596785");
        text3.setPosition(90, 80);
        //text3.setColor(Color.dodgerblue);
        text3.drawOn(page);

        TextLine text4 = new TextLine(f2,
                "Fax:456457");
        text4.setPosition(90, 90);
        //text4.setColor(Color.dodgerblue);
        text4.drawOn(page);

        TextLine text8 = new TextLine(f3,
                "INVOICE");
        text8.setPosition(280, 30);
        text8.setColor(Color.dodgerblue);
        text8.drawOn(page);

        TextLine text9 = new TextLine(f4,
                "Bill To");
        text9.setPosition(90, 110);
        //text9.setColor(Color.dodgerblue);
        text9.drawOn(page);

        TextLine payable = new TextLine(f2,
                "Notes:");
        payable.setPosition(90, 320);
        //payable.setColor(Color.dodgerblue);
        payable.drawOn(page);

        TextLine allpay = new TextLine(f2,
                "1. Make all amount payable to Easy Services");
        allpay.setPosition(90, 330);
        //allpay.setColor(Color.dodgerblue);
        allpay.drawOn(page);

        TextLine totaldue = new TextLine(f2,
                "2. Total amount due in 30 days");
        totaldue.setPosition(90, 340);
        //totaldue.setColor(Color.dodgerblue);
        totaldue.drawOn(page);

        TextLine include = new TextLine(f2,
                "3. Include your invoice number on the cheque");
        include.setPosition(90, 350);
        // include.setColor(Color.dodgerblue);
        include.drawOn(page);

        TextLine contact = new TextLine(f2,
                "If you have any question about this invoice please email us at: info@easyservice.com");
        contact.setPosition(180, 400);
        // contact.setColor(Color.dodgerblue);
        contact.drawOn(page);

        TextLine thankyou = new TextLine(f1,
                "THANK YOU FOR YOUR BUSINESS!!");
        thankyou.setPosition(250, 420);
        //thankyou.setColor(Color.dodgerblue);
        thankyou.drawOn(page);

        TextLine copyright = new TextLine(f2,
                "Copyright EasyService 2016");
        copyright.setPosition(265, 430);
        //copyright.setColor(Color.dodgerblue);
        copyright.drawOn(page);

        // dynamic variables
        TextLine text5 = new TextLine(f2,
                "Date:" + dateobj);
        text5.setPosition(450, 60);
        //text5.setColor(Color.dodgerblue);
        text5.drawOn(page);

        TextLine text6 = new TextLine(f2,
                "INVOICE NO:" + serviceOrder.getServiceOrderId());
        text6.setPosition(450, 70);
        //text6.setColor(Color.dodgerblue);
        text6.drawOn(page);

        TextLine text7 = new TextLine(f2,
                "CUSTOMER NO:" + serviceOrder.getCustomer().getId());
        text7.setPosition(450, 80);
        //text7.setColor(Color.dodgerblue);
        text7.drawOn(page);

        TextLine text10 = new TextLine(f2, serviceOrder.getCustomer().getFirstName());
        text10.setPosition(90, 120);
        //text10.setColor(Color.dodgerblue);
        text10.drawOn(page);

        TextLine text11 = new TextLine(f2,
                "Phone No:" + serviceOrder.getCustomer().getPhoneNo());
        text11.setPosition(90, 130);
        //text11.setColor(Color.dodgerblue);
        text11.drawOn(page);

        TextLine text12 = new TextLine(f2,
                "Address:" + serviceOrder.getCustomer().getAddress().getStreet() + " " + serviceOrder.getCustomer().getAddress().getStreet() + " " + serviceOrder.getCustomer().getAddress().getZipCode());
        text12.setPosition(90, 140);
        //text12.setColor(Color.dodgerblue);
        text12.drawOn(page);

        List<List<Cell>> tableData = new ArrayList<List<Cell>>();

        List<Cell> row = new ArrayList<Cell>();
        Cell cell = new Cell(f1, "OrderID");
        cell.setTopPadding(5f);
        cell.setBottomPadding(5f);
        row.add(cell);
        
        cell = new Cell(f1, "Description");
        cell.setTopPadding(5f);
        cell.setBottomPadding(5f);
        row.add(cell);

        cell = new Cell(f1, "Start/End date");
        cell.setTopPadding(5f);
        cell.setBottomPadding(5f);
        row.add(cell);

        cell = new Cell(f1, "Total Hour");
        cell.setTopPadding(5f);
        cell.setBottomPadding(5f);
        row.add(cell);

        cell = new Cell(f1, "Rate");
        cell.setTopPadding(5f);
        cell.setBottomPadding(5f);
        row.add(cell);

        cell = new Cell(f1, "Total Amount");
        cell.setTopPadding(5f);
        cell.setBottomPadding(5f);
        row.add(cell);

        tableData.add(row);
        row = new ArrayList<Cell>();
        cell = new Cell(f2, getServiceItemName(serviceOrder.getServiceOrderId().toString()));
        cell.setTopPadding(5f);
        cell.setBottomPadding(5f);
        row.add(cell);
        
        cell = new Cell(f2, getServiceItemName(serviceOrder.getServiceOrderItem().get(0).toString()));
        cell.setTopPadding(5f);
        cell.setBottomPadding(5f);
        row.add(cell);

        cell = new Cell(f2, serviceOrder.getFromDate()+"  /  "+serviceOrder.getToDate());
        cell.setTopPadding(5f);
        cell.setBottomPadding(5f);
        row.add(cell);
        String dateStart = serviceOrder.getFromDate();
        String dateStop = serviceOrder.getToDate();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");

        Date d1 = null;
        Date d2 = null;
        d1 = format.parse(dateStart);
        d2 = format.parse(dateStop);

        long diff = d2.getTime() - d1.getTime();
        long diffDays = diff / (24 * 60 * 60 * 1000);
        long totalhour = diffDays * Integer.parseInt(serviceOrder.getHoursPerDay()) * Integer.parseInt(serviceOrder.getStaffRequired());
        cell = new Cell(f2, String.valueOf(totalhour));
        cell.setTopPadding(5f);
        cell.setBottomPadding(5f);
        row.add(cell);

        String rate = getServiceItemRate(serviceOrder.getServiceOrderItem().get(0).toString());
        cell = new Cell(f2, rate);
        cell.setTopPadding(5f);
        cell.setBottomPadding(5f);
        row.add(cell);
        
             
        Float totalPrice = Float.parseFloat(String.valueOf(totalhour)) * Float.parseFloat(rate);
        cell = new Cell(f2, String.valueOf(totalPrice));
        cell.setTopPadding(5f);
        cell.setBottomPadding(5f);
        row.add(cell);
        tableData.add(row);

        row = new ArrayList<Cell>();
        cell = new Cell(f2, "");
        cell.setTopPadding(5f);
        cell.setBottomPadding(5f);
        row.add(cell);
        cell = new Cell(f2, "");
        cell.setTopPadding(5f);
        cell.setBottomPadding(5f);
        row.add(cell);
        cell = new Cell(f2, "");
        cell.setTopPadding(5f);
        cell.setBottomPadding(5f);
        row.add(cell);
        cell = new Cell(f2, "");
        cell.setTopPadding(5f);
        cell.setBottomPadding(5f);
        row.add(cell);
        cell = new Cell(f2, "");
        cell.setTopPadding(5f);
        cell.setBottomPadding(5f);
        row.add(cell);
        cell = new Cell(f2, "");
        cell.setTopPadding(5f);
        cell.setBottomPadding(5f);
        row.add(cell);
        tableData.add(row);

        row = new ArrayList<Cell>();
        cell = new Cell(f2, "");
        cell.setTopPadding(5f);
        cell.setBottomPadding(5f);
        row.add(cell);
        cell = new Cell(f2, "");
        cell.setTopPadding(5f);
        cell.setBottomPadding(5f);
        row.add(cell);
        cell = new Cell(f2, "");
        cell.setTopPadding(5f);
        cell.setBottomPadding(5f);
        row.add(cell);
         cell = new Cell(f2, "");
        cell.setTopPadding(5f);
        cell.setBottomPadding(5f);
        row.add(cell);
        cell = new Cell(f2, "Total Due");
        cell.setTopPadding(5f);
        cell.setBottomPadding(5f);
        row.add(cell);
        cell = new Cell(f2,String.valueOf(totalPrice));
        cell.setTopPadding(5f);
        cell.setBottomPadding(5f);
        row.add(cell);
        tableData.add(row);

        Table table = new Table();
        table.setData(tableData, Table.DATA_HAS_1_HEADER_ROWS);
        table.setLocation(90f, 170f);
        table.setColumnWidth(0,50f);
        table.setColumnWidth(1, 70f);
        table.setColumnWidth(2, 100f);
        table.setColumnWidth(3, 70f);
        table.setColumnWidth(4, 70f);

        table.setColumnWidth(5, 100f);
        table.wrapAroundCellText();
        // table.wrapAroundCellText2();

        int numOfPages = table.getNumberOfPages(page);
        while (true) {
            table.drawOn(page);
            if (!table.hasMoreData()) {
                break;
            }
            page = new Page(pdf, A4.PORTRAIT);
            table.setLocation(200f, 200f);
        }

        pdf.close();

        return "listServiceOrders.xhtml?faces-redirect=true";
    }

    public String doChangeStatusServiceOrder(Long id, String status) {
        serviceOrderEJB.changeStatusServiceOrder(id, status);
        return "listServiceOrders.xhtml?faces-redirect=true";
    }

    public String frontendDoChangeStatusServiceOrder(Long id, String status) {
        serviceOrderEJB.changeStatusServiceOrder(id, status);
        return "customerMyOrders.xhtml?faces-redirect=true";
    }

    public String doRescheduleServiceOrder(Long id) {
        serviceOrder = serviceOrderEJB.rescheduleServiceOrder(id);
        return "cutomerRescheduleOrder.xhtml?faces-redirect=true";
    }

    public String doRescheduleServiceOrderCommit(Long id) {
        serviceOrder = serviceOrderEJB.rescheduleServiceOrderCommit(serviceOrder, id);
        return "customerMyOrders.xhtml";
    }

    public String doConfirmServiceOrder(Long id) {
        serviceOrder = serviceOrderEJB.confirmServiceOrder(id);
        return "confirmServiceOrder.xhtml?faces-redirect=true";
    }

    public String doConfirmServiceOrderCommit(Long id) {
        serviceOrder = serviceOrderEJB.confirmServiceOrderCommit(id, staffid);
        return "listServiceOrders.xhtml?faces-redirect=true";
    }

    public String doSearch() {
        serviceOrderList = serviceOrderEJB.search(search, searchBy);
        FacesMessage infoMsg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Search result for: " + search, "");
        FacesContext.getCurrentInstance().addMessage(null, infoMsg);
        return "listServiceOrders.xhtml";
    }

    public int doSearchServiceOrderByStatusForDashboard(String status, String searchBy) {
        return serviceOrderEJB.search(status, searchBy).size();
    }

}
