package org.example.service;

import org.example.Controller.ClientController;
import org.example.Service.InvoiceService;
import org.example.models.Client;
import org.example.models.Invoice;
import org.junit.jupiter.api.Test;

import java.text.ParseException;


import static org.junit.jupiter.api.Assertions.*;


class FiltroDeFaturasServiceTest {

    @Test
    public void create_client() throws ParseException {
        Client client = new Client("Armando", "07/06/2022", "Paraíba");
        assertEquals("Armando", client.getName());
        assertEquals("Paraíba", client.getState());
    }

    @Test
    public void create_invoice() throws ParseException {
        ClientController clientController = new ClientController();
        String idClient = clientController.insert(new Client("Armando", "07/06/2022", "Paraíba"));

        Invoice inv = new Invoice("INVOICE XXX", 250, "08/06/2022", idClient);

        assertEquals("INVOICE XXX", inv.getInvoice());
        assertEquals(2500, inv.getValue());
        assertEquals("Armando", clientController.findById(inv.getClient()).getName());
    }


    @Test
    public void minimun_twok() throws ParseException {
        ClientController clientController = new ClientController();
        String idClient = clientController.insert(new Client("Armando", "07/05/2022", "Paraíba"));
        InvoiceService invoiceService = new InvoiceService(clientController);
        Invoice[] inv = {new Invoice("INVOICE XXXX", 250, "10/10/2022", idClient)};
        assertEquals(0, invoiceService.filterInvoices(inv).size());

    }

    @Test
    public void between_twok_and_twokandhalf_date_more_month() throws ParseException {
        ClientController clientController = new ClientController();
        String idClient = clientController.insert(new Client("Armando", "12/06/2022", "Paraíba"));
        InvoiceService service = new InvoiceService(clientController);
        Invoice[] invoices = {new Invoice("INVOICE XXXX", 2200, "07/05/2022", idClient),
                new Invoice("INVOICE XXXX", 2430, "07/05/2022", idClient)};
        assertEquals(1, service.filterInvoices(invoices).size());

    }

    @Test
    public void twokhalf_three_cliennt_more_two_month() throws ParseException {
        ClientController clientController = new ClientController();
        String idClient = clientController.insert(new Client("Armando", "12/06/2022", "Paraíba"));
        InvoiceService service = new InvoiceService(clientController);
        Invoice[] invoices = {new Invoice("INVOICE XXXX", 2300, "10/09/2022", idClient),
                new Invoice("INVOICE XXXX", 2500, "10/06/2022", idClient)};
        assertEquals(0, service.filterInvoices(invoices).size());
    }


    @Test
    public void filter_above_fourk_south() throws ParseException {
        ClientController clientController = new ClientController();
        String idClient = clientController.insert(new Client("Armando", "12/04/2022", "Paraíba"));
        String idClient2 = clientController.insert(new Client("Sousa", "30/01/2022", "Paraná"));
        InvoiceService service = new InvoiceService(clientController);

        Invoice[] invoices = {new Invoice("INVOICE XXXX", 2300, "10/09/2022", idClient),
                new Invoice("INVOICE XXXX", 4900, "10/06/2022", idClient2)};

        assertEquals(1, service.filterInvoices(invoices).size());

    }




}