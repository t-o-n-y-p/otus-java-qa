package org.example.petstore;

import com.fasterxml.jackson.annotation.JsonInclude;
import java.util.List;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 .
 */
@NoArgsConstructor
@Data
@Accessors(chain = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Pet {

  private Long id;
  private IdObject category;
  private String name;
  private List<String> photoUrls;
  private List<IdObject> tags;
  private PetStatus status;

}
