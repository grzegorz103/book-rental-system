package book.system.models;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table (name = "user_roles")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserRole
{
        @Id
        @GeneratedValue (strategy = GenerationType.IDENTITY)
        private Long id;

        @Enumerated (EnumType.STRING)
        private UserType userType;

        public enum UserType
        {
                ROLE_ADMIN,
                ROLE_USER
        }
}
