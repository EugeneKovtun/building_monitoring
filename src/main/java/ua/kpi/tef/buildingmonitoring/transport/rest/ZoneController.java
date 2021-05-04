package ua.kpi.tef.buildingmonitoring.transport.rest;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.BarcodeQRCode;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.ByteArrayOutputStream;
import java.util.HashMap;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.UUID;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import ua.kpi.tef.buildingmonitoring.domain.Zone;
import ua.kpi.tef.buildingmonitoring.service.BuildingService;

@RestController
@RequestMapping("/zone")
@Validated
public class ZoneController {

    @Value("${building.monitoring.front-end.url}")
    private String frontEndBaseUrl;
    private final BuildingService buildingService;

    public ZoneController(BuildingService buildingService) {
        this.buildingService = buildingService;
    }


    @PostMapping
    @Validated
    public Zone createZone(@Valid @RequestBody Zone zone) throws Exception {
        return buildingService.createZone(zone);
    }

    @PutMapping("/{uuid}")
    public Zone updateZoneParameters(@RequestBody Zone zone, @PathVariable UUID uuid) throws Exception {
        try {
            return buildingService.updateZone(zone, uuid);
        } catch (NoSuchElementException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,
                    "Zone with UUID:" + uuid + " not found.");
        }
    }

    @GetMapping
    public List<Zone> getAllZones() {
        return buildingService.getAllZones();
    }


    @GetMapping("/{uuid}")
    public Zone getZone(@PathVariable UUID uuid) {
        return buildingService.getZone(uuid);
    }

    @GetMapping("/{uuid}/qr")
    public ResponseEntity<byte[]> getZoneQRCode(@PathVariable UUID uuid) throws Exception {
        Zone zone = buildingService.getZone(uuid);


        String linkToBeEncoded = frontEndBaseUrl + "/room/" + uuid;

        Document document = new Document();
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        PdfWriter.getInstance(document, byteArrayOutputStream);
        document.open();

        Font font = FontFactory.getFont(FontFactory.COURIER, 24, BaseColor.BLACK);
        document.add(new Chunk(zone.getName(), font));

        BarcodeQRCode qrCode = new BarcodeQRCode(linkToBeEncoded, 550, 550, new HashMap<>());
        Image image = qrCode.getImage();

        Paragraph paragraph = new Paragraph();
        paragraph.add(image);
        document.add(paragraph);
        document.close();

        return ResponseEntity.ok()
                .contentType(MediaType.APPLICATION_PDF)
                .body(byteArrayOutputStream.toByteArray());


    }
}
