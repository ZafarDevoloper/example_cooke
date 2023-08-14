package uz.pdp.example;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@NoArgsConstructor
@AllArgsConstructor
@Data
public class FileNameGson {
    private String newName;
    private String originalName;
    private String mimeType;
}
