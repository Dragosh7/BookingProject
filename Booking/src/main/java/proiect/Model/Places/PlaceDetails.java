package proiect.Model.Places;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Lob;
import jakarta.persistence.Transient;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.PositiveOrZero;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.springframework.web.multipart.MultipartFile;
import proiect.Model.AbstractEntity;

import java.time.LocalDate;

import static org.apache.tomcat.util.codec.binary.Base64.encodeBase64String;

@Getter
@Entity
@DynamicUpdate
public class PlaceDetails extends AbstractEntity {
    @NotBlank(message = "Author is required.")
    private String owner;
    @Size(max = 500, message = "Description too long.")
    private String description;
    private LocalDate publishedDate;
    @PositiveOrZero(message = "Value must be greater than 0.")
    private Long persons;
    @PositiveOrZero(message = "Price must be greater or equal to 0.")
    private Long price;

    @Lob
    @Column(length = 999999999)
    private byte[] imageData;

    public PlaceDetails(String owner, String description, LocalDate publishedDate, Long persons, Long price, byte[] imageData) {
        this.owner = owner;
        this.description = description;
        this.publishedDate = publishedDate;
        this.persons = persons;
        this.price = price;
        this.imageData = imageData;
    }

    public PlaceDetails() {
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setPublishedDate(LocalDate publishedDate) {
        this.publishedDate = publishedDate;
    }

    public void setPersons(Long persons) {
        this.persons = persons;
    }

    public void setPrice(Long price) {
        this.price = price;
    }

    public void setImageData(byte[] imageData) {
        this.imageData = imageData;
    }

    public String getBase64imageData() {
        return encodeBase64String(imageData);
    }
}
