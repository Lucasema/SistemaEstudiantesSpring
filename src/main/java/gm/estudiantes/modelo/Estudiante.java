package gm.estudiantes.modelo;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
// boleirplate - repetitivo
@Data //agrega getters y setters
@NoArgsConstructor //agrega constructor vacio
@AllArgsConstructor //agrega constructor con todos los argumentos
@ToString // agrega metodo toString
public class Estudiante {
    @Id //ES PK
    @GeneratedValue(strategy = GenerationType.IDENTITY) //AUTOINCREMENTABLE
    private Integer idEstudiante;
    private String nombre;
    private String apellido;
    private String telefono;
    private String email;
}
