package dev.danvega.h2_demo.model;

import lombok.Data;
import jakarta.persistence.*;
import java.time.LocalDate;

@Data
@Table(name = "sale")
@Entity
public class Sale {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

    @Column(name = "customer_email")
    private String customerEmail;

    @Column(name = "sale_date")
    private LocalDate saleDate;

    @Column(name = "quantity")
    private Integer quantity;

    @Column(name = "total_amount")
    private Double totalAmount;
}

/*package dev.danvega.h2_demo.model;

import lombok.Data; // Lombok kütüphanesinden @Data annotation'ını kullanarak getter, setter, equals, hashCode ve toString gibi yöntemlerin otomatik olarak oluşturulmasını sağlar.

import jakarta.persistence.*; // JPA (Java Persistence API) için gerekli olan annotation'ları içerir.
import java.time.LocalDateTime; //Java 8 den itibaren gelen LocalDateTime sınıfı, tarih ve saat işlemleri yapmak için kullanılan bir sınıftır.
// Bu sınıf java.time paketi altında yer alır ve yerel tarih ve saat bilgisini temsisl eder
//LocalDateTime Sınıfı
//Java'da LocalDateTime sınıfı, tarih ve saat bilgisini bir arada tutan bir yapıdır.
// Bu yapı içinde yıl, ay, gün, saat, dakika ve saniye gibi bilgiler saklanabilir.
// Örneğin, bir etkinliğin hangi tarihte ve saatte başladığını veya bir siparişin hangi tarihte verildiğini temsil etmek için kullanılabilir.

@Data //Lombok tarafından sağlanan bir annotation'dır. Bu annotation, sınıfın tüm getter, setter, equals, hashCode ve toString metotlarını otomatik olarak oluşturur.
@Entity //Açıklama: @Entity annotation'ı, bu sınıfın bir JPA entity sınıfı olduğunu belirtir.
@Table(name = "Sale") //@Table annotation'ı ise bu entity'nin veritabanında hangi tabloya karşılık geldiğini belirtir (tablo adı "Sale").
public class Sale {

    @Id //@Id annotasyonu, bir entity sınıfındaki alanın veya metotun primary key olduğunu belirtir.
    // @Id annotasyonu, bir JPA (Java Persistence API) entity sınıfındaki bir alanın (field) veya metotun (getter) primary key olarak işaretlendiğini belirtir.
    // Yani, bu annotasyon kullanıldığında ilgili alan veya metot, veritabanındaki tablodaki benzersiz kimliği temsil eder.
    @GeneratedValue(strategy = GenerationType.IDENTITY) //@GeneratedValue: Bu annotasyon, ilgili alanın değerinin otomatik olarak üretileceğini belirtir. strategy = GenerationType.IDENTITY bu ise auto-increment bir sütunun değerinin veritabanı tarafından yönetileceğini belirtir. Bu strateji, genellikle MySQL gibi bazı veritabanı sistemlerinde kullanılır. Veritabanı bu durumda, yeni bir kayıt eklediğinizde otomatik olarak bir ID değeri atar ve bu değeri artırır.
    private Long id; // Satışın benzersiz kimliğini tutan alan.

    @ManyToOne //Birçok satışın aynı ürünle ilişkilendirilebilieceğini belirtir. Yani, birçok satış kaydı (Sale), tek bir bir ürüne (Product) ait olabilir.
    @JoinColumn(name = "product_id") //nullable = false dememizin nedeni bu alan boş olamaz. @JoinColumn anatasyonu ilişkinin nasıl yönetileceğini belirtir.
    private Product product;

    @Column(name = "sale_date")
    private LocalDateTime saleDate;

    @Column(name = "quantity")
    private Integer quantity;

    @Column(name = "total_amount")
    private Double totalAmount;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;
}*/





