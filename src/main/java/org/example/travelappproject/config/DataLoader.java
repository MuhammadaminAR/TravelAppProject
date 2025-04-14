package org.example.travelappproject.config;

import org.example.travelappproject.entity.*;
import org.example.travelappproject.enums.AccommodationType;
import org.example.travelappproject.enums.RoleName;
import org.example.travelappproject.enums.RoomStatus;
import org.example.travelappproject.enums.RoomType;
import org.example.travelappproject.repo.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class DataLoader implements CommandLineRunner {
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
    private final DestinationRepository destinationRepository;
    private final ContinentRepository continentRepository;
    private final CountryRepository countryRepository;
    private final CityRepository cityRepository;
    private final AttachmentRepository attachmentRepository;
    private final HotelRepository hotelRepository;
    private final RoomRepository roomRepository;

    public DataLoader(RoleRepository roleRepository, PasswordEncoder passwordEncoder, UserRepository userRepository, DestinationRepository destinationRepository, ContinentRepository continentRepository, CountryRepository countryRepository, CityRepository cityRepository, AttachmentRepository attachmentRepository, HotelRepository hotelRepository, RoomRepository roomRepository) {
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
        this.userRepository = userRepository;
        this.destinationRepository = destinationRepository;
        this.continentRepository = continentRepository;
        this.countryRepository = countryRepository;
        this.cityRepository = cityRepository;
        this.attachmentRepository = attachmentRepository;
        this.hotelRepository = hotelRepository;
        this.roomRepository = roomRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        List<Role> allRoles = roleRepository.findAll();
        if (allRoles.isEmpty()) {
            Role role = new Role();
            role.setRoleName(RoleName.ROLE_USER);
            roleRepository.save(role);
            Role role2 = new Role();
            role2.setRoleName(RoleName.ROLE_ADMIN);
            roleRepository.save(role2);
            Role role3 = new Role();
            role3.setRoleName(RoleName.ROLE_SUPER_ADMIN);
            roleRepository.save(role3);
            Role role4 = new Role();
            role4.setRoleName(RoleName.ROLE_OPERATOR);
            roleRepository.save(role4);
        }
        if(userRepository.findAll().isEmpty()){
            for (Role role : allRoles) {
                if (role.getRoleName().equals(RoleName.ROLE_SUPER_ADMIN)) {
                    User user1 = User.builder()
                            .roles(new ArrayList<>(List.of(role)))
                            .email("superadmin@123gmail.com")
                            .password(passwordEncoder.encode("root123"))
                            .build();
                    userRepository.save(user1);
                }
                if (role.getRoleName().equals(RoleName.ROLE_OPERATOR)) {
                    User user1 = User.builder()
                            .roles(new ArrayList<>(List.of(role)))
                            .email("abdullayevmuhammadamin89@gmail.com")
                            .password(passwordEncoder.encode("root123"))
                            .build();
                    userRepository.save(user1);
                }
                if (role.getRoleName().equals(RoleName.ROLE_USER)) {
                    User user1 = User.builder()
                            .roles(new ArrayList<>(List.of(role)))
                            .email("kinouchunok777@gmail.com")
                            .password(passwordEncoder.encode("root123"))
                            .build();
                    userRepository.save(user1);
                }
            }
        }
        List<Continent> continents = continentRepository.findAll();
        if (continents.isEmpty()) {
            Continent asiacontinent = new Continent("Asia");
            Continent continent2 = new Continent("Europe");
            continentRepository.save(asiacontinent);
            continentRepository.save(continent2);

            Country uzbekistan = Country.builder()
                    .name("Uzbekistan")
                    .continent(asiacontinent)
                    .build();
            Country japan = Country.builder()
                    .name("Japan")
                    .continent(asiacontinent)
                    .build();
            Country germany = Country.builder()
                    .name("Germany")
                    .continent(continent2)
                    .build();
            Country france = Country.builder()
                    .name("France")
                    .continent(continent2)
                    .build();
            countryRepository.save(uzbekistan);
            countryRepository.save(japan);
            countryRepository.save(germany);
            countryRepository.save(france);

            City buxara = City.builder()
                    .name("Buxara")
                    .country(uzbekistan)
                    .build();
            City samarkand = City.builder()
                    .name("Samarkand")
                    .country(uzbekistan)
                    .build();
            City tokio = City.builder()
                    .name("tokio")
                    .country(japan)
                    .build();
            City kioto = City.builder()
                    .name("Kioto")
                    .country(japan)
                    .build();
            City berlin = City.builder()
                    .name("Berlin")
                    .country(germany)
                    .build();
            City munich = City.builder()
                    .name("Munich")
                    .country(germany)
                    .build();
            City paris = City.builder()
                    .name("Paris")
                    .country(france)
                    .build();
            City marseille = City.builder()
                    .name("Marseille")
                    .country(france)
                    .build();
            cityRepository.save(buxara);
            cityRepository.save(samarkand);
            cityRepository.save(tokio);
            cityRepository.save(kioto);
            cityRepository.save(berlin);
            cityRepository.save(munich);
            cityRepository.save(paris);
            cityRepository.save(marseille);

            Attachment attachment = new Attachment("Lyabi-Hovuz Ensemble");
            Attachment attachment2 = new Attachment("Ark Qal’asi");
            Attachment attachment3 = new Attachment("Registon maydoni");
            Attachment attachment4 = new Attachment("Shohi Zinda maqbarasi");
            Attachment attachment5 = new Attachment("Shibuya Chorahasi");
            Attachment attachment6 = new Attachment("Sensoji Ma’badi");
            Attachment attachment7 = new Attachment("Kinkakuji Ma’badi");
            Attachment attachment8 = new Attachment("Fushimi Inari Ma’badi");
            Attachment attachment9 = new Attachment("Brandenburg Darvozasi");
            Attachment attachment10 = new Attachment("Reyxstag Binosi");
            Attachment attachment11 = new Attachment("Marienplatz Maydoni");
            Attachment attachment12 = new Attachment("Nymphenburg Saroyi");
            Attachment attachment13 = new Attachment("Eyfel Minorasi");
            Attachment attachment14 = new Attachment("Luvr Muzeyi");
            Attachment attachment15 = new Attachment("Vieux-Port");
            Attachment attachment16 = new Attachment("Notr-Dam-de-la-Gard Bazilikasi");
            attachmentRepository.save(attachment);
            attachmentRepository.save(attachment2);
            attachmentRepository.save(attachment3);
            attachmentRepository.save(attachment4);
            attachmentRepository.save(attachment5);
            attachmentRepository.save(attachment6);
            attachmentRepository.save(attachment7);
            attachmentRepository.save(attachment8);
            attachmentRepository.save(attachment9);
            attachmentRepository.save(attachment10);
            attachmentRepository.save(attachment11);
            attachmentRepository.save(attachment12);
            attachmentRepository.save(attachment13);
            attachmentRepository.save(attachment14);
            attachmentRepository.save(attachment15);
            attachmentRepository.save(attachment16);

            Destination destination = Destination.builder()
                    .name("Lyabi-Hovuz Ensemble")
                    .description("Lyabi Hovuz – Buxoroning markaziy va eng muhim joylaridan biri. Bu yerda katta hovuz atrofida madrasa va masjidlar joylashgan bo‘lib, sayyohlarni o‘zining tinch muhiti va qadimiy me’morchiligi bilan jalb qiladi.")
                    .city(buxara)
                    .attachmentList(List.of(attachment))
                    .build();

            Destination destination2 = Destination.builder()
                    .name("Ark Qal'asi")
                    .description("Ark Qal’asi – Buxoroning qadimiy mudofaa inshooti va hukmdorlar qarorgohi. Bu ulkan qal’a shaharning tarixiy ramzi bo‘lib, ichida muzey va eski saroy xonalari saqlanadi.")
                    .city(buxara)
                    .attachmentList(List.of(attachment2))
                    .build();

            Destination destination3 = Destination.builder() // qq //ee
                    .name("Registon Maydoni")
                    .description("egiston Maydoni – Samarqandning eng muhim tarixiy joyi bo‘lib, uchta katta madrasa – Ulug‘bek, Sherdor va Tillakori bilan mashhur. Bu maydon o‘rta asr me’morchiligining eng yaxshi namunalardan biri sifatida UNESCO ro‘yxatiga kiritilgan.")
                    .city(samarkand)
                    .attachmentList(List.of(attachment3))
                    .build();

            Destination destination4 = Destination.builder()
                    .name("Shohi Zinda Maqbarasi")
                    .description("Shohi Zinda – Samarqanddagi muqaddas maqbaralar majmuasi bo‘lib, ko‘k va yashil rangli naqshlari bilan ajralib turadi. Bu yerda Temuriylar davrining ko‘plab taniqli shaxslari dafn etilgan va ziyoratgoh sifatida ham mashhur.")
                    .city(samarkand)
                    .attachmentList(List.of(attachment4))
                    .build();

            Destination destination5 = Destination.builder()
                    .name("Shibuya Chorrahasi")
                    .description("Shibuya Chorahasi – Tokyoning eng mashhur va gavjum joylaridan biri. Bu yerda minglab odamlar yo‘lni kesib o‘tadi, ayniqsa kechqurun neon chiroqlar ostida ajoyib manzara hosil bo‘ladi. Hachiko haykali ham shu yaqin joyda joylashgan.")
                    .city(tokio)
                    .attachmentList(List.of(attachment5))
                    .build();

            Destination destination6 = Destination.builder()
                    .name("Sensoji Ma’badi")
                    .description("Sensoji Ma’badi – Tokyoning eng qadimiy ibodatxonasi bo‘lib, Asakusa hududida joylashgan. Kaminarimon darvozasi va Nakamise savdo ko‘chasi bilan mashhur. Bu yerda an’anaviy yapon madaniyatini his qilish mumkin.")
                    .city(tokio)
                    .attachmentList(List.of(attachment6))
                    .build();

            Destination destination7 = Destination.builder()
                    .name("Kinkakuji Ma’badi")
                    .description("Kinkakuji, ya’ni “Oltin Pavilyon” – Kiotodagi eng mashhur ma’badlardan biri. Uning yuqori qavatlari oltin barglar bilan qoplangan bo‘lib, ko‘l yuzasida ajoyib aks etadi. Bu yer tinchlik va go‘zallik ramzi hisoblanadi.")
                    .city(kioto)
                    .attachmentList(List.of(attachment7))
                    .build();

            Destination destination8 = Destination.builder()
                    .name("Fushimi Inari Ma’badi")
                    .description("Fushimi Inari – minglab qizil torii darvozalari bilan mashhur ziyoratgoh. Bu ma’bad Inari xudosiga bag‘ishlangan bo‘lib, tog‘ yo‘laklaridagi darvozalar sayyohlarni o‘ziga jalb qiladi. Bu yerda yapon madaniyatini chuqur his qilish mumkin.")
                    .city(kioto)
                    .attachmentList(List.of(attachment8))
                    .build();

            Destination destination9 = Destination.builder()
                    .name("Brandenburg Darvozasi")
                    .description("Brandenburg Darvozasi – Berlinning eng muhim ramzlaridan biri. Bu XVIII asrda qurilgan klassik uslubdagi darvoza bo‘lib, shaharning tarixiy markazida joylashgan. Sayyohlar uchun ajoyib suratga olish joyi hisoblanadi.")
                    .city(berlin)
                    .attachmentList(List.of(attachment9))
                    .build();

            Destination destination10 = Destination.builder()
                    .name("Reyxstag Binosi")
                    .description("Reyxstag – Germaniya parlamentining joylashgan binosi. Uning shisha gumbazi Berlinning panoramik manzarasini ko‘rish imkonini beradi. Tarixiy ahamiyati va zamonaviy dizayni bilan mashhur.")
                    .city(berlin)
                    .attachmentList(List.of(attachment10))
                    .build();

            Destination destination11 = Destination.builder()
                    .name("Marienplatz Maydoni")
                    .description("Marienplatz – Myunxenning markaziy maydoni bo‘lib, shaharning yuragi hisoblanadi. Bu yerda Yangi Ratusha (Neues Rathaus) binosi joylashgan bo‘lib, uning soat minorasidagi harakatlanuvchi figurinlar sayyohlarni o‘ziga jalb qiladi.")
                    .city(munich)
                    .attachmentList(List.of(attachment11))
                    .build();

            Destination destination12 = Destination.builder()
                    .name("Nymphenburg Saroyi")
                    .description("Nymphenburg Saroyi – Myunxendagi muhtasham barokko uslubidagi saroy. U keng bog‘lari va chiroyli ichki bezaklari bilan mashhur. Bu yerda Bavariya qirollik oilasining tarixini ko‘rish mumkin.")
                    .city(munich)
                    .attachmentList(List.of(attachment12))
                    .build();

            Destination destination13 = Destination.builder()
                    .name("Eyfel Minorasi")
                    .description("Eyfel Minorasi – Parijning eng mashhur ramzi bo‘lib, 1889-yilda qurilgan. Bu temir minora shaharning ajoyib manzarasini taqdim etadi va har yili millionlab sayyohlarni jalb qiladi.")
                    .city(paris)
                    .attachmentList(List.of(attachment13))
                    .build();

            Destination destination14 = Destination.builder()
                    .name("Luvr Muzeyi")
                    .description("Luvr Muzeyi – dunyodagi eng katta san’at muzeylaridan biri. Bu yerda Mona Liza va minglab tarixiy asarlar saqlanadi. Uning shisha piramida kirish qismi ham diqqatga sazovor.")
                    .city(paris)
                    .attachmentList(List.of(attachment14))
                    .build();

            Destination destination15 = Destination.builder()
                    .name("Vieux-Port")
                    .description("Eski Port – Marselning tarixiy va eng jonli joylaridan biri. Bu yerda dengiz bo‘yidagi ajoyib manzara, baliq bozori va kafe-restoranlar sayyohlarni o‘ziga jalb qiladi.")
                    .city(marseille)
                    .attachmentList(List.of(attachment15))
                    .build();

            Destination destination16 = Destination.builder()
                    .name("Notr-Dam-de-la-Gard Bazilikasi")
                    .description("Notr-Dam-de-la-Gard – shaharning eng baland nuqtasida joylashgan bazilika. U shahar va dengizning muhtasham manzarasini taqdim etadi, ichidagi mozaikalar esa diqqatga sazovor.")
                    .city(marseille)
                    .attachmentList(List.of(attachment16))
                    .build();
            destinationRepository.save(destination);
            destinationRepository.save(destination2);
            destinationRepository.save(destination3);
            destinationRepository.save(destination4);
            destinationRepository.save(destination5);
            destinationRepository.save(destination6);
            destinationRepository.save(destination7);
            destinationRepository.save(destination8);
            destinationRepository.save(destination9);
            destinationRepository.save(destination10);
            destinationRepository.save(destination11);
            destinationRepository.save(destination12);
            destinationRepository.save(destination13);
            destinationRepository.save(destination14);
            destinationRepository.save(destination15);
            destinationRepository.save(destination16);

            Hotel hotel = new Hotel("Lyabi-Hovuz hotel", AccommodationType.HOTEL, buxara, "bu Hotelimiz judaham shinam va qulayliklarga ega");
            Hotel hotel2 = new Hotel("Ark Qal'asi hotel", AccommodationType.HOMESTAY, buxara, "bu Hotelimiz judaham shinam va qulayliklarga ega");
            Hotel hotel3 = new Hotel("Registon Square hotel", AccommodationType.RESORT, samarkand, "bu Hotelimiz judaham shinam va qulayliklarga ega");
            Hotel hotel4 = new Hotel("Shah-i-Zinda Necropolis hotel", AccommodationType.APARTMENT, samarkand, "bu Hotelimiz judaham shinam va qulayliklarga ega");
            Hotel hotel5 = new Hotel("Shibuya Choraha hotel", AccommodationType.VILLA, tokio, "bu Hotelimiz judaham shinam va qulayliklarga ega");
            Hotel hotel6 = new Hotel("Sensoji hotel", AccommodationType.HOTEL, tokio, "bu Hotelimiz judaham shinam va qulayliklarga ega");
            Hotel hotel7 = new Hotel("Kinkakuji hotel", AccommodationType.HOMESTAY, kioto, "bu Hotelimiz judaham shinam va qulayliklarga ega");
            Hotel hotel8 = new Hotel("Fushimi Inari hotel", AccommodationType.RESORT, kioto, "bu Hotelimiz judaham shinam va qulayliklarga ega");
            Hotel hotel9 = new Hotel("Brandenburg hotel", AccommodationType.APARTMENT, berlin, "bu Hotelimiz judaham shinam va qulayliklarga ega");
            Hotel hotel10 = new Hotel("Reyxstag hotel", AccommodationType.VILLA, berlin, "bu Hotelimiz judaham shinam va qulayliklarga ega");
            Hotel hotel11 = new Hotel("Marienplatz hotel", AccommodationType.HOTEL, munich, "bu Hotelimiz judaham shinam va qulayliklarga ega");
            Hotel hotel12 = new Hotel("Nymphenburg hotel", AccommodationType.HOMESTAY, munich, "bu Hotelimiz judaham shinam va qulayliklarga ega");
            Hotel hotel13 = new Hotel("Eyfel hotel", AccommodationType.RESORT, paris, "bu Hotelimiz judaham shinam va qulayliklarga ega");
            Hotel hotel14 = new Hotel("Luvr hotel", AccommodationType.RESORT, paris, "bu Hotelimiz judaham shinam va qulayliklarga ega");
            Hotel hotel15 = new Hotel("Vieux hotel", AccommodationType.APARTMENT, marseille, "bu Hotelimiz judaham shinam va qulayliklarga ega");
            Hotel hotel16 = new Hotel("Notr-Dam-de-la-Gard hotel", AccommodationType.VILLA, marseille, "bu Hotelimiz judaham shinam va qulayliklarga ega");
            hotelRepository.save(hotel);
            hotelRepository.save(hotel2);
            hotelRepository.save(hotel3);
            hotelRepository.save(hotel4);
            hotelRepository.save(hotel5);
            hotelRepository.save(hotel6);
            hotelRepository.save(hotel7);
            hotelRepository.save(hotel8);
            hotelRepository.save(hotel9);
            hotelRepository.save(hotel10);
            hotelRepository.save(hotel11);
            hotelRepository.save(hotel12);
            hotelRepository.save(hotel13);
            hotelRepository.save(hotel14);
            hotelRepository.save(hotel15);
            hotelRepository.save(hotel16); // mana yana

            Room room = Room.builder()
                    .roomNumber("1")
                    .hotel(hotel)
                    .roomStatus(RoomStatus.LUX)
                    .roomType(RoomType.DELUXE)
                    .price(100.0)
                    .build();
            Room room2 = Room.builder()
                    .roomNumber("2")
                    .hotel(hotel)
                    .roomStatus(RoomStatus.ECONOMY)
                    .roomType(RoomType.SINGLE)
                    .price(50.0)
                    .build();
            Room room3 = Room.builder()
                    .roomNumber("1")
                    .hotel(hotel2)
                    .roomStatus(RoomStatus.PREMIUM_CLASS)
                    .roomType(RoomType.DOUBLE)
                    .price(150.0)
                    .build();
            Room room4 = Room.builder()
                    .roomNumber("2")
                    .hotel(hotel2)
                    .roomStatus(RoomStatus.LUX)
                    .roomType(RoomType.DELUXE)
                    .price(100.0)
                    .build();
            Room room5 = Room.builder()
                    .roomNumber("1")
                    .hotel(hotel3)
                    .roomStatus(RoomStatus.PRESIDENT_CLASS)
                    .roomType(RoomType.SINGLE)
                    .price(1000.0)
                    .build();
            Room room6 = Room.builder()
                    .roomNumber("2")
                    .hotel(hotel3)
                    .roomStatus(RoomStatus.LUX)
                    .roomType(RoomType.DELUXE)
                    .price(100.0)
                    .build();
            Room room7 = Room.builder()
                    .roomNumber("1")
                    .hotel(hotel4)
                    .roomStatus(RoomStatus.ECONOMY)
                    .roomType(RoomType.FAMILY)
                    .price(100.0)
                    .build();
            Room room8 = Room.builder()
                    .roomNumber("2")
                    .hotel(hotel4)
                    .roomStatus(RoomStatus.LUX)
                    .roomType(RoomType.DELUXE)
                    .price(100.0)
                    .build();
            Room room9 = Room.builder()
                    .roomNumber("1")
                    .hotel(hotel5)
                    .roomStatus(RoomStatus.PRESIDENT_CLASS)
                    .roomType(RoomType.SINGLE)
                    .price(1000.0)
                    .build();
            Room room10 = Room.builder()
                    .roomNumber("2")
                    .hotel(hotel5)
                    .roomStatus(RoomStatus.LUX)
                    .roomType(RoomType.DELUXE)
                    .price(100.0)
                    .build();
            Room room11 = Room.builder()
                    .roomNumber("1")
                    .hotel(hotel6)
                    .roomStatus(RoomStatus.PREMIUM_CLASS)
                    .roomType(RoomType.FAMILY)
                    .price(1000.0)
                    .build();
            Room room12 = Room.builder()
                    .roomNumber("2")
                    .hotel(hotel6)
                    .roomStatus(RoomStatus.LUX)
                    .roomType(RoomType.DELUXE)
                    .price(100.0)
                    .build();
            Room room13 = Room.builder()
                    .roomNumber("1")
                    .hotel(hotel7)
                    .roomStatus(RoomStatus.ECONOMY)
                    .roomType(RoomType.DOUBLE)
                    .price(70.0)
                    .build();
            Room room14 = Room.builder()
                    .roomNumber("2")
                    .hotel(hotel7)
                    .roomStatus(RoomStatus.LUX)
                    .roomType(RoomType.DELUXE)
                    .price(100.0)
                    .build();
            Room room15 = Room.builder()
                    .roomNumber("1")
                    .hotel(hotel8)
                    .roomStatus(RoomStatus.PREMIUM_CLASS)
                    .roomType(RoomType.FAMILY)
                    .price(1000.0)
                    .build();
            Room room16 = Room.builder()
                    .roomNumber("2")
                    .hotel(hotel8)
                    .roomStatus(RoomStatus.LUX)
                    .roomType(RoomType.DELUXE)
                    .price(100.0)
                    .build();
            Room room17 = Room.builder()
                    .roomNumber("1")
                    .hotel(hotel9)
                    .roomStatus(RoomStatus.ECONOMY)
                    .roomType(RoomType.FAMILY)
                    .price(100.0)
                    .build();
            Room room18 = Room.builder()
                    .roomNumber("2")
                    .hotel(hotel9)
                    .roomStatus(RoomStatus.LUX)
                    .roomType(RoomType.DELUXE)
                    .price(100.0)
                    .build();
            Room room19 = Room.builder()
                    .roomNumber("1")
                    .hotel(hotel10)
                    .roomStatus(RoomStatus.PRESIDENT_CLASS)
                    .roomType(RoomType.SINGLE)
                    .price(1000.0)
                    .build();
            Room room20 = Room.builder()
                    .roomNumber("2")
                    .hotel(hotel10)
                    .roomStatus(RoomStatus.LUX)
                    .roomType(RoomType.DELUXE)
                    .price(100.0)
                    .build();
            Room room21 = Room.builder()
                    .roomNumber("1")
                    .hotel(hotel11)
                    .roomStatus(RoomStatus.PREMIUM_CLASS)
                    .roomType(RoomType.DOUBLE)
                    .price(1000.0)
                    .build();
            Room room22 = Room.builder()
                    .roomNumber("2")
                    .hotel(hotel11)
                    .roomStatus(RoomStatus.LUX)
                    .roomType(RoomType.DELUXE)
                    .price(100.0)
                    .build();
            Room room23 = Room.builder()
                    .roomNumber("1")
                    .hotel(hotel12)
                    .roomStatus(RoomStatus.ECONOMY)
                    .roomType(RoomType.SINGLE)
                    .price(50.0)
                    .build();
            Room room24 = Room.builder()
                    .roomNumber("2")
                    .hotel(hotel12)
                    .roomStatus(RoomStatus.LUX)
                    .roomType(RoomType.DELUXE)
                    .price(100.0)
                    .build();
            Room room25 = Room.builder()
                    .roomNumber("1")
                    .hotel(hotel13)
                    .roomStatus(RoomStatus.PREMIUM_CLASS)
                    .roomType(RoomType.SUITE)
                    .price(1500.0)
                    .build();
            Room room26 = Room.builder()
                    .roomNumber("2")
                    .hotel(hotel13)
                    .roomStatus(RoomStatus.LUX)
                    .roomType(RoomType.DELUXE)
                    .price(100.0)
                    .build();
            Room room27 = Room.builder()
                    .roomNumber("1")
                    .hotel(hotel14)
                    .roomStatus(RoomStatus.ECONOMY)
                    .roomType(RoomType.DOUBLE)
                    .price(80.0)
                    .build();
            Room room28 = Room.builder()
                    .roomNumber("2")
                    .hotel(hotel14)
                    .roomStatus(RoomStatus.LUX)
                    .roomType(RoomType.DELUXE)
                    .price(100.0)
                    .build();
            Room room29 = Room.builder()
                    .roomNumber("1")
                    .hotel(hotel15)
                    .roomStatus(RoomStatus.PRESIDENT_CLASS)
                    .roomType(RoomType.DOUBLE)
                    .price(1500.0)
                    .build();
            Room room30 = Room.builder()
                    .roomNumber("2")
                    .hotel(hotel15)
                    .roomStatus(RoomStatus.LUX)
                    .roomType(RoomType.DELUXE)
                    .price(100.0)
                    .build();
            Room room31 = Room.builder()
                    .roomNumber("1")
                    .hotel(hotel16)
                    .roomStatus(RoomStatus.ECONOMY)
                    .roomType(RoomType.SINGLE)
                    .price(80.0)
                    .build();
            Room room32 = Room.builder()
                    .roomNumber("2")
                    .hotel(hotel16)
                    .roomStatus(RoomStatus.LUX)
                    .roomType(RoomType.DELUXE)
                    .price(100.0)
                    .build();
            roomRepository.save(room);
            roomRepository.save(room2);
            roomRepository.save(room3);
            roomRepository.save(room4);
            roomRepository.save(room5);
            roomRepository.save(room6);
            roomRepository.save(room7);
            roomRepository.save(room8);
            roomRepository.save(room9);
            roomRepository.save(room10);
            roomRepository.save(room11);
            roomRepository.save(room12);
            roomRepository.save(room13);
            roomRepository.save(room14);
            roomRepository.save(room15);
            roomRepository.save(room16);
            roomRepository.save(room17);
            roomRepository.save(room18);
            roomRepository.save(room19);
            roomRepository.save(room20);
            roomRepository.save(room21);
            roomRepository.save(room22);
            roomRepository.save(room23);
            roomRepository.save(room24);
            roomRepository.save(room25);
            roomRepository.save(room26);
            roomRepository.save(room27);
            roomRepository.save(room28);
            roomRepository.save(room29);
            roomRepository.save(room30);
            roomRepository.save(room31);
            roomRepository.save(room32);
            // roomlar qo'shildi.
        }
    }
}
