package com.railroad.service.impl;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.railroad.dto.ticket.TicketDto;
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
import java.util.stream.Stream;

@Service("mailService")
public class EmailService{
    @Autowired
    private JavaMailSender mailSender;

    public static final String IMG = "src/main/webapp/resources/img/logo.PNG";

    @Async
    public void sendMail(TicketDto ticketDto, String typeEmail) {
        String departStation = ticketDto.getTrainTicketDto().getDepartStation();
        String arrivalStation = ticketDto.getTrainTicketDto().getArrivalStation();
        String subject;
        String content;
        String pdfName = "ticket№" + ticketDto.getNumber() + ".pdf";
        if(typeEmail.equals("new")){
            subject = "Your ticket from " + departStation + " to "
                    + arrivalStation ;
            content = "Dear " + ticketDto.getPassengerDto().getLastName() + " "
                    + ticketDto.getPassengerDto().getName() + ", your ticket in attachment";
            mailSender.send(getNewOrChangedMessage(subject, content, pdfName, ticketDto));
        }else if(typeEmail.equals("changed")){
            subject = "Your ticket from " + departStation + " to "
                    + arrivalStation + " has been changed";
            content = "Dear " + ticketDto.getPassengerDto().getLastName() + " "
                    + ticketDto.getPassengerDto().getName() + ", your new ticket in attachment";
            mailSender.send(getNewOrChangedMessage(subject, content, pdfName, ticketDto));
        }else{
            subject = "Your train from " + departStation + " to "
                    + arrivalStation + " has been canceled";
            content = "Dear " + ticketDto.getPassengerDto().getLastName() + " "
                    + ticketDto.getPassengerDto().getName() + ", your train from " + departStation + " to "
                    + arrivalStation + " has been canceled";
            mailSender.send(getDeleteMessage(subject, content, ticketDto.getPassengerDto().getEmail()));
        }
    }

    private MimeMessage getNewOrChangedMessage(String subject, String content, String pdfName, TicketDto ticketDto){
        MimeMessage message = mailSender.createMimeMessage();
        try(ByteArrayOutputStream outputStream = new ByteArrayOutputStream()) {
            MimeBodyPart textBodyPart = new MimeBodyPart();
            textBodyPart.setText(content);
            writePdf(outputStream, ticketDto);
            byte[] bytes = outputStream.toByteArray();
            DataSource dataSource = new ByteArrayDataSource(bytes, "application/pdf");
            MimeBodyPart pdfBodyPart = new MimeBodyPart();
            pdfBodyPart.setDataHandler(new DataHandler(dataSource));
            pdfBodyPart.setFileName(pdfName);

            MimeMultipart mimeMultipart = new MimeMultipart();
            mimeMultipart.addBodyPart(textBodyPart);
            mimeMultipart.addBodyPart(pdfBodyPart);
            message.setSubject(subject);
            message.setRecipient(Message.RecipientType.TO, new InternetAddress(ticketDto.getPassengerDto().getEmail()));
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



    private void writePdf(OutputStream outputStream, TicketDto ticketDto) throws Exception {
        Document document = new Document();
        PdfWriter.getInstance(document, outputStream);
        document.open();
       /* document.add(getLogo());*/
        document.add(createHead(ticketDto.getNumber()));
        PdfPTable passengerTable = new PdfPTable(3);
        createPassengerFields(passengerTable, ticketDto);
        PdfPTable ticket = new PdfPTable(4);
        createTicketFields(ticket, ticketDto);
        document.add(passengerTable);
        document.add(ticket);
        document.close();
    }
    private Image getLogo() throws IOException, BadElementException {
        return Image.getInstance(IMG);
    }
    private Chunk createHead(Long ticketNumber){
        Font font = FontFactory.getFont(FontFactory.COURIER, 16, BaseColor.BLACK);
        String head = "Ticket №" + ticketNumber;
        return new Chunk(head, font);
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
        Stream.of("Departing station", "Departing date", "Arrival station", "Arrival date")
                .forEach(columnTitle -> {
                    PdfPCell header = new PdfPCell();
                    header.setBackgroundColor(BaseColor.LIGHT_GRAY);
                    header.setBorderWidth(2);
                    header.setPhrase(new Phrase(columnTitle));
                    table.addCell(header);
                });

        table.addCell(ticketDto.getTrainTicketDto().getDepartStation());
        table.addCell(ticketDto.getTrainTicketDto().getDepartDate());
        table.addCell(ticketDto.getTrainTicketDto().getArrivalStation());
        table.addCell(ticketDto.getTrainTicketDto().getArrivalDate());
    }



}
