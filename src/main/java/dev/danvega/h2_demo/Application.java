package dev.danvega.h2_demo;


import dev.danvega.h2_demo.model.Role;
import dev.danvega.h2_demo.model.User;
import dev.danvega.h2_demo.service.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
public class Application {

	public static void main(String[] args) {

		SpringApplication.run(Application.class, args);
	}

	@Bean
	CommandLineRunner commandLineRunner(UserService userService) {
		return args -> {
			Role admin =Role.builder().roleName("ADMIN").build();
			Role user =Role.builder().roleName("USER").build();
			userService.save(user);
			userService.save(admin);

			userService.save(User.builder().name("John").username("jdoe").password(passwordEncoder().encode("1234")).role(admin).build());
			userService.save(User.builder().name("Alice").username("alice").password(passwordEncoder().encode("1234")).role(user).build());

		};
	}
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
}

/*
Bu Java kod parçası, Spring Boot kullanarak bir H2 veritabanı örneği üzerinde çalışan bir uygulamayı göstermektedir. Aşağıda her bir bileşenin ne yaptığını ve ne anlama geldiğini tek tek açıklayalım:

1. package dev.danvega.h2_demo;
- `dev.danvega.h2_demo` paketi içindeki sınıfları gruplar.

2. import dev.danvega.h2_demo.model.products
- `dev.danvega.h2_demo.model` paketinden `products` adında bir model sınıfı (muhtemelen ürünlerle ilgili verileri içeren bir sınıf) `import` edilir.

3. import dev.danvega.h2_demo.repository.productsRepository;
- `dev.danvega.h2_demo.repository` paketinden `productsRepository` adında bir repository (veritabanı işlemlerini yapan sınıf) `import` edilir.

4. import org.springframework.boot.CommandLineRunner;
- Spring Boot uygulamasında komut satırı çalıştırıcı (`CommandLineRunner`) arayüzünü `import` eder. Bu arayüz, uygulama başlatıldığında belirli işlemleri otomatik olarak çalıştırmak için kullanılır.

5. import org.springframework.boot.SpringApplication;
- Spring Boot uygulamasını başlatmak için gerekli olan sınıfı `import` eder.

6. import org.springframework.boot.autoconfigure.SpringBootApplication;
- Spring Boot uygulamasının otomatik yapılandırılmasını sağlayan anotasyon olan `@SpringBootApplication`'ı `import` eder.

7. import org.springframework.context.ConfigurableApplicationContext;
- Yapılandırılabilir bir uygulama bağlamı (application context) sağlayan sınıfı `import` eder.

8. import org.springframework.context.annotation.Bean;
- Spring uygulamasında `@Bean` anotasyonunu kullanarak bir bileşenin (component) yönetimini sağlar.

9. @SpringBootApplication
- Bu anotasyon, Spring Boot uygulamasının ana sınıfı üzerinde kullanılır.
- İçerisinde üç anotasyonu bir arada barındırır: `@Configuration`, `@EnableAutoConfiguration`, ve `@ComponentScan`.
- `@Configuration`: Bu sınıfın bir yapılandırma sınıfı olduğunu belirtir.
- `@EnableAutoConfiguration`: Spring Boot'un otomatik yapılandırma özelliğini etkinleştirir.
- `@ComponentScan`: Spring tarafından yönetilen bileşenlerin (`@Component`, `@Service`, `@Repository`, vb.) otomatik olarak taramasını sağlar.

10. public static void main(String[] args)
- Java uygulamasının giriş noktası olan `main` metodu.
- `SpringApplication.run(Application.class, args);` satırıyla Spring Boot uygulamasını başlatır.

11. @Bean
- Bu anotasyon, Spring konteynerine (application context) yönetimini üstlenecek bir bileşen eklemek için kullanılır.
- `CommandLineRunner commandLineRunner(productsRepository repository)` metodu, bir `CommandLineRunner` nesnesi üretmek için `productsRepository` türünde bir parametre alır.

12. CommandLineRunner commandLineRunner(productsRepository repository)
- `CommandLineRunner` arayüzünü uygulayan bir lambda fonksiyonu döndüren bir metod.
- `repository.save(new products(null,"Iphone 14",69999.99,72));` ve `repository.save(new products(null,"Macbook Air M1 ",26996.99,25));` satırlarıyla, uygulama başladığında `productsRepository` aracılığıyla H2 veritabanına bazı ürünler eklenir.

Bu kod parçası, Spring Boot'u kullanarak basit bir uygulamanın nasıl yapılandırıldığını ve H2 veritabanı üzerinde nasıl işlemler yapılabileceğini göstermektedir. `CommandLineRunner` arayüzü sayesinde uygulama başladığında otomatik olarak veritabanına başlangıç verileri eklenmesi sağlanmıştır.*/

