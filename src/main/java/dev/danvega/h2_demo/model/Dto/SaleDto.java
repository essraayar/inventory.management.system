package dev.danvega.h2_demo.model.Dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class SaleDto {
    private Long id;
    private Long productId;
    private String customerEmail;
    private LocalDate saleDate;
    private Integer quantity;
    private Double totalAmount;
}


/*
package dev.danvega.h2_demo.model.Dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class SaleDto {

    private Long id;
    private ProductDto product;
    private LocalDateTime saleDate;
    private Integer quantity;
    private Double totalAmount;
    private CustomerDto customer;

}

*/


/*
Dto (Data Transfer Object), genellikle farklı katmanlar arasında veri taşımak veya transfer etmek için kullanılan bir tasarım desenidir.
Dto'ların temel amacı, veritabanı varlıklarının veya diğer karmaşık nesnelerin basitleştirilmiş bir temsilini sağlamaktır.
Bazı ana kullanım alanları şunlardır:

Veri Taşıma ve Transferi: Dto'lar, genellikle servisler arasında veya istemci (client) ile sunucu (server) arasında veri taşımak için kullanılır. Özellikle web uygulamalarında ve mikro hizmet mimarilerinde sıkça görülür.

Karmaşık Veri Yapılarının Basitleştirilmesi: Dto'lar, genellikle veritabanı varlıkları veya diğer karmaşık nesnelerin (örneğin, ORM varlıkları) basitleştirilmiş bir versiyonunu sunar. Bu, veri tabanından veya başka bir kaynaktan alınan verilerin, istemci uygulamalarında veya farklı servisler arasında işlenmesini kolaylaştırır.

Veri Transferi Performansının İyileştirilmesi: Dto'lar, ihtiyaç duyulmayan veya gereksiz verileri taşımadan sadece gerekli verilerin aktarılmasını sağlar. Bu da iletişim maliyetlerini ve veri transferi sürelerini azaltabilir.

API Tasarımı ve İletişimi: Web servisler ve API'ler üzerinden veri iletişiminde, Dto'lar genellikle istemciler ve sunucular arasında kullanılan yapısal bir araç olarak görev yapar. API endpoint'lerinde giriş ve çıkış veri yapılarını tanımlamak için kullanılırlar.

Dto'lar genellikle şu özelliklere sahiptir:

Getter ve Setter Metotları: Verilerin okunması ve yazılması için getter ve setter metotları bulunur.
Seriye Alma ve Deseriye Etme Yetenekleri: JSON veya XML gibi formatlara dönüşüm yapabilme yeteneği, özellikle web servislerde önemlidir.
İş Mantığından Arındırılmış: Veri transferi amaçlı oldukları için genellikle iş mantığı içermezler, sadece veri taşır ve sınırlı sayıda işlem yaparlar.
Dto'ların kullanımı, temiz ve modüler bir kod tasarımı sağlamak ve sistemler arası iletişimi iyileştirmek için önemli bir yapısal bileşendir.



*/






/*
package dev.danvega.h2_demo.model.Dto;
import java.util.Date;

public class SaleDto {

    private Long saleId;
    private CustomerDto customer;
    private ProductDto product;
    private Date saleDate;
    private Integer quantity;

    public SaleDto() {
    }

    public SaleDto(Long saleId, CustomerDto customer, ProductDto product, Date saleDate, Integer quantity) {
        this.saleId = saleId;
        this.customer = customer;
        this.product = product;
        this.saleDate = saleDate;
        this.quantity = quantity;
    }

    // Getters and setters
    public Long getSaleId() {
        return saleId;
    }

    public void setSaleId(Long saleId) {
        this.saleId = saleId;
    }

    public CustomerDto getCustomer() {
        return customer;
    }

    public void setCustomer(CustomerDto customer) {
        this.customer = customer;
    }

    public ProductDto getProduct() {
        return product;
    }

    public void setProduct(ProductDto product) {
        this.product = product;
    }

    public Date getSaleDate() {
        return saleDate;
    }

    public void setSaleDate(Date saleDate) {
        this.saleDate = saleDate;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }
}
*/
