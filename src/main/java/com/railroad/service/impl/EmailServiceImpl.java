package com.railroad.service.impl;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.Image;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.railroad.dto.TicketDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.mail.MessagingException;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.util.ByteArrayDataSource;
import java.io.ByteArrayOutputStream;
import java.io.OutputStream;
import java.util.stream.Stream;

@Service("mailService")
public class EmailServiceImpl {
    @Autowired
    private JavaMailSender mailSender;

    public void sendMail(TicketDto ticketDto) {
        MimeMessage message = mailSender.createMimeMessage();
        String departStation = ticketDto.getTrainTicketDto().getDepartStation();
        String arrivalStation = ticketDto.getTrainTicketDto().getArrivalStation();
        String subject = "Your ticket from " + departStation + " to "
                + arrivalStation ;
        try( ByteArrayOutputStream outputStream = new ByteArrayOutputStream()) {
            MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(message, true);
            writePdf(outputStream, ticketDto);
            byte[] bytes = outputStream.toByteArray();
            DataSource dataSource = new ByteArrayDataSource(bytes, "application/pdf");
            MimeBodyPart pdfBodyPart = new MimeBodyPart();
            pdfBodyPart.setDataHandler(new DataHandler(dataSource));
            pdfBodyPart.setFileName("test.pdf");

            mimeMessageHelper.setTo(ticketDto.getEmail());
            mimeMessageHelper.setSubject(subject);
            mimeMessageHelper.setText("Dear " + ticketDto.getPassengerDto().getLastName() + " "
                    + ticketDto.getPassengerDto().getName() + ", your ticket in attachment");
            mimeMessageHelper.addAttachment("ticket", dataSource);

        } catch (MessagingException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }

        mailSender.send(message);
    }

    private void writePdf(OutputStream outputStream, TicketDto ticketDto) throws Exception {
        Document document = new Document();
        PdfWriter.getInstance(document, outputStream);
        document.open();
        PdfPTable passengerTable = new PdfPTable(3);
        createPassengerFields(passengerTable, ticketDto);
        PdfPTable ticket = new PdfPTable(4);
        createTicketFields(ticket, ticketDto);
        document.add(passengerTable);
        document.add(ticket);
        document.close();
    }

    private void createPassengerFields(PdfPTable table, TicketDto ticketDto){
        Stream.of("Last name", "name", "birth date")
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
