package UpdateUser;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data  
@AllArgsConstructor
@NoArgsConstructor
@Builder

public class UserLombok {
	
	private String name;
	private String email;
	private String gender;
	private String status;
	
	
	/* @Data
	 * Generates getters for all fields, a useful toString method, and hashCode and
	 * equals implementations that checkall non-transient fields. Will also generate
	 * setters for all non-final fields, as well as a constructor(except that no
	 * constructor will be generated if any explicitly written constructors already
	 * exist).
	 * 
	 * Equivalent
	 * to @Getter @Setter @RequiredArgsConstructor @ToString @EqualsAndHashCode.
	 */

	
}
