package edu.icet.entity.event;

import edu.icet.util.SupplierType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.OffsetDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
@Table(name = "agenda_task")
public class AgendaTaskEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long taskId;

    @Column(nullable = false)
    private String taskName;

    @Column(name = "start_time")
    private OffsetDateTime startTime;

    @Column(name = "end_time")
    private OffsetDateTime endTime;

    @Column(name = "supplier_id")
    private Long supplierId;

    @Enumerated(EnumType.STRING)
    @Column(name = "supplier_type")
    private SupplierType supplierType;

}


//Event(
// id=null,
// userId=1,
// eventDate=Thu May 08 17:30:00 IST 2025,
// startTime=14:00:00, endTime=16:00:00,
// venue=Venue(venueId=null, capacity=300,
// location=Location(
// locationId=100,
// city=Colombo,
// village=Nugegoda
// ),
// venueType=RESTAURANT,
// properties=[],
// requests=[]
// ),
// location=Location(
// locationId=null,
// city=Colombo,
// village=Nugegoda
// ),
// eventType=WEDDING,
// capacity=200,
// budgetType=MEDIUM,
// eventStatus=SCHEDULED,
// suppliers=[
// Supplier(
// id=null,
// businessName=Elite Catering,
// businessContactNumber=0771234567,
// businessEmail=catering@example.com,
// description=Professional catering for events,
// availability=true,
// location=Location(
// locationId=null,
// city=Colombo,
// village=Nugegoda
// ),
// profileImage=null,
// category=CATERING,
// beautySaloon=null,
// catering=null,
// venue=null,
// music=null,
// requests=null,
// packages=null,
// previousWorks=null,
// inventories=[
// Inventory(inventoryID=null,
// imageURL=https://example.com/chairs.jpg,
// description=Plastic chairs,
// supplierCategory=FURNITURE)]), Supplier(id=null, businessName=Sound Solutions, businessContactNumber=0777654321, businessEmail=sound@example.com, description=Sound system rentals for events, availability=true, location=Location(locationId=null, city=Colombo, village=Nugegoda), profileImage=null, category=MUSIC, beautySaloon=null, catering=null, venue=null, music=null, requests=null, packages=null, previousWorks=null, inventories=[Inventory(inventoryID=null, imageURL=https://example.com/sound-system.jpg, description=High-quality sound system, supplierCategory=MUSIC)]), Supplier(id=null, businessName=Elite Photography, businessContactNumber=0779988777, businessEmail=photography@example.com, description=Professional photography services for weddings, availability=true, location=Location(locationId=null, city=Colombo, village=Nugegoda), profileImage=null, category=PHOTOGRAPHY, beautySaloon=null, catering=null, venue=null, music=null, requests=null, packages=null, previousWorks=null, inventories=[Inventory(inventoryID=null, imageURL=https://example.com/photography.jpg, description=Professional camera setup, supplierCategory=EQUIPMENT)])], agenda=Agenda(id=null, date=2025-05-08, time=14:00, tasks=[AgendaTask(taskId=600, taskName=Photography, startTime=2025-05-08T10:30Z, endTime=2025-05-08T11:30Z, supplierId=13, supplierType=PHOTOGRAPHY)]), anniversary=null, wedding=Wedding(id=null, weddingType=TRADITIONAL, date=2025-05-08), getTogether=null, birthdayParty=null)