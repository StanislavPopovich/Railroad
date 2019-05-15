package com.railroad.service.impl;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.railroad.dto.ticket.TicketDto;
import com.railroad.service.api.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.annotation.PostConstruct;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.mail.util.ByteArrayDataSource;
import java.io.*;
import java.util.List;
import java.util.stream.Stream;


/**
 * @author Stanislav Popovich
 */

@Service("mailService")
public class EmailServiceImpl implements EmailService {
    @Autowired
    private JavaMailSender mailSender;

    private static final String IMG = "src/main/webapp/resources/img/logo.PNG";
    private static final String NEW = "new";


    @Async
    @Override
    public void sendMail(List<TicketDto> tickets, String typeEmail) {
        if(typeEmail.equals(NEW)){
            String subject = "Your tickets" ;
            String ticket;
            if(tickets.size() < 2){
                ticket = "ticket";
            }else{
                ticket = "tickets";
            }
            String content = String.format("Dear %s %s, your %s in attachment",
                    tickets.get(0).getPassengerDto().getLastName(), tickets.get(0).getPassengerDto().getName(),
                    ticket);
            mailSender.send(getNewOrChangedMessage(subject, content, tickets));
        }
    }

    /**
     * Creating email
     * @param subject subject
     * @param content email content
     * @param tickets tickets
     * @return Message
     */
    private MimeMessage getNewOrChangedMessage(String subject, String content,  List<TicketDto> tickets){
        MimeMessage message = mailSender.createMimeMessage();
        try(ByteArrayOutputStream outputStream = new ByteArrayOutputStream()) {
            MimeBodyPart textBodyPart = new MimeBodyPart();
            textBodyPart.setText(content);
            writePdf(outputStream, tickets);
            byte[] bytes = outputStream.toByteArray();
            DataSource dataSource = new ByteArrayDataSource(bytes, "application/pdf");
            MimeBodyPart pdfBodyPart = new MimeBodyPart();
            pdfBodyPart.setDataHandler(new DataHandler(dataSource));
            pdfBodyPart.setFileName("tickets.pdf");

            MimeMultipart mimeMultipart = new MimeMultipart();
            mimeMultipart.addBodyPart(textBodyPart);
            mimeMultipart.addBodyPart(pdfBodyPart);
            message.setSubject(subject);
            message.setRecipient(Message.RecipientType.TO, new InternetAddress(tickets.get(1).getPassengerDto().getEmail()));
            message.setContent(mimeMultipart);

        } catch (MessagingException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return message;
    }

    private MimeMessage getDeleteMessage(String subject, String content, String email){
        MimeMessage message = mailSender.createMimeMessage();
        MimeBodyPart textBodyPart = new MimeBodyPart();
        try {
            textBodyPart.setText(content);
            MimeMultipart mimeMultipart = new MimeMultipart();
            mimeMultipart.addBodyPart(textBodyPart);
            message.setSubject(subject);
            message.setRecipient(Message.RecipientType.TO, new InternetAddress(email));
            message.setContent(mimeMultipart);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
        return message;
    }


    /**
     * Creating pdf
     * @param outputStream
     * @param tickets
     * @throws Exception
     */
    private void writePdf(OutputStream outputStream, List<TicketDto> tickets) throws Exception {
        Document document = new Document();
        PdfWriter.getInstance(document, outputStream);
        document.open();
        for(TicketDto ticket: tickets){
            PdfPTable headerTable = new PdfPTable(1);
            createHeaderFields(headerTable, ticket.getNumber().toString());
            PdfPTable passengerTable = new PdfPTable(3);
            createPassengerFields(passengerTable, ticket);
            PdfPTable ticketTable = new PdfPTable(4);
            createTicketFields(ticketTable, ticket);
            PdfPTable ticketTable1 = new PdfPTable(4);
            createTicketFields(ticketTable1, null);
            document.add(headerTable);
            document.add(passengerTable);
            document.add(ticketTable);
            document.add(ticketTable1);
        }
        document.close();
    }

    private void createHeaderFields(PdfPTable table, String number){
        Stream.of("TicketNumber")
                .forEach(columnTitle -> {
                    PdfPCell header = new PdfPCell();
                    header.setBackgroundColor(BaseColor.WHITE);
                    header.setBorderWidth(2);
                    header.setPhrase(new Phrase(columnTitle));
                    table.addCell(header);
                });
        table.addCell(number);
    }

    private void createPassengerFields(PdfPTable table, TicketDto ticketDto){
        Stream.of("Last name", "Name", "Birth date")
                .forEach(columnTitle -> {
                    PdfPCell header = new PdfPCell();
                    header.setBackgroundColor(BaseColor.WHITE);
                    header.setBorderWidth(2);
                    header.setPhrase(new Phrase(columnTitle));
                    table.addCell(header);
                });
        table.addCell(ticketDto.getPassengerDto().getLastName());
        table.addCell(ticketDto.getPassengerDto().getName());
        table.addCell(ticketDto.getPassengerDto().getBirthDate());
    }

    private void createTicketFields(PdfPTable table, TicketDto ticketDto){
        if(ticketDto != null){
            Stream.of("Departing station", "Departing date", "Arrival station", "Arrival date")
                    .forEach(columnTitle -> {
                        PdfPCell header = new PdfPCell();
                        header.setBackgroundColor(BaseColor.LIGHT_GRAY);
                        header.setBorderWidth(2);
                        header.setPhrase(new Phrase(columnTitle));
                        table.addCell(header);
                    });
            Stream.of(ticketDto.getTrainTicketDto().getDepartStation(),
                    ticketDto.getTrainTicketDto().getDepartDate(),
                    ticketDto.getTrainTicketDto().getArrivalStation(), ticketDto.getTrainTicketDto().getArrivalDate())
                    .forEach(columnTitle -> {
                        PdfPCell content = new PdfPCell();
                        content.setBackgroundColor(BaseColor.LIGHT_GRAY);
                        content.setBorderWidth(2);
                        content.setPhrase(new Phrase(columnTitle));
                        table.addCell(content);
                    });
        }else{

        }

    }



}
