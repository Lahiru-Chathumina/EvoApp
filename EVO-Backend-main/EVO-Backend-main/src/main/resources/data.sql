
--ALTER TABLE event_summary ADD CONSTRAINT fk_event_summary_venue FOREIGN KEY (venue_id) REFERENCES venue
--(venue_id);
--ALTER TABLE event_summary ADD CONSTRAINT fk_event_summary_user FOREIGN KEY (user_id) REFERENCES `user`
--(user_id);

-- LOCATIONS
INSERT INTO location (city, village) VALUES
('Colombo', 'Pettah'),
('Kandy', 'Peradeniya'),
('Galle', 'Unawatuna'),
('Matara', 'Weligama'),
('Jaffna', 'Chunnakam'),
('Anuradhapura', 'Mihintale'),
('Polonnaruwa', 'Medirigiriya'),
('Badulla', 'Bandarawela'),
('Nuwara Eliya', 'Hatton'),
('Ratnapura', 'Pelmadulla'),
('Kurunegala', 'Pannala'),
('Batticaloa', 'Kattankudy'),
('Trincomalee', 'Kinniya'),
('Puttalam', 'Chilaw'),
('Ampara', 'Kalmunai'),
('Hambantota', 'Tangalle'),
('Mannar', 'Pesalai'),
('Kilinochchi', 'Pallai'),
('Monaragala', 'Wellawaya'),
('Vavuniya', 'Omanthai'),
('Kalutara', 'Beruwala'),
('Gampaha', 'Negombo'),
('Matale', 'Dambulla'),
('Kegalle', 'Mawanella'),
('Udawalawa', 'Embilipitiya');



-- USERS
INSERT INTO user (first_name, last_name, password, email, registered_date, user_type, contact_number, address,
city) VALUES
('John', 'Doe', 'hashed_password_1', 'john.doe@example.com', '2024-04-02', 'CUSTOMER', '1234567890', '123 Main St', 'New York'),
('Jane', 'Smith', 'hashed_password_2', 'jane.smith@example.com', '2024-04-02', 'SUPPLIER', '2345678901', '456 Oak St', 'Los Angeles'),
('Michael', 'Johnson', 'hashed_password_3', 'michael.johnson@example.com', '2024-04-02', 'ADMIN', '3456789012', '789 Pine St', 'Chicago'),
('Emily', 'Williams', 'hashed_password_4', 'emily.williams@example.com', '2024-04-02', 'CUSTOMER', '4567890123', '101 Maple St', 'Miami'),
('David', 'Brown', 'hashed_password_5', 'david.brown@example.com', '2024-04-02', 'SUPPLIER', '5678901234', '202 Birch St', 'San Francisco'),
('Sarah', 'Jones', 'hashed_password_6', 'sarah.jones@example.com', '2024-04-02', 'ADMIN', '6789012345', '303 Cedar St', 'Houston'),
('James', 'Miller', 'hashed_password_7', 'james.miller@example.com', '2024-04-02', 'CUSTOMER', '7890123456', '404 Elm St', 'Dallas'),
('Olivia', 'Davis', 'hashed_password_8', 'olivia.davis@example.com', '2024-04-02', 'SUPPLIER', '8901234567', '505 Willow St', 'Austin'),
('Benjamin', 'Garcia', 'hashed_password_9', 'benjamin.garcia@example.com', '2024-04-02', 'ADMIN', '9012345678', '606 Pine St', 'Atlanta'),
('Mia', 'Martinez', 'hashed_password_10', 'mia.martinez@example.com', '2024-04-02', 'CUSTOMER', '0123456789', '707 Cedar St', 'Phoenix');

-- VENUE
INSERT INTO venue (capacity, venue_type, location_id) VALUES
(200, 'RESTAURANT', 1),
(300, 'VILLA', 2),
(100, 'HOTEL', 3),
(150, 'RESTAURANT', 4),
(400, 'VILLA', 5),
(250, 'HOTEL', 6),
(500, 'RESTAURANT', 7),
(350, 'VILLA', 8),
(450, 'HOTEL', 9),
(600, 'RESTAURANT', 10);

---- Inserting a new Supplier into the supplier table
--INSERT INTO supplier (
--    business_name,
--    business_contact_number,
--    business_email,
--    description,
--    availability,
--    category,
--    profile_image_url
--)
--VALUES (
--    'Glow Beauty',
--    '0771234567',
--    'contact@glowbeauty.lk',
--    'Professional bridal and party makeup services.',
--    true,
--    'BEAUTY',
--    'https://yourapp.com/images/supplier1.jpg'
--);

---- Inserting sample data for SupplierEntity
--INSERT INTO supplier (business_name, business_contact_number, business_email, description, availability, category, profile_image_url)
--VALUES
--    ('Glamour Studio', '0112345678', 'contact@glamourstudio.com', 'Leading beauty service provider for weddings and events.', TRUE, 'BEAUTY', 'https://example.com/profile1.jpg'),
--    ('Style & Grace', '0123456789', 'info@styleandgrace.com', 'Salon offering makeup and hairstyling services.', TRUE, 'BEAUTY', 'https://example.com/profile2.jpg'),
--    ('Bridal Bliss', '0134567890', 'hello@bridalbliss.com', 'Expert bridal makeup and hair services.', TRUE, 'BEAUTY', 'https://example.com/profile3.jpg');

-- Inserting sample data for BeautySaloonEntity
--INSERT INTO beauty_salon (specialty, supplier_id)
--VALUES
--    ('Bridal Makeup, Hair Styling', 1),  -- Supplier 1
--    ('Makeup Artistry, Event Styling', 2),  -- Supplier 2
--    ('Wedding Hair & Makeup', 3);  -- Supplier 3
