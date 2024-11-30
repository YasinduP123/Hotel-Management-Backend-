package lk.icet.hotel.service.impl;

import com.itextpdf.kernel.geom.PageSize;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.properties.HorizontalAlignment;
import com.itextpdf.layout.properties.TextAlignment;
import com.itextpdf.layout.properties.VerticalAlignment;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import lk.icet.hotel.dto.Billing;
import lk.icet.hotel.entity.BillingEntity;
import lk.icet.hotel.repository.BillingRepository;
import lk.icet.hotel.repository.BookingRepository;
import lk.icet.hotel.service.BillingService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
@Slf4j
public class BillingServiceImpl implements BillingService {

	final ModelMapper mapper;
	final BillingRepository billingRepository;
	final BookingRepository bookingRepository;
	final EntityManager entityManager;

	@Override
	@Transactional
	public void saveBill(Billing billing) throws IOException {
		BillingEntity save = billingRepository.save(mapper.map(billing, BillingEntity.class));

		if(save.getBookingId() != null) {
			String name = "Invoice_" + billing.getBookingId();
			String path = "E:\\Project_Intelij\\Frame Works (Spring Boot)\\Hotel\\Hotel_Management_System\\src\\main\\resources\\Invoices\\" + name;


				PdfWriter pdfWriter = new PdfWriter(path);
				PdfDocument pdfDocument = new PdfDocument(pdfWriter);
				pdfDocument.setDefaultPageSize(PageSize.A4);

				Document document = new Document(pdfDocument);

				document.add(new Paragraph("Hotel Management System")
								.setTextAlignment(TextAlignment.CENTER))
						.setFontSize(18);
				document.add(new Paragraph("INVOICE")
						.setTextAlignment(TextAlignment.CENTER)
						.setFontSize(14));
				document.add(new Paragraph("\n"));

				Table table = new Table(2);
				table.setVerticalAlignment(VerticalAlignment.MIDDLE);
				table.setHorizontalAlignment(HorizontalAlignment.CENTER);

				table.addCell("Booking ID ");
				table.addCell(billing.getBookingId().toString());

				table.addCell("Date " );
				table.addCell(billing.getDate().toString());

				table.addCell("Amount " );
				table.addCell(billing.getAmount());

				table.addCell("Payment Status ");
				table.addCell(billing.getPaymentStatus());


				document.add(table);

				document.add(new Paragraph("\nThank you for your business!")
						.setTextAlignment(TextAlignment.CENTER)
						.setFontSize(10));

				document.close();
				log.info("Invoice generated successfully at: " + path);

		}

			bookingRepository.deleteById(billing.getBookingId());
	}

	@Override
	public List<Billing> getAll() {
		List<Billing> billings = new ArrayList<>();
		billingRepository.findAll().forEach(billingEntity -> billings.add(mapper.map(billingEntity, Billing.class)));
		return billings;
	}

	@Override
	public List<Billing> findByBookingId(Long bookingId) {
		List<Billing> billingList = new ArrayList<>();
		billingRepository.findByBookingId(bookingId).forEach(billingEntity -> billingList.add(mapper.map(billingEntity, Billing.class)));
		return billingList;
	}

	@Override
	public List<Billing> findByBillingId(Long billingId) {
		List<Billing> billingList = new ArrayList<>();
		billingList.add(mapper.map(billingRepository.findById(billingId), Billing.class));
		return billingList;
	}


}
