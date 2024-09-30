package dev.danvega.h2_demo.service.implementation;
import dev.danvega.h2_demo.model.Dto.SaleDto;
import dev.danvega.h2_demo.model.Product;
import dev.danvega.h2_demo.service.SaleService;
import dev.danvega.h2_demo.model.Dto.ProductDto;
import dev.danvega.h2_demo.model.Sale;
import dev.danvega.h2_demo.repository.ProductRepository;
import dev.danvega.h2_demo.repository.SaleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class SaleServiceImpl implements SaleService {

    @Autowired
    private SaleRepository saleRepository;

    @Autowired
    private ProductRepository productRepository;

    @Override
    public List<SaleDto> getAllSales() {
        List<Sale> sales = saleRepository.findAll();
        return sales.stream()
                .map(sale -> {
                    SaleDto dto = new SaleDto();
                    dto.setId(sale.getId());
                    dto.setProductId(sale.getProduct().getId());
                    dto.setCustomerEmail(sale.getCustomerEmail());
                    dto.setSaleDate(sale.getSaleDate());
                    dto.setQuantity(sale.getQuantity());
                    dto.setTotalAmount(sale.getTotalAmount());
                    return dto;
                })
                .collect(Collectors.toList());
    }

    @Override
    public SaleDto createSale(SaleDto saleDto) {
        Sale sale = new Sale();
        Product product = productRepository.findById(saleDto.getProductId()).orElseThrow();
        if (product.getStockQuantity() < saleDto.getQuantity()) {
            throw new IllegalArgumentException("Yetersiz stok miktarı");
        }

        // Toplam tutar hesaplama
        double totalAmount = product.getPrice() * saleDto.getQuantity();
        saleDto.setTotalAmount(totalAmount);


        product.setStockQuantity(product.getStockQuantity() - saleDto.getQuantity()); // Stok güncelleniyor
        sale.setProduct(product);
        sale.setCustomerEmail(saleDto.getCustomerEmail());
        sale.setSaleDate(saleDto.getSaleDate());
        sale.setQuantity(saleDto.getQuantity());
        sale.setTotalAmount(saleDto.getTotalAmount());

        Sale savedSale = saleRepository.save(sale);
        saleDto.setId(savedSale.getId());
        return saleDto;
    }

    @Override
    public SaleDto updateSale(Long id, SaleDto saleDto) {
        Sale sale = saleRepository.findById(id).orElseThrow();
        Product product = productRepository.findById(saleDto.getProductId()).orElseThrow();

        // Toplam tutar hesaplama
        double totalAmount = product.getPrice() * saleDto.getQuantity();
        saleDto.setTotalAmount(totalAmount);


        // Stok güncelleme işlemi
        if (sale.getQuantity() != saleDto.getQuantity()) {
            if (product.getStockQuantity() + sale.getQuantity() < saleDto.getQuantity()) {
                throw new IllegalArgumentException("Yetersiz stok miktarı");
            }
            product.setStockQuantity(product.getStockQuantity() - (saleDto.getQuantity() - sale.getQuantity())); // Stok güncelleniyor
        }

        sale.setProduct(product);
        sale.setCustomerEmail(saleDto.getCustomerEmail());
        sale.setSaleDate(saleDto.getSaleDate());
        sale.setQuantity(saleDto.getQuantity());
        sale.setTotalAmount(saleDto.getTotalAmount());
        Sale updatedSale = saleRepository.save(sale);
        return saleDto;
    }


    @Override
    public void deleteSale(Long id) {
        saleRepository.deleteById(id);
    }

    @Override
    public List<ProductDto> getProductList() {
        List<ProductDto> productDtoList = productRepository.findAll()
                .stream()
                .map(i->new ProductDto(i))
                .collect(Collectors.toUnmodifiableList());
        return productDtoList;
    }

    @Override
    public List<String> getAllCustomerEmails() {
        return saleRepository.findAll().stream()
                .map(Sale::getCustomerEmail)
                .distinct()
                .collect(Collectors.toList());
    }

}


